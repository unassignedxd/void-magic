package com.unassigned.voidmagic.common.tileentity;

import com.unassigned.voidmagic.client.recipe.VoidInfusionRecipe;
import com.unassigned.voidmagic.common.capability.playervoid.CapabilityPlayerVoid;
import com.unassigned.voidmagic.common.items.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.unassigned.voidmagic.common.blocks.ModBlocks.voidInfuserTile;
/*
    Player must be nearby holding void stone -> ability to drawn void from player to infuser
    This stone can be soul bound to the player and placed on a stand nearby to automate the process

    Once recipe is placed on the 4 bordering stands, name tag displays about the infuser to display amount of void needed for the process
    - at this time the void will be drawn.

    Once the void is drawn the infusion will start. This infusion process will be used to either create the void upgradeable gear or to empower the gear.
 */
public class TileVoidInfuser extends TileEntity implements ITickableTileEntity {

    public LazyOptional<IItemHandler> itemHandler = LazyOptional.of(this::getItemHandler);
    public int storedVoid = 0;
    public VoidInfusionRecipe curRecipe;

    private int ticksElapsed;

    private static final int TESTREQ = 25000;

    public TileVoidInfuser() {
        super(voidInfuserTile);
    }

    @Override
    public void tick() {
        ticksElapsed++;
        if(!world.isRemote) {
            if (properConfiguration()) {
                //check for recipe here too --- if not enough void stored run the following
                PlayerEntity player = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ());
                if (player != null) {
                    if ((player.getHeldItem(Hand.MAIN_HAND).getItem() == ModItems.transmuteStone ||
                            player.getHeldItem(Hand.OFF_HAND).getItem() == ModItems.transmuteStone) && getDistanceSq(player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ()) <= 8.0D) {
                        if (storedVoid < TESTREQ) {
                            player.getCapability(CapabilityPlayerVoid.CAPABILITY_PLAYER_VOID).ifPresent(v -> {
                                if (v.getVoidStored() > 0) {
                                    v.removeVoid(1); //void per tick based on craft time and sucking amount
                                    storedVoid++;
                                    if (ticksElapsed % 5 == 0)
                                        ((ServerWorld) world).spawnParticle(ParticleTypes.END_ROD, pos.getX(), pos.getY(), pos.getZ(), 10, 0, 1f, 0, 1D);
                                }
                            });
                        }
                    }
                }
            }
        }
    }

    private boolean properConfiguration() {
        return true; // check for display stands
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return itemHandler.cast();

        return super.getCapability(cap, side);
    }

    private IItemHandler getItemHandler() {
        return new ItemStackHandler(1){
            @Override
            protected int getStackLimit(int slot, @Nonnull ItemStack stack) {
                return 1;
            }
        };
    }

    @Override
    public void read(CompoundNBT compound) {
        itemHandler.ifPresent(h -> ((INBTSerializable<CompoundNBT>)h).deserializeNBT(compound.getCompound("inv")));
        super.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        itemHandler.ifPresent(h -> {
            compound.put("inv", ((INBTSerializable<CompoundNBT>)h).serializeNBT());
        });
        return super.write(compound);
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT compoundNBT = new CompoundNBT();
        this.write(compoundNBT);
        return new SUpdateTileEntityPacket(this.pos, -1, compoundNBT);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        this.read(pkt.getNbtCompound());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT compoundNBT = new CompoundNBT();
        this.write(compoundNBT);
        return compoundNBT;
    }

    @Override
    public void handleUpdateTag(CompoundNBT tag) {
        this.read(tag);
    }
}
