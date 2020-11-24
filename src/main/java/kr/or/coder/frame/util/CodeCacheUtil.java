package kr.or.coder.frame.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeCacheUtil {

	private static Map<String, List<Map<String, Object>>> codeMap = new HashMap<String, List<Map<String, Object>>>();

	public static void setCodeList(String grpCode, List<Map<String, Object>> codeMapList) {
		codeMap.put(grpCode, codeMapList);
	}
	
	public static List<Map<String, Object>> getCodeList(String grpCode) {
		return codeMap.get(grpCode);
	}
	
	public static void clear() throws Exception { 
		codeMap.clear(); 
	}
}