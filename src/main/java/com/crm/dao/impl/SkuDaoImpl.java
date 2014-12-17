package com.crm.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.wrappers.StringTrimmedResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import util.DBUtility;

import com.crm.dao.spec.ISkuDao;
import com.crm.dto.SKUConDto;
import com.crm.dto.SKUDto;

@Repository
public class SkuDaoImpl implements ISkuDao {

	private static String SQL_CREATE_TABLE = "CREATE TABLE %s (sku_code varchar(50) primary key, sku_name varchar(100)) "
			+ "ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci";
	private static String SQL_INSERT = "INSERT INTO %s (sku_code,  sku_name) values(?, ?)";
	private static String SQL_QUERY_ALL = "SELECT (sku_code, sku_name) from %s ";
	private static String SQL_QUERY_BY_CODE = "SELECT (sku_code, sku_name) from %s where sku_code = ? ";
	private static String SQL_QUERY_FAV = "select S.sku_code, S.sku_name, count(T.sku_number) from %s S, %s T "
			+ " where T.sku_number = S.sku_code and T.member_id = ? group by(T.sku_number) order by count(T.sku_number) DESC";
	
	
	private static class SkuMapper implements RowMapper<SKUDto>{
		@Override
		public SKUDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			String skuCode = rs.getString(1);
			String skuName = rs.getString(2);
			return new SKUDto(skuCode, skuName);
		}
	}
	
	private static class SkuConMapper implements RowMapper<SKUConDto>{

		@Override
		public SKUConDto mapRow(ResultSet rs, int index) throws SQLException {
			String skuCode = rs.getString(1);
			String skuName = rs.getString(2);
			int count = rs.getInt(3);
			return new SKUConDto(new SKUDto(skuCode, skuName), count);
		}
		
	}
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<SKUDto> getAllSku(String companyCode) {
		
		String tableName = DBUtility.companySkuTableName(companyCode);
		String SQL = String.format(SQL_QUERY_ALL, tableName);
		return jdbcTemplate.query(SQL, new SkuMapper());
	}

	@Override
	public SKUDto getSKUByCode(String companyCode, final String code) {
		String tableName = DBUtility.companySkuTableName(companyCode);
		String SQL = String.format(SQL_QUERY_BY_CODE, tableName);
		
		List<SKUDto> skuDtos = jdbcTemplate.query(SQL, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, code);
			}
		}, new SkuMapper());
		
		if(skuDtos.size() > 0){
			return skuDtos.get(0);
		}
		return null;
	}

	@Override
	public void addSku(String companyCode, final SKUDto newSku) {
		String tableName = DBUtility.companySkuTableName(companyCode);
		String SQL = String.format(SQL_INSERT, tableName);
		
		jdbcTemplate.update(SQL, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, newSku.getSkuCode());
				ps.setString(2, newSku.getSkuName());
				
			}
		});
	}

	@Override
	public void addSkuByBatch(String companyCode, final List<SKUDto> skuDtos) {
		
		String tableName = DBUtility.companySkuTableName(companyCode);
		String SQL = String.format(SQL_INSERT, tableName);
		
		jdbcTemplate.batchUpdate(SQL, new BatchPreparedStatementSetter(){

			@Override
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				ps.setString(1, skuDtos.get(i).getSkuCode());
				ps.setString(2, skuDtos.get(i).getSkuName());
			}

			@Override
			public int getBatchSize() {
				return skuDtos.size();
			}
			
		});
	}

	public void insertTestData(String companyCode){
		
		String tableName = DBUtility.companySkuTableName(companyCode);
		String createTablesql = String.format(SQL_CREATE_TABLE, tableName);
		jdbcTemplate.update(createTablesql);
		addSkuByBatch(companyCode, SKUDto.GenerateTestData());
	}
	
	@Override
	public List<SKUConDto> getFavoriteSkus(String companyCode, final String memberId) {
		String transTable = DBUtility.TransTableName(companyCode);
		String skuTable = DBUtility.companySkuTableName(companyCode);
		String SQL = String.format(SQL_QUERY_FAV, skuTable, transTable);
		return jdbcTemplate.query(SQL, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, memberId);
			}
		}, new SkuConMapper());
	}
	

}
