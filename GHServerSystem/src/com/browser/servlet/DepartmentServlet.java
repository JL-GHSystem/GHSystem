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

public class DepartmentServlet extends ServletBase implements IServlet {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String type = request.getParameter("type");

		HttpSession session = request.getSession();
		UserModel userModel = (UserModel) session.getAttribute("PUser");

		switch(type) {
			case "search":
				search(request, response);
				break;
			case "add":
				add(request, response);
				break;
			case "update":
				update(request, response);
				break;
			case "delete":
				delete(request, response);
				break;
			case "select":
				select(request, response);
				break;
			case "tree":
				tree(request, response);
				break;
			default: 
				response.getWriter().write(error(3, "ajax��������"));
				break;
		}
		
	}
	private void tree(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject jo = toJSON(request);
		
		DepartmentModel departmentModel = departmentService.jTB(jo);

		boolean a = departmentService.addDepartment(departmentModel);
		if(a) {
			response.getWriter().write(success("�����ɹ�"));
		} 
		else {
			response.getWriter().write(error(2, "����ʧ��"));
		}
	}

	private void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DepartmentModel[] departmentModels;
		JSONObject jOb;
		JSONArray jArr;

		departmentModels = departmentService.getAll();
		
		jOb = new JSONObject();
		jArr = new JSONArray();
		
		for(int i=0; i<departmentModels.length; i++) {
			JSONObject jEle = new JSONObject();
			jEle.put("ODEPARTID", departmentModels[i].getODEPARTID());
			jEle.put("OPARENTID", departmentModels[i].getOPARENTID());
			jEle.put("ODEPARTCODE", departmentModels[i].getODEPARTCODE());
			jEle.put("ODEPARTNAME", departmentModels[i].getODEPARTNAME());
			jEle.put("FDEPARTMENTTYPE", departmentModels[i].getFDEPARTMENTTYPE());
			jEle.put("ODEPARTNAMEPATH", departmentModels[i].getODEPARTNAMEPATH());
			jArr.add(jEle);
		}
		
		jOb.put("data", jArr);
		
		response.getWriter().write(content(jOb));
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String[] ids = request.getParameterValues("ids[]");

		boolean a = departmentService.deleteDepartment(ids);
		if(a) {
			response.getWriter().write(success("ɾ���ɹ�"));
		} 
		else {
			response.getWriter().write(error(2, "ɾ��ʧ�ܣ��ò����»��г�������Ա"));
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject jo = toJSON(request);
		
		DepartmentModel departmentModel = departmentService.jTB(jo);

		boolean a = departmentService.updateDepartment(departmentModel);
		if(a) {
			response.getWriter().write(success("�޸ĳɹ�"));
		} 
		else {
			response.getWriter().write(error(2, "�޸�ʧ��"));
		}
	}

	private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DepartmentModel[] departmentModels;
		Pagination p;
		JSONObject jOb;
		JSONArray jArr;
		
		p = departmentService.dTP(request.getParameter("current"), request.getParameter("rows"));

		JSONObject jo = toJSON(request);
		DepartmentModel departmentModel = departmentService.jTB(jo);
		
		departmentModels = departmentService.getTable(p, departmentModel);
		
		jOb = new JSONObject();
		jArr = new JSONArray();
		
		for(int i=0; i<departmentModels.length; i++) {
			JSONObject jEle = new JSONObject();
			jEle.put("ODEPARTID", departmentModels[i].getODEPARTID());
			jEle.put("OPARENTID", departmentModels[i].getOPARENTID());
			jEle.put("ODEPARTNAME", departmentModels[i].getODEPARTNAME());
			jEle.put("OPARENTNAME", departmentModels[i].getOPARENTNAME());
			jEle.put("FDEPARTMENTTYPE", departmentModels[i].getFDEPARTMENTTYPE());
			jEle.put("ODEPARTCODE", departmentModels[i].getODEPARTCODE());
			jEle.put("ODEPARTNAMEPATH", departmentModels[i].getODEPARTNAMEPATH());
			jArr.add(jEle);
		}
		
		jOb.put("data", jArr);
		jOb.put("pagination", departmentService.pTJ(p));
		
		response.getWriter().write(content(jOb));
	}
	
}
