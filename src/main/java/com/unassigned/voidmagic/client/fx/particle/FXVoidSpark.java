package com.unassigned.voidmagic.client.fx.particle;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class FXVoidSpark extends Particle {

    private static final ResourceLocation vanPart = new ResourceLocation("textures/particle/particles.png");
    public static final ResourceLocation PARTICLES  = new ResourceLocation("voidmagic", "textures/fx/particles.png");

    protected float particleScale = (this.rand.nextFloat()*.75f + .75f) * 1.25f;
    private final int particle = 16;
    private final int multiplier;

    protected double endX, endY, endZ;

    public FXVoidSpark(World world, double x, double y, double z, double endXs, double endYs, double endZs, float size,
                       float red, float green, float blue,
                       float maxAgeX, boolean noClip){
        super(world, x, y, z, 0.0D, 0.0D, 0.0D);
        particleRed = red;
        particleGreen = green;
        particleBlue = blue;
        endX = endXs;
        endY = endYs;
        endZ = endZs;
        particleGravity = 0;
        motionX = motionY = motionZ = 0;
        particleScale *= size;
        maxAge = (int)(3*maxAgeX);
        multiplier = (int)maxAgeX;
        setSize(0.01f, 0.01f);
        prevPosX=posX;
        prevPosY=posY;
        prevPosZ=posZ;
        this.canCollide = !noClip;
    }

    @Override
    public void tick() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if(age++ >= maxAge) setExpired();
        else {
            float percent = ((float)this.age / (float)this.maxAge)*2F;
            this.posX = endX + motionX*(double)percent;
            this.posY = endY + motionY*(double)percent;
            this.posZ = endZ + motionZ*(double)percent;
        }

        this.move(motionX, motionY, motionZ);
    }

    @Override
    public void renderParticle(IVertexBuilder buffer, ActiveRenderInfo renderInfo, float partialTicks) {
        int part = particle + age/multiplier;

        float var8 = part % 8 / 8.0F;
        float var9 = var8 + 0.624375F*2;
        float var10 = part / 8 / 8.0F;
        float var11 = var10 + 0.624375F*2;
        float var12 = 0.1F * particleScale;
        boolean shrink = true;

        if(shrink) var12 *= (maxAge-age+1)/(float)maxAge;

        //float var13 = (float)(prevPosX + (posX - prevPosX) * partialTicks - interpPosX);
        //float var14 = (float)(prevPosY + (posY - prevPosY) * partialTicks - interpPosY);
        //float var15 = (float)(prevPosZ + (posZ - prevPosZ) * partialTicks - interpPosZ);
        //float var16 = 1.0f;

        //buffer.pos(var13 - rotationX * var12 - rotationXY * var12, var14 - rotationZ * var12, var15 - rotationYZ * var12 - rotationXZ * var12).tex(var9, var11).color(particleRed * var16, particleGreen * var16, particleBlue * var16, 1).endVertex();
        //buffer.pos(var13 - rotationX * var12 + rotationXY * var12, var14 + rotationZ * var12, var15 - rotationYZ * var12 + rotationXZ * var12).tex(var9, var10).color(particleRed * var16, particleGreen * var16, particleBlue * var16, 1).endVertex();
        //buffer.pos(var13 + rotationX * var12 + rotationXY * var12, var14 + rotationZ * var12, var15 + rotationYZ * var12 + rotationXZ * var12).tex(var8, var10).color(particleRed * var16, particleGreen * var16, particleBlue * var16, 1).endVertex();
        //buffer.pos(var13 + rotationX * var12 - rotationXY * var12, var14 - rotationZ * var12, var15 + rotationYZ * var12 - rotationXZ * var12).tex(var8, var11).color(particleRed * var16, particleGreen * var16, particleBlue * var16, 1).endVertex();

    }

    @Override
    public IParticleRenderType getRenderType() {
        return RENDERER;
    }

    private static final IParticleRenderType RENDERER = new IParticleRenderType() {
        @Override
        public void beginRender(BufferBuilder buffer, TextureManager textureManager) {
            GlStateManager.depthMask(false);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
            GlStateManager.alphaFunc(GL11.GL_GREATER, 0.003921569F);
            GlStateManager.disableLighting();
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 0.75F);
            textureManager.bindTexture(PARTICLES);
            buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
        }

        @Override
        public void finishRender(Tessellator tess) {
            tess.draw();
            GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);
            GlStateManager.disableBlend();
            GlStateManager.depthMask(true);
        }

        @Override
        public String toString() {
            return "voidmagic:voidspark";
        }
    };
}
