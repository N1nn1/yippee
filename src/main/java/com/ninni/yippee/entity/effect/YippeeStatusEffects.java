package com.ninni.yippee.entity.effect;


import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.ninni.yippee.Yippee.*;

public class YippeeStatusEffects {
    public static final StatusEffect FLATTENED = register("flattened", new PublicStatusEffect(StatusEffectCategory.HARMFUL, 0x12121A).addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.2f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));

    private static StatusEffect register(String id, StatusEffect effect) { return Registry.register(Registry.STATUS_EFFECT, new Identifier(MOD_ID, id), effect); }
}
