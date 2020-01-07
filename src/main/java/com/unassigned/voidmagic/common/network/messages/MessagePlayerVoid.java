package com.unassigned.voidmagic.common.network.messages;

import com.unassigned.voidmagic.VoidMagic;
import com.unassigned.voidmagic.common.capability.playervoid.PlayerVoidProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

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
            PlayerEntity e = VoidMagic.proxy.getClientPlayer();
            if(e != null) {
                e.getCapability(PlayerVoidProvider.CAPABILITY_PLAYER_VOID).ifPresent(pv -> pv.setVoidStored(message.playerVoid));
            }
        });
        ctx.get().setPacketHandled(true);
    }
}