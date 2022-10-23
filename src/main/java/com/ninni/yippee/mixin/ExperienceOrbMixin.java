package com.ninni.yippee.mixin;

import com.ninni.yippee.init.YippeeMobEffects;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ExperienceOrb.class)
public class ExperienceOrbMixin {

    @Inject(method = "playerTouch", at = @At(value = "HEAD"))
    private void applyWiseEffect(Player player, CallbackInfo ci) {
        if (!player.level.isClientSide()) {
            if (player.takeXpDelay == 0 && player.hasEffect(YippeeMobEffects.WISE.get())) {
                player.giveExperiencePoints(5);
            }
        }
    }
}