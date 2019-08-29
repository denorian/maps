package com.heightmap;

public class Coordinate {
	private double latitude;
	private double longitude;
	private int height;
	
	public Coordinate(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public Coordinate(double latitude, double longitude, int height) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.height = height;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	@Override
	public String toString() {
		return "latitude=" + latitude + ", longitude=" + longitude + ", height = " + height;
	}
	
	public String getMapKey(){
		return latitude + "_" + longitude;
	}
}

