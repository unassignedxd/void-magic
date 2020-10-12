package com.unassigned.voidmagic.common.util;

import com.unassigned.voidmagic.common.capability.playervoid.CapabilityPlayerVoid;
import com.unassigned.voidmagic.common.network.ModNetwork;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ModSetup {

    public static ItemGroup ITEM_GROUP = new ItemGroup("voidmagic") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModRegistration.TESTBLOCK.get());
        }
    };

    public static void init(final FMLCommonSetupEvent event) {
        ModNetwork.registerMessages();
        CapabilityPlayerVoid.registerCap();
    }
}
