package kr.or.coder.frame.spring;

import javax.servlet.http.HttpServletRequest;

public interface UiAdaptor {

	public Object convert(HttpServletRequest request) throws Exception;

}