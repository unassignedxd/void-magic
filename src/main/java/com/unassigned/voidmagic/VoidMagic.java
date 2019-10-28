package com.unassigned.voidmagic;

import com.unassigned.voidmagic.client.ClientProxy;
import com.unassigned.voidmagic.common.ServerProxy;
import com.unassigned.voidmagic.common.util.ModSetup;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("voidmagic")
public class VoidMagic {

    public static final String MODID = "voidmagic";
    public static IProxy proxy = DistExecutor.runForDist(()->()-> new ClientProxy(), ()->()-> new ServerProxy());
    public static ModSetup setup = new ModSetup();

    public static final Logger LOGGER = LogManager.getLogger();

    public VoidMagic() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        setup.init();
        proxy.init();
    }
}
