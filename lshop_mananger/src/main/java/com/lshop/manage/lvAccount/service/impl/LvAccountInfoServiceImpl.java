package com.lshop.manage.lvAccount.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.gv.core.util.StringUtil;

import com.lshop.common.pojo.user.LvAccount;
import com.lshop.common.pojo.user.LvAccountInfo;
import com.lshop.manage.lvAccount.service.LvAccountInfoService;

/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvAccount.service.impl.lvAccountInfoServiceImpl.java]  
 * @ClassName:    [lvAccountInfoServiceImpl]   
 * @Description:  [用户登录信息表-数据访问层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-2 下午03:29:16]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-2 下午03:29:16]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0] 
 *
 */
@Service("LvAccountInfoService")
public class LvAccountInfoServiceImpl implements LvAccountInfoService {
	private static final Log logger	= LogFactory.getLog(LvAccountInfoServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	
	@Override
	public Pagination getList(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountServiceImpl.getList() method end*****");
		}
		String flag=dto.getAsString("flag");//店铺标识
		String orderField = dto.getAsString("orderField");
		String orderDirection = dto.getAsString("orderDirection");
		SimplePage page = (SimplePage)dto.get("page");
		LvAccountInfo lvAccountInfo=(LvAccountInfo) dto.get("lvAccountInfo");
		String hql="from LvAccount where 1=1 ";
//		if(ObjectUtils.isNotEmpty(flag)){
//			hql+=" and storeId='"+flag+"'";
//		}
		if(!StringUtil.IsNullOrEmpty(orderField)&&!StringUtil.IsNullOrEmpty(orderDirection)){
			hql+=" order by "+ orderField+" "+orderDirection;
		}
	
		return dao.find(Finder.create(hql), page.getPageNum(), page.getNumPerPage());
	}

	
	@Override
	public void delete(Dto dto) throws ServiceException {
		
	}
	
	@Override
	public Dto save(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @Method: getAccountInfo 
	 * @Description:  [根据用户code查询用户信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-2 下午03:30:18]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-2 下午03:30:18]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public LvAccountInfo getAccountInfo(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountServiceImpl.getAccountInfo() method begin*****");
		}
		String usercode=dto.getAsString("usercode");
		LvAccountInfo lvAccountInfo= (LvAccountInfo) dao.findUniqueByProperty(LvAccountInfo.class, "userCode", usercode);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountServiceImpl.getAccountInfo() method end*****");
		}
		return lvAccountInfo;
	}


	/**
	 * 
	 * @Method: update 
	 * @Description:  [更新用户登录信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-2 下午03:30:23]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-2 下午03:30:23]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public void update(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountServiceImpl.update() method begin*****");
		}
		LvAccountInfo lvAccountInfo=(LvAccountInfo) dto.get("lvAccountInfo");
		dao.update(lvAccountInfo);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountServiceImpl.update() method end*****");
		}
	}

}
