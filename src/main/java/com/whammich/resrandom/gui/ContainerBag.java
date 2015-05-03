package com.whammich.resrandom.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerBag extends Container {

	public final InventoryBag inventory;

	private static final int INV_START = InventoryBag.INV_SIZE, INV_END = INV_START + 26,
			HOTBAR_START = INV_END + 1, HOTBAR_END = HOTBAR_START + 8;

	public ContainerBag(EntityPlayer par1Player, InventoryPlayer player, InventoryBag inventoryBag) {
		
		this.inventory = inventoryBag;
		
		int i;
		for (i = 0; i < InventoryBag.INV_SIZE; ++i) {
			this.addSlotToContainer(new SlotItemInv(this.inventory, i, 80 + (18 * (int) (i / 4)), 8 + (18 * (i % 4))));
		}

		for (i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(player, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(player, i, 8 + i * 18, 142));
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
				// if(stack1.getItem() instanceof Block) {
				// if(!this.mergeItemStack(stack1, 0, InventoryBag.INV_SIZE,
				// false)){
				// return null;
				// }
				// }

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
	public ItemStack slotClick(int slot, int button, int flag, EntityPlayer player){
		if(slot>=0 && getSlot(slot) != null && getSlot(slot).getStack() == player.getHeldItem()){
			return null;
		}
		return super.slotClick(slot, button, flag, player);
	}

    @Override
    public void onContainerClosed(EntityPlayer player)
    {
        super.onContainerClosed(player);
        inventory.save();
        player.inventory.setInventorySlotContents(player.inventory.currentItem, inventory.invItem);
    }
}
