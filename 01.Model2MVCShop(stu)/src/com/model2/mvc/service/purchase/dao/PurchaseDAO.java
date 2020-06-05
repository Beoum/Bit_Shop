package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
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
	
	public HashMap<String, Object> getPurchaseList(SearchVO searchVO, String buyerId) throws SQLException{
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT * FROM transaction WHERE buyer_id=?";
		
		PreparedStatement stmt = 
			con.prepareStatement(	sql,
														ResultSet.TYPE_SCROLL_INSENSITIVE, // resultset.next() �Ŀ� �ٽ� ���� �� ��ĵ���� .last ���� �ʼ� Ŀ�� �̵��� �����Ӱ� �ϰ� ������Ʈ ������ �ݿ����� �ʴ´�
														ResultSet.CONCUR_UPDATABLE); // ���� ������ ���
		stmt.setString(1, buyerId);
		
		ResultSet rs = stmt.executeQuery();

		rs.last();
		int total = rs.getRow();
		System.out.println("�ο��� ��:" + total); // ���� �������� ��

		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("count", new Integer(total));

		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1); // ������ ��ġ�� Ŀ���� �̵��ؼ� ����¡ó�� �̰� �� �̷���;;
		System.out.println("searchVO.getPage():" + searchVO.getPage()); // ���� ������
		System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit()); // ���� �������� ������

		ArrayList<PurchaseVO> list = new ArrayList<PurchaseVO>();
		if (total > 0) {
			for (int i = 0; i < searchVO.getPageUnit(); i++) { // ���ʿ��� ������ �ʹ� ���� �������°� �ƴ���...
				PurchaseVO purchaseVO = new PurchaseVO();
				UserVO userVO = new UserVO();
				ProductVO productVO = new ProductVO();
				userVO.setUserId(rs.getString("buyer_id"));
				productVO.setProdNo(Integer.parseInt(rs.getString("prod_no")));
				purchaseVO.setBuyer(userVO);
				purchaseVO.setTranNo(rs.getInt("tran_no"));
				purchaseVO.setPurchaseProd(productVO);
				purchaseVO.setTranCode(rs.getString("tran_status_code"));
				
				list.add(purchaseVO);
				if (!rs.next())
					break;
			}
		}
		System.out.println("list.size() : "+ list.size());
		map.put("list", list);
		System.out.println("map().size() : "+ map.size());

		con.close();
		
		for(int i = 0; i < list.size() ; i++) {
			System.out.println(list.get(i));
		}
		
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
				UserVO userVO = new UserVO();
				
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
		String SQL = "UPDATE transaction SET payment_option=?, receiver_name=?, receiver_phone=?, DEMAILADDR=?, DLVY_REQUEST=?, DLVY_DATE=? WHERE tran_no=?";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SQL)){
			pstmt.setString(1, purchaseVO.getPaymentOption());
			pstmt.setString(2, purchaseVO.getReceiverName());
			pstmt.setString(3, purchaseVO.getReceiverPhone());
			pstmt.setString(4, purchaseVO.getDivyAddr());
			pstmt.setString(5, purchaseVO.getDivyRequest());
			pstmt.setString(6, purchaseVO.getDivyDate());
			pstmt.setInt(7, purchaseVO.getTranNo());
			
			pstmt.executeUpdate();
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
}
