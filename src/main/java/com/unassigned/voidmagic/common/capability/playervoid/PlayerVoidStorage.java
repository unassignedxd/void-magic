package com.unassigned.voidmagic.common.capability.playervoid;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class PlayerVoidStorage implements Capability.IStorage<IPlayerVoid> {
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
                    instance.setVoidStored(compound.getInt("VoidStored"));
                }
                if(compound.contains("VoidSkills")){
                    // -- TODO -- \\
                }
            }
        }
    }
}
