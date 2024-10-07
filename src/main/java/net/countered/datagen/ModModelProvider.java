package net.countered.datagen;

import net.countered.terrainslabs.block.ModBlocksRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.data.client.*;
import net.minecraft.item.Item;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        //BlockStateModelGenerator.BlockTexturePool grassPool = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.GRASS_BLOCK);
        //blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.GRASS_BLOCK).slab(ModBlocksRegistry.GRASS_SLAB);
        //grassPool.slab(ModBlocksRegistry.GRASS_SLAB);

        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.GRAVEL).slab(ModBlocksRegistry.GRAVEL_SLAB);
        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.SAND).slab(ModBlocksRegistry.SAND_SLAB);
        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.RED_SAND).slab(ModBlocksRegistry.RED_SAND_SLAB);

        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.DIRT).slab(ModBlocksRegistry.DIRT_SLAB);
        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.MUD).slab(ModBlocksRegistry.MUD_SLAB);

        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.TERRACOTTA).slab(ModBlocksRegistry.TERRACOTTA_SLAB);
        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.BROWN_TERRACOTTA).slab(ModBlocksRegistry.BROWN_TERRACOTTA_SLAB);
        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.ORANGE_TERRACOTTA).slab(ModBlocksRegistry.ORANGE_TERRACOTTA_SLAB);
        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.WHITE_TERRACOTTA).slab(ModBlocksRegistry.WHITE_TERRACOTTA_SLAB);
        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.RED_TERRACOTTA).slab(ModBlocksRegistry.RED_TERRACOTTA_SLAB);
        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.YELLOW_TERRACOTTA).slab(ModBlocksRegistry.YELLOW_TERRACOTTA_SLAB);
        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.LIGHT_GRAY_TERRACOTTA).slab(ModBlocksRegistry.LIGHT_GRAY_TERRACOTTA_SLAB);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}
