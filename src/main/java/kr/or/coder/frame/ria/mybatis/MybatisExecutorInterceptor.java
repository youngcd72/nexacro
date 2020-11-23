package kr.or.coder.frame.ria.mybatis;

import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nexacro17.xeni.extend.XeniMultipartProcBase;

import kr.or.coder.frame.ria.data.RiaRstDataset.RiaRstMap;

/**
 * <pre>
 * XENI에서 Spring의 MultipartRequest를 처리하기 위한 구현체
 * Spring의 MultipartResolver가 등록 되어 있을 경우에 xeni.properties을 이용하여 등록하여 사용한다.
 *      xeni.multipart.proc=com.nexacro.spring.servlet.XeniMultipartHandler 이름으로 등록 가능하다.
 * </pre>
 *
 * @author Park SeongMin
 * @since 08.24.2015
 * @version 1.0
 * @see XeniMultipartProcBase
 */
@Intercepts({ @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class MybatisExecutorInterceptor implements Interceptor {

	private static Logger logger = LoggerFactory.getLogger(MybatisExecutorInterceptor.class);

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		
		Object proceed = invocation.proceed();
		
		if(proceed instanceof List) {
		
			Object[] args = invocation.getArgs();
			MappedStatement ms = (MappedStatement) args[0];

			List<ResultMap> resultMaps = ms.getResultMaps();
			for(ResultMap resultMap: resultMaps) {
				if(isRiaRstMap(resultMap)) {

					LookupResultSetMetaDataConfig config = new LookupResultSetMetaDataConfig(true, ms);
					LookupResultSetMetaDataHolder.setLookupResultSetMetaDataConfig(config);
	            }
			}
		}
		return proceed;
	}

	private boolean isRiaRstMap(ResultMap resultMap) {
        
        Class resultClass = resultMap.getType();

        // mybatis resultType이 RiaRstMap 인지 체크
        if(RiaRstMap.class.isAssignableFrom(resultClass)) {
           return true; 
        }
        
        return false;
        
    }
}