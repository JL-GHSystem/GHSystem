package com.server.model;

import java.util.ArrayList;
import java.util.Iterator;

import com.common.lib.Lib;

public class MenuModel {
	
	private String O_MENUID;
	private String O_MENUPREVID;
	private String O_MENUNAME;
	private String O_MENUPATH;
	private String O_MENUURL;
	private ArrayList<MenuModel> O_CHILD;
	
	public String getO_MENUID() {
		return O_MENUID;
	}
	public void setO_MENUID(String o_MENUID) {
		O_MENUID = o_MENUID;
	}
	public String getO_MENUPREVID() {
		return O_MENUPREVID;
	}
	public void setO_MENUPREVID(String o_MENUPREVID) {
		O_MENUPREVID = o_MENUPREVID;
	}
	public String getO_MENUNAME() {
		return O_MENUNAME;
	}
	public void setO_MENUNAME(String o_MENUNAME) {
		O_MENUNAME = o_MENUNAME;
	}
	public String getO_MENUPATH() {
		return O_MENUPATH;
	}
	public void setO_MENUPATH(String o_MENUPATH) {
		O_MENUPATH = o_MENUPATH;
	}
	public String getO_MENUURL() {
		return O_MENUURL;
	}
	public void setO_MENUURL(String o_MENUURL) {
		O_MENUURL = o_MENUURL;
	}
	public ArrayList<MenuModel> getO_CHILD() {
		return O_CHILD;
	}
	public MenuModel getO_CHILD(String o_MENUID) {
		if(Lib.istEmpty(o_MENUID)) {
			Iterator<MenuModel> i = O_CHILD.iterator();
			MenuModel a = null;
			while(i.hasNext()) {
				a = (MenuModel) i.next();
				if(o_MENUID.equals(a.getO_MENUID())) {
					return a;
				}
			}
		}
		return null;
	}
	public void setO_CHILD(ArrayList<MenuModel> o_CHILD) {
		O_CHILD = o_CHILD;
	}
	public void setO_CHILD(MenuModel o_CHILD) {
		O_CHILD.add(o_CHILD);
	}
}
