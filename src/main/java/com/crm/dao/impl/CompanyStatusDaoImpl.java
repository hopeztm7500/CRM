package com.crm.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import util.DBUtility;

import com.crm.dao.spec.ICompanyStatusDao;
import com.crm.dto.CompanyStatusDto;

@Repository
public class CompanyStatusDaoImpl implements ICompanyStatusDao {

	private String SQL_SELECT = "SELECT member_count, per_member_expense, per_order_expense, per_member_order, update_date from %s where update_date = ? ";
	private String SQL_INSERT = "INSERT INTO %s(member_count, per_member_expense, per_order_expense, per_member_order, update_date) values(?, ?, ?, ?, ?) ";
	private String SQL_DISTINCT_COUNT = "SELECT COUNT(DISTINCT ?) from %s ";
	private String SQL_SUM = "SELECT SUM(?) from %s ";
	private String NOT_NULL_CLAUSE = "WHERE !isnull(member_id) ";
    
	@Autowired
	JdbcTemplate template;
	
	private static class CompanyStatusRowMapper implements RowMapper<CompanyStatusDto>{
		@Override
		public CompanyStatusDto mapRow(ResultSet arg0, int arg1) throws SQLException {
			int memberCount = arg0.getInt("member_count");
			double perMemberExpense = arg0.getDouble("per_member_expense");
			double perOrderExpense = arg0.getDouble("per_order_expense");
			double perMemberOrder = arg0.getDouble("per_member_order");
			long updateDate = arg0.getLong("update_date");
			return new CompanyStatusDto(memberCount,perMemberExpense,perMemberOrder,perOrderExpense,updateDate);
		}	
	}
	private static CompanyStatusRowMapper COMPANY_STATUS_ROW_MAPPER = new CompanyStatusRowMapper();
	
	private String generateQuerySQL(String companyCode){
		return String.format(SQL_SELECT, DBUtility.CompanyStatusTableName(companyCode));
	}
	private String generateInsertSQL(String companyCode){
		return String.format(SQL_INSERT, DBUtility.CompanyStatusTableName(companyCode));
	}
	private String generateMemberCountSQL(String companyCode, String columnName){
		return String.format(SQL_DISTINCT_COUNT, DBUtility.MemberTableName(companyCode)).replace("?", columnName);
	}
	private String generateTransactionCountSQL(String companyCode, String columnName){
		return String.format(SQL_DISTINCT_COUNT, DBUtility.TransTableName(companyCode)).replace("?", columnName);
	}
	private String generateSumSQL(String companyCode, String columnName){
		return String.format(SQL_SUM, DBUtility.TransTableName(companyCode)).replace("?", columnName);
	}
	
	@Override
	public CompanyStatusDto getByUpdateDate(String companyCode,final long updateDate) {
		
		List<CompanyStatusDto> company_statuses = template.query(generateQuerySQL(companyCode), new PreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement statement) throws SQLException {
				statement.setString(1, String.valueOf(updateDate));
			}
		}, COMPANY_STATUS_ROW_MAPPER);
		
		if(company_statuses.isEmpty()){
			return null;
		}
		return company_statuses.get(0);
	}

	@Override
	public boolean update(CompanyStatusDto companyStatusDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(String companyCode, final CompanyStatusDto companyStatusDto) {
		try {
			template.getDataSource().getConnection().setAutoCommit(false);
			
			int rows = template.update(generateInsertSQL(companyCode), new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement arg0) throws SQLException {
					
					arg0.setString(1, String.valueOf(companyStatusDto.getMemberCount()));
					arg0.setString(2, String.valueOf(companyStatusDto.getPerMemberExpense()));
					arg0.setString(3, String.valueOf(companyStatusDto.getPerOrderExpense()));
					arg0.setString(4, String.valueOf(companyStatusDto.getPerMemberOrder()));
					arg0.setString(5, String.valueOf(companyStatusDto.getUpdateDate()));
				}
			});
			return rows == 1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

	@Override
	public boolean delete(CompanyStatusDto companyStatusDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createAnalysisTables(String companyCode) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public int getMemberCount(String companyCode){
		String SQL = generateMemberCountSQL(companyCode,"id");
		return template.queryForObject(SQL, Integer.class);
	}
	@Override
	public int getTransactionCount(String companyCode){
		String SQL = generateTransactionCountSQL(companyCode,"order_number");
		return template.queryForObject(SQL, Integer.class);
	}
	@Override
	public int getMemberTransactionCount(String companyCode){
		String SQL = generateTransactionCountSQL(companyCode,"order_number") + NOT_NULL_CLAUSE;
		return template.queryForObject(SQL, Integer.class);
	}
	@Override
	public double getAllExpense(String companyCode){
		String SQL = generateSumSQL(companyCode,"SKU_total");
		return template.queryForObject(SQL, Double.class);
	}
	@Override
	public double getAllMemberExpense(String companyCode){
		String SQL = generateSumSQL(companyCode,"SKU_total") + NOT_NULL_CLAUSE;
		return template.queryForObject(SQL, Double.class);
	}
}
