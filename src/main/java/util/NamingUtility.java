package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class NamingUtility {

	public static String generateUniqIDBaseOnDate(){
		String dateString = new SimpleDateFormat("yyyyMMddhhMMss").format(new Date());
		return String.format("%s%03d%05d", dateString, new Date().getTime() % 1000, Math.abs(new Random().nextInt()) % 100000);
	}
	public static String getUniqueName(){
		Long base = new Date().getTime();
		Long random = new Random().nextLong();
		
		return base.toString() + random.toString();
	}
	
	public static String generateNewNameFor(String fileName){
		String ext = fileName.substring(fileName.lastIndexOf('.'));
		return getUniqueName() + "." + ext;
	}
}
