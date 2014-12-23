package com.crm.dao.spec;

import java.util.List;

import com.crm.dto.CategoryDetailDto;
import com.crm.dto.CategoryDto;

public interface ICategoryDao {

	public CategoryDto addCategory(String companyCode, CategoryDto category);
	public void deleteCategory(String companyCode, CategoryDto category);
	public void addCategoryByBatch(String companyCode, List<CategoryDto> categories);
	public void updateCategory(String companyCode, CategoryDto category);
	public CategoryDto getCategoryByCode(String companyCode, int id);
	public List<CategoryDto> getAllCategories(String companyCode);
	public List<CategoryDetailDto> getCategoryServiceDetail(String companyCode);
	public void createTable(String companyCode);

}
