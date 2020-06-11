package com.pim.jdbc;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.transaction.TransactionStatus;

/**
 * @author mritunjay
 *
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GDValueBean implements Serializable {

	private static final long serialVersionUID = -8125782857461129205L;
	/**
	 * This is the transaction status map, which will contain the transaction
	 * status of all the data services participating in the transaction.
	 */
	public Map<String, TransactionStatus> txnStatusMap = new LinkedHashMap<String, TransactionStatus>();

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);

		/*
		 * Class clazz = this.getClass(); StringBuffer sb = new StringBuffer();
		 * try { sb.append(clazz.getName()); sb.append("[");
		 * java.lang.reflect.Method[] getters = clazz.getDeclaredMethods();
		 * java.lang.reflect.Method getter = null; String fname = null; boolean
		 * first = true; for (int i = 0; i < getters.length; i++) { try { getter
		 * = getters[i]; fname = getter.getName(); if (fname.startsWith("get")
		 * || fname.startsWith("is")) { if (!first) sb.append(", "); else first
		 * = false; if (fname.startsWith("get")) sb.append(fname.substring(3));
		 * else sb.append(fname.substring(2)); sb.append("=" +
		 * getter.invoke(this, null)); } } catch (Throwable it) {
		 * sb.append("Error invoking method: " + fname); } } sb.append("]"); }
		 * catch (Throwable t) { sb.append("Error creating toString for Bean: "
		 * + clazz); } return sb.toString();
		 */
	}

	/**
	 * @return
	 */
	public Map<String, TransactionStatus> getTxnStatusMap() {
		return txnStatusMap;
	}

	/**
	 * @param map
	 */
	public void setTxnStatusMap(Map<String, TransactionStatus> map) {
		txnStatusMap = map;
	}

	private Map<String, Object> values = new LinkedHashMap<String, Object>();

	public Map<String, Object> getValues() {
		return values;
	}

	public void setValues(Map<String, Object> values) {
		this.values = values;
	}

	public void setValue(String key, Object value) {
		if (values == null) {
			values = new LinkedHashMap<String, Object>();
		}
		values.put(key, value);
	}

	public Object getValue(String key) {
		if (values == null) {
			values = new LinkedHashMap<String, Object>();
		}
		return values.get(key);
	}
	/*
	 * public Map describe() throws GDException { try{ return
	 * BeanUtils.describe(this); }catch(Throwable th) { throw new
	 * GDException(th); } }
	 * 
	 * public void set(Object o) throws GDException { try{
	 * BeanUtils.populate(this, BeanUtils.describe(o)); }catch(Throwable th) {
	 * throw new GDException(th); } }
	 * 
	 * public void populate(Object o) throws GDException { try{
	 * BeanUtils.populate(o, this.describe()); }catch(Throwable th) { throw new
	 * GDException(th); } }
	 */

}