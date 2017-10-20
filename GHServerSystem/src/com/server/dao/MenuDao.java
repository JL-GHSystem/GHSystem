package com.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.server.iDao.IMenuDao;
import com.server.map.Map;
import com.server.model.MenuModel;
import com.server.support.ConnBean;
import com.server.support.Dao;

public class MenuDao extends Dao implements IMenuDao {

	@Override
	public MenuModel[] selectByGroup(String o_USERGROUPID) {
		// TODO Auto-generated method stub
		ArrayList<MenuModel> menuModels = new ArrayList<MenuModel>();
		ConnBean cb = Dao.getConn();
		if(cb != null){
			Connection cn = cb.getConn();
			try {
				//DTO²Ù×÷
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
		if(menuModels.isEmpty()) {
			return null;
		}
		return MenuModel.toTree(menuModels);
	}

	@Override
	public MenuModel[] selectByGroupInTable(String o_USERGROUPID) {
		// TODO Auto-generated method stub
		ArrayList<MenuModel> menuModels = new ArrayList<MenuModel>();
		ConnBean cb = Dao.getConn();
		if(cb != null){
			Connection cn = cb.getConn();
			try {
				//DTO²Ù×÷
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
}
