package com.unassigned.voidmagic.common.capability.playervoid;

import com.unassigned.voidmagic.common.capability.playervoid.impl.PlayerVoid;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PlayerVoidProvider implements ICapabilitySerializable<CompoundNBT> {

    @CapabilityInject(IPlayerVoid.class)
    public static final Capability<IPlayerVoid> CAPABILITY_PLAYER_VOID = null;

    private final LazyOptional<IPlayerVoid> instance;

    public PlayerVoidProvider(PlayerEntity player) { instance = LazyOptional.of(() -> new PlayerVoid(player)); }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap != CAPABILITY_PLAYER_VOID) return LazyOptional.empty();
        return this.instance.cast();
    }

    @Override
    public CompoundNBT serializeNBT() {
        return (CompoundNBT)(CAPABILITY_PLAYER_VOID.getStorage()
                .writeNBT(CAPABILITY_PLAYER_VOID,
                        instance.orElseThrow(()->new IllegalArgumentException("Invalid LazyOptional!")),
                        null));
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        CAPABILITY_PLAYER_VOID.getStorage().readNBT(CAPABILITY_PLAYER_VOID,
                instance.orElseThrow(()->new IllegalArgumentException("Invalid LazyOptional!")),
                null, nbt);
    }

    public static IPlayerVoid getPlayerVoid(PlayerEntity playerEntity) {
        return playerEntity.getCapability(CAPABILITY_PLAYER_VOID, null).orElseThrow(() -> new IllegalArgumentException("Invalid Target!"));
    }
}
