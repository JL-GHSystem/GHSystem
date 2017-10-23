package com.browser.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.lib.Lib;
import com.server.model.MenuModel;
import com.server.model.UserGroupModel;
import com.server.model.UserModel;
import com.server.service.RoleService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class MenuServlet
 */

public class RoleServlet extends HttpServlet implements IServlet {
	private static final long serialVersionUID = 1L;
	private RoleService roleService = new RoleService();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoleServlet() {
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

		UserGroupModel[] userGroupModels;
		MenuModel[] menuModels;
		switch(type) {
			case "tree":
				menuModels = roleService.getMenuTree();

				JSONArray ja = new JSONArray();
				for(int i=0; i<menuModels.length; i++) {
					JSONObject jo = new JSONObject();
					jo.put("O_MENUID", menuModels[i].getO_MENUID());
					jo.put("O_MENUNAME", menuModels[i].getO_MENUNAME());
					jo.put("F_MENUPREVNAME", menuModels[i].getF_MENUPREVNAME());
					jo.put("O_MENULEVEL", menuModels[i].getO_MENULEVEL());
					jo.put("O_MENUURL", menuModels[i].getO_MENUURL());
					ja.add(jo);
				}
				
				response.setContentType("application/json; charset=utf-8");
				response.getWriter().write(content(ja));
				break;
			case "table":
				userGroupModels = roleService.getUserGroupTable();

				response.setContentType("application/json; charset=utf-8");
				response.getWriter().write(content(JSONArray.fromObject(userGroupModels)));
				
				break;
			case "add":
				
				String name = request.getParameter("O_USERGROUPNAME");
				String[] ids = request.getParameterValues("O_USERGROUPIDS[]");
				
				if(Lib.istEmpty(name)) {
					UserGroupModel userGroupModel = new UserGroupModel();
					userGroupModel.setO_USERGROUPNAME(name);
					boolean a = roleService.addGroup(userGroupModel, ids);
					if(a) {
						response.setContentType("application/json; charset=utf-8");
						response.getWriter().write(success("创建成功"));
					}
					else {
						response.setContentType("application/json; charset=utf-8");
						response.getWriter().write(error(2, "创建失败"));
					}
				}
				break;
			case "update":
				
				break;
			case "delete":
				String[] gids = request.getParameterValues("O_USERGROUPIDS[]");
				
				if(Lib.istEmpty(gids)) {
					boolean a = roleService.deleteGroup(gids);
					if(a) {
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
