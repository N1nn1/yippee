package com.ninni.yippee.events;

import com.ninni.yippee.Yippee;
import com.ninni.yippee.init.YippeeItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Yippee.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MiscEvents {

    @SubscribeEvent
    public void onLootTableLoaded(LootTableLoadEvent event) {
        LootTable table = event.getTable();
        ResourceLocation name = event.getName();
        if (name.equals(BuiltInLootTables.JUNGLE_TEMPLE)) {
            table.addPool(LootPool.lootPool().add(LootItem.lootTableItem(YippeeItems.MOYAI_STATUE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))).build());
        }
    }

}
