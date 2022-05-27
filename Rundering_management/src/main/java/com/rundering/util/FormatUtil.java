package com.rundering.util;

public class FormatUtil {
	
	//전화번호에 하이픈 붙이기
	public static String hyphenationPhoneNum(String phoneNoStr) {
		String regEx = "(\\d{3})(\\d{3,4})(\\d{4})";
	   return phoneNoStr.replaceAll(regEx, "$1-$2-$3");


	}
	
	//특수문자 제거
	public static String RemoveSpecialChar(String src) {
		return src.replaceAll("[^0-9]", "");
	}

}