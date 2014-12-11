package com.crm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.spec.ICompanyStatusDao;
import com.crm.dto.CompanyStatusDto;
import com.crm.service.spec.IAnalysisService;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class AnalysisServiceImpl implements IAnalysisService {

	@Autowired
	private ICompanyStatusDao companyStatusDao;
	
	@Override
	public CompanyStatusDto getCompanyStatusByUpdateDate(String companyCode, long updateDate) {
		return companyStatusDao.getByUpdateDate(companyCode, updateDate);
	}
	
	@Override
	public int getCompanyMemberCount(String companyCode) {
		return companyStatusDao.getMemberCount(companyCode);
	};
	
	@Override
	public boolean updateCompanyStatus(CompanyStatusDto companyStatusDto) {
		return companyStatusDao.update(companyStatusDto);
	}

	@Override
	public boolean insertCompanyStatus(String companyCode, CompanyStatusDto companyStatusDto) {
		return companyStatusDao.insert(companyCode, companyStatusDto);
	}

	@Override
	public boolean insertCompanyStatus(String companyCode){
		int member_count = companyStatusDao.getMemberCount(companyCode);
		double perMemberExpense = companyStatusDao.getAllMemberExpense(companyCode)/member_count;
		double perOrderExpense = companyStatusDao.getAllExpense(companyCode)/companyStatusDao.getTransactionCount(companyCode);
		double perMemberOrder = companyStatusDao.getMemberTransactionCount(companyCode)/(double)member_count;
		long update_date = new Date().getTime();
		CompanyStatusDto company_status = new CompanyStatusDto(member_count,perMemberExpense,perOrderExpense,perMemberOrder,update_date);
		return companyStatusDao.insert(companyCode, company_status);		
	}
	@Override
	public boolean deleteCompanyStatus(CompanyStatusDto companyStatusDto) {
		return companyStatusDao.delete(companyStatusDto);
	}

	@Override
	public boolean createAnalysisTables(String companyCode) {
		// TODO Auto-generated method stub
		return companyStatusDao.createAnalysisTables(companyCode);
	}

}
