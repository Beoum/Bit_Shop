package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.vo.UserVO;

public class ProductDAO {
	public ProductDAO(){
	}

	public void insertProduct(ProductVO productVO) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "insert into PRODUCT values (seq_product_prod_no.nextval,?,?,?,?,?,sysdate)";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, productVO.getProdName());
		stmt.setString(2, productVO.getProdDetail());
		stmt.setString(3, productVO.getManuDate());
		stmt.setInt(4, productVO.getPrice());
		stmt.setString(5, productVO.getFileName());
		stmt.executeUpdate();

		con.close();
	}

	public ProductVO findProduct(int prodno) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "select p.*, t.tran_status_code State from PRODUCT p, transaction t where p.prod_no=? AND t.prod_no(+)=p.prod_no";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, prodno);

		ResultSet rs = stmt.executeQuery();

		ProductVO productVO = null;
		while (rs.next()) {
			productVO = new ProductVO();
			productVO.setProdNo(rs.getInt("prod_no"));
			productVO.setProdName(rs.getString("prod_name"));
			productVO.setFileName(rs.getString("image_file"));
			productVO.setProdDetail(rs.getString("prod_detail"));
			productVO.setManuDate(rs.getString("MANUFACTURE_DAY"));
			productVO.setPrice(rs.getInt("price"));
			productVO.setRegDate(rs.getDate("reg_date"));
			if(rs.getString("State") == null) {
				productVO.setProTranCode("0");
			}else {
				productVO.setProTranCode(rs.getString("State"));	
			}
		}
		
		con.close();
		
		return productVO;
	}

	public HashMap<String,Object> getProductList(SearchVO searchVO) throws Exception {

		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT distinct p.*, t.TRAN_STATUS_CODE State FROM transaction t, product p ";
		if (searchVO.getSearchCondition() != null) { // 검색조건  user_id 선택
			if (searchVO.getSearchCondition().equals("0")) {
				sql += " where p.prod_no(+)='" + searchVO.getSearchKeyword()
						+ "' AND t.prod_no(+)=p.prod_no";
			} else if (searchVO.getSearchCondition().equals("1")) { // 검색조건 user_name 선택
				sql += "WHERE p.prod_name(+) LIKE '" + "%" +searchVO.getSearchKeyword().trim() + "%"
						+ "' AND t.prod_no(+)=p.prod_no";
			} else if (searchVO.getSearchCondition().equals("2")) {
				sql += "WHERE p.price=(+)'" + searchVO.getSearchKeyword()
						+ "'  AND t.prod_no(+)=p.prod_no";
			}
		}else {
			sql += "WHERE t.prod_no(+)=p.prod_no";
		}
		
		sql += " ORDER BY p.prod_no";

		PreparedStatement stmt = 
			con.prepareStatement(sql,
														ResultSet.TYPE_SCROLL_INSENSITIVE, // resultset.next() 후에 다시 이전 값 스캔가능 .last 사용시 필수 커서 이동을 자유롭게 하고 업데이트 내용을 반영하지 않는다
														ResultSet.CONCUR_UPDATABLE); // 수정 가능한 모드
		
		ResultSet rs = stmt.executeQuery();
		System.out.println(sql);
		rs.last();
		int total = rs.getRow();
		System.out.println("로우의 수:" + total);
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("count", new Integer(total));
		
		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1);
		System.out.println("searchVO.getPage():" + searchVO.getPage());
		System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());
		
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		
		if (total > 0) {
			for (int i = 0; i < searchVO.getPageUnit(); i++) {
				ProductVO vo = new ProductVO();
				vo.setProdNo(rs.getInt("prod_no"));
				vo.setProdName(rs.getString("prod_name"));
				vo.setPrice(rs.getInt("price"));
				vo.setRegDate(rs.getDate("reg_date"));
				if(rs.getString("State") == null) {
					vo.setProTranCode("0");
				}else{
					vo.setProTranCode(rs.getString("State"));
				}

				
				list.add(vo);
				if (!rs.next())
					break;
			}

		}
		
		System.out.println("list.size() : "+ list.size());
		map.put("list", list);
		System.out.println("map().size() : "+ map.size());

		con.close();

		return map;
	}

	public void updateProduct(ProductVO productVO) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "update PRODUCT set prod_name=?, prod_detail=?, MANUFACTURE_DAY=?, price=?, IMAGE_FILE=? where prod_no=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, productVO.getProdName());
		stmt.setString(2, productVO.getProdDetail());
		stmt.setString(3, productVO.getManuDate());
		stmt.setInt(4, productVO.getPrice());
		stmt.setString(5, productVO.getFileName());
		stmt.setInt(6, productVO.getProdNo());
		
		stmt.executeUpdate();
		
		con.close();
	}
}
