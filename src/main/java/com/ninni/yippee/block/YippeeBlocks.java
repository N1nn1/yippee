package com.ninni.yippee.block;

import com.ninni.yippee.item.YippeeItems;
import com.ninni.yippee.sound.YippeeBlockSoundGroups;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
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
import static net.minecraft.block.Blocks.*;

public class YippeeBlocks {

    public static final Block WHOOPEE_CUSHION = register("whoopee_cushion", new WhoopeeCushionBlock(FabricBlockSettings.of(Material.AGGREGATE).breakInstantly().sounds(BlockSoundGroup.WOOL)));
    public static final Block TACO_BELL = register("taco_bell", new TacoBellBlock(FabricBlockSettings.copyOf(BELL)));
    public static final Block WEIGHT = register("weight", new WeightBlock(FabricBlockSettings.of(Material.METAL, MapColor.IRON_GRAY).requiresTool().strength(10.0f, 1200.0f).sounds(YippeeBlockSoundGroups.WEIGHT)));

    private static Block register(String id, Block block) { return Registry.register(Registry.BLOCK, new Identifier(MOD_ID, id), block); }

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
