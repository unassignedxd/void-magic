package com.unassigned.voidmagic.common.capability.playervoid;

import com.unassigned.voidmagic.VoidMagic;
import com.unassigned.voidmagic.api.capability.IPlayerVoid;
import com.unassigned.voidmagic.api.capability.IVoidSkill;
import com.unassigned.voidmagic.common.capability.playervoid.impl.PlayerVoid;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nullable;

public class CapabilityPlayerVoid {
    @CapabilityInject(IPlayerVoid.class)
    public static final Capability<IPlayerVoid> CAPABILITY_PLAYER_VOID = null;

    public static final ResourceLocation RESLOC = new ResourceLocation(VoidMagic.MODID, "playervoid");

    public static void registerCap() {
        CapabilityManager.INSTANCE.register(IPlayerVoid.class, new Capability.IStorage<IPlayerVoid>() {
            @Nullable
            @Override
            public INBT writeNBT(Capability<IPlayerVoid> capability, IPlayerVoid instance, Direction side) {
                CompoundNBT compound = new CompoundNBT();
                if(capability != null && instance != null) {
                    if(instance.getAttachedPlayer() != null) {
                        compound.putInt("VoidStored", instance.getVoidStored());

                        CompoundNBT skillCompound = new CompoundNBT();
                        for(IVoidSkill skill : instance.getVoidSkills()) {
                            if(skill != null){ //fallback check
                                skillCompound.putInt("Skill#"+skill.skillID(), skill.skillID());
                            }
                        }
                        compound.put("VoidSkills", skillCompound);
                    }
                }
                return compound;
            }

            @Override
            public void readNBT(Capability<IPlayerVoid> capability, IPlayerVoid instance, Direction side, INBT nbt) {
                if(capability != null && instance != null && nbt != null) {
                    if(nbt instanceof CompoundNBT) {
                        CompoundNBT compound = (CompoundNBT)nbt;
                        if(compound.contains("VoidStored")) {
                            instance.setVoidStored(compound.getInt("VoidStored"), false);
                        }
                        if(compound.contains("VoidSkills")){
                            // -- TODO -- \\
                        }
                    }
                }
            }
        }, ()->new PlayerVoid(null));
    }

    public static LazyOptional<IPlayerVoid> getPlayerVoid(PlayerEntity player) {
        return player.getCapability(CAPABILITY_PLAYER_VOID);
    }


}
