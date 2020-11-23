package kr.or.coder.frame.ria.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nexacro17.xapi.data.datatype.DataType;

/**
 * ria 처리결과 dataset
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
public class RiaRstDataset {

	private Map<String, DataType> metaDataMap;
	
	private List<RiaRstMap<String, Object>> riaRstMapList;

	public RiaRstDataset() {
		
		metaDataMap = new HashMap<String, DataType>();
	}
	
	public void put(String colNm, DataType colType) {

		metaDataMap.put(colNm, colType);
	}
	
	public DataType get(String colNm) {
		
		return metaDataMap.get(colNm);
	}

	public Map<String, DataType> getMetaDataMap() {
		
		return metaDataMap;
	}
	
	public void setRiaRstMapList(List<RiaRstMap<String, Object>> riaRstMapList) {
		this.riaRstMapList = riaRstMapList;
	}
	
	public List<RiaRstMap<String, Object>> getRiaRstMapList() {
		
		return riaRstMapList;
	}

	public class RiaRstMap<K, V> extends HashMap<K, V> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 3870050127659965066L;
		
	}
}