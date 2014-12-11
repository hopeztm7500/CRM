package com.crm.service.spec;

import com.crm.dto.CompanyStatusDto;

public interface IAnalysisService {
	public CompanyStatusDto getCompanyStatusByUpdateDate(String companyCode, long updateDate);
	public int getCompanyMemberCount(String companyCode);
	public boolean updateCompanyStatus(CompanyStatusDto companyStatusDto);
	public boolean insertCompanyStatus(String companyCode);
	public boolean insertCompanyStatus(String companyCode, CompanyStatusDto companyStatusDto);
	public boolean deleteCompanyStatus(CompanyStatusDto companyStatusDto);
	public boolean createAnalysisTables(String companyCode);
}