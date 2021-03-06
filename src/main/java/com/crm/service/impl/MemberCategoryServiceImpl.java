package com.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.spec.ICategoryDao;
import com.crm.dao.spec.IMemberCategoryDao;
import com.crm.dto.CategoryDto;
import com.crm.dto.MemberCategoryDto;
import com.crm.dto.RawMemberCategoryDto;
import com.crm.service.spec.IMemberCategoryService;

@Service
public class MemberCategoryServiceImpl implements IMemberCategoryService {

	@Autowired
	private IMemberCategoryDao memberCategoryDao;
	@Autowired
	private ICategoryDao categoryDao;
	
	@Override
	public MemberCategoryDto getMemberCategoryByCategoryId(String companyCode,int id) {
		CategoryDto category = categoryDao.getCategoryByCode(companyCode, id);
		return memberCategoryDao.getMemberCategoryCategory(companyCode, category);
	}

	@Override
	public void createNewCategory(String companyCode,MemberCategoryDto memberCategory) {
		memberCategoryDao.createNewCategory(companyCode, memberCategory);
	}

	@Override
	public void createTable(String companyCode) {
		memberCategoryDao.createTable(companyCode);
	}

	@Override
	public void addMemberCategoryByBatch(String companyCode,
			List<RawMemberCategoryDto> memberCategoryDtos) {
		memberCategoryDao.addMemberCategoryByBatch(companyCode, memberCategoryDtos);
		
	}

}
