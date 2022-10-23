package com.ninni.yippee.world;

import com.ninni.yippee.Yippee;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Yippee.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class YippeeTreeDecorators {

    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATOR_TYPES = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES, Yippee.MOD_ID);

    public static final RegistryObject<TreeDecoratorType<MysticalTreeLogDecorator>> MYSTICAL_TREE_LOG = TREE_DECORATOR_TYPES.register("mystical_tree_log", () -> new TreeDecoratorType<>(MysticalTreeLogDecorator.CODEC));

}