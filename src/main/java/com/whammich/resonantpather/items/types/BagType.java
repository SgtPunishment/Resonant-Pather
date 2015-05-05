package com.whammich.resonantpather.items.types;

import java.util.Locale;

public enum BagType {

	BASIC(9),
	HARDENED(18),
	REINFORCED(27),
	RESONANT(54);
	
	public final int slots;
	
	private BagType(int slots) {
		this.slots = slots;
	}
	
	@Override
    public String toString() {
        return name().toLowerCase(Locale.ENGLISH);
    }
}
