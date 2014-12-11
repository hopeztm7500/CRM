package com.crm.dto;

import java.util.Date;


public class StoreDto{
	
	private int id;
	private String storeCode;
	private String storeName;
	
	private double longtitude;
	private double latitude;
	private Date openDate;
	private String province;
	private String city;
	private String address;
	
	public StoreDto(int id, String storeCode, String storeName, double longtitude, double latitude, String address, String province, String city, Date openDate){
		this.id = id;
		this.storeCode = storeCode;
		this.storeName = storeName;
		this.longtitude = longtitude;
		this.latitude = latitude;
		this.address = address;
		this.province = province;
		this.city = city;
		this.setOpenDate(openDate);
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	
}