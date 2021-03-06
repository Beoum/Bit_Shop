package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDAO;
import com.model2.mvc.service.purchase.PurchaseService;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	private PurchaseDAO purchaseDAO;
	
	public PurchaseServiceImpl() {

	}
	
	@Autowired
	public PurchaseServiceImpl(PurchaseDAO purchaseDAO) {
		this.purchaseDAO=purchaseDAO;
	}
	
	@Override
	public void insertPurchase(Purchase purchase) throws Exception {
		purchaseDAO.insertPurchase(purchase);
	}

	@Override
	public Map<String, Object> getPurchaseList(Search search, String userId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", search);
		map.put("userId", userId);
		map.put("list", purchaseDAO.getPurchaseList(map));
		map.put("totalCount", purchaseDAO.getTotalCount(search));
		return map;
	}

	@Override
	public Purchase findPurchase(int tranNo) throws Exception {
		Purchase purchase = purchaseDAO.findPurchase(tranNo);
		purchase.setDivyDate(purchase.getDivyDate().substring(0, 4) +"-"+ purchase.getDivyDate().substring(5, 7) +"-"+ purchase.getDivyDate().substring(8,10));
		return purchase;
	}

	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		purchaseDAO.updatePurchase(purchase);
	}
	
	// 상품 상태 코드를 업데이트 하는 메소드
	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		purchaseDAO.updateTranCode(purchase);
	}
	
	// 구매 품목 리스트의 총 갯수를 조회하는 메소드
	@Override
	public int getTotalCount(Search search) throws Exception {
		return purchaseDAO.getTotalCount(search);
	}

}
