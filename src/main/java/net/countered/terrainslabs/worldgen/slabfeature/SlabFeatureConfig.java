package net.countered.terrainslabs.worldgen.slabfeature;

import com.mojang.serialization.Codec;
import net.countered.terrainslabs.block.ModSlabsMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.registry.tag.BlockTags;
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

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        WorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        ChunkPos chunkPos = new ChunkPos(origin);
        int count = 0;

        //check chunk surface
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                //get surface position
                BlockPos surfacePos = world.getTopPosition(Heightmap.Type.OCEAN_FLOOR_WG, chunkPos.getBlockPos(x, 0, z));
                //check whether top block has air next to it and no blocks above (to avoid changing where trees are placed)
                if (world.getBlockState(surfacePos.down()).isOpaque() && !world.getBlockState(surfacePos).isOpaque() && world.getBlockState(surfacePos.down().down()).isOpaque()) {

                    BlockPos placePos = surfacePos.down();
                    BlockState blockBelowState = world.getBlockState(placePos);
                    BlockState slabState = ModSlabsMap.getSlabForBlock(blockBelowState.getBlock()).getDefaultState();

                    for (Direction direction : Direction.Type.HORIZONTAL) {
                        BlockPos neighborPos = surfacePos.down().offset(direction);
                        if (slabState.contains(Properties.WATERLOGGED)) {
                            if (world.getBlockState(neighborPos).contains(Properties.WATERLOGGED)) {
                                if (world.getBlockState(neighborPos).get(Properties.WATERLOGGED)) {
                                    slabState = slabState.with(Properties.WATERLOGGED, true);
                                }
                            }
                            else if (world.getBlockState(neighborPos).getBlock().equals(Blocks.WATER)) {
                                slabState = slabState.with(Properties.WATERLOGGED, true);
                            }
                        }
                        //check for a neighboring block being walk-through + blocks where we dont want slabs next to
                        if (!world.getBlockState(neighborPos).isOpaque() && !world.getBlockState(neighborPos).getBlock().equals(Blocks.ICE) && !world.getBlockState(neighborPos).getBlock().equals(Blocks.LAVA)) {
                            if (world.getBlockState(neighborPos.up()).isOpaque()) {
                                count = 0;
                                break;
                            }
                            count++;
                        }
                    }
                    if (count != 0){
                        if (slabState.contains(Properties.WATERLOGGED)) {
                            if (world.getBlockState(surfacePos).contains(Properties.WATERLOGGED)) {
                                if (world.getBlockState(surfacePos).get(Properties.WATERLOGGED)) {
                                    slabState = slabState.with(Properties.WATERLOGGED, true);
                                }
                            } else if (world.getBlockState(surfacePos).getBlock().equals(Blocks.WATER)) {
                                slabState = slabState.with(Properties.WATERLOGGED, true);
                            }
                        }
                        world.setBlockState(placePos, slabState, 3);
                    }
                }
            }
        }
        return true;
    }
}
