package com.ninni.yippee.init;

import com.ninni.yippee.item.MoyaiStatueItem;
import com.ninni.yippee.item.TBHItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
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
    public static final RegistryObject<Item> TACO_BELL = ITEMS.register("taco_bell", () -> new BlockItem(YippeeBlocks.TACO_BELL.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> WEIGHT = ITEMS.register("weight", () -> new BlockItem(YippeeBlocks.WEIGHT.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> DAMAGED_DISC = ITEMS.register("damaged_disc", () -> new RecordItem(15, YippeeSoundEvents.ITEM_RECORD_SCRATCH1, new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_MISC).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> BROKEN_DISC = ITEMS.register("broken_disc", () -> new RecordItem(15, YippeeSoundEvents.ITEM_RECORD_SCRATCH2, new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_MISC).rarity(Rarity.RARE)));

}


