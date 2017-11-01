package com.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.common.enums.Pagination;
import com.common.lib.Lib;
import com.server.iDao.IRunDao;
import com.server.map.Map;
import com.server.model.RunModel;
import com.server.support.ConnBean;
import com.server.support.Dao;

public class RunDao extends Dao implements IRunDao {

	@Override
	public ArrayList<RunModel> select(Pagination page, RunModel runModel) {
		// TODO Auto-generated method stub
		ArrayList<RunModel> runModels = new ArrayList<RunModel>();
		ConnBean cb = Dao.getConn();
		if(cb != null){
			Connection cn = cb.getConn();
			try {
				//DTO²Ù×÷
				
				String sql1 = ex.select("O_DEPARTID", "O_VEHICLECODE", "O_DRIVERCODE",
						"O_DEPARTNAME", "O_VEHICLENAME", "O_DRIVERNAME",
						"O_DATE", "O_BC", "O_TC", "O_DIRECTION", "O_TIME", "O_ENDTIME",
						"O_CREWCODE", "O_CREWNAME",
						"O_CREWCODE2", "O_CREWNAME2",
						"O_CREWCODE3", "O_CREWNAME3").count()
						.from(Map.RUN_MAP)
						.whereIn("O_DIRECTION", runModel.getS_DIRECTION().length)
						.and().like("O_DRIVERNAME", runModel.getF_DRIVERNAME())
						.and().like("O_VEHICLECODE", runModel.getO_VEHICLECODE())
						.and().like("O_DATE", runModel.getO_DATE())
						.end();

				if(Lib.istEmpty(runModel.getO_DEPARTID())) {
					sql1 += ex.and("O_DEPARTID = ?").end();
				}
				if(runModel.getO_BC() != -1) {
					sql1 += ex.and("O_BC = ?").end();
				}
				if(runModel.getO_TC() != -1) {
					sql1 += ex.and("O_TC = ?").end();
				}
				
				String sql4 = ex.orderBy("O_DATE desc").end();
				
				String sql = ex.page(sql1 + sql4, page);
				
				PreparedStatement pst = cn.prepareStatement(sql);
				int i=0;
				for(i=1; i<=runModel.getS_DIRECTION().length; i++) {
					pst.setInt(i, runModel.getS_DIRECTION(i-1));
				}
				if(Lib.istEmpty(runModel.getO_DEPARTID())) {
					pst.setString(i, runModel.getO_DEPARTID());
				}
				if(runModel.getO_BC() != -1) {
					i++;
					pst.setInt(i, runModel.getO_BC());
				}
				if(runModel.getO_TC() != -1) {
					i++;
					pst.setInt(i, runModel.getO_TC());
				}
				ResultSet rs = pst.executeQuery();
				while(rs.next()) 
				{
					RunModel runModela = new RunModel();
					runModela.setO_DEPARTID(rs.getString(1));
					runModela.setO_VEHICLECODE(rs.getString(2));
					runModela.setO_DRIVERCODE(rs.getString(3));
					runModela.setF_DEPARTNAME(rs.getString(4));
					runModela.setF_VEHICLENAME(rs.getString(5));
					runModela.setF_DRIVERNAME(rs.getString(6));
					runModela.setO_DATE(rs.getString(7));
					runModela.setO_BC(rs.getInt(8));
					runModela.setO_TC(rs.getInt(9));
					runModela.setO_DIRECTION(rs.getInt(10));
					runModela.setO_TIME(rs.getString(11));
					runModela.setO_ENDTIME(rs.getString(12));
					int k;
					for(k=13; k<18; k=k+2) {
						if(Lib.istEmpty(rs.getString(k))) {
							runModela.getO_CREWCODE().add(rs.getString(k));
							runModela.getF_CREWNAME().add(rs.getString(k+1));
						}
					}
					page.setRecords(rs.getInt(k));
					runModels.add(runModela);
				}
				rs.close();
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Dao.freeConn(cb);
		}
		return runModels;
	}

}
