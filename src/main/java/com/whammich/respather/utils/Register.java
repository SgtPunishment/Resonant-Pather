package com.whammich.respather.utils;

import net.minecraft.item.Item;

import com.whammich.respather.Main;
import com.whammich.respather.gui.GuiHandler;
import com.whammich.respather.items.ItemBagBase;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;



public class Register {
	public static ResPathTab CREATIVE_TAB = new ResPathTab();
	public static Item bagBasic = new ItemBagBase();
	public static void regObj() {
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.modInstance, new GuiHandler());
		items();
	}
	
	public static void items() {
		GameRegistry.registerItem(bagBasic, "bagBasic");
	}
}
