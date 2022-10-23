package com.ninni.yippee.init;

import com.ninni.yippee.Yippee;
import com.ninni.yippee.item.MoyaiStatueItem;
import com.ninni.yippee.item.MysticalSapBottleItem;
import com.ninni.yippee.item.TBHItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
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

    public static final RegistryObject<Item> YIPPEE = ITEMS.register("yippee", () -> new Item(new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> TBH = ITEMS.register("tbh", () -> new TBHItem(new Item.Properties().tab(Yippee.TAB).stacksTo(1)));
    public static final RegistryObject<Item> MOYAI_STATUE = ITEMS.register("moyai_statue", () -> new MoyaiStatueItem(new Item.Properties().tab(Yippee.TAB).stacksTo(1).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> WHOOPEE_CUSHION = ITEMS.register("whoopee_cushion", () -> new BlockItem(YippeeBlocks.WHOOPEE_CUSHION.get(), new Item.Properties().tab(Yippee.TAB).stacksTo(16)));
    public static final RegistryObject<Item> TACO_BELL = ITEMS.register("taco_bell", () -> new BlockItem(YippeeBlocks.TACO_BELL.get(), new Item.Properties().tab(Yippee.TAB)));
    public static final RegistryObject<Item> WEIGHT = ITEMS.register("weight", () -> new BlockItem(YippeeBlocks.WEIGHT.get(), new Item.Properties().tab(Yippee.TAB)));
    public static final RegistryObject<Item> DAMAGED_DISC = ITEMS.register("damaged_disc", () -> new RecordItem(15, YippeeSoundEvents.ITEM_RECORD_SCRATCH1, new Item.Properties().stacksTo(1).tab(Yippee.TAB).rarity(Rarity.RARE), 1));
    public static final RegistryObject<Item> BROKEN_DISC = ITEMS.register("broken_disc", () -> new RecordItem(15, YippeeSoundEvents.ITEM_RECORD_SCRATCH2, new Item.Properties().stacksTo(1).tab(Yippee.TAB).rarity(Rarity.RARE), 2));
    public static final RegistryObject<Item> MYSTICAL_OAK_LOG = ITEMS.register("mystical_oak_log", () -> new BlockItem(YippeeBlocks.MYSTICAL_OAK_LOG.get(), new Item.Properties().tab(Yippee.TAB).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> RESTING_MYSTICAL_OAK_LOG = ITEMS.register("resting_mystical_oak_log", () -> new BlockItem(YippeeBlocks.RESTING_MYSTICAL_OAK_LOG.get(), new Item.Properties().tab(Yippee.TAB).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> STRIPPED_MYSTICAL_OAK_LOG = ITEMS.register("stripped_mystical_oak_log", () -> new BlockItem(YippeeBlocks.STRIPPED_MYSTICAL_OAK_LOG.get(), new Item.Properties().tab(Yippee.TAB).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> MYSTICAL_OAK_PLANKS = ITEMS.register("mystical_oak_planks", () -> new BlockItem(YippeeBlocks.MYSTICAL_OAK_PLANKS.get(), new Item.Properties().tab(Yippee.TAB).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> MYSTICAL_OAK_SAPLING = ITEMS.register("mystical_oak_sapling", () -> new BlockItem(YippeeBlocks.MYSTICAL_OAK_SAPLING.get(), new Item.Properties().tab(Yippee.TAB).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> MYSTICAL_OAK_DOOR = ITEMS.register("mystical_oak_door", () -> new BlockItem(YippeeBlocks.MYSTICAL_OAK_DOOR.get(), new Item.Properties().tab(Yippee.TAB).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> MYSTICAL_OAK_TRAPDOOR = ITEMS.register("mystical_oak_trapdoor", () -> new BlockItem(YippeeBlocks.MYSTICAL_OAK_TRAPDOOR.get(), new Item.Properties().tab(Yippee.TAB).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> MYSTICAL_SAP_BOTTLE = ITEMS.register("mystical_sap_bottle", () -> new MysticalSapBottleItem(new Item.Properties().stacksTo(16).tab(Yippee.TAB).craftRemainder(Items.GLASS_BOTTLE).rarity(Rarity.UNCOMMON).food(new FoodProperties.Builder().nutrition(3).saturationMod(0.2f).build())));

}


