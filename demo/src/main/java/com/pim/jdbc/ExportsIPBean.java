package com.pim.jdbc;

public class ExportsIPBean extends GDValueBean {
	private static final long serialVersionUID = 1L;
	private String exportReqtId;
	private String fileStatus = "";
	private String uniqueId = "";

	public String getExportReqtId() {
		return exportReqtId;
	}

	public void setExportReqtId(String exportReqtId) {
		this.exportReqtId = exportReqtId;
	}

	/**
	 * @return
	 */

	public String getFileStatus() {
		return fileStatus;
	}

	/**
	 * @param string
	 */

	public void setFileStatus(String string) {
		fileStatus = string;
	}

	/**
	 * @return
	 */
	public String getUniqueId() {
		return uniqueId;
	}

	/**
	 * @param string
	 */
	public void setUniqueId(String string) {
		uniqueId = string;
	}

}
