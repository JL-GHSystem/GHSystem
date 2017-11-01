package com.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
import com.server.model.SchemeModel;
import com.server.support.ConnBean;
import com.server.support.Dao;

public class FenceDao extends Dao implements IFenceDao {

	@Override
	public ArrayList<FenceModel> select(Pagination page,String search) {
		// TODO Auto-generated method stub
		ArrayList<FenceModel> fenceModels = new ArrayList<FenceModel>();
		ConnBean cb = Dao.getConn();
		if(cb != null){
			Connection cn = cb.getConn();
			try {
				String sql;
				PreparedStatement pst;
				if(Lib.istEmpty(search)) {
					sql = ex.page(ex.select("O_FENCEID", "O_FENCENAME", "O_FENCETYPE", "O_COMMIT").count()
							.from(Map.FENCE_MAP)
							.where().like("O_FENCENAME", search)
							.orderBy("O_FENCETYPE").end(), page);
					pst = cn.prepareStatement(sql);
				}
				else {
					sql = ex.page(ex.select("O_FENCEID", "O_FENCENAME", "O_FENCETYPE", "O_COMMIT").count()
							.from(Map.FENCE_MAP)
							.orderBy("O_FENCETYPE").end(), page);
					pst = cn.prepareStatement(sql);
				}
				
				//DTO操作
				
				ResultSet rs = pst.executeQuery();
				while(rs.next()) 
				{
					FenceModel fenceModel = new FenceModel();
					fenceModel.setO_FENCEID(rs.getString(1));
					fenceModel.setO_FENCENAME(rs.getString(2));
					fenceModel.setO_FENCETYPE(Fence.parseInt(rs.getInt(3)));
					fenceModel.setO_COMMIT(rs.getString(4));
					page.setRecords(rs.getInt(5));
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
	public ArrayList<FenceModel> selectByLine(Pagination page) {
		// TODO Auto-generated method stub
		ArrayList<FenceModel> fenceModels = new ArrayList<FenceModel>();
		ConnBean cb = Dao.getConn();
		if(cb != null){
			Connection cn = cb.getConn();
			try {
				String sql1 = "( " + ex.select("O_FENCEID", "O_FENCENAME", "O_FENCETYPE").from(Map.FENCE_MAP).end() + ") T1";
				String sql2 = "( " + ex.select("O_DEPARTID", "O_DEPARTNAME").from(Map.DEPARTMENT_MAP).end() + ") T2";
				
				String sql = ex.page(ex.select("T1.O_FENCEID", "T2.O_DEPARTID", "O_FENCENAME", "O_DEPARTNAME", "O_FENCETYPE").count()
						.from(Map.FENCE_LINE_MAP)
						.leftJoin(sql1)
						.on(Map.FENCE_LINE_MAP, "O_FENCEID", "T1")
						.leftJoin(sql2)
						.on(Map.FENCE_LINE_MAP, "O_DEPARTID", "T2").end(), page);
				
				//DTO操作
				PreparedStatement pst = cn.prepareStatement(sql);
				
				ResultSet rs = pst.executeQuery();
				while(rs.next()) 
				{
					FenceModel fenceModel = new FenceModel();
					fenceModel.setO_FENCEID(rs.getString(1));
					fenceModel.setF_DEPARTID(rs.getString(2));
					fenceModel.setO_FENCENAME(rs.getString(3));
					fenceModel.setF_DEPARTNAME(rs.getString(4));
					fenceModel.setO_FENCETYPE(Fence.parseInt(rs.getInt(5)));
					page.setRecords(rs.getInt(6));
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
	public ArrayList<FenceModel> selectByLine(Pagination page, String o_LineId) {
		// TODO Auto-generated method stub
		ArrayList<FenceModel> fenceModels = new ArrayList<FenceModel>();
		ConnBean cb = Dao.getConn();
		if(cb != null){
			Connection cn = cb.getConn();
			try {
				String sql1 = "( " + ex.select("O_FENCEID", "O_FENCENAME", "O_FENCETYPE").from(Map.FENCE_MAP).end() + ") T1";
				String sql2 = "( " + ex.select("O_DEPARTID", "O_DEPARTNAME").from(Map.DEPARTMENT_MAP).end() + ") T2";
				
				String sql = ex.page(ex.select("T1.O_FENCEID", "T2.O_DEPARTID", "O_FENCENAME", "O_DEPARTNAME", "O_FENCETYPE").count()
						.from(Map.FENCE_LINE_MAP)
						.leftJoin(sql1)
						.on(Map.FENCE_LINE_MAP, "O_FENCEID", "T1")
						.leftJoin(sql2)
						.on(Map.FENCE_LINE_MAP, "O_DEPARTID", "T2")
						.where("T2.O_DEPARTID = ?")
						.orderBy("O_FENCENO").end(), page);
				
				//DTO操作
				PreparedStatement pst = cn.prepareStatement(sql);
				pst.setString(1, o_LineId);
				
				ResultSet rs = pst.executeQuery();
				while(rs.next()) 
				{
					FenceModel fenceModel = new FenceModel();
					fenceModel.setO_FENCEID(rs.getString(1));
					fenceModel.setF_DEPARTID(rs.getString(2));
					fenceModel.setO_FENCENAME(rs.getString(3));
					fenceModel.setF_DEPARTNAME(rs.getString(4));
					fenceModel.setO_FENCETYPE(Fence.parseInt(rs.getInt(5)));
					page.setRecords(rs.getInt(6));
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
	public ArrayList<FenceModel> selectByLine(String o_LineCode) {
		// TODO Auto-generated method stub
		ArrayList<FenceModel> fenceModels = new ArrayList<FenceModel>();
		if(Lib.isEmpty(o_LineCode)) {
			return fenceModels;
		}
		ConnBean cb = Dao.getConn();
		if(cb != null){
			Connection cn = cb.getConn();
			try {
				String sql = ex.select("O_FENCENO", 
						"O_AREANAME", "O_FENCENAME", "O_FENCETYPE")
						.from(Map.FENCE_LINE_MAP, "t1")
						.leftJoin(Map.DEPARTMENT_MAP, "t2")
						.on("t1", "O_DEPARTID", "t2")
						.leftJoin(Map.FENCE_MAP, "t3")
						.on("t1", "O_FENCEID", "t3")
						.where("O_DEPARTCODE = ?")
						.orderBy("O_FENCENO").end();
				
				//DTO操作
				PreparedStatement pst = cn.prepareStatement(sql);
				pst.setString(1, o_LineCode);
				
				ResultSet rs = pst.executeQuery();
				while(rs.next()) 
				{
					FenceModel fenceModel = new FenceModel();
					fenceModel.setF_FENCENO(rs.getInt(1));
					fenceModel.setF_AREANAME(rs.getString(2));
					fenceModel.setO_FENCENAME(rs.getString(3));
					fenceModel.setO_FENCETYPE(Fence.parseInt(rs.getInt(4)));
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
	public ArrayList<FenceModel> selectExceptLine(Pagination page, String o_LineId, String o_Fencename) {
		// TODO Auto-generated method stub
		ArrayList<FenceModel> fenceModels = new ArrayList<FenceModel>();
		ConnBean cb = Dao.getConn();
		if(cb != null){
			Connection cn = cb.getConn();
			try {
				String sql1 = ex.select("1")
						.from(Map.FENCE_LINE_MAP + " t2")
						.where("t1.O_FENCEID = t2.O_FENCEID")
						.and("t2.O_DEPARTID = ?").end();
				
				String sql;
				if(Lib.istEmpty(o_Fencename)) {
					sql = ex.page(ex.select("O_FENCEID", "O_FENCENAME", "O_FENCETYPE").count()
							.from(Map.FENCE_MAP + " t1")
							.where("not").exists(sql1)
							.and().like("O_FENCENAME", o_Fencename).end(), page);
				}
				else {
					sql = ex.page(ex.select("O_FENCEID", "O_FENCENAME", "O_FENCETYPE").count()
							.from(Map.FENCE_MAP + " t1")
							.where("not").exists(sql1).end(), page);
				}
				
				System.out.println(sql);
				//DTO操作
				PreparedStatement pst = cn.prepareStatement(sql);
				pst.setString(1, o_LineId);
				
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
	public FenceModel selectByLineDetail(FenceModel fenceModel) {
		// TODO Auto-generated method stub
		ConnBean cb = Dao.getConn();
		if(cb != null){
			Connection cn = cb.getConn();
			try {
				String sql = ex.select("O_FENCEID", 
						"O_DEPARTID", "O_FENCENO", "O_AREANAME", 
						"O_STATUS", "O_PHBJ", "O_SPEEDLIMT", 
						"O_STAYTIME", "O_TIMEINTERVAL", "O_TIMECOST")
						.from(Map.FENCE_LINE_MAP)
						.where("O_FENCEID = ?")
						.and("O_DEPARTID = ?").end();
				
				//DTO操作
				PreparedStatement pst = cn.prepareStatement(sql);
				pst.setString(1, fenceModel.getO_FENCEID());
				pst.setString(2, fenceModel.getF_DEPARTID());
				
				ResultSet rs = pst.executeQuery();
				while(rs.next()) 
				{
					fenceModel.setO_FENCEID(rs.getString(1));
					fenceModel.setF_DEPARTID(rs.getString(2));
					fenceModel.setF_FENCENO(rs.getInt(3));
					fenceModel.setF_AREANAME(rs.getString(4));
					fenceModel.setF_STATUS(rs.getInt(5));
					fenceModel.setF_PHBJ(rs.getInt(6));
					fenceModel.setF_STAYTIME(rs.getString(7));
					fenceModel.setF_SPEEDLIMT(rs.getInt(8));
					fenceModel.setF_TIMEINTERVAL(rs.getString(9));
					fenceModel.setF_TIMECOST(rs.getString(10));				
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
	public FenceModel selectByNode(FenceModel fenceModel) {
		// TODO Auto-generated method stub
		ConnBean cb = Dao.getConn();
		if(cb != null){
			Connection cn = cb.getConn();
			try {
				String sql = ex.select(Map.FENCENODE_MAP + ".O_FENCEID", "O_FENCETYPE", "O_LONGITUDE", "O_LATITUDE", "O_RADIUS")
						.from(Map.FENCENODE_MAP)
						.leftJoin(Map.FENCE_MAP).on(Map.FENCENODE_MAP, "O_FENCEID", Map.FENCE_MAP)
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
	public ArrayList<FenceModel> selectByScheme(SchemeModel schemeModel) {
		ArrayList<FenceModel> fenceModels = new ArrayList<FenceModel>();
		ConnBean cb = Dao.getConn();
		if(cb != null){
			Connection cn = cb.getConn();
			try {
				String sql = ex.select("T1.O_FENCEID", "T1.O_DEPARTID", 
						"T2.O_DEPARTCODE", "T1.O_FENCENO", "T1.O_AREANAME", 
						"T3.O_FENCENAME", "T3.O_FENCETYPE")
						.from(Map.FENCE_LINE_MAP, "T1")
						.leftJoin(Map.DEPARTMENT_MAP, "T2")
						.on("T1", "O_DEPARTID", "T2")
						.leftJoin(Map.FENCE_MAP, "T3")
						.on("T1", "O_FENCEID", "T3")
						.whereIn("T1.O_FENCENO", schemeModel.getO_PROGRAMID().size())
						.and("T2.O_DEPARTCODE = ?")
						.orderByDecode("t1.O_FENCENO", schemeModel.getO_PROGRAMID().size()).end();
				
				//DTO操作
				PreparedStatement pst = cn.prepareStatement(sql);
				int i = 1;
				Iterator<Integer> it = schemeModel.getO_PROGRAMID().iterator();
				while(it.hasNext()) {
					pst.setInt(i, it.next());
					i++;
				}
				
				pst.setString(i, schemeModel.getO_DEPARTCODE());
				i++;
				
				it = schemeModel.getO_PROGRAMID().iterator();
				while(it.hasNext()) {
					pst.setInt(i, it.next());
					i++;
				}
				
				ResultSet rs = pst.executeQuery();
				while(rs.next()) 
				{
					FenceModel fenceModel = new FenceModel();
					fenceModel.setO_FENCEID(rs.getString(1));
					fenceModel.setF_DEPARTID(rs.getString(2));
					fenceModel.setF_DEPARTCODE(rs.getString(3));
					fenceModel.setF_FENCENO(rs.getInt(4));
					fenceModel.setF_AREANAME(rs.getString(5));
					fenceModel.setO_FENCENAME(rs.getString(6));
					fenceModel.setO_FENCETYPE(Fence.parseInt(rs.getInt(7)));
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
					String sql = ex.insert(Map.FENCE_MAP, "O_FENCENAME", "O_FENCETYPE", "O_RADIUS", "O_COMMIT")
							.values(4).end();

					
					DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
					df.setMaximumFractionDigits(15);
			        df.setMinimumFractionDigits(15);
					//DTO操作
					PreparedStatement pst = cn.prepareStatement(sql);
					pst.setString(1, fenceModel.getO_FENCENAME());
					pst.setInt(2, Fence.toInt(fenceModel.getO_FENCETYPE()));
					pst.setString(3, df.format(fenceModel.getO_RADIUS()));
					if(Lib.istEmpty(fenceModel.getO_COMMIT()))
					{
						pst.setString(4, fenceModel.getO_COMMIT());
					}
					else {
						pst.setNull(4, Types.INTEGER);
					}
					
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
					String sql = ex.insert(Map.FENCENODE_MAP, "O_FENCEID", "O_POINTNO", "O_LONGITUDE", "O_LATITUDE")
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
	public boolean insertByLine(FenceModel fenceModel) {
		// TODO Auto-generated method stub
		if(Lib.isEmpty(fenceModel.getO_FENCEID()) || Lib.isEmpty(fenceModel.getF_DEPARTID())) {
			System.out.println("FenceDao： id不能为空，无法插入数据");
			return false;
		}
		else {
			boolean isSuccess = false;
			ConnBean cb = Dao.getConn();
			if(cb != null){
				Connection cn = cb.getConn();
				try {
					String sql = ex.insert(Map.FENCE_LINE_MAP, "O_FENCEID", 
							"O_DEPARTID", "O_FENCENO", "O_AREANAME", 
							"O_STATUS", "O_PHBJ", "O_SPEEDLIMT", 
							"O_STAYTIME", "O_TIMECOST", "O_TIMEINTERVAL")
							.values(10).end();
					
					//DTO操作
					PreparedStatement pst = cn.prepareStatement(sql);
					pst.setString(1, fenceModel.getO_FENCEID());
					pst.setString(2, fenceModel.getF_DEPARTID());
					pst.setInt(3, fenceModel.getF_FENCENO());
					if(Lib.istEmpty(fenceModel.getF_AREANAME()))
					{
						pst.setString(4, fenceModel.getF_AREANAME());
					}
					else {
						pst.setNull(4, Types.INTEGER);
					}
					pst.setInt(5, fenceModel.getF_STATUS());
					pst.setInt(6, fenceModel.getF_PHBJ());
					pst.setInt(7, fenceModel.getF_SPEEDLIMT());
					if(Lib.istEmpty(fenceModel.getF_STAYTIME()))
					{
						pst.setString(8, fenceModel.getF_STAYTIME());
					}
					else {
						pst.setNull(8, Types.INTEGER);
					}
					if(Lib.istEmpty(fenceModel.getF_TIMECOST()))
					{
						pst.setString(9, fenceModel.getF_TIMECOST());
					}
					else {
						pst.setNull(9, Types.INTEGER);
					}
					if(Lib.istEmpty(fenceModel.getF_TIMEINTERVAL()))
					{
						pst.setString(10, fenceModel.getF_TIMEINTERVAL());
					}
					else {
						pst.setNull(10, Types.INTEGER);
					}

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
	public boolean deleteByNode(String o_FENCEID) {
		// TODO Auto-generated method stub
		if(Lib.isEmpty(o_FENCEID)) {
			System.out.println("FenceDao： id不能为空，无法删除数据");
			return false;
		}
		else {
			boolean isSuccess = false;
			ConnBean cb = Dao.getConn();
			if(cb != null){
				Connection cn = cb.getConn();
				try {
					//DTO操作
					PreparedStatement pst = cn.prepareStatement("delete from "+ Map.FENCENODE_MAP +" "
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

	@Override
	public boolean deleteByLine(String ids, String fid) {
		// TODO Auto-generated method stub
		if(Lib.isEmpty(ids) || Lib.isEmpty(fid)) {
			System.out.println("FenceDao： id不能为空，无法删除绑定关系");
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
							ex.delete(Map.FENCE_LINE_MAP).where("O_FENCEID = ?").and("O_DEPARTID = ?").end());
					
					pst.setString(1, ids);
					pst.setString(2, fid);
					
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
	public boolean updateByLine(FenceModel fenceModel) {
		// TODO Auto-generated method stub
		if(Lib.isEmpty(fenceModel.getO_FENCEID()) || Lib.isEmpty(fenceModel.getF_DEPARTID())) {
			System.out.println("FenceDao： id不能为空，无法插入数据");
			return false;
		}
		else {
			boolean isSuccess = false;
			ConnBean cb = Dao.getConn();
			if(cb != null){
				Connection cn = cb.getConn();
				try {
					String sql = ex.update(Map.FENCE_LINE_MAP)
							.set("O_FENCENO = ?",
								"O_AREANAME = ?",
								"O_STATUS = ?",
								"O_PHBJ = ?",
								"O_SPEEDLIMT = ?",
								"O_STAYTIME = ?",
								"O_TIMECOST = ?",
								"O_TIMEINTERVAL = ?")
							.where("O_FENCEID = ?")
							.and("O_DEPARTID = ?").end();
					
					DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
					df.setMaximumFractionDigits(15);
			        df.setMinimumFractionDigits(15);
					//DTO操作
					PreparedStatement pst = cn.prepareStatement(sql);
					pst.setInt(1, fenceModel.getF_FENCENO());
					if(Lib.istEmpty(fenceModel.getF_AREANAME()))
					{
						pst.setString(2, fenceModel.getF_AREANAME());
					}
					else {
						pst.setNull(2, Types.INTEGER);
					}
					pst.setInt(3, fenceModel.getF_STATUS());
					pst.setInt(4, fenceModel.getF_PHBJ());
					pst.setInt(5, fenceModel.getF_SPEEDLIMT());
					if(Lib.istEmpty(fenceModel.getF_STAYTIME()))
					{
						pst.setString(6, fenceModel.getF_STAYTIME());
					}
					else {
						pst.setNull(6, Types.INTEGER);
					}
					if(Lib.istEmpty(fenceModel.getF_TIMECOST()))
					{
						pst.setString(7, fenceModel.getF_TIMECOST());
					}
					else {
						pst.setNull(7, Types.INTEGER);
					}
					if(Lib.istEmpty(fenceModel.getF_TIMEINTERVAL()))
					{
						pst.setString(8, fenceModel.getF_TIMEINTERVAL());
					}
					else {
						pst.setNull(8, Types.INTEGER);
					}
					pst.setString(9, fenceModel.getO_FENCEID());
					pst.setString(10, fenceModel.getF_DEPARTID());

					
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
