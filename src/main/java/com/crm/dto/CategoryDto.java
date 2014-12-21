package com.crm.dto;

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
}
