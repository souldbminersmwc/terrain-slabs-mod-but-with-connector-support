package net.countered.datagen;

import com.google.gson.JsonElement;
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

import static net.minecraft.data.client.BlockStateModelGenerator.createSlabBlockState;


public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.GRAVEL).slab(ModBlocksRegistry.GRAVEL_SLAB);
        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.SAND).slab(ModBlocksRegistry.SAND_SLAB);
        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.RED_SAND).slab(ModBlocksRegistry.RED_SAND_SLAB);

        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.DIRT).slab(ModBlocksRegistry.DIRT_SLAB);
        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.MUD).slab(ModBlocksRegistry.MUD_SLAB);
        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.PACKED_ICE).slab(ModBlocksRegistry.PACKED_ICE_SLAB);
        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.COARSE_DIRT).slab(ModBlocksRegistry.COARSE_SLAB);
        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.SNOW_BLOCK).slab(ModBlocksRegistry.SNOW_SLAB);

        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.TERRACOTTA).slab(ModBlocksRegistry.TERRACOTTA_SLAB);
        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.BROWN_TERRACOTTA).slab(ModBlocksRegistry.BROWN_TERRACOTTA_SLAB);
        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.ORANGE_TERRACOTTA).slab(ModBlocksRegistry.ORANGE_TERRACOTTA_SLAB);
        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.WHITE_TERRACOTTA).slab(ModBlocksRegistry.WHITE_TERRACOTTA_SLAB);
        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.RED_TERRACOTTA).slab(ModBlocksRegistry.RED_TERRACOTTA_SLAB);
        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.YELLOW_TERRACOTTA).slab(ModBlocksRegistry.YELLOW_TERRACOTTA_SLAB);
        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.LIGHT_GRAY_TERRACOTTA).slab(ModBlocksRegistry.LIGHT_GRAY_TERRACOTTA_SLAB);

        //registerTopSoilsSlabs(blockStateModelGenerator);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }

    private void registerTopSoilsSlabs(BlockStateModelGenerator blockStateModelGenerator) {
        Identifier identifier = TextureMap.getId(ModBlocksRegistry.DIRT_SLAB);
        TextureMap textureMap = new TextureMap()
                .put(TextureKey.BOTTOM, identifier)
                .inherit(TextureKey.BOTTOM, TextureKey.PARTICLE)
                .put(TextureKey.TOP, TextureMap.getSubId(ModBlocksRegistry.GRASS_SLAB, "_top"))
                .put(TextureKey.SIDE, TextureMap.getSubId(ModBlocksRegistry.GRASS_SLAB, "_snow"));
        BlockStateVariant blockStateVariant = BlockStateVariant.create()
                .put(VariantSettings.MODEL, Models.CUBE_BOTTOM_TOP.upload(ModBlocksRegistry.GRASS_SLAB, "_snow", textureMap, blockStateModelGenerator.modelCollector));
        blockStateModelGenerator.registerTopSoil(ModBlocksRegistry.GRASS_SLAB, ModelIds.getBlockModelId(ModBlocksRegistry.GRASS_SLAB), blockStateVariant);
        Identifier identifier2 = TexturedModel.CUBE_BOTTOM_TOP
                .get(ModBlocksRegistry.MYCELIUM_SLAB)
                .textures(textures -> textures.put(TextureKey.BOTTOM, identifier))
                .upload(ModBlocksRegistry.MYCELIUM_SLAB, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.registerTopSoil(ModBlocksRegistry.MYCELIUM_SLAB, identifier2, blockStateVariant);
        Identifier identifier3 = TexturedModel.CUBE_BOTTOM_TOP
                .get(ModBlocksRegistry.PODZOL_SLAB)
                .textures(textures -> textures.put(TextureKey.BOTTOM, identifier))
                .upload(ModBlocksRegistry.PODZOL_SLAB, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.registerTopSoil(ModBlocksRegistry.PODZOL_SLAB, identifier3, blockStateVariant);
    }
}

