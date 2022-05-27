package com.rundering.util;

import java.util.UUID;


public class MakeFileName {

	public static String toUUIDFileName(String fileName, String delimiter) {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		return uuid + delimiter + fileName;
	}

	public static String parseFileNameFromUUID(String fileName, String delimiter) {
		// split 함수에서 구분자를 정규표현식으로 받기 때문에 예약어로 사용한 특수문자는 에러가 발생합니다.
		// 에러가 발생하지 않고 특수문자 기준으로 문자열을 자르고 싶다면 \\를 특수문자 앞에 넣으면 됩니다.
		String[] uuidFileName = fileName.split(delimiter);
		return uuidFileName[uuidFileName.length - 1];
	}
}
