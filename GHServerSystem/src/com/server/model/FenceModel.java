package com.server.model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class FenceModel {
	private String O_FENCEID;
	private String O_FENCENAME;
	private String O_FENCETYPE;
	private String O_COMMIT;
	private BigDecimal O_RADIUS;
	
	private ArrayList<FenceNodeModel> F_FENCENODES = new ArrayList<FenceNodeModel>();
	
	private String F_DEPARTNAME;
	private String F_DEPARTID;
	private String F_DEPARTCODE;

	private int F_FENCENO;
	private String F_AREANAME;
	private int F_STATUS;
	private int F_PHBJ;
	private int F_SPEEDLIMT;
	private String F_STAYTIME;
	private String F_TIMECOST;
	private String F_TIMEINTERVAL;

	public String getO_FENCEID() {
		return O_FENCEID;
	}

	public void setO_FENCEID(String o_FENCEID) {
		O_FENCEID = o_FENCEID;
	}

	public String getO_FENCENAME() {
		return O_FENCENAME;
	}

	public void setO_FENCENAME(String o_FENCENAME) {
		O_FENCENAME = o_FENCENAME;
	}

	public String getO_FENCETYPE() {
		return O_FENCETYPE;
	}

	public void setO_FENCETYPE(String o_FENCETYPE) {
		O_FENCETYPE = o_FENCETYPE;
	}

	public String getO_COMMIT() {
		return O_COMMIT;
	}

	public void setO_COMMIT(String o_COMMIT) {
		O_COMMIT = o_COMMIT;
	}

	public ArrayList<FenceNodeModel> getF_FENCENODES() {
		return F_FENCENODES;
	}

	public void setF_FENCENODES(ArrayList<FenceNodeModel> f_FENCENODES) {
		F_FENCENODES = f_FENCENODES;
	}

	public BigDecimal getO_RADIUS() {
		return O_RADIUS;
	}

	public void setO_RADIUS(BigDecimal o_RADIUS) {
		O_RADIUS = o_RADIUS;
	}

	public String getF_DEPARTNAME() {
		return F_DEPARTNAME;
	}

	public void setF_DEPARTNAME(String f_DEPARTNAME) {
		F_DEPARTNAME = f_DEPARTNAME;
	}

	public String getF_DEPARTID() {
		return F_DEPARTID;
	}

	public void setF_DEPARTID(String f_DEPARTID) {
		F_DEPARTID = f_DEPARTID;
	}


	public String getF_AREANAME() {
		return F_AREANAME;
	}

	public void setF_AREANAME(String f_AREANAME) {
		F_AREANAME = f_AREANAME;
	}


	public String getF_STAYTIME() {
		return F_STAYTIME;
	}

	public void setF_STAYTIME(String f_STAYTIME) {
		F_STAYTIME = f_STAYTIME;
	}

	public String getF_TIMECOST() {
		return F_TIMECOST;
	}

	public void setF_TIMECOST(String f_TIMECOST) {
		F_TIMECOST = f_TIMECOST;
	}

	public String getF_TIMEINTERVAL() {
		return F_TIMEINTERVAL;
	}

	public void setF_TIMEINTERVAL(String f_TIMEINTERVAL) {
		F_TIMEINTERVAL = f_TIMEINTERVAL;
	}

	public int getF_FENCENO() {
		return F_FENCENO;
	}

	public void setF_FENCENO(int f_FENCENO) {
		F_FENCENO = f_FENCENO;
	}

	public int getF_STATUS() {
		return F_STATUS;
	}

	public void setF_STATUS(int f_STATUS) {
		F_STATUS = f_STATUS;
	}

	public int getF_PHBJ() {
		return F_PHBJ;
	}

	public void setF_PHBJ(int f_PHBJ) {
		F_PHBJ = f_PHBJ;
	}

	public int getF_SPEEDLIMT() {
		return F_SPEEDLIMT;
	}

	public void setF_SPEEDLIMT(int f_SPEEDLIMT) {
		F_SPEEDLIMT = f_SPEEDLIMT;
	}

	public String getF_DEPARTCODE() {
		return F_DEPARTCODE;
	}

	public void setF_DEPARTCODE(String f_DEPARTCODE) {
		F_DEPARTCODE = f_DEPARTCODE;
	}
		
}
