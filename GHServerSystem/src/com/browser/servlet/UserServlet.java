package com.browser.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.lib.Lib;
import com.server.model.MenuModel;
import com.server.model.UserModel;
import com.server.service.UserService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class MenuServlet
 */

public class UserServlet extends HttpServlet implements IServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
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

		String type = request.getParameter("type");
		
		UserModel[] userModels;
		switch(type) {
			case "table":
				userModels = userService.getUser();
				/*
				JSONArray ja = new JSONArray();
				for(int i=0; i<menuModels.length; i++) {
					JSONObject jo = new JSONObject();
					jo.put("O_MENUID", menuModels[i].getO_MENUID());
					jo.put("O_MENUPEVID", menuModels[i].getO_MENUPEVID());
					jo.put("O_MENUNAME", menuModels[i].getO_MENUNAME());
					jo.put("F_MENUPREVNAME", menuModels[i].getF_MENUPREVNAME());
					jo.put("O_MENULEVEL", menuModels[i].getO_MENULEVEL());
					jo.put("O_MENUSORTID", menuModels[i].getO_MENUSORTID());
					jo.put("O_MENUURL", menuModels[i].getO_MENUURL());
					jo.put("O_MENUENABLED", menuModels[i].isO_MENUENABLED());
					ja.add(jo);
				}
				*/
				response.setContentType("application/json; charset=utf-8");
				response.getWriter().write(content(JSONArray.fromObject(userModels)));
				break;
			case "add":
				break;
			case "update":
				break;
			case "delete":
				break;
			default: break;
		}
		
	}

}
