package kr.or.coder.frame.ria.nexacro;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import com.nexacro17.xapi.tx.PlatformException;

import kr.or.coder.frame.ria.data.RiaParameterMap;
import kr.or.coder.frame.ria.spring.UiAdaptor;
import kr.or.coder.frame.ria.util.RiaRequestUtil;

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
public class NxcUiAdaptor implements UiAdaptor {

	public Object convert(HttpServletRequest request) throws Exception {
		
	    RiaParameterMap<String, Object> paramMap = new RiaParameterMap<String, Object>();

        /* variable 처리 */

        /* argument 처리 */

        /* dataset 처리 */

        return paramMap;

	}
}