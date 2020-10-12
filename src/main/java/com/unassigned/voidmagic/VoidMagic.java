package com.unassigned.voidmagic;

import com.unassigned.voidmagic.client.ClientSetup;
import com.unassigned.voidmagic.common.util.ModRegistration;
import com.unassigned.voidmagic.common.util.ModSetup;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("voidmagic")
public class VoidMagic {

    public static final String MODID = "voidmagic";
    public static final Logger LOGGER = LogManager.getLogger();

    public VoidMagic() {
        ModRegistration.init();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModSetup::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);

        LOGGER.info("Void Magic Initialization Complete!");
    }

}
