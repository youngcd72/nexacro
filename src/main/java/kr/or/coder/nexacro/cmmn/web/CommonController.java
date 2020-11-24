package kr.or.coder.nexacro.cmmn.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import kr.or.coder.frame.ria.data.RiaParameterMap;
import kr.or.coder.frame.ria.nexacro.NxcResult;
import kr.or.coder.nexacro.cmmn.service.CodeService;

@Controller
public class CommonController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource(name = "codeService")
	private CodeService codeService;
	
	public ModelAndView getCodeList(RiaParameterMap paramMap) throws Exception {

		NxcResult nxcResult = new NxcResult();

		List<Map<String, Object>> codeMapList = codeService.getCodeList("");
		nxcResult.addDataset("", codeMapList);
		
		return nxcResult.getRiaModelAndView();
	}
}
