package com.model2.mvc.service.category.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model2.mvc.service.category.CategoryDAO;
import com.model2.mvc.service.category.CategoryService;
import com.model2.mvc.service.domain.Category;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	private CategoryDAO CategoryDAO;
	
	public CategoryServiceImpl() {
		
	}
	
	@Autowired
	public CategoryServiceImpl(CategoryDAO CategoryDAO) {
		this.CategoryDAO = CategoryDAO;
	}
	
	@Override
	public List<Category> getCategoryHierarchy(Category category) throws Exception {
		List<Category> list = new ArrayList<Category>();
		Category categoryList = CategoryDAO.getCategoryHierarchy(category.getCategoryId());
		list.add(categoryList);
		while(true) {
			categoryList = CategoryDAO.getCategoryHierarchy(categoryList.getCategoryId()-1);
			list.add(categoryList);
			if(categoryList.getDepth() == 1) {
				break;
			}
		}
		return list;
	}

}
