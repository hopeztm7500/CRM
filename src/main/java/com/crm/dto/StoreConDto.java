package com.crm.dto;


public class StoreConDto {

	private StoreDto store;
	private int times;
	
	public StoreConDto(){
		
	}
	public StoreConDto(StoreDto storeDto, int times){
		setStore(storeDto);
		setTimes(times);
	}
	
	public StoreDto getStore() {
		return store;
	}
	public void setStore(StoreDto store) {
		this.store = store;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	
}
