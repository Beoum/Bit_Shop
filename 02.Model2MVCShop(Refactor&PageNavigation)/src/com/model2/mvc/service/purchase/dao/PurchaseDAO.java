package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

public class PurchaseDAO {
	public void insertPurchase(PurchaseVO purchaseVO) {
		String SQL = "INSERT INTO transaction VALUES(seq_transaction_tran_no.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, 1, sysdate, ?)";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SQL);){
			pstmt.setInt(1, purchaseVO.getPurchaseProd().getProdNo());
			pstmt.setString(2, purchaseVO.getBuyer().getUserId());
			pstmt.setString(3, purchaseVO.getPaymentOption());
			pstmt.setString(4, purchaseVO.getReceiverName());
			pstmt.setString(5, purchaseVO.getReceiverPhone());
			pstmt.setString(6, purchaseVO.getDivyAddr());
			pstmt.setString(7, purchaseVO.getDivyRequest());
			//
			pstmt.setString(8, purchaseVO.getDivyDate());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public HashMap<String, Object> getPurchaseList(Search searchVO, String buyerId) throws Exception{

		HashMap<String,Object> map = new HashMap<String,Object>();
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT * FROM transaction WHERE buyer_id='" + buyerId + "'";
		
		
		int totalCount = this.getTotalCount(sql);
		System.out.println("PurchaseDAO :: totalCount  :: " + totalCount);
		
		
		sql = makeCurrentPageSql(sql, searchVO);
		PreparedStatement stmt = con.prepareStatement(sql); // 수정 가능한 모드
//		stmt.setString(1, buyerId);
		ResultSet rs = stmt.executeQuery();

		ArrayList<PurchaseVO> list = new ArrayList<PurchaseVO>();
		
		while(rs.next()) {
			PurchaseVO vo = new PurchaseVO();
			User user = new User();
			ProductVO productVO = new ProductVO();
			
			user.setUserId(rs.getString("buyer_id"));
			productVO.setProdNo(Integer.parseInt(rs.getString("prod_no")));
			
			vo.setBuyer(user);
			vo.setTranNo(rs.getInt("tran_no"));
			vo.setPurchaseProd(productVO);
			vo.setTranCode(rs.getString("tran_status_code"));
			
			list.add(vo);
		}
		
		System.out.println("list.size() : "+ list.size());
		map.put("list", list);
		map.put("totalCount", new Integer(totalCount));
		System.out.println("map().size() : "+ map.size());

		con.close();
		
		return map;
	}
	
	public PurchaseVO findPurchase(int tranNO) {
		String SQL = "SELECT * FROM transaction WHERE tran_no=?";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SQL);){
			
			pstmt.setInt(1, tranNO);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				PurchaseVO vo = new PurchaseVO();
				ProductVO productVO = new ProductVO();
				User userVO = new User();
				
				productVO.setProdNo(rs.getInt("prod_no"));
				userVO.setUserId(rs.getString("buyer_id"));
				
				vo.setPurchaseProd(productVO);
				vo.setBuyer(userVO);
				vo.setPaymentOption(rs.getString("PAYMENT_OPTION"));
				vo.setReceiverName(rs.getString("receiver_name"));
				vo.setReceiverPhone(rs.getString("receiver_phone"));
				vo.setDivyAddr(rs.getString("DEMAILADDR"));
				vo.setDivyRequest(rs.getString("dlvy_request"));
				vo.setDivyDate(rs.getString("dlvy_date"));
				vo.setOrderDate(rs.getDate("ORDER_DATA"));
				vo.setTranCode(rs.getString("TRAN_STATUS_CODE"));
				
				return vo;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void updatePurchase(PurchaseVO purchaseVO) {
		String SQL = "UPDATE transaction SET payment_option=?, receiver_name=?, receiver_phone=?, DEMAILADDR=?, DLVY_REQUEST=?, DLVY_DATE=TO_DATE(?, 'yyyy/MM/DD') WHERE tran_no=?";
		Connection conn = DBUtil.getConnection();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, purchaseVO.getPaymentOption());
			pstmt.setString(2, purchaseVO.getReceiverName());
			pstmt.setString(3, purchaseVO.getReceiverPhone());
			pstmt.setString(4, purchaseVO.getDivyAddr());
			pstmt.setString(5, purchaseVO.getDivyRequest());
			pstmt.setDate(6, java.sql.Date.valueOf(purchaseVO.getDivyDate()));
			pstmt.setInt(7, purchaseVO.getTranNo());
			
			pstmt.executeUpdate();
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateTranCode(PurchaseVO purchaseVO) {
		String SQL = null;
		if(purchaseVO.getPurchaseProd() == null) {
			SQL = "UPDATE transaction SET TRAN_STATUS_CODE=? WHERE tran_no=?";
		}else {
			SQL = "UPDATE transaction SET TRAN_STATUS_CODE=? WHERE prod_no=?";
		}
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SQL);){
			
			pstmt.setString(1, purchaseVO.getTranCode());
			if(purchaseVO.getPurchaseProd() == null) {
				pstmt.setInt(2, purchaseVO.getTranNo());
			}else {
				pstmt.setInt(2, purchaseVO.getPurchaseProd().getProdNo());
			}
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 게시판 Page 처리를 위한 전체 Row(totalCount)  return
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
