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
		categoryDtos.add(new CategoryDto(0, "����Ļ�Ա"));
		categoryDtos.add(new CategoryDto(0, "��С���Ļ�Ա"));
		categoryDtos.add(new CategoryDto(0, "������Ļ�Ա"));
		categoryDtos.add(new CategoryDto(0, "����Ļ�Ա"));
		categoryDtos.add(new CategoryDto(0, "ֵ�ù�ע�Ļ�Ա"));
		
		return categoryDtos;
	}
}
