package com.model2.mvc.service.domain;

public class Category {
	
	private String name; // 카테고리 이름
	private int parentsId; // 부모 카테고리의 아이디
	private int categoryId; // 카테고리의 아이디
	private int depth; // 카테고리의 깊이
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParentsId() {
		return parentsId;
	}
	public void setParentsId(int parentsId) {
		this.parentsId = parentsId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	@Override
	public String toString() {
		return "Category [name=" + name + ", parentsId=" + parentsId + ", categoryId=" + categoryId 
				+ ", depth=" + depth + "]";
	}
}
