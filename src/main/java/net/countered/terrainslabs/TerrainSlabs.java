package net.countered.terrainslabs;

import net.countered.terrainslabs.block.ModBlocksRegistry;
import net.countered.terrainslabs.worldgen.ModAddedFeatures;
import net.countered.terrainslabs.worldgen.slabfeature.ModSlabGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.data.client.BlockStateModelGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//TODO add pathslab, add underground feature?, add stairs instead of slabs?, add class mudslab and snowslab?

public class TerrainSlabs implements ModInitializer {
	public static final String MOD_ID = "terrainslabs";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("Initializing TerrainSlabs");
		ModBlocksRegistry.registerModBlocks();
		ModAddedFeatures.registerFeatures();
		ModSlabGeneration.generateSlabs();

		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
			return tintIndex == 0 ? BiomeColors.getGrassColor(world, pos) : -1;
		}, ModBlocksRegistry.GRASS_SLAB);
	}
}