package com.ninni.yippee.mixin;

import com.ninni.yippee.entity.effect.YippeeStatusEffects;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ExperienceOrbEntity.class)
public class ExperienceOrbEntityMixin {

    @Inject(method = "onPlayerCollision", at = @At(value = "HEAD"))
    private void applyWiseEffect(PlayerEntity player, CallbackInfo ci) {
        if (!player.world.isClient) {
            if (player.experiencePickUpDelay == 0 && player.hasStatusEffect(YippeeStatusEffects.WISE)) {
                player.addExperience(5);
            }
        }
    }
}
