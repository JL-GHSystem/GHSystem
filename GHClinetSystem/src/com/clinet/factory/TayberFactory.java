package com.clinet.factory;

import com.clinet.config.TayberFactoryConfig;
import com.clinet.controller.TayberController;

public class TayberFactory {
	
	public static TayberController create() {
		TayberController t = new TayberController();
		t.getTayberModel().setTitle(TayberFactoryConfig.TITLE);
		t.getTayberModel().setX(TayberFactoryConfig.X);
		t.getTayberModel().setY(TayberFactoryConfig.Y);
		t.getTayberModel().setWidth(TayberFactoryConfig.WIDTH);
		t.getTayberModel().setHeight(TayberFactoryConfig.HEIGHT);
		t.bind();
		return t;
	}
	
	public static TayberController create(String title) {
		TayberController t = new TayberController();
		t.getTayberModel().setTitle(title);
		t.getTayberModel().setX(TayberFactoryConfig.X);
		t.getTayberModel().setY(TayberFactoryConfig.Y);
		t.getTayberModel().setWidth(TayberFactoryConfig.WIDTH);
		t.getTayberModel().setHeight(TayberFactoryConfig.HEIGHT);
		t.bind();
		return t;
	}

	public static TayberController create(int width, int height) {
		TayberController t = new TayberController();
		t.getTayberModel().setTitle(TayberFactoryConfig.TITLE);
		t.getTayberModel().setX(TayberFactoryConfig.X);
		t.getTayberModel().setY(TayberFactoryConfig.Y);
		t.getTayberModel().setWidth(width);
		t.getTayberModel().setHeight(height);
		t.bind();
		return t;
	}
	
	
}
