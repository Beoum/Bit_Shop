package com.model2.mvc.view.purchase;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

public class AddPurchaseAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 구매자 정보
		UserVO buyer = new UserVO();
		buyer.setUserId(request.getParameter("buyerId"));
		
		// 상품정보
		ProductVO purchaseProd = new ProductVO();
		purchaseProd.setProdNo(Integer.parseInt(request.getParameter("prodNo")));
		
		// Transaction에 구매자정보와 상품정보 등록
		PurchaseVO purchaseVO = new PurchaseVO();
		purchaseVO.setBuyer(buyer);
		purchaseVO.setPurchaseProd(purchaseProd);
		
		// 주문정보 입력
		purchaseVO.setPaymentOption(request.getParameter("paymentOption"));	// 결제방법
		purchaseVO.setReceiverName(request.getParameter("receiverName"));	// 수령자 이름
		purchaseVO.setReceiverPhone(request.getParameter("receiverPhone"));	// 수령자 전화번호
		purchaseVO.setDivyAddr(request.getParameter("receiverAddr"));	// 배송 주소
		purchaseVO.setDivyRequest(request.getParameter("receiverRequest"));	// 배송요청사항
		purchaseVO.setDivyDate(request.getParameter("receiverDate"));	// 배송희망일

		// 배송상태코드 셋팅해야함
		// 주문일시 sysdate 셋팅	
		
		PurchaseService service = new PurchaseServiceImpl();
		service.addPurchase(purchaseVO);
		
		// 요청완료시 product status update 해양함
		
		request.setAttribute("purchaseVO", purchaseVO);
		
		return "forward:/purchase/addPurchase.jsp";
	}

}
