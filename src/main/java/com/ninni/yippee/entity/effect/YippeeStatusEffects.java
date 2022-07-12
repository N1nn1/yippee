package com.ninni.yippee.entity.effect;


import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.ninni.yippee.Yippee.*;

public class YippeeStatusEffects {
    public static final StatusEffect FLATTENED = register("flattened", new PublicStatusEffect(StatusEffectCategory.HARMFUL, 0x12121A));

    private static StatusEffect register(String id, StatusEffect effect) { return Registry.register(Registry.STATUS_EFFECT, new Identifier(MOD_ID, id), effect); }
}
