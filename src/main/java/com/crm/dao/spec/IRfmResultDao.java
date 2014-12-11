package com.crm.dao.spec;

import java.util.List;

import com.crm.dto.RFMResultDto;

public interface IRfmResultDao {
	public List<RFMResultDto> getAllRfmResult(String companyCode);
}
