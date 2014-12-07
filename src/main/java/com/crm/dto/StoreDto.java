package com.crm.dto;


public class StoreDto{
	
	private int id;
	private String storeCode;
	private String storeName;
	
	private double longtitude;
	private double latitude;
	private String address;
	
	public StoreDto(int id, String storeCode, String storeName, double longtitude, double latitude, String address){
		this.id = id;
		this.storeCode = storeCode;
		this.storeName = storeName;
		this.longtitude = longtitude;
		this.latitude = latitude;
		this.address = address;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStoreCode() {
		return storeCode;
	}
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public double getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}