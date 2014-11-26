package com.crm.service.spec;

import com.crm.dto.TransactionDto;

public interface ITransationService {
	public TransactionDto getByCode(String transactionId);
	public boolean update(TransactionDto transactionDto);
	public boolean insert(TransactionDto transactionDto);
	public boolean delete(int transactionId);
}
