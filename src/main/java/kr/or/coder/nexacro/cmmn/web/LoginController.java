package kr.or.coder.nexacro.cmmn.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.or.coder.frame.data.UserInfo;
import kr.or.coder.frame.ria.data.RiaParameterMap;
import kr.or.coder.frame.ria.nexacro.NxcResult;
import kr.or.coder.frame.service.BaseService;

@Controller
@RequestMapping(value = "/login/")
public class LoginController {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Resource(name = "baseService")
	private BaseService baseService;
	
	@RequestMapping(value = "login.do")
	public ModelAndView login(RiaParameterMap paramMap, HttpServletRequest request) {
		
		NxcResult nxcResult = new NxcResult();

		try {
		
			// 사용자 정보 조회
			UserInfo userInfo = (UserInfo)baseService.selectOne("", paramMap);

			if(userInfo != null) {
			
				// session에 정보를 설정한다.
				HttpSession session = request.getSession();
				session.setAttribute("userInfo", userInfo);

				// menu 정보와 권한 정보 조회
				
			} else {
				
				// Id Password 가 틀
			}

		} catch (Exception ex) {
			
		}
		return nxcResult.getRiaModelAndView();
	}
	
	@RequestMapping(value = "logout.do")
	public ModelAndView logout(RiaParameterMap paramMap, HttpServletRequest request) {

		HttpSession session = request.getSession();
		session.invalidate();

		return new NxcResult().getRiaModelAndView();
	}
}