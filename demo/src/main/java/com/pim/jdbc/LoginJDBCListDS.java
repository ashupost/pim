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
		ResultBean exportsOutputValueBean = null;
		// GDTransactionManager bipTransactionManager = (GDTransactionManager)
		// GDApplicationContextManager.getBean("gdTxManager");
		gdTxManager.createTransaction(inputBean.getTxnStatusMap());

		try {
			exportsInputValueBean = (ExportsIPBean) inputBean;
			Map<String, Object> inputMap = new HashMap<String, Object>(0);
			
			inputMap.put("PRODUCT_ID", "1");
			
			Map<String, Object> outputMap = bipBatchGenericDAO.execute(inputMap);
			Object o = outputMap.get("RESULT");
			System.out.println("ooo="+ o);
			
			exportsOutputValueBean = new ResultBean();
			exportsOutputValueBean.setResult(ResultStatus.SUCCESS);
			exportsOutputValueBean.setResultList((List<?>) outputMap.get(bipBatchGenericDAO.getReturnResultSet()));
			exportsOutputValueBean.setResultMap(outputMap);
			
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