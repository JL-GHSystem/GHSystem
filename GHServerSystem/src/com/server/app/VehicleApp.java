package com.server.app;

import java.util.ArrayList;

import com.common.enums.Pagination;
import com.server.dao.VehicleDao;
import com.server.iDao.IVehicleDao;
import com.server.model.VehicleModel;

public class VehicleApp {
	private IVehicleDao vehicleDao = new VehicleDao();

	public VehicleModel[] getVehicle() {
		// TODO Auto-generated method stub
		return vehicleDao.selectAll();
	}
	
	public VehicleModel[] getTable(Pagination page) {
		// TODO Auto-generated method stub
		ArrayList<VehicleModel> vehicleModels = vehicleDao.select(page);
		VehicleModel[] vehicleModelss = new VehicleModel[vehicleModels.size()];
		vehicleModels.toArray(vehicleModelss);
		return vehicleModelss;
	}

	public boolean addVehicle(VehicleModel vehicleModel) {
		// TODO Auto-generated method stub
		boolean a = false;
		a = vehicleDao.insert(vehicleModel);
		return a;
	}
	
	
	
}
