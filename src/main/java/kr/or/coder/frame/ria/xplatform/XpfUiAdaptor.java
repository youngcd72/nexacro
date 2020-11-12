package kr.or.coder.frame.ria.xplatform;

import javax.servlet.http.HttpServletRequest;

import com.tobesoft.xplatform.tx.PlatformRequest;
import com.tobesoft.xplatform.tx.PlatformType;

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
 * 수정일       수정자                수정내용
 * ----------  --------    ---------------------------
 * 2020.11.02  youngcd                최초 생성
 * </pre>
 * 
 */
public class XpfUiAdaptor implements UiAdaptor {

    
    
    public Object convert(HttpServletRequest request) throws Exception {

        PlatformRequest platformRequest = null;
        
        /* variable / arguments 설정 */
        try {

            platformRequest = new PlatformRequest(request.getInputStream(), PlatformType.CONTENT_TYPE_BINARY, PlatformType.DEFAULT_CHAR_SET);
            platformRequest.receiveData();

        } catch(Exception e) {
            

        }

        RiaParameterMap<String, Object> paramMap = new RiaParameterMap<String, Object>();

        /* variable 처리 */

        /* argument 처리 */

        /* dataset 처리 */

        return paramMap;
    }
}