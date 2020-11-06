package kr.or.coder.frame.servlet;

import java.util.HashMap;

import kr.or.coder.frame.util.NumberUtil;

public class ParameterMap<K, V> extends HashMap<K, V> {

	private static final long serialVersionUID = 1L;

	public int getInt(String key) {

		return NumberUtil.toInt(get(key));
	}
	
	public long getLong(String key) {
		
		return NumberUtil.toLong(get(key));
	}
	
	public float getFloat(String key) {
		
		return NumberUtil.toFloat(get(key));
	}
	
	public double getDouble(String key) {
		
		return NumberUtil.toDouble(get(key));
	}
	
	public String getString(String key) {
		
		Object val = get(key);
		
		if(val == null) {
			return "";
		}
		return String.valueOf(get(key));
	}
}