package com.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.spec.ISkuDao;
import com.crm.dto.SKUDto;
import com.crm.service.spec.ISkuService;

@Service
public class SkuServiceImpl implements ISkuService {

	@Autowired
	private ISkuDao skuDao;
	
	
	@Override
	public List<SKUDto> getAllSku(String companyCode) {
		return skuDao.getAllSku(companyCode);
	}

	@Override
	public SKUDto getSKUByCode(String companyCode, String code) {
		return skuDao.getSKUByCode(companyCode, code);
	}

	@Override
	public void addSku(String companyCode, SKUDto newSku) {
		skuDao.addSku(companyCode, newSku);
	}

	@Override
	public void addSkuByBatch(String companyCode, List<SKUDto> skuDtos) {
		skuDao.addSkuByBatch(companyCode, skuDtos);
	}

	@Override
	public List<SKUDto> getFavoriteSkus(String companyCode, String memberId) {
		return skuDao.getFavoriteSkus(companyCode, memberId);
	}

}
