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

import com.crm.dao.spec.ICompanyDao;
import com.crm.dto.CompanyDto;

@Repository
public class CompanyDaoImpl implements ICompanyDao {

	private String SQL_SELECT = "SELECT id, company_code, company_name from Company where company_code = ? ";
	private String SQL_INSERT = "INSERT INTO Company(company_code, company_name) values(?, ?) ";
	private String SQL_CREATE_TABLE = "create table ";
	private String SQL_TABLE_COLUMNS = "("
			+ "id varchar(50) NOT NULL PRIMARY KEY, wechat varchar(50), "
			+ "telphone varchar(50), name varchar(50), gender varchar(50))";
			
	private String SQL_DB_SETTING = " ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci ";
	private String SQL_TRANSACTION_COLUMNS = "(id int NOT NULL AUTO_INCREMENT PRIMARY KEY, member_id varchar(50) NOT NULL , dept_code varchar(60),"
			+ "  con_date  bigint,"
			+ "  order_code  varchar(60), goods_code varchar(60),"
			+ "  count int, total double, ";

    
	@Autowired
	JdbcTemplate template;
	
	private static class CompanyRowMapper implements RowMapper<CompanyDto>{
		@Override
		public CompanyDto mapRow(ResultSet arg0, int arg1) throws SQLException {
			int id = arg0.getInt("id");
			String companyCode = arg0.getString("company_code");
			String companyName = arg0.getString("company_name");
			return new CompanyDto(id, companyCode, companyName);
		}	
	}
	private static CompanyRowMapper COMPANY_ROW_MAPPER = new CompanyRowMapper();
	
	
	@Override
	public CompanyDto getByCode(final String companyCode) {
		
		List<CompanyDto> companys = template.query(SQL_SELECT, new PreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement statement) throws SQLException {
				statement.setString(1, companyCode);
			}
		}, COMPANY_ROW_MAPPER);
		
		if(companys.isEmpty()){
			return null;
		}
		return companys.get(0);
	}

	@Override
	public boolean update(CompanyDto companyDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(final CompanyDto companyDto) {
		try {
			template.getDataSource().getConnection().setAutoCommit(false);
			
			int rows = template.update(SQL_INSERT, new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement arg0) throws SQLException {
					
					arg0.setString(1, companyDto.getCompanyCode());
					arg0.setString(2, companyDto.getCompanyName());	
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
	public boolean delete(CompanyDto companyDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createDataTables(String companyCode) {
		// TODO Auto-generated method stub
		String finalCreateMemberTableSQL = SQL_CREATE_TABLE + DBUtility.MemberTableName(companyCode)
				+ SQL_TABLE_COLUMNS + SQL_DB_SETTING;
		template.update(finalCreateMemberTableSQL);
		
		String finalCreateTransactionTableSQL = SQL_CREATE_TABLE + DBUtility.TransTableName(companyCode) 
				+ SQL_TRANSACTION_COLUMNS 
				+ DBUtility.GenerateForeignKeyPart(DBUtility.TransTableName(companyCode), "member_id",
						DBUtility.MemberTableName(companyCode), "id") 
			    + ")"
			    + SQL_DB_SETTING;
		
		
		template.update(finalCreateTransactionTableSQL);
		
		return true;
	}

}
