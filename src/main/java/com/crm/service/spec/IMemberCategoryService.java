package com.crm.service.spec;

import com.crm.dto.MemberCategoryDto;

public interface IMemberCategoryService {

	public MemberCategoryDto getMemberCategoryByCategoryId(String companyCode, int id);
	public void createNewCategory(String companyCode, MemberCategoryDto memberCategory);
}
