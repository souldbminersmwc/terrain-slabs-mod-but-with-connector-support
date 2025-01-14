package net.countered.terrainslabs.block;

import net.countered.terrainslabs.TerrainSlabs;
import net.countered.terrainslabs.block.customslabs.soilslabs.GrassSlab;
import net.countered.terrainslabs.block.customslabs.soilslabs.PathSlab;
import net.countered.terrainslabs.block.customslabs.specialslabs.CustomSlab;
import net.countered.terrainslabs.block.customslabs.specialslabs.GravityAffectedSlab;
import net.countered.terrainslabs.block.customslabs.soilslabs.MyceliumSlab;
import net.countered.terrainslabs.block.customslabs.soilslabs.PodzolSlab;
import net.countered.terrainslabs.block.customslabs.specialslabs.MudSlab;
import net.countered.terrainslabs.block.customslabs.specialslabs.dimensions.NetherrackSlab;
import net.countered.terrainslabs.block.customslabs.specialslabs.dimensions.NyliumSlab;
import net.countered.terrainslabs.block.customslabs.specialslabs.dimensions.SoulSandSlab;
import net.countered.terrainslabs.block.ontopofslabs.*;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;

public class ModBlocksRegistry {

    public static final Block DIRT_SLAB = registerBlock("dirt_slab",
            new CustomSlab(createBlockSettings(Blocks.DIRT)));
    public static final Block MUD_SLAB = registerBlock("mud_slab",
            new MudSlab(createBlockSettings(Blocks.MUD).blockVision(Blocks::never)));
    public static final Block COARSE_SLAB = registerBlock("coarse_slab",
            new CustomSlab(createBlockSettings(Blocks.COARSE_DIRT)));
    public static final Block SNOW_SLAB = registerBlock("snow_slab",
            new CustomSlab(createBlockSettings(Blocks.SNOW_BLOCK)));
    public static final Block PACKED_ICE_SLAB = registerBlock("packed_ice_slab",
            new CustomSlab(createBlockSettings(Blocks.PACKED_ICE)));
    public static final Block DEEPSLATE_SLAB = registerBlock("deepslate_slab",
            new CustomSlab(createBlockSettings(Blocks.DEEPSLATE)));
    public static final Block CLAY_SLAB = registerBlock("clay_slab",
            new CustomSlab(createBlockSettings(Blocks.CLAY)));
    public static final Block MOSS_SLAB = registerBlock("moss_slab",
            new CustomSlab(createBlockSettings(Blocks.MOSS_BLOCK)));

    public static final Block GRASS_SLAB = registerBlock("grass_slab",
            new GrassSlab(createBlockSettings(Blocks.GRASS_BLOCK)));
    public static final Block MYCELIUM_SLAB = registerBlock("mycelium_slab",
            new MyceliumSlab(createBlockSettings(Blocks.MYCELIUM)));
    public static final Block PODZOL_SLAB = registerBlock("podzol_slab",
            new PodzolSlab(createBlockSettings(Blocks.PODZOL)));
    public static final Block PATH_SLAB = registerBlock("path_slab",
            new PathSlab(createBlockSettings(Blocks.DIRT_PATH).blockVision(Blocks::never)));

    public static final Block GRAVEL_SLAB = registerBlock("gravel_slab",
            new GravityAffectedSlab(createBlockSettings(Blocks.GRAVEL)));
    public static final Block SAND_SLAB = registerBlock("sand_slab",
            new GravityAffectedSlab(createBlockSettings(Blocks.SAND)));
    public static final Block RED_SAND_SLAB = registerBlock("red_sand_slab",
            new GravityAffectedSlab(createBlockSettings(Blocks.RED_SAND)));

    public static final Block TERRACOTTA_SLAB = registerBlock("terracotta_slab",
            new CustomSlab(createBlockSettings(Blocks.TERRACOTTA)));
    public static final Block RED_TERRACOTTA_SLAB = registerBlock("red_terracotta_slab",
            new CustomSlab(createBlockSettings(Blocks.RED_TERRACOTTA)));
    public static final Block ORANGE_TERRACOTTA_SLAB = registerBlock("orange_terracotta_slab",
            new CustomSlab(createBlockSettings(Blocks.ORANGE_TERRACOTTA)));
    public static final Block LIGHT_GRAY_TERRACOTTA_SLAB = registerBlock("light_gray_terracotta_slab",
            new CustomSlab(createBlockSettings(Blocks.LIGHT_GRAY_TERRACOTTA)));
    public static final Block WHITE_TERRACOTTA_SLAB = registerBlock("white_terracotta_slab",
            new CustomSlab(createBlockSettings(Blocks.WHITE_TERRACOTTA)));
    public static final Block BROWN_TERRACOTTA_SLAB = registerBlock("brown_terracotta_slab",
            new CustomSlab(createBlockSettings(Blocks.BROWN_TERRACOTTA)));
    public static final Block YELLOW_TERRACOTTA_SLAB = registerBlock("yellow_terracotta_slab",
            new CustomSlab(createBlockSettings(Blocks.YELLOW_TERRACOTTA)));

    public static final Block CUSTOM_STONE_SLAB = registerBlock("terrain_stone_slab",
            new CustomSlab(createBlockSettings(Blocks.STONE_SLAB)));
    public static final Block CUSTOM_TUFF_SLAB = registerBlock("terrain_tuff_slab",
            new CustomSlab(createBlockSettings(Blocks.TUFF_SLAB)));
    public static final Block CUSTOM_SANDSTONE_SLAB = registerBlock("terrain_sandstone_slab",
            new CustomSlab(createBlockSettings(Blocks.SANDSTONE_SLAB)));
    public static final Block CUSTOM_RED_SANDSTONE_SLAB = registerBlock("terrain_red_sandstone_slab",
            new CustomSlab(createBlockSettings(Blocks.RED_SANDSTONE_SLAB)));
    public static final Block CUSTOM_ANDESITE_SLAB = registerBlock("terrain_andesite_slab",
            new CustomSlab(createBlockSettings(Blocks.ANDESITE_SLAB)));
    public static final Block CUSTOM_DIORITE_SLAB = registerBlock("terrain_diorite_slab",
            new CustomSlab(createBlockSettings(Blocks.DIORITE_SLAB)));
    public static final Block CUSTOM_GRANITE_SLAB = registerBlock("terrain_granite_slab",
            new CustomSlab(createBlockSettings(Blocks.GRANITE_SLAB)));

    public static final Block SOUL_SAND_SLAB = registerBlock("soul_sand_slab",
            new SoulSandSlab(createBlockSettings(Blocks.SOUL_SAND).blockVision(Blocks::never)));
    public static final Block SOUL_SOIL_SLAB = registerBlock("soul_soil_slab",
            new CustomSlab(createBlockSettings(Blocks.SOUL_SOIL)));
    public static final Block NETHERRACK_SLAB = registerBlock("netherrack_slab",
            new NetherrackSlab(createBlockSettings(Blocks.NETHERRACK)));
    public static final Block WARPED_NYLIUM_SLAB = registerBlock("warped_nylium_slab",
            new NyliumSlab(createBlockSettings(Blocks.WARPED_NYLIUM)));
    public static final Block CRIMSON_NYLIUM_SLAB = registerBlock("crimson_nylium_slab",
            new NyliumSlab(createBlockSettings(Blocks.CRIMSON_NYLIUM)));
    public static final Block BASALT_SLAB = registerBlock("basalt_slab",
            new CustomSlab(createBlockSettings(Blocks.BASALT)));
    public static final Block CUSTOM_BLACKSTONE_SLAB = registerBlock("terrain_blackstone_slab",
            new CustomSlab(createBlockSettings(Blocks.BLACKSTONE_SLAB)));
    public static final Block ENDSTONE_SLAB = registerBlock("endstone_slab",
            new CustomSlab(createBlockSettings(Blocks.END_STONE)));

    public static final Block SNOW_ON_TOP = registerBlock("snow_on_top",
            new SnowOnTop(createBlockSettings(Blocks.SNOW)));
    public static final Block SEAGRASS_ON_TOP = registerBlock("seagrass_on_top",
            new SeagrassOnTop(createBlockSettings(Blocks.SEAGRASS)));
    public static final Block POPPY_ON_TOP = registerBlock("poppy_on_top",
            new FlowerOnTop(StatusEffects.NIGHT_VISION, 5.0F, createBlockSettings(Blocks.POPPY)));
    public static final Block DANDELION_ON_TOP = registerBlock("dandelion_on_top",
            new FlowerOnTop(StatusEffects.SATURATION, 0.35F, createBlockSettings(Blocks.DANDELION)));
    public static final Block AZURE_BLUET_ON_TOP = registerBlock("azure_bluet_on_top",
            new FlowerOnTop(StatusEffects.BLINDNESS, 8.0F, createBlockSettings(Blocks.AZURE_BLUET)));
    public static final Block CORNFLOWER_ON_TOP = registerBlock("cornflower_on_top",
            new FlowerOnTop(StatusEffects.JUMP_BOOST, 6.0F, createBlockSettings(Blocks.CORNFLOWER)));
    public static final Block DEAD_BUSH_ON_TOP = registerBlock("dead_bush_on_top",
            new DeadBushOnTop(createBlockSettings(Blocks.DEAD_BUSH)));
    public static final Block BROWN_MUSHROOM_ON_TOP = registerBlock("brown_mushroom_on_top",
            new MushroomOnTop(TreeConfiguredFeatures.HUGE_BROWN_MUSHROOM, createBlockSettings(Blocks.BROWN_MUSHROOM)));
    public static final Block RED_MUSHROOM_ON_TOP = registerBlock("red_mushroom_on_top",
            new MushroomOnTop(TreeConfiguredFeatures.HUGE_RED_MUSHROOM, createBlockSettings(Blocks.RED_MUSHROOM)));
    public static final Block SHORT_GRASS_ON_TOP = registerBlock("short_grass_on_top",
            new GrassOnTop(createBlockSettings(Blocks.SHORT_GRASS)));
    public static final Block FERN_ON_TOP = registerBlock("fern_on_top",
            new GrassOnTop(createBlockSettings(Blocks.FERN)));

    private static Block.Settings createBlockSettings(Block baseBlock) {
        Identifier id = Identifier.of(TerrainSlabs.MOD_ID, Registries.BLOCK.getId(baseBlock).getPath());
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, id);
        return AbstractBlock.Settings.copy(baseBlock).registryKey(key);
    }


    // Updated block registration method with the registry key
    private static Block registerBlock(String name, Block block) {
        Identifier id = Identifier.of(TerrainSlabs.MOD_ID, name);
        RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, id);
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, id);

        registerBlockItem(name, block, itemKey);  // Register the item using a block-prefixed translation key
        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    // Method to register the BlockItem with the correct settings
    private static void registerBlockItem(String name, Block block, RegistryKey<Item> itemKey) {
        Item.Settings itemSettings = new Item.Settings()
                .useBlockPrefixedTranslationKey()  // Use the block-prefixed translation key format
                .registryKey(itemKey);

        Registry.register(Registries.ITEM, itemKey, new BlockItem(block, itemSettings));
    }

    public static void registerModBlocks() {
        TerrainSlabs.LOGGER.info("Registering Mod Blocks for " + TerrainSlabs.MOD_ID);
    }
}
