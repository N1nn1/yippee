package com.ninni.yippee.mixin.client;

import com.ninni.yippee.entity.effect.YippeeStatusEffects;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin<T extends LivingEntity, M extends EntityModel<T>> {
    @Inject(method = "setupTransforms", at = @At("TAIL"))
    private void onSetUpTransforms(T entity, MatrixStack matrices, float animationProgress, float bodyYaw, float tickDelta, CallbackInfo ci) {
        if (entity.hasStatusEffect(YippeeStatusEffects.FLATTENED)) {
            matrices.scale(1, 0.01F, 1);
            if (entity.isInSneakingPose()) matrices.translate(0, 15F, 0);
        }
    }
}
