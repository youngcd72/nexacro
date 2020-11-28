package kr.or.coder.frame.resolver;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import kr.or.coder.frame.data.ParameterMap;
import kr.or.coder.frame.spring.UiAdaptor;

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
public class CustomParamMapArgumentResolver implements WebArgumentResolver {

	private UiAdaptor uiA;
	
	public void setUiAdaptor(UiAdaptor uiA) {
		this.uiA = uiA;
	}
	
    public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) throws Exception {

        Class<?> type = methodParameter.getParameterType();
        
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
         
        if(type.equals(ParameterMap.class)) {

            return uiA.convert(request);
        }
        return UNRESOLVED;
	}
}
