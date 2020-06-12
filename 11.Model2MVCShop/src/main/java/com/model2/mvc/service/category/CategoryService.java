package com.model2.mvc.service.category;

import java.util.List;

import com.model2.mvc.service.domain.Category;

public interface CategoryService {
	
	public List<Category> getCategoryHierarchy(Category category) throws Exception;
}
