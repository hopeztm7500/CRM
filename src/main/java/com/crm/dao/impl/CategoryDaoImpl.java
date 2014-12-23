package com.crm.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import util.DBUtility;

import com.crm.dao.spec.ICategoryDao;
import com.crm.dto.CategoryDetailDto;
import com.crm.dto.CategoryDto;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

@Repository
public class CategoryDaoImpl implements ICategoryDao {

	private static String SQL_CREATE_TABLE = "CREATE TABLE %s (id int NOT NULL AUTO_INCREMENT PRIMARY KEY, "
			+ " category_name varchar(50)) " + DBUtility.SQL_DB_SETTING;
	
	private static String SQL_INSERT = "INSERT INTO %s(category_name) values(?) ";
	
	private static String SQL_QUERY_CATEGORY_PEOPLE = "SELECT C.id, C.category_name, COUNT(MC.member_id), SUM(T.sku_total)"
			+ " from %s C, %s MC, %s T "
			+ " where C.id = MC.category_id and MC.member_id = T.member_id  group by C.id ";
	
	
	private static String SQL_UPDATE = "UPDATE %s SET category_name = ? WHERE id = ?";
	private static String SQL_GET_ALL = "SELECT id, category_name from %s";
	private static String SQL_GET_BY_ID = "SELECT id, category_name from %s WHERE id = ?";
	

	private static class CategoryPeopleMapper implements RowMapper<CategoryDetailDto>{
		@Override
		public CategoryDetailDto mapRow(ResultSet rs, int index)
				throws SQLException {
			long id = rs.getLong(1);
			String categoryName = rs.getString(2);
			int count = rs.getInt(3);
			double sum = rs.getDouble(4);
			return new CategoryDetailDto(new CategoryDto(id, categoryName), sum, count);		
		}
		
	}
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	
	@Override
	public CategoryDto addCategory(String companyCode, final CategoryDto category) {
		jdbcTemplate.update(SQL_CREATE_TABLE);
		
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String tableName = DBUtility.CategoryTableName(companyCode);
		final String sql = String.format(SQL_INSERT, tableName);
		
		
		int updatecount = jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public java.sql.PreparedStatement createPreparedStatement(
					java.sql.Connection connection) throws SQLException {
				PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, category.getName());
				return ps;
			}
		}, keyHolder);
		if(updatecount > 0){
			long id = keyHolder.getKey().longValue();
			return new CategoryDto(id, category.getName());
		}
		return null;//insert failed;
	}

	@Override
	public void deleteCategory(String companyCode, CategoryDto category) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addCategoryByBatch(String companyCode,
			final List<CategoryDto> categories) {
		String tableName = DBUtility.CategoryTableName(companyCode);
		String sql = String.format(SQL_INSERT, tableName);
		
		
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(java.sql.PreparedStatement pps, int index)
					throws SQLException {
				pps.setString(1, categories.get(index).getName());
			}
			
			@Override
			public int getBatchSize() {
				return categories.size();
			}
		});

	}

	@Override
	public void updateCategory(String companyCode, CategoryDto category) {
		// TODO Auto-generated method stub

	}

	@Override
	public CategoryDto getCategoryByCode(String companyCode, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryDto> getAllCategories(String companyCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryDetailDto> getCategoryServiceDetail(String companyCode) {
		String categoryTable = DBUtility.CategoryTableName(companyCode);
		String memberCategoryTable = DBUtility.MemberCategoryTableName(companyCode);
		String transitionTable = DBUtility.TransTableName(companyCode);
		String sql = String.format(SQL_QUERY_CATEGORY_PEOPLE, categoryTable, memberCategoryTable, transitionTable);
		
		return jdbcTemplate.query(sql, new CategoryPeopleMapper());
	}

	@Override
	public void createTable(String companyCode) {
		String tableName = DBUtility.CategoryTableName(companyCode);
		String sql = String.format(SQL_CREATE_TABLE, tableName);
		
		jdbcTemplate.update(sql);
		
		List<CategoryDto> categoryDtos = CategoryDto.getTestData();
		addCategoryByBatch(companyCode, categoryDtos);
		
		
	}

}
