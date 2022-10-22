package com.ninni.yippee.world;

import com.google.common.collect.ImmutableList;
import com.ninni.yippee.Yippee;
import com.ninni.yippee.block.YippeeBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.RandomFeatureConfig;
import net.minecraft.world.gen.feature.RandomFeatureEntry;
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;

public class YippeeWorldGen {

    public static void init() { }

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> MYSTICAL_TREE = register("mystical", Feature.TREE, mysticalTree().build());
    public static final RegistryEntry<PlacedFeature> MYSTICAL_TREE_CHECKED = register("mystical_tree", MYSTICAL_TREE, PlacedFeatures.wouldSurvive(YippeeBlocks.MYSTICAL_OAK_SAPLING));
    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> MYSTICAL_TREE_FILTERED = register("mystical_tree_filtered", Feature.RANDOM_SELECTOR, new RandomFeatureConfig(List.of(new RandomFeatureEntry(MYSTICAL_TREE_CHECKED, 0.0F)), MYSTICAL_TREE_CHECKED));
    public static final RegistryEntry<PlacedFeature> MYSTICAL_TREES = register("trees_mystical", MYSTICAL_TREE_FILTERED, VegetationPlacedFeatures.modifiers(RarityFilterPlacementModifier.of(100)));

    private static TreeFeatureConfig.Builder builder(Block log, Block leaves, int baseHeight, int firstRandomHeight, int secondRandomHeight, int radius) {
        return new TreeFeatureConfig.Builder(BlockStateProvider.of(log), new StraightTrunkPlacer(baseHeight, firstRandomHeight, secondRandomHeight), BlockStateProvider.of(leaves), new BlobFoliagePlacer(ConstantIntProvider.create(radius), ConstantIntProvider.create(0), 3), new TwoLayersFeatureSize(1, 0, 1));
    }

    private static TreeFeatureConfig.Builder mysticalTree() {
        return builder(Blocks.OAK_LOG, Blocks.OAK_LEAVES, 4, 2, 0, 2).decorators(ImmutableList.of(MysticalTreeLogDecorator.INSTANCE)).ignoreVines();
    }

    public static RegistryEntry<PlacedFeature> register(String id, RegistryEntry<? extends ConfiguredFeature<?, ?>> registryEntry, PlacementModifier... modifiers) {
        return register(id, registryEntry, List.of(modifiers));
    }

    public static RegistryEntry<PlacedFeature> register(String id, RegistryEntry<? extends ConfiguredFeature<?, ?>> registryEntry, List<PlacementModifier> modifiers) {
        return BuiltinRegistries.add(BuiltinRegistries.PLACED_FEATURE, new Identifier(Yippee.MOD_ID, id), new PlacedFeature(RegistryEntry.upcast(registryEntry), List.copyOf(modifiers)));
    }

    public static <FC extends FeatureConfig, F extends Feature<FC>> RegistryEntry<ConfiguredFeature<FC, ?>> register(String id, F feature, FC config) {
        return BuiltinRegistries.addCasted(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Yippee.MOD_ID, id).toString(), new ConfiguredFeature<>(feature, config));
    }

}
