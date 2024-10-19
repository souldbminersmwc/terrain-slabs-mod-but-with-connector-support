package net.countered.terrainslabs.worldgen.slabfeature;

import com.mojang.serialization.Codec;
import net.countered.terrainslabs.block.ModBlocksRegistry;
import net.countered.terrainslabs.block.ModSlabsMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.HashSet;
import java.util.Set;

public class SlabFeatureConfig extends Feature<DefaultFeatureConfig> {

    public SlabFeatureConfig(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }
    public static final Set<Block> VALID_BLOCKS_FOR_SLAB_PLACEMENT = new HashSet<>();

    static {

        VALID_BLOCKS_FOR_SLAB_PLACEMENT.add(Blocks.GRASS_BLOCK);
        VALID_BLOCKS_FOR_SLAB_PLACEMENT.add(Blocks.PODZOL);
        VALID_BLOCKS_FOR_SLAB_PLACEMENT.add(Blocks.MYCELIUM);

        VALID_BLOCKS_FOR_SLAB_PLACEMENT.add(Blocks.DIRT);
        VALID_BLOCKS_FOR_SLAB_PLACEMENT.add(Blocks.MOSS_BLOCK);
        VALID_BLOCKS_FOR_SLAB_PLACEMENT.add(Blocks.PACKED_ICE);
        VALID_BLOCKS_FOR_SLAB_PLACEMENT.add(Blocks.COARSE_DIRT);
        VALID_BLOCKS_FOR_SLAB_PLACEMENT.add(Blocks.MUD);
        VALID_BLOCKS_FOR_SLAB_PLACEMENT.add(Blocks.SNOW_BLOCK);
        VALID_BLOCKS_FOR_SLAB_PLACEMENT.add(Blocks.SANDSTONE);
        VALID_BLOCKS_FOR_SLAB_PLACEMENT.add(Blocks.CLAY);
        VALID_BLOCKS_FOR_SLAB_PLACEMENT.add(Blocks.DEEPSLATE);

        VALID_BLOCKS_FOR_SLAB_PLACEMENT.add(Blocks.STONE);
        VALID_BLOCKS_FOR_SLAB_PLACEMENT.add(Blocks.ANDESITE);
        VALID_BLOCKS_FOR_SLAB_PLACEMENT.add(Blocks.DIORITE);
        VALID_BLOCKS_FOR_SLAB_PLACEMENT.add(Blocks.GRANITE);
        VALID_BLOCKS_FOR_SLAB_PLACEMENT.add(Blocks.TUFF);

        VALID_BLOCKS_FOR_SLAB_PLACEMENT.add(Blocks.SAND);
        VALID_BLOCKS_FOR_SLAB_PLACEMENT.add(Blocks.GRAVEL);
        VALID_BLOCKS_FOR_SLAB_PLACEMENT.add(Blocks.RED_SAND);

        VALID_BLOCKS_FOR_SLAB_PLACEMENT.add(Blocks.TERRACOTTA);
        VALID_BLOCKS_FOR_SLAB_PLACEMENT.add(Blocks.BROWN_TERRACOTTA);
        VALID_BLOCKS_FOR_SLAB_PLACEMENT.add(Blocks.RED_TERRACOTTA);
        VALID_BLOCKS_FOR_SLAB_PLACEMENT.add(Blocks.ORANGE_TERRACOTTA);
        VALID_BLOCKS_FOR_SLAB_PLACEMENT.add(Blocks.LIGHT_GRAY_TERRACOTTA);
        VALID_BLOCKS_FOR_SLAB_PLACEMENT.add(Blocks.YELLOW_TERRACOTTA);
        VALID_BLOCKS_FOR_SLAB_PLACEMENT.add(Blocks.WHITE_TERRACOTTA);
    }
    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        WorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        ChunkPos chunkPos = new ChunkPos(origin);

        // Iterate through the entire chunk (from bottom to height limit)
        for (int y = world.getBottomY(); y < world.getHeight(); y++) {
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    BlockPos currentPos = chunkPos.getBlockPos(x, y, z);

                    // Check conditions to place a slab on top of the current block
                    if (shouldPlaceSlabTop(world, currentPos)) {
                        if (world.getBlockState(currentPos).isOf(Blocks.SNOW)){
                            world.setBlockState(currentPos, ModBlocksRegistry.SNOW_SLAB.getDefaultState(), 3);
                            continue;
                        }
                        BlockState blockBelowState = world.getBlockState(currentPos.down());

                        // Retrieve the slab type based on the block below the current position
                        BlockState slabState = ModSlabsMap.getSlabForBlock(blockBelowState.getBlock()).getDefaultState();

                        // Handle grass slab special case by converting grass to dirt before placing the slab
                        if (slabState.isOf(ModBlocksRegistry.GRASS_SLAB)
                                || slabState.isOf(ModBlocksRegistry.PODZOL_SLAB)
                                || slabState.isOf(ModBlocksRegistry.MYCELIUM_SLAB)) {
                            world.setBlockState(currentPos.down(), Blocks.DIRT.getDefaultState(), 3);
                        }

                        slabState = updateWaterloggedState(world, currentPos, slabState);
                        world.setBlockState(currentPos, slabState, 3);
                        continue;
                    }


                    if (shouldPlaceSlabOnUnderside(world, currentPos)) {
                        BlockState previousBlockState = world.getBlockState(currentPos);
                        BlockState slabState = ModSlabsMap.getSlabForBlock(previousBlockState.getBlock()).getDefaultState();

                        if (slabState.isOf(ModBlocksRegistry.GRASS_SLAB)
                                || slabState.isOf(ModBlocksRegistry.PODZOL_SLAB)
                                || slabState.isOf(ModBlocksRegistry.MYCELIUM_SLAB)) {
                            slabState = ModBlocksRegistry.DIRT_SLAB.getDefaultState();
                        }
                        slabState = slabState.with(Properties.SLAB_TYPE, SlabType.TOP);
                        slabState = updateWaterloggedState(world, currentPos, slabState);
                        world.setBlockState(currentPos, slabState, 3);
                    }
                }
            }
        }
        return true;
    }

    /**
     * Determines if a slab should be placed at the given position based on world conditions.
     */
    private boolean shouldPlaceSlabTop(WorldAccess world, BlockPos currentPos) {
        BlockState blockBelow = world.getBlockState(currentPos.down());
        BlockState blockAbove = world.getBlockState(currentPos.up());
        BlockState currentBlockState = world.getBlockState(currentPos);

        if (ModSlabsMap.getSlabForBlock(world.getBlockState(currentPos.down()).getBlock()) == Blocks.AIR || world.getBlockState(currentPos.down()).getBlock() instanceof SlabBlock){
            return false;
        }
        // Check that the block below is opaque and not a slab, and the position is air or snow
        if (blockBelow.isOpaque() && !(blockBelow.getBlock() instanceof SlabBlock) && bottomOfMountain(world, currentPos)
                && (!currentBlockState.isOpaque() || currentBlockState.isOf(Blocks.SNOW) || currentBlockState.isReplaceable())
                && (blockAbove.isAir() || blockAbove.isOf(Blocks.WATER))) {

            // Check neighboring blocks to ensure at least one opaque block is adjacent
            for (Direction direction : Direction.Type.HORIZONTAL) {
                if (badNextToWaterSlab(world, currentPos, direction) ||
                        world.getBlockState(currentPos.offset(direction)).isOf(Blocks.ICE) ||
                        world.getBlockState(currentPos.offset(direction)).isOf(Blocks.LAVA)) {
                    return false;
                }
            }

            for (Direction direction1 : Direction.Type.HORIZONTAL) {
                BlockPos neighborPos = currentPos.offset(direction1);
                BlockState neighborState = world.getBlockState(neighborPos);
                // Check if a neighboring block is opaque and not a slab
                if (neighborState.isOpaque() && VALID_BLOCKS_FOR_SLAB_PLACEMENT.contains(neighborState.getBlock())
                        && !(neighborState.getBlock() instanceof SlabBlock)
                        && (!world.getBlockState(neighborPos.up()).isOpaque() || world.getBlockState(neighborPos.up()).getBlock() == Blocks.SNOW)
                        && !world.getBlockState(neighborPos).isOf(Blocks.SNOW)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean shouldPlaceSlabOnUnderside(WorldAccess world, BlockPos currentPos) {
        BlockState currentBlock = world.getBlockState(currentPos);
        BlockState blockBelow = world.getBlockState(currentPos.down());
        boolean place = false;

        if (ModSlabsMap.getSlabForBlock(world.getBlockState(currentPos.up()).getBlock()) == Blocks.AIR || world.getBlockState(currentPos.up()).getBlock() instanceof SlabBlock || nextToGlowLichen(world, currentPos)){
            return false;
        }
        // Check that the block above is a valid block for slab placement and that the current block is air or water
        if (VALID_BLOCKS_FOR_SLAB_PLACEMENT.contains(currentBlock.getBlock())
                && (blockBelow.isAir() || blockBelow.getBlock() == Blocks.WATER)
                && currentBlock.isOpaque()) {

            // Check neighboring blocks to ensure at least one horizontal neighbor is air or water
            for (Direction direction : Direction.Type.HORIZONTAL) {
                BlockPos neighborPos = currentPos.offset(direction);
                BlockState neighborState = world.getBlockState(neighborPos);
                if (badNextToWaterSlab(world, currentPos, direction)) {
                    return false;
                }

                // If at least one horizontal neighbor is air or water, mark this position for slab placement
                if ((neighborState.isAir() || neighborState.getBlock() == Blocks.WATER) && !neighborState.isOf(Blocks.LAVA)) {
                    place = true;
                }
            }
        }
        return place;
    }


    /**
     * Updates the slab state to be waterlogged if applicable.
     */
    private BlockState updateWaterloggedState(WorldAccess world, BlockPos pos, BlockState slabState) {

        if (slabState.contains(Properties.WATERLOGGED)) {
            if (world.getBlockState(pos).isOf(Blocks.WATER) || world.getBlockState(pos.up()).isOf(Blocks.WATER)) {
                return slabState.with(Properties.WATERLOGGED, true);
            }
            for (Direction direction1 : Direction.Type.HORIZONTAL) {
                // Check if the neighbor or the block above contains water to set the waterlogged property
                if (world.getBlockState(pos.offset(direction1)).isOf(Blocks.WATER)) {
                    return slabState.with(Properties.WATERLOGGED, true);
                }
            }
        }
        return slabState;
    }

    private Boolean badNextToWaterSlab(WorldAccess world, BlockPos currentPos, Direction direction) {
        if (world.getBlockState(currentPos.offset(direction)).isOf(Blocks.WATER) &&
                (world.getBlockState(currentPos.down()).isOf(Blocks.AIR) || world.getBlockState(currentPos.offset(direction.getOpposite())).getBlock() == Blocks.AIR)) {
            return true;
        }
        return false;
    }

    private boolean bottomOfMountain(WorldAccess world, BlockPos currentPos) {
        for (Direction direction : Direction.Type.HORIZONTAL) {
            if (world.getBlockState(currentPos.offset(direction).down()).isOpaque() && world.getBlockState(currentPos.offset(direction.getOpposite())).isOpaque()
            && !(world.getBlockState(currentPos.offset(direction).down()).getBlock() instanceof SlabBlock) && !(world.getBlockState(currentPos.offset(direction.getOpposite())).getBlock() instanceof SlabBlock))
            {
                return true;
            }
        }
        return false;
    }

    private boolean nextToGlowLichen(WorldAccess world, BlockPos currentPos) {
        for (Direction direction : Direction.Type.HORIZONTAL) {
            if (world.getBlockState(currentPos.offset(direction)).isOf(Blocks.GLOW_LICHEN)){
                return true;
            }
        }
        return false;
    }
}
