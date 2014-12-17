package com.crm.service.spec;

import java.util.List;

import com.crm.dto.SKUDto;

public interface ISkuService {
	public List<SKUDto> getAllSku(String companyCode);
	public SKUDto getSKUByCode(String companyCode, String code);
	public void addSku(String companyCode, SKUDto newSku);
	public void addSkuByBatch(String companyCode, List<SKUDto> skuDtos);
	public List<SKUDto> getFavoriteSkus(String companyCode, String memberId);
	
}
