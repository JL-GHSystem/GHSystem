package com.test;

import net.sf.json.JSONObject;

public class JTest {
	
	public static void main(String[] args) {
		JSONObject jo = JSONObject.fromObject("{"
				+ "O_ID: 1,"
				+ "O_NAME: 2"
				+ "}");
		
		AModel a = (AModel) JSONObject.toBean(jo, AModel.class);
		System.out.println(a);
	}
	
	
}
