package com.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.*;

import com.server.support.DaoEx;

public class ConnectionTest {
    private static Connection con = null;// 创建一个数据库连接
    private static String path = "d:/SqlToOracle.txt";
    private static DaoEx ex = new DaoEx();
    
    private static String exists1 = ex.select("1").from("TBL_CORE_ELEFENCEITEM t2")
			.where("t1.O_FENCEID = t2.O_FENCEID").end();
    
    private static String exists2 = ex.select("1").from("TBL_CORE_ELEFENCE t2")
			.where("t1.O_FENCEID = t2.O_FENCEID").end();
    
    private static String exists3 = ex.select("1").from("TBL_CORE_ELEFENCE t2")
			.where("t1.O_AREAID = t2.O_FENCEID").end();
    
    private static String exists4 = ex.select("1").from("TBL_CORE_DEPARTINFO t3")
			.where("t1.O_DEPARTID = t3.O_DEPARTID").end();
    
    
    private static String sql1 = ex.select("O_FENCEID", "O_FENCENAME", "O_FENCETYPE", "O_RADIUS")
    						.from("TBL_CORE_ELEFENCE t1")
    						.where().exists(exists1)
    						.end();

    private static String sql2 = ex.select("O_FENCEID", "O_POINTNO", "O_LONGITUDE", "O_LATITUDE")
    						.from("TBL_CORE_ELEFENCEITEM t1")
    						.where().exists(exists2)
    						.end();

    private static String sql3 = ex.select("O_AREAID", "O_DEPARTID", "O_AREANO", "O_AREANAME", "O_STATUS", "O_YAWR",
    		"O_SPEEDLIMIT", "O_STRANDEDLIMIT", "O_INTIME", "O_OUTTIME")
    						.from("TBL_CORE_SCHEAREA t1")
    						.where().exists(exists3)
    						.and().exists(exists4)
    						.end();
    
    public static void main(String[] args){
        try
        {
        	
        	Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序
            System.out.println("开始尝试连接数据库！");
            String url = "jdbc:oracle:thin:@115.228.184.173:1521:thinklan";// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
            String user = "tds";// 用户名,系统默认的账户名
            String password = "tds";// 你安装时选设置的密码
            con = DriverManager.getConnection(url, user, password);// 获取连接
            System.out.println("连接成功！");
            
            PreparedStatement pst = con.prepareStatement(sql3);
            ResultSet rs = pst.executeQuery();
            
            File f = new File(path);
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            
/*            while (rs.next()) {
                bw.write("INSERT into TBL_NEW_CORE_ELEFENCE (O_FENCEID, O_FENCENAME, O_FENCETYPE, O_RADIUS) VALUES('"+ 
            rs.getString(1)+"', '"+ rs.getString(2) +"', '"+ rs.getString(3) +"', '"+ rs.getString(4) +"');\n");
            }*/
            while (rs.next()) {
	            bw.write("INSERT into TBL_NEW_CORE_ELEFENCE_LINE (O_FENCEID, O_DEPARTID, O_FENCENO, O_AREANAME, O_STATUS,"
	            		+ "O_PHBJ, O_SPEEDLIMT, O_STAYTIME, O_TIMECOST, O_TIMEINTERVAL) VALUES('"+ 
	        rs.getString(1)+"', '"+ rs.getString(2) +"', "+ rs.getInt(3) +", '"+ rs.getString(4) +"', "+ rs.getInt(5) +", "
	        		+ rs.getInt(6) +", '"+ rs.getString(7) +"', '"+ rs.getString(8) +"', '"+ rs.getString(9) + "', '"+ rs.getString(10) +"');\n");
	        }
            
            bw.close();
        	/*
        	Class.forName("com.mysql.jdbc.Driver");// 加载Oracle驱动程序
            System.out.println("开始尝试连接数据库！");
            String url = "jdbc:mysql://localhost:3306/tds";// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
            String user = "root";// 用户名,系统默认的账户名
            String password = "wsdrfgtyhuiokl";// 你安装时选设置的密码
            con = DriverManager.getConnection(url, user, password);// 获取连接
            System.out.println("连接成功！");
            String sql = "select * from TBL_CORE_USERINFO";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                System.out.println(rs.getString(2) + "\t" + rs.getString(4));
            }
        	*/
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
