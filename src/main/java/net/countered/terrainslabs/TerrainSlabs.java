package net.countered.terrainslabs;

import net.countered.terrainslabs.block.ModBlocksRegistry;
import net.countered.terrainslabs.worldgen.feature.ModAddedFeatures;
import net.countered.terrainslabs.worldgen.slabfeature.ModSlabGeneration;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//TODO
// add vegetation placed on slabs
// add drops
// add crafting
// fix bottom slabs being waterlogged when water below
// add class snowslab?
// clay, deepslate, pathslab
// grass slab converting to bottom type with block above,
// slabs breaking correct pixels, map colors, copy settings, translation
// water should be running through slabs?
// only place on bottom of mountain

// wrong slabs placement (dirt on sand)
// add vertical slabs
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
	}
}