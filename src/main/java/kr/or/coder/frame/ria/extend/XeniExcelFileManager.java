package kr.or.coder.frame.ria.extend;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nexacro17.xeni.data.GridExportData;
import com.nexacro17.xeni.data.GridExportDataFactory;
import com.nexacro17.xeni.extend.XeniMultipartProcBase;

/**
 * <pre>
 * XENI에서 Spring의 MultipartRequest를 처리하기 위한 구현체
 * Spring의 MultipartResolver가 등록 되어 있을 경우에 xeni.properties을 이용하여 등록하여 사용한다.
 *      xeni.multipart.proc=com.nexacro.spring.servlet.XeniMultipartHandler 이름으로 등록 가능하다.
 * </pre>
 *
 * @author Park SeongMin
 * @since 08.24.2015
 * @version 1.0
 * @see XeniMultipartProcBase
 */
public class XeniExcelFileManager extends TimerTask {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	private static XeniExcelFileManager INSTANCE = null;
	private static Timer TIMER = null;

	private String exportPath = null;
	private String importPath = null;

	private int storageTime = 0;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private boolean isFileManage = true;

	public static XeniExcelFileManager getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new XeniExcelFileManager();
		}
		return INSTANCE;
	}

	public Timer newTimerInstance() {

		if (TIMER == null) {
			TIMER = new Timer();
		}
		return TIMER;
	}

	public Timer getTimer() { return TIMER; }

	public void setServiceDir(String exportPath, String importPath, boolean isFileManage) {

	    this.exportPath = exportPath;
	    this.importPath = importPath + '/';
	    this.isFileManage = isFileManage;
	}
	  
	public void setServiceDir(String exportPath, boolean isFileManage) {

		this.exportPath = exportPath;
		this.isFileManage = isFileManage;
	}

	public void setStorageTime(int storageTime) { this.storageTime = storageTime; }

	public void run() {

		if (logger.isInfoEnabled()) {
			logger.info("run file manager [ " + this.sdf.format(Long.valueOf(System.currentTimeMillis())) + " ]");
		}

	    removeUncompletedChunkedData();

		if (this.isFileManage == true) {
			deleteExportFile(); 
		}
	}

	public void deleteExportFile() {

		if (logger.isDebugEnabled()) {
			logger.debug("Find file has been exported.");
		}

		File file = new File(this.exportPath);

	    if (!file.exists()) {

			if (logger.isInfoEnabled()) {
				logger.info("The directory does not exist [ " + this.exportPath + " ]");
			}

			boolean bool = file.mkdir();

			if (bool == true) {

				if (logger.isInfoEnabled()) {
					logger.info("'Export' path has been created.");
				}
			} else {

				if (logger.isWarnEnabled()) {
	  				logger.warn("Fail to creat directory [ " + this.exportPath + " ]");
	  				logger.warn("File Monitor Thread Terminated.");
	  			} 
				getTimer().cancel();
				return;
			} 
		} 
	    deleteFile(file);
	}
	  
	private void deleteFile(File delFile) {

		boolean bool = false;
		File[] files = delFile.listFiles();

		if (files != null) {
			for (File file : files) {

				if (file.isDirectory()) {

					deleteFile(file);

				} else if (!bool) {

					Calendar calendar = Calendar.getInstance();
					Date date1 = calendar.getTime();
					calendar.setTimeInMillis(file.lastModified());
					calendar.add(13, this.storageTime);
					Date date2 = calendar.getTime();

					if (date1.getTime() >= date2.getTime()) {
						if (file.delete() == true) {
							bool = true;
							if (logger.isInfoEnabled()) {
								logger.info("Succeeded in deleting files [ " + file.getName() + " ]");
							}
						} else if (logger.isWarnEnabled()) {
							logger.warn("Failed to delete the file is in using [ " + file.getName() + " ]");
						}  
					}
				} else {
					file.delete();
				} 
			}
		}

		if (bool == true) {
			delFile.delete();
		}
	}
	  
	public void removeUncompletedChunkedData() {

		HashMap hashMap = GridExportDataFactory.getExportDataFactoryInstance().getExportDataFactory();
	    
		if (logger.isDebugEnabled()) {
			logger.debug("Find chunk data uncompleted");
		}

	    Set set = hashMap.keySet();
	    Iterator iterator = set.iterator();

	    while (iterator.hasNext()) {
	    	
			String str = (String)iterator.next();
			GridExportData gridExportData = (GridExportData)hashMap.get(str);

			Calendar calendar = Calendar.getInstance();
			Date date1 = calendar.getTime();

			long l = gridExportData.getLastAccTime();
			calendar.setTimeInMillis(l);

			Date date2 = calendar.getTime();
			calendar.add(13, this.storageTime);
			Date date3 = calendar.getTime();

			if (date1.getTime() >= date3.getTime()) {
				iterator.remove();
				if (logger.isInfoEnabled()) {
					logger.info("Succeeded in deleting uncompleted chunk data [ " + str + " ]");
				}
			}
		}
	}
	
	public void deleteImportFile() {

		if (logger.isDebugEnabled()) {
			logger.debug("Find file has been imported.");
		}

		File file = new File(this.importPath);

		if (!file.exists()) {

			if (logger.isInfoEnabled()) {
				logger.info("The directory does not exist [ " + this.importPath + " ]");
			}

			boolean bool = file.mkdir();

			if (bool == true) {
				if (logger.isInfoEnabled()) {
					logger.info("'Import' path has been created."); 
				} else {
					if (logger.isWarnEnabled()) {
						logger.warn("Fail to creat directory [ " + this.importPath + " ]");
						logger.warn("File Monitor Thread Terminated.");
					} 
					getTimer().cancel();
					return;
				}
			}
			deleteFile(file);
		}
	}
}