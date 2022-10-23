package com.ninni.yippee.item;

import com.ninni.yippee.block.YippeeBlocks;
import com.ninni.yippee.sound.YippeeSoundEvents;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import static com.ninni.yippee.Yippee.ITEM_GROUP;
import static com.ninni.yippee.Yippee.MOD_ID;

@SuppressWarnings("unused")
public class YippeeItems {

    public static final Item YIPPEE = register("yippee", new Item(new FabricItemSettings().maxCount(1).fireproof().rarity(Rarity.EPIC)));

    public static final Item TBH = register("tbh", new TBHItem(new FabricItemSettings().group(ITEM_GROUP).maxCount(1)));
    public static final Item MOYAI_STATUE = register("moyai_statue", new MoyaiStatueItem(new FabricItemSettings().group(ITEM_GROUP).maxCount(1).rarity(Rarity.UNCOMMON)));
    public static final Item WHOOPEE_CUSHION = register("whoopee_cushion", new BlockItem(YippeeBlocks.WHOOPEE_CUSHION, new FabricItemSettings().group(ITEM_GROUP).maxCount(16)));
    public static final Item TACO_BELL = register("taco_bell", new BlockItem(YippeeBlocks.TACO_BELL, new FabricItemSettings().group(ITEM_GROUP)));
    public static final Item WEIGHT = register("weight", new BlockItem(YippeeBlocks.WEIGHT, new FabricItemSettings().group(ITEM_GROUP)));
    public static final Item DAMAGED_DISC = register("damaged_disc", new PublicMusicDiscItem(15, YippeeSoundEvents.ITEM_RECORD_SCRATCH1, new Item.Settings().maxCount(1).group(ITEM_GROUP).rarity(Rarity.RARE), 1));
    public static final Item BROKEN_DISC = register("broken_disc", new PublicMusicDiscItem(15, YippeeSoundEvents.ITEM_RECORD_SCRATCH2, new Item.Settings().maxCount(1).group(ITEM_GROUP).rarity(Rarity.RARE), 2));
    public static final Item MYSTICAL_OAK_LOG = register("mystical_oak_log", new BlockItem(YippeeBlocks.MYSTICAL_OAK_LOG, new FabricItemSettings().group(ITEM_GROUP).rarity(Rarity.UNCOMMON)));
    public static final Item RESTING_MYSTICAL_OAK_LOG = register("resting_mystical_oak_log", new BlockItem(YippeeBlocks.RESTING_MYSTICAL_OAK_LOG, new FabricItemSettings().group(ITEM_GROUP).rarity(Rarity.UNCOMMON)));
    public static final Item STRIPPED_MYSTICAL_OAK_LOG = register("stripped_mystical_oak_log", new BlockItem(YippeeBlocks.STRIPPED_MYSTICAL_OAK_LOG, new FabricItemSettings().group(ITEM_GROUP).rarity(Rarity.UNCOMMON)));
    public static final Item MYSTICAL_OAK_PLANKS = register("mystical_oak_planks", new BlockItem(YippeeBlocks.MYSTICAL_OAK_PLANKS, new FabricItemSettings().group(ITEM_GROUP).rarity(Rarity.UNCOMMON)));
    public static final Item MYSTICAL_OAK_SAPLING = register("mystical_oak_sapling", new BlockItem(YippeeBlocks.MYSTICAL_OAK_SAPLING, new FabricItemSettings().group(ITEM_GROUP).rarity(Rarity.UNCOMMON)));
    public static final Item MYSTICAL_OAK_DOOR = register("mystical_oak_door", new BlockItem(YippeeBlocks.MYSTICAL_OAK_DOOR, new FabricItemSettings().group(ITEM_GROUP).rarity(Rarity.UNCOMMON)));
    public static final Item MYSTICAL_OAK_TRAPDOOR = register("mystical_oak_trapdoor", new BlockItem(YippeeBlocks.MYSTICAL_OAK_TRAPDOOR, new FabricItemSettings().group(ITEM_GROUP).rarity(Rarity.UNCOMMON)));
    public static final Item MYSTICAL_SAP_BOTTLE = register("mystical_sap_bottle", new MysticalSapBottleItem(new Item.Settings().maxCount(16).group(ITEM_GROUP).recipeRemainder(Items.GLASS_BOTTLE).rarity(Rarity.UNCOMMON).food(new FoodComponent.Builder().hunger(3).saturationModifier(0.2f).build())));

    private static Item register(String id, Item item) { return Registry.register(Registry.ITEM, new Identifier(MOD_ID, id), item); }
}


