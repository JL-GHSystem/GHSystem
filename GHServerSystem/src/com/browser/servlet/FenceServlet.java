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
				String search = request.getParameter("search");
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
				
				fenceModels = fenceService.getTable(page, search);

				
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
					jd.put("O_COMMIT", fenceModels[i].getO_COMMIT());
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
				String commit = request.getParameter("commit");
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
					fenceModel.setO_COMMIT(commit);
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
			case "fenceInLine":
				page = fenceService.dTP(request.getParameter("current"), request.getParameter("rows"));
				
				String o_LineId = request.getParameter("id");
				if(Lib.istEmpty(o_LineId)) {
					fenceModels = fenceService.getTableInLine(page, o_LineId);
				}
				else {
					fenceModels = fenceService.getTableInLine(page);
				}
				
				JSONObject jol = new JSONObject();
				JSONObject jpl = new JSONObject();
				JSONArray jdsl = new JSONArray();
				jpl.put("total", page.getTotal());
				jpl.put("records", page.getRecords());
				jpl.put("rows", page.getRows());
				jpl.put("current", page.getCurrent());

				for(int i=0; i<fenceModels.length; i++) {
					JSONObject jd = new JSONObject();
					jd.put("O_FENCEID", fenceModels[i].getO_FENCEID());
					jd.put("F_DEPARTID", fenceModels[i].getF_DEPARTID());
					jd.put("O_FENCENAME", fenceModels[i].getO_FENCENAME());
					jd.put("F_DEPARTNAME", fenceModels[i].getF_DEPARTNAME());
					jd.put("O_FENCETYPE", fenceModels[i].getO_FENCETYPE());
					jdsl.add(jd);
				}
				
				jol.put("data", jdsl);
				jol.put("pagination", jpl);
				
				response.setContentType("application/json; charset=utf-8");
				response.getWriter().write(content(jol));
				break;
			case "fenceInLineDetail":
				fenceModel = new FenceModel();
				fenceModel.setO_FENCEID(request.getParameter("id"));
				fenceModel.setF_DEPARTID(request.getParameter("fid"));
				
				fenceModel = fenceService.getFenceInLine(fenceModel);

				JSONObject jdr = new JSONObject();
				jdr.put("O_FENCEID", fenceModel.getO_FENCEID());
				jdr.put("O_DEPARTID", fenceModel.getF_DEPARTID());
				jdr.put("O_FENCENO", fenceModel.getF_FENCENO());
				jdr.put("O_AREANAME", fenceModel.getF_AREANAME());
				jdr.put("O_STATUS", fenceModel.getF_STATUS());
				jdr.put("O_PHBJ", fenceModel.getF_PHBJ());
				jdr.put("O_STAYTIME", fenceModel.getF_STAYTIME());
				jdr.put("O_SPEEDLIMT", fenceModel.getF_SPEEDLIMT());
				jdr.put("O_TIMEINTERVAL", fenceModel.getF_TIMEINTERVAL());
				jdr.put("O_TIMECOST", fenceModel.getF_TIMECOST());
				
				response.setContentType("application/json; charset=utf-8");
				response.getWriter().write(content(jdr));
				break;
			case "fenceNotInLine":
				String currentss = request.getParameter("current");
				String rowsss = request.getParameter("rows");
				if(Lib.istEmpty(currentss)) {
					page.setCurrent(Integer.parseInt(currentss));
				}
				else {
					page.setCurrent(1);
				}
				if(Lib.istEmpty(rowsss)) {
					page.setRows(Integer.parseInt(rowsss));
				}
				else {
					page.setRows(20);
				}
				
				String o_LineIds = request.getParameter("id");
				String o_Fencename = request.getParameter("name");
				if(Lib.istEmpty(o_LineIds)) {
					fenceModels = fenceService.getTableNotInLine(page, o_LineIds, o_Fencename);
					
					JSONObject jols = new JSONObject();
					JSONObject jpls = new JSONObject();
					JSONArray jdsls = new JSONArray();
					jpls.put("total", page.getTotal());
					jpls.put("records", page.getRecords());
					jpls.put("rows", page.getRows());
					jpls.put("current", page.getCurrent());

					for(int i=0; i<fenceModels.length; i++) {
						JSONObject jd = new JSONObject();
						jd.put("O_FENCEID", fenceModels[i].getO_FENCEID());
						jd.put("O_FENCENAME", fenceModels[i].getO_FENCENAME());
						jd.put("O_FENCETYPE", fenceModels[i].getO_FENCETYPE());
						jdsls.add(jd);
					}
					
					jols.put("data", jdsls);
					jols.put("pagination", jpls);
					
					response.setContentType("application/json; charset=utf-8");
					response.getWriter().write(content(jols));
				}
				else {
					
				}
				break;
			case "bind":
				fenceModel = new FenceModel();
				fenceModel.setO_FENCEID(request.getParameter("O_FENCEID"));
				fenceModel.setF_DEPARTID(request.getParameter("O_DEPARTID"));
				if(Lib.istEmpty(request.getParameter("O_FENCENO"))) {
					fenceModel.setF_FENCENO(Integer.parseInt(request.getParameter("O_FENCENO")));
				}
				fenceModel.setF_AREANAME(request.getParameter("O_AREANAME"));
				if(Lib.istEmpty(request.getParameter("O_FENCENO"))) {
					fenceModel.setF_STATUS(Integer.parseInt(request.getParameter("O_STATUS")));
				}
				if(Lib.istEmpty(request.getParameter("O_PHBJ"))) {
					fenceModel.setF_PHBJ(Integer.parseInt(request.getParameter("O_PHBJ")));
				}
				fenceModel.setF_STAYTIME(request.getParameter("O_STAYTIME"));
				if(Lib.istEmpty(request.getParameter("O_SPEEDLIMT"))) {
					fenceModel.setF_SPEEDLIMT(Integer.parseInt(request.getParameter("O_SPEEDLIMT")));
				}
				fenceModel.setF_TIMEINTERVAL(request.getParameter("O_TIMEINTERVAL"));
				fenceModel.setF_TIMECOST(request.getParameter("O_TIMECOST"));				
				
				if(Lib.istEmpty(fenceModel.getO_FENCEID()) && Lib.istEmpty(fenceModel.getF_DEPARTID())) {
					boolean a1 = fenceService.bindFence(fenceModel);
					if(a1) {
						response.setContentType("application/json; charset=utf-8");
						response.getWriter().write(success("绑定成功"));
					} 
					else {
						response.setContentType("application/json; charset=utf-8");
						response.getWriter().write(error(2, "绑定失败"));
					}
				}
				break;
			case "unbind":
				String ids = request.getParameter("id");
				String fid = request.getParameter("fid");
				if(Lib.istEmpty(ids) && Lib.istEmpty(fid)) {
					boolean a1 = fenceService.unbindFence(ids, fid);
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
			case "updateBind":
				fenceModel = new FenceModel();
				fenceModel.setO_FENCEID(request.getParameter("O_FENCEID"));
				fenceModel.setF_DEPARTID(request.getParameter("O_DEPARTID"));
				if(Lib.istEmpty(request.getParameter("O_FENCENO"))) {
					fenceModel.setF_FENCENO(Integer.parseInt(request.getParameter("O_FENCENO")));
				}
				fenceModel.setF_AREANAME(request.getParameter("O_AREANAME"));
				if(Lib.istEmpty(request.getParameter("O_FENCENO"))) {
					fenceModel.setF_STATUS(Integer.parseInt(request.getParameter("O_STATUS")));
				}
				if(Lib.istEmpty(request.getParameter("O_PHBJ"))) {
					fenceModel.setF_PHBJ(Integer.parseInt(request.getParameter("O_PHBJ")));
				}
				fenceModel.setF_STAYTIME(request.getParameter("O_STAYTIME"));
				if(Lib.istEmpty(request.getParameter("O_SPEEDLIMT"))) {
					fenceModel.setF_SPEEDLIMT(Integer.parseInt(request.getParameter("O_SPEEDLIMT")));
				}
				fenceModel.setF_TIMEINTERVAL(request.getParameter("O_TIMEINTERVAL"));
				fenceModel.setF_TIMECOST(request.getParameter("O_TIMECOST"));				
				
				if(Lib.istEmpty(fenceModel.getO_FENCEID()) && Lib.istEmpty(fenceModel.getF_DEPARTID())) {
					boolean a1 = fenceService.updateFenceInLine(fenceModel);
					if(a1) {
						response.setContentType("application/json; charset=utf-8");
						response.getWriter().write(success("修改成功"));
					} 
					else {
						response.setContentType("application/json; charset=utf-8");
						response.getWriter().write(error(2, "修改失败"));
					}
				}
				break;
			default: break;
		}
		
	}

}
