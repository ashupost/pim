package com.pim.jdbc;

public class GDSPException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String exceptionMsg;

	public GDSPException(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}
}