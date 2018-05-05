package com.lshop.web.shopCart.component;

import org.springframework.data.redis.core.HashOperations;

import com.lshop.common.pojo.logic.LvPaymentStyle;
import com.lshop.common.redis.RedisOperation;

public interface PaymentCacheDao extends HashOperations<String, String, LvPaymentStyle>, RedisOperation<String, LvPaymentStyle>{

}
