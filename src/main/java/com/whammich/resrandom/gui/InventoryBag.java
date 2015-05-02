package com.whammich.resrandom.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import com.whammich.resrandom.items.ItemBaseBag;
import com.whammich.resrandom.utils.ModLogger;

public class InventoryBag implements IInventory {

	public ItemStack stack;
	public EntityPlayer player;
	public static final int INV_SIZE = 9;
	public ItemStack[] bagContents = new ItemStack[INV_SIZE];
	private String bagName = "resrandom.bag";
	private final ItemStack invItem;

	public InventoryBag(ItemStack stack) {
		
		invItem = stack;
		
		if(!stack.hasTagCompound()){
			stack.setTagCompound(new NBTTagCompound());
		}
		
		readFromNBT(stack.getTagCompound());
		
		ModLogger.logInfo("01: Reading Stack: " + stack.toString());

	}
	
	@Override
	public int getSizeInventory() {
		return bagContents.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return this.bagContents[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack stack = getStackInSlot(slot);
		if(stack != null) {
			if(stack.stackSize > amount) {
				stack = stack.splitStack(amount);
				markDirty();
			} else {
				setInventorySlotContents(slot, null);
			}
		}
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack stack = getStackInSlot(slot);
		setInventorySlotContents(slot, null);
		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		bagContents[slot] = stack;
		if (stack != null && stack.stackSize > getInventoryStackLimit()){
			stack.stackSize = getInventoryStackLimit();
		}
		markDirty();
	}

	@Override
	public String getInventoryName() {
		return bagName;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return bagName.length() > 0;
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void markDirty() {
		for (int i = 0; i < getSizeInventory(); ++i){
			if(getStackInSlot(i) != null && getStackInSlot(i).stackSize == 0){
				bagContents[i] = null;
			}
		}
//		NBTTagCompound tag = new NBTTagCompound();
//		writeToNBT(tag);
//		invItem.setTagCompound(tag);
		writeToNBT(invItem.getTagCompound());
	}

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
		return !(stack.getItem() instanceof ItemBaseBag);
	}

	public void readFromNBT(NBTTagCompound compound) {
		NBTTagList items = compound.getTagList("Items", 10);
		for (int i = 0; i < items.tagCount(); ++i) {
			
			NBTTagCompound item = items.getCompoundTagAt(i);
			ModLogger.logInfo("02: Reading Item: " + item.toString());
			
			int slot = item.getInteger("Slot");
			ModLogger.logInfo("03: Reading Item: " + item.toString());
			
			if (slot >= 0 && slot < this.bagContents.length) {
				ModLogger.logInfo("04: Reading Item: " + item.toString());
				
				this.bagContents[slot] = ItemStack.loadItemStackFromNBT(item);
				ModLogger.logInfo("05: Reading Item: " + item.toString());

			}
		}
	}

	public void writeToNBT(NBTTagCompound tagCompound) {
		NBTTagList items = new NBTTagList();
		
		for (int i = 0; i < getSizeInventory(); ++i) {
			
			if (getStackInSlot(i) != null) {
				
				NBTTagCompound item = new NBTTagCompound();
				item.setInteger("Slot", i);
				
				getStackInSlot(i).writeToNBT(item);
				items.appendTag(item);
			}
		}
		ModLogger.logInfo("Saving Item: " + items.toString());
		tagCompound.setTag("Items", items);
	}
	
}
