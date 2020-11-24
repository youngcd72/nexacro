package kr.or.coder.nexacro.cmmn.service;

import java.util.List;
import java.util.Map;

public interface CodeService {

	public List<Map<String, Object>> getCodeList(String grpCode) throws Exception;
}
