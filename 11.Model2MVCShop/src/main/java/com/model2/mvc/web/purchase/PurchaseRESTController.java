package com.model2.mvc.web.purchase;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;

@RestController
@RequestMapping("/purchase/*")
public class PurchaseRESTController {
	
	private PurchaseService purchaseService;
	
	public PurchaseRESTController() {
		System.out.println(this.getClass());
	}
	
	@Autowired
	public PurchaseRESTController(PurchaseService purchaseService, 
								ProductService productService, UserService userService) {
		this.purchaseService=purchaseService;
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	@RequestMapping(value="/json/getPurchase", method=RequestMethod.GET)
	public Purchase getPurchase(Purchase purchase) throws Exception {
		
		Purchase purchaseView = purchaseService.findPurchase(purchase.getTranNo());
		
		return purchaseView;
	}
	

}


