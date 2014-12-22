package com.crm.dto;

import java.util.ArrayList;
import java.util.List;

public class CategoryDto {

	private long id; //category id;
	private String name; //category name 
	
	public CategoryDto(long id, String name){
		this.id = id;
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public static List<CategoryDto> getTestData(){
		List<CategoryDto> categoryDtos = new ArrayList<CategoryDto>();
		categoryDtos.add(new CategoryDto(0, "最穷的会员"));
		categoryDtos.add(new CategoryDto(0, "最小气的会员"));
		categoryDtos.add(new CategoryDto(0, "最霸气的会员"));
		categoryDtos.add(new CategoryDto(0, "最慷慨的会员"));
		categoryDtos.add(new CategoryDto(0, "值得关注的会员"));
		
		return categoryDtos;
	}
}
