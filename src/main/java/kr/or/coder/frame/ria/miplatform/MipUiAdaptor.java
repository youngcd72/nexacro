package kr.or.coder.frame.ria.miplatform;

import javax.servlet.http.HttpServletRequest;

import kr.or.coder.frame.ria.data.RiaParameterMap;
import kr.or.coder.frame.ria.spring.UiAdaptor;

/**
 * Nexacro UiAdaptor interface
 *  
 * @author youngcd
 * @since 2020.11.02
 * @version 1.0
 * 
 * <pre>
 *  수정일자    수정자              수정내용
 * ----------  --------    ---------------------------
 * 2020.11.02  	youngcd        	    최초작성
 * </pre>
 * 
 */
public class MipUiAdaptor implements UiAdaptor {

	public Object convert(HttpServletRequest request) throws Exception {
		
	    RiaParameterMap<String, Object> paramMap = new RiaParameterMap<String, Object>();

        /* variable 처리 */

        /* argument 처리 */

        /* dataset 처리 */

        return paramMap;
	}
}