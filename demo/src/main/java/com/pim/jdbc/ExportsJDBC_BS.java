package com.pim.jdbc;

public class ExportsJDBC_BS extends GDJDBCAbstractBusinessService {

	@SuppressWarnings("unused")
	private final static String CLASS_NAME = "com.ground.dao.jdbc.ExportsJDBC_BS";

	public ExportsOPBean getLoginList(ExportsIPBean exportsInputValueBean) throws GDException {
		@SuppressWarnings("unused")
		final String METHOD_NAME = "getLoginList";
		ExportsOPBean exportOutputValueBean = null;
		try {
			exportsInputValueBean.setUniqueId("12345");
			exportOutputValueBean = (ExportsOPBean) getJDBCDataService("loginJDBCListDS", exportsInputValueBean);
			System.out.println(exportOutputValueBean);
			return exportOutputValueBean;
		} catch (Throwable e) {
			throw new GDException();
		}
	}
}