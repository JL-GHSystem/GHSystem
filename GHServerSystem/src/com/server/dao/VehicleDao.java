package com.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import com.common.enums.Pagination;
import com.common.lib.Lib;
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
				//DTO操作
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

	@Override
	public ArrayList<VehicleModel> select(Pagination page) {
		// TODO Auto-generated method stub
		ArrayList<VehicleModel> vehicleModels = new ArrayList<VehicleModel>();
		ConnBean cb = Dao.getConn();
		if(cb != null){
			Connection cn = cb.getConn();
			try {
				String sql = ex.page(ex.select("O_VEHICLEID", "t1.O_PARENTID", "O_VEHICLECODE", "O_VEHICLEREGCODE", 
						"O_VEHICLEMODEL",  "t1.O_DEPARTNAME", "O_EQUIPMENTNO", "O_EQUIPMENTTYPE", "O_CAMERANUM").count()
						.from(Map.VEHICLE_MAP)
						.leftJoin(Map.DEPARTMENT_MAP + " t1")
						.on(Map.VEHICLE_MAP, "O_PARENTID", "t1", "O_DEPARTID")
						.orderBy("O_VEHICLECODE").end(), page);
				
				//DTO操作
				PreparedStatement pst = cn.prepareStatement(sql);
				
				ResultSet rs = pst.executeQuery();
				while(rs.next()) 
				{
					VehicleModel vehicleModel = new VehicleModel();
					vehicleModel.setO_VEHICLEID(rs.getString(1));
					vehicleModel.setO_PARENTID(rs.getString(2));
					vehicleModel.setO_VEHICLECODE(rs.getString(3));
					vehicleModel.setO_VEHICLEREGCODE(rs.getString(4));
					vehicleModel.setO_VEHICLEMODEL(rs.getString(5));
					vehicleModel.setF_DEPARTNAME(rs.getString(6));
					vehicleModel.setO_EQUIPMENTNO(rs.getString(7));
					vehicleModel.setO_EQUIPMENTTYPE(rs.getString(8));
					vehicleModel.setO_CAMERANUM(Integer.parseInt(rs.getString(9)));
					page.setRecords(rs.getInt(10));
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
		return vehicleModels;
	}

	@Override
	public boolean insert(VehicleModel vehicleModel) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		if(Lib.isEmpty(vehicleModel.getO_VEHICLECODE())) {
			System.out.println("VehicleDao： 车辆编号不能为空，无法插入数据");
			return false;
		}
		else {
			boolean isSuccess = false;
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			vehicleModel.setO_VEHICLEID(uuid);
			ConnBean cb = Dao.getConn();
			if(cb != null){
				Connection cn = cb.getConn();
				try {
					//DTO操作
					PreparedStatement pst = cn.prepareStatement(
						ex.insert(Map.VEHICLE_MAP, "O_VEHICLEID", "O_PARENTID", "O_VEHICLECODE", 
								"O_VEHICLEREGCODE", "O_VEHICLEMODEL", "O_EQUIPMENTNO", "O_EQUIPMENTTYPE", "O_CAMERANUM")
						.values(8).end());
					
					pst.setString(1, vehicleModel.getO_VEHICLEID());
					pst.setString(2, vehicleModel.getO_PARENTID());
					pst.setString(3, vehicleModel.getO_VEHICLECODE());
					pst.setString(4, vehicleModel.getO_VEHICLEREGCODE());
					pst.setString(5, vehicleModel.getO_VEHICLEMODEL());
					pst.setString(6, vehicleModel.getO_EQUIPMENTNO());
					pst.setString(7, vehicleModel.getO_EQUIPMENTTYPE());
					pst.setInt(8, vehicleModel.getO_CAMERANUM());
					
					int i=pst.executeUpdate();
					if(i > 0) {
						isSuccess = true;
					}
					pst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Dao.freeConn(cb);
			}
			return isSuccess;
		}
	}

}
