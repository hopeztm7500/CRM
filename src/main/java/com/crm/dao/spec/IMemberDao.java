package com.crm.dao.spec;


import java.util.List;

import com.crm.dto.MemberDto;

public interface IMemberDao {
	public MemberDto getMemberById(int id);
	public MemberDto getMemberByWechat(String wechart);
	
	public boolean insert(MemberDto memberDto);
	public boolean insert(List<MemberDto> members);
	public boolean delete(int id);
	public boolean update(MemberDto memberDto);
	
}
