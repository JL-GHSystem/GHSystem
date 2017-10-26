package com.server.support;

import com.common.enums.Pagination;

public class DaoEx {
	private StringBuilder sql = new StringBuilder();
	
	public boolean clear() {
		try {
			this.sql = new StringBuilder();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public DaoEx insert(String tableName, String... colName) {
		sql.append("insert into ");
		sql.append(tableName);
		sql.append(" (");
        for (int i = 0; i < colName.length; i++) {
        	if(i == 0) {
            	sql.append(colName[i]);
        	}
        	else {
        		sql.append(", " + colName[i]);
        	}
        }
		sql.append(") ");
		return this;
	}
	
	public DaoEx values(int index) {
		sql.append("values ");
		sql.append("(");
        for (int i = 0; i < index; i++) {
        	if(i == 0) {
            	sql.append("?");
        	}
        	else {
        		sql.append(", ?");
        	}
        }
		sql.append(") ");
		return this;
	}
	
	public DaoEx delete(String tableName) {
		sql.append("delete from ");
		sql.append(tableName);
        sql.append(" ");
		return this;
	}

	public DaoEx update(String tableName) {
		// TODO Auto-generated method stub
		sql.append("update ");
		sql.append(tableName);
        sql.append(" ");
		return this;
	}

	public DaoEx set(String... options) {
		sql.append("set ");
        for (int i = 0; i < options.length; i++) {
        	if(i == 0) {
            	sql.append(options[i]);
        	}
        	else {
        		sql.append(", " + options[i]);
        	}
        }
        sql.append(" ");
		return this;
	}
	
	public DaoEx select(String... colName) {
		sql.append("select ");
        for (int i = 0; i < colName.length; i++) {
        	if(i == 0) {
            	sql.append(colName[i]);
        	}
        	else {
        		sql.append(", " + colName[i]);
        	}
        }
        sql.append(" ");
		return this;
	}
	
	public DaoEx from(String tableName) {
		sql.append("from ");
		sql.append(tableName);
        sql.append(" ");
		return this;
	}
	
	public DaoEx from(String tableName, String displayName) {
		sql.append("from ");
		sql.append(tableName);
        sql.append(" ");
		sql.append(displayName);
        sql.append(" ");
		return this;
	}
	
	public DaoEx leftJoin(String tableName) {
		sql.append("left join ");
		sql.append(tableName);
        sql.append(" ");
		return this;
	}
	
	public DaoEx on(String tableName1, String colName, String tableName2) {
		sql.append("on ");
		sql.append(tableName1 + "." + colName);
		sql.append(" = ");
		sql.append(tableName2 + "." + colName);
        sql.append(" ");
		return this;
	}

	public DaoEx on(String tableName1, String colName1, String tableName2, String colName2) {
		// TODO Auto-generated method stub
		sql.append("on ");
		sql.append(tableName1 + "." + colName1);
		sql.append(" = ");
		sql.append(tableName2 + "." + colName2);
        sql.append(" ");
		return this;
	}
	
	public DaoEx where() {
		sql.append("where ");
		return this;
	}
	
	public DaoEx where(String options) {
		sql.append("where ");
    	sql.append(options);
        sql.append(" ");
		return this;
	}

	public DaoEx and() {
		sql.append("and ");
		return this;
	}
	
	public DaoEx and(String options) {
		sql.append("and ");
    	sql.append(options);
        sql.append(" ");
		return this;
	}

	public DaoEx or(String options) {
		sql.append("or ");
    	sql.append(options);
        sql.append(" ");
		return this;
	}
	
	public DaoEx whereIn(String colName, int index, String... options) {
		sql.append("where ");
    	sql.append(colName);
    	sql.append(" in (");
        for (int i = 0; i < index; i++) {
        	if(i == 0) {
            	sql.append("?");
        	}
        	else {
        		sql.append(", ?");
        	}
        }
		sql.append(") ");
        for (int i = 0; i < options.length; i++) {
    		sql.append(" and " + options[i]);
        }
		return this;
	}

	public DaoEx exists (String ex) {
		sql.append("exists (");
		sql.append(ex);
		sql.append(") ");
		return this;
	}

	public DaoEx like(String colName, String search) {
		// TODO Auto-generated method stub
		sql.append(colName);
		sql.append(" like '%"+ search +"%' ");
		return this;
	}
	
	public DaoEx orderBy(String... colName) {
		sql.append("order by ");
        for (int i = 0; i < colName.length; i++) {
        	if(i == 0) {
            	sql.append(colName[i]);
        	}
        	else {
        		sql.append(", " + colName[i]);
        	}
        }
        sql.append(" ");
		return this;
	}
	
	public DaoEx count() {
		sql.append(", count(*) over() total ");
		return this;
	}
	
	public String page(String express, int start, int end) {
		sql.append("select * from ( ");
		sql.append("select A.*, ROWNUM RN from ( " + express);
		sql.append(") A where ROWNUM <= " + end);
        sql.append(") where RN > " + start);
		String e = sql.toString();
		clear();
		return e;
	}

	public String page(String express, Pagination page) {
		// TODO Auto-generated method stub
		return this.page(express, (page.getCurrent()-1) * page.getRows(), 
				(page.getCurrent()) * page.getRows());
	}
	
	public String end() {
		String e = sql.toString();
		clear();
		return e;
	}

}
