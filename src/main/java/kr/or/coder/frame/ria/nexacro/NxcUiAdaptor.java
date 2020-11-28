package kr.or.coder.frame.ria.nexacro;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nexacro17.xapi.data.DataSet;
import com.nexacro17.xapi.data.DataSetList;
import com.nexacro17.xapi.data.Debugger;
import com.nexacro17.xapi.data.PlatformData;
import com.nexacro17.xapi.tx.HttpPlatformRequest;
import com.nexacro17.xapi.tx.PlatformType;

import kr.or.coder.frame.ria.data.ConvertRiaData;
import kr.or.coder.frame.ria.data.RiaParameterMap;
import kr.or.coder.frame.spring.UiAdaptor;


/**
 * Nexacro UiAdaptor interface
 *  
 * @author youngcd
 * @since 2020.11.02
 * @version 1.0
 * 
 * <pre>
 *  수정일자    수정자              수정내용
 * ----------  --------    ---------------------------
 * 2020.11.02  	youngcd        	    최초작성
 * </pre>
 * 
 */
public class NxcUiAdaptor implements UiAdaptor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public Object convert(HttpServletRequest request) throws Exception {

        HttpPlatformRequest platformRequest = null; 
	    
        try {

            platformRequest = new HttpPlatformRequest(request.getInputStream(), PlatformType.CONTENT_TYPE_BINARY, PlatformType.DEFAULT_CHAR_SET);
            platformRequest.receiveData();
        } catch(Exception ex) {
            
            logger.error(ex.getMessage(), ex);
        }

        PlatformData platformData = platformRequest.getData();
        if(logger.isDebugEnabled()) {
            logger.debug("request = [{}]", new Debugger().detail(platformData));
        }

        RiaParameterMap<String, Object> paramMap = new RiaParameterMap<String, Object>();

        ConvertRiaData.convertVariableToMap(platformData.getVariableList(), paramMap);
        
        /* dataset 처리 */
        DataSetList dsList = platformData.getDataSetList();

        for(int i = 0; i < dsList.size(); i++) {
            
            DataSet ds = dsList.get(i);
            
            paramMap.setRiaDataset(ds.getName(), ConvertRiaData.convertDatasetToRiaDataset(ds));
        }

        return paramMap;
	}
}