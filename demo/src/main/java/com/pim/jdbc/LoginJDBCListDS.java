package com.pim.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

public class LoginJDBCListDS extends GDJDBCAbstractDataService {

	@Autowired
	GDTransactionManager gdTxManager;

	public Object execute(GDValueBean inputBean) throws GDException {
		@SuppressWarnings("unused")
		final String METHOD_NAME = "execute";
		@SuppressWarnings("unused")
		ExportsIPBean exportsInputValueBean = null;
		ExportsOPBean exportsOutputValueBean = null;
		// GDTransactionManager bipTransactionManager = (GDTransactionManager)
		// GDApplicationContextManager.getBean("gdTxManager");
		gdTxManager.createTransaction(inputBean.getTxnStatusMap());

		try {
			exportsInputValueBean = (ExportsIPBean) inputBean;
			Map<String, ?> inputMap = new HashMap<String, Object>(0);
			Map<String, ?> outputMap = bipBatchGenericDAO.execute(inputMap);
			exportsOutputValueBean = new ExportsOPBean();
			exportsOutputValueBean.setExportsOPBeanList((List<?>) outputMap.get(bipBatchGenericDAO.getReturnResultSet()));
			gdTxManager.commitTransaction(inputBean.getTxnStatusMap());
		} catch (DataAccessException bipDae) {
			gdTxManager.rollbackTransaction(inputBean.getTxnStatusMap());
			throw new GDException(bipDae);
		} catch (Throwable e) {
			gdTxManager.rollbackTransaction(inputBean.getTxnStatusMap());
			throw new GDException(e);
		}
		return exportsOutputValueBean;
	}
}