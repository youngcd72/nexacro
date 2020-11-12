package kr.or.coder.frame.ria.spring;

import javax.servlet.http.HttpServletRequest;

import kr.or.coder.frame.ria.miplatform.MipUiAdaptor;
import kr.or.coder.frame.ria.util.RiaRequestUtil;
import kr.or.coder.frame.ria.xplatform.XpfUiAdaptor;

public class UiAdaptorFactory {

    public static UiAdaptor getUiAdaptor(HttpServletRequest request) {

        String userAgent    = request.getHeader("User-Agent");     // miplatform / xplatform  요청 구분

	    if(RiaRequestUtil.isMiplatformRequest(userAgent)) {
	        return new MipUiAdaptor();
	    } else if(RiaRequestUtil.isXplatformRequest(userAgent)) {
	        return new XpfUiAdaptor();
	    }
	    return new UiAdaptorImpl();
	}
}
