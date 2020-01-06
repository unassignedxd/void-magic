package com.unassigned.voidmagic.client.render.tile;

import com.mojang.blaze3d.platform.GlStateManager;
import com.unassigned.voidmagic.VoidMagic;
import com.unassigned.voidmagic.common.tileentity.TileVoidStand;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileVoidStandRenderer extends TileEntityRenderer<TileVoidStand> {

    @Override
    public void render(TileVoidStand te, double x, double y, double z, float partialTicks, int destroyStage) {
        te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(i -> {
            ItemStack item = i.getStackInSlot(0);
            if(!item.isEmpty()) {
                GlStateManager.pushMatrix();
                GlStateManager.translated(x+.5D, y+1.5D, z+.5D);

                float scale = item.getItem() instanceof BlockItem ? .85F : .65F;
                GlStateManager.scalef(scale, scale, scale);

                try {
                    GlStateManager.pushMatrix();
                    GlStateManager.disableLighting();
                    GlStateManager.pushLightingAttributes();
                    RenderHelper.enableStandardItemLighting();
                    Minecraft.getInstance().getItemRenderer().renderItem(item, ItemCameraTransforms.TransformType.FIXED);
                    RenderHelper.disableStandardItemLighting();
                    GlStateManager.popAttributes();
                    GlStateManager.enableLighting();
                    GlStateManager.popMatrix();
                } catch(Exception e) {
                    VoidMagic.LOGGER.error(e);
                }

                GlStateManager.popMatrix();
            }
        });
    }
}
