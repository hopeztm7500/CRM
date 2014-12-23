package com.crm.dao.spec;

import java.util.List;

import com.crm.dto.CategoryDto;
import com.crm.dto.MemberCategoryDto;
import com.crm.dto.RawMemberCategoryDto;

public interface IMemberCategoryDao {

	public MemberCategoryDto getMemberCategoryCategory(String companyCode, CategoryDto id);

	public void createNewCategory(String companyCode, MemberCategoryDto memberCategory);

	public void createTable(String companyCode);

	public void addMemberCategoryByBatch(String companyCode, List<RawMemberCategoryDto> memberCategoryDtos);
}
