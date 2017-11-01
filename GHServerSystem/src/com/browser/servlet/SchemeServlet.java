package com.browser.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.enums.Bool;
import com.common.enums.Direction;
import com.common.enums.Pagination;
import com.common.lib.Lib;
import com.server.model.FenceModel;
import com.server.model.SchemeModel;
import com.server.model.UserModel;
import com.server.service.SchemeService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class MenuServlet
 */

public class SchemeServlet extends HttpServlet implements IServlet {
	private static final long serialVersionUID = 1L;
	private SchemeService schemeService = new SchemeService();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SchemeServlet() {
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

		SchemeModel[] schemeModels;
		SchemeModel schemeModel;
		FenceModel[] fenceModels;
		Pagination page = new Pagination();
		String o_LINECODE;
		JSONArray ja;
		boolean a;
		switch(type) {
			case "schemeTable":
				o_LINECODE = request.getParameter("code");
				
				schemeModels = schemeService.getSchemeInLine(o_LINECODE);
				ja = new JSONArray();

				for(int i=0; i<schemeModels.length; i++) {
					JSONObject jd = new JSONObject();
					jd.put("O_DEPARTCODE", schemeModels[i].getO_DEPARTCODE());
					jd.put("O_DEPARTNAME", schemeModels[i].getF_DEPARTNAME());
					jd.put("O_PROGRAMID", schemeModels[i].getO_PROGRAMID(true));
					jd.put("O_PROGRAMNAME", schemeModels[i].getO_PROGRAMNAME());
					jd.put("O_BOPER", Bool.parseBool(schemeModels[i].isO_BOPER()));
					jd.put("O_STATUS", schemeModels[i].getO_STATUS());
					jd.put("O_DIRECTION", Direction.parseBool(schemeModels[i].isO_DIRECTION()));
					jd.put("O_STANDERKM", schemeModels[i].getO_STANDERKM());
					jd.put("O_STANDERTIME", schemeModels[i].getO_STANDERTIME());
					ja.add(jd);
				}
				
				response.setContentType("application/json; charset=utf-8");
				response.getWriter().write(content(ja));
				break;
			case "fenceInScheme":
				schemeModel = new SchemeModel();
				o_LINECODE = request.getParameter("code");
				String o_FENCENO = request.getParameter("no");
				
				if(Lib.istEmpty(o_LINECODE) && Lib.istEmpty(o_FENCENO)) {
					schemeModel.setO_DEPARTCODE(o_LINECODE);
					schemeModel.setO_PROGRAMID(o_FENCENO);
					
					fenceModels = schemeService.getFenceInScheme(schemeModel);
					
					ja = new JSONArray();

					for(int i=0; i<fenceModels.length; i++) {
						JSONObject jd = new JSONObject();
						jd.put("O_FENCEID", fenceModels[i].getO_FENCEID());
						jd.put("F_DEPARTID", fenceModels[i].getF_DEPARTID());
						jd.put("F_DEPARTCODE", fenceModels[i].getF_DEPARTCODE());
						jd.put("F_FENCENO", fenceModels[i].getF_FENCENO());
						jd.put("F_AREANAME", fenceModels[i].getF_AREANAME());
						jd.put("O_FENCENAME", fenceModels[i].getO_FENCENAME());
						jd.put("O_FENCETYPE", fenceModels[i].getO_FENCETYPE());
						ja.add(jd);
					}
					
					response.setContentType("application/json; charset=utf-8");
					response.getWriter().write(content(ja));
				}
				else {
					response.setContentType("application/json; charset=utf-8");
					response.getWriter().write(error(2, "id为空，无法查询数据"));
				}
				break;
			case "fenceInLine":
				
				String o_LineCode = request.getParameter("code");
				if(Lib.istEmpty(o_LineCode)) {
					fenceModels = schemeService.getFenceInLine(o_LineCode);
				
					JSONObject jol = new JSONObject();
					JSONArray jdsl = new JSONArray();
	
					for(int i=0; i<fenceModels.length; i++) {
						JSONObject jd = new JSONObject();
						jd.put("F_FENCENO", fenceModels[i].getF_FENCENO());
						jd.put("F_AREANAME", fenceModels[i].getF_AREANAME());
						jd.put("O_FENCENAME", fenceModels[i].getO_FENCENAME());
						jd.put("O_FENCETYPE", fenceModels[i].getO_FENCETYPE());
						jdsl.add(jd);
					}
					
					jol.put("data", jdsl);
					
					response.setContentType("application/json; charset=utf-8");
					response.getWriter().write(content(jol));
				}
				break;
			case "add":
				schemeModel = new SchemeModel();
				schemeModel.setO_DEPARTCODE(request.getParameter("O_DEPARTCODE"));
				schemeModel.setF_DEPARTNAME(request.getParameter("O_DEPARTNAME"));
				schemeModel.setO_PROGRAMNAME(request.getParameter("O_PROGRAMNAME"));
				schemeModel.setO_BOPER(Bool.parseString(request.getParameter("O_BOPER")));
				schemeModel.setO_DIRECTION(Direction.parseString(request.getParameter("O_DIRECTION")));
				schemeModel.setO_STATUS(request.getParameter("O_STATUS"));
				schemeModel.setO_STANDERKM(Integer.parseInt(request.getParameter("O_STANDERKM")));
				schemeModel.setO_STANDERTIME(Integer.parseInt(request.getParameter("O_STANDERTIME")));

				a = schemeService.addscheme(schemeModel);
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
				schemeModel = new SchemeModel();
				schemeModel.setO_DEPARTCODE(request.getParameter("O_DEPARTCODE"));
				schemeModel.setF_DEPARTNAME(request.getParameter("O_DEPARTNAME"));
				schemeModel.setO_PROGRAMNAME(request.getParameter("O_PROGRAMNAME"));
				schemeModel.setO_BOPER(Bool.parseString(request.getParameter("O_BOPER")));
				schemeModel.setO_DIRECTION(Direction.parseString(request.getParameter("O_DIRECTION")));
				schemeModel.setO_STATUS(request.getParameter("O_STATUS"));
				schemeModel.setO_STANDERKM(Integer.parseInt(request.getParameter("O_STANDERKM")));
				schemeModel.setO_STANDERTIME(Integer.parseInt(request.getParameter("O_STANDERTIME")));

				a = schemeService.updateScheme(schemeModel);
				if(a) {
					response.setContentType("application/json; charset=utf-8");
					response.getWriter().write(success("创建成功"));
				} 
				else {
					response.setContentType("application/json; charset=utf-8");
					response.getWriter().write(error(2, "创建失败"));
				}
				break;
			case "updateFence":
				schemeModel = new SchemeModel();
				schemeModel.setO_DEPARTCODE(request.getParameter("O_DEPARTCODE"));
				schemeModel.setO_PROGRAMNAME(request.getParameter("O_PROGRAMNAME"));
				if(Lib.istEmpty(request.getParameter("O_PROGRAMID"))) {
					schemeModel.setO_PROGRAMID(request.getParameter("O_PROGRAMID"));
				}
				if(Lib.istEmpty(request.getParameter("O_PROGRAMNOTE"))) {
					schemeModel.setO_PROGRAMNOTE(request.getParameter("O_PROGRAMNOTE"));
				}
				
				a = schemeService.updateFenceInScheme(schemeModel);
				if(a) {
					response.setContentType("application/json; charset=utf-8");
					response.getWriter().write(success("创建成功"));
				} 
				else {
					response.setContentType("application/json; charset=utf-8");
					response.getWriter().write(error(2, "创建失败"));
				}
				break;
			case "delete":
				String code[] = request.getParameterValues("O_DEPARTCODE[]");
				String name[] = request.getParameterValues("O_PROGRAMNAME[]");
				schemeModels = new SchemeModel[code.length];
				for(int i=0; i<schemeModels.length; i++) {
					SchemeModel scheme = new SchemeModel();
					scheme.setO_DEPARTCODE(code[i]);
					scheme.setO_PROGRAMNAME(name[i]);
					schemeModels[i] = scheme;
				}

				a = schemeService.deleteScheme(schemeModels);
				if(a) {
					response.setContentType("application/json; charset=utf-8");
					response.getWriter().write(success("删除成功"));
				} 
				else {
					response.setContentType("application/json; charset=utf-8");
					response.getWriter().write(error(2, "删除失败"));
				}
				break;
			default: break;
		}
		
	}

}



