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
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDAO;
import com.model2.mvc.service.product.ProductService;


@Service
public class ProductServiceImpl implements ProductService {
	
	private ProductDAO productDAO;
	
	public ProductServiceImpl() {
	}
	
	@Autowired
	public ProductServiceImpl(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	
	@Override
	public void addProduct(Product product, MultipartHttpServletRequest mpRequest) throws Exception {
		// server에 file upload
		String fileName = uploadFile(mpRequest);
		
		// hash처리된 fileName을 setting
		product.setFileName(fileName);
		
		productDAO.insertProduct(product);
	}

	@Override
	public Product getProduct(int prodNo) throws Exception {
		Product product = productDAO.findProduct(prodNo);
		
		// 제품 상태코드가 null이면 0으로 setting
		if(product.getProTranCode() == null) {
			product.setProTranCode("0");
		}
		
		// 상품의 이미지가 존재하면 경로를 setting
		if(product.getFileName() != null) {
			product.setFileName("/images/uploadFiles/"+product.getFileName());
		}
		
		return product;
	}

	@Override
	public Map<String, Object> getProductList(Search search) throws Exception {
		List<Product> list = new ArrayList<Product>();
		int totalCount = 0;
		
		// 관리자 페이지에서 리스트를 요청했을때 상품 리스트
		if(search.getMenu().equals("manage")) {
			list = productDAO.getProductList(search);
			totalCount = productDAO.getTotalCount(search);
		}else {
			list = productDAO.getProductListSearch(search);
			totalCount = productDAO.getTotalCountSearch(search);
		}
		
		// 제품리스트를 볼때에 상태를 표시하기위해 상태코드를 셋팅
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
	
	// 상품정보 업데이트
	@Override
	public void updateProduct(Product productVO) throws Exception {
		productDAO.updateProduct(productVO);
	}

	// 상품의 전체 갯수를 조회하는 메소드
	@Override
	public int getTotalCount(Search search) throws Exception {
		return productDAO.getTotalCount(search);
	}

	// file upload
	@Override
	public String uploadFile(MultipartHttpServletRequest mpRequest) throws Exception {
		String root_path = mpRequest.getSession().getServletContext().getRealPath("/");  
	    String attach_path = "/images/uploadFiles/";
	      
	    String filePath = root_path + attach_path;
        MultipartFile file = mpRequest.getFile("imgFile");
        String fileName = "";
        if(file!=null){
	       	fileName = saveFileName(file.getOriginalFilename());
	   	    file.transferTo(new File(filePath + fileName));
    	}
        
		return fileName;
	}
	
	// fileName을 hash
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
