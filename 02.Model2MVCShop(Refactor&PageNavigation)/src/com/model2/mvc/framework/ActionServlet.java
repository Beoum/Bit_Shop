package com.model2.mvc.framework;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.util.HttpUtil;


public class ActionServlet extends HttpServlet {
	
	private RequestMapping mapper;

	@Override
	public void init() throws ServletException {
		super.init();
		String resources=getServletConfig().getInitParameter("resources");
		mapper=RequestMapping.getInstance(resources);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
																									throws ServletException, IOException {
		
		String url = request.getRequestURI();
		String contextPath = request.getContextPath(); // ������Ʈ�� path�� �����´� http://localhost/ZESTINE/test.jsp �� ��� /ZESTINE
		String path = url.substring(contextPath.length());
		System.out.println(path);
		
		try{
			Action action = mapper.getAction(path); // ������ Class ��ȯ, ���� ó������
			action.setServletContext(getServletContext()); //servlet context setting
			
			String resultPage=action.execute(request, response); // ��� ��� return
			String result=resultPage.substring(resultPage.indexOf(":")+1); // ���� forward��� ��θ� ����

			if(resultPage.startsWith("forward:")) // �ش� ���ڿ��� �����ϸ�;
				HttpUtil.forward(request, response, result); // result�� path�� ����
			else
				HttpUtil.redirect(response, result);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}