package com.ninni.yippee.mixin;

import com.ninni.yippee.effects.YippeeMobEffect;
import com.ninni.yippee.effects.YippeeMobEffects;
import com.ninni.yippee.entities.LivingEntityAccess;
import com.ninni.yippee.network.FlattenPacket;
import com.ninni.yippee.network.YippeeNetworkHandler;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.PacketDistributor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements LivingEntityAccess {

    @Shadow public abstract boolean hasEffect(MobEffect p_21024_);

    @OnlyIn(Dist.CLIENT)
    private @Unique boolean flattened;

    private LivingEntityMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Override
    public @Unique boolean isFlattened() {
        return this.flattened;
    }

    @Override
    public @Unique void setFlattened(boolean flattened) {
        this.flattened = flattened;
    }

    @Inject(method = "tickEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;updateGlowingStatus()V", ordinal = 0))
    private void onStatusEffectChange(CallbackInfo ci) {
        YippeeNetworkHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> this), new FlattenPacket(this.getId(), this.hasEffect(YippeeMobEffects.FLATTENED.get())));
    }
}
