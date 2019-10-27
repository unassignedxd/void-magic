package com.unassigned.voidmagic.client;

import com.unassigned.voidmagic.IProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

public class ClientProxy implements IProxy {

    @Override
    public World getClientWorld() {
        return Minecraft.getInstance().world;
    }
}
