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
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.UserService;

import jdk.nashorn.internal.ir.annotations.Ignore;


/*
 *	FileName :  UserServiceTest.java
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
										"classpath:config/context-aspect.xml",
										"classpath:config/context-mybatis.xml",
										"classpath:config/context-transaction.xml" })
public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
	@Autowired
	private ProductService productService;
	
	//@Test
	public void testAddUser() throws Exception {
	  
		ProductVO productVO = new ProductVO();
		productVO.setProdName("myBatisTestProduct");
		productVO.setProdDetail("���̹�Ƽ�� �׽�Ʈ�� insert");
		productVO.setManuDate("20200513");
		productVO.setPrice(39800);
		productVO.setFileName("�������ּ���.jpg");
		
		productService.addProduct(productVO);;
		
	}
	 
	
	//@Test
	public void testGetProduct() throws Exception {
		
		ProductVO vo = new ProductVO();
		
		vo = productService.getProduct(10000);

		System.out.println(vo);
		
		//==> console Ȯ��
		//System.out.println(user);
		
		//==> API Ȯ��/////////////////////////////////////////////////////////////
		
		
		  Assert.assertEquals("AHlbAAAAtBqyWAAA.jpg", vo.getFileName());
		  Assert.assertEquals("20120514", vo.getManuDate());
		  Assert.assertEquals(2000000, vo.getPrice());
		  Assert.assertEquals("�Ҵ� ���̿� ��Ʈ�� �ŵ�ǰ", vo.getProdDetail());
		  Assert.assertEquals("vaio vgn FS70B", vo.getProdName());
		  Assert.assertEquals(10000, vo.getProdNo()); 
		  Assert.assertEquals(null, vo.getProTranCode());
	}
	
	//@Test
	public void testUpdateUser() throws Exception{
	  
		ProductVO productVO = productService.getProduct(10000);
		Assert.assertNotNull(productVO);
		  
		Assert.assertEquals("AHlbAAAAtBqyWAAA.jpg", productVO.getFileName());
		Assert.assertEquals("20120514", productVO.getManuDate());
		Assert.assertEquals(2000000, productVO.getPrice());
		Assert.assertEquals("�Ҵ� ���̿� ��Ʈ�� �ŵ�ǰ", productVO.getProdDetail());
		Assert.assertEquals("vaio vgn FS70B", productVO.getProdName());
		Assert.assertEquals(10000, productVO.getProdNo());
		Assert.assertEquals(null, productVO.getProTranCode());
		
		  
		productVO.setFileName("change File"); 
		productVO.setPrice(7000000);
		productVO.setProdName("���γ��� �Ҵ� ���̿� ��Ʈ�� �ŵ�ǰ"); 
		  
		productService.updateProduct(productVO);
		  
	 }

	  //@Test 
	  public void testProductList() throws Exception{
	  
		  Search search = new Search(); 
		  search.setCurrentPage(1);
		  search.setPageSize(3); 
		  search.setSearchCondition("0");
		  search.setSearchKeyword(""); 
		  search.setOrderBy("ASC");
		  
		  Map<String,Object> map = null; 
		  List<ProductVO> list = productService.getProductList(search);
		  
		  for(int i = 0 ; i < list.size(); i++) {
			  System.out.println("=======================================");
			  System.out.println("DB���� ������ Data " + (i+1)+ "��° " +list.get(i));
		  }

		  int totalCount = productService.getTotalCount(search);
		  
		  
		  System.out.println("totaolCount !!!! �Դϴ� !! " + totalCount);
		  // �ʿ� ��Ƽ� �߰����ּ���;
		  
//		  Assert.assertEquals(1, list.size());
		  
		  //==> console Ȯ�� //System.out.println(list);
		  
//		  Integer totalCount = (Integer)map.get("totalCount");
//		  System.out.println(totalCount);
		  
//		  System.out.println("=======================================");
		  
//		  search.setSearchCondition("0");
//		  search.setSearchKeyword(""+System.currentTimeMillis()); map =
//		  userService.getUserList(search);
		  
//		  list = (List<Object>)map.get("list"); Assert.assertEquals(0, list.size());
		  
		  //==> console Ȯ�� //System.out.println(list);
		  
//		  totalCount = (Integer)map.get("totalCount"); System.out.println(totalCount);
	  }
}
	  /*
	 * @Test public void testGetUserListByUserName() throws Exception{
	 * 
	 * Search search = new Search(); search.setCurrentPage(1);
	 * search.setPageSize(3); search.setSearchCondition("1");
	 * search.setSearchKeyword("SCOTT"); Map<String,Object> map =
	 * userService.getUserList(search);
	 * 
	 * List<Object> list = (List<Object>)map.get("list"); Assert.assertEquals(3,
	 * list.size());
	 * 
	 * //==> console Ȯ�� System.out.println(list);
	 * 
	 * Integer totalCount = (Integer)map.get("totalCount");
	 * System.out.println(totalCount);
	 * 
	 * System.out.println("=======================================");
	 * 
	 * search.setSearchCondition("1");
	 * search.setSearchKeyword(""+System.currentTimeMillis()); map =
	 * userService.getUserList(search);
	 * 
	 * list = (List<Object>)map.get("list"); Assert.assertEquals(0, list.size());
	 * 
	 * //==> console Ȯ�� System.out.println(list);
	 * 
	 * totalCount = (Integer)map.get("totalCount"); System.out.println(totalCount);
	 * } 
	 * }
	 */