package net.countered.datagen;

import net.countered.terrainslabs.block.ModBlocksRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.data.server.tag.TagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagsProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.SLABS)
                .add(ModBlocksRegistry.DIRT_SLAB)
                .add(ModBlocksRegistry.MUD_SLAB)
                .add(ModBlocksRegistry.COARSE_SLAB)
                .add(ModBlocksRegistry.SNOW_SLAB)
                .add(ModBlocksRegistry.PACKED_ICE_SLAB)
                .add(ModBlocksRegistry.DEEPSLATE_SLAB)
                .add(ModBlocksRegistry.CLAY_SLAB)
                .add(ModBlocksRegistry.MOSS_SLAB)
                .add(ModBlocksRegistry.GRASS_SLAB)
                .add(ModBlocksRegistry.MYCELIUM_SLAB)
                .add(ModBlocksRegistry.PODZOL_SLAB)
                .add(ModBlocksRegistry.GRAVEL_SLAB)
                .add(ModBlocksRegistry.SAND_SLAB)
                .add(ModBlocksRegistry.RED_SAND_SLAB)
                .add(ModBlocksRegistry.TERRACOTTA_SLAB)
                .add(ModBlocksRegistry.RED_TERRACOTTA_SLAB)
                .add(ModBlocksRegistry.ORANGE_TERRACOTTA_SLAB)
                .add(ModBlocksRegistry.LIGHT_GRAY_TERRACOTTA_SLAB)
                .add(ModBlocksRegistry.WHITE_TERRACOTTA_SLAB)
                .add(ModBlocksRegistry.BROWN_TERRACOTTA_SLAB)
                .add(ModBlocksRegistry.YELLOW_TERRACOTTA_SLAB);
    }
}
