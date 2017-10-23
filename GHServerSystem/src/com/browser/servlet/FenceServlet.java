package com.browser.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.enums.Fence;
import com.common.enums.Pagination;
import com.common.lib.Lib;
import com.server.model.FenceModel;
import com.server.model.FenceNodeModel;
import com.server.model.LineModel;
import com.server.model.UserGroupModel;
import com.server.model.UserModel;
import com.server.service.DepartmentService;
import com.server.service.FenceService;
import com.server.service.LineService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class MenuServlet
 */

public class FenceServlet extends HttpServlet implements IServlet {
	private static final long serialVersionUID = 1L;
	private FenceService fenceService = new FenceService();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FenceServlet() {
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

		FenceModel[] fenceModels;
		FenceModel fenceModel;
		Pagination page = new Pagination();
		switch(type) {
			case "detail":
				String id = request.getParameter("id");
				String ftype = request.getParameter("o");
				fenceModel = new FenceModel();
				fenceModel.setO_FENCEID(id);
				fenceModel.setO_FENCETYPE(ftype);
				
				fenceModel = fenceService.getFence(fenceModel);

				JSONObject jo1 = new JSONObject();
				JSONArray jds1 = new JSONArray();
				jo1.put("O_FENCEID", fenceModel.getO_FENCEID());
				jo1.put("O_FENCETYPE", fenceModel.getO_FENCETYPE());
				jo1.put("O_RADIUS", fenceModel.getO_RADIUS());

				Iterator<FenceNodeModel> it = fenceModel.getF_FENCENODES().iterator();
				while(it.hasNext()) {
					JSONObject jd = new JSONObject();
					FenceNodeModel f = it.next();
					jd.put("O_LONGITUDE", f.getO_LONGITUDE());
					jd.put("O_LATITUDE", f.getO_LATITUDE());
					jds1.add(jd);
				}
				
				jo1.put("F_FENCENODES", jds1);
				
				response.setContentType("application/json; charset=utf-8");
				response.getWriter().write(content(jo1));
				
				break;
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
				
				fenceModels = fenceService.getTable(page);

				
				JSONObject jo = new JSONObject();
				JSONObject jp = new JSONObject();
				JSONArray jds = new JSONArray();
				jp.put("total", page.getTotal());
				jp.put("records", page.getRecords());
				jp.put("rows", page.getRows());
				jp.put("current", page.getCurrent());

				for(int i=0; i<fenceModels.length; i++) {
					JSONObject jd = new JSONObject();
					jd.put("O_FENCEID", fenceModels[i].getO_FENCEID());
					jd.put("O_FENCENAME", fenceModels[i].getO_FENCENAME());
					jd.put("O_FENCETYPE", fenceModels[i].getO_FENCETYPE());
					jds.add(jd);
				}
				
				jo.put("data", jds);
				jo.put("pagination", jp);
				
				response.setContentType("application/json; charset=utf-8");
				response.getWriter().write(content(jo));
				break;
			case "add":
				String name = request.getParameter("name");
				String radius = request.getParameter("radius");
				String fetype = request.getParameter("ftype");
				String[] laPoints = request.getParameterValues("laPoints[]");
				String[] loPoints = request.getParameterValues("loPoints[]");
				
				fenceModel = new FenceModel();
				if(Lib.istEmpty(name) && Lib.istEmpty(fetype)) {
					fenceModel.setO_FENCENAME(name);
					if(Lib.istEmpty(radius)) {
						fenceModel.setO_RADIUS(new BigDecimal(radius));
					}
					else {
						fenceModel.setO_RADIUS(new BigDecimal("-1"));
					}
					fenceModel.setO_FENCETYPE(Fence.parseInt(Integer.parseInt(fetype)));
					for(int i=0; i<laPoints.length; i++) {
						FenceNodeModel fenceNodeModel = new FenceNodeModel();
						fenceNodeModel.setO_POINTNO(i);
						fenceNodeModel.setO_LONGITUDE(new BigDecimal(loPoints[i]));
						fenceNodeModel.setO_LATITUDE(new BigDecimal(laPoints[i]));
						fenceModel.getF_FENCENODES().add(fenceNodeModel);
					}
				}
				
				boolean a = fenceService.addFence(fenceModel);
				
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
				String uid = request.getParameter("id");
				String uname = request.getParameter("name");
				String uradius = request.getParameter("radius");
				String ufetype = request.getParameter("ftype");
				String[] ulaPoints = request.getParameterValues("laPoints[]");
				String[] uloPoints = request.getParameterValues("loPoints[]");
				
				fenceModel = new FenceModel();
				if(Lib.istEmpty(uname) && Lib.istEmpty(ufetype) && Lib.istEmpty(uid)) {
					fenceModel.setO_FENCEID(uid);
					fenceModel.setO_FENCENAME(uname);
					if(Lib.istEmpty(uradius)) {
						fenceModel.setO_RADIUS(new BigDecimal(uradius));
					}
					else {
						fenceModel.setO_RADIUS(new BigDecimal("-1"));
					}
					fenceModel.setO_FENCETYPE(Fence.parseInt(Integer.parseInt(ufetype)));
					for(int i=0; i<ulaPoints.length; i++) {
						FenceNodeModel fenceNodeModel = new FenceNodeModel();
						fenceNodeModel.setO_POINTNO(i);
						fenceNodeModel.setO_LONGITUDE(new BigDecimal(uloPoints[i]));
						fenceNodeModel.setO_LATITUDE(new BigDecimal(ulaPoints[i]));
						fenceModel.getF_FENCENODES().add(fenceNodeModel);
					}
				}
				
				boolean u = fenceService.updateFence(fenceModel);
				
				if(u) {
					response.setContentType("application/json; charset=utf-8");
					response.getWriter().write(success("更新成功"));
				} 
				else {
					response.setContentType("application/json; charset=utf-8");
					response.getWriter().write(error(2, "更新失败"));
				}
				break;
			case "delete":
				fenceModel = new FenceModel();
				if(Lib.istEmpty(request.getParameter("O_FENCEID"))) {
					fenceModel.setO_FENCEID(request.getParameter("O_FENCEID"));
					boolean a1 = fenceService.deleteFence(fenceModel);
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
