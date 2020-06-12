package com.model2.mvc.service.category;

import java.util.List;

import com.model2.mvc.service.domain.Category;

public interface CategoryDAO {
	public Category getCategoryHierarchy(int category) throws Exception;
}
