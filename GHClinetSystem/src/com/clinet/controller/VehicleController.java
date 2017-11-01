package com.clinet.controller;

import com.clinet.bean.VehicleBean;
import com.clinet.view.VehicleView;

public class VehicleController implements IController{
	private VehicleBean vehicleBean;
	private VehicleView vehicleView;
	
	public VehicleController() {
		// TODO Auto-generated constructor stub
		vehicleBean = new VehicleBean();
		bind();
	}
	
	@Override
	public void bind() {
		// TODO Auto-generated method stub
		vehicleView = new VehicleView(vehicleBean);
	}

	public VehicleBean getVehicleBean() {
		return vehicleBean;
	}

	public void setVehicleBean(VehicleBean vehicleBean) {
		this.vehicleBean = vehicleBean;
	}

	public VehicleView getVehicleView() {
		return vehicleView;
	}

	public void setVehicleView(VehicleView vehicleView) {
		this.vehicleView = vehicleView;
	}
	
	
	
}
