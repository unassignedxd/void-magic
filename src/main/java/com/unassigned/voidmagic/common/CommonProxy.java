package com.unassigned.voidmagic.common;

import com.unassigned.voidmagic.IProxy;
import net.minecraft.world.World;

public class CommonProxy implements IProxy {

    @Override
    public World getClientWorld() {
        throw new IllegalStateException("Only run this on the client!");
    }
}
