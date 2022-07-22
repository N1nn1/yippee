package com.ninni.yippee.block;

import com.ninni.yippee.init.YippeeSoundEvents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.ninni.yippee.Yippee.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class YippeeBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);

    public static final RegistryObject<Block> WHOOPEE_CUSHION = BLOCKS.register("whoopee_cushion", () -> new WhoopeeCushionBlock(BlockBehaviour.Properties.of(Material.SAND).instabreak().sound(SoundType.WOOL)));
    public static final RegistryObject<Block> TACO_BELL = BLOCKS.register("taco_bell", () -> new TacoBellBlock(BlockBehaviour.Properties.copy(Blocks.BELL)));
    public static final RegistryObject<Block> WEIGHT = BLOCKS.register("weight", () -> new WeightBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(10.0f, 1200.0f).sound(YippeeSoundEvents.WEIGHT)));

}
