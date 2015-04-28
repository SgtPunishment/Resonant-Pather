package com.whammich.respather.gui;

import java.util.ArrayList;
import java.util.List;

import com.whammich.respather.utils.Config;
import com.whammich.respather.utils.Config.Section;
import com.whammich.respather.utils.Reference;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;

public class GuiResPathConfig extends GuiConfig {

	public GuiResPathConfig(GuiScreen parentScreen) {
		super(parentScreen, getConfigElements(parentScreen), Reference.MOD_ID, false,
				false, GuiConfig.getAbridgedConfigPath("/ResonantPather/"));
	}

	@SuppressWarnings("rawtypes")
	private static List<IConfigElement> getConfigElements(GuiScreen parentScreen) {
		List<IConfigElement> list = new ArrayList<IConfigElement>();

		for (Section section : Config.sections) {
			list.add(new ConfigElement<ConfigCategory>(Config.config
					.getCategory(section.lc())));
		}
		return list;
	}
}