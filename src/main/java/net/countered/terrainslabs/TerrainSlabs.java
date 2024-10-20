package net.countered.terrainslabs;

import net.countered.terrainslabs.block.ModBlocksRegistry;
import net.countered.terrainslabs.item.ModItemGroups;
import net.countered.terrainslabs.worldgen.feature.ModAddedFeatures;
import net.countered.terrainslabs.worldgen.slabfeature.ModSlabGeneration;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//TODO
// Tags

// Nether/end
// place slab whenever surrounded by atleast one slab plus opaque blocks
// add pathslab
// add vegetation placed on slabs
// water should be running through slabs?
// add vertical slabs
// fix ao (custom slab tag?)
// wrong slabs placement (dirt on sand)
// convert to mud tag
// tag extension/improvement (carver replacable)
// add correct silk touch drops
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