package com.lshop.web.activity.service;

import com.lshop.common.activity.vo.ShareActivity;

/**
 * 分享活动
 * @author caicl
 *
 */
public interface ShareService {
	/**
	 * 获得与抽奖有关联的最新分享活动
	 * @param lotteryCode
	 * @return
	 * @throws Exception 
	 */
	ShareActivity getFirstLotteryShare(String lotteryCode) throws Exception;
	/**
	 * 用户分享成功回调
	 * @param userCode
	 * @param shareCode
	 * @return
	 * @throws Exception 
	 */
	boolean shareSuccess(String userCode, String shareCode) throws Exception;
	/**
	 * 返回分享活动详情
	 * @param activityCode
	 * @return
	 * @throws Exception
	 */
	ShareActivity getShareDetail(String activityCode) throws Exception;
}
