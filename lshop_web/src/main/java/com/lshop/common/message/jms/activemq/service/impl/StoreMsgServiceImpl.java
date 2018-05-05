package com.lshop.common.message.jms.activemq.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.core.util.ObjectUtils;
import com.gv.html.component.HtmlComponent;
import com.gv.html.core.HtmlEngine;
import com.lshop.common.activity.vo.ActivityLink;
import com.lshop.common.cache.component.CacheComponent;
import com.lshop.common.message.jms.activemq.common.StoreMsgUtil;
import com.lshop.common.message.jms.activemq.service.StoreMsgService;
import com.lshop.common.pojo.logic.LvActivityLink;
import com.lshop.common.redis.RedisKeyConstant;
import com.lshop.common.util.Constants;
import com.lshop.common.util.DateUtils;
import com.lshop.web.activity.service.ActivityService;
import com.lshop.web.shopCart.service.ShopCartService;

@Service("StoreMsgService")
public class StoreMsgServiceImpl implements StoreMsgService {
	private static final Log logger = LogFactory.getLog(StoreMsgServiceImpl.class);

	@Resource
	private ActivityService activityService;
	@Resource
	private HtmlEngine htmlEngine;
	@Resource
	private HtmlComponent htmlComponent;
	@Resource
	private CacheComponent cacheComponent;
	@Resource
	private ShopCartService shopCartService;

	@Override
	public void processStoreMSGToWEB(String msg) {
		if (logger.isInfoEnabled()) {
			logger.info("***** StoreMsgServiceImpl.processStoreMSGToWEB begin*****");
			logger.info("***** getMessage=>" + msg);
		}
		try {
			JSONObject jsonObject = JSONObject.fromObject(msg);
			Float rateNum = 0.0f;
			Float rateNumCNY = 0.0f;
			Float rateVbNum=0.0f;
			Float rateVbNumCny=0.0f;
			Map<String, String> STORE_IDS = null;
			Map<String, String> STORE_LIST = null;
			Map<String, String> STORE_LIST_ALL = null;
			Map<String, String> STORE_TO_MALL_SYSTEM = null;
			Map<String, Integer> STORE_FLAG_TO_IDS = null;
			Map<String, String> MALL_TO_DOMAIN_POSTFIX = null;
			Map<String, List<String>> MALL_SYSTEM_TO_STORE = null;
			Map<String, String> STORE_TO_CURRENCY = null;
			Map<String, List<LvActivityLink>> ACTIVITY_LINK = null;
			if (jsonObject.containsKey("rateNum")) {// 美元兑人民币汇率
				if (ObjectUtils.isNotEmpty(jsonObject.get("rateNum"))) {
					rateNum = Float.parseFloat(jsonObject.get("rateNum").toString());
					Constants.rateNum = rateNum;
				}
			}
			if (jsonObject.containsKey("rateNumCNY")) {// 人民币兑美元汇率
				if (ObjectUtils.isNotEmpty(jsonObject.get("rateNumCNY"))) {
					rateNumCNY = Float.parseFloat(jsonObject.get("rateNumCNY").toString());
					Constants.rateNumCNY = rateNumCNY;
				}
			}
			if (jsonObject.containsKey("rateVbNum")) {//V币对美元汇率信息
				if (ObjectUtils.isNotEmpty(jsonObject.get("rateVbNum"))) {
					rateVbNum = Float.parseFloat(jsonObject.get("rateVbNum").toString());
					Constants.rateVbNum = rateVbNum;
				}
			}
			if (jsonObject.containsKey("rateVbNumCny")) {// //V币对人民币汇率信息
				if (ObjectUtils.isNotEmpty(jsonObject.get("rateVbNumCny"))) {
					rateVbNumCny = Float.parseFloat(jsonObject.get("rateVbNumCny").toString());
					Constants.rateVbNumCny = rateVbNumCny;
				}
			}
	
			
			if (jsonObject.containsKey("STORE_IDS")) {// 店铺信息(域名对应店铺标志)
				if (ObjectUtils.isNotEmpty(jsonObject.getJSONObject("STORE_IDS"))) {
					STORE_IDS = StoreMsgUtil.processStoreMSGString(jsonObject.getJSONObject("STORE_IDS"));
					Constants.STORE_IDS.clear();
					Constants.STORE_IDS = STORE_IDS;
				}
			}
			if (jsonObject.containsKey("STORE_LIST")) {// 商城对应下级店铺列表集合(只是包含启用店铺)
				if (ObjectUtils.isNotEmpty(jsonObject.getJSONObject("STORE_LIST"))) {
					STORE_LIST = StoreMsgUtil.processStoreMSGString(jsonObject.getJSONObject("STORE_LIST"));
					Constants.STORE_LIST.clear();
					Constants.STORE_LIST = STORE_LIST;
				}
			}
			if (jsonObject.containsKey("STORE_LIST_ALL")) {// 商城对应下级店铺列表集合
				if (ObjectUtils.isNotEmpty(jsonObject.getJSONObject("STORE_LIST_ALL"))) {
					STORE_LIST_ALL = StoreMsgUtil.processStoreMSGString(jsonObject.getJSONObject("STORE_LIST_ALL"));
					Constants.STORE_LIST_ALL.clear();
					Constants.STORE_LIST_ALL = STORE_LIST_ALL;
				}
			}
			if (jsonObject.containsKey("STORE_TO_MALL_SYSTEM")) {// 商城体系信息(店铺标志对应商城体系标志)
				if (ObjectUtils.isNotEmpty(jsonObject.getJSONObject("STORE_TO_MALL_SYSTEM"))) {
					STORE_TO_MALL_SYSTEM = StoreMsgUtil.processStoreMSGString(jsonObject.getJSONObject("STORE_TO_MALL_SYSTEM"));
					Constants.STORE_TO_MALL_SYSTEM.clear();
					Constants.STORE_TO_MALL_SYSTEM = STORE_TO_MALL_SYSTEM;
				}
			}
			if (jsonObject.containsKey("STORE_FLAG_TO_IDS")) {// 店铺标志对于店铺id信息
				if (ObjectUtils.isNotEmpty(jsonObject.getJSONObject("STORE_FLAG_TO_IDS"))) {
					STORE_FLAG_TO_IDS = StoreMsgUtil.processStoreMSGInteger(jsonObject.getJSONObject("STORE_FLAG_TO_IDS"));
					Constants.STORE_FLAG_TO_IDS.clear();
					Constants.STORE_FLAG_TO_IDS = STORE_FLAG_TO_IDS;
				}
			}
			if (jsonObject.containsKey("MALL_TO_DOMAIN_POSTFIX")) {// 商城体系标示对应商城体系后缀域名信息
				if (ObjectUtils.isNotEmpty(jsonObject.getJSONObject("MALL_TO_DOMAIN_POSTFIX"))) {
					MALL_TO_DOMAIN_POSTFIX = StoreMsgUtil.processStoreMSGString(jsonObject.getJSONObject("MALL_TO_DOMAIN_POSTFIX"));
					Constants.MALL_TO_DOMAIN_POSTFIX.clear();
					Constants.MALL_TO_DOMAIN_POSTFIX = MALL_TO_DOMAIN_POSTFIX;
				}
			}
			if (jsonObject.containsKey("MALL_SYSTEM_TO_STORE")) {// 商城体系标志对应商城店铺标志
				if (ObjectUtils.isNotEmpty(jsonObject.getJSONObject("MALL_SYSTEM_TO_STORE"))) {
					MALL_SYSTEM_TO_STORE = StoreMsgUtil.processStoreMSGList(jsonObject.getJSONObject("MALL_SYSTEM_TO_STORE"));
					Constants.MALL_SYSTEM_TO_STORE.clear();
					Constants.MALL_SYSTEM_TO_STORE = MALL_SYSTEM_TO_STORE;
				}
			}

			if (jsonObject.containsKey("STORE_TO_CURRENCY")) {// 店铺标志对于币种关系
				if (ObjectUtils.isNotEmpty(jsonObject.getJSONObject("STORE_TO_CURRENCY"))) {
					STORE_TO_CURRENCY = StoreMsgUtil.processStoreMSGString(jsonObject.getJSONObject("STORE_TO_CURRENCY"));
					Constants.STORE_TO_CURRENCY.clear();
					Constants.STORE_TO_CURRENCY = STORE_TO_CURRENCY;
				}
			}
			if (jsonObject.has("ACTIVITYLINKS")) {// 修改点击连接就送活动信息
				if (ObjectUtils.isNotEmpty(jsonObject.get("ACTIVITYLINKS"))) {
					JSONArray activityLinkList = (JSONArray) jsonObject.get("ACTIVITYLINKS");
					for (int i = 0; i < activityLinkList.size(); i++) {
						ActivityLink activitylink = new ActivityLink();
						JSONObject temp = activityLinkList.getJSONObject(i);
						if (ObjectUtils.isNotEmpty(temp.get("activityCode"))) {
							activitylink.setActivityCode(String.valueOf(temp.get("activityCode")));
						}
						if (ObjectUtils.isNotEmpty(temp.get("limitNum"))) {
							activitylink.setLimitNum(Integer.valueOf(temp.get("limitNum").toString()));
						}
						if (ObjectUtils.isNotEmpty(temp.get("givenProductCode"))) {
							activitylink.setGivenProductCode(String.valueOf(temp.get("givenProductCode")));
						}
						if (ObjectUtils.isNotEmpty(temp.get("grantTotal"))) {
							activitylink.setGrantTotal(Integer.valueOf(temp.get("grantTotal").toString()));
						}
						if (ObjectUtils.isNotEmpty(temp.get("uncollectedTotal"))) {
							activitylink.setUncollectedTotal(Integer.valueOf(temp.get("uncollectedTotal").toString()));
						}
						if (ObjectUtils.isNotEmpty(temp.get("startTime"))) {
							activitylink.setStartTime(DateUtils.parseDateTime(temp.get("startTime").toString(), "yyyy-MM-dd HH:mm:ss"));
						}
						if (ObjectUtils.isNotEmpty(temp.get("endTime"))) {
							activitylink.setEndTime(DateUtils.parseDateTime(temp.get("endTime").toString(), "yyyy-MM-dd HH:mm:ss"));
						}
						if (ObjectUtils.isNotEmpty(temp.get("activityTitle"))) {
							activitylink.setActivityTitle(String.valueOf(temp.get("activityTitle")));
						}
						if (ObjectUtils.isNotEmpty(temp.get("status"))) {
							activitylink.setStatus(Integer.valueOf(temp.get("status").toString()));
						}
						if(ObjectUtils.isNotEmpty(temp.get("strategyType"))){
							activitylink.setStrategyType(Integer.valueOf(temp.get("strategyType").toString()));	
						}

						// 将当前点击链接就送活动写入缓存
						activityService.updateLinkActivityMap(activitylink);
					}
				}
			}
			if(jsonObject.has("orderId")&&jsonObject.has("userCode")&&jsonObject.has("productList")){//改变限量活动数目和赠送优惠券
				String orderId=jsonObject.getString("orderId");
				String userCode=jsonObject.getString("userCode");
				JSONArray productList = (JSONArray) jsonObject.get("productList");
				Map<String, Integer> productQuantity=new HashMap<String, Integer>();
				for (int i = 0; i < productList.size(); i++) {
					JSONObject temp = productList.getJSONObject(i);
					String productCode=temp.getString("productCode");
					Integer punm=temp.getInt("pnum");
					productQuantity.put(productCode, punm);
				}
				if(ObjectUtils.isNotEmpty(orderId)&&ObjectUtils.isNotEmpty(userCode)&&ObjectUtils.isNotEmpty(productQuantity)){
					activityService.finishOrderActivity(orderId, userCode, productQuantity);
				}
				
			}
			if(jsonObject.has("INIT_LINK_STORE_IDS")){//发送静态化连接店铺请求
				JSONArray initLinkStoreIds = (JSONArray) jsonObject.get("INIT_LINK_STORE_IDS");
				String[] storeIdArr=new String[initLinkStoreIds.size()];
				for (int i = 0; i < initLinkStoreIds.size(); i++) {
					JSONObject temp = initLinkStoreIds.getJSONObject(i);
					String storeId=temp.getString("storeId");
					storeIdArr[i]=storeId;
				}
				//执行静态化请求
				htmlEngine.asynchronExcute(storeIdArr);
			}
			if(jsonObject.has("storeId")&&jsonObject.has("htmlPath")){//单个页面执行静态化请求
				String storeId=jsonObject.getString("storeId");
				String htmlPath=jsonObject.getString("htmlPath");
				htmlComponent.asyncProcessHtml(htmlPath, storeId);
			}
			
			/**
			 * 刷新商城配置
			 */
			if (jsonObject.containsKey("configParam")) {
				if (ObjectUtils.isNotEmpty(jsonObject.getJSONObject("configParam"))) {
					JSONObject obj = jsonObject.getJSONObject("configParam");
					Constants.sysConfigParam = StoreMsgUtil.processConfigParam(obj,Constants.baseDomain);
				}
			}
			//广告信息缓存
			if (jsonObject.containsKey("AD_CHANGE")) {
				String adid = jsonObject.getString("AD_CHANGE");
				cacheComponent.clearCache(RedisKeyConstant.ADKey(adid));
			}
			//运费缓存
			if (jsonObject.containsKey("CARRIAGE_CHANGE")) {
				String storeId=jsonObject.getString("storeId");
				String storeDeliver = jsonObject.getString("CARRIAGE_CHANGE");
				shopCartService.reloadCarriageCache(storeId, Integer.valueOf(storeDeliver));
			}
			//支付方式缓存
			if (jsonObject.containsKey("PAYMENT_CHANGE")) {
				String storeId=jsonObject.getString("storeId");
				String payment = jsonObject.getString("PAYMENT_CHANGE");
				shopCartService.reloadPaymentCache(storeId, payment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (logger.isInfoEnabled()) {
				logger.info("***** StoreMsgServiceImpl.processStoreMSGToWEB end*****");
			}
		}

	}

}
