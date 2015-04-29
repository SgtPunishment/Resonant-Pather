package com.whammich.respather.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cofh.api.energy.IEnergyContainerItem;

import com.whammich.respather.utils.Reference;
import com.whammich.respather.utils.Register;
import com.whammich.respather.utils.Utils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemToolBase extends Item implements IEnergyContainerItem {

	public static int capacity = 16000;
    public static int send = 80;
    public static int recieve = 0;
	
    public ItemToolBase() {
    	super();
    	setUnlocalizedName(Reference.MOD_ID + ".tool.base");
    	setTextureName(Reference.MOD_ID + ":toolbase");
    	setCreativeTab(Register.CREATIVE_TAB);
    }
    
    public static ItemStack setDefaultEnergyTag(ItemStack stack, int energy) {
        if(stack.stackTagCompound == null) {
            stack.setTagCompound(new NBTTagCompound());
        }

        stack.stackTagCompound.setInteger("Energy", energy);
        return stack;
    }
    
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int hitSide, float hitX, float hitY, float hitZ) {

        if (!player.capabilities.isCreativeMode)
            extractEnergy(stack, send, false);

        return super.onItemUse(stack, player, world, x, y, z, hitSide, hitX, hitY, hitZ);
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean held) {
        if (!world.isRemote) {
            if (getEnergyStored(stack) <= 0) {
                ((EntityPlayer) entity).inventory.decrStackSize(slot, 1);
                ((EntityPlayer) entity).inventory.addItemStackToInventory(new ItemStack(Items.baked_potato, 2));

            }
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void getSubItems(Item item, CreativeTabs tabs, List list) {
        list.add(setDefaultEnergyTag(new ItemStack(item), capacity));
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        if (stack.stackTagCompound == null)
            setDefaultEnergyTag(stack, 0);

        int currentEnergy = stack.stackTagCompound.getInteger("Energy");

        return 1.0 - ((double) currentEnergy / (double) capacity);
    }

    @SideOnly(Side.CLIENT)
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean held) {

        if (Utils.displayShiftForDetail && !Utils.isShiftKeyDown())
            list.add(Utils.shiftForDetails());

        if (stack.stackTagCompound == null)
            setDefaultEnergyTag(stack, 0);

        if (Utils.isShiftKeyDown()) {
            list.add(Utils.localize("info.cofh.charge") + ": " + stack.stackTagCompound.getInteger("Energy") + " / " + capacity + " RF");
            list.add(Utils.ORANGE + Utils.localizeFormatted("info.RArm.tooltip.peruse", "" + send) + Utils.END);
        }
    }

    // Energy Stuff

    @Override
    public int receiveEnergy(ItemStack stack, int i, boolean simulate) {
        if (stack.stackTagCompound == null)
            setDefaultEnergyTag(stack, 0);

        int energy = stack.stackTagCompound.getInteger("Energy");
        int energyReceived = Math.min(i, Math.min(capacity - energy, recieve));

        if (!simulate) {
            energy += energyReceived;
            stack.stackTagCompound.setInteger("Energy", energy);
        }

        return energyReceived;
    }

    @Override
    public int extractEnergy(ItemStack stack, int extract, boolean simulate) {
        if (stack.stackTagCompound == null)
            setDefaultEnergyTag(stack, 0);

        int energy = stack.stackTagCompound.getInteger("Energy");
        int energyExtracted = Math.min(extract, Math.min(energy, send));

        if (!simulate) {
            energy -= energyExtracted;
            stack.stackTagCompound.setInteger("Energy", energy);
        }

        return energyExtracted;
    }

    @Override
    public int getEnergyStored(ItemStack stack) {
        if (stack.stackTagCompound == null)
            setDefaultEnergyTag(stack, 0);

        return stack.stackTagCompound.getInteger("Energy");
    }

    @Override
    public int getMaxEnergyStored(ItemStack stack) {
        return capacity;
    }

}
