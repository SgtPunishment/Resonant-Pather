package com.whammich.resrandom.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerBag extends Container {

	public ContainerBag(InventoryPlayer player, InventoryBag bag) {

		this.addSlotToContainer(new Slot(bag, 0, 62, 17));
		this.addSlotToContainer(new Slot(bag, 1, 80, 17));
		this.addSlotToContainer(new Slot(bag, 2, 98, 17));

		this.addSlotToContainer(new Slot(bag, 3, 62, 35));
		this.addSlotToContainer(new Slot(bag, 4, 80, 35));
		this.addSlotToContainer(new Slot(bag, 5, 98, 35));

		this.addSlotToContainer(new Slot(bag, 6, 62, 53));
		this.addSlotToContainer(new Slot(bag, 7, 80, 53));
		this.addSlotToContainer(new Slot(bag, 8, 98, 53));

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(player, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(player, i, 8 + i * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);
	}

}
