package com.pim.jdbc;

import java.sql.Types;

public class GDDAOHelper {

	public static final String TYPE_VARCHAR = "VARCHAR";
	public static final String TYPE_INTEGER = "INTEGER";
	public static final String TYPE_CHAR = "CHAR";
	public static final String TYPE_DATE = "DATE";
	public static final String TYPE_DOUBLE = "DOUBLE";
	public static final String TYPE_DECIMAL = "DECIMAL";
	public static final String TYPE_BLOB = "BLOB";
	public static final String TYPE_BINARY_STREAM = "VARBINARY";
	public static final String TYPE_TIMESTAMP = "TIMESTAMP";
	public static final String TYPE_ORACLE_CURSOR = "CURSOR";
	public static final String TYPE_BIGINT = "BIGINT";

	/**
	 * @param type
	 *            String describing Sql type of the input/output parameter
	 * @return integer consntant from Types class representing that Sql type
	 */
	public int convertType(String type) {

		// Will be used to return parameter type after reading from xml file
		if (type.equals(TYPE_VARCHAR))
			return Types.VARCHAR;
		else if (type.equals(TYPE_INTEGER))
			return Types.INTEGER;
		else if (type.equals(TYPE_CHAR))
			return Types.CHAR;
		else if (type.equals(TYPE_DATE))
			return Types.DATE;
		else if (type.equals(TYPE_DOUBLE))
			return Types.DOUBLE;
		else if (type.equals(TYPE_DECIMAL))
			return Types.DECIMAL;
		else if (type.equals(TYPE_BLOB))
			return Types.BLOB;
		else if (type.equals(TYPE_TIMESTAMP))
			return Types.TIMESTAMP;
		else if (type.equals(TYPE_BINARY_STREAM))
			return Types.LONGVARBINARY;
		else if (type.equals(TYPE_BIGINT))
			return Types.BIGINT;
		else
			return Types.NULL;

	}

}