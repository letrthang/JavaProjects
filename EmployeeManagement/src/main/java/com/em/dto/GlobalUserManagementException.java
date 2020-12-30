package com.em.dto;

public class GlobalUserManagementException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String message;
	private final int errorReason;

	public GlobalUserManagementException(String message, int errorReason) {
		this.message = message;
		this.errorReason = errorReason;
	}

	public GlobalUserManagementException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
			String message1, int errorReason) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.message = message1;
		this.errorReason = errorReason;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public int getErrorReason() {
		return errorReason;
	}
}
