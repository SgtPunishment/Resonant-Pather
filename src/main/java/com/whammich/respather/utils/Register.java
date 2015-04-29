package com.whammich.respather.utils;

import net.minecraft.item.Item;

import com.whammich.respather.Respather;
import com.whammich.respather.gui.GuiHandler;
import com.whammich.respather.items.ItemBagBase;
import com.whammich.respather.items.ItemToolBase;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;



public class Register {
	public static ResPathTab CREATIVE_TAB = new ResPathTab();
	public static Item bagBasic = new ItemBagBase();
	public static Item toolBase = new ItemToolBase();
	public static void regObj() {
		NetworkRegistry.INSTANCE.registerGuiHandler(Respather.modInstance, new GuiHandler());
		items();
	}
	
	public static void items() {
		GameRegistry.registerItem(bagBasic, bagBasic.getUnlocalizedName());
		GameRegistry.registerItem(toolBase, toolBase.getUnlocalizedName());
	}
}
