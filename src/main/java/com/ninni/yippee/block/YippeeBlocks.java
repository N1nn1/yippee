package com.ninni.yippee.block;

import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.loot.LootTables;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.ninni.yippee.Yippee.*;

public class YippeeBlocks {

    public static final Block WHOOPEE_CUSHION = register("whoopee_cushion", new WhoopeeCushionBlock(AbstractBlock.Settings.of(Material.AGGREGATE).requiresTool().strength(0.2F).sounds(BlockSoundGroup.WOOL)));


    private static Block register(String id, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(MOD_ID, id), block);
    }

    static {
        LootTableLoadingCallback.EVENT.register((resourceManager, manager, id, supplier, setter) -> {
            if (id.equals(LootTables.JUNGLE_TEMPLE_CHEST)) { supplier.copyFrom(manager.getTable(createDefaultLootTable(LootTables.JUNGLE_TEMPLE_CHEST))); }
        });
    }
    private static Identifier createDefaultLootTable(Identifier base) {
        return new Identifier(base.getNamespace(), "%s/%s".formatted(MOD_ID, base.getPath()));
    }
}
