package com.lshop.common.service;

/**
 * 数据初始化服务
 * @author xusl
 *
 */
public interface InitDataService {
	
	public void init();
	
	/**
	 * 初始化店铺数据
	 * @return
	 */
	public Integer initStoreIds();

}
