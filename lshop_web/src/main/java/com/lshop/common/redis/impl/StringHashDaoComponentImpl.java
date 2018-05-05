package com.lshop.common.redis.impl;

import org.springframework.stereotype.Component;

import com.lshop.common.redis.HashRedisDaoSupport;
import com.lshop.common.redis.StringHashDaoComponent;

@Component
public class StringHashDaoComponentImpl extends HashRedisDaoSupport<String, String, String> implements StringHashDaoComponent{

}
