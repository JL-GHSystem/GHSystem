package com.server.service;

import com.server.app.VehicleApp;
import com.server.model.VehicleModel;

public class VehicleService {
	private VehicleApp vehicleApp = new VehicleApp();

	public VehicleModel[] getVehicle() {
		// TODO Auto-generated method stub
		return vehicleApp.getVehicle();
	}
}
