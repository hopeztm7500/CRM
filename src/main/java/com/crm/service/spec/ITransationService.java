package com.crm.service.spec;

import java.util.List;

import com.crm.dto.TransactionDto;

public interface ITransationService {
	public TransactionDto getByCode(String companyCode, String transactionId);
	public boolean update(String companyCode, TransactionDto transactionDto);
	public boolean insert(String companyCode, TransactionDto transactionDto);
	public boolean insertByBatch(String companyCode, List<TransactionDto> transactionDtos);
	public boolean delete(String companyCode, int transactionId);
	
}
