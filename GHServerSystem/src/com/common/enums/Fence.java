package com.common.enums;

public class Fence {
	public static final String Circle = "圆形";
	public static final String Polyline = "线形";
	public static final String Polygon = "多边形";
	public static final String Actengle = "矩形";
	
	public static String parseInt(int i) {
		switch(i) {
			case 0: 
				return Fence.Polyline;
			case 1: 
				return Fence.Actengle;
			case 2:
				return Fence.Circle;
			case 3: 
				return Fence.Polygon;
		}
		return null;
	}
	
	public static int toInt(String type) {
		switch(type) {
		case Fence.Polyline: 
			return 0;
		case Fence.Actengle: 
			return 1;
		case Fence.Circle:
			return 2;
		case Fence.Polygon: 
			return 3;
		}
		return -1;
	}
}
