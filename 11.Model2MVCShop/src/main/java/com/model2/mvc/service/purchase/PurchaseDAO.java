package com.model2.mvc.service.purchase;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;

@Mapper
public interface PurchaseDAO {
	
	public void insertPurchase(Purchase purchase) throws Exception;
	
	public List<Purchase> getPurchaseList(Map<String, Object> map) throws Exception;
	
	public Purchase findPurchase(int tranNo) throws Exception;
	
	public void updatePurchase(Purchase purchase) throws Exception;
	
	public void updateTranCode(Purchase purchase) throws Exception;
	
	public int getTotalCount(Search search) throws Exception;
}
