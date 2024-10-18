package net.countered.terrainslabs.item;

import net.countered.terrainslabs.TerrainSlabs;
import net.countered.terrainslabs.block.ModBlocksRegistry;
import net.countered.terrainslabs.block.customslabs.soilslabs.GrassSlab;
import net.countered.terrainslabs.block.customslabs.soilslabs.MyceliumSlab;
import net.countered.terrainslabs.block.customslabs.soilslabs.PodzolSlab;
import net.countered.terrainslabs.block.customslabs.specialslabs.GravityAffectedSlab;
import net.countered.terrainslabs.block.customslabs.specialslabs.MudSlab;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup TERRAIN_SLABS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(TerrainSlabs.MOD_ID, "terrainslabs"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.terrainslabs"))
            .icon(() -> new ItemStack(ModBlocksRegistry.GRASS_SLAB)).entries((displayContext, entries) -> {

                entries.add(ModBlocksRegistry.DIRT_SLAB);
                entries.add(ModBlocksRegistry.MUD_SLAB);
                entries.add(ModBlocksRegistry.COARSE_SLAB);
                entries.add(ModBlocksRegistry.SNOW_SLAB);
                entries.add(ModBlocksRegistry.PACKED_ICE_SLAB);
                entries.add(ModBlocksRegistry.DEEPSLATE_SLAB);
                entries.add(ModBlocksRegistry.CLAY_SLAB);
                entries.add(ModBlocksRegistry.MOSS_SLAB);

                entries.add(ModBlocksRegistry.GRASS_SLAB);
                entries.add(ModBlocksRegistry.MYCELIUM_SLAB);
                entries.add(ModBlocksRegistry.PODZOL_SLAB);

                entries.add(ModBlocksRegistry.GRAVEL_SLAB);
                entries.add(ModBlocksRegistry.SAND_SLAB);
                entries.add(ModBlocksRegistry.RED_SAND_SLAB);

                entries.add(ModBlocksRegistry.TERRACOTTA_SLAB);
                entries.add(ModBlocksRegistry.RED_TERRACOTTA_SLAB);
                entries.add(ModBlocksRegistry.ORANGE_TERRACOTTA_SLAB);
                entries.add(ModBlocksRegistry.LIGHT_GRAY_TERRACOTTA_SLAB);
                entries.add(ModBlocksRegistry.WHITE_TERRACOTTA_SLAB);
                entries.add(ModBlocksRegistry.BROWN_TERRACOTTA_SLAB);
                entries.add(ModBlocksRegistry.YELLOW_TERRACOTTA_SLAB);

            }).build());

    public static void registerItemGroups() {
        TerrainSlabs.LOGGER.info("Registering Mod Item Groups");
    }
}