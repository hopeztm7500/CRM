package com.crm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.spec.ITransactionDao;
import com.crm.dto.TransactionDto;
import com.crm.service.spec.ITransationService;

@Service
public class TransationServiceImpl implements ITransationService {

	@Autowired ITransactionDao transactionDao;
	
	@Override
	public TransactionDto getByCode(String transactionId) {
		return transactionDao.getByCode(transactionId);
	}

	@Override
	public boolean update(TransactionDto transactionDto) {
	    return transactionDao.update(transactionDto);
	}

	@Override
	public boolean insert(TransactionDto transactionDto) {
		return transactionDao.insert(transactionDto);
	}

	@Override
	public boolean delete(int transactionId) {
		return transactionDao.delete(transactionId);
	}

}
