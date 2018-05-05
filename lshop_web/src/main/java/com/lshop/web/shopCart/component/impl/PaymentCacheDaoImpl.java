package com.lshop.web.shopCart.component.impl;

import org.springframework.stereotype.Component;

import com.lshop.common.pojo.logic.LvPaymentStyle;
import com.lshop.common.redis.HashRedisDaoSupport;
import com.lshop.web.shopCart.component.PaymentCacheDao;
@Component
public class PaymentCacheDaoImpl extends HashRedisDaoSupport<String, String, LvPaymentStyle> implements PaymentCacheDao{

}
