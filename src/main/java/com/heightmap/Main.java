package com.heightmap;

import java.util.Iterator;
import java.util.LinkedList;

public class Main {
	public static void main(String[] args) {
		
		long start = System.currentTimeMillis();
		
		Coordinate coordinateStart = new Coordinate(44.391614, 38.519921);
		Coordinate coordinateEnd = new Coordinate(44.355355, 38.540778);
		
		Map map = new Map(coordinateStart, coordinateEnd, 3);
		map.drawConsoleMatrix();
		
		System.out.println();
		System.out.println(System.currentTimeMillis() - start + " ms");
	}
}