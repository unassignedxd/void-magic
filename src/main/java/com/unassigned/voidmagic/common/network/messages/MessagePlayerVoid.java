package com.unassigned.voidmagic.common.network.messages;

import com.unassigned.voidmagic.common.capability.playervoid.CapabilityPlayerVoid;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.Optional;
import java.util.function.Supplier;

public class MessagePlayerVoid {

    private final int playerVoid;

    public MessagePlayerVoid(int playerVoid){
        this.playerVoid = playerVoid;
    }

    public static MessagePlayerVoid decode(PacketBuffer buf) {
        return new MessagePlayerVoid(buf.readInt());
    }

    public static void encode(MessagePlayerVoid message, PacketBuffer buf) {
        buf.writeInt(message.playerVoid);
    }

    public static void handle(MessagePlayerVoid message, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            PlayerEntity playerEntity = Minecraft.getInstance().player;
            CapabilityPlayerVoid.getPlayerVoid(playerEntity).ifPresent(pv -> pv.addVoid(message.playerVoid));
        });
        ctx.get().setPacketHandled(true);
    }
}
