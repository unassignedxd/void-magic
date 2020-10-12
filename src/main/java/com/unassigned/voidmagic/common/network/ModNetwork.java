package com.unassigned.voidmagic.common.network;

import com.unassigned.voidmagic.VoidMagic;
import com.unassigned.voidmagic.common.network.messages.MessagePlayerVoid;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class ModNetwork {

    public static SimpleChannel INSTANCE;
    private static int ID = 0;

    private static int nextID() {
        return ID++;
    }

    public static void registerMessages() {
        INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(VoidMagic.MODID, "voidmagic"),
                () -> "1.0",
                s -> true,
                s -> true);

        INSTANCE.messageBuilder(MessagePlayerVoid.class, nextID())
                .decoder(MessagePlayerVoid::decode)
                .encoder(MessagePlayerVoid::encode)
                .consumer(MessagePlayerVoid::handle)
                .add();
    }

    public static void sendToClient(Object packet, ServerPlayerEntity player) {
        INSTANCE.sendTo(packet, player.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
    }

    public static void sendToServer(Object packet) {
        INSTANCE.sendToServer(packet);
    }
}
