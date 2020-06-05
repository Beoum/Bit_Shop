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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.domain.Product;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.domain.Purchase;

@Controller
@RequestMapping("/product/*")
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
	
	@RequestMapping(value="addProductView", method=RequestMethod.GET)
	public String addProductView() throws Exception {
		System.out.println("/addProductView.do");
		return "redirect:/product/addProductView.jsp";
	}
	
	@RequestMapping(value="addProduct", method=RequestMethod.POST)
	public String addProduct(Product product, MultipartHttpServletRequest mpRequest) throws Exception {
		System.out.println("/addProduct.do");
		        
		productService.addProduct(product, mpRequest);
		
		return "redirect:/product/listProduct.do?menu=manage";
	}
	
	@RequestMapping( value="getProduct", method=RequestMethod.GET)
	public String getProduct(Product product, Model model) throws Exception{
		System.out.println("/getProduct.do");
		
		Product getProduct = productService.getProduct(product.getProdNo());
		
		model.addAttribute("product", getProduct);
		
		return "forward:/product/getProduct.jsp";
	}

	@RequestMapping(value="updateProductView", method=RequestMethod.GET)
	public String updateProductView(Product product, Model model) throws Exception{
		System.out.println("/updateProductView.do");

		Product getProduct = productService.getProduct(product.getProdNo());
		
		model.addAttribute("product", getProduct);
		
		return "forward:/product/updateProductView.jsp";
	}
	
	@RequestMapping(value="updateProduct", method=RequestMethod.POST)
	public String updateProduct(Product product, Model model) throws Exception {
		productService.updateProduct(product);
		return "redirect:/product/getProduct.do?prodNo=" + product.getProdNo();
	}
	
	@RequestMapping( value="listProduct")
	public String listProduct(Search search , Model model) throws Exception{
		
		System.out.println("/listpProduct");
		System.out.println("search : " + search);
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
	

	
}
