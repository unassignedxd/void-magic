package com.unassigned.voidmagic.client.events;

import com.unassigned.voidmagic.common.capability.playervoid.PlayerVoidProvider;
import com.unassigned.voidmagic.common.items.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.Hand;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClientEvents {

    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent event) {
        if(event.isCancelable() || event.getType() != RenderGameOverlayEvent.ElementType.EXPERIENCE) return;

        ClientPlayerEntity player = Minecraft.getInstance().player;

        if(player.getHeldItem(Hand.MAIN_HAND).isEmpty()) return;
        if(player.getHeldItem(Hand.MAIN_HAND).getItem() != ModItems.transmuteStone) return;

        FontRenderer fr = Minecraft.getInstance().fontRenderer;

        player.getCapability(PlayerVoidProvider.CAPABILITY_PLAYER_VOID).ifPresent(v -> {
            fr.drawString(
                    "Current Void Stored: " + v.getVoidStored() + "/" + v.getMaxVoidStored(),
                    0, 0, 0xffffff);
        });
    }

}
