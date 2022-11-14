package com.ninni.yippee;

import com.google.common.reflect.Reflection;
import com.ninni.yippee.block.YippeeBlocks;
import com.ninni.yippee.block.entity.YippeeBlockEntityType;
import com.ninni.yippee.entity.effect.YippeeStatusEffects;
import com.ninni.yippee.item.YippeeItems;
import com.ninni.yippee.sound.YippeeSoundEvents;
import com.ninni.yippee.world.YippeeTreeDecorators;
import com.ninni.yippee.world.YippeeWorldGen;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.BiomeTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public class Yippee implements ModInitializer {
	public static final String MOD_ID = "yippee";
	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "item_group"), () -> new ItemStack(YippeeItems.YIPPEE));

	@Override
	@SuppressWarnings("UnstableApiUsage")
	public void onInitialize() {
		YippeeWorldGen.init();
		YippeeTreeDecorators.init();
		Reflection.initialize(
		YippeeSoundEvents.class,
		YippeeBlocks.class,
		YippeeStatusEffects.class,
		YippeeItems.class
		);
		YippeeWorldGen.MYSTICAL_TREES.getKey().ifPresent(this::addFeature);
		YippeeBlockEntityType.initialize();
	}

	private void addFeature(RegistryKey<PlacedFeature> placedFeatureRegistryKey) {
		BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_FOREST), GenerationStep.Feature.VEGETAL_DECORATION, placedFeatureRegistryKey);
	}
}
