package com.whammich.resonantpather.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

import com.whammich.resonantpather.items.ItemBaseBag;
import com.whammich.resonantpather.items.types.BagType;
import com.whammich.resonantpather.utils.ModLogger;

public class InventoryBag implements IInventory {

	public EntityPlayer player;
	public static int INV_SIZE;
	public ItemStack[] bagContents;
	private String bagName = "resonantpather.bag";
	public final ItemStack invItem;

	public BagType bType;
	
	public InventoryBag(BagType type, ItemStack stack) {
		this.bType = type;
		invItem = stack;
		INV_SIZE = bType.slots;
		this.bagContents = new ItemStack[INV_SIZE];
		
		if(!stack.hasTagCompound()){
			stack.setTagCompound(new NBTTagCompound());
		}
		readFromNBT(stack.getTagCompound());
		ModLogger.logDebug("01: Reading Stack: " + stack.toString());
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
		if (stack != null && stack.stackSize > getInventoryStackLimit()){
			stack.stackSize = getInventoryStackLimit();
		}
        bagContents[slot] = stack;
	}

	@Override
	public String getInventoryName() {
		return invItem.getDisplayName();
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
		NBTTagList items = compound.getTagList("Items", Constants.NBT.TAG_COMPOUND);
		for (int i = 0; i < items.tagCount(); ++i) {
			NBTTagCompound item = items.getCompoundTagAt(i);
			ModLogger.logDebug("Pre-Slot: " + i + " :" + item.toString());
			int slot = item.getInteger("Slot");
			ModLogger.logDebug("Post-Slot: " + i + " :" + item.toString());			
			if (slot >= 0 && slot < this.bagContents.length) {
				ModLogger.logDebug("If-Slot: " + i + " :" + item.toString());
				this.bagContents[slot] = ItemStack.loadItemStackFromNBT(item);
				ModLogger.logDebug("Done-Slot: " + i + " :" + item.toString());
			}
		}
	}

	public void writeToNBT(NBTTagCompound tagCompound) {
		NBTTagList items = new NBTTagList();
		
		for (int i = 0; i < getSizeInventory(); ++i) {
			ModLogger.logDebug("Null-Slot: " + i + " :" + items.toString());
			if (getStackInSlot(i) != null) {
				ModLogger.logDebug("Tag-Slot: " + i + " :" + items.toString());
				NBTTagCompound item = new NBTTagCompound();
				item.setInteger("Slot", i);
				ModLogger.logDebug("Pre-Slot: " + i + " :" + items.toString());
				getStackInSlot(i).writeToNBT(item);
				items.appendTag(item);
				ModLogger.logDebug("Post-Slot: " + i + " :" + items.toString());
			}
		}
		ModLogger.logDebug("Saving Item: " + items.toString());
		tagCompound.setTag("Items", items);
	}

    public void save()
    {
        for (int i = 0; i < getSizeInventory(); ++i)
        {
            if (getStackInSlot(i) != null && getStackInSlot(i).stackSize == 0)
            {
                setInventorySlotContents(i, null);
            }
        }
        writeToNBT(invItem.getTagCompound());
        ModLogger.logDebug("markDirt(): " + invItem.getTagCompound());
    }
}
