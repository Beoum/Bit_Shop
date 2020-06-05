package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

public class UpdatePurchaseAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PurchaseVO purchaseVO = new PurchaseVO();
		User userVO = new User();
		
		userVO.setUserId(request.getParameter("buyerId"));
		
		purchaseVO.setBuyer(userVO);
		purchaseVO.setTranNo(Integer.parseInt(request.getParameter("tranNo")));
		purchaseVO.setPaymentOption(request.getParameter("paymentOption"));
		purchaseVO.setReceiverName(request.getParameter("receiverName"));
		purchaseVO.setReceiverPhone(request.getParameter("receiverPhone"));
		purchaseVO.setDivyAddr(request.getParameter("receiverAddr"));
		purchaseVO.setDivyRequest(request.getParameter("receiverRequest"));
		purchaseVO.setDivyDate(request.getParameter("divyDate"));
		
		PurchaseService service = new PurchaseServiceImpl();
		service.updatePurcahse(purchaseVO);
		PurchaseVO vo = service.getPurchase(Integer.parseInt(request.getParameter("tranNo")));
		
		request.setAttribute("vo", vo);
		return "forward:/purchase/getPurchase.jsp";
	}

}
