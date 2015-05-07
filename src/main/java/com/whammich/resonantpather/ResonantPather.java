package com.whammich.resonantpather;

import net.minecraft.item.Item;

import com.whammich.resonantpather.gui.GuiHandler;
import com.whammich.resonantpather.items.ItemBaseBag;
import com.whammich.resonantpather.items.ItemBaseTool;
import com.whammich.resonantpather.utils.Config;
import com.whammich.resonantpather.utils.Reference;
import com.whammich.resonantpather.utils.ResonantPatherTab;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(
		modid = Reference.MOD_ID, 
		name = Reference.MOD_NAME, 
		version = Reference.MOD_VERSION, 
		guiFactory = Reference.GuiFactory_class
	)

public class ResonantPather {
	
	public static ResonantPatherTab CREATIVE_TAB = new ResonantPatherTab();
	public static Item bagBase = new ItemBaseBag();
	public static Item toolBase = new ItemBaseTool();
	
	@Instance(Reference.MOD_ID)
	public static ResonantPather modInstance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Config.load(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(ResonantPather.modInstance, new GuiHandler());
		GameRegistry.registerItem(bagBase, bagBase.getUnlocalizedName());
		GameRegistry.registerItem(toolBase, toolBase.getUnlocalizedName());
	}

}
