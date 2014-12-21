package com.crm.dao.impl;

import com.crm.dao.spec.IMemberCategoryDao;
import com.crm.dto.CategoryDto;
import com.crm.dto.MemberCategoryDto;

public class MemberCategoryDaoImpl implements IMemberCategoryDao {

	private String SQL_CREATE_TABLE = "CREATE TABLE %s (category_id int, member_id varchar(50)) ";
	private String SQL_INSERT = "INSERT INTO %s(category_id, member_id) values(?, ?)";
	private String SQL_QUEYR_BY_ID = "SELECT(category_id, member_id) from %s WHERE category_id = ?";
	
	
	@Override
	public MemberCategoryDto getMemberCategoryCategory(String companyCode,
			CategoryDto category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createNewCategory(String companyCode,
			MemberCategoryDto memberCategory) {
		// TODO Auto-generated method stub
		
	}

}
