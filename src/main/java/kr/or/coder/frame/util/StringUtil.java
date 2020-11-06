package kr.or.coder.frame.util;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {

	/**
	 * <p>입력값이 null 일 경우 "" 를 반환한다.</p>
	 * 
	 * @param s
	 */
	public static String null2void(String str) {
		return (isEmpty(str)) ? "" : str;
	}

	/**
	 * <p>입력값이 null 이거나 "", whitespace일 경우 true를 반환한다.</p>
	 * 
	 * @param  string 
	 * @return boolean
	 */
	public static boolean isEmpty(String str) {
		return (str == null || str.trim().length() == 0);
	}

	/**
	 * <p>입력값의 선행공백값을 제거한다.</p>
	 * 
	 * @param  string 
	 * @return boolean
	 */
	public static String ltrim(String str) {
		
		if(str == null) {
			return "";
		}
		
		return str.replaceAll("\\s+", "");
	}
	
	/**
	 * <p>입력값의 후행공백값을 제거한다.</p>
	 * 
	 * @param  string 
	 * @return string
	 */
	public static String rtrim(String str) {

		if(isEmpty(str)) {
			return "";
		}
		return str.replaceAll("\\s{Z}+$", "");
	}
	
	/**
	 * <p>입력값이 LEN만큼 크지 않으면 그 부족분 만큼 선행문자에 padChar를 추가한다.</p>
	 * 
	 * @param  string 
	 * @return string
	 */
	public static String lpad(String str, int len, char padChar) {
		return StringUtils.leftPad(str, len, padChar);
	}
	
	/**
	 * <p>입력값이 LEN만큼 크지 않으면 그 부족분 만큼 후행문자에 padChar를 추가한다.</p>
	 * 
	 * @param  string 
	 * @return string
	 */
	public static String rpad(String str, int len, char padChar) {
		return StringUtils.rightPad(str, len, padChar);
	}

	/**
	 * <p>입력값이 빈값은 경우 입력받은 default value를 되돌려 준다.</p>
	 * 
	 * @param  string 
	 * @return string
	 */
	public static String nvl(String str, String defValue) {
		return isEmpty(str)?defValue:str;
	}
}