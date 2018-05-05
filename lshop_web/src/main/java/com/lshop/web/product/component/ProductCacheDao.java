/**
 * 
 */
package com.lshop.web.product.component;

import org.springframework.data.redis.core.ValueOperations;

import com.lshop.common.redis.RedisOperation;
import com.lshop.web.product.vo.ProdCache;

/**
 * @author caicl
 *
 */
public interface ProductCacheDao extends ValueOperations<String, ProdCache>, RedisOperation<String, ProdCache> {

}
