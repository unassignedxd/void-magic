package com.unassigned.voidmagic.common.util;

import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerChunkProvider;
import net.minecraft.world.server.ServerWorld;

/*
    Class courtesy of Vazkii's Botania
 */
public final class VanillaPacketDispatcher {

    public static void dispatchTEToNearbyPlayers(TileEntity tile) {
        SUpdateTileEntityPacket packet = tile.getUpdatePacket();
        BlockPos pos = tile.getPos();

        if(packet != null && tile.getWorld() instanceof ServerWorld) {
            ((ServerChunkProvider)tile.getWorld().getChunkProvider()).chunkManager
                    .getTrackingPlayers(new ChunkPos(pos), false)
                    .forEach(e -> { e.connection.sendPacket(packet); });
        }

    }

    public static void dispatchTEToNearbyPlayers(World world, BlockPos pos) {
        TileEntity tile = world.getTileEntity(pos);
        if(tile != null)
            dispatchTEToNearbyPlayers(tile);
    }

}
