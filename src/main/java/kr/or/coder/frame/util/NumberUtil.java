package kr.or.coder.frame.util;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

public class NumberUtil {

	
	/**
	 * <p>입역받은 값을 int값으로 변환 
	 *     -. 값이 null이거나 숫자가 아닌경우 0으로 반환</p>
	 * 
	 * @param  Object
	 * @return int
	 */
	public static int toInt(Object obj) {
		
		if(obj == null) { 
			return 0;
		}

		if(obj instanceof BigDecimal) {

			return ((BigDecimal)obj).intValue();
		}
		return toInt(obj.toString());
	}
	
	public static int toInt(String str) {

		if(StringUtils.isEmpty(str)) {

			return 0;
		}
		
		try {

			return Integer.valueOf(str);

		} catch(Exception e) {

			return 0;
		}		
	}
	
	/**
	 * <p>입역받은 값을 long값으로 변환 
	 *     -. 값이 null이거나 숫자가 아닌경우 0으로 반환</p>
	 * 
	 * @param  Obejct
	 * @return long
	 */
	public static long toLong (Object obj) {
		
		if(obj == null) { 
			return 0L;
		}

		if(obj instanceof BigDecimal) {

			return ((BigDecimal)obj).longValue();
		}

		try {

			return Long.valueOf(obj.toString());

		} catch(Exception e) {

			return 0L;
		}
	}

	public static long toLong (String str) {
		
		if(StringUtil.isEmpty(str)) {
			return 0L;
		}
		
		try {

			return Long.valueOf(str);

		} catch(Exception e) {

			return 0L;
		}
	}
	
	/**
	 * <p>입역받은 값을 float값으로 변환 
	 *     -. 값이 null이거나 숫자가 아닌경우 0으로 반환</p>
	 * 
	 * @param  Obejct
	 * @return float
	 */
	public static float toFloat (Object obj) {
		
		if(obj == null) { 
			return 0.0F;
		}

		if(obj instanceof BigDecimal) {

			return ((BigDecimal)obj).floatValue();
		}

		try {

			return Float.valueOf(obj.toString());

		} catch(Exception e) {

			return 0.0F;
		}
	}
	
	public static float toFloat (String str) {
		
		if(StringUtil.isEmpty(str)) {
			return 0;
		}
		
		try {

			return Float.valueOf(str);

		} catch(Exception e) {

			return 0;
		}
	}
	
	/**
	 * <p>입역받은 값을 double값으로 변환 
	 *     -. 값이 null이거나 숫자가 아닌경우 0으로 반환</p>
	 * 
	 * @param  Obejct
	 * @return double
	 */
	public static double toDouble (Object obj) {
		
		if(obj == null) { 
			return 0.0D;
		}

		if(obj instanceof BigDecimal) {

			return ((BigDecimal)obj).doubleValue();
		}

		try {

			return Double.valueOf(obj.toString());

		} catch(Exception e) {

			return 0.0D;
		}
	}

	public static double toDouble (String str) {
		
		if(StringUtil.isEmpty(str)) {
			return 0;
		}

		try {

			return Double.valueOf(str);

		} catch(Exception e) {

			return 0.0D;
		}
	}
}