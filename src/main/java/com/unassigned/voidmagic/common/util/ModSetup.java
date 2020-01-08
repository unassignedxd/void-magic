package com.unassigned.voidmagic.common.util;

import com.unassigned.voidmagic.common.blocks.ModBlocks;
import com.unassigned.voidmagic.common.capability.playervoid.CapabilityPlayerVoid;
import com.unassigned.voidmagic.common.capability.playervoid.IPlayerVoid;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class ModSetup {

    public ItemGroup itemGroup = new ItemGroup("voidmagic") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.testBlock);
        }
    };

    public void init() {
        CapabilityPlayerVoid.registerCap();
    }
}
