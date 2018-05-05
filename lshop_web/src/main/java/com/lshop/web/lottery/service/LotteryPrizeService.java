package com.lshop.web.lottery.service;

import java.util.Date;

import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvAccountPhysicalTicket;

/**
 * 抽奖用户奖品服务
 * @author caicl
 *
 */
public interface LotteryPrizeService {
	/**
	 * 分页查看用户奖品
	 * @param userCode
	 * @param pageNo
	 * @param pageSize
	 * @param mallFlag 体系标识
	 * @return
	 */
	public Pagination findPrizePage(String userCode, int pageNo, int pageSize, String mallFlag);
	/**
	 * 增加用户奖品
	 * @param userCode 用户号
	 * @param prizeName 奖品名称
	 * @param lotteryCode 抽奖活动号
	 * @param prizeItemNo TODO
	 * @param expiryDate TODO
	 * @param mallFlag 商城体系标识
	 * @param priceNo 奖品系统编码
	 * @param priceType 奖品类型
	 * @throws Exception 
	 */
	public String addPrize(String userCode, String prizeNo, String prizeName, int prizeType, String lotteryCode, String prizeItemNo, Date expiryDate, String mallFlag) throws Exception;
	/**
	 * 查看实物奖品寄送信息
	 * @return
	 */
	public LvAccountPhysicalTicket getMaterialDeliver(String prizeCode);
	/**
	 * 增加实物兑奖收货信息
	 * @param addrCode
	 * @param userCode TODO
	 * @param prizeCode TODO
	 * @return
	 */
	public String addDeliver(String addrCode, String userCode, String prizeCode);
}
