package com.unassigned.voidmagic.common.events;

import com.unassigned.voidmagic.common.capability.CapabilityProviderSerializable;
import com.unassigned.voidmagic.common.capability.playervoid.CapabilityPlayerVoid;
import com.unassigned.voidmagic.common.capability.playervoid.IPlayerVoid;
import com.unassigned.voidmagic.common.capability.playervoid.impl.PlayerVoid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "voidmagic", bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonEvents {

    @SubscribeEvent
    public void onCapabilityAttachPlayer(final AttachCapabilitiesEvent<Entity> event) {
        Entity e = event.getObject();
        if(e != null) {
            if(e instanceof PlayerEntity) {
                final IPlayerVoid pv = new PlayerVoid((PlayerEntity)e);
                event.addCapability(CapabilityPlayerVoid.RESLOC, new CapabilityProviderSerializable<>(CapabilityPlayerVoid.CAPABILITY_PLAYER_VOID, null, pv));
            }
        }
    }

    @SubscribeEvent
    public void onPlayerCloned(PlayerEvent.Clone event) { //TODO -> void fade on death
        if(event.isWasDeath()) {
        }
    }

}

