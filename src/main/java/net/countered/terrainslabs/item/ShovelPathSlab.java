package net.countered.terrainslabs.item;

import net.countered.terrainslabs.block.ModBlocksRegistry;
import net.fabricmc.fabric.mixin.content.registry.ShovelItemAccessor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.enums.SlabType;

import java.util.Properties;

import static net.minecraft.block.SlabBlock.TYPE;


public class ShovelPathSlab {

    public static void init() {
        // Register each slab type with the default path slab state.
        registerSlabPath(ModBlocksRegistry.DIRT_SLAB);
        registerSlabPath(ModBlocksRegistry.GRASS_SLAB);
        registerSlabPath(ModBlocksRegistry.MYCELIUM_SLAB);
        registerSlabPath(ModBlocksRegistry.PODZOL_SLAB);
    }

    private static void registerSlabPath(Block originalSlab) {
        // Register the slab with its default path slab block state in PATH_STATES.
        ShovelItemAccessor.getPathStates().put(originalSlab, ModBlocksRegistry.PATH_SLAB.getDefaultState());
    }
}



