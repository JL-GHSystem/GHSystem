package com.server.iDao;

import java.util.ArrayList;

import com.common.enums.Pagination;
import com.server.model.VehicleModel;

public interface IVehicleDao {

	public VehicleModel[] selectAll();

	public ArrayList<VehicleModel> select(Pagination page);

	public boolean insert(VehicleModel vehicleModel);

}
