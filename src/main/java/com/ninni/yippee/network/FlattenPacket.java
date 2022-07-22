package com.ninni.yippee.network;

import com.ninni.yippee.entities.LivingEntityAccess;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;

import java.util.Optional;
import java.util.function.Supplier;

public class FlattenPacket {
    private final int entityId;
    private final boolean flag;

    public FlattenPacket(int entityId, boolean flag) {
        this.entityId = entityId;
        this.flag = flag;
    }

    public static FlattenPacket read(FriendlyByteBuf buf) {
        int entityId = buf.readInt();
        boolean flag = buf.readBoolean();
        return new FlattenPacket(entityId, flag);
    }

    public static void write(FlattenPacket packet, FriendlyByteBuf buf) {
        buf.writeInt(packet.entityId);
        buf.writeBoolean(packet.flag);
    }

    public static void handle(FlattenPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Minecraft minecraft = Minecraft.getInstance();
            LocalPlayer clientPlayer = minecraft.player;
            Optional.ofNullable(minecraft.level).ifPresent(world -> {
                int id = packet.getEntityId();
                Optional.ofNullable(minecraft.level.getEntity(id))
                        .filter(LivingEntity.class::isInstance)
                        .map(LivingEntityAccess.class::cast)
                        .ifPresent(entity -> {
                            boolean flattened = packet.getFlag();
                            entity.setFlattened(flattened);
                        });
            });
        });
        ctx.get().setPacketHandled(true);
    }

    @OnlyIn(Dist.CLIENT)
    public int getEntityId() {
        return this.entityId;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean getFlag() {
        return this.flag;
    }

}
