package net.countered.terrainslabs.worldgen.slabfeature;

import com.mojang.serialization.Codec;
import net.countered.terrainslabs.block.ModSlabsMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
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


        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {

                BlockPos surfacePos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE_WG, chunkPos.getBlockPos(x, 0, z));

                if (world.getBlockState(surfacePos.down()).isOpaqueFullCube(world, surfacePos.down()) && world.getBlockState(surfacePos.down().down()).isOpaqueFullCube(world, surfacePos.down().down())) {

                    for (Direction direction : Direction.Type.HORIZONTAL) {

                        BlockPos neighborPos = surfacePos.down().offset(direction);

                        if (!world.getBlockState(neighborPos).isOpaque() && world.getBlockState(neighborPos.up()).isAir()) {
                            BlockPos belowPos = surfacePos.down();
                            BlockState blockBelowState = world.getBlockState(belowPos);
                            Block slabToPlace = ModSlabsMap.getSlabForBlock(blockBelowState.getBlock());

                            world.setBlockState(surfacePos.down(), slabToPlace.getDefaultState(), 3);
                            world.setBlockState(surfacePos, Blocks.AIR.getDefaultState(), 3);

                            break;
                        }
                    }
                }
            }
        }
        return true;
    }
}