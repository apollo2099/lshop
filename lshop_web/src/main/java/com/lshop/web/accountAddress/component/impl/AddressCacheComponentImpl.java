package com.lshop.web.accountAddress.component.impl;

import org.springframework.stereotype.Component;

import com.lshop.common.pojo.user.LvAccountAddress;
import com.lshop.common.redis.HashRedisDaoSupport;
import com.lshop.web.accountAddress.component.AddressCacheComponent;

@Component
public class AddressCacheComponentImpl extends HashRedisDaoSupport<String, String, LvAccountAddress> implements AddressCacheComponent{

}
