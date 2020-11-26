package kr.or.coder.frame.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.coder.frame.dao.BaseDAO;
import kr.or.coder.frame.ria.data.RiaRstDataset;

@Service("baseService")
public class BaseServiceImpl {

	@Resource(name = "baseDAO")
	private BaseDAO baseDAO;
	
	public int insert(String queryId) throws Exception { 
		return baseDAO.insert(queryId); 
	}
   
	public int insert(String queryId, Object paramObj) throws Exception {
		return baseDAO.insert(queryId, paramObj); 
	}

	public int insertList(String queryId, List<Object> paramObjList) throws Exception {
		
		int totInsCnt = 0;
		
		for(Object paramObj : paramObjList) {
			totInsCnt += baseDAO.insert(queryId, paramObj);
		}
		return totInsCnt;
	}
	
	public int update(String queryId) throws Exception {
		return baseDAO.update(queryId); 
	}
   
	public int update(String queryId, Object paramObj) throws Exception { 
		return baseDAO.update(queryId, paramObj); 
	}

	public int updateList(String queryId, List<Object> paramObjList) throws Exception {

		int totUptCnt = 0;

		for(Object paramObj : paramObjList) {
			totUptCnt += baseDAO.update(queryId, paramObj);
		}
		return totUptCnt;
	}
	
	public int delete(String queryId) throws Exception {
		return baseDAO.delete(queryId); 
	}
   
	public int delete(String queryId, Object paramObj) throws Exception { 
		return baseDAO.delete(queryId, paramObj); 
	}

	public int deleteList(String queryId, List<Object> paramObjList) throws Exception {
		
		int totDelCnt = 0;
		
		for(Object paramObj : paramObjList) {
			totDelCnt += baseDAO.delete(queryId, paramObj);
		}
		return totDelCnt; 
	}
	
	public Object selectOne(String queryId) throws Exception {
		return baseDAO.selectOne(queryId); 
	}
   
	public Object selectOne(String queryId, Object paramObj) throws Exception { 
		return baseDAO.selectOne(queryId, paramObj); 
	}
   
	public Map<Object, Object> selectMap(String queryId, String mapKey) throws Exception {
		return baseDAO.selectMap(queryId, mapKey); 
	}
   
	public Map<Object, Object> selectMap(String queryId, Object paramObj, String mapKey) throws Exception { 
		return baseDAO.selectMap(queryId, paramObj, mapKey); 
	}
   
	public <E> List<E> selectList(String queryId) throws Exception {
		return baseDAO.selectList(queryId); 
	}
   
	public <E> List<E> selectList(String queryId, Object paramObj) throws Exception { 
		return baseDAO.selectList(queryId, paramObj); 
	}

	public RiaRstDataset selectRiaDataset(String queryId) throws Exception {

		return baseDAO.selectRiaDataset(queryId);
	}
	
	public RiaRstDataset selectRiaDataset(String queryId, Object paramObj) throws Exception {

		return baseDAO.selectRiaDataset(queryId, paramObj);
	}
}
