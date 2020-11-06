package kr.or.coder.frame.ria.xplatform;

import javax.servlet.http.HttpServletRequest;

import kr.or.coder.frame.ria.data.RiaParameterMap;
import kr.or.coder.frame.ria.spring.UiAdaptor;

/**
 * Xplatform UiAdaptor interface
 *  
 * @author youngcd
 * @since 2020.11.02
 * @version 1.0
 * 
 * <pre>
 * 수정일     	수정자    			수정내용
 * ----------  --------    ---------------------------
 * 2020.11.02  	youngcd        		최초 생성
 * </pre>
 * 
 */
public class XpfUiAdaptor implements UiAdaptor {

	public RiaParameterMap<String, Object> convert(HttpServletRequest request) throws Exception {

		RiaParameterMap<String, Object> paramMap = new RiaParameterMap<String, Object>();
		
		return paramMap;
	}
}