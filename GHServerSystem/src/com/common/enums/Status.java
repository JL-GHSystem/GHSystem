package com.common.enums;

public class Status {
	public static final String A = "运营";
	public static final String B = "专车";
	public static final String C = "事故";
	public static final String D = "保养";
	public static final String E = "全天下班";
	public static final String F = "加油";
	public static final String G = "吃饭";
	public static final String H = "回修理厂";
	public static final String I = "回停车场";
	public static final String J = "故障";
	public static final String K = "整车";
	public static final String L = "纠纷";
	public static final String M = "路堵";
	public static final String N = "路抛";
	public static final String O = "门检";
	public static final String P = "验车";
	
	public static String parseInt(int i) {
		switch(i) {
			case 0: 
				return Status.A;
			case 1: 
				return Status.B;
			case 2:
				return Status.C;
			case 3: 
				return Status.D;
			case 4: 
				return Status.E;
			case 5: 
				return Status.F;
			case 6:
				return Status.G;
			case 7: 
				return Status.H;
			case 8: 
				return Status.I;
			case 9: 
				return Status.J;
			case 10:
				return Status.K;
			case 11: 
				return Status.L;
			case 12: 
				return Status.M;
			case 13:
				return Status.N;
			case 14: 
				return Status.O;
			case 15: 
				return Status.P;
		}
		return null;
	}
	
	public static int toInt(String type) {
		switch(type) {
			case Status.A:
				return 0;
			case Status.B:
				return 1;
			case Status.C:
				return 2; 
			case Status.D:
				return 3; 
			case Status.E:
				return 4; 
			case Status.F:
				return 5;
			case Status.G:
				return 6; 
			case Status.H:
				return 7; 
			case Status.I:
				return 8;
			case Status.J:
				return 9;
			case Status.K:
				return 10;
			case Status.L:
				return 11;
			case Status.M:
				return 12;
			case Status.N:
				return 13;
			case Status.O:
				return 14;
			case Status.P:
				return 15;
		}
		return -1;
	}
}
