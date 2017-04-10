package com.hortifruti.application;

public class BusinessException extends RuntimeException {
	
	private static final long serialVersionUID = -174785794883498932L;

	public BusinessException(String message) {
	    super(message);
	  }
	
	public BusinessException(Throwable cause){
		super(cause);
	}
	
	public BusinessException(String message, Throwable cause){
        super(message, cause);
    }
}
