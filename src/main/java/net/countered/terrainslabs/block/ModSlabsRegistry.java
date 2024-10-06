package net.countered.terrainslabs.block;

import net.countered.terrainslabs.TerrainSlabs;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModSlabsRegistry {


    public static final Block GRASS_SLAB = registerBlock("grass_slab",
            new GrassSlab(AbstractBlock.Settings.copy(Blocks.OAK_SLAB)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(TerrainSlabs.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(TerrainSlabs.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        TerrainSlabs.LOGGER.info("Registering Mod Blocks for" + TerrainSlabs.MOD_ID);
    }
}
