package com.crm.dto;

public class RFMResultDto {
	private String id;
	private double rScore;
	private double fScore;
	private double mScore;
	private double rfmScore;
	private long recency;
	private int frequency;
	private double monetary;
	private int rfmCategory;
	
	public RFMResultDto(String id, double rScore, double fScore, double mScore, double rfmScore, long recency, int frequency, double monetary, int rfmCategory){
		this.id = id;
		this.rScore = rScore;
		this.fScore = fScore;
		this.mScore = mScore;
		this.rfmScore = rfmScore;
		this.recency = recency;
		this.frequency = frequency;
		this.monetary = monetary;
		this.rfmCategory = rfmCategory;
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getrScore() {
		return rScore;
	}
	public void setrScore(double rScore) {
		this.rScore = rScore;
	}
	public double getfScore() {
		return fScore;
	}
	public void setfScore(double fScore) {
		this.fScore = fScore;
	}
	public double getmScore() {
		return mScore;
	}
	public void setmScore(double mScore) {
		this.mScore = mScore;
	}
	public double getRfmScore() {
		return rfmScore;
	}
	public void setRfmScore(double rfmScore) {
		this.rfmScore = rfmScore;
	}
	public long getRecency() {
		return recency;
	}
	public void setRecency(long recency) {
		this.recency = recency;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public double getMonetary() {
		return monetary;
	}
	public void setMonetary(double monetary) {
		this.monetary = monetary;
	}
	public int getRfmCategory() {
		return rfmCategory;
	}
	public void setRfmCategory(int rfmCategory) {
		this.rfmCategory = rfmCategory;
	}
}
