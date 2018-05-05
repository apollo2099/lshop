package com.lshop.web.order.component.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.lshop.common.redis.RedisKeyConstant;
import com.lshop.common.redis.ValueRedisDaoSupport;
import com.lshop.web.order.component.OrderCacheDao;
import com.lshop.web.order.vo.OrderCahce;
@Component
public class OrderCacheDaoImpl extends ValueRedisDaoSupport<String, OrderCahce> implements OrderCacheDao {

}
