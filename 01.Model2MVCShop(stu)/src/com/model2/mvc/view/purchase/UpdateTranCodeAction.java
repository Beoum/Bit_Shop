package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class UpdateTranCodeAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PurchaseVO purchaseVO = new PurchaseVO();
		
		purchaseVO.setTranCode(request.getParameter("tranCode"));
		purchaseVO.setTranNo(Integer.parseInt(request.getParameter("tranNo")));

		PurchaseService service = new PurchaseServiceImpl();
		service.updateTranCode(purchaseVO);
		
		return "forward:/listPurchase.do";
	}

}
