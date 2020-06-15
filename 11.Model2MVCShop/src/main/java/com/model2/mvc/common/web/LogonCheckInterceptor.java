package com.model2.mvc.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.model2.mvc.service.domain.User;

public class LogonCheckInterceptor extends HandlerInterceptorAdapter {

	public LogonCheckInterceptor(){
		
	}
	
	public boolean preHandle(	HttpServletRequest request,
														HttpServletResponse response, 
														Object handler) throws Exception {
		
		
		// �α��� ����Ȯ��
		HttpSession session = request.getSession(true);
		User user = (User)session.getAttribute("user");

		// �α����� ȸ���̶��
		if(   user != null   )  {
			// �α��� ���¿��� ���� �Ұ� URI
			String uri = request.getRequestURI();
			
			if(		uri.indexOf("addUser") != -1 ||	uri.indexOf("login") != -1 		|| 
					uri.indexOf("checkDuplication") != -1 ){
				request.getRequestDispatcher("/index.jsp").forward(request, response);
				return false;
			}
			
			return true;
		}else{ // �� �α����� ȭ���̶��
			// �α��� �õ� ��
			String uri = request.getRequestURI();
			
			if(		uri.indexOf("addUser") != -1 ||	uri.indexOf("login") != -1 		|| 
					uri.indexOf("checkDuplication") != -1 ){
				return true;
			}
			
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return false;
		}
	}
}//end of class