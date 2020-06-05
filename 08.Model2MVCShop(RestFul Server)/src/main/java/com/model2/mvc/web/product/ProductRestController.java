package com.model2.mvc.web.product;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.domain.Product;

@RestController
@RequestMapping("/product/*")
public class ProductRestController {
	
	@Autowired
	private ProductService productService;
	
	public ProductRestController() {
		System.out.println(":: Product RestController Start...");
	}
	
	@RequestMapping(value="/json/getProduct/{prodNo}", method=RequestMethod.GET)
	public Product getProduct(@PathVariable int prodNo) throws Exception {
		return productService.getProduct(prodNo);
	}
	
	@RequestMapping(value="/json/getProduct", method=RequestMethod.POST)
	public Product getProductPost(@RequestBody Product product) throws Exception {
		return productService.getProduct(product.getProdNo());
	}
	
	@RequestMapping(value="/json/listProduct", method=RequestMethod.GET)// list로 return 해야할듯?
	public Map listProduct(Search search) throws Exception{
		int pageSize= 10;
		int pageUnit= 5;

		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		
		if(search.getSearchCondition() == null) {
			search.setSearchCondition("0");
		}
		
		if(search.getSearchKeyword() == null) {
			search.setSearchKeyword("");
		}
		
		search.setPageSize(pageSize);
		// Business logic 수행
		Map<String , Object> map= productService.getProductList(search);
		System.out.println("map : " + map);
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		return map;
		
	}
	
	@RequestMapping(value="/json/listProduct", method=RequestMethod.POST)// list로 return 해야할듯?
	public Map listProductPost(@RequestBody Search search) throws Exception{
		int pageSize= 10;
		int pageUnit= 5;

		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		
		if(search.getSearchCondition() == null) {
			search.setSearchCondition("0");
		}
		
		if(search.getSearchKeyword() == null) {
			search.setSearchKeyword("");
		}
		
		search.setPageSize(pageSize);
		// Business logic 수행
		Map<String , Object> map= productService.getProductList(search);
		System.out.println("map : " + map);
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		return map;
		
	}
}
