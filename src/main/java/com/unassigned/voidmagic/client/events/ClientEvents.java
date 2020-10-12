package com.unassigned.voidmagic.client.events;

import com.unassigned.voidmagic.VoidMagic;
import com.unassigned.voidmagic.common.capability.playervoid.CapabilityPlayerVoid;
import com.unassigned.voidmagic.common.util.ModRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.Hand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = VoidMagic.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvents {

    @SubscribeEvent
    public static void onRender(final RenderGameOverlayEvent event) {
        if(event.isCancelable() || event.getType() != RenderGameOverlayEvent.ElementType.EXPERIENCE) return;

        ClientPlayerEntity player = Minecraft.getInstance().player;

        if(player.getHeldItem(Hand.MAIN_HAND).isEmpty()) return;
        if(player.getHeldItem(Hand.MAIN_HAND).getItem() != ModRegistration.TRANSMUTESTONE.get()) return;

        FontRenderer fr = Minecraft.getInstance().fontRenderer;

        CapabilityPlayerVoid.getPlayerVoid(player).ifPresent(v -> fr.drawString(
                "Current Void Stored: " + v.getVoidStored() + "/" + v.getMaxVoidStored(),
                0, 0, 0xffffff));

    }

}
