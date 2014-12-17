package com.crm.dao.spec;

import java.util.List;

import com.crm.dto.SKUConDto;
import com.crm.dto.SKUDto;

public interface ISkuDao {
	public List<SKUDto> getAllSku(String companyCode);
	public SKUDto getSKUByCode(String companyCode, String code);
	public void addSku(String companyCode, SKUDto newSku);
	public void addSkuByBatch(String companyCode, List<SKUDto> skuDtos);
	public List<SKUConDto> getFavoriteSkus(String companyCode, String memberId);
}
