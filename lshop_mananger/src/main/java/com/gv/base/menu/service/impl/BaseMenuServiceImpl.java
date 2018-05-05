package com.gv.base.menu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.base.menu.common.pojo.BaseMenu;
import com.gv.base.menu.service.BaseMenuService;
import com.gv.base.resources.service.BaseResourcesService;
import com.gv.base.springSecurity.common.pojo.BaseResources;
import com.gv.base.util.BaseUtil;
import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.ObjectUtils;
@Service("BaseMenuService")
public class BaseMenuServiceImpl extends BaseServiceImpl implements BaseMenuService{
	private static final Log logger	= LogFactory.getLog(BaseMenuServiceImpl.class);
	@Resource private HibernateBaseDAO dao;
	@Resource private BaseResourcesService baseResourcesService;
	/**
	 * 
	 * 功能描述：   获取所有菜单
	 * 创建人：yangzheng  
	 * 创建时间：2012-2-28 下午01:16:03   
	 * 修改人：yangzheng   
	 * 修改时间：2012-2-28 下午01:16:03   
	 * 修改备注：
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<BaseMenu> getAllMenu(Dto dto) throws ServiceException{
		if (logger.isInfoEnabled()){
			logger.info("***** BaseMenuServiceImpl.getAllMenu() method begin*****");
		}		

		String hql = "from BaseMenu where status=1 order by menuDisorder ASC";
		List<BaseMenu> menuList = dao.find(hql, null);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseMenuServiceImpl.getAllMenu() method end*****");
		}	
	    return menuList;
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
			logger.info("***** BaseMenuServiceImpl.delete() method begin*****");
		}	
		
		String menuCode = dto.getAsString("menuCode");
		String hql = "update from BaseMenu set status = -1 where menuCode = '"+menuCode+"'";
		dao.update(hql,null);
		
		dto.put("resourceCode", menuCode);
		
		BaseResources baseResources = baseResourcesService.get(dto);
		dto.put("ids", baseResources.getId());
		baseResourcesService.delete(dto);

		if (logger.isInfoEnabled()){
			logger.info("***** BaseMenuServiceImpl.delete() method end*****");
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
	public void save(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseMenuServiceImpl.save() method begin*****");
		}		

		BaseMenu baseMenu = (BaseMenu) dto.get("baseMenu");
		String menuCode = BaseUtil.getCode("MENU");
		baseMenu.setMenuCode(menuCode);
		baseMenu.setStatus(1);
		dao.save(baseMenu);
		BaseResources baseResources = new BaseResources();
		baseResources.setResourceCode(menuCode);
		baseResources.setResourceName(baseMenu.getMenuName());
		baseResources.setResourceDesc(baseMenu.getMenuName());
		
		if (ObjectUtils.isNotEmpty(baseMenu.getMenuUrl())) {
			String url=baseMenu.getMenuUrl();
			Integer i= url.indexOf("?");
			if(i>0){
				baseResources.setResourceString(url.substring(0, i)+"?json.navTabId="+menuCode);	
			}
		}else{
			baseResources.setResourceString(menuCode);
		}
		baseResources.setResourceType(1);
		dto.put("baseResources", baseResources);
		baseResourcesService.save(dto);
		
		
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseMenuServiceImpl.save() method end*****");
		}	
	}

	/**
	 * 
	 * 功能描述： 获取单个菜单信息
	 * 创建人：yangzheng  
	 * 创建时间：2012-3-19 上午10:39:51   
	 * 修改人：yangzheng   
	 * 修改时间：2012-3-19 上午10:39:51   
	 * 修改备注：
	 * @param dto
	 * @throws ServiceException
	 */
	public BaseMenu get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseMenuServiceImpl.get() method get*****");
		}		

		String menuCode = dto.getAsString("menuCode");
		BaseMenu baseMenu = (BaseMenu) dao.findUniqueByProperty(BaseMenu.class, "menuCode", menuCode);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseMenuServiceImpl.get() method get*****");
		}	
		
		return baseMenu;
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
			logger.info("***** BaseMenuServiceImpl.update() method begin*****");
		}		

		BaseMenu baseMenu = (BaseMenu) dto.get("baseMenu");
		dao.update(baseMenu);
		BaseResources baseResources = (BaseResources) dao.findUniqueByProperty(BaseResources.class, "resourceCode", baseMenu.getMenuCode());
		baseResources.setResourceName(baseMenu.getMenuName());
		if (ObjectUtils.isNotEmpty(baseMenu.getMenuUrl())) {
			String url=baseMenu.getMenuUrl();
			Integer i= url.indexOf("?");
			if(i>0){
				baseResources.setResourceString(url.substring(0, i)+"?json.navTabId="+baseMenu.getMenuCode());	
			}
		}
		dto.put("baseResources", baseResources);
		baseResourcesService.update(dto);
		if (logger.isInfoEnabled()){
			logger.info("***** BaseMenuServiceImpl.update() method end*****");
		}	
	}
}
