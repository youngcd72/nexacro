package kr.or.coder.frame.ria.spring;

import javax.servlet.http.HttpServletRequest;

import kr.or.coder.frame.servlet.ParameterMap;

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
public class UiAdaptorImpl implements UiAdaptor {

    public Object convert(HttpServletRequest request) throws Exception {
        
        ParameterMap<String, Object> paramMap = new ParameterMap<String, Object>();

        /* variable 처리 */

        /* argument 처리 */

        /* dataset 처리 */

        return paramMap;

    }
}