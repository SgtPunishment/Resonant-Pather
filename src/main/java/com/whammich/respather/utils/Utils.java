package com.whammich.respather.utils;

import net.minecraft.util.StatCollector;

public class Utils {
	
	public static String localize(String key) {
		return StatCollector.translateToLocal(key);
	}
	
	public static String localizeFormatted(String key, String keyFormat) {
		return String.format(localize(key), localize(keyFormat));
	}
}
