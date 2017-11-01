package com.browser.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.enums.Pagination;
import com.common.lib.Lib;
import com.server.model.UserModel;
import com.server.model.VehicleModel;
import com.server.service.VehicleService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class MenuServlet
 */

public class VehicleServlet extends ServletBase implements IServlet {
	private static final long serialVersionUID = 1L;
	private VehicleService vehicleService = new VehicleService();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public VehicleServlet() {
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

		VehicleModel[] vehicleModels;
		VehicleModel vehicleModel;
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
				
				vehicleModels = vehicleService.getTable(page);
				
				JSONObject jo = new JSONObject();
				JSONObject jp = new JSONObject();
				JSONArray jds = new JSONArray();
				jp.put("total", page.getTotal());
				jp.put("records", page.getRecords());
				jp.put("rows", page.getRows());
				jp.put("current", page.getCurrent());
				
				for(int i=0; i<vehicleModels.length; i++) {
					JSONObject jd = new JSONObject();
					jd.put("O_VEHICLEID", vehicleModels[i].getO_VEHICLEID());
					jd.put("O_PARENTID", vehicleModels[i].getO_PARENTID());
					jd.put("O_VEHICLECODE", vehicleModels[i].getO_VEHICLECODE());
					jd.put("O_VEHICLEREGCODE", vehicleModels[i].getO_VEHICLEREGCODE());
					jd.put("F_PARENTNAME", vehicleModels[i].getF_DEPARTNAME());
					jd.put("O_EQUIPMENTNO", vehicleModels[i].getO_EQUIPMENTNO());
					jd.put("O_EQUIPMENTTYPE", vehicleModels[i].getO_EQUIPMENTTYPE());
					jd.put("O_CAMERANUM", vehicleModels[i].getO_CAMERANUM());
					jds.add(jd);
				}
				
				jo.put("data", jds);
				jo.put("pagination", jp);
				
				response.setContentType("application/json; charset=utf-8");
				response.getWriter().write(content(jo));
				break;
			case "add":
				vehicleModel = new VehicleModel();
				vehicleModel.setO_PARENTID(request.getParameter("O_PARENTID"));
				vehicleModel.setO_VEHICLECODE(request.getParameter("O_VEHICLECODE"));
				vehicleModel.setO_VEHICLEREGCODE(request.getParameter("O_VEHICLEREGCODE"));
				vehicleModel.setO_VEHICLEMODEL(request.getParameter("O_VEHICLEMODEL"));
				vehicleModel.setO_EQUIPMENTNO(request.getParameter("O_EQUIPMENTNO"));
				vehicleModel.setO_EQUIPMENTTYPE(request.getParameter("O_EQUIPMENTTYPE"));
				if(Lib.istEmpty(request.getParameter("O_CAMERANUM"))) {
					vehicleModel.setO_CAMERANUM(Integer.parseInt(request.getParameter("O_CAMERANUM")));
				}

				boolean a = vehicleService.addVehicle(vehicleModel);
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
