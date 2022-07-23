package com.ninni.yippee.init;

import com.ninni.yippee.Yippee;
import com.ninni.yippee.block.blockentities.TacoBellBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Yippee.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class YippeeBlockEntityTypes {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Yippee.MOD_ID);

    public static final RegistryObject<BlockEntityType<TacoBellBlockEntity>> TACO_BELL = BLOCK_ENTITIES.register("taco_bell", () -> BlockEntityType.Builder.of(TacoBellBlockEntity::new, YippeeBlocks.TACO_BELL.get()).build(null));

}
