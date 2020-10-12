package com.unassigned.voidmagic.common.blocks.tile;

import com.unassigned.voidmagic.common.util.ModRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

import javax.annotation.Nonnull;

public class TileVoidStand extends TileInvBase {

    public TileVoidStand() {
        super(ModRegistration.VOIDSTAND_TILE.get());
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public int getInventorySize() {
        return 1;
    }

    @Override
    protected CustomItemStackHandler createItemHandler() {
        return new CustomItemStackHandler(this, true) {
            @Override
            protected int getStackLimit(int slot, @Nonnull ItemStack stack) {
                return 1;
            }
        };
    }

    @Override
    public void writePacketNBT(CompoundNBT compound) {
        super.writePacketNBT(compound);
    }

    @Override
    public void readPacketNBT(CompoundNBT compound) {
        super.readPacketNBT(compound);
    }
}
