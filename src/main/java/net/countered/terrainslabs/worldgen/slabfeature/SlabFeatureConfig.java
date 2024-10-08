package net.countered.terrainslabs.worldgen.slabfeature;

import com.mojang.serialization.Codec;
import net.countered.terrainslabs.block.ModSlabsMap;
import net.minecraft.block.Block;
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

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        WorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        ChunkPos chunkPos = new ChunkPos(origin);


        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {

                BlockPos surfacePos = world.getTopPosition(Heightmap.Type.OCEAN_FLOOR_WG, chunkPos.getBlockPos(x, 0, z));

                if (world.getBlockState(surfacePos.down()).isOpaque() && world.getBlockState(surfacePos.down().down()).isOpaque() ) {

                    for (Direction direction : Direction.Type.HORIZONTAL) {

                        BlockPos neighborPos = surfacePos.down().offset(direction);

                        if (!world.getBlockState(neighborPos).isOpaque() && !world.getBlockState(neighborPos.up()).isOpaque()
                                && !world.getBlockState(neighborPos).equals(Blocks.ICE)
                                && !world.getBlockState(neighborPos).equals(Blocks.LAVA)) {

                            BlockPos belowPos = surfacePos.down();
                            BlockState blockBelowState = world.getBlockState(belowPos);
                            Block slabToPlace = ModSlabsMap.getSlabForBlock(blockBelowState.getBlock());

                            BlockState slabState = slabToPlace.getDefaultState();

                            if (world.getBlockState(surfacePos).getBlock() == Blocks.WATER || world.getBlockState(neighborPos).getBlock() == Blocks.WATER) {
                                slabState = slabState.with(Properties.WATERLOGGED, true);
                            }
                            world.setBlockState(belowPos, slabState, 3);
                            break;
                        }
                    }
                }
            }
        }
        return true;
    }
}