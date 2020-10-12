package com.unassigned.voidmagic.common.blocks;

import com.unassigned.voidmagic.common.blocks.tile.TileVoidStand;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nullable;

public class BlockVoidStand extends Block {

    public BlockVoidStand() {
        super(Properties.create(Material.IRON).hardnessAndResistance(2.5F));
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileVoidStand();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockRayTraceResult ray) {
        TileEntity te = world.getTileEntity(blockPos);
        ItemStack item = player.getHeldItem(hand);
        if(te != null) {
            if(te instanceof TileVoidStand){
                if(item.isEmpty() && player.isSneaking()) {
                    te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(i -> {
                        ItemStack holding = i.getStackInSlot(0);
                        if(!holding.isEmpty()) {
                            if(!world.isRemote) player.addItemStackToInventory(i.extractItem(0, 1, false));
                            if(world.isRemote) player.playSound(SoundEvents.ENTITY_ITEM_FRAME_ADD_ITEM, .5f, 1f);
                        }
                    });
                    return ActionResultType.SUCCESS;
                } else if(!item.isEmpty()){
                    te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(i -> {
                        ItemStack holding = i.getStackInSlot(0);
                        if(holding.isEmpty()) {
                            if(!world.isRemote) {
                                i.insertItem(0, item, false);
                                item.shrink(1);
                            }
                            if(world.isRemote) player.playSound(SoundEvents.ENTITY_ITEM_FRAME_ADD_ITEM, .5f, 1f);
                        }
                    });
                    return ActionResultType.SUCCESS;
                }
            }
        }
        return ActionResultType.FAIL;
    }
}
