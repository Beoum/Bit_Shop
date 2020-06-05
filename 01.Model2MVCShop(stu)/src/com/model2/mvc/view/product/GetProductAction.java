package com.model2.mvc.view.product;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;

public class GetProductAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		int prodNo= Integer.parseInt(request.getParameter("prodNo"));
		String cookieValue = request.getParameter("prodNo");
		String history = "";

		if(request.getCookies() != null) {
				for(Cookie cookie: request.getCookies()) {
					if(!(cookie.getName().equals("JSESSIONID"))) {
						String[] h = cookie.getValue().split(",");
						for(int i = 0 ; i < h.length ; i++) {
							if(h[i].equals(request.getParameter("prodNo").trim())) {
								cookieValue="";
								continue;
							}
						}
						if(cookieValue.equals("")) {
							history = cookie.getValue();
						}else if(cookie.getValue().equals("")){
							history += cookieValue;
						}else {
							history += "," + cookie.getValue();
						}
					}else {
						history = cookieValue;
					}
				}
				response.addCookie(new Cookie("history", history));
		}

		
		
		// service
		ProductService service=new ProductServiceImpl();
		ProductVO vo=service.getProduct(prodNo);
		
		request.setAttribute("vo", vo);

		return "forward:/product/getProduct.jsp";
	}

}
