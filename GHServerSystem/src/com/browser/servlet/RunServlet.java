package com.browser.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.enums.Pagination;
import com.common.lib.Lib;
import com.server.model.MenuModel;
import com.server.model.RunModel;
import com.server.model.UserModel;
import com.server.service.UserService;
import com.server.service.RunService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class MenuServlet
 */

public class RunServlet extends HttpServlet implements IServlet {
	private static final long serialVersionUID = 1L;
	private RunService runService = new RunService();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RunServlet() {
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
		
		UserModel[] userModels;
		RunModel[] runModels;
		RunModel runModel;
		Pagination page = new Pagination();
		JSONObject jo;
		JSONObject jp;
		JSONArray jds;
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
				runModel = new RunModel();
				
				runModel.setO_DEPARTID(request.getParameter("O_DEPARTID"));
				runModel.setF_DRIVERNAME(request.getParameter("F_DRIVERNAME"));
				runModel.setO_VEHICLECODE(request.getParameter("O_VEHICLECODE"));
				runModel.setO_DATE(request.getParameter("O_DATE"));
				if(Lib.istEmpty(request.getParameter("O_BC"))) {
					runModel.setO_BC(Integer.parseInt(request.getParameter("O_BC")));
				}
				if(Lib.istEmpty(request.getParameter("O_TC"))) {
					runModel.setO_TC(Integer.parseInt(request.getParameter("O_TC")));
				}
				runModel.setS_DIRECTION(request.getParameterValues("O_DIRECTION[]"));
								
				runModels = runService.getTable(page, runModel);
				
				jo = new JSONObject();
				jp = new JSONObject();
				jds = new JSONArray();
				jp.put("total", page.getTotal());
				jp.put("records", page.getRecords());
				jp.put("rows", page.getRows());
				jp.put("current", page.getCurrent());
				
				for(int i=0; i<runModels.length; i++) {
					JSONObject jd = new JSONObject();
					
					jd.put("O_DEPARTID", runModels[i].getO_DEPARTID());
					jd.put("O_VEHICLECODE", runModels[i].getO_VEHICLECODE());
					jd.put("O_DRIVERCODE", runModels[i].getO_DRIVERCODE());
					jd.put("O_DEPARTNAME", runModels[i].getF_DEPARTNAME());
					jd.put("O_VEHICLENAME", runModels[i].getF_VEHICLENAME());
					jd.put("O_DRIVERNAME", runModels[i].getF_DRIVERNAME());
					jd.put("O_DATE", runModels[i].getO_DATE());
					jd.put("O_BC", runModels[i].getO_BC());
					jd.put("O_TC", runModels[i].getO_TC());
					jd.put("O_DIRECTION", runModels[i].getO_DIRECTION());
					jd.put("O_TIME", runModels[i].getO_TIME());
					jd.put("O_ENDTIME", runModels[i].getO_ENDTIME());
					if(runModels[i].getO_CREWCODE() != null) {
						for(int j=0; j<runModels[i].getO_CREWCODE().size(); j++) {
							if(runModels[i].getO_CREWCODE().get(j) != null) {
								jd.put("O_CREWCODE"+ j, runModels[i].getO_CREWCODE().get(j));
								jd.put("O_CREWNAME"+ j, runModels[i].getF_CREWNAME().get(j));
							}
						}
					}
					jds.add(jd);
				}
				
				jo.put("data", jds);
				jo.put("pagination", jp);
				
				response.setContentType("application/json; charset=utf-8");
				response.getWriter().write(content(jo));
				break;
			case "add":
				break;
			case "input":
				break;
			case "output":
				break;
			case "update":
				break;
			case "delete":
				break;
			default: break;
		}
		
	}

}
