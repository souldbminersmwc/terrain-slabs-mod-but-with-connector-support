package net.countered.terrainslabs;

import net.countered.terrainslabs.block.ModBlocksRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class TerrainSlabsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocksRegistry.GRASS_SLAB, RenderLayer.getCutoutMipped());
    }
}
