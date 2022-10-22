package com.ninni.yippee;

import com.google.common.reflect.Reflection;
import com.ninni.yippee.block.YippeeBlocks;
import com.ninni.yippee.entity.effect.YippeeStatusEffects;
import com.ninni.yippee.item.YippeeItems;
import com.ninni.yippee.sound.YippeeSoundEvents;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class Yippee implements ModInitializer {
	public static final String MOD_ID = "yippee";
	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "item_group"), () -> new ItemStack(YippeeItems.YIPPEE));

	@Override
	@SuppressWarnings("UnstableApiUsage")
	public void onInitialize() {
		Reflection.initialize(
		YippeeSoundEvents.class,
		YippeeBlocks.class,
		YippeeStatusEffects.class,
		YippeeItems.class
		);
	}
}
