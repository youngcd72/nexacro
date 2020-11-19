package kr.or.coder.frame.exception;

public class UserException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8183268733140233808L;

	public UserException(String msg){
        super(msg);
    }

    public UserException(Exception ex){
        super(ex);
    }
}
