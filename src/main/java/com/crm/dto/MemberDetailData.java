package com.crm.dto;

import java.util.List;

public class MemberDetailData {

	private MemberDto basic;
	private RFMResultDto rfm;
	private List<StoreConDto> favoriteStores;
	private List<SKUDto> skus;
	
	
	public MemberDto getBasic() {
		return basic;
	}
	public void setBasic(MemberDto basic) {
		this.basic = basic;
	}
	public RFMResultDto getRfm() {
		return rfm;
	}
	public void setRfm(RFMResultDto rfm) {
		this.rfm = rfm;
	}
	public List<StoreConDto> getFavoriteStores() {
		return favoriteStores;
	}
	public void setFavoriteStores(List<StoreConDto> favoriteStores) {
		this.favoriteStores = favoriteStores;
	}
	public List<SKUDto> getSkus() {
		return skus;
	}
	public void setSkus(List<SKUDto> skus) {
		this.skus = skus;
	}
	
	
	
}
