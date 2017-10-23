package com.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.common.lib.Lib;
import com.server.iDao.IUserGroupDao;
import com.server.map.Map;
import com.server.model.UserGroupModel;
import com.server.support.ConnBean;
import com.server.support.Dao;

public class UserGroupDao extends Dao implements IUserGroupDao{

	@Override
	public UserGroupModel select(UserGroupModel userGroupModel) {
		// TODO Auto-generated method stub
		ConnBean cb = Dao.getConn();
		if(cb != null){
			Connection cn = cb.getConn();
			try {
				//DTO操作
				PreparedStatement pst = cn.prepareStatement(
						ex.select("O_USERGROUPID").from(Map.USERGROUP_MAP)
						.where("O_USERGROUPNAME = ?").end());
				pst.setString(1, userGroupModel.getO_USERGROUPNAME());
				ResultSet rs = pst.executeQuery();
				if(rs.next())
				{
					userGroupModel.setO_USERGROUPID(rs.getString(1));
				}
				int i=1;
				while(rs.next()) {
					System.out.println("UserGroupDao：发现重名组" + i);
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
						+ Map.USERGROUP_MAP + ".O_USERGROUPID, "
						+ Map.USERGROUP_MAP + ".O_USERGROUPNAME "
						+ "from " + Map.USERGROUP_USER_MAP + " "
						+ "LEFT JOIN "+ Map.USERGROUP_MAP +" on "+ Map.USERGROUP_MAP +".O_USERGROUPID = "+ Map.USERGROUP_USER_MAP +".O_USERGROUPID "
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

	@Override
	public ArrayList<UserGroupModel> selectInTable() {
		// TODO Auto-generated method stub
		ArrayList<UserGroupModel> userGroupModels = new ArrayList<UserGroupModel>();
		ConnBean cb = Dao.getConn();
		if(cb != null){
			Connection cn = cb.getConn();
			try {
				//DTO操作
				PreparedStatement pst = cn.prepareStatement(
						ex.select("O_USERGROUPID","O_USERGROUPNAME")
						.from(Map.USERGROUP_MAP).end());
				ResultSet rs = pst.executeQuery();
				while(rs.next())
				{
					UserGroupModel userGroupModel = new UserGroupModel();
					userGroupModel.setO_USERGROUPID(rs.getString(1));
					userGroupModel.setO_USERGROUPNAME(rs.getString(2));
					userGroupModels.add(userGroupModel);
				}
				rs.close();
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Dao.freeConn(cb);
		}
		return userGroupModels;
	}

	@Override
	public boolean insert(UserGroupModel userGroupModel) {
		// TODO Auto-generated method stub
		if(Lib.isEmpty(userGroupModel.getO_USERGROUPNAME())) {
			System.out.println("UserGroupDao： 组名不能为空，无法插入数据");
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
							ex.insert(Map.USERGROUP_MAP, "O_USERGROUPNAME")
							.values(1).end());
					
					pst.setString(1, userGroupModel.getO_USERGROUPNAME());
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
	public boolean deleteInUser(String[] gids) {
		// TODO Auto-generated method stub
		if(Lib.isEmpty(gids)) {
			System.out.println("UserGroupDao： id不能为空，无法删除数据");
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
							ex.delete(Map.USERGROUP_USER_MAP).whereIn("O_USERGROUPID", gids.length).end());
					
					int j=1;
					for(String gid: gids) {
						pst.setString(j, gid);
						j++;
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
	public boolean deleteInMenu(String[] gids) {
		// TODO Auto-generated method stub
		if(Lib.isEmpty(gids)) {
			System.out.println("UserGroupDao： id不能为空，无法删除数据");
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
							ex.delete(Map.MENU_USERGROUP_MAP).whereIn("O_USERGROUPID", gids.length).end());
					
					int j=1;
					for(String gid: gids) {
						pst.setString(j, gid);
						j++;
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
	public boolean delete(String[] gids) {
		// TODO Auto-generated method stub
		if(Lib.isEmpty(gids)) {
			System.out.println("UserGroupDao： id不能为空，无法删除数据");
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
							ex.delete(Map.USERGROUP_MAP).whereIn("O_USERGROUPID", gids.length).end());
					
					int j=1;
					for(String gid: gids) {
						pst.setString(j, gid);
						j++;
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
}
