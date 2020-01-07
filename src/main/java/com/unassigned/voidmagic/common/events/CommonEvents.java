package com.unassigned.voidmagic.common.events;

import com.unassigned.voidmagic.VoidMagic;
import com.unassigned.voidmagic.common.capability.playervoid.PlayerVoidProvider;
import com.unassigned.voidmagic.common.capability.playervoid.IPlayerVoid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
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
                event.addCapability(new ResourceLocation(VoidMagic.MODID, "voidmagic"), new PlayerVoidProvider((PlayerEntity)e));
            }
        }
    }

    @SubscribeEvent
    public void onPlayerCloned(PlayerEvent.Clone event) { //TODO -> void fade on death
        if(event.isWasDeath()) {
            IPlayerVoid deadVoid = PlayerVoidProvider.getPlayerVoid(event.getOriginal());
            IPlayerVoid newVoid = PlayerVoidProvider.getPlayerVoid(event.getPlayer());
            newVoid.setVoidSkills(deadVoid.getVoidSkills());
            newVoid.setVoidStored(deadVoid.getVoidStored());
        }
    }

}

