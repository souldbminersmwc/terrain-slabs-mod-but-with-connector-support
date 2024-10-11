package net.countered.terrainslabs.worldgen.slabfeature;

import com.mojang.serialization.Codec;
import net.countered.terrainslabs.block.ModSlabsMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class SlabFeatureConfig extends Feature<DefaultFeatureConfig> {

    public SlabFeatureConfig(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    private static BlockState slabstate = Blocks.AIR.getDefaultState();

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        WorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        ChunkPos chunkPos = new ChunkPos(origin);

        for (int y = world.getHeight(); y > 0; y--) {
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    BlockPos currentPos = chunkPos.getBlockPos(x, y, z);
                    BlockState currentBlockState = world.getBlockState(currentPos.down());
                    slabstate = ModSlabsMap.getSlabForBlock(currentBlockState.getBlock()).getDefaultState();

                    // Check conditions to place a slab on top of the current block
                    if (checkSlabTopPlacement(world, currentPos)) {
                        world.setBlockState(currentPos, slabstate, 3);
                    }

                    // Check conditions to place a slab on the underside of the current block
                    /*
                    if (canPlaceSlabUnderneath(currentBlockState, world, currentPos)) {
                        placeSlabUnderneath(currentBlockState, world, currentPos);
                    }

                     */
                }
            }
        }
        return true;
    }

    private boolean canPlaceSlabUnderneath(WorldAccess world, BlockPos pos) {

        return false;
    }
    private void placeSlabUnderneath(BlockState slabState, WorldAccess world, BlockPos pos) {


    }
    private boolean checkSlabTopPlacement(WorldAccess world, BlockPos currentPos) {
        boolean placeSlab = false;
        if(world.getBlockState(currentPos.down()).isOpaque() && !world.getBlockState(currentPos).isOpaque()) {

            for (Direction direction : Direction.Type.HORIZONTAL) {
                BlockPos neighborPos = currentPos.offset(direction);
                slabstate = checkWaterState( world, currentPos, direction);
                //check for a neighboring block being walk-through + blocks where we dont want slabs next to
                if (!(world.getBlockState(neighborPos).getBlock() instanceof SlabBlock) && world.getBlockState(neighborPos).isOpaque() && !world.getBlockState(neighborPos).getBlock().equals(Blocks.ICE) && !world.getBlockState(neighborPos).getBlock().equals(Blocks.LAVA)) {
                    if (world.getBlockState(neighborPos.up()).isOpaque()) {
                        return false;
                    }
                    placeSlab = true;
                }
            }
            return placeSlab;
        }
        return false;
    }
    private BlockState checkWaterState( WorldAccess world, BlockPos pos, Direction direction) {
        BlockPos neighborPos = pos.offset(direction);
        BlockPos surfacePos = pos.up();

        if (slabstate.contains(Properties.WATERLOGGED)) {
            if (world.getBlockState(neighborPos).contains(Properties.WATERLOGGED)) {
                if (world.getBlockState(neighborPos).get(Properties.WATERLOGGED)) {
                    return slabstate.with(Properties.WATERLOGGED, true);
                }
            }
            else if (world.getBlockState(neighborPos).getBlock().equals(Blocks.WATER)) {
                return slabstate.with(Properties.WATERLOGGED, true);
            }
            else if (world.getBlockState(surfacePos).contains(Properties.WATERLOGGED)) {
                if (world.getBlockState(surfacePos).get(Properties.WATERLOGGED)) {
                    return slabstate.with(Properties.WATERLOGGED, true);
                }
            } else if (world.getBlockState(surfacePos).getBlock().equals(Blocks.WATER)) {
                return slabstate.with(Properties.WATERLOGGED, true);
            }
        }
        return slabstate;
    }
}
