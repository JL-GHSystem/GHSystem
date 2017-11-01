package com.browser.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.enums.Pagination;
import com.server.model.LineModel;
import com.server.model.UserModel;
import com.server.service.LineService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class MenuServlet
 */

public class LineServlet extends ServletBase implements IServlet {
	private static final long serialVersionUID = 1L;
	private LineService lineService = new LineService();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LineServlet() {
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

		LineModel[] lineModels;
		LineModel lineModel;
		switch(type) {
			case "search":
				search(request, response);
				break;
			case "table":
				/*String current = request.getParameter("current");
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
				
				lineModels = lineService.getTable(page);

				
				JSONObject jo = new JSONObject();
				JSONObject jp = new JSONObject();
				JSONArray jds = new JSONArray();
				jp.put("total", page.getTotal());
				jp.put("records", page.getRecords());
				jp.put("rows", page.getRows());
				jp.put("current", page.getCurrent());
				
				for(int i=0; i<lineModels.length; i++) {
					JSONObject jd = new JSONObject();
					jd.put("O_DEPARTID", lineModels[i].getO_DEPARTID());
					jd.put("O_PARENTID", lineModels[i].getO_PARENTID());
					jd.put("O_DEPARTNAME", lineModels[i].getO_DEPARTNAME());
					jd.put("O_PARENTNAME", lineModels[i].getO_PARENTNAME());
					jd.put("F_DEPARTMENTTYPE", lineModels[i].getF_DEPARTMENTTYPE());
					jd.put("O_DEPARTNAMEPATH", lineModels[i].getO_DEPARTNAMEPATH());
					jds.add(jd);
				}
				
				jo.put("data", jds);
				jo.put("pagination", jp);
				
				response.setContentType("application/json; charset=utf-8");
				response.getWriter().write(content(jo));*/
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
			case "selectLine":
				/*String currents = request.getParameter("current");
				String rowss = request.getParameter("rows");
				if(Lib.istEmpty(currents)) {
					page.setCurrent(Integer.parseInt(currents));
				}
				else {
					page.setCurrent(1);
				}
				if(Lib.istEmpty(rowss)) {
					page.setRows(Integer.parseInt(rowss));
				}
				else {
					page.setRows(20);
				}
				
				lineModels = lineService.getSLTable(page);

				
				JSONObject jos = new JSONObject();
				JSONObject jps = new JSONObject();
				JSONArray jdss = new JSONArray();
				jps.put("total", page.getTotal());
				jps.put("records", page.getRecords());
				jps.put("rows", page.getRows());
				jps.put("current", page.getCurrent());
				
				for(int i=0; i<lineModels.length; i++) {
					JSONObject jd = new JSONObject();
					jd.put("O_DEPARTID", lineModels[i].getO_DEPARTID());
					jd.put("O_PARENTID", lineModels[i].getO_PARENTID());
					jd.put("O_DEPARTCODE", lineModels[i].getO_DEPARTCODE());
					jd.put("O_DEPARTNAME", lineModels[i].getO_DEPARTNAME());
					jdss.add(jd);
				}
				
				jos.put("data", jdss);
				jos.put("pagination", jps);
				
				response.setContentType("application/json; charset=utf-8");
				response.getWriter().write(content(jos));*/
				break;
			default: break;
		}
		
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String[] ids = request.getParameterValues("ids[]");

		boolean a = lineService.deleteLine(ids);
		if(a) {
			response.getWriter().write(success("删除成功"));
		} 
		else {
			response.getWriter().write(error(2, "删除失败，该部门下还有车辆或人员"));
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject jo = toJSON(request);

		LineModel lineModel = lineService.jTB(jo);

		boolean a = lineService.updateLine(lineModel);
		if(a) {
			response.getWriter().write(success("修改成功"));
		} 
		else {
			response.getWriter().write(error(2, "修改失败"));
		}
	}

	private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LineModel[] lineModels;
		Pagination p;
		JSONObject jOb;
		JSONArray jArr;
		
		p = lineService.dTP(request.getParameter("current"), request.getParameter("rows"));

		JSONObject jo = toJSON(request);
		LineModel lineModel = lineService.jTB(jo);
		
		lineModels = lineService.getTable(p, lineModel);
		
		jOb = new JSONObject();
		jArr = new JSONArray();
		
		for(int i=0; i<lineModels.length; i++) {
			JSONObject jEle = new JSONObject();
			jEle.put("ODEPARTID", lineModels[i].getODEPARTID());
			jEle.put("OPARENTID", lineModels[i].getOPARENTID());
			jEle.put("ODEPARTNAME", lineModels[i].getODEPARTNAME());
			jEle.put("OPARENTNAME", lineModels[i].getOPARENTNAME());
			jEle.put("FDEPARTMENTTYPE", lineModels[i].getFDEPARTMENTTYPE());
			jEle.put("ODEPARTCODE", lineModels[i].getODEPARTCODE());
			jEle.put("ODEPARTNAMEPATH", lineModels[i].getODEPARTNAMEPATH());
			jArr.add(jEle);
		}
		
		jOb.put("data", jArr);
		jOb.put("pagination", lineService.pTJ(p));
		
		response.getWriter().write(content(jOb));
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject jo = toJSON(request);

		LineModel lineModel = lineService.jTB(jo);

		boolean a = lineService.addLine(lineModel);
		if(a) {
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().write(success("创建成功"));
		} 
		else {
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().write(error(2, "创建失败"));
		}
	}
}