package com.lshop.manage.lvArea.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.util.RedisClient;
import com.lshop.manage.lvArea.common.AreaConstant;
import com.lshop.manage.lvArea.service.AreaRedisServer;
import com.lshop.manage.lvArea.service.LvAreaService;


@Service("AreaRedisServer")
public class AreaRedisServerImpl implements AreaRedisServer {
	private static final Log logger = LogFactory.getLog(AreaRedisServerImpl.class);
	@Resource
	private LvAreaService lvAreaService;

	public void initArea(){
		logger.info("=================初始化区域缓存数据 开始===========================");
		BaseDto dto = new BaseDto();
		List<LvArea> areaList=lvAreaService.getAllArea(dto);
		if (ObjectUtils.isNotEmpty(areaList)) {
			logger.info("=================区域总条数:"+areaList.size()+"===========================");
			for (LvArea lvArea : areaList) {
				if (ObjectUtils.isNotEmpty(lvArea.getId())) {
					// 设置优惠券码数据到缓存中
					try {
						String couponKey=AreaConstant.getAreaKey(lvArea.getId().toString());
						cacheAreaData(couponKey,lvArea.getNameen());
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}
			}
		}
		logger.info("=================初始化区域缓存数据 结束===========================");
	}
	
	//设置redis缓存客户端调用对象
	public synchronized void  cacheAreaData(String areaKey,String areaValue){
		Jedis jedis = null;
		try {
			jedis = RedisClient.getRedis();
			if(!jedis.exists(areaKey)){
			   // 更新优惠码缓存
			   jedis.sadd(areaKey, areaValue);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			RedisClient.closeRedis(jedis);
		}
	}
	
	

}
