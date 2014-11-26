package com.crm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crm.dao.spec.IMemberDao;
import com.crm.dto.MemberDto;

@Repository
public class MemberDaoImpl implements IMemberDao{

	private static String SQL_CREATE_TABLE = "";
	
	@Override
	public MemberDto getMemberById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberDto getMemberByWechat(String wechart) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(MemberDto memberDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(MemberDto memberDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(List<MemberDto> members) {
		// TODO Auto-generated method stub
		return false;
	}

}
