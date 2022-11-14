package com.ninni.yippee.block.entity;

import com.ninni.yippee.block.YippeeBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.ninni.yippee.Yippee.MOD_ID;

public class YippeeBlockEntityType {
    public static BlockEntityType<TacoBellBlockEntity> TACO_BELL;

    public static final void initialize() {
        TACO_BELL = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, "taco_bell"), FabricBlockEntityTypeBuilder.create(TacoBellBlockEntity::new, YippeeBlocks.TACO_BELL).build(null));
    }
}
