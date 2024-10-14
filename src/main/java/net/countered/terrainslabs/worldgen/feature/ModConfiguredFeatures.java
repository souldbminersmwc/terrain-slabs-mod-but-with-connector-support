package net.countered.terrainslabs.worldgen.feature;

import net.countered.terrainslabs.TerrainSlabs;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;

public class ModConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?,?>> SLAB_FEATURE_KEY = registerKey("slab_feature");


    public static void bootstrap(Registerable<ConfiguredFeature<?,?>> context){
        register(context, SLAB_FEATURE_KEY, ModAddedFeatures.SLAB_FEATURE, FeatureConfig.DEFAULT);    }


    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(TerrainSlabs.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
