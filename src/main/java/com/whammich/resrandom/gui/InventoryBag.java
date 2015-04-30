package com.whammich.resrandom.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryBag implements IInventory {

	public ItemStack stack;
	public EntityPlayer player;
	public ItemStack[] inventory;
	private String bagName;

	public InventoryBag() {
		inventory = new ItemStack[9];
	}
	
	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return this.inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		if (inventory[slot] != null) {
            if (inventory[slot].stackSize <= amount) {
                ItemStack itemstack = inventory[slot];
                inventory[slot] = null;
                return itemstack;
            }
            ItemStack itemstack1 = inventory[slot].splitStack(amount);
            if (inventory[slot].stackSize == 0) {
                inventory[slot] = null;
            }
            return itemstack1;
        }
        else {
            return null;
        }
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index) {
		if (this.inventory != null) {
			ItemStack itemstack = this.inventory[index];
			this.inventory[index] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inventory[slot] = stack;
		if (stack != null && stack.stackSize > getInventoryStackLimit()){
			stack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.bagName : "resrandom.bag";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return this.bagName != null && this.bagName.length() > 0;
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void markDirty() {}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return true;
	}

	public void readFromNBT(NBTTagCompound tagCompound) {
		NBTTagList tagList = tagCompound.getTagList("Items", 10);
		this.inventory = new ItemStack[this.getSizeInventory()];
		for (int i = 0; i < tagList.tagCount(); ++i) {
			NBTTagCompound tabCompound1 = tagList.getCompoundTagAt(i);
			byte byte0 = tabCompound1.getByte("Slot");
			if (byte0 >= 0 && byte0 < this.inventory.length) {
				this.inventory[byte0] = ItemStack.loadItemStackFromNBT(tabCompound1);
			}
		}
	}

	public void writeToNBT(NBTTagCompound tagCompound) {
		NBTTagList tagList = new NBTTagList();
		for (int i = 0; i < this.inventory.length; ++i) {
			if (this.inventory[i] != null) {
				NBTTagCompound tagCompound1 = new NBTTagCompound();
				tagCompound1.setByte("Slot", (byte) i);
				this.inventory[i].writeToNBT(tagCompound1);
				tagList.appendTag(tagCompound1);
			}
		}
		tagCompound.setTag("Items", tagList);
	}
	
}
