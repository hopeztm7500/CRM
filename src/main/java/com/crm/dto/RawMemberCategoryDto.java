package com.crm.dto;

import java.util.ArrayList;
import java.util.List;

public class RawMemberCategoryDto {

	private int id;
	private String memberId;
	
	public RawMemberCategoryDto(int id, String memberId){
		this.id = id;
		this.memberId = memberId;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	public static List<RawMemberCategoryDto> getTestData(){
		List<RawMemberCategoryDto> memberCategoryDtos = new ArrayList<RawMemberCategoryDto>();
		memberCategoryDtos.add(new RawMemberCategoryDto(1, "2014120211123182591323"));
		memberCategoryDtos.add(new RawMemberCategoryDto(1, "2014120211123182600124"));
		memberCategoryDtos.add(new RawMemberCategoryDto(1, "2014120211123182727450"));
		memberCategoryDtos.add(new RawMemberCategoryDto(1, "2014120211123182755472"));
		memberCategoryDtos.add(new RawMemberCategoryDto(1, "2014120211123182784624"));
		memberCategoryDtos.add(new RawMemberCategoryDto(1, "2014120211123182796327"));
		memberCategoryDtos.add(new RawMemberCategoryDto(2, "2014120211123182591323"));
		memberCategoryDtos.add(new RawMemberCategoryDto(2, "2014120211123182803771"));
		memberCategoryDtos.add(new RawMemberCategoryDto(2, "2014120211123182823275"));
		memberCategoryDtos.add(new RawMemberCategoryDto(2, "2014120211123182831806"));
		memberCategoryDtos.add(new RawMemberCategoryDto(2, "2014120211123182895864"));
		memberCategoryDtos.add(new RawMemberCategoryDto(2, "2014120211123182859539"));
		memberCategoryDtos.add(new RawMemberCategoryDto(2, "2014120211123183042186"));
		memberCategoryDtos.add(new RawMemberCategoryDto(2, "2014120211123183042559"));
		
		
		return memberCategoryDtos;
	}
	
}
