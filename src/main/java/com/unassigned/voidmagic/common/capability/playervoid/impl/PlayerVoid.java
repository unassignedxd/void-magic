package com.unassigned.voidmagic.common.capability.playervoid.impl;

import com.unassigned.voidmagic.VoidMagic;
import com.unassigned.voidmagic.common.capability.playervoid.IVoidSkill;
import com.unassigned.voidmagic.common.capability.playervoid.IPlayerVoid;
import com.unassigned.voidmagic.common.network.messages.MessagePlayerVoid;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.network.PacketDistributor;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public class PlayerVoid implements IPlayerVoid {

    protected final PlayerEntity player;

    protected ArrayList<IVoidSkill> voidSkills;
    protected int voidStored;

    public PlayerVoid(PlayerEntity player) {
        this.player = player;
        this.voidStored = 0;
        this.voidSkills = new ArrayList<>();
    }

    @Override
    public PlayerEntity getAttachedPlayer() {
        return this.player;
    }

    @Override
    public ArrayList<IVoidSkill> getVoidSkills() {
        return this.voidSkills;
    }

    @Override
    public void setVoidSkills(ArrayList<IVoidSkill> set) {
        this.voidSkills = set;
    }

    @Override
    public void addSkill(@Nonnull IVoidSkill skill) {
        if(!this.voidSkills.contains(skill)) this.voidSkills.add(skill);
    }

    @Override
    public void removeSkill(IVoidSkill skill) {
        this.voidSkills.remove(skill);
    }

    @Override
    public int getVoidStored() {
        return this.voidStored;
    }

    @Override
    public void setVoidStored(int set, boolean sendPacket) {
        this.voidStored = set;
        if(sendPacket) onVoidChanged();
    }

    @Override
    public void addVoid(int toAdd) {
        if(this.voidStored + toAdd > getMaxVoidStored()) this.voidStored = getMaxVoidStored();
            else this.voidStored += toAdd;
        onVoidChanged();
    }

    @Override
    public void removeVoid(int removeVoid) {
        if(this.voidStored - removeVoid < 0){ this.voidStored = 0; }
            else { this.voidStored -= removeVoid; }
        onVoidChanged();
    }

    @Override
    public int getMaxVoidStored() {
        return 100000;
    }

    public void onVoidChanged() {
        PlayerEntity player = getAttachedPlayer();
        if(player != null && player.getEntityWorld().isRemote) return;

        VoidMagic.network.send(PacketDistributor.PLAYER.with(()->(ServerPlayerEntity)player), new MessagePlayerVoid(getVoidStored()));
    }

}
