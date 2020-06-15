package com.model2.mvc.service.product;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;

@Mapper
public interface ProductDAO {
	public void insertProduct(Product productVO) throws Exception;
	
	public Product findProduct(int prodno) throws Exception;
	
	public void updateProduct(Product productVO) throws Exception;
	
	public List<Product> getProductList(Search searchVO) throws Exception;
	
	public List<Product> getProductListSearch(Search search) throws Exception;
	
	public int getTotalCount(Search search) throws Exception;
	
	public int getTotalCountSearch(Search search) throws Exception;
	
}
