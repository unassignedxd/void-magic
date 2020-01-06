package com.unassigned.voidmagic.client.screens;

import com.unassigned.voidmagic.VoidMagic;
import com.unassigned.voidmagic.common.container.ContainerCoalGenerator;
import com.unassigned.voidmagic.common.tileentity.TileCoalGenerator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class CoalGeneratorScreen extends ContainerScreen<ContainerCoalGenerator> {

    private final ResourceLocation resLoc = new ResourceLocation(VoidMagic.MODID, "textures/gui/coalgeneratorgui.png");

    public CoalGeneratorScreen(ContainerCoalGenerator container, PlayerInventory playerInventory, ITextComponent name) {
        super(container, playerInventory, name);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.font.drawString(this.title.getFormattedText(), 8f, 6f, 4210752);

        drawString(Minecraft.getInstance().fontRenderer, "Energy Stored: " + container.getEnergyStored(), 10, 10, 0xfffff);
        this.minecraft.getTextureManager().bindTexture(resLoc);
        float i = ((float)container.getEnergyStored() / (float)container.getMaxEnergyStored())*144F;
        this.blit(16, 39, 0, 134, (int)i, 7);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        renderBackground();
        this.minecraft.getTextureManager().bindTexture(resLoc);
        int relX = (this.width - this.xSize) / 2;
        int relY = (this.height - this.ySize) / 2;
        this.blit(relX, relY, 0, 0, 176, 133);
    }
}
