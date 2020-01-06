package com.unassigned.voidmagic.network;

import com.unassigned.voidmagic.VoidMagic;
import com.unassigned.voidmagic.network.messages.MessagePlayerVoid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class NetworkHandler {

    private static final ResourceLocation CHANNEL_NAME = new ResourceLocation(VoidMagic.MODID, "network");
    private static final String NETWORK_VERSION = new ResourceLocation(VoidMagic.MODID, "1").toString();

    public static SimpleChannel getNetworkChannel() {
        int id = 0;
        final SimpleChannel channel = NetworkRegistry.ChannelBuilder.named(CHANNEL_NAME)
                .clientAcceptedVersions(version -> true)
                .serverAcceptedVersions(version -> true)
                .networkProtocolVersion(()->NETWORK_VERSION)
                .simpleChannel();

        channel.messageBuilder(MessagePlayerVoid.class, id++)
                .decoder(MessagePlayerVoid::decode)
                .encoder(MessagePlayerVoid::encode)
                .consumer(MessagePlayerVoid::handle)
                .add();

        return channel;
    }

    private NetworkHandler() {}
}
