package com.crm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.spec.ICompanyDao;
import com.crm.dto.CompanyDto;
import com.crm.service.spec.ICompanyService;
@Service
public class CompanyServiceImpl implements ICompanyService {

	@Autowired
	private ICompanyDao companyDao;
	
	@Override
	public CompanyDto getByCode(String companyCode) {
		return companyDao.getByCode(companyCode);
	}

	@Override
	public boolean update(CompanyDto companyDto) {
		return companyDao.update(companyDto);
	}

	@Override
	public boolean insert(CompanyDto companyDto) {
		return companyDao.insert(companyDto);
	}

	@Override
	public boolean delete(CompanyDto companyDto) {
		return companyDao.delete(companyDto);
	}

}
