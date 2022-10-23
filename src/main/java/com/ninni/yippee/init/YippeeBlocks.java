package com.ninni.yippee.init;

import com.ninni.yippee.block.AbstractMysticalLogBlock;
import com.ninni.yippee.block.MysticalOakLogBlock;
import com.ninni.yippee.block.MysticalOakPlanksBlock;
import com.ninni.yippee.block.RestingMysticalOakLogBLock;
import com.ninni.yippee.block.TacoBellBlock;
import com.ninni.yippee.block.WeightBlock;
import com.ninni.yippee.block.WhoopeeCushionBlock;
import com.ninni.yippee.init.YippeeSoundEvents;
import com.ninni.yippee.world.MysticalSaplingGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TrapDoorBlock;
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
    public static final RegistryObject<Block> MYSTICAL_OAK_LOG = BLOCKS.register("mystical_oak_log", () -> new MysticalOakLogBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(4.0f).sound(YippeeSoundEvents.MYSTICAL_WOOD).lightLevel(state -> 8)));
    public static final RegistryObject<Block> RESTING_MYSTICAL_OAK_LOG = BLOCKS.register("resting_mystical_oak_log", () -> new RestingMysticalOakLogBLock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(4.0f).sound(YippeeSoundEvents.MYSTICAL_WOOD)));
    public static final RegistryObject<Block> STRIPPED_MYSTICAL_OAK_LOG = BLOCKS.register("stripped_mystical_oak_log", () -> new AbstractMysticalLogBlock(BlockBehaviour.Properties.copy(MYSTICAL_OAK_LOG.get())));
    public static final RegistryObject<Block> MYSTICAL_OAK_PLANKS = BLOCKS.register("mystical_oak_planks", () -> new MysticalOakPlanksBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(YippeeSoundEvents.MYSTICAL_WOOD).lightLevel(state -> 3)));
    public static final RegistryObject<Block> MYSTICAL_OAK_SAPLING = BLOCKS.register("mystical_oak_sapling", () -> new SaplingBlock(new MysticalSaplingGenerator(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING).lightLevel(state -> 4)));
    public static final RegistryObject<Block> POTTED_MYSTICAL_OAK_SAPLING = BLOCKS.register("potted_mystical_oak_sapling", () -> new FlowerPotBlock(MYSTICAL_OAK_SAPLING.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion().lightLevel(state -> 4)));
    public static final RegistryObject<Block> MYSTICAL_OAK_DOOR = BLOCKS.register("mystical_oak_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR).sound(YippeeSoundEvents.MYSTICAL_WOOD).strength(6.0f)));
    public static final RegistryObject<Block> MYSTICAL_OAK_TRAPDOOR = BLOCKS.register("mystical_oak_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR).sound(YippeeSoundEvents.MYSTICAL_WOOD).strength(6.0f)));

}
