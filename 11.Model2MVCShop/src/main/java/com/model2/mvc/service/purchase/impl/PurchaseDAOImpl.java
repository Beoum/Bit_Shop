package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDAO;

//@Repository
public class PurchaseDAOImpl implements PurchaseDAO {
	
	private SqlSession sqlSession;
	
	public PurchaseDAOImpl() {
	}
	
	@Autowired
	public PurchaseDAOImpl(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
	}
	
	@Override
	public void insertPurchase(Purchase purchase) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("purchase", purchase);
		map.put("userId", purchase.getBuyer().getUserId());
		map.put("prodNo", purchase.getPurchaseProd().getProdNo());
		
		sqlSession.insert("PurchaseMapper.addPurchase", map);
	}

	@Override
	public List<Purchase> getPurchaseList(Search search, String userId) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", search);
		map.put("userId", userId);
		return sqlSession.selectList("PurchaseMapper.getPurchaseList", map);
	}

	@Override
	public Purchase findPurchase(int tranNo) throws Exception {
		return sqlSession.selectOne("PurchaseMapper.getPurchase", tranNo);
	}

	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		sqlSession.update("PurchaseMapper.updatePurchase", purchase);
	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("purchase", purchase);
		if(purchase.getPurchaseProd() != null) {
			map.put("prodNo", purchase.getPurchaseProd().getProdNo());
		}else {
			map.put("prodNo", "");
		}
		sqlSession.update("PurchaseMapper.updateTranCode", map);
	}

	@Override
	public int getTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("PurchaseMapper.getTotalCount", search);
	}

}
