package kr.or.coder.frame.ria.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Spring UiAdaptor interface
 *  
 * @author youngcd
 * @since 2020.11.02
 * @version 1.0
 * 
 * <pre>
 * 수정일     	수정자              수정내용
 * ----------  --------    ---------------------------
 * 2020.11.02  	youngcd        	    최초 생성
 * </pre>
 * 
 */
public class RiaRequestUtil {

	/*
	 * Request 유형분류
	 */
	public static String USER_AGENT_NEXACRO   = "_NEXACRO";
	public static String USER_AGENT_XPLATFORM = "_XPLATFORM";
	public static String USER_AGENT_NORMAL    = "_NORMAL";

	/* 
	 * url-pattern 정의
	 *  
	 * xplatform : *.xpf
	 * nexacro   : *.nxc
	 * normal    : *.do
	 */
	public static String REQUEST_URL_PATTERN_NEXACRO   = "nxc";
	public static String REQUEST_URL_PATTERN_XPLATFORM = "xpf";
	public static String REQUEST_URL_PATTERN_NORMAL    = "do";
	
	public boolean isNexacroRequest(HttpServletRequest request) {
		
		if(REQUEST_URL_PATTERN_NEXACRO.equals(getUrlPattern(request)) || USER_AGENT_NEXACRO.equals(checkUserAgent(request))) {
			return true;
		}
		return false;
	}

	public boolean isXplatformRequest(HttpServletRequest request) {

		if(REQUEST_URL_PATTERN_XPLATFORM.equals(getUrlPattern(request)) || USER_AGENT_XPLATFORM.equals(checkUserAgent(request))) {
			return true;
		}
		return false;		
	}

	
	public String getUrlPattern(HttpServletRequest request) {
		
		String reqUrl = request.getRequestURL().toString();
		
		int idx = reqUrl.lastIndexOf(".");
		
		if(idx >= 0) { 
			return reqUrl.substring(idx);
		}
		return "";
	}

	private String checkUserAgent(HttpServletRequest request) {

		String userAgent = request.getHeader("User-Agent");
		
		if(userAgent.toLowerCase().startsWith("xplatform")) {
			return USER_AGENT_XPLATFORM;
		} else if(userAgent.toLowerCase().startsWith("nexacro")) {
			return USER_AGENT_NEXACRO;
		} else {
			return USER_AGENT_NORMAL; 
		}
	}
}