package com.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.common.enums.Pagination;
import com.server.iDao.ILineDao;
import com.server.map.Map;
import com.server.model.LineModel;
import com.server.support.ConnBean;
import com.server.support.Dao;

public class LineDao extends Dao implements ILineDao {

	@Override
	public ArrayList<LineModel> select(Pagination page) {
		// TODO Auto-generated method stub
		ArrayList<LineModel> lineModels = new ArrayList<LineModel>();
		ConnBean cb = Dao.getConn();
		if(cb != null){
			Connection cn = cb.getConn();
			try {
				//DTO操作
				String sql = ex.page(ex.select("O_DEPARTID", "O_PARENTID", "O_DEPARTNAME", "O_PARENTNAME", 
						"O_DICTITEMNAME", "O_DEPARTNAMEPATH").count()
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
					lineModel.setO_DEPARTID(rs.getString(1));
					lineModel.setO_PARENTID(rs.getString(2));
					lineModel.setO_DEPARTNAME(rs.getString(3));
					lineModel.setO_PARENTNAME(rs.getString(4));
					lineModel.setF_DEPARTMENTTYPE(rs.getString(5));
					lineModel.setO_DEPARTNAMEPATH(rs.getString(6));
					page.setRecords(rs.getInt(7));
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

}
