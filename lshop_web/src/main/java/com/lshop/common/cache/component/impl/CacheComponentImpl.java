package com.lshop.common.cache.component.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.lshop.common.cache.component.CacheComponent;
import com.lshop.common.redis.RedisKeyConstant;
import com.lshop.common.util.Constants;
import com.lshop.web.shopCart.service.ShopCartService;

@Component
public class CacheComponentImpl implements CacheComponent{
	@Resource RedisTemplate<String, Object> redisTemplate;
	@Resource ShopCartService shopCartService;
	/**
	 * 基础数据缓存
	 */
	private Map<String, Object> baseDataMap = new HashMap<String, Object>();
	
	@Override
	public void initStartup() {
		// 清除运运费缓存
		clearCache(RedisKeyConstant.CarriageKey("*"));
		//载入国家列表
		shopCartService.loadCountryList();
		//重新加载所有店铺支付方式缓存
		for (Iterator<String> iterator = Constants.STORE_IN_USE.iterator(); iterator.hasNext();) {
			shopCartService.reloadPaymentCache(iterator.next());
		}
		//清除用户收货地址缓存
		clearCache(RedisKeyConstant.AccountAddressKey("*"));
	}

	@Override
	public void clearCache(String pattern) {
		Set<String> keys = redisTemplate.keys(pattern);
		if (CollectionUtils.isNotEmpty(keys)) {
			redisTemplate.delete(keys);
		}
	}

	@Override
	public Object getCache(String key) {
		return baseDataMap.get(key);
	}

	@Override
	public void setCache(String key, Object value) {
		synchronized (baseDataMap) {
			baseDataMap.put(key, value);
		}
	}

	@Override
	public boolean clearMutableCacle() {
		//清空页面片断缓存
		clearCache(RedisKeyConstant.FrameKey("*"));
		return true;
	}
}
