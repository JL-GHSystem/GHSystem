package com.clinet.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ConnTest {
	private static URLConnection conn;
	
	public static void beforeSend(String url) {
		String result = "";
		try
	    {
	        URL realUrl = new URL(url);
	        //构造一个到指定URL的连接
	        conn = realUrl.openConnection();
	         
	        conn.setRequestProperty("accept", "*/*");
	        conn.setRequestProperty("connection", "Keep-Alive");
	        conn.setRequestProperty("user-agent",
	        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
	         
	        conn.setDoOutput(true);
	        conn.setDoInput(true);
		}catch (Exception e) {
			// TODO: handle exception
			
		}
	}

	public static void write() {
        try {
            //将JSON数据添加到输出流中
            OutputStream ot = conn.getOutputStream();            
            PrintWriter out = new PrintWriter(ot);
            
            out.flush();
        } catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
		}
	}
	
	public static void write(String s) {
        try {
            //将JSON数据添加到输出流中
            OutputStream ot = conn.getOutputStream();
            ot.write(s.getBytes());  
            
            PrintWriter out = new PrintWriter(ot);
            
            out.flush();
        } catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
		}
	}
	
	public static String read() {
        StringBuilder result = new StringBuilder();
        try {
            //接收服务器端发回的响应
            BufferedReader in = new BufferedReader(new InputStreamReader
                (conn.getInputStream() , "utf-8"));
                
            String line;
            while ((line = in.readLine())!= null)
            {
                result.append("\n" + line);
            }
        } catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
		}
        return result.toString();
	}

    public static String sendPost(String url)
    {
        String result = "";
        try
        {
        	beforeSend(url);
        	
            write();
        	
            result = read();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public static String sendPost(String url, Object param)
    {
        String result = "";
        try
        {
        	beforeSend(url);
        	
            write(JSONObject.fromObject(param).toString());
        	
            result = read();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
     
    public static void main(String args[])
    {
    	JSONObject jo = new JSONObject();
    	jo.put("a", "aaa");
    	
    	
        //使用本地Tomcat服务器，已经将应用a部署到了Tomcat容器中
        String s1 = ConnTest.sendPost("http://localhost:8080/GHServerSystem/ConnServlet", jo);
        System.out.println(s1);
    }
}
