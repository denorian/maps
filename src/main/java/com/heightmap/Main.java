package com.heightmap;

import com.heightmap.worker.CoordinateWorkerThread;

import java.util.Iterator;
import java.util.LinkedList;

public class Main {
	public static long start = System.currentTimeMillis();
	public static void main(String[] args) {
		
		
		Coordinate coordinateStart = new Coordinate(44.3901, 38.4999);
		Coordinate coordinateEnd = new Coordinate(44.3545, 38.5430);
		
		Map map = new Map(coordinateStart, coordinateEnd, 3);
		
		map.drawConsoleMatrix();
		
		System.out.println();
		System.out.println(System.currentTimeMillis() - start + " ms");
	}
}