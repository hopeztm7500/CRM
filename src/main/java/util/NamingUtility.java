package util;

import java.util.Date;
import java.util.Random;

public class NamingUtility {

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
