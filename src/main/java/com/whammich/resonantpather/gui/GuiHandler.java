package com.whammich.resonantpather.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;

import com.whammich.resonantpather.ResonantPather;
import com.whammich.resonantpather.items.ItemBaseTool;
import com.whammich.resonantpather.items.types.BagType;

import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		InventoryBaubles BaubleBag = PlayerHandler.getPlayerBaubles(player);
		if(player.getHeldItem().getItem() instanceof ItemBaseTool){
			return new ContainerBag(player, player.inventory, new InventoryBag(BagType.values()[ID], new ItemStack(ResonantPather.bagBase, 1, BaubleBag.stackList[3].getItemDamage())));
		}
		
		return new ContainerBag(player, player.inventory, new InventoryBag(BagType.values()[ID], player.getHeldItem()));
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		InventoryBaubles BaubleBag = PlayerHandler.getPlayerBaubles(player);
		if(player.getHeldItem().getItem() instanceof ItemBaseTool){
			return new GuiBag(new ContainerBag(player, player.inventory, new InventoryBag(BagType.values()[ID], new ItemStack(ResonantPather.bagBase, 1, BaubleBag.stackList[3].getItemDamage()))));
		}
		return new GuiBag(new ContainerBag(player, player.inventory, new InventoryBag(BagType.values()[ID], player.getHeldItem())));
	}
}