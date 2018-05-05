package com.lshop.web.tvpadTopic.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvDealerApplication;
import com.lshop.common.pojo.logic.LvServiceNetwork;
import com.lshop.web.tvpadTopic.service.TvpadTopicService;

/**
 * 专题模块
 * @author zhengxue
 *
 */
@Service("TvpadTopicService")
public class TvpadTopicServiceImpl implements TvpadTopicService {
	
	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;

	/**
	 * 获取服务网点列表
	 */
	@Override
	public List<LvServiceNetwork> getNetworkList(Dto dto)
			throws ServiceException {
		
		String hql="from LvServiceNetwork where storeId=:storeFlag order by orderValue desc";
		HashMap param=new HashMap();
		param.put("storeFlag", dto.getAsString("storeFlag"));
		List<LvServiceNetwork> networkList = (List<LvServiceNetwork>)lvlogicReadDao.find(hql, param);
		return networkList;
	}

	/**
	 * 根据ID获取LvArea
	 */
	@Override
	public LvArea getAreaById(Dto dto) throws ServiceException {
		
		LvArea area=(LvArea)lvlogicReadDao.findUniqueByProperty(LvArea.class, "id", dto.getAsInteger("areaId"));
		return area;
	}

	/**
	 * 保存经销商申请信息
	 */
	@Override
	public Boolean saveApplication(Dto dto) {
		
		LvDealerApplication app = (LvDealerApplication)dto.getDefaultPo();
		lvlogicWriteDao.save(app);
		return null;
	}

}
