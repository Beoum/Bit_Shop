package com.model2.mvc.web.product;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;

@RestController
@RequestMapping("/product/*")
public class ProductRestController {
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	private ProductService productService;
	
	public ProductRestController() {
		System.out.println("Product default Constructor start().....");
	}
	
	@Autowired
	public ProductRestController(ProductService productService) {
		this.productService=productService;
		System.out.println("Controller <- Service AutoWired start");
	}
	
	// 상품 목록에서 ajax처리를 위한 메소드
	@RequestMapping( value="/json/getProduct", method=RequestMethod.GET)
	public Product getProductJSON(Product product, Model model) throws Exception{
		System.out.println("/json/getProduct");
		
		Product getProduct = productService.getProduct(product.getProdNo());
		
		return getProduct;
	}
}
