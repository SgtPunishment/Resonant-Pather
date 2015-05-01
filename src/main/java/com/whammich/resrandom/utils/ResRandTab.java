package com.whammich.resrandom.utils;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.whammich.resrandom.items.types.BagType;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ResRandTab extends CreativeTabs {

		public ResRandTab() {
			super("resrandom");
		}


		public static ItemStack getStackItem(BagType advanced) {
	        return new ItemStack(Register.bagBase, 1, advanced.ordinal());
	    }
		
		@Override
		public ItemStack getIconItemStack() {
			return getStackItem(BagType.ADVANCED);
		}

		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return null;
		}
	}
