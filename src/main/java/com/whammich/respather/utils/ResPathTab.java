package com.whammich.respather.utils;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ResPathTab extends CreativeTabs {

		public ResPathTab() {
			super("respather");
		}

		@Override
		public ItemStack getIconItemStack() {
			return new ItemStack(Blocks.diamond_block);
		}

		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return null;
		}
	}
