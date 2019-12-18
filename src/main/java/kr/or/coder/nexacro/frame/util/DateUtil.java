package kr.or.coder.nexacro.frame.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

public class DateUtil {

	public static String DEFAULT_DATE_FORMAT = "yyyyMMdd";
	
	/**
	 * <p>오늘일자를 yyyyMMdd형식의 String으로 반환</p>
	 * 
	 * @param  
	 * @return String
	 */
	public static String today() {
		
		return format(new Date(), DEFAULT_DATE_FORMAT);
	}
	
	/**
	 * <p>입력한 일자를 yyyyMMdd형식의 String으로 반환</p>
	 * 
	 * @param  Date  
	 * @return String
	 */
	public static String format(Date date) {
		
		return format(date, DEFAULT_DATE_FORMAT);
	}
	
	/**
	 * <p>입력한 일자를 입력한 format형식의 String으로 반환</p>
	 * 
	 * @param   Date
	 * @param   format
	 * @return  String
	 */
	public static String format(Date date, String format) {
		
		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * <p>입력한 일자에 입력한 년(年)만큼 더한다.</p>
	 * 
	 * @param  Strinsg
	 * @return String
	 */
	public static String addYears(String sDate, int amount) {
		
		return addYears(sDate, DEFAULT_DATE_FORMAT, amount);
	}
	
	public static String addYears(String sDate, String format, int amount) {
		
		Date date = parseDate(sDate, format);
		
		if(date != null) {
			
			return format(DateUtils.addYears(date, amount));
		}
		return "";
	}

	/**
	 * <p>입력한 일자에 입력한 월(月)만큼 더한다.</p>
	 * 
	 * @param  Strinsg
	 * @return String
	 */
	public static String addMonths(String sDate, int amount) {
		
		return addMonths(sDate, DEFAULT_DATE_FORMAT, amount);
	}
	
	public static String addMonths(String sDate, String format, int amount) {
		
		Date date = parseDate(sDate, format);
		
		if(date != null) {
			
			return format(DateUtils.addMonths(date, amount));
		}
		return "";
	}

	/**
	 * <p>입력한 일자에 입력한 일(日)만큼 더한다.</p>
	 * 
	 * @param  Strinsg
	 * @return String
	 */
	public static String addDays(String sDate, int amount) {
		
		return addDays(sDate, DEFAULT_DATE_FORMAT, amount);
	}
	
	public static String addDays(String sDate, String format, int amount) {
		
		Date date = parseDate(sDate, format);
		
		if(date != null) {
			
			return format(DateUtils.addDays(date, amount));
		}
		return "";
	}

	/**
	 * <p>입력한 srcFormat형식의 String Type의 일자데이터를 tgtFormat형식으로 변경한다.</p>
	 * 
	 * @param  Strinsg
	 * @return String
	 */
	public static String transform(String sDate, String srcFormat, String tgtFormat) {
		
		Date date = parseDate(sDate, srcFormat);

		if(date != null) {
			
			return new SimpleDateFormat(tgtFormat).format(date);
		}
		return "";
	}
	
	/**
	 * <p>입력한 String Type의 일자데이터를 Date Type로 변경한다.</p>
	 * 
	 * @param  Strinsg
	 * @return String
	 */
	public static Date parseDate(String sDate, String format) {
		
		try {

			return DateUtils.parseDate(sDate, format);
			
		} catch(Exception e) {

			return null;
		}
	}
}