package com.pim.service;

import com.pim.jdbc.ExportsIPBean;
import com.pim.jdbc.GDException;
import com.pim.jdbc.ResultBean;

public interface ExportService {
	public ResultBean getLoginList(ExportsIPBean exportsInputValueBean) throws GDException;
	
	public ResultBean getUserPassword(ExportsIPBean exportsInputValueBean) throws GDException;
}