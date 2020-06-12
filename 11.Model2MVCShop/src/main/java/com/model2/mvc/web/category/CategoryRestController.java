package com.model2.mvc.web.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.service.category.CategoryService;
import com.model2.mvc.service.domain.Category;

@RestController
@RequestMapping("/category/*")
public class CategoryRestController {
	
	private CategoryService categoryService;
	
	public CategoryRestController() {
		
	}
	
	@Autowired
	public CategoryRestController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@RequestMapping(value="/json/getCategoryHierarchy", method=RequestMethod.GET)
	public List<Category> getCategoryHierarchy(Category category) throws Exception{
		System.out.println("/json/getCategoryHierarchy");
		
		List<Category> list = categoryService.getCategoryHierarchy(category);
		
		System.out.println("list : " + list);
		
		return categoryService.getCategoryHierarchy(category);
	}

}
