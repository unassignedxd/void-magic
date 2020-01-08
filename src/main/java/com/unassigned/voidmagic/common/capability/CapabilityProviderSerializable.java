package com.unassigned.voidmagic.common.capability;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/*
 * general implementation of ICapabilityProvider & INBTSerializable
 * should be used with caps that need to save data.
 */
public class CapabilityProviderSerializable<HANDLER> implements ICapabilityProvider, INBTSerializable<INBT> {

    protected final Capability<HANDLER> capability;
    protected final HANDLER instance;
    protected final Direction dir;

    protected final LazyOptional<HANDLER> lazyOptional;

    public CapabilityProviderSerializable(Capability<HANDLER> capability, Direction dir, HANDLER instance) {
        this.capability = capability;
        this.instance = instance;
        this.dir = dir;

        if(instance != null)
            lazyOptional = LazyOptional.of(()->this.instance);
        else
            lazyOptional = LazyOptional.empty();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction side) {
        return getCapability().orEmpty(capability, lazyOptional);
    }

    public Capability<HANDLER> getCapability() {
        return capability;
    }

    public Direction getDir() {
        return dir;
    }

    public HANDLER getInstance() {
        return instance;
    }

    @Override
    public INBT serializeNBT() {
        if(getInstance() == null) return null;

        return getCapability().writeNBT(getInstance(), getDir());
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        if(getInstance() == null) return;

        getCapability().readNBT(getInstance(), getDir(), nbt);
    }
}
