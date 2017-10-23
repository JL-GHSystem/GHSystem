package com.common.enums;

public class Pagination {

/*	private int total;*/
/*	private int pages;*/
	private int records;
	private int current;
	private int rows;
	
	public int getTotal() {
		if(records % rows == 0) {
			return records / rows;
		}
		else {
			return records / rows + 1;
		}
	}
	public int getRecords() {
		return records;
	}
	public void setRecords(int records) {
		this.records = records;
	}
	public int getCurrent() {
		return current;
	}
	public void setCurrent(int current) {
		this.current = current;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	
	
}
