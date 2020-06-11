package com.pim.jdbc;

import java.util.List;

public class ExportsOPBean extends GDValueBean {

	private static final long serialVersionUID = 1L;
	private Integer status;
	private boolean error;
	private List<?> exportsOPBeanList;
	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return status
	 */
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return
	 */
	public boolean getError() {
		return error;
	}

	/**
	 * @param b
	 */
	public void setError(boolean b) {
		error = b;
	}

	public List<?> getExportsOPBeanList() {
		return exportsOPBeanList;
	}

	public void setExportsOPBeanList(List<?> exportsOPBeanList) {
		this.exportsOPBeanList = exportsOPBeanList;
	}
}