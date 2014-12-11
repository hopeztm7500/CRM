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
public class MainController {

	
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
	

	
	
	
	@RequestMapping(value="memberDetail")
	public String getCustomerById(@RequestParam String id, ModelMap model) {
		
		
		MemberDto member = memberService.getMemberById(getCompanyCode(), id);
		model.put("member", member);
		
		return "member-detail";
	}
	
	
	
	@RequestMapping(value = { "/", "index" })
	public String index(ModelMap model) {
		checkAndAddAuth(model);
	
		return "index";
	}
	 
	@RequestMapping(value = { "/dashboard"})
	public String dashboard(ModelMap model) {
		checkAndAddAuth(model);
	
		return "dashboard";
	}

	
	
	@RequestMapping(value = { "/transaction-data"})
	public String transactionData(ModelMap model) {
		checkAndAddAuth(model);
	
		return "transaction-data";
	}

	
	
	@RequestMapping(value = { "/helloworld"})
	public String helloworld(ModelMap model) {
		checkAndAddAuth(model);
	
		return "customer-data";
	}
	

	@RequestMapping(value = { "/customer-data"})
	public String customerData(ModelMap model) {
		checkAndAddAuth(model);
	
		return "customer-data";
	}
	
	@RequestMapping(value = "/members", method = RequestMethod.POST)
	public @ResponseBody List<MemberDto> getMembers(){
		String companyCode = getCompanyCode();
		
		return memberService.getAllMember(companyCode);

	}
	
	@RequestMapping(value = "/transactions", method = RequestMethod.POST)
	public @ResponseBody List<TransactionDto> getTransactions(){
		String companyCode = getCompanyCode();
		
		return transationService.getAllTransaction(companyCode);

	}
	
	
	
	
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody String uploadFileHandler(@RequestParam("name") String name, @RequestParam("file") MultipartFile file, ModelMap model) {
	 

		
	        if (!file.isEmpty()) {
	            try {
	                byte[] bytes = file.getBytes();
	 
	                // Creating the directory to store file
	                String rootPath = System.getProperty("catalina.home");
	                File dir = new File(rootPath + File.separator + "tmpFiles");
	                if (!dir.exists())
	                    dir.mkdirs();
	 
	                String serverfileDir = dir.getAbsolutePath()
	                        + NamingUtility.generateNewNameFor(name);
	                
	                File serverFile = new File(serverfileDir);
	                BufferedOutputStream stream = new BufferedOutputStream(
	                        new FileOutputStream(serverFile));
	                stream.write(bytes);
	                stream.close();
	                Map<String, List<RawDataRecordDto>> rawDatas = null;
	                // Create the file on server
	                if(name.indexOf(".xlsx") > 0 || name.indexOf(".xls") > 0){
	                	rawDatas = RawDataReader.readXLS(serverfileDir);
	                	for(String s: rawDatas.keySet()){
	                		 companyService.createDataTables(s);
	                		 List<RawDataRecordDto> records = rawDatas.get(s);
	                		 List<MemberDto> memberDtos = new ArrayList<>();
	                		 List<TransactionDto> transactionDtos = new ArrayList<>();
	                		 Map<String, MemberDto> memberMap = new TreeMap<>();
	                		 
	                		 for(RawDataRecordDto raw: records){
	                			 if(!memberMap.containsKey(raw.getWechat())){
	                				 MemberDto newDto = new MemberDto(raw.getWechat(), raw.getTelphone());
	                				 memberMap.put(raw.getWechat(), newDto);
	                				 memberDtos.add(newDto);
	                			 }
	                		 }
	                		 
	                		 memberService.insertByBatch(s, memberDtos);
	                		 for(RawDataRecordDto raw: records){	 
	                			 transactionDtos.add(new TransactionDto(memberMap.get(raw.getWechat()).getId(), raw.getDepartCode(), raw.getDate().getTime(), raw.getOrderCode(),
	                					 raw.getGoodsId(), raw.getCount(), raw.getTotal()));
	                		 }
	                		 
	                		 transationService.insertByBatch(s, transactionDtos);
	                	}
	                }
	                else{
	                	return "File is not valid Office Excel file";
	                }

	      
	                
	              
	                return "redirect: update success";
	            } catch (Exception e) {
	                return "You failed to upload " + name + " => " + e.getMessage();
	            }
	        } else {
	            return "You failed to upload " + name
	                    + " because the file was empty.";
	        }
	}

}