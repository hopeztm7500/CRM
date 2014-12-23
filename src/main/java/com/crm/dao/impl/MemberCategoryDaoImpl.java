package com.crm.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import util.DBUtility;

import com.crm.dao.spec.IMemberCategoryDao;
import com.crm.dto.CategoryDto;
import com.crm.dto.MemberCategoryDto;
import com.crm.dto.RawMemberCategoryDto;

@Repository
public class MemberCategoryDaoImpl implements IMemberCategoryDao {

	private String SQL_CREATE_TABLE = "CREATE TABLE %s (category_id int, member_id varchar(50), PRIMARY KEY (category_id, member_id), " + 
             "FOREIGN KEY (member_id) REFERENCES  %s(id) ON DELETE CASCADE," +
             "FOREIGN KEY (category_id) REFERENCES %s (id) ON DELETE CASCADE) " + DBUtility.SQL_DB_SETTING;
	
	private String SQL_INSERT = "INSERT INTO %s(category_id, member_id) values(?, ?)";
	private String SQL_QUEYR_BY_ID = "SELECT(category_id, member_id) from %s WHERE category_id = ?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public MemberCategoryDto getMemberCategoryCategory(String companyCode,
			CategoryDto category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createNewCategory(String companyCode,
			MemberCategoryDto memberCategory) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void addMemberCategoryByBatch(String companyCode, final List<RawMemberCategoryDto> memberCategoryDtos) {
		String tableName = DBUtility.MemberCategoryTableName(companyCode);
		String sql = String.format(SQL_INSERT, tableName);
		
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pps, int index) throws SQLException {
				pps.setInt(1, memberCategoryDtos.get(index).getId());
				pps.setString(2, memberCategoryDtos.get(index).getMemberId());
			}
			
			@Override
			public int getBatchSize() {
				return memberCategoryDtos.size();
			}
		});
	}


	@Override
	public void createTable(String companyCode) {
		String tableName = DBUtility.MemberCategoryTableName(companyCode);
		String memberTable = DBUtility.MemberTableName(companyCode);
		String categoryTable = DBUtility.CategoryTableName(companyCode);
		
		String sql = String.format(SQL_CREATE_TABLE, tableName, memberTable, categoryTable);
		jdbcTemplate.update(sql);
		
		this.addMemberCategoryByBatch(companyCode, RawMemberCategoryDto.getTestData());
		
	}

	
}
