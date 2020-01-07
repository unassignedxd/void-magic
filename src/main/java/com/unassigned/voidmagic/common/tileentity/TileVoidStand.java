package com.unassigned.voidmagic.common.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.unassigned.voidmagic.common.blocks.ModBlocks.voidStandTile;

public class TileVoidStand extends TileEntityBase {

    public final ItemStackHandler inv = new ItemStackHandler() {
        @Override
        public int getSlotLimit(int slot) {
            return 1;
        }

        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            getTileEntity().markDirty();
        }
    };
    public LazyOptional<IItemHandler> itemHandler = LazyOptional.of(()->inv);

    public TileVoidStand() {
        super(voidStandTile);
    }

    @Override
    public void update() {
        super.update();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return itemHandler.cast();
        return super.getCapability(cap, side);
    }

    @Override
    public void writeNBT(CompoundNBT compoundNBT, NBTSaveType saveType) {
        compoundNBT.put("Inventory", this.inv.serializeNBT());
        super.writeNBT(compoundNBT, saveType);
    }

    @Override
    public void readNBT(CompoundNBT compoundNBT, NBTSaveType saveType) {
        this.inv.deserializeNBT(compoundNBT.getCompound("Inventory"));
        super.readNBT(compoundNBT, saveType);
    }
}
