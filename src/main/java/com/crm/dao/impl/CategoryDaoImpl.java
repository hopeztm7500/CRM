package com.crm.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import util.DBUtility;

import com.crm.dao.spec.ICategoryDao;
import com.crm.dto.CategoryDto;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

@Repository
public class CategoryDaoImpl implements ICategoryDao {

	private static String SQL_CREATE_TABLE = "CREATE TABLE %s (id int NOT NULL AUTO_INCREMENT PRIMARY KEY, "
			+ " category_name varchar(50)) " + DBUtility.SQL_DB_SETTING;
	private static String SQL_INSERT = "INSERT INTO %s(category_name) values(?) ";
	
	private static String SQL_UPDATE = "UPDATE %s SET category_name = ? WHERE id = ?";
	private static String SQL_GET_ALL = "SELECT (id, category_name) from %s";
	private static String SQL_GET_BY_ID = "SELECT(id, category_name) from %s WHERE id = ?";
	

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
			List<CategoryDto> categories) {
		// TODO Auto-generated method stub

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

}
