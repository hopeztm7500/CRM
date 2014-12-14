package com.crm.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the class defined for goods sell in the store
 * @author hopeztm
 *
 */
public class SKUDto {

	private String skuId;
	private String skuName;
	
	public SKUDto(String skuId, String skuName){
		this.skuId = skuId;
		this.skuName = skuName;
	}
	
	public String getSkuId() {
		return skuId;
	}
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	public String getSkuName() {
		return skuName;
	}
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}
	
	
	public List<SKUDto> GenerateTestData(){
		List<SKUDto> dtos = new ArrayList<SKUDto>();
		dtos.add(new SKUDto("1", "面包"));
		dtos.add(new SKUDto("2", "香肠"));
		dtos.add(new SKUDto("3", "咖啡"));
		dtos.add(new SKUDto("4", "钱包"));
		dtos.add(new SKUDto("5", "手机"));
		dtos.add(new SKUDto("6", "电脑"));
		return dtos;
	}
	
}
