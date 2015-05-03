package com.whammich.resrandom.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.whammich.resrandom.items.types.BagType;

import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {


	
	public GuiHandler() {

	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		switch(ID) {
		case 0: return new ContainerBag(player, player.inventory, new InventoryBag(BagType.values()[0], player.getHeldItem()));
		case 1: return new ContainerBag(player, player.inventory, new InventoryBag(BagType.values()[1], player.getHeldItem()));
		case 2: return new ContainerBag(player, player.inventory, new InventoryBag(BagType.values()[2], player.getHeldItem()));
		}
		return null; 
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID) {
		case 0: return new GuiBag((ContainerBag) new ContainerBag(player, player.inventory, new InventoryBag(BagType.values()[0],player.getHeldItem())));
		case 1: return new GuiBag((ContainerBag) new ContainerBag(player, player.inventory, new InventoryBag(BagType.values()[1],player.getHeldItem())));
		case 2: return new GuiBag((ContainerBag) new ContainerBag(player, player.inventory, new InventoryBag(BagType.values()[2],player.getHeldItem())));
		}
		return null;
	}
}