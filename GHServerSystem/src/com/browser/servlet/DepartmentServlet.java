package com.browser.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.enums.Pagination;
import com.common.lib.Lib;
import com.server.model.DepartmentModel;
import com.server.model.UserModel;
import com.server.service.DepartmentService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class MenuServlet
 */

public class DepartmentServlet extends HttpServlet implements IServlet {
	private static final long serialVersionUID = 1L;
	private DepartmentService departmentService = new DepartmentService();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepartmentServlet() {
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

		DepartmentModel[] departmentModels;
		DepartmentModel departmentModel;
		Pagination page = new Pagination();
		switch(type) {
			case "table":
				String current = request.getParameter("current");
				String rows = request.getParameter("rows");
				if(Lib.istEmpty(current)) {
					page.setCurrent(Integer.parseInt(current));
				}
				else {
					page.setCurrent(1);
				}
				if(Lib.istEmpty(rows)) {
					page.setRows(Integer.parseInt(rows));
				}
				else {
					page.setRows(20);
				}
				
				departmentModels = departmentService.getTable(page);

				
				JSONObject jo = new JSONObject();
				JSONObject jp = new JSONObject();
				JSONArray jds = new JSONArray();
				jp.put("total", page.getTotal());
				jp.put("records", page.getRecords());
				jp.put("rows", page.getRows());
				jp.put("current", page.getCurrent());
				
				for(int i=0; i<departmentModels.length; i++) {
					JSONObject jd = new JSONObject();
					jd.put("O_DEPARTID", departmentModels[i].getO_DEPARTID());
					jd.put("O_PARENTID", departmentModels[i].getO_PARENTID());
					jd.put("O_DEPARTNAME", departmentModels[i].getO_DEPARTNAME());
					jd.put("O_PARENTNAME", departmentModels[i].getO_PARENTNAME());
					jd.put("F_DEPARTMENTTYPE", departmentModels[i].getF_DEPARTMENTTYPE());
					jd.put("O_DEPARTNAMEPATH", departmentModels[i].getO_DEPARTNAMEPATH());
					jds.add(jd);
				}
				
				jo.put("data", jds);
				jo.put("pagination", jp);
				
				response.setContentType("application/json; charset=utf-8");
				response.getWriter().write(content(jo));
				break;
			case "add":
				departmentModel = new DepartmentModel();
				departmentModel.setO_PARENTID(request.getParameter("O_PARENTID"));
				departmentModel.setO_DEPARTTYPE(request.getParameter("O_DEPARTTYPE"));
				departmentModel.setO_DEPARTNAME(request.getParameter("O_DEPARTNAME"));
				departmentModel.setO_PARENTNAME(request.getParameter("O_PARENTNAME"));

				boolean a = departmentService.addDepartment(departmentModel);
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
				
				break;
			case "delete":
				
				break;
			default: break;
		}
		
	}

}
