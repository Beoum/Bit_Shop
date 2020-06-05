package com.model2.mvc.service.product;

import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.product.domain.Product;

public interface ProductDAO {
	public void insertProduct(Product productVO) throws Exception;
	
	public Product findProduct(int prodno) throws Exception;
	
	public void updateProduct(Product productVO) throws Exception;
	
	public List<Product> getProductList(Search searchVO) throws Exception;
	
	public int getTotalCount(Search search) throws Exception;
	
}
