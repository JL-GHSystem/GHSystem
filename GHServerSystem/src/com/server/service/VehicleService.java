package com.server.service;

import com.common.enums.Pagination;
import com.server.app.VehicleApp;
import com.server.model.VehicleModel;

public class VehicleService {
	private VehicleApp vehicleApp = new VehicleApp();
	
	public VehicleModel[] getTable(Pagination page) {
		// TODO Auto-generated method stub
		return vehicleApp.getTable(page);
	}
	
	public boolean addVehicle(VehicleModel vehicleModel) {
		// TODO Auto-generated method stub
		return vehicleApp.addVehicle(vehicleModel);
	}

}
