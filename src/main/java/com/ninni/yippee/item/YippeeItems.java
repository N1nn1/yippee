package com.ninni.yippee.item;

import com.ninni.yippee.Yippee;
import com.ninni.yippee.block.YippeeBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.ninni.yippee.Yippee.MOD_ID;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class YippeeItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static final RegistryObject<Item> TBH = ITEMS.register("tbh", () -> new TBHItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1)));
    public static final RegistryObject<Item> MOYAI_STATUE = ITEMS.register("moyai_statue", () -> new MoyaiStatueItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> WHOOPEE_CUSHION = ITEMS.register("whoopee_cushion", () -> new BlockItem(YippeeBlocks.WHOOPEE_CUSHION.get(), new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE).stacksTo(16)));

}


