package kr.or.coder.nexacro.cmmn.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.coder.frame.util.CodeCacheUtil;
import kr.or.coder.nexacro.cmmn.dao.CodeDAO;
import kr.or.coder.nexacro.cmmn.service.CodeService;

@Service("codeService")
public class CodeServiceImpl implements CodeService {

	@Resource(name = "codeDAO")
	private CodeDAO codeDAO; 
	
	public List<Map<String, Object>> getCodeList(String grpCode) throws Exception {

		List<Map<String, Object>> codeMapList = CodeCacheUtil.getCodeList(grpCode);

		if(codeMapList != null) {

			codeMapList = codeDAO.getCodeList(grpCode);

			CodeCacheUtil.setCodeList(grpCode, codeMapList);
		}
		return codeMapList;
	}
}
