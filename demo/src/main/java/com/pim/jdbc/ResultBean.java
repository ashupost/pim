package com.pim.jdbc;


import java.io.Serializable;
import java.util.List;
import java.util.Map;



public class ResultBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer status;
	private boolean error;
	private ResultStatus result;
	private Map<String, Object> resultMap;
	private List<?> resultList;
	
	

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public ResultStatus getResult() {
		return result;
	}

	public void setResult(ResultStatus result) {
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

	public List<?> getResultList() {
		return resultList;
	}

	public void setResultList(List<?> resultList) {
		this.resultList = resultList;
	}
}