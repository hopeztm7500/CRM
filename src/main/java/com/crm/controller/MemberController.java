package com.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import util.ConditionFilter;
import util.ConditionFilterItem;

import com.crm.dto.CategoryDetailDto;
import com.crm.dto.MemberDetailData;
import com.crm.dto.MemberDto;
import com.crm.dto.RFMResultDto;
import com.crm.dto.SKUConDto;
import com.crm.dto.StoreConDto;
import com.crm.service.spec.IMemberService;
import com.crm.service.spec.IRfmResultService;
import com.crm.service.spec.ISkuService;
import com.crm.service.spec.IStoreService;
import com.crm.service.spec.ICategoryService;
import com.crm.service.spec.IMemberCategoryService;


@Controller
@RequestMapping("/")
public class MemberController {


	@Autowired
	private IMemberService memberService;
	
	@Autowired
	private IStoreService storeService;
	
	@Autowired 
	private IRfmResultService rfmResultService;
	
	@Autowired 
	private ISkuService skuService;
	
	@Autowired
	private ICategoryService catoryService;
	
	@Autowired
	private IMemberCategoryService memberCategoryService;

	
	public String getCompanyCode(){
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		return "SDFX";
	}
	
	@RequestMapping(value="/member")
	public String getCustomerById(ModelMap model) {
		return "member-detail";
	}
	
	@RequestMapping(value="/categories")
	public String getCategories(ModelMap model) {
		return "member-categories";
	}
	
	@RequestMapping(value = "/category-detail-info", method = RequestMethod.POST)
	public @ResponseBody List<CategoryDetailDto> getCategoriesDetail(){
		String companyCode = getCompanyCode();
		
		List<CategoryDetailDto> categoryDetailDtos = catoryService.getCategoryServiceDetail(companyCode);
		return categoryDetailDtos;
	}
	
	@RequestMapping(value = "/member-detail-info", method = RequestMethod.POST)
	public @ResponseBody MemberDetailData getMemberDetailInfo(@RequestBody String id){
		String companyCode = getCompanyCode();
		List<StoreConDto> stores =  storeService.getFavirateStores(companyCode, id);
		RFMResultDto myRfm = rfmResultService.getMyRfmResult(companyCode, id);
		MemberDto member = memberService.getMemberById(companyCode, id);
		List<SKUConDto> skus = skuService.getFavoriteSkus(companyCode, id);
		
		
		
		MemberDetailData memberDetail = new MemberDetailData(); 
		
		memberDetail.setRfm(myRfm);
		memberDetail.setBasic(member);
		memberDetail.setFavoriteStores(stores);
		memberDetail.setSkus(skus);
		
		
		return memberDetail;
		
	}
	
	
	@RequestMapping(value = "/members", method = RequestMethod.POST)
	public @ResponseBody List<MemberDto> getMembers(@RequestBody(required=false) ConditionFilter conditions){
		String companyCode = getCompanyCode();
		return memberService.getAllMember(companyCode, conditions);
	}
	
	
	
	
	
}
