package com.ninni.yippee.item;

import com.ninni.yippee.block.YippeeBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import static com.ninni.yippee.Yippee.*;

@SuppressWarnings("unused")
public class YippeeItems {

    public static final Item TBH = register("tbh", new TBHItem(new FabricItemSettings().group(ItemGroup.TOOLS).maxCount(1)));
    public static final Item MOYAI_STATUE = register("moyai_statue", new MoyaiStatueItem(new FabricItemSettings().group(ItemGroup.TOOLS).maxCount(1).rarity(Rarity.UNCOMMON)));
    public static final Item WHOOPEE_CUSHION = register("whoopee_cushion", new BlockItem(YippeeBlocks.WHOOPEE_CUSHION, new FabricItemSettings().group(ItemGroup.TOOLS).maxCount(16)));

    private static Item register(String id, Item item) { return Registry.register(Registry.ITEM, new Identifier(MOD_ID, id), item); }
}


