package com.crm.dao.spec;


import java.util.List;

import com.crm.dto.MemberDto;

public interface IMemberDao {
	public MemberDto getMemberById(String companyCode, String id);
	public MemberDto getMemberByWechat(String companyCode, String wechart);
	
	public boolean insert(String companyCode, MemberDto memberDto);
	public boolean insert(String companyCode, List<MemberDto> members);
	public boolean delete(String companyCode, String id);
	public boolean update(String companyCode, MemberDto memberDto);
	public boolean insertByBatch(String companyCode, List<MemberDto> members);
	public void queryIdForMembers(String companyCode, List<MemberDto> memberDtos);
	public List<MemberDto> getAllMember(String companyCode);
	
}
