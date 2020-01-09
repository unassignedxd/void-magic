package com.unassigned.voidmagic.common.tileentity;

import com.unassigned.voidmagic.client.fx.particle.VoidSparkParticleData;
import com.unassigned.voidmagic.client.recipe.VoidInfusionRecipe;
import com.unassigned.voidmagic.common.capability.playervoid.CapabilityPlayerVoid;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
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
public class TileVoidInfuser extends TileEntityBase {

    public final ItemStackHandler inv = new ItemStackHandler(1){
        @Override
        public int getSlotLimit(int slot) {
            return 1;
        }
    };
    public LazyOptional<IItemHandler> itemHandler = LazyOptional.of(()->this.inv);

    public int storedVoid = 0;
    public VoidInfusionRecipe curRecipe;

    private static final int TESTREQ = 25000;

    public TileVoidInfuser() {
        super(voidInfuserTile);
    }

    @Override
    public void update() {
        super.update();
        PlayerEntity player = world.getClosestPlayer(this.pos.getX(), this.pos.getY(), this.pos.getZ());
        if(player != null){
            VoidSparkParticleData data = new VoidSparkParticleData((float)Math.random(), (float)player.posX, (float)player.posY+1.5F, (float) player.posZ,
                    (float)Math.random(),(float)Math.random(), (float)Math.random(), 100, false, true);
            world.addParticle(data, this.pos.getX()+.5, this.pos.getY()+1.5, this.pos.getZ()+.5, 0,0,0);
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

    @Override
    public void writeNBT(CompoundNBT compoundNBT, NBTSaveType saveType) {
        if(saveType != NBTSaveType.SAVE_BLOCK) {
            compoundNBT.putInt("StoredVoid", this.storedVoid);
        }
        compoundNBT.put("Inventory", this.inv.serializeNBT());
        super.writeNBT(compoundNBT, saveType);
    }

    @Override
    public void readNBT(CompoundNBT compoundNBT, NBTSaveType saveType) {
        if(saveType != NBTSaveType.SAVE_BLOCK) {
            this.storedVoid = compoundNBT.getInt("StoredVoid");
        }
        this.inv.deserializeNBT(compoundNBT.getCompound("Inventory"));
        super.readNBT(compoundNBT, saveType);
    }
}
