package com.server.app;

import com.server.dao.VehicleDao;
import com.server.iDao.IVehicleDao;
import com.server.model.VehicleModel;

public class VehicleApp {
	private IVehicleDao vehicleDao = new VehicleDao();

	public VehicleModel[] getVehicle() {
		// TODO Auto-generated method stub
		return vehicleDao.selectAll();
	}
	
	
	
}
