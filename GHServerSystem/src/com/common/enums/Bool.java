package com.common.enums;

public class Bool {
	public static final String Yes = "ÊÇ";
	public static final String No = "·ñ";

	public static String parseInt(int i) {
		// TODO Auto-generated method stub
		switch(i) {
		case 0:
			return No;
		case 1:
			return Yes;
		}
		return null;
	}
	
	public static String parseBool(boolean i) {
		// TODO Auto-generated method stub
		if(i) {
			return Yes;
		}
		else {
			return No;
		}
	}
	
	public static boolean parseString(String bool) {
		// TODO Auto-generated method stub
		switch(bool) {
		case No:
			return false;
		case Yes:
			return true;
		}
		return false;
	}
	
}
