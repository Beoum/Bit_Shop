package com.model2.mvc.service.product.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.product.ProductDAO;
import com.model2.mvc.service.product.domain.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {
	
	private SqlSession sqlSession;
	
	public ProductDAOImpl(){
	}
	
	@Autowired
	public ProductDAOImpl(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
	}
	
	public void insertProduct(Product product) throws Exception {
		sqlSession.insert("ProductMapper.addProduct", product);
	}

	public Product findProduct(int prodNo) throws Exception {
		return sqlSession.selectOne("ProductMapper.getProduct", prodNo);
	}

	public void updateProduct(Product product) throws Exception {
		sqlSession.update("ProductMapper.updateProduct", product);
	}
	

	public List<Product> getProductList(Search search) throws Exception {
		return sqlSession.selectList("ProductMapper.getProductList", search);
	}


	
	// 게시판 currentPage Row 만  return 
	private String makeCurrentPageSql(String sql , Search search){
		return null;
	}

	@Override
	public int getTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("ProductMapper.getTotalCount", search);
	}

}
