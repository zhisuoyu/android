package utils;

public class CheckUtils {
	
	public static boolean notNull(String...params) {
		for (String param : params) {
			if(param==null) {
				return false;
			}
		}
		return true;
	}

}
