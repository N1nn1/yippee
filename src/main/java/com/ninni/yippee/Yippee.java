package com.ninni.yippee;

import com.ninni.yippee.init.YippeeBlocks;
import com.ninni.yippee.init.YippeeMobEffects;
import com.ninni.yippee.events.MiscEvents;
import com.ninni.yippee.init.YippeeBlockEntityTypes;
import com.ninni.yippee.init.YippeeSoundEvents;
import com.ninni.yippee.init.YippeeItems;
import com.ninni.yippee.network.YippeeNetworkHandler;
import com.ninni.yippee.util.YippeeCreativeModeTab;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Yippee.MOD_ID)
public class Yippee {
	public static final String MOD_ID = "yippee";
	public static final CreativeModeTab TAB = new YippeeCreativeModeTab(MOD_ID);

	public Yippee() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::setup);

		YippeeBlocks.BLOCKS.register(modEventBus);
		YippeeBlockEntityTypes.BLOCK_ENTITIES.register(modEventBus);
		YippeeItems.ITEMS.register(modEventBus);
		YippeeMobEffects.MOB_EFFECT.register(modEventBus);
		YippeeSoundEvents.SOUND_EVENTS.register(modEventBus);

		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(new MiscEvents());
	}

	private void setup(final FMLCommonSetupEvent event) {
		event.enqueueWork(YippeeNetworkHandler::init);
	}

}
