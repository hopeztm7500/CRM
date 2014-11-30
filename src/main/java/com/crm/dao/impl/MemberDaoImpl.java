package com.crm.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import util.DBUtility;

import com.crm.dao.spec.IMemberDao;
import com.crm.dto.MemberDto;

@Repository
public class MemberDaoImpl implements IMemberDao {

	private static String SQL_CREATE_TABLE = "INSERT INTO %s(wechat, telphone) VALUES(?, ?)";
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public MemberDto getMemberById(String companyCode, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberDto getMemberByWechat(String companyCode, String wechart) {
		// TODO Auto-generated method stub
		return null;
	}

	private String generateInsertSQL(String companyCode) {
		return String.format(SQL_CREATE_TABLE,
				DBUtility.MemberTableName(companyCode),
				DBUtility.MemberTableName(companyCode));
	}

	@Override
	public boolean insert(String companyCode, MemberDto memberDto) {
		return false;
	}

	@Override
	public boolean delete(String companyCode, int id) {
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
					pps.setString(1, members.get(index).getWechat());
					pps.setString(2, members.get(index).getTelphone());
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

}
