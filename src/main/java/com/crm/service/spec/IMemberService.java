package com.crm.service.spec;

import java.util.List;

import com.crm.dto.MemberDto;

public interface IMemberService {
	public MemberDto getMemberById(String companyCode, int id);
	public MemberDto getMemberByWechat(String companyCode, String wechart);
	
	public boolean insert(String companyCode, MemberDto memberDto);
	public boolean delete(String companyCode, int id);
	public boolean update(String companyCode, MemberDto memberDto);
	public boolean insertByBatch(String companyCode, List<MemberDto> members);
}
