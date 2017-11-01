package com.server.model;

import java.util.ArrayList;

import com.common.lib.Lib;

public class RunModel {
	private String O_DEPARTID;
	private String O_VEHICLECODE;
	private String O_DRIVERCODE;
	private ArrayList<String> O_CREWCODE;
	
	private String O_DATE;
	private int O_BC = -1;
	private int O_TC = -1;
	private int O_DIRECTION = -1;
	private String O_TIME;
	private int O_KM = -1;
	private int O_YS = -1;
	private String O_NOTE;
	private String O_ID;
	private String O_ENDTIME;
	private String O_IMPORTDEPART;

	private String F_DEPARTNAME;
	private String F_VEHICLENAME;
	private String F_DRIVERNAME;
	private ArrayList<String> F_CREWNAME;

	private int[] S_DIRECTION;
	
	public String getO_DEPARTID() {
		return O_DEPARTID;
	}
	public void setO_DEPARTID(String o_DEPARTID) {
		O_DEPARTID = o_DEPARTID;
	}
	public String getO_VEHICLECODE() {
		return O_VEHICLECODE;
	}
	public void setO_VEHICLECODE(String o_VEHICLECODE) {
		O_VEHICLECODE = o_VEHICLECODE;
	}
	public String getO_DRIVERCODE() {
		return O_DRIVERCODE;
	}
	public void setO_DRIVERCODE(String o_DRIVERCODE) {
		O_DRIVERCODE = o_DRIVERCODE;
	}
	public ArrayList<String> getO_CREWCODE() {
		return O_CREWCODE;
	}
	public void setO_CREWCODE(ArrayList<String> o_CREWCODE) {
		O_CREWCODE = o_CREWCODE;
	}
	public String getO_DATE() {
		return O_DATE;
	}
	public void setO_DATE(String o_DATE) {
		O_DATE = o_DATE;
	}
	public String getO_TIME() {
		return O_TIME;
	}
	public void setO_TIME(String o_TIME) {
		O_TIME = o_TIME;
	}
	
	public String getO_NOTE() {
		return O_NOTE;
	}
	public void setO_NOTE(String o_NOTE) {
		O_NOTE = o_NOTE;
	}
	public String getO_ID() {
		return O_ID;
	}
	public void setO_ID(String o_ID) {
		O_ID = o_ID;
	}
	public String getO_ENDTIME() {
		return O_ENDTIME;
	}
	public void setO_ENDTIME(String o_ENDTIME) {
		O_ENDTIME = o_ENDTIME;
	}
	public String getO_IMPORTDEPART() {
		return O_IMPORTDEPART;
	}
	public void setO_IMPORTDEPART(String o_IMPORTDEPART) {
		O_IMPORTDEPART = o_IMPORTDEPART;
	}
	public String getF_DEPARTNAME() {
		return F_DEPARTNAME;
	}
	public void setF_DEPARTNAME(String f_DEPARTNAME) {
		F_DEPARTNAME = f_DEPARTNAME;
	}
	public String getF_VEHICLENAME() {
		return F_VEHICLENAME;
	}
	public void setF_VEHICLENAME(String f_VEHICLENAME) {
		F_VEHICLENAME = f_VEHICLENAME;
	}
	public String getF_DRIVERNAME() {
		return F_DRIVERNAME;
	}
	public void setF_DRIVERNAME(String f_DRIVERNAME) {
		F_DRIVERNAME = f_DRIVERNAME;
	}
	public ArrayList<String> getF_CREWNAME() {
		return F_CREWNAME;
	}
	public void setF_CREWNAME(ArrayList<String> f_CREWNAME) {
		F_CREWNAME = f_CREWNAME;
	}
	public int[] getS_DIRECTION() {
		return S_DIRECTION;
	}
	public int getS_DIRECTION(int index) {
		return S_DIRECTION[index];
	}
	public void setS_DIRECTION(int[] s_DIRECTION) {
		S_DIRECTION = s_DIRECTION;
	}
	public void setS_DIRECTION(int s_DIRECTION, int index) {
		S_DIRECTION[index] = s_DIRECTION;
	}
	public void setS_DIRECTION(String[] s_DIRECTION) {
		S_DIRECTION = new int[s_DIRECTION.length];
		for(int i=0; i<s_DIRECTION.length; i++) {
			if(Lib.istEmpty(s_DIRECTION[i])) {
				S_DIRECTION[i] = Integer.parseInt(s_DIRECTION[i]);
			}
		}
	}
	public int getO_BC() {
		return O_BC;
	}
	public void setO_BC(int o_BC) {
		O_BC = o_BC;
	}
	public int getO_TC() {
		return O_TC;
	}
	public void setO_TC(int o_TC) {
		O_TC = o_TC;
	}
	public int getO_DIRECTION() {
		return O_DIRECTION;
	}
	public void setO_DIRECTION(int o_DIRECTION) {
		O_DIRECTION = o_DIRECTION;
	}
	public int getO_KM() {
		return O_KM;
	}
	public void setO_KM(int o_KM) {
		O_KM = o_KM;
	}
	public int getO_YS() {
		return O_YS;
	}
	public void setO_YS(int o_YS) {
		O_YS = o_YS;
	}
	
}
