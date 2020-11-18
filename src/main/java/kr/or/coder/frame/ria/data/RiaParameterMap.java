package kr.or.coder.frame.ria.data;

import java.util.HashMap;
import java.util.Map;

public class RiaParameterMap<K, V>  extends HashMap<K, V> {

	private static final long serialVersionUID = 1L;

	private Map<String, RiaDataset> datasetMaps;
	
	public void setRiaDataset(String dsName, RiaDataset datasetMap) {

		this.datasetMaps.put(dsName, datasetMap);
	}
	
	public RiaDataset getRiaDataset(String dsName) {
		
		return datasetMaps.get(dsName);
	}
}