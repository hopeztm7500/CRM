package com.crm.service.spec;

import com.crm.dto.MemberDto;

public interface IMemberService {
	public MemberDto getMemberById(int id);
	public MemberDto getMemberByWechat(String wechart);
	
	public boolean insert(MemberDto memberDto);
	public boolean delete(int id);
	public boolean update(MemberDto memberDto);
}
