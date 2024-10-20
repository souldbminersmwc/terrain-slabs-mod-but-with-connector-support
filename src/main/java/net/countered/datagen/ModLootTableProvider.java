package net.countered.datagen;

import net.countered.terrainslabs.block.ModBlocksRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        this.addDrop(ModBlocksRegistry.DIRT_SLAB, block -> slabDrops(block, Blocks.DIRT));
        this.addDrop(ModBlocksRegistry.MUD_SLAB, block -> slabDrops(block, Blocks.MUD));
        this.addDrop(ModBlocksRegistry.COARSE_SLAB, block -> slabDrops(block, Blocks.COARSE_DIRT));
        this.addDrop(ModBlocksRegistry.SNOW_SLAB, block -> slabDrops(block, Blocks.SNOW_BLOCK));
        this.addDrop(ModBlocksRegistry.PACKED_ICE_SLAB, block -> slabDrops(block, Blocks.PACKED_ICE));
        this.addDrop(ModBlocksRegistry.DEEPSLATE_SLAB, block -> slabDrops(block, Blocks.DEEPSLATE));
        this.addDrop(ModBlocksRegistry.CLAY_SLAB, block -> slabDrops(block, Blocks.CLAY));
        this.addDrop(ModBlocksRegistry.MOSS_SLAB, block -> slabDrops(block, Blocks.MOSS_BLOCK));
        this.addDrop(ModBlocksRegistry.GRASS_SLAB, block -> slabDrops(block, Blocks.GRASS_BLOCK));
        this.addDrop(ModBlocksRegistry.MYCELIUM_SLAB, block -> slabDrops(block, Blocks.MYCELIUM));
        this.addDrop(ModBlocksRegistry.PODZOL_SLAB, block -> slabDrops(block, Blocks.PODZOL));
        this.addDrop(ModBlocksRegistry.GRAVEL_SLAB, block -> slabDrops(block, Blocks.GRAVEL));
        this.addDrop(ModBlocksRegistry.SAND_SLAB, block -> slabDrops(block, Blocks.SAND));
        this.addDrop(ModBlocksRegistry.RED_SAND_SLAB, block -> slabDrops(block, Blocks.RED_SAND));
        this.addDrop(ModBlocksRegistry.TERRACOTTA_SLAB, block -> slabDrops(block, Blocks.TERRACOTTA));
        this.addDrop(ModBlocksRegistry.RED_TERRACOTTA_SLAB, block -> slabDrops(block, Blocks.RED_TERRACOTTA));
        this.addDrop(ModBlocksRegistry.ORANGE_TERRACOTTA_SLAB, block -> slabDrops(block, Blocks.ORANGE_TERRACOTTA));
        this.addDrop(ModBlocksRegistry.LIGHT_GRAY_TERRACOTTA_SLAB, block -> slabDrops(block, Blocks.LIGHT_GRAY_TERRACOTTA));
        this.addDrop(ModBlocksRegistry.WHITE_TERRACOTTA_SLAB, block -> slabDrops(block, Blocks.WHITE_TERRACOTTA));
        this.addDrop(ModBlocksRegistry.BROWN_TERRACOTTA_SLAB, block -> slabDrops(block, Blocks.BROWN_TERRACOTTA));
        this.addDrop(ModBlocksRegistry.YELLOW_TERRACOTTA_SLAB, block -> slabDrops(block, Blocks.YELLOW_TERRACOTTA));
        this.addDrop(ModBlocksRegistry.CUSTOM_STONE_SLAB, block -> slabDrops(block, Blocks.STONE));
        this.addDrop(ModBlocksRegistry.CUSTOM_ANDESITE_SLAB, block -> slabDrops(block, Blocks.ANDESITE));
        this.addDrop(ModBlocksRegistry.CUSTOM_DIORITE_SLAB, block -> slabDrops(block, Blocks.DIORITE));
        this.addDrop(ModBlocksRegistry.CUSTOM_GRANITE_SLAB, block -> slabDrops(block, Blocks.GRANITE));
        this.addDrop(ModBlocksRegistry.CUSTOM_TUFF_SLAB, block -> slabDrops(block, Blocks.TUFF));
        this.addDrop(ModBlocksRegistry.CUSTOM_SANDSTONE_SLAB, block -> slabDrops(block, Blocks.SANDSTONE));
        this.addDrop(ModBlocksRegistry.CUSTOM_RED_SANDSTONE_SLAB, block -> slabDrops(block, Blocks.RED_SANDSTONE));
    }


    /**
     * Adds a loot table entry that makes the slab drop its base block instead of itself.
     */
    public LootTable.Builder slabDrops(Block slabBlock, Block baseBlock) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(
                                        this.applyExplosionDecay(
                                                slabBlock,
                                                ItemEntry.builder(baseBlock)
                                                        .apply(
                                                                SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F))
                                                                        .conditionally(
                                                                                BlockStatePropertyLootCondition.builder(slabBlock)
                                                                                        .properties(StatePredicate.Builder.create().exactMatch(SlabBlock.TYPE, SlabType.DOUBLE))
                                                                        )
                                                        )
                                        )
                                )
                );
    }
}
