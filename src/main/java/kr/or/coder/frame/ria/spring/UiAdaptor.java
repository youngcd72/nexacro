package kr.or.coder.frame.ria.spring;

import javax.servlet.http.HttpServletRequest;

public interface UiAdaptor {

	public Object convert(HttpServletRequest request) throws Exception;

}
