package com.whammich.resrandom;

import com.whammich.resrandom.utils.Config;
import com.whammich.resrandom.utils.Reference;
import com.whammich.resrandom.utils.Register;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(
		modid = Reference.MOD_ID, 
		name = Reference.MOD_NAME, 
		version = Reference.MOD_VERSION, 
		guiFactory = Reference.GuiFactory_class
	)

public class ResRandom {
	@Instance(Reference.MOD_ID)
	public static ResRandom modInstance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Config.load(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		Register.regObj();
	}

}
