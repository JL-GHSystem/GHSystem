package com.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import com.common.enums.Pagination;
import com.common.lib.Lib;
import com.server.iDao.IDepartmentDao;
import com.server.map.Map;
import com.server.model.DepartmentModel;
import com.server.support.ConnBean;
import com.server.support.Dao;

public class DepartmentDao extends Dao implements IDepartmentDao {

	@Override
	public DepartmentModel[] selectAllInTree() {
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
					departmentModel.setODEPARTNAME(rs.getString(1));
					departmentModel.setFDEPARTMENTTYPE(rs.getString(2));
					departmentModel.setOPARENTNAME(rs.getString(3));
					departmentModel.setODEPARTNAMEPATH(rs.getString(4));
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
	public ArrayList<DepartmentModel> select(Pagination page, DepartmentModel department) {
		// TODO Auto-generated method stub
		ArrayList<DepartmentModel> departmentModels = new ArrayList<DepartmentModel>();
		ConnBean cb = Dao.getConn();
		if(cb != null){
			Connection cn = cb.getConn();
			try {
				String sql = ex.page(ex.select("O_DEPARTID", "O_PARENTID", "O_DEPARTNAME", "O_PARENTNAME", 
						"O_DICTITEMNAME", "O_DEPARTCODE", "O_DEPARTNAMEPATH").count()
						.from(Map.DEPARTMENT_MAP)
						.leftJoin(Map.DICTITEM_MAP)
						.on(Map.DEPARTMENT_MAP, "O_DEPARTTYPE", Map.DICTITEM_MAP, "O_DICTITEMID")
						.whereIn("O_DICTITEMNAME", 2)
						.and().like("O_DEPARTNAME", department.getODEPARTNAME())
						.and().like("O_PARENTNAME", department.getOPARENTNAME()).orderBy("O_DEPARTCODE").end(), page);
				
				//DTO操作
				PreparedStatement pst = cn.prepareStatement(sql);
				pst.setString(1, "公司");
				pst.setString(2, "调度");
				
				ResultSet rs = pst.executeQuery();
				while(rs.next()) 
				{
					DepartmentModel departmentModel = new DepartmentModel();
					departmentModel.setODEPARTID(rs.getString(1));
					departmentModel.setOPARENTID(rs.getString(2));
					departmentModel.setODEPARTNAME(rs.getString(3));
					departmentModel.setOPARENTNAME(rs.getString(4));
					departmentModel.setFDEPARTMENTTYPE(rs.getString(5));
					departmentModel.setODEPARTCODE(rs.getString(6));
					departmentModel.setODEPARTNAMEPATH(rs.getString(7));
					page.setRecords(rs.getInt(8));
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

	@Override
	public ArrayList<DepartmentModel> select() {
		// TODO Auto-generated method stub
		ArrayList<DepartmentModel> departmentModels = new ArrayList<DepartmentModel>();
		ConnBean cb = Dao.getConn();
		if(cb != null){
			Connection cn = cb.getConn();
			try {
				String sql = ex.select("O_DEPARTID", "O_PARENTID", "O_DEPARTCODE", "O_DEPARTNAME", "O_DICTITEMNAME", "O_DEPARTNAMEPATH")
						.from(Map.DEPARTMENT_MAP)
						.leftJoin(Map.DICTITEM_MAP)
						.on(Map.DEPARTMENT_MAP, "O_DEPARTTYPE", Map.DICTITEM_MAP, "O_DICTITEMID")
						.whereIn("O_DICTITEMNAME", 2).orderBy("O_DEPARTSORTID").end();
				
				//DTO操作
				PreparedStatement pst = cn.prepareStatement(sql);
				pst.setString(1, "公司");
				pst.setString(2, "调度");
				
				ResultSet rs = pst.executeQuery();
				while(rs.next()) 
				{
					DepartmentModel departmentModel = new DepartmentModel();
					departmentModel.setODEPARTID(rs.getString(1));
					departmentModel.setOPARENTID(rs.getString(2));
					departmentModel.setODEPARTCODE(rs.getString(3));
					departmentModel.setODEPARTNAME(rs.getString(4));
					departmentModel.setFDEPARTMENTTYPE(rs.getString(5));
					departmentModel.setODEPARTNAMEPATH(rs.getString(6));
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
	
	@Override
	public boolean insert(DepartmentModel departmentModel) {
		// TODO Auto-generated method stub
		if(Lib.isEmpty(departmentModel.getODEPARTNAME())) {
			System.out.println("DepartmentDao： 部门名不能为空，无法插入数据");
			return false;
		}
		else {
			boolean isSuccess = false;
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			departmentModel.setODEPARTID(uuid);
			ConnBean cb = Dao.getConn();
			if(cb != null){
				Connection cn = cb.getConn();
				try {
					//DTO操作
					PreparedStatement pst = cn.prepareStatement(
						ex.insert(Map.DEPARTMENT_MAP, "O_DEPARTID", "O_PARENTID", 
								"O_DEPARTTYPE", "O_DEPARTNAME", "O_PARENTNAME", 
								"O_DEPARTCODE", "O_DEPARTNAMEPATH")
						.values(7).end());
					
					pst.setString(1, departmentModel.getODEPARTID());
					pst.setString(2, departmentModel.getOPARENTID());
					pst.setString(3, departmentModel.getODEPARTTYPE());
					pst.setString(4, departmentModel.getODEPARTNAME());
					pst.setString(5, departmentModel.getOPARENTNAME());
					pst.setString(6, departmentModel.getODEPARTCODE());
					pst.setString(7, departmentModel.getODEPARTNAMEPATH());
					
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

	@Override
	public boolean delete(String[] ids) {
		// TODO Auto-generated method stub
		if(Lib.isEmpty(ids)) {
			System.out.println("DepartmentDao： id不能为空，无法删除数据");
			return false;
		}
		else {
			boolean isSuccess = false;
			ConnBean cb = Dao.getConn();
			if(cb != null){
				Connection cn = cb.getConn();
				try {
					//DTO操作
					PreparedStatement pst = cn.prepareStatement(
							ex.delete(Map.DEPARTMENT_MAP).where("O_DEPARTID = ?").end());
					
					for(int i=0; i<ids.length; i++) {
						pst.setString(1, ids[i]);
						pst.addBatch();
					}
					
					int[] is =pst.executeBatch();
					isSuccess = true;
					for(int i: is) {
						if(i <=0 && i!=-2) {
							isSuccess = false;
							break;
						}
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

	@Override
	public boolean update(DepartmentModel departmentModel) {
		// TODO Auto-generated method stub
		if(Lib.isEmpty(departmentModel.getODEPARTNAME())) {
			System.out.println("DepartmentDao： 部门名不能为空，无法插入数据");
			return false;
		}
		else {
			boolean isSuccess = false;
			ConnBean cb = Dao.getConn();
			if(cb != null){
				Connection cn = cb.getConn();
				try {
					//DTO操作
					PreparedStatement pst = cn.prepareStatement(
						ex.update(Map.DEPARTMENT_MAP)
						.set("O_PARENTID = ?",	"O_DEPARTTYPE = ?",
								"O_DEPARTNAME = ?", "O_PARENTNAME = ?", 
								"O_DEPARTCODE = ?", "O_DEPARTNAMEPATH = ?"
								)
						.where("O_DEPARTID = ?").end());
					
					pst.setString(1, departmentModel.getOPARENTID());
					pst.setString(2, departmentModel.getODEPARTTYPE());
					pst.setString(3, departmentModel.getODEPARTNAME());
					pst.setString(4, departmentModel.getOPARENTNAME());
					pst.setString(5, departmentModel.getODEPARTCODE());
					pst.setString(6, departmentModel.getODEPARTNAMEPATH());
					pst.setString(7, departmentModel.getODEPARTID());
					
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
