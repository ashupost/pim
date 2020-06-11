package com.pim.jdbc;

public abstract class GDJDBCAbstractDataService {

	protected GDSimpleJdbcCallImpl bipBatchGenericDAO;

	/**
	 * description: This is a abstract method which is implemented in each child
	 * class Basically the implimenting method in child class does the following
	 * things: 1.populate input map from input bean 2.execute method of Stored
	 * Procedure class from Spring 3.populate output bean from outputMap
	 * returned by executeSP
	 * 
	 * @param inputAbstractBean
	 * @return Object
	 */
	public abstract Object execute(GDValueBean inputBean) throws GDException;

	/**
	 * @param daoImpl
	 *            void
	 */
	public void setBipBatchGenericDAO(GDSimpleJdbcCallImpl daoImpl) {
		this.bipBatchGenericDAO = daoImpl;
	}
}