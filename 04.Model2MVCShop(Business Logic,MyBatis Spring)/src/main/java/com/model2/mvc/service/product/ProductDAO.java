package com.model2.mvc.service.product;

import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.product.vo.ProductVO;

public interface ProductDAO {
	public void insertProduct(ProductVO productVO) throws Exception;
	
	public ProductVO findProduct(int prodno) throws Exception;
	
	public void updateProduct(ProductVO productVO) throws Exception;
	
	public List<ProductVO> getProductList(Search searchVO) throws Exception;
	
	public int getTotalCount(Search search) throws Exception;
	
}
