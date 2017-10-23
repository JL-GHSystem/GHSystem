package com.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.server.iDao.IVehicleDao;
import com.server.map.Map;
import com.server.model.VehicleModel;
import com.server.support.ConnBean;
import com.server.support.Dao;

public class VehicleDao extends Dao implements IVehicleDao {

	@Override
	public VehicleModel[] selectAll() {
		// TODO Auto-generated method stub
		VehicleModel[] vehicles = null;
		ArrayList<VehicleModel> vehicleModels = new ArrayList<VehicleModel>();
		ConnBean cb = Dao.getConn();
		if(cb != null){
			Connection cn = cb.getConn();
			try {
				//DTO²Ù×÷
				PreparedStatement pst = cn.prepareStatement("select "
						+ "O_VEHICLECODE, O_VEHICLEREGCODE, O_VEHICLEMODEL, "
						+ "O_PARENTNAME, O_EQUIPMENTNO, O_EQUIPMENTTYPE, "
						+ "O_CAMERANUM "
						+ "from " + Map.VEHICLE_MAP);
				ResultSet rs = pst.executeQuery();
				while(rs.next()) 
				{
					VehicleModel vehicleModel = new VehicleModel();
					vehicleModel.setO_VEHICLECODE(rs.getString(1));
					vehicleModel.setO_VEHICLEREGCODE(rs.getString(2));
					vehicleModel.setO_VEHICLEMODEL(rs.getString(3));
					vehicleModel.setO_PARENTNAME(rs.getString(4));
					vehicleModel.setO_EQUIPMENTNO(rs.getString(5));
					vehicleModel.setO_EQUIPMENTTYPE(rs.getString(6));
					vehicleModel.setO_CAMERANUM(rs.getInt(7));
					vehicleModels.add(vehicleModel);
				}
				rs.close();
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Dao.freeConn(cb);
		}
		if(vehicleModels.isEmpty()) {
			return null;
		}
		vehicles = new VehicleModel[vehicleModels.size()];
		vehicleModels.toArray(vehicles);
		return vehicles;
	}

}
