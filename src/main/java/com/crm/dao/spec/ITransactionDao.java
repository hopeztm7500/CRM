package com.crm.dao.spec;

import java.util.List;

import com.crm.dto.TransactionDto;

public interface ITransactionDao {
	
	public TransactionDto getByCode(String transactionId);
	public boolean update(TransactionDto transactionDto);
	public boolean insert(TransactionDto transactionDto);
	public boolean insert(List<TransactionDto> transactions);
	public boolean delete(int transactionId);
	
}
