package com.crm.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;


import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
@RequestMapping("/")
public class MainController {

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

	@RequestMapping(value = { "/table"})
	public String table(ModelMap model) {
		checkAndAddAuth(model);
	
		return "table";
	}

	@RequestMapping(value = "product-details")
	public String aboutUs(ModelMap model) {
		checkAndAddAuth(model);
		return "product-details";
	}
	

	@RequestMapping(value = "cart")
	public String cart(ModelMap model) {
		checkAndAddAuth(model);
		return "cart";
	}

	@RequestMapping(value = "contact-us")
	public String contactUs(ModelMap model) {
		checkAndAddAuth(model);
		return "contact-us";
	}

}