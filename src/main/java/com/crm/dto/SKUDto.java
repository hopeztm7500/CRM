package com.crm.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the class defined for goods sell in the store
 * @author hopeztm
 *
 */
public class SKUDto {

	private String skuCode;
	private String skuName;
	
	public SKUDto(String skuCode, String skuName){
		this.skuCode = skuCode;
		this.skuName = skuName;
	}
	
	public String getSkuCode() {
		return skuCode;
	}
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}
	public String getSkuName() {
		return skuName;
	}
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}
	
	
	public static List<SKUDto> GenerateTestData(){
		List<SKUDto> dtos = new ArrayList<SKUDto>();
		dtos.add(new SKUDto("A001", "面包"));
		dtos.add(new SKUDto("B001", "香肠"));
		dtos.add(new SKUDto("C001", "咖啡"));
		dtos.add(new SKUDto("D001", "钱包"));
		dtos.add(new SKUDto("E001", "手机"));
		dtos.add(new SKUDto("F001", "面巾纸"));
		dtos.add(new SKUDto("G001", "花生米"));
		dtos.add(new SKUDto("H001", "小鱼"));
		dtos.add(new SKUDto("I001", "小鸡"));
		dtos.add(new SKUDto("J001", "小猪"));
		dtos.add(new SKUDto("K001", "小狗"));
		dtos.add(new SKUDto("L001", "小猫"));
		dtos.add(new SKUDto("M001", "小鸭"));
		dtos.add(new SKUDto("N001", "小鹅"));
		dtos.add(new SKUDto("O001", "豆豆1"));
		dtos.add(new SKUDto("P001", "豆豆2"));
		dtos.add(new SKUDto("Q001", "豆豆3"));
		dtos.add(new SKUDto("R001", "豆豆4"));
		dtos.add(new SKUDto("S001", "小猴"));
		dtos.add(new SKUDto("T001", "小王子棒棒糖"));
		dtos.add(new SKUDto("U001", "大枣"));
		dtos.add(new SKUDto("V001", "花生米"));
		dtos.add(new SKUDto("W001", "粉丝"));
		dtos.add(new SKUDto("X001", "豆干"));
		dtos.add(new SKUDto("Y001", "巴豆"));
		dtos.add(new SKUDto("Z001", "小蚕豆"));
		return dtos;
	}
	
}
