package com.unassigned.voidmagic.common.tileentity;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import javax.annotation.Nullable;

public class TileEntityBase extends TileEntity implements ITickableTileEntity {

    protected int ticksElapsed;

    public TileEntityBase(TileEntityType<?> type) {
        super(type);
    }

    @Override
    public void tick() {
        update();
    }

    public void update() {
        ticksElapsed++;
    }

    @Override
    public void read(CompoundNBT compound) {
        this.readNBT(compound, NBTSaveType.SAVE_TILE);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        this.writeNBT(compound, NBTSaveType.SAVE_TILE);
        return compound;
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT compoundNBT = new CompoundNBT();
        this.writeNBT(compoundNBT, NBTSaveType.SYNC);
        return new SUpdateTileEntityPacket(this.pos, -1, compoundNBT);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        this.readNBT(pkt.getNbtCompound(), NBTSaveType.SYNC);
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT compound = new CompoundNBT();
        this.writeNBT(compound, NBTSaveType.SYNC);
        return compound;
    }

    @Override
    public void handleUpdateTag(CompoundNBT tag) {
        this.readNBT(tag, NBTSaveType.SYNC);
    }

    public void writeNBT(CompoundNBT compoundNBT, NBTSaveType saveType) {
        if(saveType != NBTSaveType.SAVE_BLOCK) super.write(compoundNBT);

        if(saveType == NBTSaveType.SAVE_TILE) {
            compoundNBT.putInt("TicksElapsed", this.ticksElapsed);
        }
    }

    public void readNBT(CompoundNBT compoundNBT, NBTSaveType saveType) {
        if(saveType != NBTSaveType.SAVE_BLOCK) super.read(compoundNBT);

        if(saveType == NBTSaveType.SAVE_TILE) {
            this.ticksElapsed = compoundNBT.getInt("TicksElapsed");
        }
    }

    protected enum NBTSaveType { //used to cut down on useless nbt saving
        SYNC,
        SAVE_TILE,
        SAVE_BLOCK
    }
}
