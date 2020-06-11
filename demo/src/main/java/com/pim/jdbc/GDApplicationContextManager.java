package com.pim.jdbc;

import org.springframework.web.context.WebApplicationContext;

public class GDApplicationContextManager {
	static WebApplicationContext applicationContext = null;

	/**
	 * @return ApplicationContext
	 */
	public static WebApplicationContext getBIPApplicationContext() {
		return applicationContext;
	}

	public static void setBIPApplicationContext(WebApplicationContext applicationContextBIP) {

		GDApplicationContextManager.applicationContext = applicationContextBIP;
	}

	/**
	 * @param beanId
	 * @return Object
	 */
	public static Object getBean(String beanId) {
		return applicationContext.getBean(beanId);
	}

	public static Object getBusinessService(String businessServiceKey) {
		return applicationContext.getBean(businessServiceKey);
	}

}