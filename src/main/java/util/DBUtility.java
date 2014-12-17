package util;

public class DBUtility {
	
	 
    private static String SQL_FOREIGN_KEY = "CONSTRAINT %s FOREIGN KEY (%s) REFERENCES %s (%s) ON DELETE CASCADE";
	public static String GenerateForeignKeyPart(String myTable, String myColumn, String targetTable, String targetColumn){
		return String.format(DBUtility.SQL_FOREIGN_KEY, myTable+"_forkey", myColumn, targetTable, targetColumn);
	}
	
	public static String companySkuTableName(String code){
		return code + "_SKUS";
	}
	public static String CompanyStoresTableName(String code){
		return code + "_STORES";
	}
	public static String MemberTableName(String code){
		return code + "_MEMBERS";
	}
	public static String TransTableName(String code){
		return code + "_TRANS";
	}

	public static String CompanyStatusTableName(String code){
		return code + "_A_COMPANY_STATUS";
	}
	
	public static String MemberRFMTableName(String code){
		return code + "_A_MEMBERS_RFM";
	}
	public static String MemberRFMCategoryName(String code){
		return code + "_A_RFM_CATEGORY";
	}
	
	public static String GenerateSQLOnCompany(String companyCode, String baseSQL){
		return String.format(baseSQL, companyCode);
	}
}
