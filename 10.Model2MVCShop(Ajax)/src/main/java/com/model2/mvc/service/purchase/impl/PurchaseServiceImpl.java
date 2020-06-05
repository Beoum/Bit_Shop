package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.purchase.PurchaseDAO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.domain.Purchase;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	private PurchaseDAO purchaseDAO;
	
	public PurchaseServiceImpl() {
		// TODO Auto-generated constructor stub
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
		map.put("list", purchaseDAO.getPurchaseList(search, userId));
		map.put("totalCount", purchaseDAO.getTotalCount(search));
		return map;
	}

	@Override
	public Purchase findPurchase(int tranNo) throws Exception {
		return purchaseDAO.findPurchase(tranNo);
	}

	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		purchaseDAO.updatePurchase(purchase);
	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		purchaseDAO.updateTranCode(purchase);
	}

	@Override
	public int getTotalCount(Search search) throws Exception {
		return purchaseDAO.getTotalCount(search);
	}

}
