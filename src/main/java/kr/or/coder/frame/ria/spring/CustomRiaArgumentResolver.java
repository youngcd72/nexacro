package kr.or.coder.frame.ria.spring;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import kr.or.coder.frame.ria.data.RiaParameterMap;


/**
 * Spring Ria ArgumentResolver 
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
public class CustomRiaArgumentResolver implements WebArgumentResolver {

    public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) throws Exception {

        Class<?> type = methodParameter.getParameterType();
        UiAdaptor uiA = null; 

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        if(type.equals(RiaParameterMap.class)) {
            uiA = UiAdaptorFactory.getUiAdaptor(request);
        } else {
            uiA = new UiAdaptorImpl();
        }
		return uiA.convert(request);
	}
}
