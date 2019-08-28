package com.heightmap;

public class Main {
	public static void main(String[] args) {
		Height height = new Height();
	
		//44.391614° N  38.519921
		double latitudeStart = 44.391614;
		double longitudeStart = 38.519921;
		
		//44.355355° N  38.540778°
		double latitudeEnd = 44.355355;
		double longitudeEnd = 38.540778;
		//44.382623 38.523321
		//44.373174 38.535141
		
		double[][] matrix = new double[100][100];
		
		for (double i = latitudeStart; i > latitudeEnd; i -= 0.0005) {
			for (double j = longitudeStart; j < longitudeEnd; j += 0.0005) {
				if(height.get(i,j) > 8	){
					System.out.print("X ");
				}else{
					System.out.print("_ ");
				}
				
			}
			System.out.println();
		}
		
		//44.382905° N  38.522909° E
		//44.382192° N  38.523933° E
		//00,000713     00,001024
	}
}