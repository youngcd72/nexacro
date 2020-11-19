package kr.or.coder.frame.ria.nexacro;

/**
 * nexacro constant
 * 
 * @author 공통팀
 * @since 2020.11.18
 * @version 1.0
 * 
 * <pre>
 * 수정일       수정자              수정내용
 * ----------  --------    ---------------------------
 * 2020.11.18  	공통팀        		최초 생성
 * </pre>
 */
public class NexacroConstant {

	/* User-Agent */
	public final class USER_AGENT {
		public final static String XPLATFORM = "xplatform";     // Xplatform
		public final static String NEXACRO   = "nexacro";       // Nexacro
	}

	/* naxcro error key string */
	public final class ERROR {
		public final static String CODE = "ErrorCode";		    // 응답 코드 
		public final static String MESSAGE = "ErrorMsg";        // 오류 메시지
	}

	public final static String OUT_RESULT_DATA = "RESULT_DATA"; 

	/* 사용자 정의 dataset명 */
	public final static String TRAN_INFO_DATASET_NAME   = "_DS_OUT_INFO_";	// Nexacro과 transaction시 기본 정보를 담은 Dataset 이름
	public final static String SEARCH_INFO_DATASET_NAME = "_DS_SEARCH_INFO_";	// 조회에 필요한 파라미터 정보를 담은 Dataset 이름

}
