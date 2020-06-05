package com.model2.mvc.service.product.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.apache.commons.io.FilenameUtils;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.product.ProductDAO;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAOImpl;
import com.model2.mvc.service.product.domain.Product;


@Service
public class ProductServiceImpl implements ProductService {
	
	private ProductDAO productDAO;
	
	public ProductServiceImpl() {
	}
	
	@Autowired
	public ProductServiceImpl(ProductDAO productDAO) {
		this.productDAO=productDAO;
	}
	
	@Override
	public void addProduct(Product product, MultipartHttpServletRequest mpRequest) throws Exception {
		// file upload 처리, 나중에 경로명이랑 파일이름이랑 동시에 넣어주세요.
		String fileName = uploadFile(mpRequest);
		
		// 날짜 형식 및 fileName setting
		Product date = new Product();
		date.setManuDate(product.getManuDate().substring(0, 4)+product.getManuDate().substring(5, 7)+product.getManuDate().substring(8));
		product.setManuDate(date.getManuDate());
		product.setFileName(fileName);
		
		//insert 작업진행
		productDAO.insertProduct(product);
	}

	@Override
	public Product getProduct(int prodNo) throws Exception {
		Product product = productDAO.findProduct(prodNo);
		if(product.getProTranCode() == null) {
			product.setProTranCode("0");
		}
		if(product.getFileName() != null) {
			product.setFileName("C:\\img\\"+product.getFileName());
		}
		
		return product;
	}

	@Override
	public Map<String, Object> getProductList(Search search) throws Exception {
		List<Product> list = new ArrayList<Product>();
		int totalCount = 0;
		if(search.getMenu().equals("manage")) {
			list = productDAO.getProductList(search);
			totalCount = productDAO.getTotalCount(search);
		}else {
			list = productDAO.getProductListSearch(search);
			totalCount = productDAO.getTotalCountSearch(search);
		}
				
		System.out.println("product Search key word : " + list);
		for(int i = 0; i < list.size() ; i++) {
			if(list.get(i).getProTranCode() == null) {
				list.get(i).setProTranCode("0");
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}

	@Override
	public void updateProduct(Product productVO) throws Exception {
		System.out.println("product :" + productVO);
		productDAO.updateProduct(productVO);
	}

	@Override
	public int getTotalCount(Search search) throws Exception {
		if(search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}

		search.setPageSize(3);
		return productDAO.getTotalCount(search);
	}

	@Override
	public String uploadFile(MultipartHttpServletRequest mpRequest) {
		// 메타 데이터화 해서 바꾸든지하세요. 경로를 Server 컴퓨터말고 표준화된 폴더 구조 안으로 넣어야하는건 아닌지?
		String filePath = "C:\\img\\";
		
        MultipartFile file = mpRequest.getFile("imgFile");
        String fileName = "";
        if(file!=null){
        	fileName = saveFileName(file.getOriginalFilename());
        	System.out.println("file 경로 : " + filePath + fileName);
	     	try {
	    	    file.transferTo(new File(filePath + fileName));
	    	} catch(Exception e) {
	    	    System.out.println("업로드 오류");
	    	}
        }
        
		return fileName;
        
		
	}

	@Override
	public String saveFileName(String originFileName) {
		String fileName = "";
		
		Calendar calendar = Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += "." + FilenameUtils.getExtension(originFileName);
		return fileName;
	}
	


}
