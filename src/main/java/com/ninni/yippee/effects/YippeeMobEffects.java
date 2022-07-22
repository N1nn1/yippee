package com.ninni.yippee.effects;

import com.ninni.yippee.Yippee;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Yippee.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class YippeeMobEffects {

    public static final DeferredRegister<MobEffect> MOB_EFFECT = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Yippee.MOD_ID);

    public static final RegistryObject<MobEffect> FLATTENED = MOB_EFFECT.register("flattened", () -> new YippeeMobEffect(MobEffectCategory.HARMFUL, 0x12121A));


}
