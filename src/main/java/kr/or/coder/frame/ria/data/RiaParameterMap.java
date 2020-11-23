package kr.or.coder.frame.ria.data;

import java.util.HashMap;
import java.util.Map;

public class RiaParameterMap<K, V>  extends HashMap<K, V> {

	private static final long serialVersionUID = 1L;

	private Map<String, RiaReqDataset> datasetMaps;
	
	public void setRiaDataset(String dsName, RiaReqDataset riaDataset) {

		this.datasetMaps.put(dsName, riaDataset);
	}
	
	public RiaReqDataset getRiaDataset(String dsName) {
		
		return datasetMaps.get(dsName);
	}
}