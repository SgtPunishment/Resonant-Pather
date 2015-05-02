package com.whammich.resrandom.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.sun.imageio.plugins.common.I18N;

public class GuiBag extends GuiContainer {

	private static final ResourceLocation TEXTURE = new ResourceLocation("textures/gui/container/dispenser.png");

	public Container inventorySlots;

	public InventoryBag inventoryBag;

	public GuiBag(ContainerBag containerBag) {
		super(containerBag);
		this.inventoryBag = containerBag.inventory;
	}

	public void drawScreen(int par1, int par2, float par3) {
		super.drawScreen(par1, par2, par3);
	}

	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		String s = this.inventoryBag.hasCustomInventoryName() ? this.inventoryBag.getInventoryName() 
				: I18N.getString(this.inventoryBag.getInventoryName());
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 0, 421752);
		this.fontRendererObj.drawString(I18N.getString("container.bag"), 26, this.ySize - 96 + 4, 421752);
	}
	
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(TEXTURE);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}
}