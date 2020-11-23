package kr.or.coder.frame.ria.mybatis;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nexacro17.xapi.data.datatype.DataType;
import com.nexacro17.xapi.data.datatype.DataTypeFactory;
import com.nexacro17.xapi.data.datatype.PlatformDataType;
import com.nexacro17.xeni.extend.XeniMultipartProcBase;

import kr.or.coder.frame.ria.data.RiaRstDataset;
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
@Intercepts({ @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})})
public class MybatisResultSetInterceptor implements Interceptor {

	private static Logger logger = LoggerFactory.getLogger(MybatisResultSetInterceptor.class);

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
	}
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		
		LookupResultSetMetaDataConfig config = LookupResultSetMetaDataHolder.getLookupResultSetMetaDataConfig();
		
		if(config != null && config.isRiaRstMap()) {

			return generateRiaRstDataset(invocation);

		} else {

			return invocation.proceed();
		}
	}

	@SuppressWarnings("unchecked")
	private Object generateRiaRstDataset(Invocation invocation) {
		
		Object[] args = invocation.getArgs();
		Statement statement = (Statement) args[0];

		RiaRstDataset riaRstDataset = new RiaRstDataset();
		
		try {

			ResultSet rs = statement.getResultSet();
			ResultSetMetaData resultMetaData = rs.getMetaData();
			
			for(int i = 1; i <= resultMetaData.getColumnCount(); i++) {

				String colNm = resultMetaData.getColumnLabel(i);
	            if (colNm == null || colNm.equals("")) {
	            	colNm = resultMetaData.getColumnName(i);
	            }

	            String sqlTypeName   = resultMetaData.getColumnTypeName(i);
	            
	            DataType dataType = DataTypeFactory.getSqlDataType(sqlTypeName);

	            if(dataType == null) {
	                // when dbms vendor's specific column type name
	                int javaTypeNum = resultMetaData.getColumnType(i);
	                dataType = DataTypeFactory.getSqlDataType(javaTypeNum);
	            }

	            // find platform datatype
	            dataType = DataTypeFactory.getPlatformDataType(dataType);

	            if(dataType == null) {
	                dataType = PlatformDataType.UNDEFINED;
	            }

	            riaRstDataset.put(colNm, dataType);
			}

			List<RiaRstMap<String, Object>> riaRstMapList = (List<RiaRstMap<String, Object>>)invocation.proceed();
			riaRstDataset.setRiaRstMapList(riaRstMapList);
			
			
		} catch(Throwable ex) {

			logger.error(ex.getMessage(), ex);

		} finally {

			LookupResultSetMetaDataHolder.resetLookupResultSetMetaDataConfig();
		}
		
		return riaRstDataset;
	}
}