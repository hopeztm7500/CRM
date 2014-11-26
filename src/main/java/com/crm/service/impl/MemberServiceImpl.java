package com.crm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.spec.IMemberDao;
import com.crm.dto.MemberDto;
import com.crm.service.spec.IMemberService;

@Service
public class MemberServiceImpl implements IMemberService {

	@Autowired IMemberDao memberDao;
	
	@Override
	public MemberDto getMemberById(int id) {
		return memberDao.getMemberById(id);
	}

	@Override
	public MemberDto getMemberByWechat(String wechart) {
	    return memberDao.getMemberByWechat(wechart);
	}

	@Override
	public boolean insert(MemberDto memberDto) {
		return memberDao.insert(memberDto);
	}

	@Override
	public boolean delete(int id) {
		return memberDao.delete(id);
	}

	@Override
	public boolean update(MemberDto memberDto) {
		return memberDao.update(memberDto);
	}

}
