package kr.or.coder.frame.ria.data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.nexacro17.xapi.data.DataSet;
import com.nexacro17.xapi.data.DataTypes;
import com.nexacro17.xapi.data.Variable;
import com.nexacro17.xapi.data.VariableList;

/**
 * ria data 변환
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
public class ConvertRiaData {

	/**
	 * data type을 가져온다.
	 * 
	 * @param Object obj
	 * @return int
	 * @throws 
	 */	  		
	@SuppressWarnings("rawtypes")
	public static int getPlatformDataType(Object obj) {

		int dataType = DataTypes.STRING;

		if (obj== null) {
			return dataType;
		}

		Class clz = obj.getClass();
		String typeName = clz.getName();

		if (typeName.equals(String.class.getName())) {
			dataType = DataTypes.STRING;
		} else if (typeName.equals(Integer.class.getName())) {
			dataType = DataTypes.INT;
		} else if (typeName.equals(Boolean.class.getName())) {
			dataType = DataTypes.INT;
		} else if (typeName.equals(Long.class.getName())) {
			dataType = DataTypes.BIG_DECIMAL;
		} else if (typeName.equals(Double.class.getName())) {
			dataType = DataTypes.FLOAT;
		} else if (typeName.equals(Date.class.getName())) {
			dataType = DataTypes.DATE_TIME;
		} else if (typeName.equals(Byte[].class.getName())) {
			dataType = DataTypes.BLOB;
		}
		return dataType;
	}
	
	/**
	 * Nexacro variable을 map으로 변환
	 * 
	 * @param  VariableList
	 * @return Map
	 * @throws 
	 */
	public static void convertVariableToMap(VariableList variableList, Map<String, Object> map) {
		
        for(int i = 0; i < variableList.size(); i++) {

            Variable var = variableList.get(i);
            map.put(var.getName(), var.getObject());
        }
	}

	/**
	 * Nexacro dataset을 datasetMap으로 변환
	 * 
	 * @param  VariableList
	 * @return Map
	 * @throws 
	 */
	public static RiaDataset convertDatasetToDatasetMap(DataSet ds) {

        RiaDataset dsMap = new RiaDataset(ds.getName());

        Map<String, Object> insDsMap = new HashMap<String, Object>();
        Map<String, Object> uptDsMap = new HashMap<String, Object>();
        Map<String, Object> delDsMap = new HashMap<String, Object>();
        Map<String, Object> readDsMap = new HashMap<String, Object>();

        /* dataset insert/update 설정 */
        for(int j = 0; j < ds.getRowCount(); j++) {
                
            for(int k = 0; k < ds.getColumnCount(); k++) {
                    
                if(DataSet.ROW_TYPE_INSERTED == ds.getRowType(j)) {
                    insDsMap.put(ds.getColumn(k).getName(), ds.getObject(j, k));
                    dsMap.addInsDsMap(insDsMap);
                } else if(DataSet.ROW_TYPE_UPDATED == ds.getRowType(j)) {
                    uptDsMap.put(ds.getColumn(k).getName(), ds.getObject(j, k));
                    dsMap.addUptDsMap(uptDsMap);
                } else {
                	readDsMap.put(ds.getColumn(k).getName(), ds.getObject(j, k));
                	dsMap.addReadDsMap(readDsMap);
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
        return dsMap;
	}
}
