package kr.or.coder.frame.ria.util;

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

    /*
     * Ria user-agent 정의
     */
    public static String USER_AGENT_XPLATFORM  = "xplatform";
    public static String USER_AGENT_NEXACRO    = "nexacro";

    public static boolean isNexacroRequest(String userAgent) {
        
        if(!StringUtil.isEmpty(userAgent) && userAgent.toLowerCase().startsWith(USER_AGENT_NEXACRO)) {
            return true;
        }
        return false;
    }

    public static boolean isXplatformRequest(String userAgent) {

        if(!StringUtil.isEmpty(userAgent) && userAgent.toLowerCase().startsWith(USER_AGENT_XPLATFORM)) {
            return true;
        }
        return false;        
    }
}