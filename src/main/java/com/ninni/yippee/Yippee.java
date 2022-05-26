package com.ninni.yippee;

import com.google.common.reflect.Reflection;
import com.ninni.yippee.item.YippeeItems;
import com.ninni.yippee.sound.YippeeSoundEvents;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Yippee implements ModInitializer {
	public static final String MOD_ID = "yippee";

	@Override
	@SuppressWarnings("UnstableApiUsage")
	public void onInitialize() {
		Reflection.initialize(
		YippeeSoundEvents.class,
		YippeeItems.class);
	}
}
