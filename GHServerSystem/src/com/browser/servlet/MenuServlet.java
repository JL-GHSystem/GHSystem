package com.browser.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.server.model.MenuModel;
import com.server.model.UserModel;
import com.server.service.MenuService;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class MenuServlet
 */

public class MenuServlet extends HttpServlet implements IServlet {
	private static final long serialVersionUID = 1L;
	private MenuService menuService = new MenuService();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuServlet() {
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

		HttpSession session = request.getSession();
		UserModel userModel = (UserModel) session.getAttribute("P_User");

		MenuModel[] menuModels;
		switch(type) {
			case "tree":
				menuModels = menuService.getMenu(userModel);
				
				response.setContentType("application/json; charset=utf-8");
				response.getWriter().write(content(JSONArray.fromObject(menuModels)));
				break;
			case "table":
				menuModels = menuService.getTable(userModel);
				
				response.setContentType("application/json; charset=utf-8");
				response.getWriter().write(content(JSONArray.fromObject(menuModels)));
				break;
			default: break;
		}
		
	}

}
