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
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
public class PurchaseServiceTest {

	//==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
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
		purchase.setReceiverName("�޴�myBatis");
		purchase.setReceiverPhone("�׽�Ʈ��");
		purchase.setDivyAddr("�׽�Ʈ�ּ�");
		purchase.setDivyRequest("�׽�Ʈ��û");
		purchase.setDivyDate("20200514");
		
		
		purchaseService.insertPurchase(purchase);
	}
	
	@Test
	public void testGetUser() throws Exception {
		
		Purchase purchase = purchaseService.findPurchase(10070);
		
		System.out.println("=======================================");
		System.out.println(purchase);
		System.out.println("=======================================");
		
		//==> console Ȯ��
		//System.out.println(user);
		
		//==> API Ȯ��/////////////////////////////////////////////////////////////
//		Assert.assertEquals("testUserId", user.getUserId());
//		Assert.assertEquals("testUserName", user.getUserName());
//		Assert.assertEquals("testPasswd", user.getPassword());
//		Assert.assertEquals("111-2222-3333", user.getPhone());
//		Assert.assertEquals("��⵵", user.getAddr());
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
		purchase.setReceiverName("������myBatis");
		purchase.setReceiverPhone("����");
		purchase.setDivyAddr("�������׽�Ʈ�ּ�");
		purchase.setDivyRequest("�������׽�Ʈ��û");
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
	
	 //==>  �ּ��� Ǯ�� �����ϸ�....
	 //@Test
	 public void testGetPurchaseList() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	
	 	List<Purchase> list = purchaseService.getPurchaseList(search, "test");
	 	System.out.println("list����" + list);
	 	for(int i = 0 ; i < list.size() ; i ++) {
	 		System.out.println("========================================");
	 		System.out.println("������ ��!!! "+list.get(i));
	 		System.out.println("========================================");
	 	}
	 	
	 }
}