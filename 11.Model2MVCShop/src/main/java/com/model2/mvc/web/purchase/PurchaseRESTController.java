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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;

@RestController
@RequestMapping("/purchase/*")
public class PurchaseRESTController {
	
	private PurchaseService purchaseService;
	private ProductService productService;
	private UserService userService;
	
	public PurchaseRESTController() {
		System.out.println(this.getClass());
	}
	
	@Autowired
	public PurchaseRESTController(PurchaseService purchaseService, 
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
	
	@RequestMapping(value="/json/getPurchase", method=RequestMethod.GET)
	public Purchase getPurchase(Purchase purchase) throws Exception {
		
		System.out.println("/getPurhcase.do");
		
		Purchase purchaseView = purchaseService.findPurchase(purchase.getTranNo());
		
		return purchaseView;
	}
	

}


