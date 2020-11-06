package kr.or.coder.frame.ria.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DatasetMap {

	private String dsName;
	
	private List<Map<String, Object>> insDsMapList; 
	private List<Map<String, Object>> uptDsMapList;
	private List<Map<String, Object>> delDsMapList;
	private List<Map<String, Object>> readDsMapList;

	public DatasetMap() {}
	
	public DatasetMap(String dsName) {
		this.dsName = dsName;
	}
	
	public void setDsName(String dsName) {
		this.dsName = dsName;
	}
	
	public String getDsName() {
		return dsName;
	}

	public void addInsDsMap(Map<String, Object> insDsMap) {
		
		if(insDsMapList == null) {
			insDsMapList = new ArrayList<Map<String, Object>>();
		}
		
		insDsMapList.add(insDsMap);
	}
	
	public void addInsDsMapList(List<Map<String, Object>> insDsMapList) {
		
		this.insDsMapList = insDsMapList;
	}

	public void addUptDsMap(Map<String, Object> uptDsMap) {
		
		if(uptDsMapList == null) {
			uptDsMapList = new ArrayList<Map<String, Object>>();
		}
		
		uptDsMapList.add(uptDsMap);
	}
	
	public void addUptDsMapList(List<Map<String, Object>> uptDsMapList) {
		
		this.uptDsMapList = uptDsMapList;
	}

	public void addDelDsMap(Map<String, Object> delDsMap) {
		
		if(delDsMapList == null) {
			delDsMapList = new ArrayList<Map<String, Object>>();
		}
		
		delDsMapList.add(delDsMap);
	}
	
	public void addDelDsMapList(List<Map<String, Object>> delDsMapList) {
		
		this.delDsMapList = delDsMapList;
	}
	
	public void addReadDsMap(Map<String, Object> readDsMap) {
		
		if(readDsMapList == null) {
			readDsMapList = new ArrayList<Map<String, Object>>();
		}
		
		delDsMapList.add(readDsMap);
	}
	
	public void addReadDsMapList(List<Map<String, Object>> readDsMapList) {
		
		this.readDsMapList = readDsMapList;
	}	
	
	public List<Map<String, Object>> getDsMap() {
		
		List<Map<String, Object>> dsMapList = new ArrayList<Map<String, Object>>();

		dsMapList.addAll(insDsMapList);
		dsMapList.addAll(uptDsMapList);
		dsMapList.addAll(delDsMapList);
		dsMapList.addAll(readDsMapList);
		
		return dsMapList;
	}
	
	public List<Map<String, Object>> getInsDsMapList() {
		
		return insDsMapList;
	}
	
	public List<Map<String, Object>> getUptDsMapList() {
		
		return uptDsMapList;
	}
	
	public List<Map<String, Object>> getDelDsMapList() {
		
		return delDsMapList;
	}
	
	public List<Map<String, Object>> getReadDsMapList() {

		return readDsMapList;
	}
	
	public int getDsRowCount() {
		
		return getInsDsRowCount() + getUptDsRowCount() + getDelDsRowCount() + getReadDsRowCount();
	}
	
	public int getInsDsRowCount() {
		
		return insDsMapList == null ? 0 : insDsMapList.size();
	}
	
	public int getUptDsRowCount() {
		
		return uptDsMapList == null ? 0 : uptDsMapList.size();
	}
	
	public int getDelDsRowCount() {
		
		return delDsMapList == null ? 0 : delDsMapList.size();
	}
	
	public int getReadDsRowCount() {
		
		return readDsMapList == null ? 0 : readDsMapList.size();
	}
}