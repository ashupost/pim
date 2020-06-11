package com.pim.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.ResourceLoaderAware;

public abstract class GDJDBCAbstractBusinessService implements ApplicationContextAware {
	protected ApplicationContext applicationContext;
	private final static String CLASS_NAME = "com.ground.dao.jdbc.GDJDBCAbstractBusinessService";
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	/**
	 * @param dataServiceKey
	 * @param GDValueBean
	 *            inputParamGDValueBean
	 * @return Object
	 * @throws GDException
	 *             Object
	 */
	protected Object getJDBCDataService(String dataServiceKey, GDValueBean inputParamGDValueBean) throws GDException {
		final String METHOD_NAME = "getDataService";
		logger.debug(GDConstant.ENTERED + CLASS_NAME + GDConstant.PERIOD + METHOD_NAME + GDConstant.SINGLE_SPACE + "dataServiceKey:" + dataServiceKey);
		if (GDUtil.isEmpty(dataServiceKey))
			throw new GDException(new IllegalArgumentException("Illegal dataServiceKey: " + dataServiceKey));

		GDJDBCAbstractDataService dataService = null;
		Object resultData = null;
		dataService = (GDJDBCAbstractDataService) applicationContext.getBean(dataServiceKey);
		resultData = dataService.execute(inputParamGDValueBean);

		logger.debug(GDConstant.EXITING + CLASS_NAME + GDConstant.PERIOD + METHOD_NAME + GDConstant.SINGLE_SPACE + "dataServiceKey:" + dataServiceKey);
		return resultData;
	}

	/**
	 * Set the ApplicationContext that this object runs in. Normally this call
	 * will be used to initialize the object.
	 * <p>
	 * Invoked after population of normal bean properties but before an init
	 * callback such as
	 * {@link org.springframework.beans.factory.InitializingBean#afterPropertiesSet()}
	 * or a custom init-method. Invoked after
	 * {@link ResourceLoaderAware#setResourceLoader},
	 * {@link ApplicationEventPublisherAware#setApplicationEventPublisher} and
	 * {@link MessageSourceAware}, if applicable.
	 * 
	 * @param applicationContext
	 *            the ApplicationContext object to be used by this object
	 * @throws ApplicationContextException
	 *             in case of context initialization errors
	 * @throws BeansException
	 *             if thrown by application context methods
	 * @see org.springframework.beans.factory.BeanInitializationException
	 */
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}