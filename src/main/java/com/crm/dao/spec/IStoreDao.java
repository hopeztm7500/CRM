package com.crm.dao.spec;

import java.util.List;

import com.crm.dto.StoreDto;

public interface IStoreDao {


	public List<StoreDto> getAllStore(String companyCode);
	public StoreDto getStoreById(String companyCode, int id);
	public void addStore(String companyCode, StoreDto store);
}
