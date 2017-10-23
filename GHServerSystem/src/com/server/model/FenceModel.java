package com.server.model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class FenceModel {
	private String O_FENCEID;
	private String O_FENCENAME;
	private String O_FENCETYPE;
	private BigDecimal O_RADIUS;
	
	private ArrayList<FenceNodeModel> F_FENCENODES = new ArrayList<FenceNodeModel>();

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

	


	
		
}
