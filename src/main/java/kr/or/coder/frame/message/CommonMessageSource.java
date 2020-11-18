package kr.or.coder.frame.message;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.dao.CannotSerializeTransactionException;
import org.springframework.dao.CleanupFailureDataAccessException;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.dao.IncorrectUpdateSemanticsDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.dao.TypeMismatchDataAccessException;
import org.springframework.dao.UncategorizedDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.UncategorizedSQLException;

/**
 * 공통 메시지 관리
 * 
 * @author 공통팀 
 * @since 2020.11.18
 * @version 1.0
 * 
 * <pre>
 * 수정일       수정자              수정내용
 * ----------  --------    ---------------------------
 * 2020.11.18   공통팀              최초작성
 * </pre>
 * 
 */
public class CommonMessageSource extends ReloadableResourceBundleMessageSource implements MessageSource {

	protected Logger logger = LoggerFactory.getLogger(getClass());
    
    private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

    /**
     * getReloadableResourceBundleMessageSource()
     * 
     * @param reloadableResourceBundleMessageSource - resource MessageSource
     * @return ReloadableResourceBundleMessageSource
     */
    public void setReloadableResourceBundleMessageSource(ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource) {
    	this.reloadableResourceBundleMessageSource = reloadableResourceBundleMessageSource;
    }
 
    /**
     * getReloadableResourceBundleMessageSource()
     * 
     * @return ReloadableResourceBundleMessageSource
     */
    public ReloadableResourceBundleMessageSource getReloadableResourceBundleMessageSource() {
    	return reloadableResourceBundleMessageSource;
    }

    /**
     * 정의된 메세지 조회
     * 
     * @param code - 메세지 코드
     * @return String
     */
    public String getMessage(String code) {
    	return getReloadableResourceBundleMessageSource().getMessage(code, null, Locale.KOREA);
    }

    /**
     * 정의된 메세지 조회
     * 
     * @param code
     * @param args
     * @return
     */
    public String getMessage(String code, Object[] args) {    
    	return getReloadableResourceBundleMessageSource().getMessage(code, args, Locale.KOREA);
    }

    /**
     * 정의된 추가 메세지 조회
     * 
     * @param code - 메세지 코드
     * @return String
     */
    public String getMessage(String code, String arg) {
		return getReloadableResourceBundleMessageSource().getMessage(code, new String[] {arg}, Locale.KOREA);
    }
}