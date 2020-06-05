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

	public void insertUser(UserVO userVO) throws Exception { // void가 아닌 int로 받아서 처리결과를 처리해주면 좋을듯

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
		if (searchVO.getSearchCondition() != null) { // 검색조건  user_id 선택
			if (searchVO.getSearchCondition().equals("0")) {
				sql += " where USER_ID='" + searchVO.getSearchKeyword()
						+ "'";
			} else if (searchVO.getSearchCondition().equals("1")) { // 검색조건 user_name 선택
				sql += " where USER_NAME='" + searchVO.getSearchKeyword()
						+ "'";
			}
		}
		sql += " order by USER_ID"; // 쿼리 실행후 user_id 기준 정렬

		PreparedStatement stmt = 
			con.prepareStatement(	sql,
														ResultSet.TYPE_SCROLL_INSENSITIVE, // resultset.next() 후에 다시 이전 값 스캔가능 .last 사용시 필수 커서 이동을 자유롭게 하고 업데이트 내용을 반영하지 않는다
														ResultSet.CONCUR_UPDATABLE); // 수정 가능한 모드
		ResultSet rs = stmt.executeQuery();

		rs.last();
		int total = rs.getRow();
		System.out.println("로우의 수:" + total); // 유저 데이터의 수

		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("count", new Integer(total));

		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1); // 지정한 위치로 커서를 이동해서 페이징처리 이걸 왜 이렇게;;
		System.out.println("searchVO.getPage():" + searchVO.getPage()); // 현재 페이지
		System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit()); // 현재 페이지의 정보수

		ArrayList<UserVO> list = new ArrayList<UserVO>();
		if (total > 0) {
			for (int i = 0; i < searchVO.getPageUnit(); i++) { // 불필요한 정보를 너무 많이 가져오는거 아닌지...
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
			
		return map; // > list에 넣었으니 map으로 변환하지 않고 바로 올려도 되는것 아닌지?
	}

	public void updateUser(UserVO userVO) throws Exception { // void가 아니라 int로 받아서 실행결과 check해주면 더 좋을듯
		
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