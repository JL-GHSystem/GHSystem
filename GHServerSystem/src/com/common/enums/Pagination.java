package com.common.enums;

public class Pagination {
	
	private int records = 0;
	private int current = 1;
	private int rows = 20;
	private int total = 0;

	public void setTotal() {
		if(records % rows == 0) {
			total = records / rows;
		}
		else {
			total = records / rows + 1;
		}
	}
	public int getTotal() {
		return total;
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
