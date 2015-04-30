package com.whammich.resrandom.items.types;

import java.util.Locale;

public enum ToolType {

	CREATIVE(0, 100000000), 
	REDSTONE(2000, 2000000), 
	RESONANT(20000, 10000000);

	public final int recieve;
	public final int capacity;

	private ToolType(int recieve, int capacity) {
		this.recieve = recieve;
		this.capacity = capacity;
	}

	@Override
    public String toString() {
        return name().toLowerCase(Locale.ENGLISH);
    }
	
}
