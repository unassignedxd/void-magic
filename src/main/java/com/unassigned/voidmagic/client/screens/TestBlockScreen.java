package com.unassigned.voidmagic.client.screens;

import com.unassigned.voidmagic.VoidMagic;
import com.unassigned.voidmagic.common.container.ContainerTestBlock;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class TestBlockScreen extends ContainerScreen<ContainerTestBlock> {

    private final ResourceLocation resLoc = new ResourceLocation(VoidMagic.MODID, "textures/gui/testblockgui.png");

    public TestBlockScreen(ContainerTestBlock container, PlayerInventory playerInventory, ITextComponent name) {
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
        this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8f, (float)(this.ySize - 98), 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        renderBackground();
        this.minecraft.getTextureManager().bindTexture(resLoc);
        int relX = (this.width - this.xSize) / 2;
        int relY = (this.height - this.ySize) / 2;
        this.blit(relX, relY, 0, 0, this.xSize, this.ySize);
    }
}
