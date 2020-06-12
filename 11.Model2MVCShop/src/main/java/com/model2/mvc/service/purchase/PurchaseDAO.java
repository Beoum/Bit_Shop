package com.model2.mvc.service.purchase;

import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;

public interface PurchaseDAO {
	
	public void insertPurchase(Purchase purchase) throws Exception;
	
	public List<Purchase> getPurchaseList(Search search, String userId) throws Exception;
	
	public Purchase findPurchase(int tranNo) throws Exception;
	
	public void updatePurchase(Purchase purchase) throws Exception;
	
	public void updateTranCode(Purchase purchase) throws Exception;
	
	public int getTotalCount(Search search) throws Exception;
}
