package kr.or.coder.frame.ria.mybatis;

import org.apache.ibatis.mapping.MappedStatement;

public class LookupResultSetMetaDataConfig {

	private final boolean isRiaRstMap;
	private final MappedStatement ms;
	
	LookupResultSetMetaDataConfig(final boolean isRiaRstMap, final MappedStatement ms) {
		this.isRiaRstMap = isRiaRstMap;
		this.ms = ms;
	}

	boolean isRiaRstMap() {
		return isRiaRstMap;
	}

	MappedStatement getMappedStatement() {
		return ms;
	}
}
