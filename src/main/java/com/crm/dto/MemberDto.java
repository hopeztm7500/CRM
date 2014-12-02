package com.crm.dto;

public class MemberDto {
	private String id;
	private String wechat;
	private String telphone;
	public MemberDto(String id, String wechat, String telphone){
		this.id = id;
		this.wechat = wechat;
		this.telphone = telphone;
	}
	public MemberDto(String wechat, String telphone){
		this.wechat = wechat;
		this.telphone = telphone;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	
}
