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
		
		// ������ ����
		UserVO buyer = new UserVO();
		buyer.setUserId(request.getParameter("buyerId"));
		
		// ��ǰ����
		ProductVO purchaseProd = new ProductVO();
		purchaseProd.setProdNo(Integer.parseInt(request.getParameter("prodNo")));
		
		// Transaction�� ������������ ��ǰ���� ���
		PurchaseVO purchaseVO = new PurchaseVO();
		purchaseVO.setBuyer(buyer);
		purchaseVO.setPurchaseProd(purchaseProd);
		
		// �ֹ����� �Է�
		purchaseVO.setPaymentOption(request.getParameter("paymentOption"));	// �������
		purchaseVO.setReceiverName(request.getParameter("receiverName"));	// ������ �̸�
		purchaseVO.setReceiverPhone(request.getParameter("receiverPhone"));	// ������ ��ȭ��ȣ
		purchaseVO.setDivyAddr(request.getParameter("receiverAddr"));	// ��� �ּ�
		purchaseVO.setDivyRequest(request.getParameter("receiverRequest"));	// ��ۿ�û����
		purchaseVO.setDivyDate(request.getParameter("receiverDate"));	// ��������

		// ��ۻ����ڵ� �����ؾ���
		// �ֹ��Ͻ� sysdate ����	
		
		PurchaseService service = new PurchaseServiceImpl();
		service.addPurchase(purchaseVO);
		
		// ��û�Ϸ�� product status update �ؾ���
		
		request.setAttribute("purchaseVO", purchaseVO);
		
		return "forward:/purchase/addPurchase.jsp";
	}

}
