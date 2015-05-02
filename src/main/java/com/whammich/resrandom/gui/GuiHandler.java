package com.whammich.resrandom.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {


	
	public GuiHandler() {

	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == 0){
			return new ContainerBag(player, player.inventory, new InventoryBag(player.getHeldItem()));
		}
		return null; 
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == 0){
			return new GuiBag((ContainerBag) new ContainerBag(player, player.inventory, new InventoryBag(player.getHeldItem())));
		}
		return null;
	}
}