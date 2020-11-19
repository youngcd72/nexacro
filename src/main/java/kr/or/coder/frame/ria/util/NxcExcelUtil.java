package kr.or.coder.frame.ria.util;

import java.util.Timer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nexacro17.xapi.data.VariableList;
import com.nexacro17.xeni.services.GridExportImportAgent;
import com.nexacro17.xeni.util.XeniProperties;

import egovframework.rte.fdl.property.EgovPropertyService;
import kr.or.coder.frame.message.CommonMessageSource;
import kr.or.coder.frame.ria.extend.XeniExcelFileManager;
 

/**
 * nexacro excel UTIL 관련
 * @author 공통팀
 * @since 2020.11.03
 * @version 1.0
 * 
 * <pre>
 * 수정일       수정자              수정내용
 * ----------  --------    ---------------------------
 * 2020.11.03  	공통팀        		최초 생성
 * </pre>
 */
@Service("NxcExcelUtil")
public class NxcExcelUtil {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/* nexacro excel import / export 기준값 설정 */
	private String absImportPath;  // import excel 임시 경로
	private String absExportPath;  // export excel 경로
	private String exportDownUrl; // 사용자 export 경로
	
	private String downloadUrl;   // export excel download url

	private int runPeriodSec   = 1800;  // 임시파일 삭제 배치 실행 시간
	private int storageTimeSec = 600;   // 임시파일 보관 시간

	private static String numberFmtLang = "ko";

	private static boolean isEnableMonitor = true;

	private static boolean isCsvQuote = true;
	private static boolean isImportTempName = false;
	private static boolean isOutExportPath = false;
	
	@Resource(name="commonMessageSource")
	CommonMessageSource commonMessageSource;
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;

	@PostConstruct
	private void init() {
		
		/* import / export 경로 설정 */
		absImportPath = propertyService.getString("Globals.excel.import.Path");
		absExportPath = propertyService.getString("Globals.excel.export.Path");
		
		exportDownUrl = propertyService.getString("Globals.excel.export.down.url");
		
		/* import excel file upload handler 설정 */
		XeniProperties.setProperty("xeni.multipart.proc", "com.tgcs.common.ria.extend.XeniMultipartHandler");
	}
	
	public int gridImport(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if(isEnableMonitor) {
			executeTimerSchedule();
		}
		
		GridExportImportAgent gridExportImportAgent = new GridExportImportAgent();
		
		VariableList variableList = new VariableList();
	    variableList.add("csv-quote", isCsvQuote);
	    variableList.add("import-temp-name", isImportTempName);

		int result = gridExportImportAgent.gridImport(absImportPath, numberFmtLang, isEnableMonitor, request, response, variableList);
		
		if(result < 0) {
			logger.error(commonMessageSource.getMessage("Error.E051", new String[] {String.valueOf(result), ""}));
		}
		return result;
	}
	
	public int gridExport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(isEnableMonitor) {
			executeTimerSchedule();
		}
		
		GridExportImportAgent gridExportImportAgent = new GridExportImportAgent();

		VariableList variableList = new VariableList();
	    variableList.add("csv-quote", isCsvQuote);
	    variableList.add("import-temp-name", isImportTempName);

	    // download url 설정
	    String reqUrl = request.getRequestURL().toString();
	    downloadUrl = reqUrl.replaceAll(request.getRequestURI(), "") + exportDownUrl;
	    
		int result = gridExportImportAgent.gridExport(absExportPath, downloadUrl, isOutExportPath, request, response, variableList);

		if(result < 0) {
			logger.error(commonMessageSource.getMessage("Error.E051", new String[] {String.valueOf(result), gridExportImportAgent.getErrorMessage()}));
		}
		return result;
	}

	private void executeTimerSchedule() {
		
		XeniExcelFileManager xeniExcelFileManager = XeniExcelFileManager.getInstance();
		Timer timer = xeniExcelFileManager.getTimer();

		if (timer == null) {

			timer = xeniExcelFileManager.newTimerInstance();
			xeniExcelFileManager.setServiceDir(absExportPath, absImportPath, isEnableMonitor);
			xeniExcelFileManager.setStorageTime(storageTimeSec);
			timer.scheduleAtFixedRate(xeniExcelFileManager, (runPeriodSec * 1000), (runPeriodSec * 1000));
		} 
	}
}
