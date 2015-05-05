package com.whammich.resonantpather.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.whammich.resonantpather.items.types.BagType;

import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new ContainerBag(player, player.inventory, new InventoryBag(BagType.values()[ID], player.getHeldItem()));
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new GuiBag(new ContainerBag(player, player.inventory, new InventoryBag(BagType.values()[ID], player.getHeldItem())));
	}
}