package com.crm.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.wrappers.StringTrimmedResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import util.DBUtility;

import com.crm.dao.spec.IStoreDao;
import com.crm.dto.StoreConDto;
import com.crm.dto.StoreDto;


@Repository
public class StoreDaoImpl implements IStoreDao{

	private static String SQL_CREATE_TABLE = "CREATE TABLE %s (id int NOT NULL AUTO_INCREMENT PRIMARY KEY, store_code varchar(50), store_name varchar(50), lon double, lat double, "
			+ " address varchar(50), province varchar(30), city varchar(30), open_date date) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci ";
	
	private static String SQL_INSERT = "INSERT INTO %s(store_code, store_name, lon, lat, address, province, city, open_date) values(?, ?, ?, ?, ?, ?, ?, ?)";
	private static String SQL_QUERY_BY_ID = "SELECT id, store_code, store_name, lon, lat, address, province, city, open_date from %s where id=?";
	private static String SQL_QUERY_ALL = "SELECT id, store_code, store_name, lon, lat, address, province, city, open_date from %s";
	private static String SQL_FAV_STORES = "select S.id, S.store_code, S.store_name, S.lon, S.lat, count(T.store_code) from %s S, %s T "
			+ " where T.store_code = S.store_code and T.member_id = ? group by(T.store_code) order by count(T.store_code) DESC";
	
	
	private static class StoreMapper implements RowMapper<StoreDto>{
		@Override
		public StoreDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			int id = rs.getInt(1);
			String code = rs.getString(2);
			String name = rs.getString(3);
			double lon = rs.getDouble(4);
			double lat = rs.getDouble(5);
			String address = rs.getString(6);
			String province = rs.getString(7);
			String city = rs.getString(8);
			Date openDate = new Date(rs.getDate(9).getTime());
			
			return new StoreDto(id, code, name, lon, lat, address, province, city, openDate);
		}
		
	}
	
	private static class StoreConMapper implements RowMapper<StoreConDto>{
		@Override
		public StoreConDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			int storeId = rs.getInt(1);
			String storeCode = rs.getString(2);
			String storeName = rs.getString(3);
			double lon = rs.getDouble(4);
			double lat = rs.getDouble(5);
			int count = rs.getInt(6);
			
			StoreDto storeDto = new StoreDto(storeId, storeCode, storeName, lon, lat, "", "", "", null);
			return new StoreConDto(storeDto, count);
			
		}
		
	}
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public List<StoreDto> getAllStore(String companyCode) {
		String tableName = DBUtility.CompanyStoresTableName(companyCode);
		String sql = String.format(SQL_QUERY_ALL, tableName);
		
		return jdbcTemplate.query(sql, new StoreMapper());
	}

	@Override
	public StoreDto getStoreById(String companyCode, final int id) {
		String tableName = DBUtility.CompanyStoresTableName(companyCode);
		String sql = String.format(SQL_QUERY_BY_ID, tableName);
		
		List<StoreDto> stores = jdbcTemplate.query(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, id);
			}
		}, new StoreMapper());
		
		if(stores.size() > 0){
			return stores.get(0);
		}
		return null;
		
	}

	@Override
	public void addStore(String companyCode, final StoreDto store) {
		String tableName = DBUtility.CompanyStoresTableName(companyCode);
		String sql = String.format(SQL_INSERT, tableName);
		
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, store.getStoreCode());
				ps.setString(2, store.getStoreName());
				ps.setDouble(3, store.getLongtitude());
				ps.setDouble(4, store.getLatitude());
				ps.setString(5, store.getAddress());
				ps.setString(6, store.getProvince());;
				ps.setString(7, store.getCity());
				ps.setDate(8, store.getOpenDate() == null ? new java.sql.Date(new Date().getTime()) : 
					new java.sql.Date(store.getOpenDate().getTime()));
			}
		} );
	}
	
	public static List<StoreDto> getTestData(){
		List<StoreDto> stores = new ArrayList<StoreDto>();
		
		stores.add(new StoreDto(1, "SDFX000010", "延安西路全家便利店",121.538988, 31.229916, "延安西路1088号长风中心1楼", "上海", "长宁区", new Date()));
		stores.add(new StoreDto(2, "SDFX000011", "福泉路全家便利店",121.538922, 31.229933, "福泉路1088号长风中心1楼","上海", "长宁区", new Date()));
		stores.add(new StoreDto(3, "SDFX000012", "仙霞西路路全家便利店",121.531988, 31.229316, "仙霞西路1088号长风中心1楼","上海", "静安区", new Date()));
		stores.add(new StoreDto(4, "SDFX000013", "江苏路全家便利店",121.530988, 31.229116, "江苏路1088号长风中心1楼","上海", "浦东新区", new Date()));
		
		return stores;
	}

	@Override
	public List<StoreConDto> getFavirateStores(String companyCode, final String memberId) {
		String storeTable = DBUtility.CompanyStoresTableName(companyCode);
		String transTable = DBUtility.TransTableName(companyCode);
		String SQL = String.format(SQL_FAV_STORES, storeTable, transTable);
		
		return jdbcTemplate.query(SQL, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, memberId);
			}
		}, new StoreConMapper());
	}

	@Override
	public void addStores(String companyCode, final List<StoreDto> stores) {
		String tableName = DBUtility.CompanyStoresTableName(companyCode);
		String sql = String.format(SQL_INSERT, tableName);
		
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, stores.get(i).getStoreCode());
				ps.setString(2, stores.get(i).getStoreName());
				ps.setDouble(3, stores.get(i).getLongtitude());
				ps.setDouble(4, stores.get(i).getLatitude());
				ps.setString(5, stores.get(i).getAddress());
				ps.setString(6, stores.get(i).getProvince());;
				ps.setString(7, stores.get(i).getCity());
				ps.setDate(8, new java.sql.Date(stores.get(i).getOpenDate().getTime()));
			
			}
			
			@Override
			public int getBatchSize() {
				return stores.size();
			}
		});
	};

}
