package com.unassigned.voidmagic.common.capability;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilityProviderSerializable<T> implements ICapabilitySerializable<INBT> {

    protected Capability<T> cap;
    protected T instance;
    protected Direction dir;

    public CapabilityProviderSerializable(Capability<T> capability, T instance, Direction dir) {
        this.cap = capability;
        this.instance = instance;
        this.dir = dir;
    }

    public Capability<T> getCap() {
        return cap;
    }

    public T getInstance() {
        return instance;
    }

    public Direction getDirection() {
        return dir;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capa, @Nullable Direction side) {
            return cap.orEmpty(capa, LazyOptional.of(this::getInstance));
    }

    @Override
    public INBT serializeNBT() {
        return getCap().writeNBT(getInstance(), getDirection());
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        getCap().readNBT(getInstance(), getDirection(), nbt);
    }
}
