package com.clinet.controller;

import com.clinet.bean.TayberModel;
import com.clinet.view.TayberView;

public class TayberController {
	private TayberModel tayberModel;
	
	private TayberView tayberView;

	public TayberController() {
		// TODO Auto-generated constructor stub
		this.tayberModel = new TayberModel();
		this.tayberView = new TayberView();
	}
	public TayberController(TayberModel tayberModel) {
		this.setTayberModel(tayberModel);
	}
	
	public boolean bind() {
		try
		{
			tayberView.setBounds(tayberModel.getX(), tayberModel.getY(), tayberModel.getWidth(), tayberModel.getHeight());
			tayberView.setTitle(tayberModel.getTitle());
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	public void show() {
		tayberView.setVisible(true);
	}
	
	public void close() {
		tayberView.setVisible(false);
	}
	
	public TayberModel getTayberModel() {
		return tayberModel;
	}

	public void setTayberModel(TayberModel tayberModel) {
		this.tayberModel = tayberModel;
	}

	public TayberView getTayberView() {
		return tayberView;
	}

	public void setTayberView(TayberView tayberView) {
		this.tayberView = tayberView;
	}
	
	
}
