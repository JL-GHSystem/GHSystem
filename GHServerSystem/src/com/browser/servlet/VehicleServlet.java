package com.browser.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.model.VehicleModel;
import com.server.service.VehicleService;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class MenuServlet
 */

public class VehicleServlet extends HttpServlet implements IServlet {
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
		
		VehicleModel[] vehicleModels = vehicleService.getVehicle();

		response.setContentType("application/json; charset=utf-8");
		response.getWriter().write(content(JSONArray.fromObject(vehicleModels)));
	}

}
