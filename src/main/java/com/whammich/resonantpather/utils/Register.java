package com.whammich.resonantpather.utils;

import net.minecraft.item.Item;

import com.whammich.resonantpather.ResonantPather;
import com.whammich.resonantpather.gui.GuiHandler;
import com.whammich.resonantpather.items.ItemBaseBag;
import com.whammich.resonantpather.items.ItemBaseTool;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;



public class Register {
	public static ResonantPatherTab CREATIVE_TAB = new ResonantPatherTab();
	public static Item bagBase = new ItemBaseBag();
	public static Item toolBase = new ItemBaseTool();
	public static void regObj() {
		NetworkRegistry.INSTANCE.registerGuiHandler(ResonantPather.modInstance, new GuiHandler());
		items();
	}
	
	public static void items() {
		GameRegistry.registerItem(bagBase, bagBase.getUnlocalizedName());
		GameRegistry.registerItem(toolBase, toolBase.getUnlocalizedName());
	}
}
