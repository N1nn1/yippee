package com.ninni.yippee.entity.damage;

import net.minecraft.entity.damage.DamageSource;

public class YippeeDamageSource extends DamageSource {
    public static final DamageSource WEIGHT = new YippeeDamageSource("weight").setFallingBlock();

    protected YippeeDamageSource(String name) {
        super(name);
    }
}
