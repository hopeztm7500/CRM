package com.crm.service.spec;

import java.util.List;

import com.crm.dto.RFMResultDto;

public interface IRfmResultService {

	public List<RFMResultDto> getAllRfmResult(String companyCode);
	
}
