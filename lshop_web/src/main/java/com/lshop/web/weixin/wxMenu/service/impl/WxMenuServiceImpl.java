/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.web.weixin.wxMenu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lshop.web.weixin.wxMenu.service.WxMenuService;
import com.lshop.web.weixin.common.pojo.WxMenu;
import com.lshop.web.weixin.common.pojo.WxNewsMaterial;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.BeanUtils;
import com.gv.core.util.ObjectUtils;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Service("WxMenuService")
public class WxMenuServiceImpl extends BaseServiceImpl implements WxMenuService {
	/**
	 * 获得所有
	 */
	@Resource private HibernateBaseDAO lvlogicReadDao;
	public List<WxMenu> findAll(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 分页查询
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination findPage(Dto dto) throws ServiceException {
		SimplePage page = (SimplePage)dto.get("page");
		 WxMenu wxMenu = (WxMenu)dto.get("model");
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from WxMenu t where 1=1 ");
		        if(ObjectUtils.isNotEmpty(wxMenu.getId())) {
		        	sql.append(" and  t.id = :id ");
		        }	
	        	if(ObjectUtils.isNotEmpty(wxMenu.getName())) {
		        	sql.append(" and  t.name like :name ");
		        }
	        	if(ObjectUtils.isNotEmpty(wxMenu.getMenuType())) {
		        	sql.append(" and  t.menuType like :menuType ");
		        }
	        	if(ObjectUtils.isNotEmpty(wxMenu.getMenuKey())) {
		        	sql.append(" and  t.menuKey like :menuKey ");
		        }
	        	if(ObjectUtils.isNotEmpty(wxMenu.getMenuUrl())) {
		        	sql.append(" and  t.menuUrl like :menuUrl ");
		        }
		        if(ObjectUtils.isNotEmpty(wxMenu.getMaterialType())) {
		        	sql.append(" and  t.materialType = :materialType ");
		        }	
		        if(ObjectUtils.isNotEmpty(wxMenu.getMaterialId())) {
		        	sql.append(" and  t.materialId = :materialId ");
		        }	
		        if(ObjectUtils.isNotEmpty(wxMenu.getMenuParent())) {
		        	sql.append(" and  t.menuParent = :menuParent ");
		        }	
		        if(ObjectUtils.isNotEmpty(wxMenu.getWxhConfigId())) {
		        	sql.append(" and  t.wxhConfigId = :wxhConfigId ");
		        }	
        Map parms = BeanUtils.describeForHQL(wxMenu);
        if(ObjectUtils.isNotEmpty(dto.getAsString("orderField"))) {
            sql.append(" order by t." + dto.getAsString("orderField") + " " + dto.getAsString("orderDirection"));
        } 
		Finder finder = Finder.create(sql.toString());
		finder.setParams(parms);
		Pagination pag = lvlogicReadDao.find(finder, page.getPageNum(), page.getNumPerPage());
		return pag;
	}
	/**
	 * 获得单独的实体信息
	 */
	public WxMenu get(Dto dto) throws ServiceException {
		 WxMenu wxMenu = (WxMenu)dto.get("model");
		 wxMenu = (WxMenu)lvlogicReadDao.load(WxMenu.class, wxMenu.getId());
		return  wxMenu;
	}
	public WxMenu getByMenuKey(int wxhConfigId, String menuKey) throws ServiceException {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("wxhConfigId", wxhConfigId);
		param.put("menuKey", menuKey);
		WxMenu wxMenu = (WxMenu) lvlogicReadDao.findUnique("select t from WxMenu t where t.wxhConfigId = :wxhConfigId and t.menuKey = :menuKey", param);
		return wxMenu;
	}
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		 WxMenu wxMenu = get(dto);
		lvlogicReadDao.delete(  wxMenu);
	}

	public void delList(Dto dto) throws ServiceException {

	}
	/**
	 * 保存
	 */
	public WxMenu save(Dto dto) throws ServiceException {
		 WxMenu wxMenu = (WxMenu)dto.get("model");
		lvlogicReadDao.save( wxMenu);
		return   wxMenu;
	}
	/**
	 * 更新
	 */
	public WxMenu update(Dto dto) throws ServiceException {
		 WxMenu wxMenu = (WxMenu)dto.get("model");
		lvlogicReadDao.update(wxMenu);
		return  wxMenu;
	}

}
