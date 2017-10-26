package com.clinet.frame;

import com.clinet.controller.TayberController;
import com.clinet.factory.TayberFactory;

public class ClinetFrame {
	private TayberController tayberController;
	
	public ClinetFrame() {
		// TODO Auto-generated constructor stub
		tayberController = TayberFactory.create("国鸿车辆监控系统");
		tayberController.getTayberView().setSize(1366, 768);
		tayberController.show();
	}
	
}
