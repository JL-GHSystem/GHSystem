package com.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.server.iDao.IUserGroupDao;
import com.server.map.Map;
import com.server.model.UserGroupModel;
import com.server.support.ConnBean;
import com.server.support.Dao;

public class UserGroupDao extends Dao implements IUserGroupDao{

	@Override
	public UserGroupModel selectByUser(String o_USERID) {
		// TODO Auto-generated method stub
		UserGroupModel userGroupModel = null;
		ConnBean cb = Dao.getConn();
		if(cb != null){
			Connection cn = cb.getConn();
			try {
				//DTO操作
				PreparedStatement pst = cn.prepareStatement("select "
						+ "TBL_NEW_CORE_USERGROUP.O_USERGROUPID, TBL_NEW_CORE_USERGROUP.O_USERGROUPNAME "
						+ "from " + Map.USERGROUP_MAP + " "
						+ "LEFT JOIN "+ Map.USERGROUP_USER_MAP +" on "+ Map.USERGROUP_USER_MAP +".O_USERGROUPID = "+ Map.USERGROUP_MAP +".O_USERGROUPID "
						+ "where O_USERID = ? ");
				pst.setString(1, o_USERID);
				ResultSet rs = pst.executeQuery();
				userGroupModel = new UserGroupModel();
				if(rs.next())
				{
					userGroupModel.setO_USERGROUPID(rs.getString(1));
					userGroupModel.setO_USERGROUPNAME(rs.getString(2));
				}
				int i=1;
				while(rs.next()) {
					System.out.println("UserGroupDao：发现一个用户属于多个组" + i);
				}
				rs.close();
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Dao.freeConn(cb);
		}
		return userGroupModel;
	}
	
}
