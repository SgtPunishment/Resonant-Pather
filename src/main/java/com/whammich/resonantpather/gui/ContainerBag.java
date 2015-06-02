package com.whammich.resonantpather.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerBag extends Container {

	public final InventoryBag inventory;
	private int numRows;
	
	private static final int INV_START = InventoryBag.INV_SIZE, INV_END = INV_START + 26, HOTBAR_START = INV_END + 1, HOTBAR_END = HOTBAR_START + 8;

	@SuppressWarnings("unused")
	public ContainerBag(EntityPlayer par1Player, InventoryPlayer player, InventoryBag inventoryBag) {
		
		this.inventory = inventoryBag;
		this.numRows = inventoryBag.getSizeInventory() / 9;
		
		int i;
		int j;
		int k;
		int x;
		int y;
		int x0 = 8;
		int y0 = 18;
		
		// Our bag inventory
		for (i = 0; i < InventoryBag.INV_SIZE; ++i) {
			this.addSlotToContainer(new SlotItemInv(this.inventory, i, x = x0 + (i % 9) * 18, y = y0 + (i/9) * 18));
		}

		// Player Main Inventory
		for (j = 0; j < 3; ++j) {
			for (k = 0; k < 9; ++k) {
				this.addSlotToContainer(new Slot(player, k + j * 9 + 9, 8 + k * 18, 103 + j * 18 + (this.numRows - 4) * 18));
			}
		}
		
		// Player hotbar
		for (i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(player, i, 8 + i * 18, 161 + (this.numRows - 4) * 18));
		}

	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return inventory.isUseableByPlayer(player);
	}

	public ItemStack transferStackInSlot(EntityPlayer player, int slots) {
		ItemStack stack = null;
		Slot slot = (Slot) this.inventorySlots.get(slots);

		if (slot != null && slot.getHasStack()) {
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();

			if (slots < INV_START) {
				if (!this.mergeItemStack(stack1, INV_START, HOTBAR_END + 1, true)) {
					return null;
				}
				slot.onSlotChange(stack1, stack);
			} else {
				if (slots >= INV_START) {
					if (!this.mergeItemStack(stack1, 0, INV_START, false)) {
						return null;
					}
				} else if (slots >= HOTBAR_START && slots < HOTBAR_END + 1) {
					if (!this.mergeItemStack(stack1, INV_START, INV_END + 1, false)) {
						return null;
					}
				}
			}

			if (stack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}

			if (stack1.stackSize == stack.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(player, stack1);
		}

		return stack;
	}
	
	@Override
	public ItemStack slotClick(int slot, int button, int flag, EntityPlayer player) {
		if(slot>=0 && getSlot(slot) != null && getSlot(slot).getStack() == player.getHeldItem()){
			return null;
		}
		return super.slotClick(slot, button, flag, player);
	}
	
	@Override
    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed(player);
        inventory.save();
        player.inventory.setInventorySlotContents(player.inventory.currentItem, inventory.invItem);
    }

}
