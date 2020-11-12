package kr.or.coder.frame.ria.spring;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import kr.or.coder.frame.ria.data.RiaParameterMap;


/**
 * Spring ArgumentResolver 클래스
 *  
 * @author youngcd
 * @since 2020.11.02
 * @version 1.0
 * 
 * <pre>
 * 수정일       수정자              수정내용
 * ----------  --------    ---------------------------
 * 2020.11.02  	youngcd        	    최초 생성
 * </pre>
 * 
 */
public class RiaArgumentResolver implements WebArgumentResolver {

	private UiAdaptor uiA;

	public void setUiAdaptor(UiAdaptor uiA) {
		this.uiA = uiA;
	}

	/**
	 * Controller의 Parameter를 읽어와 분기한다.
	 * 
	 * @param MethodParameter methodParameter
	 * @param NativeWebRequest webRequest
	 * @return Object
	 * @throws Exception
	 */	  	
	public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) throws Exception {
		Class<?> type = methodParameter.getParameterType();
		Object uiObject = null;
		
		if (uiA == null)
			return UNRESOLVED;
		
		
		/*
		 * Controller 에서 처리해야 할 Argument 에 따른  Adapter 구분 방법을 확인해야 함.
		 */
		if (type.equals(RiaParameterMap.class)) {
			
			HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
			uiObject = (Object) uiA.convert(request);
			
			return uiObject;
		}
		
		return UNRESOLVED;
	}

}
