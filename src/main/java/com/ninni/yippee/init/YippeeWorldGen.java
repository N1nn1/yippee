package com.ninni.yippee.init;

import com.google.common.collect.ImmutableList;
import com.ninni.yippee.Yippee;
import com.ninni.yippee.world.MysticalTreeLogDecorator;
import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

import java.util.List;

public class YippeeWorldGen {

    public static void init() { }

    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> MYSTICAL_TREE = register("mystical", Feature.TREE, mysticalTree().build());
    public static final Holder<PlacedFeature> MYSTICAL_TREE_CHECKED = register("mystical_tree", MYSTICAL_TREE, PlacementUtils.filteredByBlockSurvival(YippeeBlocks.MYSTICAL_OAK_SAPLING.get()));

    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> MYSTICAL_TREE_FILTERED = register("mystical_tree_filtered", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(MYSTICAL_TREE_CHECKED, 0.0F)), MYSTICAL_TREE_CHECKED));
    public static final Holder<PlacedFeature> MYSTICAL_TREES = register("trees_mystical", MYSTICAL_TREE_FILTERED, VegetationPlacements.treePlacement(RarityFilter.onAverageOnceEvery(50)));

    private static TreeConfiguration.TreeConfigurationBuilder builder(Block log, Block leaves, int baseHeight, int firstRandomHeight, int secondRandomHeight, int radius) {
        return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(log), new StraightTrunkPlacer(baseHeight, firstRandomHeight, secondRandomHeight), BlockStateProvider.simple(leaves), new BlobFoliagePlacer(ConstantInt.of(radius), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1));
    }

    private static TreeConfiguration.TreeConfigurationBuilder mysticalTree() {
        return builder(Blocks.OAK_LOG, Blocks.OAK_LEAVES, 4, 2, 0, 2).decorators(ImmutableList.of(MysticalTreeLogDecorator.INSTANCE)).ignoreVines();
    }

    public static Holder<PlacedFeature> register(String id, Holder<? extends ConfiguredFeature<?, ?>> registryEntry, PlacementModifier... modifiers) {
        return register(id, registryEntry, List.of(modifiers));
    }

    public static Holder<PlacedFeature> register(String id, Holder<? extends ConfiguredFeature<?, ?>> registryEntry, List<PlacementModifier> modifiers) {
        return BuiltinRegistries.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(Yippee.MOD_ID, id).toString(), new PlacedFeature(Holder.hackyErase(registryEntry), List.copyOf(modifiers)));
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> register(String id, F feature, FC config) {
        return BuiltinRegistries.registerExact(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(Yippee.MOD_ID, id).toString(), new ConfiguredFeature<>(feature, config));
    }

}
