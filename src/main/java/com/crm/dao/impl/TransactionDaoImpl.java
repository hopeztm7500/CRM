package com.crm.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import util.DBUtility;

import com.crm.dao.spec.ITransactionDao;
import com.crm.dto.TransactionDto;

@Repository
public class TransactionDaoImpl implements ITransactionDao {

	private static String SQL_CREATE_TABLE = "INSERT INTO %s(member_id, store_code, purchase_date, order_number,"
			+ " SKU_number, SKU_count, SKU_total) "
			+ " values(?, ?, ?, ?, ?, ?, ?)";
	
	private static String SQL_GET_ALL = "SELECT member_id, store_code, purchase_date, order_number, SKU_number, "
			+ "SKU_count, SKU_total from %s ";
	
	private static class TransRowMapper implements RowMapper<TransactionDto>{
		@Override
		public TransactionDto mapRow(ResultSet rs, int arg1)
				throws SQLException {
			return new TransactionDto(rs.getString(1), rs.getString(2), rs.getLong(3), 
					rs.getString(4), rs.getString(5), rs.getInt(6), rs.getDouble(7));
		}
	}
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private String generateInsertSQL(String companyCode){
		return String.format(SQL_CREATE_TABLE, DBUtility.TransTableName(companyCode));
	}
	
	private String generateQuerySQL(String companyCode){
		return String.format(SQL_GET_ALL, DBUtility.TransTableName(companyCode));
	}
	
	@Override
	public TransactionDto getByCode(String companyCode, String transactionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(String companyCode, TransactionDto transactionDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(String companyCode, TransactionDto transactionDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String companyCode, int transactionId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(String companyCode, List<TransactionDto> transactions) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertByBatch(String companyCode,
			final List<TransactionDto> trans) {
		
		String SQL = generateInsertSQL(companyCode);
		
		jdbcTemplate.batchUpdate(SQL, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pps, int index) throws SQLException {
				pps.setString(1, trans.get(index).getMemberId());
				pps.setString(2, trans.get(index).getDepartment());
				pps.setLong(3, trans.get(index).getDate());
				pps.setString(4, trans.get(index).getOrderCode());
				pps.setString(5, trans.get(index).getGoodsId());
				pps.setInt(6, trans.get(index).getCount());
				pps.setDouble(7, trans.get(index).getTotal());
			}
			
			@Override
			public int getBatchSize() {
				return trans.size();
			}
		});
		
		return true;
	}

	@Override
	public List<TransactionDto> getAllTransaction(String companyCode) {
		
		String SQL = generateQuerySQL(companyCode);
		
		return jdbcTemplate.query(SQL, new TransRowMapper());
	}

}
