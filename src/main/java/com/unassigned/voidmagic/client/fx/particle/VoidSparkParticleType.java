package com.unassigned.voidmagic.client.fx.particle;

import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.particles.ParticleType;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class VoidSparkParticleType extends ParticleType<VoidSparkParticleData> {

    public VoidSparkParticleType() {
        super(false, VoidSparkParticleData.DESERIALIZER);
    }

    public static class Factory implements IParticleFactory<VoidSparkParticleData> {
        @Nullable
        @Override
        public Particle makeParticle(VoidSparkParticleData data, World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new FXVoidSpark(world, x, y, z, data.endX, data.endY, data.endZ, data.size, data.r, data.g, data.b, data.maxAgeX, data.noClip);
        }
    }

}
