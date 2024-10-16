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
                        if (world.getBlockState(currentPos).equals(Blocks.SNOW.getDefaultState())){
                            world.setBlockState(currentPos, ModBlocksRegistry.SNOW_SLAB.getDefaultState(), 3);
                            continue;
                        }
                        BlockState blockBelowState = world.getBlockState(currentPos.down());

                        // Retrieve the slab type based on the block below the current position
                        BlockState slabState = ModSlabsMap.getSlabForBlock(blockBelowState.getBlock()).getDefaultState();

                        // Handle grass slab special case by converting grass to dirt before placing the slab
                        if (slabState.getBlock().equals(ModBlocksRegistry.GRASS_SLAB)
                                || slabState.getBlock().equals(ModBlocksRegistry.PODZOL_SLAB)
                                || slabState.getBlock().equals(ModBlocksRegistry.MYCELIUM_SLAB)) {
                            world.setBlockState(currentPos.down(), Blocks.DIRT.getDefaultState(), 3);
                        }

                        slabState = updateWaterloggedState(world, currentPos, slabState);
                        world.setBlockState(currentPos, slabState, 3);
                        continue;
                    }


                    if (shouldPlaceSlabOnUnderside(world, currentPos)) {
                        BlockPos blockAbovePos = currentPos.up();
                        BlockState blockAboveState = world.getBlockState(blockAbovePos);
                        BlockState slabState = ModSlabsMap.getSlabForBlock(blockAboveState.getBlock()).getDefaultState();

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
        if (blockBelow.isOpaque() && !(blockBelow.getBlock() instanceof SlabBlock)
                && (!currentBlockState.isOpaque() || currentBlockState.getBlock() == Blocks.SNOW || currentBlockState.isReplaceable())
                && (blockAbove.isAir() || blockAbove.getBlock() == Blocks.WATER)) {

            // Check neighboring blocks to ensure at least one opaque block is adjacent
            for (Direction direction : Direction.Type.HORIZONTAL) {
                BlockPos neighborPos = currentPos.offset(direction);
                BlockState neighborState = world.getBlockState(neighborPos);

                // Check if a neighboring block is opaque and not a slab
                if (neighborState.isOpaque() && VALID_BLOCKS_FOR_SLAB_PLACEMENT.contains(neighborState.getBlock())
                        && !(neighborState.getBlock() instanceof SlabBlock)
                        && (!world.getBlockState(neighborPos.up()).isOpaque() || world.getBlockState(neighborPos.up()).getBlock() == Blocks.SNOW)
                        && !world.getBlockState(neighborPos).equals(Blocks.SNOW.getDefaultState())) {

                    for (Direction direction2 : Direction.Type.HORIZONTAL) {
                        if (world.getBlockState(currentPos.offset(direction2)).getBlock().equals(Blocks.ICE) || world.getBlockState(currentPos.offset(direction2)).getBlock().equals(Blocks.LAVA)) {
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private boolean shouldPlaceSlabOnUnderside(WorldAccess world, BlockPos currentPos) {
        BlockState blockAbove = world.getBlockState(currentPos.up());
        BlockState currentBlock = world.getBlockState(currentPos);
        BlockState blockBelow = world.getBlockState(currentPos.down());
        boolean place = false;

        if (ModSlabsMap.getSlabForBlock(world.getBlockState(currentPos.up()).getBlock()) == Blocks.AIR || world.getBlockState(currentPos.up()).getBlock() instanceof SlabBlock){
            return false;
        }
        // Check that the block above is a valid block for slab placement and that the current block is air or water
        if (VALID_BLOCKS_FOR_SLAB_PLACEMENT.contains(blockAbove.getBlock())
                && (blockBelow.isAir() || blockBelow.getBlock() == Blocks.WATER)
                && currentBlock.isOpaque()) {

            // Check neighboring blocks to ensure at least one horizontal neighbor is air or water
            for (Direction direction : Direction.Type.HORIZONTAL) {
                BlockPos neighborPos = currentPos.offset(direction);
                BlockState neighborState = world.getBlockState(neighborPos);

                // If at least one horizontal neighbor is air or water, mark this position for slab placement
                if (neighborState.isAir() || neighborState.getBlock() == Blocks.WATER) {
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
            if (world.getBlockState(pos.up()) == Blocks.WATER.getDefaultState() || world.getBlockState(pos.down()) == Blocks.WATER.getDefaultState()) {
                return slabState.with(Properties.WATERLOGGED, true);
            }
            for (Direction direction1 : Direction.Type.HORIZONTAL) {
                // Check if the neighbor or the block above contains water to set the waterlogged property
                if (world.getBlockState(pos.offset(direction1)) == Blocks.WATER.getDefaultState()) {
                    return slabState.with(Properties.WATERLOGGED, true);
                }
            }
        }
        return slabState;
    }
}

