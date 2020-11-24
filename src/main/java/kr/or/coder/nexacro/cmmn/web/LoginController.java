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
import kr.or.coder.nexacro.cmmn.service.LoginService;

@Controller
@RequestMapping(value = "/login/")
public class LoginController {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Resource(name = "loginService")
	private LoginService loginService;
	
	@RequestMapping(value = "login.do")
	public ModelAndView login(RiaParameterMap paramMap, HttpServletRequest request) {
		
		// session 정보로 로그인 체크
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");

		if(userInfo != null) {

			
		} else {

			// 로그인 처리
		}
		
		NxcResult nxcResult = new NxcResult();
		
		return nxcResult.getRiaModelAndView();
	}
}