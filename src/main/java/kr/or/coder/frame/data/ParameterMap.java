package kr.or.coder.frame.data;

import java.util.HashMap;

import kr.or.coder.frame.util.NumberUtil;

/**
 * request 요청 parameter 값을 Map 으로 변환
 *  
 * @author youngcd
 * @since 2020.11.02
 * @version 1.0
 * 
 * <pre>
 *  수정일자    수정자             수정내용
 * ----------  --------    ---------------------------
 * 2020.11.02  	youngcd        	   최초작성
 * </pre>
 * 
 */
public class ParameterMap<K, V> extends HashMap<K, V> {

    /**
     * 
     */
    private static final long serialVersionUID = -3850619441195411470L;

    /**
     * Object 형식을 int형식으로 반환
     * 
     * @param key
     * @return int
     */
    public int getInt(String key) {

        return NumberUtil.toInt(get(key));
    }

    /**
     * Object 형식을 long형식으로 반환
     * 
     * @param key
     * @return long
     */
    public long getLong(String key) {
        
        return NumberUtil.toLong(get(key));
    }

    /**
     * Object 형식을 long형식으로 반환
     * 
     * @param key
     * @return float
     */
    public float getFloat(String key) {
        
        return NumberUtil.toFloat(get(key));
    }

    /**
     * Object 형식을 long형식으로 반환
     * 
     * @param key
     * @return double
     */
    public double getDouble(String key) {
        
        return NumberUtil.toDouble(get(key));
    }

    /**
     * Object 형식을 long형식으로 반환
     * 
     * @param key
     * @return String
     */
    public String getString(String key) {
        
        Object val = get(key);
        
        if(val == null) {
            return "";
        }
        return String.valueOf(get(key));
    }
}