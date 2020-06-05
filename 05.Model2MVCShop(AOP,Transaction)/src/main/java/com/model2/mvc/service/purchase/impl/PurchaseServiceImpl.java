package com.model2.mvc.service.purchase.impl;

import java.util.List;

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
	public List<Purchase> getPurchaseList(Search search, String userId) throws Exception {
		return purchaseDAO.getPurchaseList(search, userId);
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
