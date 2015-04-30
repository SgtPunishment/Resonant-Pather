package com.whammich.resrandom.utils;

import net.minecraft.item.Item;

import com.whammich.resrandom.ResRandom;
import com.whammich.resrandom.gui.GuiHandler;
import com.whammich.resrandom.items.ItemBaseBag;
import com.whammich.resrandom.items.ItemBaseTool;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;



public class Register {
	public static ResPathTab CREATIVE_TAB = new ResPathTab();
	public static Item bagBasic = new ItemBaseBag();
	public static Item toolBase = new ItemBaseTool();
	public static void regObj() {
		NetworkRegistry.INSTANCE.registerGuiHandler(ResRandom.modInstance, new GuiHandler());
		items();
	}
	
	public static void items() {
		GameRegistry.registerItem(bagBasic, bagBasic.getUnlocalizedName());
		GameRegistry.registerItem(toolBase, toolBase.getUnlocalizedName());
	}
}
