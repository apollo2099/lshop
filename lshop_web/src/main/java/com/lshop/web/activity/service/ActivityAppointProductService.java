package com.lshop.web.activity.service;

import java.util.List;
public interface ActivityAppointProductService {

	public List findActivityByProductCode(String productCode) throws Exception;
	public List findActivityByProductCode(String [] productArr) throws Exception;

}
