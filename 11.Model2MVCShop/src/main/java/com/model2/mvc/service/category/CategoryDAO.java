package com.model2.mvc.service.category;

import org.apache.ibatis.annotations.Mapper;

import com.model2.mvc.service.domain.Category;

@Mapper
public interface CategoryDAO {
	public Category getCategoryHierarchy(int category) throws Exception;
}
