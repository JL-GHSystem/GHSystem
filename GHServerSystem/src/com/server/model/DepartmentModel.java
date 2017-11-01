package com.server.model;

import java.util.ArrayList;

public class DepartmentModel implements IModel{
	private String ODEPARTID;
	private String ODEPARTNAME;
	private String OPARENTID;
	private String OPARENTNAME;
	private String ODEPARTNAMEPATH;
	private String ODEPARTIDPATH;
	private String ODEPARTTYPE;
	private String ODEPARTCODE;
	private String ODEPARTSORTID;
	private String ODEPARTBRIEFCODE;
	
	private String FDEPARTMENTTYPE;
	private ArrayList<DepartmentModel> FDEPART = new ArrayList<DepartmentModel>();
	private ArrayList<LineModel> FLINE = new ArrayList<LineModel>();
	
	public String getODEPARTID() {
		return ODEPARTID;
	}
	public void setODEPARTID(String oDEPARTID) {
		ODEPARTID = oDEPARTID;
	}
	public String getODEPARTNAME() {
		return ODEPARTNAME;
	}
	public void setODEPARTNAME(String oDEPARTNAME) {
		ODEPARTNAME = oDEPARTNAME;
	}
	public String getOPARENTID() {
		return OPARENTID;
	}
	public void setOPARENTID(String oPARENTID) {
		OPARENTID = oPARENTID;
	}
	public String getOPARENTNAME() {
		return OPARENTNAME;
	}
	public void setOPARENTNAME(String oPARENTNAME) {
		OPARENTNAME = oPARENTNAME;
	}
	public String getODEPARTNAMEPATH() {
		return ODEPARTNAMEPATH;
	}
	public void setODEPARTNAMEPATH(String oDEPARTNAMEPATH) {
		ODEPARTNAMEPATH = oDEPARTNAMEPATH;
	}
	public String getODEPARTIDPATH() {
		return ODEPARTIDPATH;
	}
	public void setODEPARTIDPATH(String oDEPARTIDPATH) {
		ODEPARTIDPATH = oDEPARTIDPATH;
	}
	public String getODEPARTTYPE() {
		return ODEPARTTYPE;
	}
	public void setODEPARTTYPE(String oDEPARTTYPE) {
		ODEPARTTYPE = oDEPARTTYPE;
	}
	public String getODEPARTCODE() {
		return ODEPARTCODE;
	}
	public void setODEPARTCODE(String oDEPARTCODE) {
		ODEPARTCODE = oDEPARTCODE;
	}
	public String getODEPARTSORTID() {
		return ODEPARTSORTID;
	}
	public void setODEPARTSORTID(String oDEPARTSORTID) {
		ODEPARTSORTID = oDEPARTSORTID;
	}
	public String getODEPARTBRIEFCODE() {
		return ODEPARTBRIEFCODE;
	}
	public void setODEPARTBRIEFCODE(String oDEPARTBRIEFCODE) {
		ODEPARTBRIEFCODE = oDEPARTBRIEFCODE;
	}
	public String getFDEPARTMENTTYPE() {
		return FDEPARTMENTTYPE;
	}
	public void setFDEPARTMENTTYPE(String fDEPARTMENTTYPE) {
		FDEPARTMENTTYPE = fDEPARTMENTTYPE;
	}
	public ArrayList<LineModel> getFLINE() {
		return FLINE;
	}
	public void setFLINE(ArrayList<LineModel> fLINE) {
		FLINE = fLINE;
	}
	public ArrayList<DepartmentModel> getFDEPART() {
		return FDEPART;
	}
	public void setFDEPART(ArrayList<DepartmentModel> fDEPART) {
		FDEPART = fDEPART;
	}

}
