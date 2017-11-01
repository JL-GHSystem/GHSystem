package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class ConnServlet
 */
@WebServlet("/ConnServlet")
public class ConnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnServlet() {
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
        StringBuffer jb = new StringBuffer();
        String line = null;
        String result = "";
        try {
            //��ȡ��������StringBuffer��
            BufferedReader reader = request.getReader();
              while ((line = reader.readLine()) != null)
                jb.append(line);
               
        } catch (Exception e) { /*report an error*/ 
        	e.printStackTrace();
        }

        try {
            //ʹ��JSONObject��parseObject��������JSON�ַ���
            JSONObject jsonObject = JSONObject.fromObject(jb.toString());
            result = jsonObject.toString();
               
        } catch (Exception e) {
          // crash and burn
        	e.printStackTrace();
        }
        System.out.println(result);
        
        
        
        JSONObject jo = new JSONObject();
        jo.put("a", "123");
        jo.put("b", "asd");
        
        //�Ƚ��������յ���JSON�ַ�����ӡ���ͻ��ˣ��ٽ����ַ���ת��ΪJSON����Ȼ����ת���ɵ�JSON�ַ�����ӡ���ͻ���
        PrintStream out = new PrintStream(response.getOutputStream());
        out.println(jo.toString());
        
	}

}
