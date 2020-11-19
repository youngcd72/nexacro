package kr.or.coder.frame.exception;

public class BizException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3445081764318715446L;

	public BizException(String msg){
        super(msg);
    }

    public BizException(Exception ex){
        super(ex);
    }
}
