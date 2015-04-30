package com.whammich.resrandom.items.types;

import java.util.Locale;

public enum BagType {

	BASIC(9),
	IMPROVED(27), // Get better name
	ADVANCED(54);
	
	public final int slots;
	
	private BagType(int slots) {
		this.slots = slots;
	}
	
	@Override
    public String toString() {
        return name().toLowerCase(Locale.ENGLISH);
    }
}
