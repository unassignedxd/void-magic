package com.unassigned.voidmagic.client.fx;

import com.unassigned.voidmagic.client.fx.particle.VoidSparkParticleData;
import com.unassigned.voidmagic.client.fx.particle.VoidSparkParticleType;
import net.minecraft.client.Minecraft;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = "voidmagic", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModParticles {

    @ObjectHolder("voidmagic:voidspark")
    public static ParticleType<VoidSparkParticleData> VOID_SPARK;

    @SubscribeEvent
    public static void registerParticles(RegistryEvent.Register<ParticleType<?>> event) {
        event.getRegistry().register(new VoidSparkParticleType().setRegistryName(new ResourceLocation("voidmagic", "voidspark")));
    }

    @Mod.EventBusSubscriber(value = Dist.CLIENT, modid = "voidmagic", bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class FactoryHandler {

        @SubscribeEvent
        public static void registerFactories(ParticleFactoryRegisterEvent event) {
            Minecraft.getInstance().particles.registerFactory(ModParticles.VOID_SPARK, new VoidSparkParticleType.Factory());
        }
    }
}
