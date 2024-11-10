package net.countered.terrainslabs;

import net.countered.terrainslabs.block.ModBlocksRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.RenderLayer;

public class TerrainSlabsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocksRegistry.GRASS_SLAB, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocksRegistry.POPPY_ON_TOP, RenderLayer.getCutoutMipped());

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
                return tintIndex == 0 && world != null && pos != null
                        ? BiomeColors.getGrassColor(world, pos)
                        : MapColor.PALE_GREEN.color;
        }, ModBlocksRegistry.GRASS_SLAB);


        ColorProviderRegistry.ITEM.register(
                (stack, tintIndex) -> tintIndex == 0 ?
                        MinecraftClient.getInstance().getBlockColors().getColor(Blocks.GRASS_BLOCK.getDefaultState(), null, null, tintIndex) : -1,
                ModBlocksRegistry.GRASS_SLAB
        );
    }
}
