package com.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.spec.ITransactionDao;
import com.crm.dto.TransactionDto;
import com.crm.service.spec.ITransationService;

@Service
public class TransationServiceImpl implements ITransationService {

	@Autowired ITransactionDao transactionDao;
	
	@Override
	public TransactionDto getByCode(String companyCode, String transactionId) {
		return transactionDao.getByCode(companyCode, transactionId);
	}

	@Override
	public boolean update(String companyCode, TransactionDto transactionDto) {
	    return transactionDao.update(companyCode, transactionDto);
	}

	@Override
	public boolean insert(String companyCode, TransactionDto transactionDto) {
		return transactionDao.insert(companyCode, transactionDto);
	}

	@Override
	public boolean delete(String companyCode, int transactionId) {
		return transactionDao.delete(companyCode, transactionId);
	}

	@Override
	public boolean insertByBatch(String companyCode,
			List<TransactionDto> transactionDtos) {
		return transactionDao.insertByBatch(companyCode, transactionDtos);
	}

}
