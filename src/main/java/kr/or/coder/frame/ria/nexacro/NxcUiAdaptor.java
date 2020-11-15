package kr.or.coder.frame.ria.nexacro;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nexacro17.xapi.data.ColumnHeader;
import com.nexacro17.xapi.data.DataSet;
import com.nexacro17.xapi.data.DataSetList;
import com.nexacro17.xapi.data.DataTypes;
import com.nexacro17.xapi.data.Debugger;
import com.nexacro17.xapi.data.PlatformData;
import com.nexacro17.xapi.data.Variable;
import com.nexacro17.xapi.data.VariableList;
import com.nexacro17.xapi.tx.HttpPlatformRequest;
import com.nexacro17.xapi.tx.PlatformException;
import com.nexacro17.xapi.tx.PlatformType;

import kr.or.coder.frame.ria.data.DatasetMap;
import kr.or.coder.frame.ria.data.RiaParameterMap;
import kr.or.coder.frame.ria.spring.UiAdaptor;
import kr.or.coder.frame.ria.util.RiaRequestUtil;

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

        /* variable 처리 */
        VariableList variableList = platformData.getVariableList();

        for(int i = 0; i < variableList.size(); i++) {

            Variable var = variableList.get(i);
            paramMap.put(var.getName(), var.getObject());
        }

        /* dataset 처리 */
        Map<String, Object> insDsMap;
        Map<String, Object> uptDsMap;
        Map<String, Object> delDsMap;

        DataSetList dsList = platformData.getDataSetList();

        for(int i = 0; i < dsList.size(); i++) {
            
            DataSet ds = dsList.get(i);
            DatasetMap dsMap = new DatasetMap(ds.getName());

            insDsMap = new HashMap<String, Object>();
            uptDsMap = new HashMap<String, Object>();
            delDsMap = new HashMap<String, Object>();

            /* dataset insert/update 설정 */
            for(int j = 0; j < ds.getRowCount(); j++) {
                
                for(int k = 0; k < ds.getColumnCount(); k++) {
                    
                    if(DataSet.ROW_TYPE_INSERTED == ds.getRowType(j)) {
                        insDsMap.put(ds.getColumn(k).getName(), ds.getObject(j, k));
                        dsMap.addInsDsMap(insDsMap);
                    } else if(DataSet.ROW_TYPE_UPDATED == ds.getRowType(j)) {
                        uptDsMap.put(ds.getColumn(k).getName(), ds.getObject(j, k));
                        dsMap.addUptDsMap(uptDsMap);
                    }
                }
            }
            
            /* dataset delete 설정 */
            for(int j = 0; j < ds.getRemovedRowCount(); j++) {

                for(int k = 0; k < ds.getColumnCount(); k++) {
                    delDsMap.put(ds.getColumn(j).getName(), ds.getRemovedData(j, k));
                }
                dsMap.addDelDsMap(delDsMap);
            }
            
            paramMap.setDatasetMap(ds.getName(), dsMap);
        }

        return paramMap;
	}
}