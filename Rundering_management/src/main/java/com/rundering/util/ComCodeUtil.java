package com.rundering.util;

import java.util.List;
import java.util.Map;

import com.rundering.dao.ComCodeDAO;
import com.rundering.dto.ComCodeVO;

public class ComCodeUtil {
	public Map<String, String> getCodeMap(String comCodeGrp,Map<String, String> codeMap,ComCodeDAO comCodeDAO) throws Exception {
		List<ComCodeVO> itemOrderComCode = comCodeDAO.selectComCodeByComCodeGrp(comCodeGrp);
		for (ComCodeVO comCode : itemOrderComCode) {
			codeMap.put(comCode.getComCode(), comCode.getComCodeNm());
		}
		return codeMap;
	}
	public Map<String, String> getUpperCodeMap(String upperCode,Map<String, String> codeMap,ComCodeDAO comCodeDAO) throws Exception {
		List<ComCodeVO> itemOrderComCode = comCodeDAO.selectComCodeByUpperComCode(upperCode);
		for (ComCodeVO comCode : itemOrderComCode) {
			codeMap.put(comCode.getComCode(), comCode.getComCodeNm());
		}
		return codeMap;
	}
	public Map<String, Object> getCodeListMap(String comCodeGrp,Map<String, Object> codeMap,ComCodeDAO comCodeDAO) throws Exception {
		List<ComCodeVO> itemOrderComCode = comCodeDAO.selectComCodeByComCodeGrp(comCodeGrp);
		codeMap.put(comCodeGrp+"List",itemOrderComCode);
		return codeMap;
	}
	
}
