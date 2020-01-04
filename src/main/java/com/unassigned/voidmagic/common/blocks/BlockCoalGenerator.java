package com.unassigned.voidmagic.common.blocks;

import com.unassigned.voidmagic.common.tileentity.TileCoalGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class BlockCoalGenerator extends Block {

    public BlockCoalGenerator() {
        super(Properties.create(Material.IRON)
                .sound(SoundType.STONE)
                .hardnessAndResistance(2.0F));
        setRegistryName("coalgenerator");
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileCoalGenerator();
    }

    @Override
    public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity entity, Hand hand, BlockRayTraceResult ray) {
        if(!world.isRemote) {
            TileEntity te = world.getTileEntity(pos);
            if(te != null && te instanceof INamedContainerProvider) {
                NetworkHooks.openGui((ServerPlayerEntity)entity, (INamedContainerProvider) te, te.getPos());
                return true;
            }
        }
        return super.onBlockActivated(state, world, pos, entity, hand, ray);
    }
}
