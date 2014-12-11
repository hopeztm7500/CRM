package com.crm.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import util.DBUtility;
import util.NamingUtility;

import com.crm.dao.spec.IMemberDao;
import com.crm.dto.MemberDto;

@Repository
public class MemberDaoImpl implements IMemberDao {

	private static String SQL_CREATE_TABLE = "INSERT INTO %s(id, wechat, telphone) VALUES(?, ?, ?)";
	private static String SQL_QUERY_FOR_IDS = "SELECT id, wechat, telphone from %s WHERE wechat = ? or telphone = ? ";
	private static String SQL_SELECT_ALL = " SELECT id, wechat, telphone, name, gender from %s ";
	private static String SQL_SELECT_BY_ID = " SELECT id, wechat, telphone, name, gender from %s where id=? ";
	
	public static class MemberRowMapper implements RowMapper<MemberDto>{

		@Override
		public MemberDto mapRow(ResultSet rs, int index) throws SQLException {
			
			MemberDto memberDto = new MemberDto(rs.getString(1), rs.getString(2), rs.getString(3));
			return memberDto;
		}
		
	}
	public static class MemberALLRowMapper implements RowMapper<MemberDto>{

		@Override
		public MemberDto mapRow(ResultSet rs, int index) throws SQLException {
			
			MemberDto memberDto = new MemberDto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			return memberDto;
		}
		
	}
	
	
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public MemberDto getMemberById(String companyCode, final String id) {
		// TODO Auto-generated method stub
		String tableName = DBUtility.MemberTableName(companyCode);
		String SQL = DBUtility.GenerateSQLOnCompany(tableName, SQL_SELECT_BY_ID);
		List<MemberDto> members = jdbcTemplate.query(SQL, new PreparedStatementSetter(){

			@Override
			public void setValues(PreparedStatement pps) throws SQLException {
				pps.setString(1, id);
			}}, new MemberRowMapper());
		if(members.size() > 0){
			return members.get(0);
		}
		
		return null;
	}

	@Override
	public MemberDto getMemberByWechat(String companyCode, String wechart) {
		// TODO Auto-generated method stub
		return null;
	}

	private String generateInsertSQL(String companyCode) {
		return String.format(SQL_CREATE_TABLE,DBUtility.MemberTableName(companyCode));
	}
	
	private String generateSelectAllSQL(String companyCode) {
		return String.format(SQL_SELECT_ALL,DBUtility.MemberTableName(companyCode));
	}

	@Override
	public boolean insert(String companyCode, MemberDto memberDto) {
		return false;
	}

	@Override
	public boolean delete(String companyCode, String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(String companyCode, MemberDto memberDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(String companyCode, List<MemberDto> members) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertByBatch(String companyCode,
			final List<MemberDto> members) {

		String SQL = generateInsertSQL(companyCode);

		try {
			jdbcTemplate.batchUpdate(SQL, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement pps, int index)
						throws SQLException {
					String newId = NamingUtility.generateUniqIDBaseOnDate();
					pps.setString(1, newId);
					pps.setString(2, members.get(index).getWechat());
					pps.setString(3, members.get(index).getTelphone());
					members.get(index).setId(newId);
				}

				@Override
				public int getBatchSize() {
					return members.size();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public void queryIdForMembers(String companyCode, List<MemberDto> memberDtos) {
		String SQL = generateInsertSQL(companyCode);
		jdbcTemplate.query(SQL, new PreparedStatementSetter(){

			@Override
			public void setValues(PreparedStatement arg0) throws SQLException {
				// TODO Auto-generated method stub
				
			}
			
		}, new MemberRowMapper());
	}

	@Override
	public List<MemberDto> getAllMember(String companyCode) {
		String SQL = generateSelectAllSQL(companyCode);
		return jdbcTemplate.query(SQL, new MemberALLRowMapper());
		
	}

}
