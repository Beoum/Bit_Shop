package com.model2.mvc.web.product;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		System.out.println("/addProduct");
		
		productService.addProduct(product, mpRequest);
		
		return "redirect:/product/listProduct.do?menu=manage";
	}
	
	@RequestMapping( value="getProduct", method=RequestMethod.GET)
	public String getProduct(Product product, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("/getProduct");
		
		String cookieValue = product.getProdNo()+"";
		String history = "";
		System.out.println("cookie value : " + cookieValue);
		if(request.getCookies() != null) {
				for(Cookie cookie: request.getCookies()) {
					System.out.println("쿠키의 name : " + cookie.getName());
					System.out.println("쿠키의 value() : " + cookie.getValue());
					String[] h = cookie.getValue().split(",");
					System.out.println("origin h : " + h);
					for(int i = 0 ; i < h.length ; i++) {
						System.out.println("h[i] 의 값 : " + h[i]);
						System.out.println("h의 길이 : " + h.length);
						if(h[i].equals(request.getParameter("prodNo").trim())) {
							cookieValue="";
							break;
						}
						if(h[i].length() > 9 && h.length == 1) {
							System.out.println("length if 실행");
							System.out.println("h[i] : " + h[i]);
							history = cookieValue;
							System.out.println("length의 history : " + history );
						}
						
						if(h[i].length() > 9) {
							cookie.setValue(cookieValue);
						}
						
					}
					if(cookieValue.equals("")) { // 중복이면 실행되는 구문
						System.out.println("1번 if");
						System.out.println("getValue() : " + cookie.getValue());
						history = cookie.getValue();
					}else if(cookie.getValue().equals("")){ // 현재 쿠키가 빈문자열이면 실행
						System.out.println("2번 if");
						history += cookieValue;
					}else if(history.length() != 5){ // 그외 실행
						System.out.println("3번 if의 history : " + history);
						System.out.println("3번 if의 value : " + cookie.getValue());
						history += "," + cookie.getValue();
					}
				}
			response.addCookie(new Cookie("JSESSIONID", history));
			System.out.println("cookie 추가 최종 histroy : " + history);
		}
		
		
		Product getProduct = productService.getProduct(product.getProdNo());
		
		model.addAttribute("product", getProduct);
		
		return "forward:/product/getProduct.jsp";
	}

	@RequestMapping(value="updateProductView", method=RequestMethod.GET)
	public String updateProductView(Product product, Model model) throws Exception{
		System.out.println("/updateProductView");

		Product getProduct = productService.getProduct(product.getProdNo());
		
		model.addAttribute("product", getProduct);
		
		return "forward:/product/updateProductView.jsp";
	}
	
	@RequestMapping(value="updateProduct", method=RequestMethod.POST)
	public String updateProduct(Product product, Model model) throws Exception {
		productService.updateProduct(product);
		return "redirect:/product/getProduct?prodNo=" + product.getProdNo();
	}
	
	@RequestMapping( value="listProduct")
	public String listProduct(Search search , Model model) throws Exception{
		
		System.out.println("/listpProduct");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}else if(search.getCurrentPage() !=0 && !search.getSearchKeyword().equals("") && search.getSearchKeyword() != null) {
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
		try {
			Map<String , Object> map= productService.getProductList(search);
			
			Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
			
			// Model 과 View 연결
			model.addAttribute("list", map.get("list"));
			model.addAttribute("resultPage", resultPage);
			model.addAttribute("search", search);
			
		} catch (Exception e) {
			model.addAttribute("search", search);
			return "forward:/common/searchError.jsp";
		}
		
		return "forward:/product/listProduct.jsp";
	}
	

	
}
