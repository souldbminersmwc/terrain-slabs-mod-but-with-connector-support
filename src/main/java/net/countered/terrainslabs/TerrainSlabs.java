package net.countered.terrainslabs;

import net.countered.terrainslabs.block.ModBlocksRegistry;
import net.countered.terrainslabs.worldgen.ModAddedFeatures;
import net.countered.terrainslabs.worldgen.slabfeature.ModSlabGeneration;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//TODO add pathslab,
// important
// add mycelium and podzol
// add vegetation placed on slabs
// add drops
// add crafting
// add vertical slabs?
// add underground feature,
// add class mudslab and snowslab?
// add granite slabs
// grass slab converting to bottom type with block above,
// slabs breaking correct pixels, map colors, copy settings
// make sandstone slab top texture
// water should be running through slabs?
// wrong slabs placement (dirt on sand)
// slabs next to cactus, dungeons

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