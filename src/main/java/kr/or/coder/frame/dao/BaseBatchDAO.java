package kr.or.coder.frame.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("baseBatchDAO")
public class BaseBatchDAO extends SqlSessionDaoSupport {

	@Resource(name = "batchSqlSession")
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSession) { 
		super.setSqlSessionTemplate(sqlSession);
	}
	
	public int insert(String queryId) { 
		return getSqlSession().insert(queryId); 
	}
   
	public int insert(String queryId, Object paramObj) { 
		return getSqlSession().insert(queryId, paramObj); 
	}
   
	public int update(String queryId) { 
		return getSqlSession().update(queryId); 
	}
   
	public int update(String queryId, Object paramObj) { 
		return getSqlSession().update(queryId, paramObj); 
	}

	public int delete(String queryId) { 
		return getSqlSession().delete(queryId); 
	}
   
	public int delete(String queryId, Object paramObj) { 
		return getSqlSession().delete(queryId, paramObj); 
	}
   
	public int updateList(String queryId, List<Object> paramObjList) {

		int totUptCnt = 0;
		
        for (Object paramObj : paramObjList) {                     
        	totUptCnt += getSqlSession().update(queryId, paramObj);
        }
        return totUptCnt;
	}
}
