package com.crm.service.spec;

import java.util.List;

import com.crm.dto.RFMResultDto;

public interface IRfmResultService {

	public RFMResultDto getMyRfmResult(String companyCode, String memberId);
	public List<RFMResultDto> getAllRfmResult(String companyCode);
	
}
