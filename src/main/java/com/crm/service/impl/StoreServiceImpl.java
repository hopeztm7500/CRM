package com.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.spec.IStoreDao;
import com.crm.dto.StoreDto;
import com.crm.service.spec.IStoreService;

@Service
public class StoreServiceImpl implements IStoreService {

	@Autowired
	private IStoreDao storeDao;
	@Override
	public List<StoreDto> getAllStore(String companyCode) {
		return storeDao.getAllStore(companyCode);
	}

	@Override
	public StoreDto getStoreById(String companyCode, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addStore(String companyCode, StoreDto store) {
		// TODO Auto-generated method stub

	}

}
