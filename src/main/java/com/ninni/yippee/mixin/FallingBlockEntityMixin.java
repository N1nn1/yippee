package com.ninni.yippee.mixin;

import com.ninni.yippee.block.WeightBlock;
import com.ninni.yippee.entities.damagesource.YippeeDamageSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FallingBlockEntity.class)
public abstract class FallingBlockEntityMixin {

    @Inject(method = "lambda$causeFallDamage$0", at = @At("TAIL"))
    private static void onDamage(DamageSource source, float amount, Entity entity, CallbackInfo ci) {
        if (source.equals(YippeeDamageSource.WEIGHT) && entity instanceof LivingEntity livingEntity) {
            WeightBlock.applyStatusEffect(livingEntity);
        }
    }

}
