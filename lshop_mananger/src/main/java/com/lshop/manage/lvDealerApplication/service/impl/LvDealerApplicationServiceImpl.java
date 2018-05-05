package com.lshop.manage.lvDealerApplication.service.impl;

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
import com.lshop.common.pojo.logic.LvDealerApplication;
import com.lshop.manage.lvDealerApplication.service.LvDealerApplicationService;

/**
 * 经销商申请
 * @author zhengxue
 *
 */
@Service("LvDealerApplicationService")
public class LvDealerApplicationServiceImpl extends BaseServiceImpl implements LvDealerApplicationService {
	 
	 private static final Log logger	= LogFactory.getLog(LvDealerApplicationServiceImpl.class);
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
			logger.info("***** LvDealerApplicationServiceImpl.findPage() method begin*****");
		}
		
		Pagination page = dto.getDefaultPage();
		LvDealerApplication application = (LvDealerApplication)dto.getDefaultPo();
		HashMap param = new HashMap();
		
		String hql = "from LvDealerApplication where 1=1";
		if(ObjectUtils.isNotEmpty(application)){
			if(ObjectUtils.isNotEmpty(application.getApplyCmp())){
				hql += " and applyCmp like :applyCmp";
				param.put("applyCmp", "%"+application.getApplyCmp().trim()+"%");
			}
			if(ObjectUtils.isNotEmpty(application.getApplyEmail())){
				hql += " and applyEmail like :applyEmail";
				param.put("applyEmail", "%"+application.getApplyEmail().trim()+"%");
			}
		}
		
		page = dao.find(Finder.create(hql).setParams(param), page.getPageNum(), page.getNumPerPage());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvDealerApplicationServiceImpl.findPage() method end*****");
		}
		return page;
	}
	 
	/**
	 * 获得单独的实体信息
	 */
	 @Override
	public LvDealerApplication get(Dto dto) throws ServiceException {
		 
		if (logger.isInfoEnabled()) {
			logger.info("***** LvDealerApplicationServiceImpl.get() method begin*****");
		}
		LvDealerApplication app = (LvDealerApplication)dto.getDefaultPo();
		app = (LvDealerApplication) dao.load(LvDealerApplication.class,app.getId());

		if (logger.isInfoEnabled()) {
			logger.info("***** LvDealerApplicationServiceImpl.get() method end*****");
		}
		return app;
	}

}
