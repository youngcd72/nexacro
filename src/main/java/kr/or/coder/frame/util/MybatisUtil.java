package kr.or.coder.frame.util;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

public class MybatisUtil {
	
	/**
	 * <pre>
	 * com.zen9.capelion.fo.module.payment.util
	 * MybatisUtil.java
	 * mybatis 에서 사용 하는 null 처리 
	 * </pre>
	 * @date    : 2019.07.25
	 * @version : 
	 * @author  : dsyeom
	 */
	public static boolean isEmpty(Object obj){
        if( obj instanceof String ) return obj==null || "".equals(obj.toString().trim());
        else if( obj instanceof List ) return obj==null || ((List)obj).isEmpty();
        else if( obj instanceof Map ) return obj==null || ((Map)obj).isEmpty();
        else if( obj instanceof Object[] ) return obj==null || Array.getLength(obj)==0;
        else return obj==null;
    }
     
    public static boolean isNotEmpty(String s){
        return !isEmpty(s);
    }
}