package kr.or.coder.frame.ria.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ria 처리결과 data
 * 
 * @author 공통팀
 * @since 2020.11.18
 * @version 1.0
 * 
 * <pre>
 * 수정일       수정자              수정내용
 * ----------  --------    ---------------------------
 * 2020.11.18  	공통팀        		최초 생성
 * </pre>
 */
public class RiaResult {

	private int    errCode;
	private String errMsg;
	
	private Map<String, Object> outDsMap;
	private Map<String, Object> outVarMap;

	public RiaResult() {
		
		outDsMap  = new HashMap<String, Object>();
		outVarMap = new HashMap<String, Object>();
	}
	
	public void addDatasetMap(String dsName, Map<String, Object> dsMap) {
		
		List<Map<String, Object>> dsMapList = new ArrayList<Map<String, Object>>();
		dsMapList.add(dsMap);
		
		addDatasetMap(dsName, dsMapList);
	}
	
	public void addDatasetMap(String dsName, List<Map<String, Object>> dsMapList) {

		outDsMap.put(dsName, dsMapList);
	}

	public void addVariableMap(String varName, Object value) {

		outVarMap.put(varName, value);
	}
	
	public void setErrorCode(int errCode) {

		this.errCode = errCode;
	}

	public int getErrorCode() {
		
		return errCode;
	}
	
	public void setErrorMessage(String message) {

		this.errMsg = message;
	}
	
	public String getErrorMessage() {
		
		return errMsg;
	}
}