package com.ninni.yippee.mixin;

import com.ninni.yippee.entity.LivingEntityAccess;
import com.ninni.yippee.entity.effect.YippeeStatusEffects;
import com.ninni.yippee.util.YippeePacketIdentifiers;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements LivingEntityAccess {
    @Shadow public abstract boolean hasStatusEffect(StatusEffect effect);

    @Environment(EnvType.CLIENT)
    private @Unique boolean flattened;

    private LivingEntityMixin(EntityType<?> type, World world) {
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

    @Inject(
        method = "tickStatusEffects",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/LivingEntity;updateGlowing()V",
            ordinal = 0
        )
    )
    private void onStatusEffectChange(CallbackInfo ci) {
        for (ServerPlayerEntity player : PlayerLookup.all(this.getServer())) {
            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeInt(this.getId());
            buf.writeBoolean(this.hasStatusEffect(YippeeStatusEffects.FLATTENED));
            ServerPlayNetworking.send(player, YippeePacketIdentifiers.FLATTEN, buf);
        }
    }
}
