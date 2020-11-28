package kr.or.coder.frame.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import kr.or.coder.frame.dao.BaseDAO;

public class PermissionInterceptor  implements HandlerInterceptor {

	@Resource(name = "baseDAO")
	private BaseDAO baseDAO;

	/**
	 * Controller 진입 전처리
	 * 
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @param Object handler
	 * @return boolean
	 * @throws 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

		// 로그인 여부 체크

		return true;
	}
	
	/**
	 * Controller 완료 후처리
	 * 
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @param Object handler
	 * @return boolean
	 * @throws 
	 */		
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// 로그인 여부 체크
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)	throws Exception {
		// TODO Auto-generated method stub

	}
}
