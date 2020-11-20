package kr.or.coder.frame.ria.nexacro;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.nexacro17.xapi.data.DataSetList;
import com.nexacro17.xapi.data.PlatformData;
import com.nexacro17.xapi.data.VariableList;

import kr.or.coder.frame.ria.data.ConvertRiaData;

/**
 * Nexacro 처리결과
 * 
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
public class NxcResult {
	
	private int    errorCode;
	private String errorMsg;

	private PlatformData platformData;

	public NxcResult() {

		errorCode = 0;
		errorMsg  = "";

		platformData = new PlatformData();
		platformData.setVariableList(new VariableList());
		platformData.setDataSetList(new DataSetList());
	}
	
	public void addDataset(String dsName, Map<String, Object> dsMap) {
		
		List<Map<String, Object>> dsMapList = new ArrayList<Map<String, Object>>();
		dsMapList.add(dsMap);
		
		addDataset(dsName, dsMapList);
	}
	
	public void addDataset(String dsName, List<Map<String, Object>> dsMapList) {

		getOutDataSetList().add(ConvertRiaData.convertMapListToDataset(dsName, dsMapList));
	}

	public void addVariable(String varNm, Object value) {

		getOutVariableList().add(ConvertRiaData.convertObjectToVariable(varNm, value));
	}

	public void setErrorCode(int errorCode) {

		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		
		return errorCode;
	}
	
	public void setErrorMessage(String message) {

		this.errorMsg = message;
	}
	
	public String getErrorMessage() {

		return errorMsg;
	}
	
	public DataSetList getOutDataSetList() {

		return platformData.getDataSetList();
	}
	
	public VariableList getOutVariableList () {

		return platformData.getVariableList();
	}

	public ModelAndView getRiaModelAndView() {

		// error code / error message variable 생성
		getOutVariableList().add(ConvertRiaData.convertObjectToVariable(NexacroConstant.ERROR.CODE, errorCode));
		getOutVariableList().add(ConvertRiaData.convertObjectToVariable(NexacroConstant.ERROR.MESSAGE, errorMsg));

		ModelAndView mav = new ModelAndView();
		mav.addObject(NexacroConstant.OUT_RESULT_DATA, platformData);

		return mav;
	}
}