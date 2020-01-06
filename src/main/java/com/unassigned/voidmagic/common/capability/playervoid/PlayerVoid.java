package com.unassigned.voidmagic.common.capability.playervoid;

import com.unassigned.voidmagic.VoidMagic;
import com.unassigned.voidmagic.common.capability.playervoid.impl.IPlayerVoid;
import com.unassigned.voidmagic.common.capability.playervoid.skills.IPlayerSkill;
import com.unassigned.voidmagic.network.messages.MessagePlayerVoid;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.network.PacketDistributor;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public class PlayerVoid implements IPlayerVoid {

    protected final PlayerEntity player;

    protected ArrayList<IPlayerSkill> playerSkills;
    protected int voidStored;

    public PlayerVoid(PlayerEntity player) {
        this.player = player;
        this.voidStored = 0;
        this.playerSkills = new ArrayList<>();
    }

    @Override
    public PlayerEntity getAttachedPlayer() {
        return this.player;
    }

    @Override
    public ArrayList<IPlayerSkill> getPlayerSkills() {
        return this.playerSkills;
    }

    @Override
    public void setPlayerSkills(ArrayList<IPlayerSkill> set) {
        this.playerSkills = set;
    }

    @Override
    public void addSkill(@Nonnull IPlayerSkill skill) {
        if(!this.playerSkills.contains(skill)) this.playerSkills.add(skill);
    }

    @Override
    public void removeSkill(IPlayerSkill skill) {
        this.playerSkills.remove(skill);
    }

    @Override
    public int getVoidStored() {
        return this.voidStored;
    }

    @Override
    public void setVoidStored(int set) {
        this.voidStored = set;
        if(!player.world.isRemote)
            VoidMagic.network.send(PacketDistributor.PLAYER.with(()-> (ServerPlayerEntity) player), new MessagePlayerVoid(this.voidStored));
    }

    @Override
    public void addVoid(int toAdd) {
        this.voidStored += toAdd;
        if(!player.world.isRemote)
            VoidMagic.network.send(PacketDistributor.PLAYER.with(()-> (ServerPlayerEntity) player), new MessagePlayerVoid(this.voidStored));
    }

    @Override
    public void removeVoid(int removeVoid) {
        if(this.voidStored - removeVoid < 0){ this.voidStored = 0; }
            else {this.voidStored -= removeVoid; }

        if(!player.world.isRemote)
            VoidMagic.network.send(PacketDistributor.PLAYER.with(()-> (ServerPlayerEntity) player), new MessagePlayerVoid(this.voidStored));
    }

    @Override
    public int getMaxVoidStored() {
        return 100000;
    }

}
