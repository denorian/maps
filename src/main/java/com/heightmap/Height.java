package com.heightmap;

import com.heightmap.services.First;
import com.heightmap.storages.Storage;

public class Height {
	public static final int ERROR_VALUE = -1000;
	public static final double STEP = 0.0005;
	private static Storage storage;
	
	static {
		storage = new Storage();
	}
	
	int get(double latitude, double longitude) {
		int height = storage.getHeight(latitude, longitude);
		
		if (height == Height.ERROR_VALUE) {
			height = First.getHeight(latitude, longitude);
			if (height > Height.ERROR_VALUE)
				storage.putHeight(latitude, longitude, height);
		}
		
		return height;
	}
	
}
