package com.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.common.lib.Lib;
import com.server.iDao.IMenuDao;
import com.server.map.Map;
import com.server.model.MenuModel;
import com.server.support.ConnBean;
import com.server.support.Dao;

public class MenuDao extends Dao implements IMenuDao {

	@Override
	public MenuModel select(MenuModel menuModel) {
		// TODO Auto-generated method stub
		ConnBean cb = Dao.getConn();
		if(cb != null){
			Connection cn = cb.getConn();
			try {

				//DTO操作
				PreparedStatement pst = cn.prepareStatement("select "
						+ "O_MENUID "
						+ "from "+ Map.MENU_MAP +" "
						+ "where O_MENUNAME = ? and O_MENULEVEL = ? and O_MENUSORTID = ? and O_MENUURL  = ?");
				pst.setString(1, menuModel.getO_MENUNAME());
				pst.setInt(2, menuModel.getO_MENULEVEL());
				pst.setInt(3, menuModel.getO_MENUSORTID());
				if(Lib.isEmpty(menuModel.getO_MENUURL())) {
					pst.setString(4, "#");
				}
				else {
					pst.setString(4, menuModel.getO_MENUURL());
				}
				
				ResultSet rs = pst.executeQuery();
				if(rs.next()) 
				{
					menuModel.setO_MENUID(rs.getString(1));
				}
				while(rs.next()) {
					System.out.println("MenuDao: 发现重复数据");
				}
				rs.close();
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Dao.freeConn(cb);
		}
		return menuModel;
	}
	
	@Override
	public ArrayList<MenuModel> selectByGroup(String o_USERGROUPID) {
		// TODO Auto-generated method stub
		ArrayList<MenuModel> menuModels = new ArrayList<MenuModel>();
		ConnBean cb = Dao.getConn();
		if(cb != null){
			Connection cn = cb.getConn();
			try {
				//DTO操作
				PreparedStatement pst = cn.prepareStatement("select " + 
						Map.MENU_MAP + ".O_MENUID, " + 
						Map.MENU_MAP + ".O_MENUPEVID, " + 
						Map.MENU_MAP + ".O_MENUNAME, " + 
						Map.MENU_MAP + ".O_MENULEVEL, " + 
						Map.MENU_MAP + ".O_MENUSORTID, " + 
						Map.MENU_MAP + ".O_MENUURL, " + 
						Map.MENU_MAP + ".O_MENUENABLED " + 
						"from "+ Map.MENU_MAP + " " + 
						"LEFT JOIN "+ Map.MENU_USERGROUP_MAP +" on "+ Map.MENU_USERGROUP_MAP +".O_MENUID = "+ Map.MENU_MAP +".O_MENUID " + 
						"where O_USERGROUPID = ? ");
				pst.setString(1, o_USERGROUPID);
				ResultSet rs = pst.executeQuery();
				while(rs.next()) 
				{
					MenuModel menuModel = new MenuModel();
					menuModel.setO_MENUID(rs.getString(1));
					menuModel.setO_MENUPREVID(rs.getString(2));
					menuModel.setO_MENUNAME(rs.getString(3));
					menuModel.setO_MENULEVEL(rs.getInt(4));
					menuModel.setO_MENUSORTID(rs.getInt(5));
					String a = rs.getString(6);
					if(a.equals("#")){
						menuModel.setO_MENUURL("javaScript:void(0)");
					}
					else {
						menuModel.setO_MENUURL(a);
					}
					menuModel.setO_MENUENABLED(rs.getBoolean(7));
					menuModels.add(menuModel);
				}
				rs.close();
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Dao.freeConn(cb);
		}
		return menuModels;
	}

	@Override
	public ArrayList<MenuModel> selectByUser(String o_USERID) {
		// TODO Auto-generated method stub
		ArrayList<MenuModel> menuModels = new ArrayList<MenuModel>();
		ConnBean cb = Dao.getConn();
		if(cb != null){
			Connection cn = cb.getConn();
			try {
				//DTO操作
				PreparedStatement pst = cn.prepareStatement("select " + 
						Map.MENU_MAP + ".O_MENUID, " + 
						Map.MENU_MAP + ".O_MENUPEVID, " + 
						Map.MENU_MAP + ".O_MENUNAME, " + 
						Map.MENU_MAP + ".O_MENULEVEL, " + 
						Map.MENU_MAP + ".O_MENUSORTID, " + 
						Map.MENU_MAP + ".O_MENUURL, " + 
						Map.MENU_MAP + ".O_MENUENABLED " + 
						"from "+ Map.MENU_USER_MAP + " " + 
						"LEFT JOIN "+ Map.MENU_MAP +" on "+ Map.MENU_USER_MAP +".O_MENUID = "+ Map.MENU_MAP +".O_MENUID " + 
						"where O_USERID = ? ");
				pst.setString(1, o_USERID);
				ResultSet rs = pst.executeQuery();
				while(rs.next()) 
				{
					MenuModel menuModel = new MenuModel();
					menuModel.setO_MENUID(rs.getString(1));
					menuModel.setO_MENUPREVID(rs.getString(2));
					menuModel.setO_MENUNAME(rs.getString(3));
					menuModel.setO_MENULEVEL(rs.getInt(4));
					menuModel.setO_MENUSORTID(rs.getInt(5));
					String a = rs.getString(6);
					if(a.equals("#")){
						menuModel.setO_MENUURL("javaScript:void(0)");
					}
					else {
						menuModel.setO_MENUURL(a);
					}
					menuModel.setO_MENUENABLED(rs.getBoolean(7));
					menuModels.add(menuModel);
				}
				rs.close();
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Dao.freeConn(cb);
		}
		return menuModels;
	}
	
	@Override
	public MenuModel[] selectByGroupInTable() {
		// TODO Auto-generated method stub
		ArrayList<MenuModel> menuModels = new ArrayList<MenuModel>();
		ConnBean cb = Dao.getConn();
		if(cb != null){
			Connection cn = cb.getConn();
			try {
				//DTO操作
				PreparedStatement pst = cn.prepareStatement("select T1.O_MENUID, T1.O_MENUNAME, T2.O_MENUNAME, " + 
						"T1.O_MENULEVEL, T1.O_MENUSORTID, T1.O_MENUURL, T1.O_MENUENABLED " + 
						"from "+ Map.MENU_MAP +" t1 LEFT JOIN "+ Map.MENU_MAP +" t2 on t1.O_MENUPEVID = t2.O_MENUID " + 
						"ORDER BY T1.O_MENULEVEL, T1.O_MENUSORTID ");
				ResultSet rs = pst.executeQuery();
				while(rs.next()) 
				{
					MenuModel menuModel = new MenuModel();
					menuModel.setO_MENUID(rs.getString(1));
					menuModel.setO_MENUNAME(rs.getString(2));
					menuModel.setF_MENUPREVNAME(rs.getString(3));
					menuModel.setO_MENULEVEL(rs.getInt(4));
					menuModel.setO_MENUSORTID(rs.getInt(5));
					menuModel.setO_MENUURL(rs.getString(6));
					menuModel.setO_MENUENABLED(rs.getBoolean(7));
					menuModels.add(menuModel);
				}
				rs.close();
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Dao.freeConn(cb);
		}
		if(menuModels.isEmpty()) {
			return null;
		}
		MenuModel[] menu = new MenuModel[menuModels.size()];
		menuModels.toArray(menu);
		return menu;
	}

	@Override
	public boolean insert(MenuModel menuModel) {
		// TODO Auto-generated method stub

		if(Lib.isEmpty(menuModel.getO_MENUNAME())) {
			System.out.println("MenuDao： 菜单名不能为空，无法插入数据");
			return false;
		}
		else {
			boolean isSuccess = false;
			ConnBean cb = Dao.getConn();
			if(cb != null){
				Connection cn = cb.getConn();
				try {
					//DTO操作
					PreparedStatement pst = cn.prepareStatement("Insert into "+ Map.MENU_MAP +"("
							+ "O_MENUPEVID, O_MENUNAME, O_MENULEVEL, O_MENUSORTID, "
							+ "O_MENUURL, O_MENUENABLED) values("
							+ "?, ?, ?, ?, ?, ?)");
					
					System.out.println("Insert into "+ Map.MENU_MAP +"("
							+ "O_MENUPREVID, O_MENUNAME, O_MENULEVEL, O_MENUSORTID, "
							+ "O_MENUURL, O_MENUENABLED) values("
							+ "?, ?, ?, ?, ?, ?)");
					if(Lib.isEmpty(menuModel.getO_MENUPREVID())) {
						pst.setString(1, "-1");
					}
					else {
						pst.setString(1, menuModel.getO_MENUPREVID());
					}
	
					pst.setString(2, menuModel.getO_MENUNAME());
					pst.setInt(3, menuModel.getO_MENULEVEL());
					pst.setInt(4, menuModel.getO_MENUSORTID());
	
					if(Lib.isEmpty(menuModel.getO_MENUURL())) {
						pst.setString(5, "#");
					}
					else {
						pst.setString(5, menuModel.getO_MENUURL());
					}
	
					pst.setBoolean(6, menuModel.isO_MENUENABLED());
					
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
	public boolean insert(String o_MENUID, String o_USERID) {
		// TODO Auto-generated method stub
		if(Lib.isEmpty(o_MENUID) || Lib.isEmpty(o_USERID)) {
			System.out.println("MenuDao： id不能为空，无法插入数据");
			return false;
		}
		else {
			boolean isSuccess = false;
			ConnBean cb = Dao.getConn();
			if(cb != null){
				Connection cn = cb.getConn();
				try {
					//DTO操作
					PreparedStatement pst = cn.prepareStatement("Insert into "+ Map.MENU_USER_MAP +"("
							+ "O_USERID, O_MENUID ) values(?, ?)");
					
					pst.setString(1, o_USERID);
					pst.setString(2, o_MENUID);
					
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
