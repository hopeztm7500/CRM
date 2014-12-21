package com.crm.dao.spec;

import com.crm.dto.CategoryDto;
import com.crm.dto.MemberCategoryDto;

public interface IMemberCategoryDao {

	public MemberCategoryDto getMemberCategoryCategory(String companyCode, CategoryDto id);

	public void createNewCategory(String companyCode, MemberCategoryDto memberCategory);
}
