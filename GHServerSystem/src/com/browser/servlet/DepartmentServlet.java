package com.browser.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.model.DepartmentModel;
import com.server.service.DepartmentService;

import net.sf.json.JSONArray;

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
		
		DepartmentModel[] departmentModel = departmentService.getDepartment();

		response.setContentType("application/json; charset=utf-8");
		response.getWriter().write(content(JSONArray.fromObject(departmentModel)));
		
		
	}

}
