package com.pim.jdbc;
import javax.servlet.ServletContextEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
/**
 * @version 	1.0
 * @author
 */
public class GDInitServlet extends ContextLoaderListener {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());
	/**
	 * 
	 */

	public void contextInitialized(ServletContextEvent servletContextEvent) 
    {
		System.out.println("*******   Initializing context       *******");
		LOGGER.debug("*******   Initializing context       *******");
		try{
		super.contextInitialized(servletContextEvent);
		}catch(Throwable ee){
			LOGGER.error("******* Error in Initializing context  *******");
			ee.printStackTrace();
		}
		LOGGER.debug("*******    Done initializing context       *******");
		System.out.println("*******    Done initializing context  *******");
		
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext()); 
		GDApplicationContextManager.setBIPApplicationContext(context);
		//PropertyConfigurator.configure("log4j.properties"); 
		
    }
	
	public void contextDestroyed(ServletContextEvent servletContextEvent) 
	{
		super.contextDestroyed(servletContextEvent);
		System.out.println("Done Distroying context context ****************");
	}
}