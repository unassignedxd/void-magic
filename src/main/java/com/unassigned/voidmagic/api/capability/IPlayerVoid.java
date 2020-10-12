package com.unassigned.voidmagic.api.capability;

import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;

public interface IPlayerVoid {

    PlayerEntity getAttachedPlayer();

    ArrayList<IVoidSkill> getVoidSkills();
    void setVoidSkills(ArrayList<IVoidSkill> set);
    void addSkill(IVoidSkill skill);
    void removeSkill(IVoidSkill skill);

    int getVoidStored();
    void setVoidStored(int set, boolean sendPacket);
    void addVoid(int toAdd);
    void removeVoid(int removeVoid);

    int getMaxVoidStored();

    void onVoidChanged();
}
