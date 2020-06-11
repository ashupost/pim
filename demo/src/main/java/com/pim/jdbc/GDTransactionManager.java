package com.pim.jdbc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class GDTransactionManager {

	@SuppressWarnings("unused")
	private final static String CLASS_NAME = "com.ground.dao.jdbc.GDTransactionManager";

	private PlatformTransactionManager transactionManager;

	/**
	 * This method creates a transaction for the data service.
	 * 
	 * @param txnStatusMap
	 */
	public void createTransaction(Map<String, TransactionStatus> txnStatusMap) {

		DefaultTransactionDefinition td = null;
		TransactionStatus transactionStatus = null;

		// put the transaction status for each data service in the txnStatusMap
		// the first transaction should will have the propagation as
		// PROPAGATION_REQUIRES_NEW, rest will have PROPAGATION_REQUIRED
		if (txnStatusMap != null) {
			if (txnStatusMap.isEmpty()) {
				/**
				 * Create a new transaction, suspending the current transaction
				 * if one exists. Analogous to the EJB transaction attribute of
				 * the same name.
				 * <p>
				 * <b>NOTE:</b> Actual transaction suspension will not work
				 * out-of-the-box on all transaction managers. This in
				 * particular applies to
				 * {@link org.springframework.transaction.jta.JtaTransactionManager}
				 * , which requires the
				 * <code>javax.transaction.TransactionManager</code> to be made
				 * available it to it (which is server-specific in standard
				 * J2EE).
				 * <p>
				 * A <code>PROPAGATION_REQUIRES_NEW</code> scope always defines
				 * its own transaction synchronizations. Existing
				 * synchronizations will be suspended and resumed appropriately.
				 * 
				 * @see org.springframework.transaction.jta.JtaTransactionManager#setTransactionManager
				 */
				td = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
				transactionStatus = transactionManager.getTransaction(td);
				txnStatusMap.put("1", transactionStatus);
			} else {
				/**
				 * Support a current transaction; create a new one if none
				 * exists. Analogous to the EJB transaction attribute of the
				 * same name.
				 * <p>
				 * This is typically the default setting of a transaction
				 * definition, and typically defines a transaction
				 * synchronization scope.
				 */
				td = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);
				transactionStatus = transactionManager.getTransaction(td);
				int size = txnStatusMap.size();
				int key = size + 1;
				txnStatusMap.put(key + "", transactionStatus);
			}
		}

	}

	/**
	 * This method commits the transaction for all the transactionStatus present
	 * in the map.
	 * 
	 * @param txnStatusMap
	 */
	public void commitTransaction(Map<String, TransactionStatus> txnStatusMap) {
		@SuppressWarnings("unused")
		int size = txnStatusMap.size();
		List<String> keyList = new ArrayList<String>(txnStatusMap.keySet());
		Collections.reverse(keyList);

		Iterator<String> itr = keyList.iterator();
		String key = "";
		TransactionStatus txnStatus = null;
		while (itr.hasNext()) {
			key = (String) itr.next();
			txnStatus = (TransactionStatus) txnStatusMap.get(key);
			transactionManager.commit(txnStatus);
		}

	}

	/**
	 * This method rollbacks the transaction for all the transactionStatus
	 * present in the map.
	 * 
	 * @param txnStatusMap
	 */
	public void rollbackTransaction(Map<String, TransactionStatus> txnStatusMap) {
		@SuppressWarnings("unused")
		int size = txnStatusMap.size();
		List<String> keyList = new ArrayList<String>(txnStatusMap.keySet());
		Collections.reverse(keyList);

		Iterator<String> itr = keyList.iterator();
		String key = "";
		TransactionStatus txnStatus = null;
		while (itr.hasNext()) {
			key = (String) itr.next();
			txnStatus = (TransactionStatus) txnStatusMap.get(key);
			transactionManager.rollback(txnStatus);
		}

	}

	/**
	 * @param manager
	 */
	public void setTransactionManager(PlatformTransactionManager manager) {
		transactionManager = manager;
		System.out.println("Set setTransactionManager");
	}

}