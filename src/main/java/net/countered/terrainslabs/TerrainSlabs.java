package net.countered.terrainslabs;

import net.countered.terrainslabs.block.ModBlocksRegistry;
import net.countered.terrainslabs.item.ModItemGroups;
import net.countered.terrainslabs.worldgen.feature.ModAddedFeatures;
import net.countered.terrainslabs.worldgen.slabfeature.ModSlabGeneration;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//TODO
// add drops
// add crafting
// slabs breaking correct pixels, map colors, copy settings
// only place on bottom of mountain
// wrong slabs placement (dirt on sand) make not feature

// place slab whenever surrounded by atleast one slab plus opaque blocks
// add pathslab
// add vegetation placed on slabs
// water should be running through slabs?
// add vertical slabs
public class TerrainSlabs implements ModInitializer {
	public static final String MOD_ID = "terrainslabs";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing TerrainSlabs");
		ModBlocksRegistry.registerModBlocks();
		ModAddedFeatures.registerFeatures();
		ModSlabGeneration.generateSlabs();
		ModItemGroups.registerItemGroups();
	}
}