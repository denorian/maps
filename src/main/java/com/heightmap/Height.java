package com.heightmap;

import com.heightmap.services.First;
import com.heightmap.storages.Storage;

import java.util.HashMap;

public class Height {
	public static final int ERROR_VALUE = -1000;
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
		//System.out.println(height);
		return height;
	}
	
	HashMap<String, Coordinate> getMap(double latitudeStart, double latitudeEnd, double longitudeStart, double longitudeEnd, int precision) {
		
		return storage.find(latitudeStart, latitudeEnd, longitudeStart, longitudeEnd, precision);
	}
}
