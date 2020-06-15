package com.model2.mvc.service.category.impl;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model2.mvc.service.category.CategoryDAO;
import com.model2.mvc.service.domain.Category;

//@Repository
public class CategoryDAOImpl implements CategoryDAO {
	
	private SqlSession sqlSession;
	
	public CategoryDAOImpl() {
		
	}
	
	@Autowired
	public CategoryDAOImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public Category getCategoryHierarchy(int category) throws Exception {
		return sqlSession.selectOne("CategoryMapper.getCategoryHierarchy", category);
	}

}
