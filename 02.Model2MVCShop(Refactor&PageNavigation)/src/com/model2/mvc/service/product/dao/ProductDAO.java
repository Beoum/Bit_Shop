package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.User;
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

	public HashMap<String,Object> getProductList(Search searchVO) throws Exception {
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT distinct p.*, t.TRAN_STATUS_CODE State FROM transaction t, product p ";
		if (searchVO.getSearchCondition() != null) { // 검색조건  user_id 선택
			if (searchVO.getSearchCondition().equals("0")) {
				if(!searchVO.getSearchKeyword().equals("") && !(searchVO.getSearchKeyword() == null)) {
					sql += "WHERE p.prod_no(+)='" + searchVO.getSearchKeyword()
					+ "' AND t.prod_no(+)=p.prod_no";
				}else {
					sql += "WHERE t.prod_no(+)=p.prod_no";
				}
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
		
		// 전체 상품수 GET
		int totalCount = this.getTotalCount(sql);
		System.out.println("ProductDAO :: totalCount ::" + totalCount);
		
		//==> CurrentPage 게시물만 받도록 수정
		sql = makeCurrentPageSql(sql, searchVO);
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		List<ProductVO> list = new ArrayList<ProductVO>();
		
		while(rs.next()) {
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
		}
		
		map.put("totalCount", new Integer(totalCount));
		
		map.put("list", list);
		
	

		rs.close();
		pstmt.close();
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
	
	private int getTotalCount(String sql) throws Exception {
		
		sql = "SELECT COUNT(*) "+
		          "FROM ( " +sql+ ") countTable";
		
		Connection con = DBUtil.getConnection();
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
		
		int totalCount = 0;
		if( rs.next() ){
			totalCount = rs.getInt(1);
		}
		
		pStmt.close();
		con.close();
		rs.close();
		
		return totalCount;
	}
	
	// 게시판 currentPage Row 만  return 
	private String makeCurrentPageSql(String sql , Search search){
		sql = 	"SELECT * "+ 
					"FROM (		SELECT inner_table. * ,  ROWNUM AS row_seq " +
									" 	FROM (	"+sql+" ) inner_table "+
									"	WHERE ROWNUM <="+search.getCurrentPage()*search.getPageSize()+" ) " +
					"WHERE row_seq BETWEEN "+((search.getCurrentPage()-1)*search.getPageSize()+1) +" AND "+search.getCurrentPage()*search.getPageSize();
		
		System.out.println("UserDAO :: make SQL :: "+ sql);	
		
		return sql;
	}
}
