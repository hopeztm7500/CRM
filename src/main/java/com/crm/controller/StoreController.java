package com.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.dto.StoreDto;
import com.crm.service.spec.IStoreService;

@Controller
@RequestMapping("/")

public class StoreController {

	@Autowired
	private IStoreService storeService;
	
	public String getCompanyCode(){
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		return "SDFX";
	}
	
	@RequestMapping(value="/all-stores-data")
	@ResponseBody public List<StoreDto> allStoreData(ModelMap model) {
		return storeService.getAllStore(this.getCompanyCode());
	}
	
	
	
	@RequestMapping(value="/stores")
	public String allStores(ModelMap model) {
		return "all-stores";
	}
	
	
	

	@RequestMapping(value = "/add-new-store", method = RequestMethod.POST)
	@ResponseBody public String addNewStore(@RequestBody StoreDto storeDto) {
		String companyCode = getCompanyCode();
		storeService.addStore(companyCode, storeDto);
		return "success";
	}
	
	
}

