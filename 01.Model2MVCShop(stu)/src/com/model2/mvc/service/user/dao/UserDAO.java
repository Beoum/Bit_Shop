package com.model2.mvc.service.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.user.vo.UserVO;


public class UserDAO {
	
	public UserDAO(){
	}

	public void insertUser(UserVO userVO) throws Exception { // void�� �ƴ� int�� �޾Ƽ� ó������� ó�����ָ� ������

		Connection con = DBUtil.getConnection();

		String sql = "insert into USERS values (?,?,?,'user',?,?,?,?,sysdate)";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, userVO.getUserId());
		stmt.setString(2, userVO.getUserName());
		stmt.setString(3, userVO.getPassword());
		stmt.setString(4, userVO.getSsn());
		stmt.setString(5, userVO.getPhone());
		stmt.setString(6, userVO.getAddr());
		stmt.setString(7, userVO.getEmail());
		stmt.executeUpdate();

		con.close();
	}

	public UserVO findUser(String userId) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "select * from USERS where USER_ID=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, userId);

		ResultSet rs = stmt.executeQuery();

		UserVO userVO = null;
		while (rs.next()) {
			userVO = new UserVO();
			userVO.setUserId(rs.getString("USER_ID"));
			userVO.setUserName(rs.getString("USER_NAME"));
			userVO.setPassword(rs.getString("PASSWORD"));
			userVO.setRole(rs.getString("ROLE"));
			userVO.setSsn(rs.getString("SSN"));
			userVO.setPhone(rs.getString("CELL_PHONE"));
			userVO.setAddr(rs.getString("ADDR"));
			userVO.setEmail(rs.getString("EMAIL"));
			userVO.setRegDate(rs.getDate("REG_DATE"));
		}
		
		con.close();

		return userVO;
	}

	public HashMap<String,Object> getUserList(SearchVO searchVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "select * from USERS ";
		if (searchVO.getSearchCondition() != null) { // �˻�����  user_id ����
			if (searchVO.getSearchCondition().equals("0")) {
				sql += " where USER_ID='" + searchVO.getSearchKeyword()
						+ "'";
			} else if (searchVO.getSearchCondition().equals("1")) { // �˻����� user_name ����
				sql += " where USER_NAME='" + searchVO.getSearchKeyword()
						+ "'";
			}
		}
		sql += " order by USER_ID"; // ���� ������ user_id ���� ����

		PreparedStatement stmt = 
			con.prepareStatement(	sql,
														ResultSet.TYPE_SCROLL_INSENSITIVE, // resultset.next() �Ŀ� �ٽ� ���� �� ��ĵ���� .last ���� �ʼ� Ŀ�� �̵��� �����Ӱ� �ϰ� ������Ʈ ������ �ݿ����� �ʴ´�
														ResultSet.CONCUR_UPDATABLE); // ���� ������ ���
		ResultSet rs = stmt.executeQuery();

		rs.last();
		int total = rs.getRow();
		System.out.println("�ο��� ��:" + total); // ���� �������� ��

		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("count", new Integer(total));

		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1); // ������ ��ġ�� Ŀ���� �̵��ؼ� ����¡ó�� �̰� �� �̷���;;
		System.out.println("searchVO.getPage():" + searchVO.getPage()); // ���� ������
		System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit()); // ���� �������� ������

		ArrayList<UserVO> list = new ArrayList<UserVO>();
		if (total > 0) {
			for (int i = 0; i < searchVO.getPageUnit(); i++) { // ���ʿ��� ������ �ʹ� ���� �������°� �ƴ���...
				UserVO vo = new UserVO();
				vo.setUserId(rs.getString("USER_ID"));
				vo.setUserName(rs.getString("USER_NAME"));
				vo.setPassword(rs.getString("PASSWORD"));
				vo.setRole(rs.getString("ROLE"));
				vo.setSsn(rs.getString("SSN"));
				vo.setPhone(rs.getString("CELL_PHONE"));
				vo.setAddr(rs.getString("ADDR"));
				vo.setEmail(rs.getString("EMAIL"));
				vo.setRegDate(rs.getDate("REG_DATE"));

				list.add(vo);
				if (!rs.next())
					break;
			}
		}
		System.out.println("list.size() : "+ list.size());
		map.put("list", list);
		System.out.println("map().size() : "+ map.size());

		con.close();
			
		return map; // > list�� �־����� map���� ��ȯ���� �ʰ� �ٷ� �÷��� �Ǵ°� �ƴ���?
	}

	public void updateUser(UserVO userVO) throws Exception { // void�� �ƴ϶� int�� �޾Ƽ� ������ check���ָ� �� ������
		
		Connection con = DBUtil.getConnection();

		String sql = "update USERS set USER_NAME=?,CELL_PHONE=?,ADDR=?,EMAIL=? where USER_ID=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, userVO.getUserName());
		stmt.setString(2, userVO.getPhone());
		stmt.setString(3, userVO.getAddr());
		stmt.setString(4, userVO.getEmail());
		stmt.setString(5, userVO.getUserId());
		stmt.executeUpdate();
		
		con.close();
	}
}