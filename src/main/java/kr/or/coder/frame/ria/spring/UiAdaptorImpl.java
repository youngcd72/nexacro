package kr.or.coder.frame.ria.spring;

import javax.servlet.http.HttpServletRequest;

import kr.or.coder.frame.ria.data.RiaParameterMap;

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
public interface UiAdaptor {

	public RiaParameterMap<String, Object> convert(HttpServletRequest request) throws Exception;

}