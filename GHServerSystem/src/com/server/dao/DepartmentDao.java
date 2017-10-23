package com.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.common.enums.Pagination;
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
				//DTO操作
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

	@Override
	public ArrayList<DepartmentModel> select(Pagination page) {
		// TODO Auto-generated method stub
		ArrayList<DepartmentModel> departmentModels = new ArrayList<DepartmentModel>();
		ConnBean cb = Dao.getConn();
		if(cb != null){
			Connection cn = cb.getConn();
			try {
				String sql = ex.page(ex.select("O_DEPARTID", "O_PARENTID", "O_DEPARTNAME", "O_PARENTNAME", 
						"O_DICTITEMNAME", "O_DEPARTNAMEPATH").count()
						.from(Map.DEPARTMENT_MAP)
						.leftJoin(Map.DICTITEM_MAP)
						.on(Map.DEPARTMENT_MAP, "O_DEPARTTYPE", Map.DICTITEM_MAP, "O_DICTITEMID")
						.whereIn("O_DICTITEMNAME", 2).orderBy("O_DEPARTSORTID").end(), page);
				
				//DTO操作
				PreparedStatement pst = cn.prepareStatement(sql);
				pst.setString(1, "公司");
				pst.setString(2, "调度");
				
				ResultSet rs = pst.executeQuery();
				while(rs.next()) 
				{
					DepartmentModel departmentModel = new DepartmentModel();
					departmentModel.setO_DEPARTID(rs.getString(1));
					departmentModel.setO_PARENTID(rs.getString(2));
					departmentModel.setO_DEPARTNAME(rs.getString(3));
					departmentModel.setO_PARENTNAME(rs.getString(4));
					departmentModel.setF_DEPARTMENTTYPE(rs.getString(5));
					departmentModel.setO_DEPARTNAMEPATH(rs.getString(6));
					page.setRecords(rs.getInt(7));
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
		return departmentModels;
	}
	
}
