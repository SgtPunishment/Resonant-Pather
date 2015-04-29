package com.whammich.respather.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiBasicBag extends GuiContainer {

	private static final ResourceLocation TEXTURE = new ResourceLocation("textures/gui/container/dispenser.png");
	private static final int GUI_WIDTH = 176;
    private static final int GUI_HEIGHT = 166;
    private int xSize, ySize, guiLeft, guiTop;
    
    public Container inventorySlots;
    
	public GuiBasicBag(InventoryPlayer inventPlayer, InventoryBag inventBag) {
		super(new ContainerBag(inventPlayer, inventBag));
		this.xSize = GUI_WIDTH;
        this.ySize = GUI_HEIGHT;
	}
	
	@Override
    public void initGui() {
        super.initGui();
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
    }
	
	@Override
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
		this.drawDefaultBackground();
		this.drawGuiContainerBackgroundLayer(p_73863_3_, p_73863_1_, p_73863_2_);
	}
	
	public static void bindTexture(ResourceLocation resource) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(resource);
    }
	
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glPushMatrix();
        int x = this.guiLeft;
        int y = this.guiTop;
        GL11.glTranslatef(x, y, 0);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        bindTexture(TEXTURE);

        drawTexturedModalRect(0, 0, 0, 0, GUI_WIDTH, GUI_HEIGHT);

        GL11.glPopMatrix();
    }

}
