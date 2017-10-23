package com.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import com.common.enums.Fence;
import com.common.enums.Pagination;
import com.common.lib.Lib;
import com.server.iDao.IFenceDao;
import com.server.map.Map;
import com.server.model.FenceModel;
import com.server.model.FenceNodeModel;
import com.server.support.ConnBean;
import com.server.support.Dao;

public class FenceDao extends Dao implements IFenceDao {

	@Override
	public ArrayList<FenceModel> select(Pagination page) {
		// TODO Auto-generated method stub
		ArrayList<FenceModel> fenceModels = new ArrayList<FenceModel>();
		ConnBean cb = Dao.getConn();
		if(cb != null){
			Connection cn = cb.getConn();
			try {
				String sql = ex.page(ex.select("O_FENCEID", "O_FENCENAME", "O_FENCETYPE").count()
						.from(Map.FENCE_MAP).end(), page);
				
				//DTO操作
				PreparedStatement pst = cn.prepareStatement(sql);
				
				ResultSet rs = pst.executeQuery();
				while(rs.next()) 
				{
					FenceModel fenceModel = new FenceModel();
					fenceModel.setO_FENCEID(rs.getString(1));
					fenceModel.setO_FENCENAME(rs.getString(2));
					fenceModel.setO_FENCETYPE(Fence.parseInt(rs.getInt(3)));
					page.setRecords(rs.getInt(4));
					fenceModels.add(fenceModel);
				}
				rs.close();
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Dao.freeConn(cb);
		}
		return fenceModels;
	}

	@Override
	public FenceModel selectByNode(FenceModel fenceModel) {
		// TODO Auto-generated method stub
		ConnBean cb = Dao.getConn();
		if(cb != null){
			Connection cn = cb.getConn();
			try {
				String sql = ex.select(Map.FENCE_NODE_MAP + ".O_FENCEID", "O_FENCETYPE", "O_LONGITUDE", "O_LATITUDE", "O_RADIUS")
						.from(Map.FENCE_NODE_MAP)
						.leftJoin(Map.FENCE_MAP).on(Map.FENCE_NODE_MAP, "O_FENCEID", Map.FENCE_MAP)
						.where(Map.FENCE_MAP+".O_FENCEID = ?")
						.orderBy("O_POINTNO").end();
				
				//DTO操作
				PreparedStatement pst = cn.prepareStatement(sql);
				pst.setString(1, fenceModel.getO_FENCEID());
				
				ResultSet rs = pst.executeQuery();
				while(rs.next()) 
				{
					FenceNodeModel fenceNodeModel = new FenceNodeModel();
					fenceModel.setO_FENCEID(rs.getString(1));
					fenceModel.setO_FENCETYPE(Fence.parseInt(rs.getInt(2)));
					fenceNodeModel.setO_LONGITUDE(rs.getBigDecimal(3));
					fenceNodeModel.setO_LATITUDE(rs.getBigDecimal(4));
					fenceModel.setO_RADIUS(rs.getBigDecimal(5));
					fenceModel.getF_FENCENODES().add(fenceNodeModel);
				}
				rs.close();
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Dao.freeConn(cb);
		}
		return fenceModel;
	}

	@Override
	public FenceModel select(FenceModel fenceModel) {
		// TODO Auto-generated method stub
		ConnBean cb = Dao.getConn();
		if(cb != null){
			Connection cn = cb.getConn();
			try {
				String sql = ex.select("O_FENCEID", "O_FENCENAME", "O_FENCETYPE", "O_RADIUS")
						.from(Map.FENCE_MAP)
						.where("O_FENCENAME = ?")
						.and("O_FENCETYPE = ?").end();
				
				//DTO操作
				PreparedStatement pst = cn.prepareStatement(sql);
				pst.setString(1, fenceModel.getO_FENCENAME());
				pst.setInt(2, Fence.toInt(fenceModel.getO_FENCETYPE()));

				
				ResultSet rs = pst.executeQuery();
				if(rs.next()) 
				{
					fenceModel.setO_FENCEID(rs.getString(1));
				}
				while(rs.next()) {
					System.out.println("FenceDao: 发现重名重类型的围栏");
				}
				rs.close();
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Dao.freeConn(cb);
		}
		return fenceModel;
	}
	@Override
	public boolean insert(FenceModel fenceModel) {
		// TODO Auto-generated method stub
		if(Lib.isEmpty(fenceModel.getO_FENCENAME()) || Lib.isEmpty(fenceModel.getO_FENCETYPE())) {
			System.out.println("FenceDao： 围栏名围栏类型不能为空，无法插入数据");
			return false;
		}
		else {
			boolean isSuccess = false;
			ConnBean cb = Dao.getConn();
			if(cb != null){
				Connection cn = cb.getConn();
				try {
					String sql = ex.insert(Map.FENCE_MAP, "O_FENCENAME", "O_FENCETYPE", "O_RADIUS")
							.values(3).end();

					
					DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
					df.setMaximumFractionDigits(15);
			        df.setMinimumFractionDigits(15);
					//DTO操作
					PreparedStatement pst = cn.prepareStatement(sql);
					pst.setString(1, fenceModel.getO_FENCENAME());
					pst.setInt(2, Fence.toInt(fenceModel.getO_FENCETYPE()));
					pst.setString(3, df.format(fenceModel.getO_RADIUS()));

					
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
	public boolean insert(String o_FENCEID, ArrayList<FenceNodeModel> f_FENCENODES) {
		// TODO Auto-generated method stub
		if(f_FENCENODES == null || f_FENCENODES.size() == 0) {
			System.out.println("Fence： 围栏数据不能为空，无法插入数据");
			return false;
		}
		else {
			boolean isSuccess = false;
			ConnBean cb = Dao.getConn();
			if(cb != null){
				Connection cn = cb.getConn();
				try {
					String sql = ex.insert(Map.FENCE_NODE_MAP, "O_FENCEID", "O_POINTNO", "O_LONGITUDE", "O_LATITUDE")
							.values(4).end();
					
					//DTO操作
					PreparedStatement pst = cn.prepareStatement(sql);
					Iterator<FenceNodeModel> it = f_FENCENODES.iterator();
					while(it.hasNext()) {
						FenceNodeModel f = it.next();
						pst.setString(1, o_FENCEID);
						pst.setInt(2, f.getO_POINTNO());
						pst.setBigDecimal(3, f.getO_LONGITUDE());
						pst.setBigDecimal(4, f.getO_LATITUDE());
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
	}

	@Override
	public boolean deleteByNode(String o_FENCEID) {
		// TODO Auto-generated method stub
		if(Lib.isEmpty(o_FENCEID)) {
			System.out.println("MenuDao： id不能为空，无法删除数据");
			return false;
		}
		else {
			boolean isSuccess = false;
			ConnBean cb = Dao.getConn();
			if(cb != null){
				Connection cn = cb.getConn();
				try {
					//DTO操作
					PreparedStatement pst = cn.prepareStatement("delete from "+ Map.FENCE_NODE_MAP +" "
							+ "where O_FENCEID = ?");
					
					pst.setString(1, o_FENCEID);
					
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
	public boolean delete(String o_FENCEID) {
		// TODO Auto-generated method stub
		if(Lib.isEmpty(o_FENCEID)) {
			System.out.println("MenuDao： id不能为空，无法删除数据");
			return false;
		}
		else {
			boolean isSuccess = false;
			ConnBean cb = Dao.getConn();
			if(cb != null){
				Connection cn = cb.getConn();
				try {
					//DTO操作
					PreparedStatement pst = cn.prepareStatement("delete from "+ Map.FENCE_MAP +" "
							+ "where O_FENCEID = ?");
					
					pst.setString(1, o_FENCEID);
					
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
	public boolean update(FenceModel fenceModel) {
		// TODO Auto-generated method stub
		if(Lib.isEmpty(fenceModel.getO_FENCENAME()) || Lib.isEmpty(fenceModel.getO_FENCETYPE())) {
			System.out.println("FenceDao： 围栏名围栏类型不能为空，无法插入数据");
			return false;
		}
		else {
			boolean isSuccess = false;
			ConnBean cb = Dao.getConn();
			if(cb != null){
				Connection cn = cb.getConn();
				try {
					String sql = ex.update(Map.FENCE_MAP)
							.set("O_FENCENAME = ?",
								"O_FENCETYPE = ?",
								"O_RADIUS = ?")
							.where("O_FENCEID = ?").end();
					
					DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
					df.setMaximumFractionDigits(15);
			        df.setMinimumFractionDigits(15);
					//DTO操作
					PreparedStatement pst = cn.prepareStatement(sql);
					pst.setString(1, fenceModel.getO_FENCENAME());
					pst.setInt(2, Fence.toInt(fenceModel.getO_FENCETYPE()));
					pst.setString(3, df.format(fenceModel.getO_RADIUS()));
					pst.setString(4, fenceModel.getO_FENCEID());

					
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
