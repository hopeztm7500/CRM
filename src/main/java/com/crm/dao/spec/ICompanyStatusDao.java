package com.crm.dao.spec;

import com.crm.dto.CompanyStatusDto;

public interface ICompanyStatusDao {
	public CompanyStatusDto getByUpdateDate(String companyCode, long updateDate);
	public int getMemberCount(String companyCode);
	public double getAllExpense(String companyCode);
	public double getAllMemberExpense(String companyCode);
	public int getTransactionCount(String companyCode);
	public int getMemberTransactionCount(String companyCode);
	public boolean update(CompanyStatusDto companyStatusDto);
	public boolean insert(String companyCode, CompanyStatusDto companyStatusDto);
	public boolean delete(CompanyStatusDto companyStatusDto);
	public boolean createAnalysisTables(String companyCode);
}