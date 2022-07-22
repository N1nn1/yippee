package com.ninni.yippee.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.ninni.yippee.entities.LivingEntityAccess;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin<T extends LivingEntity, M extends EntityModel<T>> {

    @Inject(at = @At("TAIL"), method = "setupRotations")
    private void onSetUpTransforms(T entity, PoseStack matrices, float animationProgress, float bodyYaw, float tickDelta, CallbackInfo ci) {
        if (((LivingEntityAccess)entity).isFlattened()) {
            matrices.scale(1, 0.01F, 1);
            entity.getEyePosition().subtract(0,5,0);
            if (entity.isCrouching()) {
                matrices.translate(0, 15F, 0);
            }
        }
    }

}
