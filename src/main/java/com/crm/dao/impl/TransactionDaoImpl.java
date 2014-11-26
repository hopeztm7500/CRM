package com.crm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crm.dao.spec.ITransactionDao;
import com.crm.dto.TransactionDto;

@Repository
public class TransactionDaoImpl implements ITransactionDao {

	@Override
	public TransactionDto getByCode(String transactionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(TransactionDto transactionDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(TransactionDto transactionDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int transactionId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(List<TransactionDto> transactions) {
		// TODO Auto-generated method stub
		return false;
	}

}
