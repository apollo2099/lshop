package com.lshop.web.order.component;

import java.util.List;

import org.springframework.data.redis.core.ValueOperations;

import com.lshop.common.redis.RedisOperation;
import com.lshop.web.order.vo.OrderCahce;

public interface OrderCacheDao extends ValueOperations<String, OrderCahce>, RedisOperation<String, OrderCahce>{

}
