package net.countered.datagen;

import net.countered.terrainslabs.block.ModBlocksRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        RegistryWrapper.Impl<Enchantment> impl = this.registries.getOrThrow(RegistryKeys.ENCHANTMENT);

        this.addDrop(ModBlocksRegistry.DIRT_SLAB, block -> silkSlabDrops(block, Blocks.DIRT));
        this.addDrop(ModBlocksRegistry.MUD_SLAB, block -> silkSlabDrops(block, Blocks.MUD));
        this.addDrop(ModBlocksRegistry.COARSE_SLAB, block -> silkSlabDrops(block, Blocks.COARSE_DIRT));
        this.addDrop(ModBlocksRegistry.DEEPSLATE_SLAB, block -> silkSlabDrops(block, Blocks.DEEPSLATE));
        this.addDrop(ModBlocksRegistry.MOSS_SLAB, block -> silkSlabDrops(block, Blocks.MOSS_BLOCK));
        this.addDrop(ModBlocksRegistry.SAND_SLAB, block -> silkSlabDrops(block, Blocks.SAND));
        this.addDrop(ModBlocksRegistry.RED_SAND_SLAB, block -> silkSlabDrops(block, Blocks.RED_SAND));
        this.addDrop(ModBlocksRegistry.TERRACOTTA_SLAB, block -> silkSlabDrops(block, Blocks.TERRACOTTA));
        this.addDrop(ModBlocksRegistry.RED_TERRACOTTA_SLAB, block -> silkSlabDrops(block, Blocks.RED_TERRACOTTA));
        this.addDrop(ModBlocksRegistry.ORANGE_TERRACOTTA_SLAB, block -> silkSlabDrops(block, Blocks.ORANGE_TERRACOTTA));
        this.addDrop(ModBlocksRegistry.LIGHT_GRAY_TERRACOTTA_SLAB, block -> silkSlabDrops(block, Blocks.LIGHT_GRAY_TERRACOTTA));
        this.addDrop(ModBlocksRegistry.WHITE_TERRACOTTA_SLAB, block -> silkSlabDrops(block, Blocks.WHITE_TERRACOTTA));
        this.addDrop(ModBlocksRegistry.BROWN_TERRACOTTA_SLAB, block -> silkSlabDrops(block, Blocks.BROWN_TERRACOTTA));
        this.addDrop(ModBlocksRegistry.YELLOW_TERRACOTTA_SLAB, block -> silkSlabDrops(block, Blocks.YELLOW_TERRACOTTA));
        this.addDrop(ModBlocksRegistry.CUSTOM_STONE_SLAB, block -> silkSlabDrops(block, Blocks.COBBLESTONE));
        this.addDrop(ModBlocksRegistry.CUSTOM_ANDESITE_SLAB, block -> silkSlabDrops(block, Blocks.ANDESITE));
        this.addDrop(ModBlocksRegistry.CUSTOM_DIORITE_SLAB, block -> silkSlabDrops(block, Blocks.DIORITE));
        this.addDrop(ModBlocksRegistry.CUSTOM_GRANITE_SLAB, block -> silkSlabDrops(block, Blocks.GRANITE));
        this.addDrop(ModBlocksRegistry.CUSTOM_TUFF_SLAB, block -> silkSlabDrops(block, Blocks.TUFF));
        this.addDrop(ModBlocksRegistry.CUSTOM_SANDSTONE_SLAB, block -> silkSlabDrops(block, Blocks.SANDSTONE));
        this.addDrop(ModBlocksRegistry.CUSTOM_RED_SANDSTONE_SLAB, block -> silkSlabDrops(block, Blocks.RED_SANDSTONE));

        this.addDrop(ModBlocksRegistry.MYCELIUM_SLAB, block -> silkSlabDrops(block, Blocks.DIRT));
        this.addDrop(ModBlocksRegistry.PODZOL_SLAB, block -> silkSlabDrops(block, Blocks.DIRT));
        this.addDrop(ModBlocksRegistry.GRASS_SLAB, block -> silkSlabDrops(block, Blocks.DIRT));
        this.addDrop(ModBlocksRegistry.PATH_SLAB, block -> silkSlabDrops(block, Blocks.DIRT));

        this.addDrop(ModBlocksRegistry.PACKED_ICE_SLAB, this::onlySilkSlabDrops);

        this.addDrop(ModBlocksRegistry.SNOW_SLAB, block -> silkSlabDropsParts(block, Items.SNOWBALL));
        this.addDrop(ModBlocksRegistry.CLAY_SLAB, block -> silkSlabDropsParts(block, Items.CLAY_BALL));

        this.addDrop(
                ModBlocksRegistry.GRAVEL_SLAB,
                block -> this.dropsWithSilkTouch(
                        block,
                        this.addSurvivesExplosionCondition(
                                block,
                                ItemEntry.builder(Items.FLINT)
                                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F))
                                                .conditionally(
                                                        BlockStatePropertyLootCondition.builder(block)
                                                                .properties(StatePredicate.Builder.create().exactMatch(SlabBlock.TYPE, SlabType.DOUBLE))
                                                ))
                                        .conditionally(TableBonusLootCondition.builder(impl.getOrThrow(Enchantments.FORTUNE), 0.1F, 0.14285715F, 0.25F, 1.0F))
                                        .alternatively(ItemEntry.builder(block)
                                                .apply(
                                                        SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F))
                                                                .conditionally(
                                                                        BlockStatePropertyLootCondition.builder(block)
                                                                                .properties(StatePredicate.Builder.create().exactMatch(SlabBlock.TYPE, SlabType.DOUBLE)))
                                                )) // For dropping the slab itself if not using Silk Touch
                        )
                )
        );

    }

    /**
     * Adds a loot table entry that makes the slab drop its base block instead of itself.
     */

    public LootTable.Builder slabDrops(Block slab, Block drop) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(
                                        this.applyExplosionDecay(
                                                slab,
                                                ItemEntry.builder(drop)
                                                        .apply(
                                                                SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F))
                                                                        .conditionally(
                                                                                BlockStatePropertyLootCondition.builder(slab)
                                                                                        .properties(StatePredicate.Builder.create().exactMatch(SlabBlock.TYPE, SlabType.DOUBLE)))
                                                        )
                                        )
                                )
                );
    }

    public LootTable.Builder silkSlabDrops(Block slab, Block drop) {
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1.0F))
                            .with(
                                ItemEntry.builder(slab)
                                        .conditionally(this.createSilkTouchCondition())  // Silk Touch condition
                                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F))
                                                .conditionally(BlockStatePropertyLootCondition.builder(slab)
                                                        .properties(StatePredicate.Builder.create().exactMatch(SlabBlock.TYPE, SlabType.DOUBLE))
                                                )
                                        )
                                        .alternatively(
                                                ItemEntry.builder(drop)
                                                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F))
                                                                .conditionally(BlockStatePropertyLootCondition.builder(slab)
                                                                        .properties(StatePredicate.Builder.create().exactMatch(SlabBlock.TYPE, SlabType.DOUBLE))
                                                                )
                                                        )
                                        )
                        )
                );
    }

    public LootTable.Builder silkSlabDropsParts(Block slab, Item drop) {
        return LootTable.builder()
                .pool(LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .with(
                                ItemEntry.builder(slab)
                                        .conditionally(this.createSilkTouchCondition())  // Silk Touch condition
                                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F))
                                                .conditionally(BlockStatePropertyLootCondition.builder(slab)
                                                        .properties(StatePredicate.Builder.create().exactMatch(SlabBlock.TYPE, SlabType.DOUBLE))
                                                )
                                        )
                                        .alternatively(
                                                ItemEntry.builder(drop)
                                                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(8.0F))
                                                                .conditionally(BlockStatePropertyLootCondition.builder(slab)
                                                                        .properties(StatePredicate.Builder.create().exactMatch(SlabBlock.TYPE, SlabType.DOUBLE))
                                                                )
                                                        )
                                                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(4.0F))
                                                                .conditionally(BlockStatePropertyLootCondition.builder(slab)
                                                                        .properties(StatePredicate.Builder.create().exactMatch(SlabBlock.TYPE, SlabType.BOTTOM))
                                                                )
                                                        )


                                        )
                        )
                );
    }

    public LootTable.Builder onlySilkSlabDrops(Block slab) {
        return LootTable.builder()
                .pool(LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .with(
                                ItemEntry.builder(slab)
                                        .conditionally(this.createSilkTouchCondition())  // Silk Touch condition
                                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F))
                                                .conditionally(BlockStatePropertyLootCondition.builder(slab)
                                                        .properties(StatePredicate.Builder.create().exactMatch(SlabBlock.TYPE, SlabType.DOUBLE))
                                                )
                                        )
                                        .alternatively(
                                                ItemEntry.builder(slab)
                                                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(0)))
                                        )

                        )
                );
    }
}
