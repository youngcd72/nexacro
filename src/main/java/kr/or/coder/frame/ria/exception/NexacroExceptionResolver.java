package kr.or.coder.frame.ria.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import com.nexacro.uiadapter17.spring.core.NexacroException;
import com.nexacro.uiadapter17.spring.core.servlet.NexacroInterceptor;
import com.nexacro.uiadapter17.spring.core.util.NexacroUtil;
import com.nexacro.uiadapter17.spring.core.view.NexacroModelAndView;
import com.nexacro.uiadapter17.spring.core.view.NexacroView;

/**
 * nexacro platform �슂泥��뿉 ���븳 �삁�쇅 諛쒖깮 �떆 泥섎━�릺�뒗 {@link HandlerExceptionResolver} �씠�떎.
 * <p>{@link NexacroInterceptor}�� 留ㅽ븨 �맂 �삁�쇅留뚯쓣 泥섎━�븳�떎.
 *
 * @author Park SeongMin
 * @since 08.03.2015
 * @version 1.0
 * @see NexacroException
 * 
 */
public class NexacroExceptionResolver extends AbstractHandlerExceptionResolver {

	private final Logger logger = LoggerFactory.getLogger(NexacroExceptionResolver.class);

	private MessageSource messageSource;
	
	private String defaultErrorMsg = NexacroException.DEFAULT_MESSAGE;
	private boolean shouldSendStackTrace = false;
	private boolean shouldLogStackTrace = false;

    private View view;

    public View getView() {
        if(view == null) {
            return new NexacroView();
        } else {
            return view;
        }
    }

    /**
     * �삁�쇅 諛쒖깮 �떆 泥섎━�릺�뒗 {@link org.springframework.web.servlet.View}瑜� 諛섑솚�븳�떎.
     * 
     * @param view
     */
    public void setView(View view) {
        this.view = view;
    }
    
    /**
     * �삁�쇅 諛쒖깮 �떆 �쓳�떟�쑝濡� �쟾�넚�릺�뒗 湲곕낯 �뿉�윭硫붿떆吏��씠�떎.
     * 
     * @param defaultErrorMsg
     * @see #setShouldSendStackTrace(boolean)
     */
    public void setDefaultErrorMsg(String defaultErrorMsg) {
		this.defaultErrorMsg = defaultErrorMsg;
	}

    /**
     * �쓳�떟�쑝濡� �삁�쇅�쓽 硫붿꽭吏� �젙蹂대�� �쟾�넚�븷吏��뿉 ���븳 �꽕�젙�씠�떎.
     * <p>�꽕�젙 �맂 媛믪씠 <code>false</code>�씪 寃쎌슦 �삁�쇅 �젙蹂대뒗 �쓳�떟�쑝濡� �쟾�떖�릺吏� �븡�뒗�떎. 
     * <p>�븯吏�留� {@link NexacroException#setErrorMsg(String)} �뿉�윭硫붿떆吏�媛� �꽕�젙 �맂 寃쎌슦 �빐�떦 硫붿떆吏�媛� �쟾�넚�맂�떎. 洹몃젃吏� �븡�쓣 寃쎌슦 {@link #setDefaultErrorMsg(String)}濡� �꽕�젙 �맂 媛믪씠 �쓳�떟�쑝濡� �쟾�넚�맂�떎.
     * 
     * @param shouldSendStackTrace
     * @see #setDefaultErrorMsg(String)
     */
	public void setShouldSendStackTrace(boolean shouldSendStackTrace) {
		this.shouldSendStackTrace = shouldSendStackTrace;
	}
	
	/**
	 * �삁�쇅 �젙蹂대�� 濡쒓퉭�븷吏��뿉 ���븳 �꽕�젙�씠�떎. 
	 * @param shouldLogStackTrace
	 */
	public void setShouldLogStackTrace(boolean shouldLogStackTrace) {
		this.shouldLogStackTrace = shouldLogStackTrace;
	}

	/**
	 * {@link MessageSource}瑜� �꽕�젙�븳�떎.
	 * 
	 * @param messageSource
	 */
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	/**
	 * �뿉�윭 諛쒖깮 �떆 硫붿떆吏� 泥섎━.
	 */
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
	    
		prepareResolveException(request, response, handler, ex);
		
		// nexacro �슂泥��씠 �븘�땶 寃쎌슦 蹂꾨룄 ExceptionResolver 媛� 泥섎━ �븷 �닔 �엳�룄濡� null�쓣 諛섑솚 �븳�떎.
	    if(NexacroUtil.isNexacroRequest(request)) {
            
            // Nexacro Exception 留뚯쓣 handling �븯�룄濡� �븳�떎.
            // for nexacro request
            NexacroModelAndView mav = new NexacroModelAndView(getView());
            
            if(ex instanceof NexacroException){ // NexacroConvertException
                NexacroException nexaExp = (NexacroException) ex;
                mav.setErrorCode(nexaExp.getErrorCode());
                mav.setErrorMsg(getExceptionSendMessage(ex));
            } else {
                // PlatformException..
                mav.setErrorCode(NexacroException.DEFAULT_ERROR_CODE);              //Undefined error Code
//                mav.setErrorMsg(NexacroException.DEFAULT_MESSAGE);         
                mav.setErrorMsg(getExceptionSendMessage(ex));         
            }
            
            return mav;
	    }
        
	    return null;
	}

	private void prepareResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
	    
		// shouldLogStackTrace 媛믪쑝濡� 泥섎━. 
        // logException(ex, request);

        if(this.shouldLogStackTrace) {
        	String exceptionMessage = getExceptionLogMessage(ex);
        	logger.error(exceptionMessage, ex);
        }
        
	}

	private String getExceptionLogMessage(Exception e) {
		
		String exceptionMessage = getLocalizedMessage(e.getMessage());
		
		if(e instanceof NexacroException) {
			String userErrorMsg = getLocalizedMessage(((NexacroException) e).getErrorMsg());
			exceptionMessage = "errorMsg="+ userErrorMsg +", stackMessage=" +exceptionMessage;
		}
		
		return exceptionMessage;
	}
	
	private String getExceptionSendMessage(Exception e) {
		
		String userErrorMsg = null;
		if(e instanceof NexacroException) {
			userErrorMsg = getLocalizedMessage(((NexacroException) e).getErrorMsg());
		}
		
		String exceptionMessage = null;
		if(this.shouldSendStackTrace) {
			
			// �샊�� cause message... 
			// �샊�� �궗�슜�옄媛� �삁�쇅瑜� �빖�뱾留� �븯湲� �븣臾몄뿉 �궗�슜�옄媛� �젙�쓽�븳 硫붿꽭吏�瑜� �뜕吏��룄濡� �븷源�..?
			exceptionMessage = getLocalizedMessage(e.getMessage());
			if(userErrorMsg != null) {
				exceptionMessage = "errorMsg="+ userErrorMsg +", stackMessage=" +exceptionMessage;
			}
			
		} else {
			if(userErrorMsg != null) {
				exceptionMessage = userErrorMsg;
			} else {
				String localizedMessage = getLocalizedMessage(this.defaultErrorMsg);
				exceptionMessage = localizedMessage;
			}
		}
		
		return exceptionMessage;
	}
	
	private String getLocalizedMessage(String reason) {
		if(reason == null) {
			return null;
		}
		if (this.messageSource != null) {
			reason = this.messageSource.getMessage(reason, null,reason, LocaleContextHolder.getLocale());
		}
		return reason;
	}
}