package com.model2.mvc.service.product;

import java.util.HashMap;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.vo.ProductVO;


public interface ProductService {
	
	public void addProduct(ProductVO productVO) throws Exception;

	public ProductVO getProduct(int prodNo) throws Exception;

	public HashMap<String,Object> getProductList(Search searchVO) throws Exception;

	public void updateProduct(ProductVO productVO) throws Exception;
	
}