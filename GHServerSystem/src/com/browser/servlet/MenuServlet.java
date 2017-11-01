package com.browser.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.lib.Lib;
import com.server.model.MenuModel;
import com.server.model.UserModel;
import com.server.service.MenuService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class MenuServlet
 */

public class MenuServlet extends ServletBase implements IServlet {
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
		MenuModel menuModel = new MenuModel(); 
		switch(type) {
			case "tree":
				menuModels = menuService.getMenu(userModel);
				
				response.getWriter().write(content(JSONArray.fromObject(menuModels)));
				break;
			case "table":
				menuModels = menuService.getTable();
				
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
				
				response.setContentType("application/json; charset=utf-8");
				response.getWriter().write(content(ja));
				break;
			case "add":
				if(Lib.istEmpty(request.getParameter("O_MENUPEVID"))) {
					menuModel.setO_MENUPEVID(request.getParameter("O_MENUPEVID"));
				}
				if(Lib.istEmpty(request.getParameter("O_MENUNAME"))) {
					menuModel.setO_MENUNAME(request.getParameter("O_MENUNAME"));
				}
				if(Lib.istEmpty(request.getParameter("O_MENUURL"))) {
					menuModel.setO_MENUURL(request.getParameter("O_MENUURL"));
				}
				if(Lib.istEmpty(request.getParameter("O_MENULEVEL"))) {
					menuModel.setO_MENULEVEL(Integer.parseInt(request.getParameter("O_MENULEVEL")));
				}
				if(Lib.istEmpty(request.getParameter("O_MENUSORTID"))) {
					menuModel.setO_MENUSORTID(Integer.parseInt(request.getParameter("O_MENUSORTID")));
				}
				if("0".equals(request.getParameter("O_MENUENABLED"))) {
					menuModel.setO_MENUENABLED(false);
				}
				else {
					menuModel.setO_MENUENABLED(true);
				}
				boolean a = menuService.addMenu(menuModel, userModel);
				if(a) {
					response.setContentType("application/json; charset=utf-8");
					response.getWriter().write(success("创建成功"));
				} 
				else {
					response.setContentType("application/json; charset=utf-8");
					response.getWriter().write(error(2, "创建失败"));
				}
				break;
			case "update":
				if(Lib.istEmpty(request.getParameter("O_MENUID"))) {
					menuModel.setO_MENUID(request.getParameter("O_MENUID"));
				}
				if(Lib.istEmpty(request.getParameter("O_MENUPEVID"))) {
					menuModel.setO_MENUPEVID(request.getParameter("O_MENUPEVID"));
				}
				if(Lib.istEmpty(request.getParameter("O_MENUNAME"))) {
					menuModel.setO_MENUNAME(request.getParameter("O_MENUNAME"));
				}
				if(Lib.istEmpty(request.getParameter("O_MENUURL"))) {
					menuModel.setO_MENUURL(request.getParameter("O_MENUURL"));
				}
				if(Lib.istEmpty(request.getParameter("O_MENULEVEL"))) {
					menuModel.setO_MENULEVEL(Integer.parseInt(request.getParameter("O_MENULEVEL")));
				}
				if(Lib.istEmpty(request.getParameter("O_MENUSORTID"))) {
					menuModel.setO_MENUSORTID(Integer.parseInt(request.getParameter("O_MENUSORTID")));
				}
				if("0".equals(request.getParameter("O_MENUENABLED"))) {
					menuModel.setO_MENUENABLED(false);
				}
				else {
					menuModel.setO_MENUENABLED(true);
				}
				boolean u = menuService.updateMenu(menuModel);
				if(u) {
					response.setContentType("application/json; charset=utf-8");
					response.getWriter().write(success("创建成功"));
				} 
				else {
					response.setContentType("application/json; charset=utf-8");
					response.getWriter().write(error(2, "创建失败"));
				}
				break;
			case "delete":				
				if(Lib.istEmpty(request.getParameter("O_MENUID"))) {
					menuModel.setO_MENUID(request.getParameter("O_MENUID"));
					boolean a1 = menuService.deleteMenu(menuModel);
					if(a1) {
						response.setContentType("application/json; charset=utf-8");
						response.getWriter().write(success("删除成功"));
					} 
					else {
						response.setContentType("application/json; charset=utf-8");
						response.getWriter().write(error(2, "删除失败"));
					}
				}
				break;
			default: break;
		}
		
	}

}
