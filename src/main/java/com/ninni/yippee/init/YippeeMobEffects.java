package com.ninni.yippee.init;

import com.ninni.yippee.Yippee;
import com.ninni.yippee.effects.YippeeMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Yippee.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class YippeeMobEffects {

    public static final DeferredRegister<MobEffect> MOB_EFFECT = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Yippee.MOD_ID);

    public static final RegistryObject<MobEffect> FLATTENED = MOB_EFFECT.register("flattened", () -> new YippeeMobEffect(MobEffectCategory.HARMFUL, 0x12121A).addAttributeModifier(Attributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.2f, AttributeModifier.Operation.MULTIPLY_TOTAL));
    public static final RegistryObject<MobEffect> WISE = MOB_EFFECT.register("wise", () -> new YippeeMobEffect(MobEffectCategory.BENEFICIAL, 0xF8A735));

}
