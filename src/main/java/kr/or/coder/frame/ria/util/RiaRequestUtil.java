package kr.or.coder.frame.ria.util;

import javax.servlet.http.HttpServletRequest;

import kr.or.coder.frame.ria.nexacro.NexacroConstant;
import kr.or.coder.frame.util.StringUtil;

/**
 * Request scope utils - Controller 이전 request 객체 요청관리
 *  
 * @author youngcd
 * @since 2020.11.02
 * @version 1.0
 * 
 * <pre>
 *   수정일자           수정자                     수정내용
 * ----------  --------    ---------------------------
 * 2020.11.02      youngcd           최초작성
 * </pre>
 * 
 */
public class RiaRequestUtil {

    public static boolean isRiaRequest(HttpServletRequest request) {

        if(isNexacroRequest(request) || isXplatformRequest(request)) {
            return true;
        }
        return false;
    }
    
    public static boolean isNexacroRequest(HttpServletRequest request) {

        String userAgent = request.getHeader("SubUserAgent");
        
        if(StringUtil.isEmpty(userAgent)) {
            userAgent = request.getHeader("User-Agent");
        }
        
        if(!StringUtil.isEmpty(userAgent) && userAgent.toLowerCase().startsWith(NexacroConstant.USER_AGENT.XPLATFORM)) {
            return true;
        }
        return false; 
    }

    public static boolean isXplatformRequest(HttpServletRequest request) {

        
        String userAgent = request.getHeader("User-Agent");
        
        if(!StringUtil.isEmpty(userAgent) && userAgent.toLowerCase().startsWith(NexacroConstant.USER_AGENT.NEXACRO)) {
            return true;
        }
        return false;
    }
}