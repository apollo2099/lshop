package com.lshop.web.userCenter.service;

import com.gv.core.datastructure.Dto;

/**
 * 物流信息
 * @author zhengxue
 * @since 2.0 2012-08-25
 *
 */
public interface LogisticsTrackingService {

	/**
	 * 处理物流跟踪信息
	 * @param dto- key:,expressNum:
	 * @return
	 */
	public Dto doLogisticsTracking(Dto dto);
}
