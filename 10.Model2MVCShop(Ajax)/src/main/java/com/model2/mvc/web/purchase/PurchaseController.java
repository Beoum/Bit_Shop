package com.model2.mvc.web.purchase;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.domain.Product;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.domain.Purchase;
import com.model2.mvc.service.user.UserService;

@Controller
@RequestMapping("/purchase/*")
public class PurchaseController {
	
	private PurchaseService purchaseService;
	private ProductService productService;
	private UserService userService;
	
	public PurchaseController() {
		System.out.println(this.getClass());
	}
	
	@Autowired
	public PurchaseController(PurchaseService purchaseService, 
								ProductService productService, UserService userService) {
		this.purchaseService=purchaseService;
		this.productService=productService;
		this.userService=userService;
	}
	
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	@RequestMapping(value="addPurchaseView")
	public ModelAndView addPurchaseView(Model model, @RequestParam("prod_no") int prodNo, HttpSession session) throws Exception {
		Product productInfo = productService.getProduct(prodNo);
		User userInfo = userService.getUser(((User)session.getAttribute("user")).getUserId());
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/purchase/addPurchaseView.jsp");
		modelAndView.addObject("user", userInfo);
		modelAndView.addObject("product", productInfo);
		
		return modelAndView;
	}
	
	@RequestMapping(value="addPurchase")
	public ModelAndView addPurchase(User user, Product product, Purchase purchase) throws Exception{
		System.out.println("/addPurchase.do");
		
		purchase.setBuyer(user);
		purchase.setPurchaseProd(product);

		purchaseService.insertPurchase(purchase);
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("/purchase/addPurchase.jsp");
		modelAndView.addObject("purchase", purchase);
		
		return modelAndView;
	}
	
	@RequestMapping(value="listPurchase")
	public ModelAndView listPurchase(Search search, HttpSession session) throws Exception {
		System.out.println("/listPurchase.do");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		
		search.setPageSize(pageSize);
		
		Map<String, Object> map = purchaseService.getPurchaseList(search, ((User)session.getAttribute("user")).getUserId());
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("list", map.get("list"));
		modelAndView.addObject("resultPage", resultPage);
		
		modelAndView.setViewName("/purchase/listPurchase.jsp");
		System.out.println("map : " + map);
		System.out.println("resultPage : " + resultPage);
		return modelAndView;
	}
	
	@RequestMapping(value="getPurchase", method=RequestMethod.GET)
	public ModelAndView getPurchase(Purchase purchase) throws Exception {
		
		System.out.println("/getPurhcase.do");
		
		Purchase view = purchaseService.findPurchase(purchase.getTranNo());
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("purchase", view);
		
		modelAndView.setViewName("/purchase/getPurchase.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping("/updatePurchaseView")
	public ModelAndView updatePurchaseView(Purchase purchase) throws Exception {
		
		Purchase originalInfo = purchaseService.findPurchase(purchase.getTranNo());
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("purchase", originalInfo);
		
		modelAndView.setViewName("/purchase/updatePurchaseView.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping("/updatePurchase")
	public ModelAndView updatePurchase(Purchase purchase) throws Exception {
		
		purchaseService.updatePurchase(purchase);
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("purchase", purchaseService.findPurchase(purchase.getTranNo()));
		
		modelAndView.setViewName("/purchase/getPurchase.jsp?tranNo=" + purchase.getTranNo());
		
		return modelAndView;
	}
	
	@RequestMapping(value="updateTranCodeByProd", method=RequestMethod.GET)
	public String updateTranCodeByProd(Search search, Purchase purchase, 
										Model model, 
										@RequestParam("menu") String menu,
										Product product) throws Exception {
		
		System.out.println("Search : " + search);
		System.out.println("Purchase : " + purchase);
		
		if(search.getSearchCondition() == null) {
			search.setSearchCondition("0");
		}
		
		if(search.getSearchKeyword() == null) {
			search.setSearchKeyword("");
		}
		
		purchase.setPurchaseProd(product);
		
		purchaseService.updateTranCode(purchase);
		
		search.setPageSize(pageSize);
		// Business logic ผ๖วเ
		Map<String , Object> map= productService.getProductList(search);

		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		model.addAttribute("menu", menu);
		
		return "forward:/product/listProduct.jsp";
	}
	
	@RequestMapping(value="updateTranCode", method=RequestMethod.GET)
	public ModelAndView updateTranCode(Product product, Purchase purchase, Search search, HttpSession session) throws Exception {
		
		purchaseService.updateTranCode(purchase);
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		
		search.setPageSize(pageSize);
		
		Map<String, Object> map = purchaseService.getPurchaseList(search, ((User)session.getAttribute("user")).getUserId());
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("list", map.get("list"));
		modelAndView.addObject("resultPage", resultPage);
		
		modelAndView.setViewName("/purchase/listPurchase.jsp");
		System.out.println("map : " + map);
		System.out.println("resultPage : " + resultPage);
		
		return modelAndView;
	}
}


