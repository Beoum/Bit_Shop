package com.model2.mvc.service.purchase;

import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.purchase.domain.Purchase;

public interface PurchaseService {
	
	public void insertPurchase(Purchase purchase) throws Exception;
	
	public Map<String, Object> getPurchaseList(Search search, String userId) throws Exception;
	
	public Purchase findPurchase(int tranNo) throws Exception;
	
	public void updatePurchase(Purchase purchase) throws Exception;
	
	public void updateTranCode(Purchase purchase) throws Exception;
	
	public int getTotalCount(Search search) throws Exception;
}
