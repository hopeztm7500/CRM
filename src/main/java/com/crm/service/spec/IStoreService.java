package com.crm.service.spec;
import java.util.List;

import com.crm.dto.StoreDto;

public interface IStoreService {

	public List<StoreDto> getAllStore(String companyCode);
	public StoreDto getStoreById(String companyCode, int id);
	public void addStore(String companyCode, StoreDto store);
	public List<StoreDto> getFavirateStores(String companyCode, String memberId);
	public void addStores(String companyCode, List<StoreDto> stores);
	
	
}
