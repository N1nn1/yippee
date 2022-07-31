package com.ninni.yippee.item;

import com.ninni.yippee.block.YippeeBlocks;
import com.ninni.yippee.sound.YippeeSoundEvents;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import static com.ninni.yippee.Yippee.*;

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

    private static Item register(String id, Item item) { return Registry.register(Registry.ITEM, new Identifier(MOD_ID, id), item); }
}


