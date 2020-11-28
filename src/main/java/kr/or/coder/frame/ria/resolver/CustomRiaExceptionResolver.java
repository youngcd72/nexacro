package kr.or.coder.frame.ria.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import kr.or.coder.frame.message.CommonMessageSource;
import kr.or.coder.frame.ria.nexacro.NxcResult;
import kr.or.coder.frame.ria.util.RiaRequestUtil;

/**
 * Ria 환경에서 발생한 Exception 처리
 *
 * @author Park SeongMin
 * @since 08.03.2015
 * @version 1.0
 * @see NexacroException
 * 
 */
public class CustomRiaExceptionResolver extends AbstractHandlerExceptionResolver {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private CommonMessageSource messageSource;
	
	public void setMessageSource(CommonMessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	/**
	 * Ria 환경에서 exception 발생시 처리
	 */
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
	    
		// 
	    if(RiaRequestUtil.isRiaRequest(request)) {
            
            NxcResult nxcResult = new NxcResult();
            
            // TO-DO Exception 별 처리 분리 필요
            
            
            nxcResult.setErrorCode(-1);
            nxcResult.setErrorMessage(messageSource.getMessage("error.common.exception"));         
            
            return nxcResult.getRiaModelAndView();
	    }

	    // 일반 환경 - Error 페이지로 이동
	    ModelAndView mav = new ModelAndView("/common/error");
        
	    return mav;
	}
}