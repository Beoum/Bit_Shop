package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class UpdateTranCodeByProdAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductVO productVO = new ProductVO(); 
		PurchaseVO purchaseVO = new PurchaseVO();
		
		productVO.setProdNo(Integer.parseInt(request.getParameter("prodNo")));
		
		purchaseVO.setTranCode(request.getParameter("tranCode"));
		purchaseVO.setPurchaseProd(productVO);

		PurchaseService service = new PurchaseServiceImpl();
		service.updateTranCode(purchaseVO);
		
		return "forward:/listProduct.do?menu=manage";
	}

}
