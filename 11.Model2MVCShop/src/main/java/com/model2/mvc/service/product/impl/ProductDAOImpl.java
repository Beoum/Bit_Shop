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
	
	// �ű� ��ǰ ���
	public void insertProduct(Product product) throws Exception {
		sqlSession.insert("ProductMapper.addProduct", product);
	}

	// ��ǰ ������ ��ȸ
	public Product findProduct(int prodNo) throws Exception {
		return sqlSession.selectOne("ProductMapper.getProduct", prodNo);
	}

	// ��ǰ ���� ������Ʈ
	public void updateProduct(Product product) throws Exception {
		sqlSession.update("ProductMapper.updateProduct", product);
	}
	
	// ������ ���������� ��ȸ�ϴ� ��ǰ ����Ʈ
	public List<Product> getProductList(Search search) throws Exception {
		return sqlSession.selectList("ProductMapper.getProductList", search);
	}

	// ������ ���������� ��ȸ�� ��ǰ�� �� ����
	@Override
	public int getTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("ProductMapper.getTotalCount", search);
	}

	// ��ǰ �˻� ���������� ��ȸ�ϴ� ��ǰ ����Ʈ
	@Override
	public List<Product> getProductListSearch(Search search) throws Exception {
		return sqlSession.selectList("ProductMapper.getProductListSearch", search);
	}

	// ��ǰ �˻� ���������� ��ȸ�� ��ǰ�� �� ����
	@Override
	public int getTotalCountSearch(Search search) throws Exception {
		return sqlSession.selectOne("ProductMapper.getTotalCountSearch", search);
	}

}
