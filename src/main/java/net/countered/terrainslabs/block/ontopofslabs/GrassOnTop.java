package net.countered.terrainslabs.block.ontopofslabs;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.ShortPlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class GrassOnTop extends ShortPlantBlock {
    protected static final VoxelShape SHAPE = Block.createCuboidShape(2.0, -8.0, 2.0, 14.0, 5.0, 14.0);

    public GrassOnTop(Settings settings) {
        super(settings);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}
