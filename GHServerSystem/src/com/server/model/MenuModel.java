package com.server.model;

import java.util.ArrayList;
import java.util.Iterator;

import com.common.lib.Lib;

public class MenuModel {
	
	private String O_MENUID;
	private String O_MENUPEVID;
	private String O_MENUNAME;
	private int O_MENULEVEL;
	private int O_MENUSORTID;
	private String O_MENUURL;
	private boolean O_MENUENABLED;

	private String F_MENUPREVNAME;
	
	private ArrayList<MenuModel> J_CHILD = new ArrayList<MenuModel>();
	
	public String getO_MENUID() {
		return O_MENUID;
	}
	public void setO_MENUID(String o_MENUID) {
		O_MENUID = o_MENUID;
	}
	public String getO_MENUPEVID() {
		return O_MENUPEVID;
	}
	public void setO_MENUPEVID(String o_MENUPEVID) {
		O_MENUPEVID = o_MENUPEVID;
	}
	public String getO_MENUNAME() {
		return O_MENUNAME;
	}
	public void setO_MENUNAME(String o_MENUNAME) {
		O_MENUNAME = o_MENUNAME;
	}
	public int getO_MENULEVEL() {
		return O_MENULEVEL;
	}
	public void setO_MENULEVEL(int o_MENULEVEL) {
		O_MENULEVEL = o_MENULEVEL;
	}
	public int getO_MENUSORTID() {
		return O_MENUSORTID;
	}
	public void setO_MENUSORTID(int o_MENUSORTID) {
		O_MENUSORTID = o_MENUSORTID;
	}
	public String getO_MENUURL() {
		return O_MENUURL;
	}
	public void setO_MENUURL(String o_MENUURL) {
		O_MENUURL = o_MENUURL;
	}
	public ArrayList<MenuModel> getJ_CHILD() {
		return J_CHILD;
	}
	public MenuModel search(String o_MENUID) {
		if(Lib.istEmpty(o_MENUID)) {
			Iterator<MenuModel> i = J_CHILD.iterator();
			MenuModel menu = null;
			while(i.hasNext()) {
				menu = (MenuModel) i.next();
				if(o_MENUID.equals(menu.getO_MENUID())) {
					return menu;
				}
				if(!menu.getJ_CHILD().isEmpty()) {
					MenuModel m = menu.search(o_MENUID);
					if(m!=null) {
						return m;
					}
				}
			}
		}
		return null;
	}
	public MenuModel getJ_CHILD(String o_MENUID) {
		if(Lib.istEmpty(o_MENUID)) {
			Iterator<MenuModel> i = J_CHILD.iterator();
			MenuModel menu = null;
			while(i.hasNext()) {
				menu = (MenuModel) i.next();
				if(o_MENUID.equals(menu.getO_MENUID())) {
					return menu;
				}
			}
		}
		return null;
	}
	public void setO_CHILD(ArrayList<MenuModel> j_CHILD) {
		J_CHILD = j_CHILD;
	}
	public void setO_CHILD(MenuModel j_CHILD) {
		Iterator<MenuModel> it = J_CHILD.iterator();
		int i=0;
		while(it.hasNext()) {
			MenuModel menuModel = it.next();
			if(menuModel.getO_MENUSORTID() > j_CHILD.getO_MENUSORTID()) {
				i = J_CHILD.indexOf(menuModel);
			}
		}
		J_CHILD.add(i, j_CHILD);
	}
	public boolean isO_MENUENABLED() {
		return O_MENUENABLED;
	}
	public void setO_MENUENABLED(boolean o_MENUENABLED) {
		O_MENUENABLED = o_MENUENABLED;
	}
	public String getF_MENUPREVNAME() {
		return F_MENUPREVNAME;
	}
	public void setF_MENUPREVNAME(String f_MENUPREVNAME) {
		F_MENUPREVNAME = f_MENUPREVNAME;
	}
	
	public static MenuModel[] toTree (ArrayList<MenuModel> menuModels) {
		
		ArrayList<MenuModel> head = new ArrayList<MenuModel>();
		
		Iterator<MenuModel> i = menuModels.iterator();
		Iterator<MenuModel> j = null;
		while(i.hasNext()) {
			MenuModel menuI = i.next();
			boolean a = true;
			j = menuModels.iterator();
			while(j.hasNext()) {
				MenuModel menuJ = j.next();
				if(menuI == menuJ) {
					
				}
				else if(menuI.getO_MENUPEVID().equals(menuJ.getO_MENUID())) {
					a = false;
					break;
				}
			}
			if(a) {
				head.add(menuI);
			}
		}
		
		if(!head.isEmpty()) {
			i = menuModels.iterator();
			while(i.hasNext()) {
				MenuModel menuI = i.next();
				j = menuModels.iterator();
				while(j.hasNext()) {
					MenuModel menuJ = j.next();
					if(menuI == menuJ) {
						
					}
					else if(menuI.getO_MENUPEVID().equals(menuJ.getO_MENUID())) {
						menuJ.setO_CHILD(menuI);
					}
					else if(menuJ.getO_MENUPEVID().equals(menuJ.getO_MENUID())) {
						menuI.setO_CHILD(menuJ);
					}
				}
			}
			
			int l = head.size();
			MenuModel[] s = new MenuModel[l];
			int n=0;
			Iterator<MenuModel> it = head.iterator();
			while(it.hasNext()) {
				s[n] = it.next();
				n++;
			}
			return s;
		}
		return null;
	}
}
