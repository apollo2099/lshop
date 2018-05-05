package com.lshop.web.lottery.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.activity.constant.ActivityConstant;
import com.lshop.common.constant.LotteryConstant;
import com.lshop.common.coupon.constant.CouponConstant;
import com.lshop.common.datastructure.ResultMsg;
import com.lshop.common.pojo.logic.LvAccountPhysicalTicket;
import com.lshop.common.pojo.logic.LvAccountPrize;
import com.lshop.common.pojo.logic.LvCoupon;
import com.lshop.common.pojo.logic.LvCouponType;
import com.lshop.common.pojo.user.LvAccountAddress;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.RedisClient;
import com.lshop.common.util.RedisConstant;
import com.lshop.web.accountAddress.service.AccountAddressService;
import com.lshop.web.activity.service.ActivityService;
import com.lshop.web.coupon.service.CouponService;
import com.lshop.web.lottery.service.LotteryPrizeService;
@Service("LotteryPrizeService")
public class LotteryPrizeServiceImpl implements LotteryPrizeService{
	
	private static Log log = LogFactory.getLog(LotteryPrizeServiceImpl.class);
	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	@Resource
	private ActivityService activityService;
	@Resource
	private CouponService couponService;
	@Resource
	private AccountAddressService addressService;
	
	@Override
	public Pagination findPrizePage(String userCode, int pageNo, int pageSize, String mallFlag) {
		String hql = "from LvAccountPrize where userCode=:userCode and mallFlag=:mallFlag order by winDate desc";
		Pagination pagination = lvlogicReadDao.find(Finder.create(hql).setParam("userCode", userCode).setParam("mallFlag", mallFlag), pageNo, pageSize);
		//查看优惠券奖品的状态
		if (ObjectUtils.isNotEmpty(pagination.getList())) {
			List<LvAccountPrize> prizes = new ArrayList<LvAccountPrize>();
			List<LvAccountPrize> pList = (List<LvAccountPrize>) pagination.getList();
			LvAccountPrize tmpPrize = null;
			for (int i = 0; i < pList.size(); i++) {
				if (pList.get(i).getPrizeType().intValue() == ActivityConstant.GIFT_TYPE_COUPON) {
					tmpPrize = pList.get(i);
					//未使用
					tmpPrize.setCouponStatus(1);
					hql = "from LvCoupon where code=:code";
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("code", tmpPrize.getPrizeCode());
					LvCoupon coupon = (LvCoupon) lvlogicReadDao.findUnique(hql, param);
					if (ObjectUtils.isEmpty(coupon)) {//防止数据异常
						prizes.add(tmpPrize);
						continue;
					}
					if (coupon.getCouponStatus().intValue() == 2) {
						//已使用
						tmpPrize.setCouponStatus(2);
					} else {
						hql = "from LvCouponType where code=:code";
						param = new HashMap<String, Object>();
						param.put("code", coupon.getCouponTypeCode());
						LvCouponType couponType = (LvCouponType) lvlogicReadDao.findUnique(hql, param);
						if (couponType.getStatus().intValue() == CouponConstant.STATUS_DIS_USE) {
							//已禁用
							tmpPrize.setCouponStatus(3);
						}
						if (couponType.getEndTime().getTime() < System.currentTimeMillis()) {
							//已过期
							tmpPrize.setCouponStatus(4);
						}
					}
					prizes.add(tmpPrize);
				} else {
					prizes.add(pList.get(i));
				}
			}
			pagination.setList(prizes);
		}
		return pagination;
	}

	@Override
	public String addPrize(String userCode, String prizeNo, String prizeName,
			int prizeType, String lotteryCode, String prizeItemNo, Date expiryDate, String mallFlag) throws Exception {
		LvAccountPrize prize = new LvAccountPrize();
		prize.setActivityCode(lotteryCode);
		prize.setUserCode(userCode);
		prize.setPrizeName(prizeName);
		prize.setPrizeType((short) prizeType);
		prize.setPrizeRelCode(prizeItemNo);
		prize.setEndTicketDate(expiryDate);
		Date curDate =  new Date();
		prize.setWinDate(curDate);
		prize.setCode(CodeUtils.getCode());
		prize.setMallFlag(mallFlag);
		//若奖品是优惠券,则先领取优惠券,再兑奖
		switch (prizeType) {
		case LotteryConstant.PRIZE_COUPON_TYPE:
			prize.setStatus((short) LotteryConstant.PRIZE_STATUS_SUBMIT);
			prize.setTicketDate(curDate);
			//获得领取的优惠券号
			String couponNo = obtainCouponFromCache(lotteryCode, prizeItemNo);
			prize.setPrizeCode(couponNo);
			//更新优惠券及优惠券码
			couponService.obtainCouponByLottery(couponNo);
			break;
		case LotteryConstant.PRIZE_MATERIAL_TYPE:
			prize.setStatus((short) LotteryConstant.PRIZE_STATUS_NOT);
			break;
		}
		lvlogicWriteDao.save(prize);
		return prize.getCode();
	}
	/**
	 * 从缓存中获取抽奖特定奖项的一个可使用优惠券
	 * @param lotteryCode
	 * @param lotteryItem
	 * @return
	 * @throws Exception 
	 */
	private String obtainCouponFromCache(String lotteryCode, String lotteryItem) throws Exception {
		String code = null;// 优惠券业务编码

		Jedis jedis = null;
		JedisPool jedisPool = null;
		try {
			jedisPool = RedisClient.getInstance().getJedisPool(RedisConstant.getRedisPoolConfigAC());
			jedis = jedisPool.getResource();
			String activityKey = ActivityConstant.getLotteryCouponKey(lotteryItem);
			Set<String> codeSet = jedis.smembers(activityKey);// 获取该活动所有优惠券业务编码SET
			if (CollectionUtils.isNotEmpty(codeSet)) {
				// 使用随机取值策略，减少资源竞争。
				List<String> codeList = new ArrayList<String>();
				codeList.addAll(codeSet);
				int listSize = codeList.size();
				int index = (int) (Math.random() * listSize);// 生成List随机索引
				for (int i = 0; i < listSize; i++) {
					String tempCode = codeList.get(index);
					long s = jedis.srem(activityKey, tempCode);// 状态码，成功返回1，成员不存在返回0
					if (s == 1) {
						code = tempCode;
						break;
					} else if (s == 0) {
						log.error("活动：" + lotteryCode + "," + "竞争获取优惠券失败，继续获取下一张！");
					}
				}
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		} finally {
			RedisClient.closeRedis(jedisPool, jedis);
		}
		return code;
	}
	@Override
	public LvAccountPhysicalTicket getMaterialDeliver(String prizeCode) {
		return (LvAccountPhysicalTicket) lvlogicReadDao.findUniqueByProperty(LvAccountPhysicalTicket.class, "accountPrizeCode", prizeCode);
	}

	@Override
	public String addDeliver(String addrCode, String userCode, String prizeCode) {
		String hql = "from LvAccountPrize where userCode=:userCode and code=:code";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userCode", userCode);
		param.put("code", prizeCode);
		LvAccountPrize prize = (LvAccountPrize) lvlogicReadDao.findUnique(hql, param);
		if (ObjectUtils.isEmpty(prize)) {
			log.error("没有找到兑奖的奖品");
			return null;
		}
		if (LotteryConstant.PRIZE_STATUS_NOT != prize.getStatus() || System.currentTimeMillis() > prize.getEndTicketDate().getTime()) {
			log.error("奖品已经兑奖或者已经超期");
			return null;
		}
		LvAccountPhysicalTicket ticket = getMaterialDeliver(prizeCode);
		if (null != ticket) {
			log.error("奖品已经填写寄送地址");
			return ticket.getId().toString();
		}
		Dto dto = new BaseDto();
		dto.put("userCode", userCode);
		dto.put("addressCode", addrCode);
		LvAccountAddress address = addressService.getAddressByCode(dto);
		if (null == address) {
			log.error("指定地址不存在");
			return null;
		}
		ticket = new LvAccountPhysicalTicket();
		ticket.setAccountPrizeCode(prizeCode);
		ticket.setRelName(address.getRelName());
		ticket.setRelName(address.getRelName());
		ticket.setContryId(address.getContryId());
		ticket.setContryName(address.getContryName());
		ticket.setProvinceId(address.getProvinceId());
		ticket.setProvinceName(address.getProvinceName());
		ticket.setCityId(address.getCityId());
		ticket.setCityName(address.getCityName());
		ticket.setAdress(address.getAdress());
		ticket.setMobile(address.getMobile());
		ticket.setTel(address.getTel());
		ticket.setPostCode(address.getPostCode());
		lvlogicWriteDao.save(ticket);
		//更新奖品状态
		hql = "update LvAccountPrize set status=:status where code=:code";
		param = new HashMap<String, Object>();
		param.put("status", (short)LotteryConstant.PRIZE_STATUS_SUBMIT);
		param.put("code", prizeCode);
		lvlogicWriteDao.update(hql, param);
		return ticket.getId().toString();
	}

}
