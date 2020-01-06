package com.unassigned.voidmagic.common.capability.playervoid;

import com.unassigned.voidmagic.VoidMagic;
import com.unassigned.voidmagic.common.capability.playervoid.impl.IPlayerVoid;
import com.unassigned.voidmagic.common.capability.playervoid.skills.IPlayerSkill;
import net.minecraft.command.arguments.NBTCompoundTagArgument;
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

    public static final Direction DEFAULT_DIR = null;
    public static final ResourceLocation CAP_ID = new ResourceLocation(VoidMagic.MODID, "capability_player_void");

    public static void registerCap() {
        CapabilityManager.INSTANCE.register(IPlayerVoid.class, new Capability.IStorage<IPlayerVoid>() {
            @Nullable
            @Override
            public INBT writeNBT(Capability<IPlayerVoid> capability, IPlayerVoid instance, Direction side) {
                CompoundNBT nbt = new CompoundNBT();
                if(instance != null) {
                    nbt.putInt("VoidStored", instance.getVoidStored());

                    CompoundNBT skillNBT = new CompoundNBT();
                    for(IPlayerSkill skill : instance.getPlayerSkills()) {
                        skillNBT.putInt(skill.getDisplayName(), skill.skillID());
                    }
                    nbt.put("PlayerSkills", skillNBT);
                }
                return nbt;
            }

            @Override
            public void readNBT(Capability<IPlayerVoid> capability, IPlayerVoid instance, Direction side, INBT nbt) {
                if(nbt != null && instance != null) {
                    if(nbt instanceof CompoundNBT) {
                        CompoundNBT compound = (CompoundNBT)nbt;

                        //if(compound.contains("VoidStored"))
                           //instance.setVoidStored(compound.getInt("VoidStored"));

                        CompoundNBT skillNBT = (CompoundNBT)compound.get("PlayerSkills");
                        //setSkills
                    }
                }
            }
        }, ()-> new PlayerVoid(null));
    }

    public static LazyOptional<IPlayerVoid> getPlayerVoid(PlayerEntity playerEntity) {
        return playerEntity.getCapability(CAPABILITY_PLAYER_VOID, DEFAULT_DIR);
    }
}
