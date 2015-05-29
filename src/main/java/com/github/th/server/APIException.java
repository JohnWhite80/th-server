package com.github.th.server;

public class APIException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 821114710348167438L;
	
	private String errorCode;

	public APIException(String code, String message) {
		super(message);
		this.errorCode = code;
	}
	
	public APIException(String code, String message, Throwable throwable) {
		super(message, throwable);
		this.errorCode = code;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String code) {
		this.errorCode = code;
	}
	
	
}
