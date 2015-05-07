package com.whammich.resonantpather.items.types;

import java.util.Locale;

import net.minecraft.item.EnumRarity;

public enum BagType {

	CREATIVE(54, EnumRarity.epic),
	BASIC(9, EnumRarity.common), 
	HARDENED(18, EnumRarity.common), 
	REINFORCED(36, EnumRarity.uncommon), 
	RESONANT(54, EnumRarity.rare);

	public final int slots;
	public final EnumRarity rarity;

	private BagType(int slots, EnumRarity rarity) {
		this.slots = slots;
		this.rarity = rarity;
	}

	@Override
	public String toString() {
		return name().toLowerCase(Locale.ENGLISH);
	}
}
