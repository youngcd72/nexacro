package kr.or.coder.frame.ria.data;

import java.util.HashMap;
import java.util.Map;

public class RiaParameterMap<K, V>  extends HashMap<K, V> {

	private static final long serialVersionUID = 1L;

	private Map<String, DatasetMap> datasetMaps;
	
	public void setDatasetMap(String dsName, DatasetMap datasetMap) {

		this.datasetMaps.put(dsName, datasetMap);
	}
	
	public DatasetMap getDatasetMap(String dsName) {
		
		return datasetMaps.get(dsName);
	}
}
