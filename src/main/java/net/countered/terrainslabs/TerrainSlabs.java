package net.countered.terrainslabs;

import eu.midnightdust.lib.config.MidnightConfig;
import net.countered.terrainslabs.block.ModBlocksRegistry;
import net.countered.terrainslabs.callbacks.RegisterCallbacks;
import net.countered.terrainslabs.config.MyModConfig;
import net.countered.terrainslabs.item.ModItemGroups;
import net.countered.terrainslabs.item.ShovelPathSlab;
import net.countered.terrainslabs.worldgen.feature.ModAddedFeatures;
import net.countered.terrainslabs.worldgen.slabfeature.ModSlabGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SnowBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TerrainSlabs implements ModInitializer {
	public static final String MOD_ID = "terrainslabs";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing TerrainSlabs");
		MidnightConfig.init(TerrainSlabs.MOD_ID, MyModConfig.class);
		ModBlocksRegistry.registerModBlocks();
		ModAddedFeatures.registerFeatures();
		ModSlabGeneration.generateSlabs();
		ModItemGroups.registerItemGroups();
		ShovelPathSlab.init();
		RegisterCallbacks.registerCallbacks();
	}
}