package com.model2.mvc.service.product.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.product.ProductDAO;
import com.model2.mvc.service.product.vo.ProductVO;

@Repository
public class ProductDAOImpl implements ProductDAO {
	
	private SqlSession sqlSession;
	
	public ProductDAOImpl(){
	}
	
	@Autowired
	public ProductDAOImpl(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
	}
	
	public void insertProduct(ProductVO productVO) throws Exception {
		sqlSession.insert("ProductMapper.addProduct", productVO);
	}

	public ProductVO findProduct(int prodNo) throws Exception {
		return sqlSession.selectOne("ProductMapper.getProduct", prodNo);
	}

	public void updateProduct(ProductVO productVO) throws Exception {
		sqlSession.update("ProductMapper.updateProduct", productVO);
	}
	
	// ȣ���ϴ� method return type list�� �����ϼ���.
	public List<ProductVO> getProductList(Search searchVO) throws Exception {
		return sqlSession.selectList("ProductMapper.getProductList", searchVO);
	}


	
	// �Խ��� currentPage Row ��  return 
	private String makeCurrentPageSql(String sql , Search search){
		return null;
	}

	@Override
	public int getTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("ProductMapper.getTotalCount", search);
	}
}
