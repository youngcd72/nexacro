package kr.or.coder.nexacro.cmmn.service;

import java.util.List;
import java.util.Map;

public interface LoginService {

	public List<Map<String, Object>> getUserInfo(Map<String, Object> param) throws Exception;
}
