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
		
		
		// 로그인 유무확인
		HttpSession session = request.getSession(true);
		User user = (User)session.getAttribute("user");

		// 로그인한 회원이라면
		if(   user != null   )  {
			// 로그인 상태에서 접근 불가 URI
			String uri = request.getRequestURI();
			
			if(		uri.indexOf("addUser") != -1 ||	uri.indexOf("login") != -1 		|| 
					uri.indexOf("checkDuplication") != -1 ){
				request.getRequestDispatcher("/index.jsp").forward(request, response);
				return false;
			}
			
			return true;
		}else{ // 미 로그인한 화원이라면
			// 로그인 시도 중
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