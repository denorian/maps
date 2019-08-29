package com.heightmap.services;

import com.heightmap.Height;

public class First {
	
	private static String DOMAIN = "http://www.heywhatsthat.com/";
	private static String RELATIVE_LINK = "bin/elev-landcover.cgi";
	
	public static String getQuery(double latitude, double longitude) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(DOMAIN);
		stringBuilder.append(RELATIVE_LINK);
		stringBuilder.append("?lat=");
		stringBuilder.append(latitude);
		stringBuilder.append("&lon=");
		stringBuilder.append(longitude);
		return stringBuilder.toString();
	}
	
	public static int getHeight(double latitude, double longitude) {
		String reponse = SimpleHttpClient.get(getQuery(latitude, longitude));
		String[] temp = reponse.trim().split(" ");
		int height = Integer.parseInt(temp[2]);
		if (height > Height.ERROR_VALUE) {
			return height;
		}
		return Height.ERROR_VALUE;
	}
}
