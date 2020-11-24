package kr.or.coder.nexacro.cmmn.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.coder.frame.dao.CommonAbstractDAO;

@Repository("codeDAO")
public class CodeDAO extends CommonAbstractDAO {

	public List<Map<String, Object>> getCodeList(String grpCode) {
		
		return selectList("", grpCode);
	}
}
