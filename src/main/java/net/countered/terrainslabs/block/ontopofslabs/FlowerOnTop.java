package net.countered.terrainslabs.block.ontopofslabs;

import net.countered.terrainslabs.block.ModBlocksRegistry;
import net.countered.terrainslabs.block.customslabs.soilslabs.GrassSlab;
import net.countered.terrainslabs.block.customslabs.soilslabs.MyceliumSlab;
import net.minecraft.block.*;
import net.minecraft.block.enums.SlabType;
import net.minecraft.component.type.SuspiciousStewEffectsComponent;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

import java.util.HashSet;
import java.util.Set;

import static net.minecraft.block.SlabBlock.TYPE;

public class FlowerOnTop extends FlowerBlock implements SuspiciousStewIngredient {

    protected static final VoxelShape SHAPE = Block.createCuboidShape(5.0, -8.0, 5.0, 11.0, 2.0, 11.0);

    public FlowerOnTop(RegistryEntry<StatusEffect> stewEffect, float effectLengthInSeconds, Settings settings) {
        super(stewEffect, effectLengthInSeconds, settings);
    }
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Vec3d vec3d = state.getModelOffset(pos);
        return SHAPE.offset(vec3d.x, vec3d.y, vec3d.z);
    }
    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos.down());
        if (blockState.getBlock().equals(ModBlocksRegistry.GRASS_SLAB) || blockState.getBlock().equals(ModBlocksRegistry.MYCELIUM_SLAB)
            || blockState.getBlock().equals(ModBlocksRegistry.PODZOL_SLAB) || blockState.getBlock().equals(ModBlocksRegistry.COARSE_SLAB)
            || blockState.getBlock().equals(ModBlocksRegistry.DIRT_SLAB) || blockState.getBlock().equals(ModBlocksRegistry.MOSS_SLAB)
            || blockState.getBlock().equals(ModBlocksRegistry.MUD_SLAB)) {
            if (blockState.get(TYPE).equals(SlabType.BOTTOM)) {
                return true;
            }
        }
        return false;
    }
}
