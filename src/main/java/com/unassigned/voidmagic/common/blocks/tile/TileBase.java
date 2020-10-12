package com.unassigned.voidmagic.common.blocks.tile;

import com.unassigned.voidmagic.common.util.VanillaPacketDispatcher;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import javax.annotation.Nonnull;

public class TileBase extends TileEntity implements ITickableTileEntity {

    protected int ticksElapsed;

    public TileBase(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public void tick() {
        update();
    }

    public void update() {
        ticksElapsed++;
    }

    @Nonnull
    @Override
    public CompoundNBT write(CompoundNBT compound) {
        CompoundNBT ret = super.write(compound);
        writePacketNBT(ret);
        return ret;
    }

    @Nonnull
    @Override
    public final CompoundNBT getUpdateTag() {
        return write(new CompoundNBT());
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        readPacketNBT(compound);
    }

    public void writePacketNBT(CompoundNBT compound) {}

    public void readPacketNBT(CompoundNBT compound) {}

    @Override
    public final SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT tag = new CompoundNBT();
        writePacketNBT(tag);
        return new SUpdateTileEntityPacket(pos, -1, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet) {
        super.onDataPacket(net, packet);
        readPacketNBT(packet.getNbtCompound());
    }

    //call when te should sync with client
    protected void sendData(){
        VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);
    }
}
