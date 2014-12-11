package com.crm.service.spec;

import com.crm.dto.CompanyDto;

public interface ICompanyService {
	public CompanyDto getByCode(String companyCode);
	public boolean update(CompanyDto companyDto);
	public boolean insert(CompanyDto companyDto);
	public boolean delete(CompanyDto companyDto);
	public boolean createDataTables(String companyCode);
}
