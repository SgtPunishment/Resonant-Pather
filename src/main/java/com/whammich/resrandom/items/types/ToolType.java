package com.whammich.resrandom.items.types;

import java.util.Locale;

public enum ToolType {

	CREATIVE(0, 100000000), 	// Cannot Charge, 100,000,000 RF Capacity
	LEADSTONE(200, 80000),		// 200 RF/t Receive, 80,000 RF Capacity
	HARDENED(800, 400000),		// 800 RF/t Receive, 400,000 RF Capacity
	REDSTONE(2000, 2000000), 	// 2,000 RF/t Receive, 2,000,000 RF Capacity
	RESONANT(20000, 10000000);	// 20,000 RF/t Receive, 10,000,000 RF Capacity

	public final int receive;
	public final int capacity;

	private ToolType(int receive, int capacity) {
		this.receive = receive;
		this.capacity = capacity;
	}

	@Override
    public String toString() {
        return name().toLowerCase(Locale.ENGLISH);
    }
	
}
