package net.countered.terrainslabs.worldgen.slabfeature;

import com.mojang.serialization.Codec;
import net.countered.terrainslabs.block.ModBlocksRegistry;
import net.countered.terrainslabs.block.ModSlabsMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class SlabFeatureConfig extends Feature<DefaultFeatureConfig> {

    public SlabFeatureConfig(Codec<DefaultFeatureConfig> codec) {
        super(codec);
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

                    // If no valid slab exists for the block or if it's already a slab, skip this position
                    if (ModSlabsMap.getSlabForBlock(world.getBlockState(currentPos.down()).getBlock()) == Blocks.AIR || world.getBlockState(currentPos.down()).getBlock() instanceof SlabBlock){
                        continue;
                    }

                    // Check conditions to place a slab on top of the current block
                    if (shouldPlaceSlab(world, currentPos)) {
                        if (world.getBlockState(currentPos).equals(Blocks.SNOW.getDefaultState())){
                            world.setBlockState(currentPos, ModBlocksRegistry.GRASS_SLAB.getDefaultState().with(Properties.SNOWY, true), 3);
                            continue;
                        }
                        BlockState blockBelowState = world.getBlockState(currentPos.down());

                        // Retrieve the slab type based on the block below the current position
                        BlockState slabState = ModSlabsMap.getSlabForBlock(blockBelowState.getBlock()).getDefaultState();

                        // Handle grass slab special case by converting grass to dirt before placing the slab
                        if (slabState.getBlock().equals(ModBlocksRegistry.GRASS_SLAB)) {
                            world.setBlockState(currentPos.down(), Blocks.DIRT.getDefaultState(), 3);
                        }
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
    private boolean shouldPlaceSlab(WorldAccess world, BlockPos currentPos) {
        BlockState blockBelow = world.getBlockState(currentPos.down());
        BlockState blockAbove = world.getBlockState(currentPos.up());
        BlockState currentBlockState = world.getBlockState(currentPos);

        // Check that the block below is opaque and not a slab, and the position is air or snow
        if (blockBelow.isOpaque() && !(blockBelow.getBlock() instanceof SlabBlock)
                && (!currentBlockState.isOpaque() || currentBlockState.getBlock() == Blocks.SNOW || currentBlockState.isReplaceable())
                && (blockAbove.isAir() || blockAbove.getBlock() == Blocks.WATER)) {

            // Check neighboring blocks to ensure at least one opaque block is adjacent
            for (Direction direction : Direction.Type.HORIZONTAL) {
                BlockPos neighborPos = currentPos.offset(direction);
                BlockState neighborState = world.getBlockState(neighborPos);

                // Check if a neighboring block is opaque and not a slab
                if (neighborState.isOpaque()
                        && !(neighborState.getBlock() instanceof SlabBlock)
                        && (!world.getBlockState(neighborPos.up()).isOpaque() || world.getBlockState(neighborPos.up()).getBlock() == Blocks.SNOW)
                        && !world.getBlockState(neighborPos).equals(Blocks.SNOW.getDefaultState())) {

                    for (Direction direction2 : Direction.Type.HORIZONTAL) {
                        if (world.getBlockState(currentPos.offset(direction2).down()).getBlock() instanceof SlabBlock
                                || world.getBlockState(currentPos.offset(direction2)).getBlock().equals(Blocks.ICE)) {
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Updates the slab state to be waterlogged if applicable.
     */
    private BlockState updateWaterloggedState(WorldAccess world, BlockPos pos, BlockState slabState) {

        if (slabState.contains(Properties.WATERLOGGED)) {
            for (Direction direction1 : Direction.Type.HORIZONTAL) {
                // Check if the neighbor or the block above contains water to set the waterlogged property
                if (world.getBlockState(pos.offset(direction1)).getFluidState().isStill() ||
                        world.getBlockState(pos.offset(direction1)).getFluidState().isStill()) {
                    return slabState.with(Properties.WATERLOGGED, true);
                }
            }
        }
        return slabState;
    }
}

