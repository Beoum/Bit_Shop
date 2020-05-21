package com.model2.mvc.web.product;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.domain.Product;

@Controller
public class ProductController {
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	private ProductService productService;
	
	public ProductController() {
		System.out.println("Product default Constructor start().....");
	}
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService=productService;
		System.out.println("Controller <- Service AutoWired start");
	}
	
	@RequestMapping("/addProductView.do")
	public String addProductView() throws Exception {
		System.out.println("/addProductView.do");
		return "redirect:/product/addProductView.jsp";
	}
	
	@RequestMapping("/addProduct.do")
	public String addProduct(Product product) throws Exception {
		System.out.println("/addProduct.do");
		
		productService.addProduct(product);
		
		return "redirect:/listProduct.do?menu=manage";
	}
	
	@RequestMapping("/getProduct.do")
	public String getProduct(Product product, Model model) throws Exception{
		System.out.println("/getProduct.do");
		
		Product getProduct = productService.getProduct(product.getProdNo());
		
		model.addAttribute("product", getProduct);
		
		return "forward:/product/getProduct.jsp";
	}

	@RequestMapping("/updateProductView.do")
	public String updateProductView(Product product, Model model) throws Exception{
		System.out.println("/updateProductView.do");

		Product getProduct = productService.getProduct(product.getProdNo());
		
		model.addAttribute("product", getProduct);
		
		return "forward:/product/updateProductView.jsp";
	}
	
	@RequestMapping("/updateProduct")
	public String updateProduct(Product product, Model model) throws Exception {
		productService.updateProduct(product);
		return "redirect:/getProduct.do?prodNo=" + product.getProdNo();
	}
	
	@RequestMapping("/listProduct.do")
	public String getListProduct(@ModelAttribute("search") Search search , Model model , HttpServletRequest request) throws Exception{
		
		System.out.println("/product.do");
		
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

		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/product/listProduct.jsp";
	}
	
//	@RequestMapping("/updateProductView.do")
}
