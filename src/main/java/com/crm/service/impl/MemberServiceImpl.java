package com.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.spec.IMemberDao;
import com.crm.dto.MemberDto;
import com.crm.service.spec.IMemberService;

@Service
public class MemberServiceImpl implements IMemberService {

	@Autowired IMemberDao memberDao;
	
	@Override
	public MemberDto getMemberById(String companyCode, int id) {
		return memberDao.getMemberById(companyCode, id);
	}

	@Override
	public MemberDto getMemberByWechat(String companyCode, String wechart) {
	    return memberDao.getMemberByWechat(companyCode, wechart);
	}

	@Override
	public boolean insert(String companyCode, MemberDto memberDto) {
		return memberDao.insert(companyCode, memberDto);
	}

	@Override
	public boolean delete(String companyCode, int id) {
		return memberDao.delete(companyCode, id);
	}

	@Override
	public boolean update(String companyCode, MemberDto memberDto) {
		return memberDao.update(companyCode, memberDto);
	}

	@Override
	public boolean insertByBatch(String companyCode, List<MemberDto> members) {
		return memberDao.insertByBatch(companyCode, members);
	}

	@Override
	public void queryIdForMembers(String companyCode, List<MemberDto> memberDtos) {
		memberDao.queryIdForMembers(companyCode, memberDtos);
	}

}
