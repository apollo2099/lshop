package com.gv.base.user.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.base.user.service.BaseUserService;
import com.gv.base.util.BaseUtil;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.call.StatusCode;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.ObjectUtils;
import com.gv.core.util.StringUtil;
@Service("BaseUserService")
public class BaseUserServiceImpl extends BaseServiceImpl implements BaseUserService{
	private static final Log logger	= LogFactory.getLog(BaseUserServiceImpl.class);
	@Resource private HibernateBaseDAO dao;
	
	
	/**
	 * 
	 * 功能描述：   分页获取信息
	 * 创建人：yangzheng  
	 * 创建时间：2011-6-10 下午04:58:19   
	 * 修改人：yangzheng   
	 * 修改时间：2011-6-10 下午04:58:19   
	 * 修改备注：
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination getList(Dto dto) throws ServiceException {
	
		if (logger.isInfoEnabled()){
			logger.info("***** BaseUserServiceImpl.getList() method begin*****");
		}		
	
		String keyword = dto.getAsString("keyword");
		String orderField = dto.getAsString("orderField");
		Integer pageNo = dto.getAsInteger("pageNo");
		Integer pageSize = dto.getAsInteger("pageSize");
		String orderDirection = dto.getAsString("orderDirection");

		String hql = "from BaseUsers where status <> -1 ";
		
		
		if (!StringUtil.IsNullOrEmpty(keyword)) {
			hql += " and userName like '%" + keyword.trim()+"%'";
		}
		if (ObjectUtils.isNotEmpty(dto.getAsString("flag"))){//店铺标示
		    hql += " and storeFlag='"+dto.getAsString("flag")+"'";
		}
		hql += " order by " + orderField+" "+orderDirection;
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseUserServiceImpl.getList() method end*****");
		}	
		return dao.find(Finder.create(hql), pageNo, pageSize);
	}
	

	
	/**
	 * 
	 * 功能描述：   删除
	 * 创建人：yangzheng  
	 * 创建时间：2012-3-19 上午10:39:42   
	 * 修改人：yangzheng   
	 * 修改时间：2012-3-19 上午10:39:42   
	 * 修改备注：
	 * @param dto
	 * @throws ServiceException
	 */
	public void delete(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseUserServiceImpl.delete() method begin*****");
		}	
		
		String ids = dto.getAsString("ids");
		String hql = "update from BaseUsers set status = -1 where id in ("+ids+")";
		dao.update(hql,null);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseUserServiceImpl.delete() method end*****");
		}	
	}
	
	/**
	 * 
	 * 功能描述：添加   
	 * 创建人：yangzheng  
	 * 创建时间：2012-3-19 上午10:39:34   
	 * 修改人：yangzheng   
	 * 修改时间：2012-3-19 上午10:39:34   
	 * 修改备注：
	 * @param dto
	 * @throws ServiceException
	 */
	public Dto save(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseUserServiceImpl.save() method begin*****");
		}		
		
		BaseUsers baseUsers = (BaseUsers) dto.get("baseUsers");
		String hql="from BaseUsers where status<>-1 and userAccount=:userAccount";
		Map param=new HashMap();
		param.put("userAccount", baseUsers.getUserAccount());
		int count = dao.countQueryResult(Finder.create(hql), param);
		
		if (count>0) {
			dto.setAppCode(StatusCode.M_Failure);
			dto.setAppMsg("改用户名已经存在!");
			return dto;
		}
		

		String userCode = BaseUtil.getCode("USER");
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		String password = md5.encodePassword("gv123456", userCode);
		baseUsers.setUserCode(userCode);
		baseUsers.setStatus(1);
		baseUsers.setUserPassword(password);
		dao.save(baseUsers);
		dto.setAppCode(StatusCode.M_Success);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseUserServiceImpl.save() method end*****");
		}	
		
		return dto;
	}

	/**
	 * 
	 * 功能描述： 获取用户信息
	 * 创建人：yangzheng  
	 * 创建时间：2012-3-19 上午10:39:51   
	 * 修改人：yangzheng   
	 * 修改时间：2012-3-19 上午10:39:51   
	 * 修改备注：
	 * @param dto
	 * @throws ServiceException
	 */
	public BaseUsers get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseUserServiceImpl.update() method get*****");
		}		

		String userCode = dto.getAsString("userCode");
		BaseUsers baseUsers = (BaseUsers) dao.findUniqueByProperty(BaseUsers.class, "userCode", userCode);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseUserServiceImpl.update() method get*****");
		}	
		
		return baseUsers;
	}
	
	/**
	 * 
	 * 功能描述：   修改
	 * 创建人：yangzheng  
	 * 创建时间：2012-3-19 上午10:39:51   
	 * 修改人：yangzheng   
	 * 修改时间：2012-3-19 上午10:39:51   
	 * 修改备注：
	 * @param dto
	 * @throws ServiceException
	 */
	public void update(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseUserServiceImpl.update() method begin*****");
		}		

		BaseUsers baseUsers = (BaseUsers) dto.get("baseUsers");
	
		dao.update(baseUsers);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseUserServiceImpl.update() method end*****");
		}	
	}
	
	/**
	 * 
	 * 功能描述：   重置密码
	 * 创建人：yangzheng  
	 * 创建时间：2012-4-24 下午06:19:27   
	 * 修改人：yangzheng   
	 * 修改时间：2012-4-24 下午06:19:27   
	 * 修改备注：
	 * @param dto
	 * @throws ServiceException
	 */
	public void updateResetPassword(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseUserServiceImpl.updateResetPassword() method begin*****");
		}		

		String userCode = dto.getAsString("userCode");
		BaseUsers baseUsers = (BaseUsers) dao.findUniqueByProperty(BaseUsers.class, "userCode", userCode);
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		String password = md5.encodePassword("gv123456", userCode);
		baseUsers.setUserPassword(password);
		dao.update(baseUsers);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseUserServiceImpl.updateResetPassword() method end*****");
		}	
	}
	
	/**
	 * 
	 * 功能描述：  修改密码 
	 * 创建人：yangzheng  
	 * 创建时间：2012-4-24 下午06:59:14   
	 * 修改人：yangzheng   
	 * 修改时间：2012-4-24 下午06:59:14   
	 * 修改备注：
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Dto updateChangePassword(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseUserServiceImpl.updateChangePassword() method begin*****");
		}		

		String userCode = dto.getAsString("userCode");
		String oldPassword = dto.getAsString("oldPassword");
		String newPassword = dto.getAsString("newPassword");
		BaseUsers baseUsers = (BaseUsers) dao.findUniqueByProperty(BaseUsers.class, "userCode", userCode);
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		oldPassword = md5.encodePassword(oldPassword, userCode);
		if (oldPassword.equals(baseUsers.getUserPassword())) {
			newPassword = md5.encodePassword(newPassword, userCode);
			baseUsers.setUserPassword(newPassword);
			dao.update(baseUsers);
			dto.setAppCode(StatusCode.M_Success);
		}else {
			dto.setAppCode(StatusCode.M_Failure);
			dto.setAppMsg("旧密码不正确!");
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseUserServiceImpl.updateChangePassword() method end*****");
		}	
		
		return dto;
	}
	
}
