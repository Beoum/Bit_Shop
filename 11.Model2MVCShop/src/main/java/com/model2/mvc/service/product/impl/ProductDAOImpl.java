package com.model2.mvc.service.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDAO;

//@Repository
public class ProductDAOImpl implements ProductDAO {
	
	private SqlSession sqlSession;
	
	public ProductDAOImpl(){
	}
	
	@Autowired
	public ProductDAOImpl(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
	}
	
	// 신규 제품 등록
	public void insertProduct(Product product) throws Exception {
		sqlSession.insert("ProductMapper.addProduct", product);
	}

	// 제품 상세정보 조회
	public Product findProduct(int prodNo) throws Exception {
		return sqlSession.selectOne("ProductMapper.getProduct", prodNo);
	}

	// 제품 정보 업데이트
	public void updateProduct(Product product) throws Exception {
		sqlSession.update("ProductMapper.updateProduct", product);
	}
	
	// 관리자 페이지에서 조회하는 제품 리스트
	public List<Product> getProductList(Search search) throws Exception {
		return sqlSession.selectList("ProductMapper.getProductList", search);
	}

	// 관리자 페이지에서 조회한 제품의 총 숫자
	@Override
	public int getTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("ProductMapper.getTotalCount", search);
	}

	// 제품 검색 페이지에서 조회하는 제품 리스트
	@Override
	public List<Product> getProductListSearch(Search search) throws Exception {
		return sqlSession.selectList("ProductMapper.getProductListSearch", search);
	}

	// 제품 검색 페이지에서 조회한 제품의 총 숫자
	@Override
	public int getTotalCountSearch(Search search) throws Exception {
		return sqlSession.selectOne("ProductMapper.getTotalCountSearch", search);
	}

}
