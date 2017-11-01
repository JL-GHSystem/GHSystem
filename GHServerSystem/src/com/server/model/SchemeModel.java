package com.server.model;

import java.util.ArrayList;
import java.util.Iterator;

import com.common.lib.Lib;

public class SchemeModel {
	private String O_DEPARTCODE;
	private ArrayList<Integer> O_PROGRAMID;
	private ArrayList<String> O_PROGRAMNOTE;
	private String O_PROGRAMNAME;
	private boolean O_BOPER;
	private boolean O_DIRECTION;
	private int O_STANDERKM;
	private int O_STANDERTIME;
	private String O_STATUS;

	private String F_DEPARTNAME;

	public String getO_DEPARTCODE() {
		return O_DEPARTCODE;
	}
	public void setO_DEPARTCODE(String o_DEPARTCODE) {
		O_DEPARTCODE = o_DEPARTCODE;
	}
	public String getO_PROGRAMID(boolean a) {
		StringBuilder sb = new StringBuilder();
		Iterator<Integer> it = O_PROGRAMID.iterator();
		if(it.hasNext()) {
			sb.append(it.next().intValue());
		}
		while(it.hasNext()) {
			sb.append("_" + it.next().intValue());
		}
		return sb.toString();
	}
	public ArrayList<Integer> getO_PROGRAMID() {
		return O_PROGRAMID;
	}
	public void setO_PROGRAMID(String o_PROGRAMID) {
		O_PROGRAMID = new ArrayList<Integer>();
		if(Lib.istEmpty(o_PROGRAMID)) {
			String[] str = o_PROGRAMID.split("_");
			for(int i=0; i<str.length; i++) {
				if(Lib.istEmpty(str[i])) {
					O_PROGRAMID.add(Integer.parseInt(str[i]));
				}
			}
		}
	}
	public void setO_PROGRAMID(int[] o_PROGRAMID) {
		O_PROGRAMID = new ArrayList<Integer>();
		for(int i=0; i<o_PROGRAMID.length; i++) {
			O_PROGRAMID.add(o_PROGRAMID[i]);
		}
	}
	public void setO_PROGRAMID(ArrayList<Integer> o_PROGRAMID) {
		O_PROGRAMID = o_PROGRAMID;
	}
	public String getO_PROGRAMNOTE(boolean a) {
		StringBuilder sb = new StringBuilder();
		Iterator<String> it = O_PROGRAMNOTE.iterator();
		if(it.hasNext()) {
			sb.append(it.next());
		}
		while(it.hasNext()) {
			sb.append("-" + it.next());
		}
		return sb.toString();
	}
	public ArrayList<String> getO_PROGRAMNOTE() {
		return O_PROGRAMNOTE;
	}
	public void setO_PROGRAMNOTE(ArrayList<String> o_PROGRAMNOTE) {
		O_PROGRAMNOTE = o_PROGRAMNOTE;
	}
	public void setO_PROGRAMNOTE(String[] o_PROGRAMNOTE) {
		O_PROGRAMNOTE = new ArrayList<String>();
		for(int i=0; i<o_PROGRAMNOTE.length; i++) {
			O_PROGRAMNOTE.add(o_PROGRAMNOTE[i]);
		}
	}
	public void setO_PROGRAMNOTE(String o_PROGRAMNOTE) {
		if(Lib.istEmpty(o_PROGRAMNOTE)) {
			String[] str = o_PROGRAMNOTE.split("-");
			O_PROGRAMNOTE = new ArrayList<String>();
			for(int i=0; i<str.length; i++) {
				O_PROGRAMNOTE.add(str[i]);
			}
		}
	}
	public String getO_PROGRAMNAME() {
		return O_PROGRAMNAME;
	}
	public void setO_PROGRAMNAME(String o_PROGRAMNAME) {
		O_PROGRAMNAME = o_PROGRAMNAME;
	}
	public boolean isO_BOPER() {
		return O_BOPER;
	}
	public void setO_BOPER(boolean o_BOPER) {
		O_BOPER = o_BOPER;
	}
	public boolean isO_DIRECTION() {
		return O_DIRECTION;
	}
	public void setO_DIRECTION(boolean o_DIRECTION) {
		O_DIRECTION = o_DIRECTION;
	}
	public int getO_STANDERKM() {
		return O_STANDERKM;
	}
	public void setO_STANDERKM(int o_STANDERKM) {
		O_STANDERKM = o_STANDERKM;
	}
	public int getO_STANDERTIME() {
		return O_STANDERTIME;
	}
	public void setO_STANDERTIME(int o_STANDERTIME) {
		O_STANDERTIME = o_STANDERTIME;
	}
	public String getO_STATUS() {
		return O_STATUS;
	}
	public void setO_STATUS(String o_STATUS) {
		O_STATUS = o_STATUS;
	}
	public String getF_DEPARTNAME() {
		return F_DEPARTNAME;
	}
	public void setF_DEPARTNAME(String f_DEPARTNAME) {
		F_DEPARTNAME = f_DEPARTNAME;
	}
	
}
