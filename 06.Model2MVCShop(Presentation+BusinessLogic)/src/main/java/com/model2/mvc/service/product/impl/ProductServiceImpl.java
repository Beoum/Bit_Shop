package com.model2.mvc.service.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.product.ProductDAO;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAOImpl;
import com.model2.mvc.service.product.domain.Product;

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
	public void addProduct(Product product) throws Exception {
		Product date = new Product();
		date.setManuDate(product.getManuDate().substring(0, 4)+product.getManuDate().substring(5, 7)+product.getManuDate().substring(8));
		product.setManuDate(date.getManuDate());
		productDAO.insertProduct(product);
	}

	@Override
	public Product getProduct(int prodNo) throws Exception {
		Product product = productDAO.findProduct(prodNo);
		if(product.getProTranCode() == null) {
			product.setProTranCode("0");
		}
		
		return product;
	}

	@Override
	public Map<String, Object> getProductList(Search search) throws Exception {
		List<Product> list = productDAO.getProductList(search);
		for(int i = 0; i < list.size() ; i++) {
			if(list.get(i).getProTranCode() == null) {
				list.get(i).setProTranCode("0");
			}
		}
		int totalCount = productDAO.getTotalCount(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}

	@Override
	public void updateProduct(Product productVO) throws Exception {
		productDAO.updateProduct(productVO);
	}

	@Override
	public int getTotalCount(Search search) throws Exception {
		if(search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}

		search.setPageSize(3);
		return productDAO.getTotalCount(search);
	}

}
