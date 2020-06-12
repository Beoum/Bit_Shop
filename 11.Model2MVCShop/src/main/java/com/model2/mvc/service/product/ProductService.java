package com.model2.mvc.service.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;

public interface ProductService {
	
	public void addProduct(Product product, MultipartHttpServletRequest mpRequest) throws Exception;

	public Product getProduct(int prodNo) throws Exception;

	public Map<String, Object> getProductList(Search search) throws Exception;

	public void updateProduct(Product product) throws Exception;
	
	public int getTotalCount(Search search) throws Exception; 
	
	public String uploadFile(MultipartHttpServletRequest mpRequest) throws Exception;
	
	public String saveFileName(String originFileName);
	
}