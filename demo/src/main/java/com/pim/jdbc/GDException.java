package com.pim.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GDException extends Throwable {

	private static final long serialVersionUID = 1L;

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	public GDException() {
		super();
		printStackTrace();
		logger.error("Error GDException");
	}

	public GDException(String message, Throwable throwable) {
		super(message, throwable);
		printStackTrace();
		logger.error(message, throwable);
	}

	public GDException(String message) {
		super(message);
		printStackTrace();
		logger.error(message);
	}

	public GDException(Throwable throwable) {
		super(throwable);
		printStackTrace();
		logger.error("", throwable);
	}

}
