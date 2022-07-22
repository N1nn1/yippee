package com.ninni.yippee.network;

import com.ninni.yippee.Yippee;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.Optional;

public class YippeeNetworkHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder.named(
                    new ResourceLocation(Yippee.MOD_ID, "network"))
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();

    protected static int packetID = 0;

    public YippeeNetworkHandler() {
    }

    public static void init() {
        INSTANCE.registerMessage(getPacketID(), FlattenPacket.class, FlattenPacket::write, FlattenPacket::read, FlattenPacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
    }

    public static int getPacketID() {
        return packetID++;
    }
}
