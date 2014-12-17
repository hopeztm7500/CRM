package com.crm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.crm.dao.spec.ISkuDao;
import com.crm.dto.SKUDto;

@Repository
public class SkuDaoImpl implements ISkuDao {

	@Override
	public List<SKUDto> getAllSku(String companyCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SKUDto getSKUByCode(String companyCode, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addSku(String companyCode, SKUDto newSku) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addSkuByBatch(String companyCode, List<SKUDto> skuDtos) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<SKUDto> getFavoriteSkus(String companyCode, String memberId) {
		// TODO Auto-generated method stub
		return SKUDto.GenerateTestData();
	}
	
	

}
