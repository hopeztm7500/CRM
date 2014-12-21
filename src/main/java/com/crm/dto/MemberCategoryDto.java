package com.crm.dto;

import java.util.List;

public class MemberCategoryDto {

	private CategoryDto category;
	private List<MemberDto> members;
	public CategoryDto getCategory() {
		return category;
	}
	public void setCategory(CategoryDto category) {
		this.category = category;
	}
	public List<MemberDto> getMembers() {
		return members;
	}
	public void setMembers(List<MemberDto> members) {
		this.members = members;
	}
	
}
