package com.crm.dto;

public class CompanyStatusDto {

	private int memberCount;
	private double perMemberExpense;
	private double perOrderExpense;
	private double perMemberOrder;
	private long updateDate;
	
	public CompanyStatusDto(int memberCount,double perMemberExpense,double perOrderExpense,double perMemberOrder,long updateDate){
		this.memberCount = memberCount;
		this.perMemberExpense = perMemberExpense;
		this.perMemberOrder = perMemberOrder;
		this.perOrderExpense = perOrderExpense;
		this.updateDate = updateDate;
	}
	public int getMemberCount() {
		return memberCount;
	}
	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}
	public double getPerMemberExpense() {
		return perMemberExpense;
	}
	public void setPerMemberExpense(double perMemberExpense) {
		this.perMemberExpense = perMemberExpense;
	}
	public double getPerOrderExpense() {
		return perOrderExpense;
	}
	public void setPerOrderExpense(double perOrderExpense) {
		this.perOrderExpense = perOrderExpense;
	}
	public double getPerMemberOrder() {
		return perMemberOrder;
	}
	public void setPerMemberOrder(double perMemberOrder) {
		this.perMemberOrder = perMemberOrder;
	}
	public long getUpdateDate(){
		return updateDate;
	}
	public void setUpdateDate(int updateDate){
		this.updateDate = updateDate;
	}
	
}
