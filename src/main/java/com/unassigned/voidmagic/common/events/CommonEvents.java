package com.unassigned.voidmagic.common.events;

import com.unassigned.voidmagic.common.capability.CapabilityProviderSerializable;
import com.unassigned.voidmagic.common.capability.playervoid.CapabilityPlayerVoid;
import com.unassigned.voidmagic.common.capability.playervoid.PlayerVoid;
import com.unassigned.voidmagic.common.capability.playervoid.impl.IPlayerVoid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "voidmagic", bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonEvents {

    @SubscribeEvent
    public void onCapabilityAttachPlayer(final AttachCapabilitiesEvent<Entity> event) {
        Entity e = event.getObject();
        if(e != null) {
            if(e instanceof PlayerEntity) {
                IPlayerVoid playerVoid = new PlayerVoid((PlayerEntity)e);
                event.addCapability(CapabilityPlayerVoid.CAP_ID, new CapabilityProviderSerializable<>(CapabilityPlayerVoid.CAPABILITY_PLAYER_VOID, playerVoid, CapabilityPlayerVoid.DEFAULT_DIR));
            }
        }
    }

}

