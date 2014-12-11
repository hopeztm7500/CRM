package com.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.spec.IRfmResultDao;
import com.crm.dto.RFMResultDto;
import com.crm.service.spec.IRfmResultService;

@Service
public class RfmResultServiceImpl implements IRfmResultService {

	@Autowired
	private IRfmResultDao rfmResultDao;
	
	@Override
	public List<RFMResultDto> getAllRfmResult(String companyCode) {
		return rfmResultDao.getAllRfmResult(companyCode);
	}

}
