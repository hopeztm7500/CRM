package com.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.dto.MemberDetailData;
import com.crm.dto.MemberDto;
import com.crm.service.spec.IMemberService;

@Controller
@RequestMapping("/")
public class MemberController {


	@Autowired
	private IMemberService memberService;
	
	public String getCompanyCode(){
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		return "SDFX";
	}
	
	@RequestMapping(value="memberDetail")
	public String getCustomerById(@RequestParam String id, ModelMap model) {
		
		
		MemberDto member = memberService.getMemberById(getCompanyCode(), id);
		model.put("member", member);
		
		return "member-detail";
	}
	
	@RequestMapping(value = "/member-detail", method = RequestMethod.POST)
	public @ResponseBody MemberDetailData getMemberDetailInfo(){
		String companyCode = getCompanyCode();
		MemberDetailData memberDetail = new MemberDetailData(); 
		
		return memberDetail;
		
	}
	
	
	@RequestMapping(value = "/members", method = RequestMethod.POST)
	public @ResponseBody List<MemberDto> getMembers(){
		String companyCode = getCompanyCode();
		return memberService.getAllMember(companyCode);
	}
	
}
