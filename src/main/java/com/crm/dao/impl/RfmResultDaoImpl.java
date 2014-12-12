package com.crm.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import util.DBUtility;

import com.crm.dao.spec.IRfmResultDao;
import com.crm.dto.RFMResultDto;

@Repository
public class RfmResultDaoImpl implements IRfmResultDao {
	
	
	private static String SQL_QUERY = "select RFM.id, RFM.r_score, RFM.f_score, RFM.m_score, RFM.rfm_score, RFM.recency, RFM.frequency, "
			+ "RFM.monetary, "
			+ "RFM.rfm_category, RFM.share_of_wallet, RFM.update_date, DES.description from %s RFM, %s DES where DES.category = RFM.rfm_category";
	
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
			int rfmCategory = rs.getInt("rfm_category");
			
			double shareOfWallet = rs.getDouble("share_of_wallet");
			long updateDate = rs.getLong("update_date");
			String rfmCategoryName = rs.getString("description");
			
			return new RFMResultDto(id, rScore, fScore, mScore, rfmScore, recency, frequency, monetary, rfmCategory, shareOfWallet, rfmCategoryName, new Date(updateDate));
		
		}
		
	}
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<RFMResultDto> getAllRfmResult(String companyCode) {
		
		String SQL = String.format(SQL_QUERY, DBUtility.MemberRFMTableName(companyCode), DBUtility.MemberRFMCategoryName(companyCode));
		return jdbcTemplate.query(SQL, new RfmMapper());
	}

}
