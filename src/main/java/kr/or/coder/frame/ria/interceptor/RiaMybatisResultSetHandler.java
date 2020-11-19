package kr.or.coder.frame.ria.interceptor;

import java.sql.Statement;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

@Intercepts({ @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class}),
			  @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class RiaMybatisResultSetHandler implements Interceptor {

	

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		
		// query method 처리
		if("query".equals(invocation.getMethod().getName())) {

		// handleResultSets method 처리	
		} else if("handleResultSets".equals(invocation.getMethod().getName())) {
			
			// search metadata
			Object[] args = invocation.getArgs();
			Statement statement = (Statement) args[0];
			
		}
		return invocation.proceed();
	}
	
	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
	}
}
