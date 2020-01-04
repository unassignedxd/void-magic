package com.unassigned.voidmagic.common.tileentity;

import com.unassigned.voidmagic.common.container.ContainerCoalGenerator;
import com.unassigned.voidmagic.common.util.CustomEnergyStorage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.unassigned.voidmagic.common.blocks.ModBlocks.coalGeneratorTile;

public class TileCoalGenerator extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    private LazyOptional<IItemHandler> itemHandler = LazyOptional.of(this::createItemHandler);
    private LazyOptional<IEnergyStorage> energy = LazyOptional.of(this::createEnergyHandler);

    private int burnTime = 0;
    private static final int ENERGY_PER_TICK = 20; //20 FE/T

    public TileCoalGenerator() {
        super(coalGeneratorTile);
    }

    @Override
    public void tick() {
        if(burnTime == 0) {
            itemHandler.ifPresent(h -> {
                ItemStack item = h.getStackInSlot(0);
                if(item.getItem() == Items.COAL){
                    h.extractItem(0, 1, false);
                    burnTime = 100; //5 seconds
                }
            });
        }

        if(burnTime > 0) {
            energy.ifPresent(e -> e.receiveEnergy(ENERGY_PER_TICK, false));
            burnTime--;
        }

    }

    @Override
    public void read(CompoundNBT compound) {
        itemHandler.ifPresent(h -> ((INBTSerializable<CompoundNBT>)h).deserializeNBT(compound.getCompound("inv")));
        energy.ifPresent(e -> ((INBTSerializable<CompoundNBT>)e).deserializeNBT(compound.getCompound("energy")));
        super.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        itemHandler.ifPresent(h -> {
            compound.put("inv", ((INBTSerializable<CompoundNBT>)h).serializeNBT());
        });
        energy.ifPresent(e -> {
            compound.put("energy", ((INBTSerializable<CompoundNBT>)e).serializeNBT());
        });
        return super.write(compound);
    }

    @Nonnull
    private IEnergyStorage createEnergyHandler() {
        return new CustomEnergyStorage(100000);
    }

    @Nonnull
    private ItemStackHandler createItemHandler() {
        return new ItemStackHandler(1) { //override handler here v
            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return stack.isItemEqual(new ItemStack(Items.COAL));
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return itemHandler.cast();
        } else if(cap == CapabilityEnergy.ENERGY) {
            return energy.cast();
        }
        return super.getCapability(cap, side);
    }


    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent(getType().getRegistryName().getPath());
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new ContainerCoalGenerator(i, world, pos, playerInventory, playerEntity);
    }
}
