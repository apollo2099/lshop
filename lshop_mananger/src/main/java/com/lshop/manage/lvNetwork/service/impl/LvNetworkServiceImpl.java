package com.lshop.manage.lvNetwork.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvNetwork;
import com.lshop.manage.lvNetwork.service.LvNetworkService;

/**
 * 服务网点
 * @author zhengxue
 *
 */
@Service("LvNetworkService")
public class LvNetworkServiceImpl extends BaseServiceImpl implements LvNetworkService {
	 
	 private static final Log logger	= LogFactory.getLog(LvNetworkServiceImpl.class);
	 @Resource private HibernateBaseDAO dao;
	 
	/**
	 * 分页查询
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	 @Override
	public Pagination findPage(Dto dto) throws ServiceException {
		 
		if (logger.isInfoEnabled()){
			logger.info("***** LvNetworkServiceImpl.findPage() method begin*****");
		}
		
		Pagination page = dto.getDefaultPage();
		LvNetwork lvNetwork = (LvNetwork)dto.getDefaultPo();
		HashMap param = new HashMap();
		
		String hql = "from LvNetwork where 1=1";
		if(ObjectUtils.isNotEmpty(lvNetwork)){
			if(ObjectUtils.isNotEmpty(lvNetwork.getStoreId())){
				hql += " and storeId=:storeId";
				param.put("storeId", lvNetwork.getStoreId());
			}
			if(ObjectUtils.isNotEmpty(lvNetwork.getContryId())){
				hql += " and contryId=:contryId";
				param.put("contryId", lvNetwork.getContryId());
			}
		}
		
		hql += " order by orderValue desc";
		
		page = dao.find(Finder.create(hql).setParams(param), page.getPageNum(), page.getNumPerPage());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvNetworkServiceImpl.findPage() method end*****");
		}
		return page;
	}
	 
	/**
	 * 获得单独的实体信息
	 */
	 @Override
	public LvNetwork get(Dto dto) throws ServiceException {
		 
		if (logger.isInfoEnabled()) {
			logger.info("***** LvNetworkServiceImpl.get() method begin*****");
		}
		LvNetwork lvNetwork = (LvNetwork)dto.getDefaultPo();
		lvNetwork = (LvNetwork) dao.load(LvNetwork.class,lvNetwork.getId());

		if (logger.isInfoEnabled()) {
			logger.info("***** LvNetworkServiceImpl.get() method end*****");
		}
		return lvNetwork;
	}
	 
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	 @Override
	public void del(Dto dto) throws ServiceException {
		 
		if (logger.isInfoEnabled()) {
			logger.info("***** LvNetworkServiceImpl.del() method begin*****");
		}
		
		LvNetwork lvNetwork = (LvNetwork)dto.getDefaultPo();
		dao.delete(lvNetwork);
		
		if (logger.isInfoEnabled()) {
			logger.info("***** LvNetworkServiceImpl.del() method end*****");
		}
	}


	/**
	 * 保存
	 */
	 @Override
	public LvNetwork save(Dto dto) throws ServiceException {
		 
		if (logger.isInfoEnabled()) {
			logger.info("***** LvNetworkServiceImpl.save() method begin*****");
		}
		
		LvNetwork lvNetwork = (LvNetwork)dto.getDefaultPo();
		dao.save(lvNetwork);

		if (logger.isInfoEnabled()) {
			logger.info("***** LvNetworkServiceImpl.save() method begin*****");
		}
		return lvNetwork;
	}
	 
	/**
	 * 更新
	 */
	 @Override
	public LvNetwork update(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()) {
			logger.info("***** LvNetworkServiceImpl.update() method begin*****");
		}
		LvNetwork lvNetwork = (LvNetwork)dto.getDefaultPo();
		dao.update(lvNetwork);

		if (logger.isInfoEnabled()) {
			logger.info("***** LvNetworkServiceImpl.update() method begin*****");
		}
		return lvNetwork;
	}

}
