package com.whammich.resonantpather.items.types;

import net.minecraft.item.EnumRarity;

import java.util.Locale;

public enum ToolType {

	CREATIVE(0, 100000000, EnumRarity.epic), 	    // Cannot Charge, 100,000,000 RF Capacity
	LEADSTONE(200, 80000, EnumRarity.common),		// 200 RF/t Receive, 80,000 RF Capacity
	HARDENED(800, 400000, EnumRarity.common),		// 800 RF/t Receive, 400,000 RF Capacity
	REDSTONE(8000, 4000000, EnumRarity.uncommon),
	RESONANT(32000, 20000000, EnumRarity.rare);	    // 32,000 RF/t Receive, 20,000,000 RF Capacity

	public final int receive;
	public final int capacity;
    public final EnumRarity rarity;

	private ToolType(int receive, int capacity, EnumRarity rarity) {
		this.receive = receive;
		this.capacity = capacity;
        this.rarity = rarity;
	}

	@Override
    public String toString() {
        return name().toLowerCase(Locale.ENGLISH);
    }
	
}
