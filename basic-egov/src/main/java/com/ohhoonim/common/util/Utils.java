package com.ohhoonim.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;
public class Utils {
	private static final Logger LOGGER = Logger.getLogger(Utils.class);
	
	// 빈 값을 받을 때 null 을 "" 로 변환
	public static String toEmptyBlank(String nullStr){
		return (nullStr == null || nullStr.equals("undefined")) ? "" : nullStr;	
	}
	public static Object toEmptyBlank(Object nullStr){
		return nullStr == null ? "" : nullStr;	
	}
	
	public static String customToEmptyBlank(String nullStr, String modifiedStr){
		return nullStr == null ? modifiedStr : nullStr;	
	}
	
	public static String webBlankToCustomize(String inputStr, String modifedStr){
		return inputStr == "" ? modifedStr : inputStr;
	}
	
	
	// html환경에서 null일때 스페이스로
	public static String nullSpace(String nullSpa) {
	      return nullSpa == null ? "&nbsp;" : nullSpa;
	   }
	
	// date 형식으로 변환 후 원하는 포맷으로 변경
	public static String dateFommatter(Object dateStr){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String modifyDate ="";
		String setDateStr = dateStr != null ? dateStr.toString(): null;
		
		try {
			if (setDateStr == null || setDateStr == ""){
				setDateStr = date.toString();
			}
			
			LOGGER.debug("=======================================================");
			LOGGER.debug("입력받은 날짜 : " + setDateStr);
			LOGGER.debug("=======================================================");
			
			date = sdf.parse(setDateStr);
			LOGGER.debug("=======================================================");
			LOGGER.debug("변환된 날짜 : " + date);
			LOGGER.debug("=======================================================");
			modifyDate= sdf2.format(date);
			
			LOGGER.debug("=======================================================");
			LOGGER.debug("수정된 날짜 : " + modifyDate);
			LOGGER.debug("=======================================================");
			
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return modifyDate;
	}
	
	//String date 를 받아서 10자리만 커트
	public static String customDate(String dateStr){
		String customDate = dateStr.substring(0, 10);
		return customDate;
	}
	
	//숫자를 원하는 포맷으로 변경
	public static String customNum(String strNum, String format){
		int intNum = Integer.parseInt(strNum);
		DecimalFormat df = new DecimalFormat(format);
		String customNum = df.format(intNum);
		
		return customNum;
	}
	
	// 입력받은 문자열이 숫자 형식인지 체크
	public static boolean chkInputOnlyNumber(String input){
		char chrInput = 0;
		boolean result = false;
		
		if (input == ""){
			result = true;
		}else{
			int inputLen = input.length();
			for (int i = 0; i < inputLen; i++ ){
				chrInput = input.charAt(i);
				if(chrInput >= 0x30 && chrInput <= 0x39){
					result = true;
				}else if (chrInput == 32){
					result = true;
				}else if (chrInput == 46){
					result = true;
				}else{
					result = false;
					break;
				}
				
			}
		}
		
		
		
		return result;
	}
	
	// 입력받은 문자열이 영문인지 체크
	public static boolean chkInputOnlyAlphabet(String input){
		char chrInput = 0;
		boolean result = false;
		
		int inputLen = input.length();
		for (int i = 0; i < inputLen; i++ ){
			chrInput = input.charAt(i);
			if(chrInput >= 0x61 && chrInput <= 0x7A){
				result = true;
			}else if(chrInput >= 0x41 && chrInput <= 0x5A){
				result = true;
			}else if(chrInput == 32){
				result = true;
			}
			else{
				result = false;
				break;
			}
		}
		
		return result;
	}
	
	// 입력받은 문자열이 영문소문자인지 체크
	public static boolean chkInputOnlyLowerAlphabet(String input){
		char chrInput = 0;
		boolean result = false;
		
		int inputLen = input.length();
		for (int i = 0; i < inputLen; i++ ){
			chrInput = input.charAt(i);
			if(chrInput >= 0x61 && chrInput <= 0x7A || chrInput == 32){
				result = true;
			}else{
				result = false;
				break;
			}
		}
		
		return result;
	}
	
	// 입력받은 문자열이 영문대문자인지 체크
	public static boolean chkInputOnlyUpperAlphabet(String input){
		char chrInput = 0;
		boolean result = false;
		
		int inputLen = input.length();
		for (int i = 0; i < inputLen; i++ ){
			chrInput = input.charAt(i);
			if(chrInput >= 0x41 && chrInput <= 0x5A || chrInput == 32){
				result = true;
			}else {
				result = false;
				break;
			}
		}
		
		return result;
	}
	
	
	// 입력받은 문자열이 한글인지 체크
	public static boolean chkInputOnlyKorean(String input){
		char chrInput = 0;
		boolean result = false;
		
		int inputLen = input.length();
		for (int i = 0; i < inputLen; i++ ){
			chrInput = input.charAt(i);
			if(chrInput >= 44032 && chrInput <= 55203 || chrInput == 32){
				result = true;
			}else{
				result = false;
				break;
			}
			
		}
		
		return result;
	}
	
	public static boolean checkParamIsEmpty(Map<String, String> params, String[] keys) {
		boolean result = false;
		
		for(String key: keys) {
			String value = params.get(key);
			if(value == null || value.equals("")) {
				result = true;
				break;
			}
		}
				
		return result;
	}
	public static <T extends Object> T mappingReqparamToVo(Map<String, String> req, Class<T> voClazz) throws Exception {
		T objVo = voClazz.newInstance();
		
		Field[] fields = voClazz.getDeclaredFields();
		
		for(Field field: fields) {
			String fieldName = field.getName();
			String methodName = "set" + 
								fieldName.substring(0,1).toUpperCase() + 
								fieldName.substring(1, fieldName.length());
			String val = req.get(field.getName());
			if(val != null && val.length() > 0) {
				Class fieldType = field.getType();
				Method setter = voClazz.getMethod(methodName, fieldType);
				if (fieldType.equals(java.sql.Date.class)) {
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date indate = sf.parse(val);
					setter.invoke(objVo, new java.sql.Date(indate.getTime()));
				} else {
					setter.invoke(objVo, val);
				}
			}
		}
		
		return objVo;
	}
	public static String fileNameWithTimestamp(String fileName) {
		long currentTime = System.nanoTime();
		return fileName + "_" + currentTime;
	}
	
}











