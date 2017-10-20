package com.common.lib;

public class Lib {
	
	public static boolean isEmpty(String s){
		if(null == s || s.length() == 0){
			return true;
		}
		else {
			s.trim();
			if ("".equals(s)){
				return true;
			}
		}
		return false;
	}
	public static boolean istEmpty(String s){
		return !Lib.isEmpty(s);
	}
}
