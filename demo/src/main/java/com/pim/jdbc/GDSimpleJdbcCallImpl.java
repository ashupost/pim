package com.pim.jdbc;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class GDSimpleJdbcCallImpl extends SimpleJdbcCall {
	@SuppressWarnings("unused")
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

	private String returnResultSet;

	public String getReturnResultSet() {
		return returnResultSet;
	}

	public void setReturnResultSet(String returnResultSet) {
		this.returnResultSet = returnResultSet;
	}

	public GDSimpleJdbcCallImpl(DataSource dataSource, String spName, List<?> paramList, String returnResultSet) {

		super(dataSource);
		super.setFunction(false);
		super.withProcedureName(spName);
		setReturnResultSet(returnResultSet);
		// final String information = "SP["+ spName +"]  paramList = " +
		// getInParameterNames();
		// logger.debug(information);
		// logger.info(information);
		// System.out.println(information);

		// setSql(spName);
		// Iterate through parameter list to declare input and output parameters
		// required by this Stored Procedure
		int paramListSize = paramList.size();
		for (int i = 0; i < paramListSize; i++) {
			// declareParameter((SqlParameter) paramList.get(i));
			// declareParameters((SqlParameter) paramList.get(i));
			super.addDeclaredParameter((SqlParameter) paramList.get(i));
		}
		super.checkCompiled();
	}

	public GDSimpleJdbcCallImpl(DataSource dataSource, String spName, List<?> paramList, String returnResultSet, RowMapper<?> rowMapper) {

		super(dataSource);
		super.setFunction(false);
		super.withProcedureName(spName);
		super.returningResultSet(returnResultSet, rowMapper);
		setReturnResultSet(returnResultSet);
		// final String information = "SP["+ spName +"]  paramList = " +
		// getInParameterNames();
		// logger.debug(information);
		// logger.info(information);
		// System.out.println(information);
		// setSql(spName);
		// Iterate through parameter list to declare input and output parameters
		// required by this Stored Procedure
		int paramListSize = paramList.size();
		for (int i = 0; i < paramListSize; i++) {
			// declareParameter((SqlParameter) paramList.get(i));
			// declareParameters((SqlParameter) paramList.get(i));
			super.addDeclaredParameter((SqlParameter) paramList.get(i));
		}
		super.checkCompiled();
	}

	/**
	 * Execute the stored procedure. Subclasses should define a strongly typed
	 * execute method (with a meaningful name) that invokes this method,
	 * populating the input map and extracting typed values from the output map.
	 * Subclass execute methods will often take domain objects as arguments and
	 * return values. Alternatively, they can return void.
	 * 
	 * @param inParams
	 *            map of input parameters, keyed by name as in parameter
	 *            declarations. Output parameters need not (but can be) included
	 *            in this map. It is legal for map entries to be
	 *            <code>null, and this will produce the
	 * correct behavior using a NULL argument to the stored procedure.
	 * @return map of output params, keyed by name as in parameter declarations.
	 *         Output parameters will appear here, with their values after the
	 *         stored procedure has been called.
	 */
	public Map<String, Object> execute(Map<String, ?> inParams) throws DataAccessException {
		return super.execute(inParams);
	}

}