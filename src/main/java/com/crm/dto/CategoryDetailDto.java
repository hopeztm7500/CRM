package com.crm.dto;

public class CategoryDetailDto {

	private CategoryDto category;
	private double totalSpend;
	private int totalMemberCount;
	
	
	public CategoryDetailDto(CategoryDto categoryDto, double totalSpend, int totalMemberCount){
		this.category = categoryDto;
		this.totalSpend = totalSpend;
		this.totalMemberCount = totalMemberCount;
	}
	
	public CategoryDto getCategory() {
		return category;
	}
	public void setCategory(CategoryDto category) {
		this.category = category;
	}
	public double getTotalSpend() {
		return totalSpend;
	}
	public void setTotalSpend(double totalSpend) {
		this.totalSpend = totalSpend;
	}
	public int getTotalMemberCount() {
		return totalMemberCount;
	}
	public void setTotalMemberCount(int totalMemberCount) {
		this.totalMemberCount = totalMemberCount;
	}
	
}
