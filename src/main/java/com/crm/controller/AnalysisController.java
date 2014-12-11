package com.crm.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;


import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import util.NamingUtility;
import util.RawDataReader;

import com.crm.dto.MemberDto;
import com.crm.dto.RawDataRecordDto;
import com.crm.dto.TransactionDto;
import com.crm.dto.CompanyStatusDto;
import com.crm.service.spec.ICompanyService;
import com.crm.service.spec.IMemberService;
import com.crm.service.spec.ITransationService;
import com.crm.service.spec.IAnalysisService;




@Controller
@RequestMapping("/")
public class AnalysisController {

	
	@Autowired
	private ICompanyService companyService;
	
	@Autowired
	private IMemberService memberService;
	
	@Autowired
	private ITransationService transationService;
	
	@Autowired
	private IAnalysisService analysisService;
	
	
	public boolean checkAndAddAuth(ModelMap model){
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!auth.getPrincipal().equals("anonymousUser")) {
			model.addAttribute("authenticated", true);
			
			return true;
		}
		else{
			return false;
		}
	}
	
	public String getCompanyCode(){
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		return "SDFX";
	}
	
	@RequestMapping(value = { "/UpdateCompanyStatus" })
	public void updateCompanyStatus() {
		String companyCode = getCompanyCode();
		analysisService.insertCompanyStatus(companyCode);
	}
}