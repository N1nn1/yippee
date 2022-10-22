package com.ninni.yippee.block;

import com.ninni.yippee.item.YippeeItems;
import com.ninni.yippee.sound.YippeeBlockSoundGroups;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.sapling.OakSaplingGenerator;
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
    public static final Block MYSTICAL_OAK_LOG = register("mystical_oak_log", new MysticalOakLogBlock(FabricBlockSettings.of(Material.WOOD, MapColor.OAK_TAN).strength(4.0f).sounds(YippeeBlockSoundGroups.MYSTICAL_WOOD).luminance(state -> 8)));
    public static final Block RESTING_MYSTICAL_OAK_LOG = register("resting_mystical_oak_log", new RestingMysticalOakLogBLock(FabricBlockSettings.of(Material.WOOD, MapColor.OAK_TAN).strength(4.0f).sounds(YippeeBlockSoundGroups.MYSTICAL_WOOD)));
    public static final Block STRIPPED_MYSTICAL_OAK_LOG = register("stripped_mystical_oak_log", new AbstractMysticalLogBlock(FabricBlockSettings.copyOf(MYSTICAL_OAK_LOG)));
    public static final Block MYSTICAL_OAK_PLANKS = register("mystical_oak_planks", new MysticalOakPlanksBlock(FabricBlockSettings.copyOf(OAK_PLANKS).sounds(YippeeBlockSoundGroups.MYSTICAL_WOOD).luminance(state -> 3)) {});
    public static final Block MYSTICAL_OAK_SAPLING = register("mystical_oak_sapling", new SaplingBlock(new OakSaplingGenerator(), FabricBlockSettings.copyOf(OAK_SAPLING).luminance(state -> 4)));
    public static final Block POTTED_MYSTICAL_OAK_SAPLING = register("potted_mystical_oak_sapling", new FlowerPotBlock(MYSTICAL_OAK_SAPLING, AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque().luminance(state -> 4)));
    public static final Block MYSTICAL_OAK_DOOR = register("mystical_oak_door", new DoorBlock(FabricBlockSettings.copyOf(OAK_DOOR).sounds(YippeeBlockSoundGroups.MYSTICAL_WOOD).strength(6.0f)));
    public static final Block MYSTICAL_OAK_TRAPDOOR = register("mystical_oak_trapdoor", new TrapdoorBlock(FabricBlockSettings.copyOf(OAK_TRAPDOOR).sounds(YippeeBlockSoundGroups.MYSTICAL_WOOD).strength(6.0f)));

    private static Block register(String id, Block block) { return Registry.register(Registry.BLOCK, new Identifier(MOD_ID, id), block); }

    static {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (id.equals(LootTables.JUNGLE_TEMPLE_CHEST)) {
                tableBuilder.pool(LootPool.builder()
                                          .with(ItemEntry.builder(YippeeItems.MOYAI_STATUE)
                                                         .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0, 1))))
                                          .build());
            }
        });
    }
}
