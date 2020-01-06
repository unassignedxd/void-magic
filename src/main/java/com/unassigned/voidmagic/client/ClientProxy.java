package com.unassigned.voidmagic.client;

import com.unassigned.voidmagic.IProxy;
import com.unassigned.voidmagic.client.events.ClientEvents;
import com.unassigned.voidmagic.client.render.tile.TileVoidStandRenderer;
import com.unassigned.voidmagic.client.screens.CoalGeneratorScreen;
import com.unassigned.voidmagic.client.screens.TestBlockScreen;
import com.unassigned.voidmagic.common.blocks.ModBlocks;
import com.unassigned.voidmagic.common.tileentity.TileVoidInfuser;
import com.unassigned.voidmagic.common.tileentity.TileVoidStand;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy implements IProxy {

    @Override
    public void init() {
        ScreenManager.registerFactory(ModBlocks.testBlockContainer, TestBlockScreen::new);
        ScreenManager.registerFactory(ModBlocks.coalGeneratorContainer, CoalGeneratorScreen::new);
        ClientRegistry.bindTileEntitySpecialRenderer(TileVoidStand.class, new TileVoidStandRenderer());

        MinecraftForge.EVENT_BUS.register(new ClientEvents());
    }

    @Override
    public World getClientWorld() {
        return Minecraft.getInstance().world;
    }

    @Override
    public PlayerEntity getClientPlayer() {
        return Minecraft.getInstance().player;
    }
}
