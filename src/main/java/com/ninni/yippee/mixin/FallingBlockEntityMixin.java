package com.ninni.yippee.mixin;

import com.ninni.yippee.entity.damage.YippeeDamageSource;
import com.ninni.yippee.entity.effect.YippeeStatusEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FallingBlockEntity.class)
public abstract class FallingBlockEntityMixin {

    @Inject(method = "method_32879", at = @At("TAIL"))
    private static void onDamage(DamageSource source, float amount, Entity entity, CallbackInfo ci) {
        if (source.equals(YippeeDamageSource.WEIGHT) && entity instanceof LivingEntity livingEntity) {
            livingEntity.addStatusEffect(new StatusEffectInstance(YippeeStatusEffects.FLATTENED, 20 * 4, 0));
        }
    }
}
