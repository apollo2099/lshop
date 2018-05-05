package com.lshop.web.ad;

import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvAd;

/**
 * 广告模块
 * @author zhengxue
 * @since 2.0 2012-07-20
 *
 */
public interface AdService {
	
	/**
	 * 根据key获取其对应的广告
	 * 需要传入参数key
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<LvAd>  getAdByKey(Dto dto) throws ServiceException;
}
