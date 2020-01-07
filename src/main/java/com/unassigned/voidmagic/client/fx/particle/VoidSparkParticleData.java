package com.unassigned.voidmagic.client.fx.particle;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.unassigned.voidmagic.client.fx.ModParticles;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;

import java.util.Locale;

public class VoidSparkParticleData implements IParticleData {
    public final float size;
    public final float endX, endY, endZ;
    public final float r, g, b;
    public final float maxAgeX;
    public final boolean depthTest;
    public final boolean noClip;

    public VoidSparkParticleData(float size, float endX, float endY, float endZ, float r, float g, float b, float maxAgeX, boolean depthTest, boolean noClip) {
        this.size = size;
        this.endX = endX;
        this.endY = endY;
        this.endZ = endZ;
        this.r = r;
        this.g = g;
        this.b = b;
        this.maxAgeX = maxAgeX;
        this.depthTest = depthTest;
        this.noClip = noClip;
    }

    @Override
    public ParticleType<?> getType() {
        return ModParticles.VOID_SPARK;
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeFloat(size);
        buffer.writeFloat(endX);
        buffer.writeFloat(endY);
        buffer.writeFloat(endZ);
        buffer.writeFloat(r);
        buffer.writeFloat(g);
        buffer.writeFloat(b);
        buffer.writeFloat(maxAgeX);
        buffer.writeBoolean(depthTest);
        buffer.writeBoolean(noClip);
    }

    @Override
    public String getParameters() {
        return String.format(Locale.ROOT, "%s %.2f %.2f %.2f %.2f %.2f %s",
                this.getType().getRegistryName(), this.size, this.endX, this.endY, this.endZ, this.r, this.g, this.b, this.maxAgeX, this.depthTest);
    }

    public static final IDeserializer<VoidSparkParticleData> DESERIALIZER = new IDeserializer<VoidSparkParticleData>() {
        @Override
        public VoidSparkParticleData deserialize(ParticleType<VoidSparkParticleData> particleTypeIn, StringReader reader) throws CommandSyntaxException {
            reader.expect(' ');
            float size = reader.readFloat();
            reader.expect(' ');
            float endX = reader.readFloat();
            reader.expect(' ');
            float endY = reader.readFloat();
            reader.expect(' ');
            float endZ = reader.readFloat();
            reader.expect(' ');
            float r = reader.readFloat();
            reader.expect(' ');
            float g = reader.readFloat();
            reader.expect(' ');
            float b = reader.readFloat();
            reader.expect(' ');
            float max = reader.readFloat();
            boolean depth = true;
            if (reader.canRead()) {
                reader.expect(' ');
                depth = reader.readBoolean();
            }
            boolean noClip = false;
            if (reader.canRead()) {
                reader.expect(' ');
                depth = reader.readBoolean();
            }
            return new VoidSparkParticleData(size, endX, endY, endZ, r, g, b, max, depth, noClip);
        }

        @Override
        public VoidSparkParticleData read(ParticleType<VoidSparkParticleData> particleTypeIn, PacketBuffer buffer) {
            return new VoidSparkParticleData(
                    buffer.readFloat(),
                    buffer.readFloat(), buffer.readFloat(), buffer.readFloat(),
                    buffer.readFloat(), buffer.readFloat(), buffer.readFloat(),
                    buffer.readFloat(),
                    buffer.readBoolean(),
                    buffer.readBoolean()
            );
        }
    };
}
