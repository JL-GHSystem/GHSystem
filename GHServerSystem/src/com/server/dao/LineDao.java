package com.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import com.common.enums.Pagination;
import com.common.lib.Lib;
import com.server.iDao.ILineDao;
import com.server.map.Map;
import com.server.model.LineModel;
import com.server.support.ConnBean;
import com.server.support.Dao;

public class LineDao extends Dao implements ILineDao {

	@Override
	public ArrayList<LineModel> select(Pagination page, LineModel lineModel) {
		// TODO Auto-generated method stub
		ArrayList<LineModel> lineModels = new ArrayList<LineModel>();
		ConnBean cb = Dao.getConn();
		if(cb != null){
			Connection cn = cb.getConn();
			try {
				//DTO操作
				String sql = ex.page(ex.select("O_DEPARTID", "O_PARENTID", "O_DEPARTNAME", "O_PARENTNAME", 
						"O_DICTITEMNAME", "O_DEPARTCODE", "O_DEPARTNAMEPATH").count()
						.from(Map.DEPARTMENT_MAP)
						.leftJoin(Map.DICTITEM_MAP)
						.on(Map.DEPARTMENT_MAP, "O_DEPARTTYPE", Map.DICTITEM_MAP, "O_DICTITEMID")
						.whereIn("O_DICTITEMNAME", 1)
						.and().like("O_DEPARTNAME", lineModel.getODEPARTNAME())
						.and().like("O_PARENTNAME", lineModel.getOPARENTNAME()).orderBy("O_DEPARTCODE").end(), page);

				//DTO操作
				PreparedStatement pst = cn.prepareStatement(sql);
				pst.setString(1, "线路");

				ResultSet rs = pst.executeQuery();
				while(rs.next()) 
				{
					LineModel lineModell = new LineModel();
					lineModell.setODEPARTID(rs.getString(1));
					lineModell.setOPARENTID(rs.getString(2));
					lineModell.setODEPARTNAME(rs.getString(3));
					lineModell.setOPARENTNAME(rs.getString(4));
					lineModell.setFDEPARTMENTTYPE(rs.getString(5));
					lineModell.setODEPARTCODE(rs.getString(6));
					lineModell.setODEPARTNAMEPATH(rs.getString(7));
					page.setRecords(rs.getInt(8));
					lineModels.add(lineModell);
				}
				rs.close();
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Dao.freeConn(cb);
		}
		return lineModels;
	}

	@Override
	public ArrayList<LineModel> selectAllInTable() {
		// TODO Auto-generated method stub
		ArrayList<LineModel> lineModels = new ArrayList<LineModel>();
		ConnBean cb = Dao.getConn();
		if(cb != null){
			Connection cn = cb.getConn();
			try {
				//DTO操作
				String sql = ex.select("O_DEPARTID", "O_PARENTID", "O_DEPARTNAME", "O_PARENTNAME", 
						"O_DICTITEMNAME", "O_DEPARTNAMEPATH")
						.from(Map.DEPARTMENT_MAP)
						.whereIn("O_DICTITEMNAME", 1).orderBy("O_DEPARTSORTID").end();
				
				//DTO操作
				PreparedStatement pst = cn.prepareStatement(sql);
				pst.setString(1, "线路");

				ResultSet rs = pst.executeQuery();
				while(rs.next()) 
				{
					LineModel lineModel = new LineModel();
					lineModel.setODEPARTID(rs.getString(1));
					lineModel.setOPARENTID(rs.getString(2));
					lineModel.setODEPARTNAME(rs.getString(3));
					lineModel.setOPARENTNAME(rs.getString(4));
					lineModel.setFDEPARTMENTTYPE(rs.getString(5));
					lineModel.setODEPARTNAMEPATH(rs.getString(6));
					lineModels.add(lineModel);
				}
				rs.close();
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Dao.freeConn(cb);
		}
		return lineModels;
	}

	@Override
	public ArrayList<LineModel> selectByScheme(Pagination page) {
		// TODO Auto-generated method stub
		ArrayList<LineModel> lineModels = new ArrayList<LineModel>();
		ConnBean cb = Dao.getConn();
		if(cb != null){
			Connection cn = cb.getConn();
			try {
				//DTO操作
				String sql = ex.page(ex.select("O_DEPARTID", "O_PARENTID", 
						"O_DEPARTNAME", "O_PARENTNAME", "O_DEPARTCODE").count()
						.from(Map.DEPARTMENT_MAP)
						.leftJoin(Map.DICTITEM_MAP)
						.on(Map.DEPARTMENT_MAP, "O_DEPARTTYPE", Map.DICTITEM_MAP, "O_DICTITEMID")
						.whereIn("O_DICTITEMNAME", 1).orderBy("O_DEPARTSORTID").end(), page);
				
				//DTO操作
				PreparedStatement pst = cn.prepareStatement(sql);
				pst.setString(1, "线路");

				ResultSet rs = pst.executeQuery();
				while(rs.next()) 
				{
					LineModel lineModel = new LineModel();
					lineModel.setODEPARTID(rs.getString(1));
					lineModel.setOPARENTID(rs.getString(2));
					lineModel.setODEPARTNAME(rs.getString(3));
					lineModel.setOPARENTNAME(rs.getString(4));
					lineModel.setODEPARTCODE(rs.getString(5));
					page.setRecords(rs.getInt(6));
					lineModels.add(lineModel);
				}
				rs.close();
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Dao.freeConn(cb);
		}
		return lineModels;
	}

	@Override
	public boolean insert(LineModel lineModel) {
		// TODO Auto-generated method stub
		if(Lib.isEmpty(lineModel.getODEPARTNAME())) {
			System.out.println("DepartmentDao： 部门名不能为空，无法插入数据");
			return false;
		}
		else {
			boolean isSuccess = false;
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			lineModel.setODEPARTID(uuid);
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
					
					pst.setString(1, lineModel.getODEPARTID());
					pst.setString(2, lineModel.getOPARENTID());
					pst.setString(3, lineModel.getODEPARTTYPE());
					pst.setString(4, lineModel.getODEPARTNAME());
					pst.setString(5, lineModel.getOPARENTNAME());
					pst.setString(6, lineModel.getODEPARTCODE());
					pst.setString(7, lineModel.getODEPARTNAMEPATH());
					
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
			System.out.println("LineDao： id不能为空，无法删除数据");
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
	public boolean update(LineModel lineModel) {
		// TODO Auto-generated method stub
		if(Lib.isEmpty(lineModel.getODEPARTNAME())) {
			System.out.println("LineDao： 线路名不能为空，无法更新数据");
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
					
					pst.setString(1, lineModel.getOPARENTID());
					pst.setString(2, lineModel.getODEPARTTYPE());
					pst.setString(3, lineModel.getODEPARTNAME());
					pst.setString(4, lineModel.getOPARENTNAME());
					pst.setString(5, lineModel.getODEPARTCODE());
					pst.setString(6, lineModel.getODEPARTNAMEPATH());
					pst.setString(7, lineModel.getODEPARTID());
					
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
