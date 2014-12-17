package com.crm.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the class defined for goods sell in the store
 * @author hopeztm
 *
 */
public class SKUDto {

	private String skuCode;
	private String skuName;
	
	public SKUDto(String skuCode, String skuName){
		this.skuCode = skuCode;
		this.skuName = skuName;
	}
	
	public String getSkuCode() {
		return skuCode;
	}
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}
	public String getSkuName() {
		return skuName;
	}
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}
	
	
	public static List<SKUDto> GenerateTestData(){
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
