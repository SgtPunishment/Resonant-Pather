package com.whammich.resonantpather.utils;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.whammich.resonantpather.items.types.BagType;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ResonantPatherTab extends CreativeTabs {

		public ResonantPatherTab() {
			super("resonantpather");
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
