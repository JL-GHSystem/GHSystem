package com.common.enums;

public class Direction {
	public static final	String go = "去行";
	public static final	String back = "回行";
	
	public static boolean parseString(String direction) {
		switch(direction) {
		case go:
			return true;
		case back:
			return false;
		}
		return true;
	}
	
	public static String parseInt(int direction) {
		switch(direction) {
		case 1:
			return go;
		case 0:
			return back;
		}
		return go;
	}

	public static Object parseBool(boolean direction) {
		// TODO Auto-generated method stub
		if(direction) {
			return go;
		}
		else {
			return back;
		}
	}
	
}
