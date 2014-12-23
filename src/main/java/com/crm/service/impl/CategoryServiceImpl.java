package com.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.spec.ICategoryDao;
import com.crm.dto.CategoryDetailDto;
import com.crm.dto.CategoryDto;
import com.crm.service.spec.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private ICategoryDao categoryDao;
	
	@Override
	public void addCategory(String companyCode, CategoryDto category) {
		categoryDao.addCategory(companyCode, category);
		
	}

	@Override
	public void deleteCategory(String companyCode, CategoryDto category) {
		categoryDao.deleteCategory(companyCode, category);
	}

	@Override
	public void addCategoryByBatch(String companyCode,
			List<CategoryDto> categories) {
		categoryDao.addCategoryByBatch(companyCode, categories);
	}

	@Override
	public void updateCategory(String companyCode, CategoryDto category) {
		categoryDao.updateCategory(companyCode, category);
	}

	@Override
	public void getCategoryByCode(String companyCode, int id) {
		categoryDao.getCategoryByCode(companyCode, id);
	}

	@Override
	public void getAllCategories(String companyCode) {

		categoryDao.getAllCategories(companyCode);
	}

	@Override
	public List<CategoryDetailDto> getCategoryServiceDetail(String companyCode) {
		
		return categoryDao.getCategoryServiceDetail(companyCode);
	}

	@Override
	public void createTable(String companyCode) {
		categoryDao.createTable(companyCode);
	}

}
