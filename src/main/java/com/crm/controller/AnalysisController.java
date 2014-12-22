package com.crm.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
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
		
		return "SDFX";
	}
	
	@RequestMapping(value = { "/UpdateCompanyStatus" })
	public void updateCompanyStatus() {
		String companyCode = getCompanyCode();
		analysisService.insertCompanyStatus(companyCode);
	}
}