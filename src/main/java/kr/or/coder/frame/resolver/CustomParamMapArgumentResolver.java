package kr.or.coder.frame.resolver;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

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
public class CustomParamMapArgumentResolver implements HandlerMethodArgumentResolver  {

    private UiAdaptor uiA;
    
    public void setUiAdaptor(UiAdaptor uiA) {
        this.uiA = uiA;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getClass().isAssignableFrom(ParameterMap.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();
        return uiA.convert(request);
    }
}
