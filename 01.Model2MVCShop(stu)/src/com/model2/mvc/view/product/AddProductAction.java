package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;

public class AddProductAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ProductVO productVO=new ProductVO();
		productVO.setProdName(request.getParameter("prodName"));
		productVO.setProdDetail(request.getParameter("prodDetail"));
		productVO.setManuDate(request.getParameter("manuDate").substring(0, 4)+request.getParameter("manuDate").substring(5, 7)+request.getParameter("manuDate").substring(8));
		productVO.setPrice(Integer.parseInt(request.getParameter("price")));
		productVO.setFileName(request.getParameter("fileName"));

		System.out.println(productVO);
		ProductService service = new ProductServiceImpl();
		service.addProduct(productVO);
		request.setAttribute("ProductVO", productVO);
		
		return "forward:/product/addProduct.jsp";
	}

}
