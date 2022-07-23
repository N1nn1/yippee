package com.ninni.yippee.mixin;

import com.ninni.yippee.block.WeightBlock;
import com.ninni.yippee.entities.damagesource.YippeeDamageSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.block.Fallable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(FallingBlockEntity.class)
public class FallingBlockEntityMixin {

    @Inject(at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(II)I"), method = "causeFallDamage")
    private void onDamage(float p_149643_, float p_149644_, DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        FallingBlockEntity $this = (FallingBlockEntity) (Object) this;
        Predicate<Entity> predicate;
        DamageSource damagesource;
        if ($this.getBlockState().getBlock() instanceof Fallable fallable) {
            predicate = fallable.getHurtsEntitySelector();
            damagesource = fallable.getFallDamageSource();
        } else {
            predicate = EntitySelector.NO_SPECTATORS;
            damagesource = DamageSource.FALLING_BLOCK;
        }
        $this.level.getEntities($this, $this.getBoundingBox(), predicate).forEach((entity) -> {
            boolean b = damagesource.equals(YippeeDamageSource.WEIGHT) && entity instanceof LivingEntity;
            if (damagesource.equals(YippeeDamageSource.WEIGHT) && entity instanceof LivingEntity livingEntity) {
                WeightBlock.applyStatusEffect(livingEntity);
            }
        });
    }

}
