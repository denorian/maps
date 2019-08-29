package com.heightmap;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class Map {
	private double step;
	private int precision;
	private Coordinate coordinateStart;
	private Coordinate coordinateEnd;
	
	public Map(Coordinate coordinateStart, Coordinate coordinateEnd) {
		this.coordinateStart = coordinateStart;
		this.coordinateEnd = coordinateEnd;
		this.precision = 3;
		this.step = 0.001;
		
	}
	
	public Map(Coordinate coordinateStart, Coordinate coordinateEnd, int precision) {
		this.coordinateStart = coordinateStart;
		this.coordinateEnd = coordinateEnd;
		this.precision = precision;
		this.step = 1 / Math.pow(10, precision);
	}
	
	public LinkedList<Coordinate> createMatrix() {
		
		LinkedList<Coordinate> coordinateLinkedList = new LinkedList();
		Height height = new Height();
		
		double latitudeStart = customRound(Math.max(coordinateStart.getLatitude(), coordinateEnd.getLatitude()));
		double latitudeEnd = customRound(Math.min(coordinateStart.getLatitude(), coordinateEnd.getLatitude()));
		double longitudeStart = customRound(Math.min(coordinateStart.getLongitude(), coordinateEnd.getLongitude()));
		double longitudeEnd = customRound(Math.max(coordinateStart.getLongitude(), coordinateEnd.getLongitude()));
		
		for (double i = latitudeStart; i > latitudeEnd; i -= step) {
			for (double j = longitudeStart; j < longitudeEnd; j += step) {
				coordinateLinkedList.add(new Coordinate(customRound(i), customRound(j)));
			}
		}
		
		HashMap<String, Coordinate> coordinateMap = height.getMap(latitudeStart, latitudeEnd, longitudeStart, longitudeEnd, precision);
		
		Iterator iterator = coordinateLinkedList.iterator();
		while (iterator.hasNext()) {
			Coordinate coordinate = (Coordinate) iterator.next();
			String mapKey = coordinate.getMapKey();
			
			if (coordinateMap.containsKey(mapKey)) {
				coordinate.setHeight(coordinateMap.get(mapKey).getHeight());
			} else {
				coordinate.setHeight(height.get(coordinate.getLatitude(), coordinate.getLongitude()));
			}
		}
		
		return coordinateLinkedList;
	}
	
	
	private double customRound(double num) {
		return new BigDecimal(num).setScale(precision, RoundingMode.HALF_UP).doubleValue();
	}
	
	public static double customRound(double num, int precision) {
		return new BigDecimal(num).setScale(precision, RoundingMode.HALF_UP).doubleValue();
	}
}
