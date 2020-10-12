package com.unassigned.voidmagic.client;

import com.unassigned.voidmagic.client.screens.CoalGeneratorScreen;
import com.unassigned.voidmagic.client.screens.TestBlockScreen;
import com.unassigned.voidmagic.common.util.ModRegistration;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {

    public static void init(final FMLClientSetupEvent event) {
        ScreenManager.registerFactory(ModRegistration.TESTBLOCK_CONTAINER.get(), TestBlockScreen::new);         //todo name refactoring
        ScreenManager.registerFactory(ModRegistration.COALGENERATOR_CONTAINER.get(), CoalGeneratorScreen::new);
    }
}
