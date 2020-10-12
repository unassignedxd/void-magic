package com.unassigned.voidmagic.common.blocks.tile;

import com.unassigned.voidmagic.api.recipe.RecipeVoidInfusion;
import com.unassigned.voidmagic.client.fx.particle.VoidSparkParticleData;
import com.unassigned.voidmagic.common.util.ModRegistration;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

/*
    Player must be nearby holding void stone -> ability to drawn void from player to infuser
    This stone can be soul bound to the player and placed on a stand nearby to automate the process

    Once recipe is placed on the 4 bordering stands, name tag displays about the infuser to display amount of void needed for the process
    - at this time the void will be drawn.

    Once the void is drawn the infusion will start. This infusion process will be used to either create the void upgradeable gear or to empower the gear.
 */
public class TileVoidInfuser extends TileInvBase {

    public int storedVoid = 0;
    public RecipeVoidInfusion curRecipe;

    private static final int TESTREQ = 25000;

    public TileVoidInfuser() {
        super(ModRegistration.VOIDINFUSER_TILE.get());
    }

    @Override
    public void update() {
        super.update();
        PlayerEntity player = world.getClosestPlayer(this.pos.getX(), this.pos.getY(), this.pos.getZ());
        if(player != null){
            VoidSparkParticleData data = new VoidSparkParticleData((float)Math.random(), (float)this.pos.getX()+.5F, (float)this.pos.getY()+1.5F, (float)this.pos.getZ()+.5F,
                    (float)Math.random(),(float)Math.random(), (float)Math.random(), 100, false, true);
            world.addParticle(data, player.getPosX(), player.getPosY(), player.getPosZ(), 0,0,0);
        }
    }

    private boolean properConfiguration() {
        return true; // check for display stands
    }

    @Override
    public int getInventorySize() {
        return 1;
    }

    @Override
    protected CustomItemStackHandler createItemHandler() {
        return new CustomItemStackHandler(this, true){
            @Override
            protected int getStackLimit(int slot, @Nonnull ItemStack stack) {
                return 1;
            }
        };
    }
}
