package kr.or.coder.frame.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import kr.or.coder.frame.ria.data.RiaRstDataset;

@Repository("baseDAO")
public class BaseDAO extends SqlSessionDaoSupport {

	private SqlSessionFactory sqlSessionFactory;
	
	@Resource(name = "sqlSession")
	public void setSqlSessionFactory(SqlSessionFactory sqlSession) { 
		
		super.setSqlSessionFactory(sqlSession);
		
		this.sqlSessionFactory = sqlSession;
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

	public int updateBatch(String queryId, List<Object> paramObjList) {

		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);

		int totUptCnt  = 0;

        try {
                
            for (Object paramObj : paramObjList) {                     
            	totUptCnt += sqlSession.update(queryId, paramObj);
            }
 
        } finally {
            sqlSession.flushStatements();
            sqlSession.close();
        }

        return totUptCnt;
	}
	
	public int delete(String queryId) { 
		return getSqlSession().delete(queryId); 
	}
   
	public int delete(String queryId, Object paramObj) { 
		return getSqlSession().delete(queryId, paramObj); 
	}
   
	public Object selectOne(String queryId) { 
		return (Object)getSqlSession().selectOne(queryId); 
	}
   
	public Object selectOne(String queryId, Object paramObj) { 
		return (Object)getSqlSession().selectOne(queryId, paramObj); 
	}
   
	public Map<Object, Object> selectMap(String queryId, String mapKey) { 
		return getSqlSession().selectMap(queryId, mapKey); 
	}
   
	public Map<Object, Object> selectMap(String queryId, Object paramObj, String mapKey) { 
		return getSqlSession().selectMap(queryId, paramObj, mapKey); 
	}
   
	public <K, V> Map<K, V> selectMap(String queryId, Object paramObj, String mapKey, RowBounds rowBounds) { 
		return getSqlSession().selectMap(queryId, paramObj, mapKey, rowBounds); 
	}
   
	public <E> List<E> selectList(String queryId) { 
		return getSqlSession().selectList(queryId); 
	}
   
	public <E> List<E> selectList(String queryId, Object paramObj) { 
		return getSqlSession().selectList(queryId, paramObj); 
	}

	public void selectList(String queryId, ResultHandler handler) { 
		getSqlSession().select(queryId, handler); 
	}

	public RiaRstDataset selectRiaDataset(String queryId) {

		return (RiaRstDataset)getSqlSession().selectList(queryId);
	}
	
	public RiaRstDataset selectRiaDataset(String queryId, Object paramObj) {

		return (RiaRstDataset)getSqlSession().selectList(queryId, paramObj);
	}
}
