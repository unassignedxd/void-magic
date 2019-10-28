package com.unassigned.voidmagic.common.blocks;

import com.unassigned.voidmagic.common.tileentity.TileTestBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class TestBlock extends Block {

    public TestBlock() {
        super(Properties.create(Material.IRON)
                .sound(SoundType.METAL)
                .hardnessAndResistance(2.0F)
                .lightValue(14));
        setRegistryName("testblock");
    }

    @Override
    public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity entity, Hand hand, BlockRayTraceResult ray) {
        if(!world.isRemote) {
            TileEntity te = world.getTileEntity(pos);
            if(te != null && te instanceof INamedContainerProvider) {
                NetworkHooks.openGui((ServerPlayerEntity)entity, (INamedContainerProvider) te, te.getPos());
            }
        }
        return super.onBlockActivated(state, world, pos, entity, hand, ray);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileTestBlock();
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
        if(entity != null)
            world.setBlockState(pos, state.with(BlockStateProperties.FACING, getFacingFromEntity(pos, entity)), 2); // set the property on block place
    }

    private static Direction getFacingFromEntity(BlockPos pos, LivingEntity entity) {
        return Direction.getFacingFromVector((float)(entity.posX - pos.getX()), (float)(entity.posY - pos.getY()), (float)(entity.posZ - pos.getZ()));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.FACING);
    }
}
