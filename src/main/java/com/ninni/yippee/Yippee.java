package com.ninni.yippee;

import com.ninni.yippee.block.YippeeBlocks;
import com.ninni.yippee.events.MiscEvents;
import com.ninni.yippee.init.YippeeSoundEvents;
import com.ninni.yippee.item.YippeeItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Yippee.MOD_ID)
public class Yippee {
	public static final String MOD_ID = "yippee";

	public Yippee() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::setup);

		YippeeBlocks.BLOCKS.register(modEventBus);
		YippeeItems.ITEMS.register(modEventBus);
		YippeeSoundEvents.SOUND_EVENTS.register(modEventBus);

		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(new MiscEvents());
	}

	private void setup(final FMLCommonSetupEvent event) {
	}

}
