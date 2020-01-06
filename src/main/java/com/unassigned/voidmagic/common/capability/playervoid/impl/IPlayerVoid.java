package com.unassigned.voidmagic.common.capability.playervoid.impl;

import com.unassigned.voidmagic.common.capability.playervoid.skills.IPlayerSkill;
import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;

public interface IPlayerVoid {

    PlayerEntity getAttachedPlayer();

    ArrayList<IPlayerSkill> getPlayerSkills();
    void setPlayerSkills(ArrayList<IPlayerSkill> set);
    void addSkill(IPlayerSkill skill);
    void removeSkill(IPlayerSkill skill);

    int getVoidStored();
    void setVoidStored(int set);
    void addVoid(int toAdd);
    void removeVoid(int removeVoid);

    int getMaxVoidStored();
}
