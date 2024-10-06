package net.countered.terrainslabs.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;

import java.util.HashMap;
import java.util.Map;

public class ModSlabsMap {
    private static final Map<Block, Block> SLAB_MAP = new HashMap<>();

    static {
        // Register your block-to-slab mappings here
        SLAB_MAP.put(Blocks.STONE, Blocks.STONE_SLAB);
        SLAB_MAP.put(Blocks.GRASS_BLOCK, ModSlabsRegistry.GRASS_SLAB);
        //SLAB_MAP.put(Blocks.SAND, ModSlabsRegistry.SAND_SLAB);
        // Add more mappings as needed
    }
    // TODO Water onto slabs in water, slabs in ocean and caves?, fix slabs after trees generation
    public static Block getSlabForBlock(Block blockBelow) {
        return SLAB_MAP.getOrDefault(blockBelow, Blocks.STONE_SLAB); // Default slab if no match
    }
}
