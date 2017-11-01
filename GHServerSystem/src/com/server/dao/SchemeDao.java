package com.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.common.lib.Lib;
import com.server.iDao.ISchemeDao;
import com.server.map.Map;
import com.server.model.SchemeModel;
import com.server.support.ConnBean;
import com.server.support.Dao;

public class SchemeDao extends Dao implements ISchemeDao{

	public ArrayList<SchemeModel> selectInLine(String o_LINECODE) {
		// TODO Auto-generated method stub
		ArrayList<SchemeModel> schemeModels = new ArrayList<SchemeModel>();
		ConnBean cb = Dao.getConn();
		if(cb != null){
			Connection cn = cb.getConn();
			try {
				String sql = ex.select("O_DEPARTCODE", "O_DEPARTNAME", "O_PROGRAMID", "O_PROGRAMNAME",
						"O_BOPER", "O_STATUS", "O_DIRECTION", "O_STANDERKM",
						"O_STANDERTIME")
						.from(Map.SCHEME_MAP)
						.where("O_DEPARTCODE = ?").end();
								
				//DTO操作
				PreparedStatement pst = cn.prepareStatement(sql);
				pst.setString(1, o_LINECODE);
				
				ResultSet rs = pst.executeQuery();
				while(rs.next()) 
				{
					SchemeModel schemeModel = new SchemeModel();
					schemeModel.setO_DEPARTCODE(rs.getString(1));
					schemeModel.setF_DEPARTNAME(rs.getString(2));
					schemeModel.setO_PROGRAMID(rs.getString(3));
					schemeModel.setO_PROGRAMNAME(rs.getString(4));
					schemeModel.setO_BOPER(rs.getBoolean(5));
					schemeModel.setO_STATUS(rs.getString(6));
					schemeModel.setO_DIRECTION(rs.getBoolean(7));
					schemeModel.setO_STANDERKM(rs.getInt(8));
					schemeModel.setO_STANDERTIME(rs.getInt(9));
					schemeModels.add(schemeModel);
				}
				rs.close();
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Dao.freeConn(cb);
		}
		return schemeModels;
	}

	@Override
	public boolean insert(SchemeModel schemeModel) {
		// TODO Auto-generated method stub
		if(Lib.isEmpty(schemeModel.getO_DEPARTCODE()) 
				|| Lib.isEmpty(schemeModel.getO_PROGRAMNAME())
				|| Lib.isEmpty(schemeModel.getF_DEPARTNAME())) {
			System.out.println("SchemeDao： 方案名或线路名或线路编号不能为空，无法插入数据");
			return false;
		}
		else {
			boolean isSuccess = false;
			ConnBean cb = Dao.getConn();
			if(cb != null){
				Connection cn = cb.getConn();
				try {
					String sql = ex.insert(Map.SCHEME_MAP, 
							"O_DEPARTCODE", "O_DEPARTNAME", "O_PROGRAMNAME", 
							"O_BOPER", "O_DIRECTION", "O_STANDERKM", 
							"O_STANDERTIME", "O_STATUS")
							.values(8).end();

					//DTO操作
					PreparedStatement pst = cn.prepareStatement(sql);
					pst.setString(1, schemeModel.getO_DEPARTCODE());
					pst.setString(2, schemeModel.getF_DEPARTNAME());
					pst.setString(3, schemeModel.getO_PROGRAMNAME());
					pst.setBoolean(4, schemeModel.isO_BOPER());
					pst.setBoolean(5, schemeModel.isO_DIRECTION());
					pst.setInt(6, schemeModel.getO_STANDERKM());
					pst.setInt(7, schemeModel.getO_STANDERTIME());
					pst.setString(8, schemeModel.getO_STATUS());
					
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
	public boolean updateFenceInScheme(SchemeModel schemeModel) {
		// TODO Auto-generated method stub
		if(Lib.isEmpty(schemeModel.getO_DEPARTCODE()) 
				|| Lib.isEmpty(schemeModel.getO_PROGRAMNAME())) {
			System.out.println("SchemeDao： 方案名或线路编号不能为空，无法插入数据");
			return false;
		}
		else {
			boolean isSuccess = false;
			ConnBean cb = Dao.getConn();
			if(cb != null){
				Connection cn = cb.getConn();
				try {
					String sql = ex.update(Map.SCHEME_MAP)
							.set("O_PROGRAMID = ?",
									"O_PROGRAMNOTE = ?")
							.where("O_DEPARTCODE = ?")
							.and("O_PROGRAMNAME = ?").end();

					//DTO操作
					PreparedStatement pst = cn.prepareStatement(sql);
					if(schemeModel.getO_PROGRAMID() == null) {
						pst.setNull(1, Types.INTEGER);
					}
					else {
						pst.setString(1, schemeModel.getO_PROGRAMID(true));
					}
					if(schemeModel.getO_PROGRAMNOTE() == null) {
						pst.setNull(2, Types.INTEGER);
					}
					else {
						pst.setString(2, schemeModel.getO_PROGRAMNOTE(true));
					}
					pst.setString(3, schemeModel.getO_DEPARTCODE());
					pst.setString(4, schemeModel.getO_PROGRAMNAME());
					
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
	public boolean delete(SchemeModel[] schemeModels) {
		// TODO Auto-generated method stub
		boolean isSuccess = false;
		ConnBean cb = Dao.getConn();
		if(cb != null){
			Connection cn = cb.getConn();
			try {
				//DTO操作
				String sql = ex.delete(Map.SCHEME_MAP)
						.where("O_DEPARTCODE = ?")
						.and("O_PROGRAMNAME = ?")
						.end();
				PreparedStatement pst = cn.prepareStatement(sql);
				for(int i=0; i<schemeModels.length; i++) {
					pst.setString(1, schemeModels[i].getO_DEPARTCODE());
					pst.setString(2, schemeModels[i].getO_PROGRAMNAME());
					pst.addBatch();
				}

				int is[] = pst.executeBatch();
				isSuccess = true;
				for(int i: is) {
					if(i <=0 && i !=-2) {
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

	@Override
	public boolean update(SchemeModel schemeModel) {
		// TODO Auto-generated method stub
		if(Lib.isEmpty(schemeModel.getO_DEPARTCODE()) 
				|| Lib.isEmpty(schemeModel.getO_PROGRAMNAME())) {
			System.out.println("SchemeDao： 方案名或线路编号不能为空，无法插入数据");
			return false;
		}
		else {
			boolean isSuccess = false;
			ConnBean cb = Dao.getConn();
			if(cb != null){
				Connection cn = cb.getConn();
				try {
					String sql = ex.update(Map.SCHEME_MAP)
							.set("O_PROGRAMNAME = ?",
									"O_BOPER = ?",
									"O_DIRECTION = ?",
									"O_STATUS = ?",
									"O_STANDERKM = ?",
									"O_STANDERTIME = ?")
							.where("O_DEPARTCODE = ?")
							.and("O_PROGRAMNAME = ?").end();

					//DTO操作
					PreparedStatement pst = cn.prepareStatement(sql);
					pst.setString(1, schemeModel.getO_PROGRAMNAME());
					pst.setBoolean(2, schemeModel.isO_BOPER());
					pst.setBoolean(3, schemeModel.isO_DIRECTION());
					pst.setString(4, schemeModel.getO_STATUS());
					pst.setInt(5, schemeModel.getO_STANDERKM());
					pst.setInt(6, schemeModel.getO_STANDERKM());
					
					pst.setString(7, schemeModel.getO_DEPARTCODE());
					pst.setString(8, schemeModel.getO_PROGRAMNAME());
					
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
