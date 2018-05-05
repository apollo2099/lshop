package com.lshop.web.accountAddress.component;

import org.springframework.data.redis.core.HashOperations;

import com.lshop.common.pojo.user.LvAccountAddress;
import com.lshop.common.redis.RedisOperation;

public interface AddressCacheComponent extends RedisOperation<String, LvAccountAddress>, HashOperations<String, String, LvAccountAddress>{

}
