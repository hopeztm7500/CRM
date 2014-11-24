package com.crm.dao.spec;

import com.crm.dto.CompanyDto;
import com.crm.dto.MemberDto;

public interface IMemberDao {
	public MemberDto getMemberById(int id);
	public MemberDto getMemberByWechat(String wechart);
	
	public boolean insert(CompanyDto companyDto);
	public boolean delete(int id);
	public boolean update(CompanyDto companyDto);
	
}
