package com.clinet.application;


import com.clinet.frame.*;

public class Application {
	private static LoginFrame login;
		
	public static boolean showView() {
		System.out.println("进入登录窗口");
		login = new LoginFrame();
		return false;
	}
	
	public static void main(String[] args) {
		showView();
	}
}
