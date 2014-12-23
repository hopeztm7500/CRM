package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.crm.dto.RawTransactionRecordDto;

public class RawDataReader {
	
	public static Map<String, List<RawTransactionRecordDto>> readXLS(String filePath) throws IOException, InvalidFormatException, ParseException 
    {
        //Blank workbook
    	
    	Map<String, List<RawTransactionRecordDto>> values = new HashMap<String, List<RawTransactionRecordDto>>();
    	
    	FileInputStream inputStream = new FileInputStream(new File(filePath));
    	 
        Workbook workbook = WorkbookFactory.create(inputStream);

        int nSheet = workbook.getNumberOfSheets();
        for(int i = 0; i < nSheet; i++){
        	 //Get first/desired sheet from the workbook
            Sheet sheet = workbook.getSheetAt(i);
            List<RawTransactionRecordDto> myValues = new ArrayList<RawTransactionRecordDto>();
            
            String sheetName = sheet.getSheetName();
            
       
            
            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            if(rowIterator.hasNext()){
            	rowIterator.next();//skip the header row
            }
            else{
            	continue;//there is now data in this sheet
            }
            
            
            while (rowIterator.hasNext()) 
            {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                String companyCode = row.getCell(0).getStringCellValue();
                String departCode = row.getCell(1).getStringCellValue();
                String date = row.getCell(2).getStringCellValue();
                String time = row.getCell(3).getStringCellValue();
                String wechat = row.getCell(4).getStringCellValue();
                String telphone = String.valueOf(row.getCell(5).getNumericCellValue());
                String orderId = row.getCell(6).getStringCellValue();
                String productId = row.getCell(7).getStringCellValue();
                
                int count = (int) row.getCell(8).getNumericCellValue();
                double total = row.getCell(9).getNumericCellValue();
                Date date2 = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").parse(date + " " + time);
                
                RawTransactionRecordDto value = new RawTransactionRecordDto(companyCode, departCode, date2, 
                		wechat, telphone, orderId, productId, count, total);
                
                myValues.add(value);
            }
            values.put(sheetName, myValues);
        }
       
        inputStream.close();
        
        return values;
    }
}
