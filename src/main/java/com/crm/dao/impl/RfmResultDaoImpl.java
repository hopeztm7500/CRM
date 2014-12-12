package com.crm.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import util.DBUtility;

import com.crm.dao.spec.IRfmResultDao;
import com.crm.dto.RFMResultDto;
import com.crm.service.spec.IRfmResultService;

@Repository
public class RfmResultDaoImpl implements IRfmResultDao {
	
	
	private static String SQL_QUERY = "select id, r_score, f_score, m_score, rfm_score, recency, frequency, monetary, "
			+ "rfm_category, share_of_wallet, update_date from %s ";
	
	private static class RfmMapper implements RowMapper<RFMResultDto>{

		@Override
		public RFMResultDto mapRow(ResultSet rs, int arg1)
				throws SQLException {
			
			String id = rs.getString("id");
			double rScore = rs.getDouble("r_score");
			double fScore = rs.getDouble("f_score");
			double mScore = rs.getDouble("m_score");
			double rfmScore = rs.getDouble("rfm_score");
			long recency = rs.getLong("recency");
			int frequency = rs.getInt("frequency");
			double monetary = rs.getDouble("monetary");
			int rfmCategory = rs.getInt("rfmCategory");
			
			return new RFMResultDto(id, rScore, fScore, mScore, rfmScore, recency, frequency, monetary, rfmCategory);
		
		}
		
	}
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<RFMResultDto> getAllRfmResult(String companyCode) {
		
		String SQL = DBUtility.GenerateSQLOnCompany(companyCode, SQL_QUERY);
		return jdbcTemplate.query(SQL, new RfmMapper());
	}

}
