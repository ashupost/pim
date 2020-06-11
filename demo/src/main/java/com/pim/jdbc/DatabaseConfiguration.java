package com.pim.jdbc;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@EnableCaching
@EnableAspectJAutoProxy
@ImportResource("classpath:/spring-xml/spring-resource.xml")
public class DatabaseConfiguration {

	
	
	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("org.mariadb.jdbc.Driver");
		driverManagerDataSource.setUrl("jdbc:mariadb://pim.ccz7m6lbcb2f.eu-west-1.rds.amazonaws.com:3306/mypimdb");
		driverManagerDataSource.setUsername("pim");
		driverManagerDataSource.setPassword("pim1234567890");
		
		return driverManagerDataSource;
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean
	public GDTransactionManager gdTxManager() {
		GDTransactionManager transactionManager = new GDTransactionManager();
		transactionManager.setTransactionManager(transactionManager());
		return transactionManager;
	}

	@Bean
	public ExportsJDBC_BS exportsJDBC_BS() {
		return new ExportsJDBC_BS();
	}

}