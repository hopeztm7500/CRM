package com.crm.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.crm.dao.spec.IStoreDao;
import com.crm.dto.StoreDto;


@Repository
public class StoreDaoImpl implements IStoreDao{

	@Override
	public List<StoreDto> getAllStore(String companyCode) {
		// TODO Auto-generated method stub
		return getTestData();
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
	
	public static List<StoreDto> getTestData(){
		List<StoreDto> stores = new ArrayList<StoreDto>();
		
		stores.add(new StoreDto(1, "CS001", "延安西路全家便利店",121.538988, 31.229916, "延安西路1088号长风中心1楼", "上海", "长宁区", new Date()));
		stores.add(new StoreDto(2, "CS002", "福泉路全家便利店",121.538922, 31.229933, "福泉路1088号长风中心1楼","上海", "长宁区", new Date()));
		stores.add(new StoreDto(3, "CS003", "仙霞西路路全家便利店",121.531988, 31.229316, "仙霞西路1088号长风中心1楼","上海", "静安区", new Date()));
		stores.add(new StoreDto(4, "CS004", "江苏路全家便利店",121.530988, 31.229116, "江苏路1088号长风中心1楼","上海", "浦东新区", new Date()));
		
		return stores;
	};

}
