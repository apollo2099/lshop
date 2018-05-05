/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.weixin.wxNewsMaterial.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.BeanUtils;
import com.gv.core.util.ObjectUtils;
import com.lshop.manage.weixin.pojo.WxNewsMaterial;
import com.lshop.manage.weixin.wxNewsMaterial.service.WxNewsMaterialService;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Service("WxNewsMaterialService")
public class WxNewsMaterialServiceImpl extends BaseServiceImpl implements WxNewsMaterialService {
	/**
	 * 获得所有
	 */
	@Resource private HibernateBaseDAO dao;
	public List<WxNewsMaterial> findAll(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder("select t from WxNewsMaterial t where 1=1 ");
		WxNewsMaterial wxNewsMaterial = (WxNewsMaterial)dto.get("model");
		  if(wxNewsMaterial!=null && ObjectUtils.isNotEmpty(wxNewsMaterial.getId())) {
	        	sql.append(" and  t.id =  '"+wxNewsMaterial.getId()+"'");
	        }
		  if(wxNewsMaterial!=null && ObjectUtils.isNotEmpty(wxNewsMaterial.getWxhConfigId())) {
	        	sql.append(" and  t.wxhConfigId ='"+wxNewsMaterial.getWxhConfigId()+"'");
	        }	
		  
		  sql.append(" order by id asc");
		return dao.find(sql.toString(), null);
		 
	}
	/**
	 * 分页查询
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination findPage(Dto dto) throws ServiceException {
		SimplePage page = (SimplePage)dto.get("page");
		WxNewsMaterial wxNewsMaterial = (WxNewsMaterial)dto.get("model");
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from WxNewsMaterial t where 1=1 ");
		        if(ObjectUtils.isNotEmpty(wxNewsMaterial.getId())) {
		        	sql.append(" and  t.id = :id ");
		        }	
	        	if(ObjectUtils.isNotEmpty(wxNewsMaterial.getName())) {
		        	sql.append(" and  t.name like :name ");
		        }
		        if(ObjectUtils.isNotEmpty(wxNewsMaterial.getNewsType())) {
		        	sql.append(" and  t.newsType = :newsType ");
		        }	
		        if(ObjectUtils.isNotEmpty(wxNewsMaterial.getWxhConfigId())) {
		        	sql.append(" and  t.wxhConfigId = :wxhConfigId ");
		        }	
        Map parms = BeanUtils.describeForHQL(wxNewsMaterial);
        if(ObjectUtils.isNotEmpty(dto.getAsString("orderField"))) {
            sql.append(" order by t." + dto.getAsString("orderField") + " " + dto.getAsString("orderDirection"));
        } 
		Finder finder = Finder.create(sql.toString());
		finder.setParams(parms);
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		return pag;
	}
	/**
	 * 获得单独的实体信息
	 */
	public WxNewsMaterial get(Dto dto) throws ServiceException {
		 WxNewsMaterial wxNewsMaterial = (WxNewsMaterial)dto.get("model");
		 wxNewsMaterial = (WxNewsMaterial)dao.load(WxNewsMaterial.class, wxNewsMaterial.getId());
		return  wxNewsMaterial;
	}
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		 WxNewsMaterial wxNewsMaterial = get(dto);
		dao.delete(  wxNewsMaterial);
	}

	public void delList(Dto dto) throws ServiceException {

	}
	/**
	 * 保存
	 */
	public WxNewsMaterial save(Dto dto) throws ServiceException {
		 WxNewsMaterial wxNewsMaterial = (WxNewsMaterial)dto.get("model");
		dao.save( wxNewsMaterial);
		return   wxNewsMaterial;
	}
	/**
	 * 更新
	 */
	public WxNewsMaterial update(Dto dto) throws ServiceException {
		 WxNewsMaterial wxNewsMaterial = (WxNewsMaterial)dto.get("model");
		dao.update(wxNewsMaterial);
		return  wxNewsMaterial;
	}

}
