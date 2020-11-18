package kr.or.coder.frame.ria.nexacro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.AbstractView;

import com.nexacro17.xapi.data.DataSet;
import com.nexacro17.xapi.data.DataSetList;
import com.nexacro17.xapi.data.PlatformData;
import com.nexacro17.xapi.data.Variable;
import com.nexacro17.xapi.data.VariableList;
import com.nexacro17.xapi.tx.PlatformResponse;
import com.nexacro17.xapi.tx.PlatformType;

import egovframework.rte.fdl.property.EgovPropertyService;
import kr.or.coder.frame.ria.data.ConvertRiaData;


/**
 * Map 형태의 DTO 들을 Nexacro Data에 맞게 변경하는 View
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
public class NxcView extends AbstractView {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected EgovPropertyService propertyService;
	
	public void setPropertiesService(EgovPropertyService propertiesService) {
		this.propertyService = propertiesService;
	}
	
	/**
	 * 뷰 수행
	 * @param Map map
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return 
	 * @throws Exception
	 */	  		 
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		PlatformData platformData = new PlatformData();
		VariableList outVariableList = new VariableList();
		DataSetList outDatasetList = new DataSetList();

		// 생성된 데이터를 넘기기 위해 변환
		setOutVariableMap(model, outVariableList);
		String sErrorMsg = setOutDataSetMap(model, outDatasetList);
		setResult(model, outVariableList, sErrorMsg);
		
		platformData.setVariableList(outVariableList);
		platformData.setDataSetList(outDatasetList);

		/* contentType 설정 */
		String contentType = propertyService.getString("Globals.nexacro.content.type");
		String userAgent   = request.getHeader("User-Agent");

		PlatformResponse platformResponse = null;
		
		/* XPlatform 형식 처리 */
		if(userAgent.toLowerCase().equals(NexacroConstant.USER_AGENT_XPLATFORM)) {
			platformResponse = new PlatformResponse(response.getOutputStream(), PlatformType.CONTENT_TYPE_BINARY, PlatformType.DEFAULT_CHAR_SET);
		} else {
			platformResponse = new PlatformResponse(response.getOutputStream(), contentType, PlatformType.DEFAULT_CHAR_SET);
		}
        platformResponse.setData(platformData);
        platformResponse.sendData();
	}
}