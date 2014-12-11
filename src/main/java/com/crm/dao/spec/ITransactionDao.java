package com.crm.dao.spec;

import java.util.List;

import com.crm.dto.TransactionDto;

public interface ITransactionDao {
	
	public TransactionDto getByCode(String companyCode, String transactionId);
	public boolean update(String companyCode, TransactionDto transactionDto);
	public boolean insert(String companyCode, TransactionDto transactionDto);
	public boolean insert(String companyCode, List<TransactionDto> transactions);
	public boolean delete(String companyCode, int transactionId);
	public boolean insertByBatch(String companyCode, List<TransactionDto> transactionDtos);
	public List<TransactionDto> getAllTransaction(String companyCode);
	
}
