package com.crm.service.spec;

import java.util.List;

import com.crm.dto.CategoryDto;

public interface ICategoryService {

	public void addCategory(String companyCode, CategoryDto category);
	public void deleteCategory(String companyCode, CategoryDto category);
	public void addCategoryByBatch(String companyCode, List<CategoryDto> categories);
	public void updateCategory(String companyCode, CategoryDto category);
	public void getCategoryByCode(String companyCode, int id);
	public void getAllCategories(String companyCode);
	
}
