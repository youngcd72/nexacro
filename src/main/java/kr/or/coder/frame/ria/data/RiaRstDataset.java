package kr.or.coder.frame.ria.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public Map<String, Object> metaDataMap;
	
	public List<RiaRstMap<String, Object>> riaRstMapList;

	public RiaRstDataset() {
		
		metaDataMap = new HashMap<String, Object>();
	}
	
	public void put(String colNm, String colType) {

		metaDataMap.put(colNm, colType);
	}
	
	public void setRiaRstMapList(List<RiaRstMap<String, Object>> riaRstMapList) {
		this.riaRstMapList = riaRstMapList;
	}

	public class RiaRstMap<K, V> extends HashMap<K, V> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 3870050127659965066L;
		
	}
}