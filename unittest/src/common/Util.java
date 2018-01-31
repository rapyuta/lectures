package common;

import exception.DateNumberFormatException;

public class Util {
	public static String emptyBlank(String str) {
		return str == null ? "" : str.toString();
	}
	
	public static String emptyBlank(int num) {
		return num == 0 ? "" : String.valueOf(num);
	}
	
	public static String dateFormat(String createDate) throws DateNumberFormatException{
		String viewDate = "";
		
		if (createDate == null) {
			viewDate = "1970-01-01";
		} else {
			if (createDate.length() != 8) {
				throw new DateNumberFormatException("8자리가 아닙니다.");
			}
			String yyyy = createDate.substring(0, 4);
			String mm = createDate.substring(4, 6);
			String dd = createDate.substring(6, 8);
			
			viewDate = yyyy + "-" + mm + "-" + dd;
		}
		return viewDate;
	}
	
	
}
