package com.browser.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class MenuServlet
 */

public class ServletBase extends HttpServlet implements IServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletBase() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	
	public static JSONObject toJSON(HttpServletRequest request){
        JSONObject JSONObject = new JSONObject();
        Map<String, String[]> pmap = request.getParameterMap();
		//通过循环遍历的方式获得key和value并set到jsonobject中
		Iterator<String> it = pmap.keySet().iterator();
		while (it.hasNext()) {
		       String key = it.next().toString();
		       String[] values = (String[])pmap.get(key);
		       JSONObject.put(key, values[0]);
		}
        return JSONObject;
	} 
}
