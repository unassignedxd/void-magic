package com.unassigned.voidmagic;

import com.unassigned.voidmagic.client.ClientProxy;
import com.unassigned.voidmagic.common.CommonProxy;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("voidmagic")
public class VoidMagic {

    public static IProxy proxy = DistExecutor.runForDist(()->()-> new ClientProxy(), ()->()-> new CommonProxy());

    public static final Logger LOGGER = LogManager.getLogger();

    public VoidMagic() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
    }
}
