package com.pim.jdbc;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

public class GDDaoImpl extends StoredProcedure {

	public GDDaoImpl(DataSource dataSource, String spName, List<?> paramList) {
		super();
		setDataSource(dataSource);
		setFunction(false);
		setSql(spName);
		// Iterate through parameter list to declare input and output parameters
		// required by this Stored Procedure
		for (int i = 0; i < paramList.size(); i++) {
			declareParameter((SqlParameter) paramList.get(i));
		}
		compile();
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