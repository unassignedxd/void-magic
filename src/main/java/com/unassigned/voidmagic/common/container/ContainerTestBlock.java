package com.unassigned.voidmagic.common.container;

import static com.unassigned.voidmagic.common.blocks.ModBlocks.testBlockContainer;

import com.unassigned.voidmagic.common.blocks.ModBlocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class ContainerTestBlock extends Container {

    private TileEntity te;
    private PlayerEntity player;
    private IItemHandler playerInventory;

    public ContainerTestBlock(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity entity) {
        super(testBlockContainer, windowId);
        te = world.getTileEntity(pos);
        te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);
        this.player = entity;
        this.playerInventory = new InvWrapper(playerInventory);

        te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h ->{
            addSlot(new SlotItemHandler(h, 0, 64, 24));
        });

        createPlayerInventorySlots(10, 70);
    }

    /**
     *  adds slots (amt) in a straight line across a change in X (dx) from (x,y)
     */
    public int addSlotRange(IItemHandler handler, int index, int x, int y, int amt, int dx) {
        for(int i = 0; i < amt; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    /**
     *  creates a box of slots (horzAmt x vertAmt) spaced depending on dx,dy at (x,y).
     */
    public int addSlotBox(IItemHandler handler, int index, int x, int y, int horzAmt, int dx, int vertAmt, int dy){
        for(int j = 0; j < vertAmt; j++) {
            index = addSlotRange(handler, index, x, y, horzAmt, dx);
            y += dy;
        }
        return index;
    }

    private void createPlayerInventorySlots(int leftCol, int topRow) {
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);
        topRow+=58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }

    @Override
    public boolean canInteractWith(PlayerEntity entity) {
        return isWithinUsableDistance(IWorldPosCallable.of(te.getWorld(), te.getPos()), entity, ModBlocks.testBlock);
    }
}
