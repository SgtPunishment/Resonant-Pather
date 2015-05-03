package com.whammich.resrandom.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.sun.imageio.plugins.common.I18N;

public class GuiBag extends GuiContainer {

	private static final ResourceLocation TEXTURE = new ResourceLocation("textures/gui/container/generic_54.png");

	public Container inventorySlots;

	public InventoryBag inventoryBag;
	private int inventoryRows;
	
	public GuiBag(ContainerBag containerBag) {
		super(containerBag);
		this.inventoryBag = containerBag.inventory;
        short short1 = 222;
        int i = short1 - 108;
        this.inventoryRows = inventoryBag.getSizeInventory() / 9;
        this.ySize = i + this.inventoryRows * 18;
	}

	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		String s = this.inventoryBag.hasCustomInventoryName() ? this.inventoryBag.getInventoryName() : I18N.getString(this.inventoryBag.getInventoryName());
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s), 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 94, 4210752);
  	}
	
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TEXTURE);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.inventoryRows * 18 + 17);
        this.drawTexturedModalRect(k, l + this.inventoryRows * 18 + 17, 0, 126, this.xSize, 96);
	}
}