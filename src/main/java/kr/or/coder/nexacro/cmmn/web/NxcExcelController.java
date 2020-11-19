package kr.or.coder.nexacro.cmmn.web;

import java.io.IOException;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexacro17.xapi.data.PlatformData;
import com.nexacro17.xapi.data.VariableList;
import com.nexacro17.xapi.tx.HttpPlatformResponse;
import com.nexacro17.xapi.tx.PlatformException;

import kr.or.coder.frame.message.CommonMessageSource;
import kr.or.coder.frame.ria.util.NxcExcelUtil;

/**
 * nexacro excel import / export 컨트롤러 클래스
 * @author 
 * @since 2020.11.03
 * @version 1.0
 * 
 * <pre>
 * 수정일        수정자       수정내용
 * ----------  --------    ---------------------------
 * 
 * </pre>
 * 
 */
@Controller
public class NxcExcelController {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Resource(name="commonMessageSource")
	CommonMessageSource commonMessageSource;
	
	@Resource(name = "NxcExcelUtil")
	private NxcExcelUtil nxcExcelUtil;

	/**
	 * Nexacro grid excel export
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/common/exportGridExcel.do")
	public void exportGridToExcel (HttpServletRequest request, HttpServletResponse response) {
		
		int result = 0;
		String resultMsg = "";
		
		try {

			result = nxcExcelUtil.gridExport(request, response);

		} catch (PlatformException ex) {

			result = -3101;
			resultMsg = ex.getMessage();

			logger.error(ex.getMessage(), ex);

		} catch (IllegalArgumentException ex) {
			
			result = -3201;
			resultMsg = ex.getMessage();

			logger.error(ex.getMessage(), ex);
			
		} catch (IndexOutOfBoundsException ex) {
			
			result = -3301;
			resultMsg = ex.getMessage();

			logger.error(ex.getMessage(), ex);

		} catch (IllegalStateException ex) {

			result = -3401;
			resultMsg = ex.getMessage();

			logger.error(ex.getMessage(), ex);

		} catch (NullPointerException ex) {
			
			result = -2004;
			resultMsg = ex.getMessage();

			logger.error(ex.getMessage(), ex);

		} catch (IOException ex) {

			result = -2003;
			resultMsg = ex.getMessage();

			logger.error(ex.getMessage(), ex);

		} catch (Exception ex) {
			
			result = -2001;
			resultMsg = ex.getMessage();

			logger.error(ex.getMessage(), ex);

		}
		
		if(result < 0) {
			sendErrorMessage(response, result, commonMessageSource.getMessage("error.nexacro.excel.export", new String[] {String.valueOf(result), resultMsg}));
		}
	}

	/**
	 * Nexacro grid excel import
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/common/importGridExcel.do")
	public void importGridToExcel (HttpServletRequest request, HttpServletResponse response) {

		int result = 0;
		String resultMsg = "";
		
		try {

			result = nxcExcelUtil.gridImport(request, response);

		} catch (PlatformException ex) {

			result = -3101;
			resultMsg = ex.getMessage();

			logger.error(ex.getMessage(), ex);

		} catch (IllegalArgumentException ex) {
			
			result = -3201;
			resultMsg = ex.getMessage();

			logger.error(ex.getMessage(), ex);
			
		} catch (IndexOutOfBoundsException ex) {
			
			result = -3301;
			resultMsg = ex.getMessage();

			logger.error(ex.getMessage(), ex);

		} catch (IllegalStateException ex) {

			result = -3401;
			resultMsg = ex.getMessage();

			logger.error(ex.getMessage(), ex);

		} catch (NullPointerException ex) {
			
			result = -2004;
			resultMsg = ex.getMessage();

			logger.error(ex.getMessage(), ex);

		} catch (IOException ex) {

			result = -2003;
			resultMsg = ex.getMessage();

			logger.error(ex.getMessage(), ex);

		} catch (Exception ex) {
			
			result = -2001;
			resultMsg = ex.getMessage();

			logger.error(ex.getMessage(), ex);

		}
		
		if(result < 0) {
			sendErrorMessage(response, result, commonMessageSource.getMessage("error.nexacro.excel.import", new String[] {String.valueOf(result), resultMsg}));
		}
	}
	
	private void sendErrorMessage(HttpServletResponse response, int errorCode, String errorMsg) {
	
		PlatformData platformData = new PlatformData();
		
		VariableList variableList = platformData.getVariableList();
		variableList.add("ErrorCode", errorCode);
		variableList.add("ErrorMsg", errorMsg);
		
		HttpPlatformResponse httpPlatformResponse = new HttpPlatformResponse(response, "PlatformSsv", "UTF-8");
		httpPlatformResponse.setData(platformData);

		if (logger.isInfoEnabled()) {
			logger.info(errorMsg);
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("RESPONSE DATA XML ============================ \n" + platformData.saveXml());
		}

		try {
		      httpPlatformResponse.sendData();
		} catch (PlatformException platformException) {
			if (logger.isInfoEnabled()) {
				logger.info(platformException.getMessage());
		    }
		} 
	}
}