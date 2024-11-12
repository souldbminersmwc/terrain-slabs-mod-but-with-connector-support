package net.countered.terrainslabs.callbacks;

import net.countered.terrainslabs.block.ModBlocksRegistry;
import net.countered.terrainslabs.block.ModSlabsMap;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SnowBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RegisterCallbacks {
    private static Map<Item, Block> FLOWERS_ON_TOP = new HashMap<>();

    static {
        FLOWERS_ON_TOP.put(Items.POPPY, ModBlocksRegistry.POPPY_ON_TOP);
        FLOWERS_ON_TOP.put(Items.SHORT_GRASS, ModBlocksRegistry.SHORT_GRASS_ON_TOP);
    }


    public static void registerCallbacks() {
        UseBlockCallback.EVENT.register((PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) -> {
            ItemStack item = player.getStackInHand(hand);

            if (item.getItem() == Items.SNOW) {
                BlockPos blockPos = hitResult.getBlockPos().offset(hitResult.getSide());

                // Ensure the block can be replaced and there's air at the target position
                if ((world.getBlockState(blockPos).isAir() || world.getBlockState(blockPos).getBlock() == ModBlocksRegistry.SNOW_ON_TOP)
                        && world.getBlockState(blockPos.down()).getBlock() instanceof SlabBlock) {
                    // Replace with custom snow slab
                    int currentLayers = world.getBlockState(blockPos).getBlock() instanceof SnowBlock
                            ? world.getBlockState(blockPos).get(SnowBlock.LAYERS)
                            : 0;

                    // If snow layers can be increased, increase by 1 layer
                    if (currentLayers < SnowBlock.MAX_LAYERS) {
                        world.setBlockState(blockPos, ModBlocksRegistry.SNOW_ON_TOP.getDefaultState()
                                .with(SnowBlock.LAYERS, currentLayers + 1));

                        // Play placement sound and consume one snow slab
                        world.playSound(player, blockPos, SoundEvents.BLOCK_SNOW_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        if (!player.isCreative()) {
                            item.decrement(1);
                        }
                        return ActionResult.SUCCESS;
                    }
                }
            }
            if (FLOWERS_ON_TOP.containsKey(item.getItem())) {
                BlockPos blockPos = hitResult.getBlockPos().offset(hitResult.getSide());

                // Ensure the block can be replaced and there's air at the target position
                if (world.getBlockState(blockPos).isAir() && world.getBlockState(blockPos.down()).getBlock() instanceof SlabBlock) {

                    world.setBlockState(blockPos, FLOWERS_ON_TOP.get(item.getItem()).getDefaultState(), 0);

                    // Play placement sound and consume one snow slab
                    world.playSound(player, blockPos, SoundEvents.BLOCK_GRASS_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    if (!player.isCreative()) {
                        item.decrement(1);
                    }
                    return ActionResult.SUCCESS;
                }
            }
            // Pass to allow normal behavior if conditions are not met
            return ActionResult.PASS;
        });
    }
}
