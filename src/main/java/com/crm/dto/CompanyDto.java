package com.crm.dto;

public class CompanyDto {

	private int id;
	private String companyCode;
	private String companyName;
	
	public CompanyDto(int id, String companyCode, String companyName){
		this.id = id;
		this.companyCode = companyCode;
		this.companyName = companyName;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
}
