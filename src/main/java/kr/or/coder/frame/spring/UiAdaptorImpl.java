package kr.or.coder.frame.spring;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.coder.frame.data.ParameterMap;



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

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	
    public Object convert(HttpServletRequest request) throws Exception {
        
        ParameterMap<String, Object> paramMap = new ParameterMap<String, Object>();

        /* request get / post parameter 설정 */
        Map<String, String[]> reqParamMap = request.getParameterMap();
        Set<String> keySet = reqParamMap.keySet();

        for(String key : keySet) {
        	
            paramMap.put(key, reqParamMap.get(key));
        }
        
        /* session, cookie User 정보설정 */
        

        return paramMap;

    }
}