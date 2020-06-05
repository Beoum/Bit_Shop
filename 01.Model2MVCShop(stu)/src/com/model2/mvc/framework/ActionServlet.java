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
		String contextPath = request.getContextPath(); // 프로젝트의 path만 가져온다 http://localhost/ZESTINE/test.jsp 의 경우 /ZESTINE
		String path = url.substring(contextPath.length());
		System.out.println(path);
		
		try{
			Action action = mapper.getAction(path); // 실행할 Class 반환, 예외 처리까지
			action.setServletContext(getServletContext()); //servlet context setting
			
			String resultPage=action.execute(request, response); // 결과 경로 return
			String result=resultPage.substring(resultPage.indexOf(":")+1); // 만약 forward라면 경로만 추출

			if(resultPage.startsWith("forward:")) // 해당 문자열로 시작하면;
				HttpUtil.forward(request, response, result); // result에 path를 설정
			else
				HttpUtil.redirect(response, result);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}