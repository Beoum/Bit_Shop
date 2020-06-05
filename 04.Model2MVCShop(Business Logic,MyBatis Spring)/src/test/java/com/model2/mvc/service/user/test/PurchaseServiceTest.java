package com.model2.mvc.service.user.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.domain.Purchase;
import com.model2.mvc.service.user.UserService;


/*
 *	FileName :  UserServiceTest.java
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
public class PurchaseServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired
	private UserService userService;

	//@Test
	public void testAddPurchase() throws Exception {
		
		Purchase purchase = new Purchase();
		User user = new User();
		user.setUserId("test");
		ProductVO product = new ProductVO();
		product.setProdNo(10100);
		
		purchase.setPurchaseProd(product);
		purchase.setBuyer(user);
		purchase.setPaymentOption("0");
		purchase.setReceiverName("받는myBatis");
		purchase.setReceiverPhone("테스트번");
		purchase.setDivyAddr("테스트주소");
		purchase.setDivyRequest("테스트요청");
		purchase.setDivyDate("20200514");
		
		
		purchaseService.insertPurchase(purchase);
	}
	
	@Test
	public void testGetUser() throws Exception {
		
		Purchase purchase = purchaseService.findPurchase(10070);
		
		System.out.println("=======================================");
		System.out.println(purchase);
		System.out.println("=======================================");
		
		//==> console 확인
		//System.out.println(user);
		
		//==> API 확인/////////////////////////////////////////////////////////////
//		Assert.assertEquals("testUserId", user.getUserId());
//		Assert.assertEquals("testUserName", user.getUserName());
//		Assert.assertEquals("testPasswd", user.getPassword());
//		Assert.assertEquals("111-2222-3333", user.getPhone());
//		Assert.assertEquals("경기도", user.getAddr());
//		Assert.assertEquals("test@test.com", user.getEmail());

//		Assert.assertNotNull(userService.getUser("user02"));
//		Assert.assertNotNull(userService.getUser("user05"));
	}
	
	//@Test
	public void testUpdatePurchase() throws Exception{
		 
		Purchase purchase = new Purchase();
		User user = new User();
		user.setUserId("test");
		ProductVO product = new ProductVO();
		product.setProdNo(10100);
			
		purchase.setTranNo(10070);
		purchase.setPurchaseProd(product);
		purchase.setBuyer(user);
		purchase.setPaymentOption("0");
		purchase.setReceiverName("수정한myBatis");
		purchase.setReceiverPhone("수정");
		purchase.setDivyAddr("수정한테스트주소");
		purchase.setDivyRequest("수정한테스트요청");
		purchase.setDivyDate("20200514");
		
		purchaseService.updatePurchase(purchase);
	 }
	 
	//@Test
	public void testUpdateTranCode() throws Exception{

		Purchase purchase = new Purchase();
		ProductVO purchaseProd = new ProductVO();
		
		purchaseProd.setProdNo(10100);
		purchase.setTranCode("1");
		purchase.setPurchaseProd(purchaseProd);
//		purchase.setTranNo(10070);
		
		purchaseService.updateTranCode(purchase);
		 
	}
	
	//@Test
	public void testGetTotalCount() throws Exception{
		Search search = new Search();
		System.out.println("count!!!!!!" + purchaseService.getTotalCount(search));
	}
	
	 //==>  주석을 풀고 실행하면....
	 //@Test
	 public void testGetPurchaseList() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	
	 	List<Purchase> list = purchaseService.getPurchaseList(search, "test");
	 	System.out.println("list값은" + list);
	 	for(int i = 0 ; i < list.size() ; i ++) {
	 		System.out.println("========================================");
	 		System.out.println("가져온 값!!! "+list.get(i));
	 		System.out.println("========================================");
	 	}
	 	
	 }
}