package com.crm.dto;

public class SKUConDto {

	private SKUDto skuDto;
	private int times;
	
	public SKUConDto(){
		
	}
	
	public SKUConDto(SKUDto skuDto, int times){
		this.skuDto = skuDto;
		this.times = times;
	}
	
	public SKUDto getSkuDto() {
		return skuDto;
	}
	public void setSkuDto(SKUDto skuDto) {
		this.skuDto = skuDto;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
}
