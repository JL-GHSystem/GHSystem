package com.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.server.iDao.IDepartmentDao;
import com.server.map.Map;
import com.server.model.DepartmentModel;
import com.server.support.ConnBean;
import com.server.support.Dao;

public class DepartmentDao extends Dao implements IDepartmentDao {

	@Override
	public DepartmentModel[] selectAll() {
		// TODO Auto-generated method stub
		DepartmentModel[] departments = null;
		ArrayList<DepartmentModel> departmentModels = new ArrayList<DepartmentModel>();
		ConnBean cb = Dao.getConn();
		if(cb != null){
			Connection cn = cb.getConn();
			try {
				//DTO²Ù×÷
				PreparedStatement pst = cn.prepareStatement("select O_DEPARTNAME, O_DICTITEMNAME, O_PARENTNAME, O_DEPARTNAMEPATH " + 
						"from "+ Map.DEPARTMENT_MAP + " " + 
						"LEFT JOIN "+ Map.DICTITEM_MAP +" on "+ Map.DEPARTMENT_MAP + ".O_DEPARTTYPE = "+ Map.DICTITEM_MAP +".O_DICTITEMID");
				ResultSet rs = pst.executeQuery();
				while(rs.next()) 
				{
					DepartmentModel departmentModel = new DepartmentModel();
					departmentModel.setO_DEPARTNAME(rs.getString(1));
					departmentModel.setF_DEPARTMENTTYPE(rs.getString(2));
					departmentModel.setO_PARENTNAME(rs.getString(3));
					departmentModel.setO_DEPARTNAMEPATH(rs.getString(4));
					departmentModels.add(departmentModel);
				}
				rs.close();
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Dao.freeConn(cb);
		}
		if(departmentModels.isEmpty()) {
			return null;
		}
		departments = new DepartmentModel[departmentModels.size()];
		departmentModels.toArray(departments);
		return departments;
	}
	
}
