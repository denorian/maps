package com.heightmap.worker;


import com.heightmap.Coordinate;
import com.heightmap.Height;
import com.heightmap.Main;

public class CoordinateWorkerThread extends Thread {
	
	private Coordinate coordinate;
	private Height height;
	
	public CoordinateWorkerThread(Coordinate coordinate, Height height) {
		this.coordinate = coordinate;
		this.height = height;
	}
	
	public void run() {
		coordinate.setHeight(height.getAndPutIntoDB(coordinate.getLatitude(),coordinate.getLongitude()));
	}
}
