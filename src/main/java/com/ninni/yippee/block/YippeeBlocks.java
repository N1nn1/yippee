package com.ninni.yippee.block;

import com.ninni.yippee.item.YippeeItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.ninni.yippee.Yippee.*;

public class YippeeBlocks {

    public static final Block WHOOPEE_CUSHION = register("whoopee_cushion", new WhoopeeCushionBlock(AbstractBlock.Settings.of(Material.AGGREGATE).breakInstantly().sounds(BlockSoundGroup.WOOL)));


    private static Block register(String id, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(MOD_ID, id), block);
    }

    static {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (id.equals(LootTables.JUNGLE_TEMPLE_CHEST)) {
                tableBuilder.pool(LootPool.builder()
                                          .rolls(UniformLootNumberProvider.create(0, 1))
                                          .with(ItemEntry.builder(YippeeItems.MOYAI_STATUE)
                                                         .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 1))))
                                          .build());
            }
        });
    }
}
