package com.ninni.yippee.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.ninni.yippee.Yippee.*;

public class YippeeBlocks {

    public static final Block WHOOPEE_CUSHION = register("whoopee_cushion", new WhoopeeCushionBlock(AbstractBlock.Settings.of(Material.AGGREGATE).requiresTool().strength(0.4F).sounds(BlockSoundGroup.WOOL)));

    private static Block register(String id, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(MOD_ID, id), block);
    }
}
