package com.crm.dto;

import java.util.Date;

public class RawTransactionRecordDto {
	private String companyCode;
	private String departCode;
	private Date date;
	private String wechat;
	private String telphone;
	private String orderCode;
	private String goodsId;
	private int count;
	private double total;
	
	public RawTransactionRecordDto(String companyCode, String departCode, Date date, String wechat, String telphone, String orderCode, String goodsId,
			int count, double total){
		this.companyCode = companyCode;
		this.departCode = departCode;
		this.date = date;
		this.wechat = wechat;
		this.telphone = telphone;
		this.orderCode = orderCode;
		this.goodsId = goodsId;
		this.count = count;
		this.setTotal(total);
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getDepartCode() {
		return departCode;
	}
	public void setDepartCode(String departCode) {
		this.departCode = departCode;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	
}
