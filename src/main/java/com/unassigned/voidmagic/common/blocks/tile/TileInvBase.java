package com.unassigned.voidmagic.common.blocks.tile;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public abstract class TileInvBase extends TileBase {

    protected CustomItemStackHandler itemHandler = createItemHandler();
    private final LazyOptional<IItemHandler> capItemHandler = LazyOptional.of(()->itemHandler);

    public TileInvBase(TileEntityType<?> type) {
        super(type);
    }

    @Override
    public void readPacketNBT(CompoundNBT compound) {
        itemHandler = createItemHandler();
        itemHandler.deserializeNBT(compound);
    }

    @Override
    public void writePacketNBT(CompoundNBT compound) {
        compound.merge(itemHandler.serializeNBT());
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(cap, capItemHandler);
    }

    protected CustomItemStackHandler createItemHandler() {
        return new CustomItemStackHandler(this, false);
    }

    public IItemHandlerModifiable getItemHandler() {
        return itemHandler;
    }

    public abstract int getInventorySize();

    protected static class CustomItemStackHandler extends ItemStackHandler {
        private final TileInvBase tile;
        private final boolean shouldSendData;

        public CustomItemStackHandler(TileInvBase tile, boolean shouldSendData) {
            super(tile.getInventorySize());
            this.tile = tile;
            this.shouldSendData = shouldSendData;
        }

        @Override
        protected void onContentsChanged(int slot) {
            tile.markDirty();
            if(shouldSendData) tile.sendData();
        }
    }

}
