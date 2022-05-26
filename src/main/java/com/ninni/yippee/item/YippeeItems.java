package com.ninni.yippee.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import static com.ninni.yippee.Yippee.*;

@SuppressWarnings("unused")
public class YippeeItems {

    public static final Item CREATURE = register("creature", new CreatureItem(new FabricItemSettings().group(ItemGroup.TOOLS).maxCount(1).rarity(Rarity.UNCOMMON)));
    public static final Item MOYAI_STATUE = register("moyai_statue", new MoyaiStatueItem(new FabricItemSettings().group(ItemGroup.TOOLS).maxCount(1).rarity(Rarity.UNCOMMON)));

    private static Item register(String id, Item item) { return Registry.register(Registry.ITEM, new Identifier(MOD_ID, id), item); }
}


