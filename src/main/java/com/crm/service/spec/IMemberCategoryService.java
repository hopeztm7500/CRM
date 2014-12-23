package com.crm.service.spec;

import java.util.List;

import com.crm.dto.MemberCategoryDto;
import com.crm.dto.RawMemberCategoryDto;

public interface IMemberCategoryService {

	public MemberCategoryDto getMemberCategoryByCategoryId(String companyCode, int id);
	public void createNewCategory(String companyCode, MemberCategoryDto memberCategory);
	public void createTable(String companyCode);
	public void addMemberCategoryByBatch(String companyCode, List<RawMemberCategoryDto> memberCategoryDtos);
}
