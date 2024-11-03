package net.countered.datagen;

import net.countered.terrainslabs.block.ModBlocksRegistry;
import net.countered.terrainslabs.item.ModItemGroups;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataOutput;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {


    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        createSlabRecipe3(RecipeCategory.BUILDING_BLOCKS, ModBlocksRegistry.DIRT_SLAB, Ingredient.ofItems(Blocks.DIRT)).criterion("has_dirt_block", conditionsFromItem(Blocks.DIRT)).offerTo(exporter);
        createSlabRecipe3(RecipeCategory.BUILDING_BLOCKS, ModBlocksRegistry.MUD_SLAB, Ingredient.ofItems(Blocks.MUD)).criterion("has_mud_block", conditionsFromItem(Blocks.MUD)).offerTo(exporter);
        createSlabRecipe3(RecipeCategory.BUILDING_BLOCKS, ModBlocksRegistry.COARSE_SLAB, Ingredient.ofItems(Blocks.COARSE_DIRT)).criterion("has_coarse_dirt_block", conditionsFromItem(Blocks.COARSE_DIRT)).offerTo(exporter);
        createSlabRecipe3(RecipeCategory.BUILDING_BLOCKS, ModBlocksRegistry.SNOW_SLAB, Ingredient.ofItems(Blocks.SNOW_BLOCK)).criterion("has_snow_block", conditionsFromItem(Blocks.SNOW_BLOCK)).offerTo(exporter);
        createSlabRecipe3(RecipeCategory.BUILDING_BLOCKS, ModBlocksRegistry.PACKED_ICE_SLAB, Ingredient.ofItems(Blocks.PACKED_ICE)).criterion("has_packed_ice_block", conditionsFromItem(Blocks.PACKED_ICE)).offerTo(exporter);
        createSlabRecipe3(RecipeCategory.BUILDING_BLOCKS, ModBlocksRegistry.DEEPSLATE_SLAB, Ingredient.ofItems(Blocks.DEEPSLATE)).criterion("has_deepslate_block", conditionsFromItem(Blocks.DEEPSLATE)).offerTo(exporter);
        createSlabRecipe3(RecipeCategory.BUILDING_BLOCKS, ModBlocksRegistry.CLAY_SLAB, Ingredient.ofItems(Blocks.CLAY)).criterion("has_clay_block", conditionsFromItem(Blocks.CLAY)).offerTo(exporter);
        createSlabRecipe3(RecipeCategory.BUILDING_BLOCKS, ModBlocksRegistry.MOSS_SLAB, Ingredient.ofItems(Blocks.MOSS_BLOCK)).criterion("has_moss_block", conditionsFromItem(Blocks.MOSS_BLOCK)).offerTo(exporter);
        createSlabRecipe3(RecipeCategory.BUILDING_BLOCKS, ModBlocksRegistry.GRASS_SLAB, Ingredient.ofItems(Blocks.GRASS_BLOCK)).criterion("has_grass_block", conditionsFromItem(Blocks.GRASS_BLOCK)).offerTo(exporter);
        createSlabRecipe3(RecipeCategory.BUILDING_BLOCKS, ModBlocksRegistry.MYCELIUM_SLAB, Ingredient.ofItems(Blocks.MYCELIUM)).criterion("has_mycelium_block", conditionsFromItem(Blocks.MYCELIUM)).offerTo(exporter);
        createSlabRecipe3(RecipeCategory.BUILDING_BLOCKS, ModBlocksRegistry.PODZOL_SLAB, Ingredient.ofItems(Blocks.PODZOL)).criterion("has_podzol_block", conditionsFromItem(Blocks.PODZOL)).offerTo(exporter);
        createSlabRecipe3(RecipeCategory.BUILDING_BLOCKS, ModBlocksRegistry.PATH_SLAB, Ingredient.ofItems(Blocks.DIRT_PATH)).criterion("has_dirt_path_block", conditionsFromItem(Blocks.DIRT_PATH)).offerTo(exporter);
        createSlabRecipe3(RecipeCategory.BUILDING_BLOCKS, ModBlocksRegistry.GRAVEL_SLAB, Ingredient.ofItems(Blocks.GRAVEL)).criterion("has_gravel_block", conditionsFromItem(Blocks.GRAVEL)).offerTo(exporter);
        createSlabRecipe3(RecipeCategory.BUILDING_BLOCKS, ModBlocksRegistry.SAND_SLAB, Ingredient.ofItems(Blocks.SAND)).criterion("has_sand_block", conditionsFromItem(Blocks.SAND)).offerTo(exporter);
        createSlabRecipe3(RecipeCategory.BUILDING_BLOCKS, ModBlocksRegistry.RED_SAND_SLAB, Ingredient.ofItems(Blocks.RED_SAND)).criterion("has_red_sand_block", conditionsFromItem(Blocks.RED_SAND)).offerTo(exporter);
        createSlabRecipe3(RecipeCategory.BUILDING_BLOCKS, ModBlocksRegistry.TERRACOTTA_SLAB, Ingredient.ofItems(Blocks.TERRACOTTA)).criterion("has_terracotta_block", conditionsFromItem(Blocks.TERRACOTTA)).offerTo(exporter);
        createSlabRecipe3(RecipeCategory.BUILDING_BLOCKS, ModBlocksRegistry.RED_TERRACOTTA_SLAB, Ingredient.ofItems(Blocks.RED_TERRACOTTA)).criterion("has_red_terracotta_block", conditionsFromItem(Blocks.RED_TERRACOTTA)).offerTo(exporter);
        createSlabRecipe3(RecipeCategory.BUILDING_BLOCKS, ModBlocksRegistry.ORANGE_TERRACOTTA_SLAB, Ingredient.ofItems(Blocks.ORANGE_TERRACOTTA)).criterion("has_orange_terracotta_block", conditionsFromItem(Blocks.ORANGE_TERRACOTTA)).offerTo(exporter);
        createSlabRecipe3(RecipeCategory.BUILDING_BLOCKS, ModBlocksRegistry.LIGHT_GRAY_TERRACOTTA_SLAB, Ingredient.ofItems(Blocks.LIGHT_GRAY_TERRACOTTA)).criterion("has_light_gray_terracotta_block", conditionsFromItem(Blocks.LIGHT_GRAY_TERRACOTTA)).offerTo(exporter);
        createSlabRecipe3(RecipeCategory.BUILDING_BLOCKS, ModBlocksRegistry.WHITE_TERRACOTTA_SLAB, Ingredient.ofItems(Blocks.WHITE_TERRACOTTA)).criterion("has_white_terracotta_block", conditionsFromItem(Blocks.WHITE_TERRACOTTA)).offerTo(exporter);
        createSlabRecipe3(RecipeCategory.BUILDING_BLOCKS, ModBlocksRegistry.BROWN_TERRACOTTA_SLAB, Ingredient.ofItems(Blocks.BROWN_TERRACOTTA)).criterion("has_brown_terracotta_block", conditionsFromItem(Blocks.BROWN_TERRACOTTA)).offerTo(exporter);
        createSlabRecipe3(RecipeCategory.BUILDING_BLOCKS, ModBlocksRegistry.YELLOW_TERRACOTTA_SLAB, Ingredient.ofItems(Blocks.YELLOW_TERRACOTTA)).criterion("has_yellow_terracotta_block", conditionsFromItem(Blocks.YELLOW_TERRACOTTA)).offerTo(exporter);
    }
    public static CraftingRecipeJsonBuilder createSlabRecipe3(RecipeCategory category, ItemConvertible output, Ingredient input) {
        return ShapedRecipeJsonBuilder.create(category, output, 3).input('#', input).pattern("###");
    }
}
