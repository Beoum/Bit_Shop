package com.model2.mvc.service.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.product.ProductDAO;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAOImpl;
import com.model2.mvc.service.product.vo.ProductVO;

@Service
public class ProductServiceImpl implements ProductService {
	
	private ProductDAO productDAO;
	
	public ProductServiceImpl() {
	}
	
	@Autowired
	public ProductServiceImpl(ProductDAO productDAO) {
		this.productDAO=productDAO;
	}
	
	@Override
	public void addProduct(ProductVO productVO) throws Exception {
		productDAO.insertProduct(productVO);
	}

	@Override
	public ProductVO getProduct(int prodNo) throws Exception {
		return productDAO.findProduct(prodNo);
	}

	@Override
	public List<ProductVO> getProductList(Search searchVO) throws Exception {
		return productDAO.getProductList(searchVO);
	}

	@Override
	public void updateProduct(ProductVO productVO) throws Exception {
		productDAO.updateProduct(productVO);
	}

	@Override
	public int getTotalCount(Search search) throws Exception {
		return productDAO.getTotalCount(search);
	}

}
