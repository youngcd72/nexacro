package kr.or.coder.frame.service;

import java.util.List;
import java.util.Map;

import kr.or.coder.frame.ria.data.RiaRstDataset;

public interface BaseService {

	public int insert(String queryId) throws Exception;
   
	public int insert(String queryId, Object paramObj) throws Exception;

	public int insertList(String queryId, List<Object> paramObjList) throws Exception;
	
	public int update(String queryId) throws Exception;
   
	public int update(String queryId, Object paramObj) throws Exception;

	public int updateList(String queryId, List<Object> paramObjList) throws Exception;
	
	public int updateBatch(String queryId, List<Object> paramObjList) throws Exception;
	
	public int delete(String queryId) throws Exception;
   
	public int delete(String queryId, Object paramObj) throws Exception;

	public int deleteList(String queryId, List<Object> paramObjList) throws Exception;
	
	public Object selectOne(String queryId) throws Exception;
   
	public Object selectOne(String queryId, Object paramObj) throws Exception;
   
	public Map<Object, Object> selectMap(String queryId, String mapKey) throws Exception;
   
	public Map<Object, Object> selectMap(String queryId, Object paramObj, String mapKey) throws Exception;
   
	public <E> List<E> selectList(String queryId) throws Exception;
   
	public <E> List<E> selectList(String queryId, Object paramObj) throws Exception;

	public RiaRstDataset selectRiaDataset(String queryId) throws Exception;
	
	public RiaRstDataset selectRiaDataset(String queryId, Object paramObj) throws Exception;
}
