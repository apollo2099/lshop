/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.weixin.wxTextMaterial.service.impl;

import java.util.ArrayList;
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
import com.lshop.manage.weixin.pojo.WxTextMaterial;
import com.lshop.manage.weixin.wxTextMaterial.service.WxTextMaterialService;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Service("WxTextMaterialService")
public class WxTextMaterialServiceImpl extends BaseServiceImpl implements WxTextMaterialService {
	/**
	 * 获得所有
	 */
	@Resource private HibernateBaseDAO dao;
	public List<WxTextMaterial> findAll(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder("select t from WxTextMaterial t where 1=1 ");
		WxTextMaterial wxTextMaterial = (WxTextMaterial)dto.get("model");
		  if(wxTextMaterial!=null && ObjectUtils.isNotEmpty(wxTextMaterial.getId())) {
	        	sql.append(" and  t.id =  '"+wxTextMaterial.getId()+"'");
	        }
		  if(wxTextMaterial!=null && ObjectUtils.isNotEmpty(wxTextMaterial.getWxhConfigId())) {
	        	sql.append(" and  t.wxhConfigId ='"+wxTextMaterial.getWxhConfigId()+"'");
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
		 WxTextMaterial wxTextMaterial = (WxTextMaterial)dto.get("model");
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from WxTextMaterial t where 1=1 ");
		        if(ObjectUtils.isNotEmpty(wxTextMaterial.getId())) {
		        	sql.append(" and  t.id = :id ");
		        }	
	        	if(ObjectUtils.isNotEmpty(wxTextMaterial.getName())) {
		        	sql.append(" and  t.name like :name ");
		        }
	        	if(ObjectUtils.isNotEmpty(wxTextMaterial.getContent())) {
		        	sql.append(" and  t.content like :content ");
		        }
		        if(ObjectUtils.isNotEmpty(wxTextMaterial.getWxhConfigId())) {
		        	sql.append(" and  t.wxhConfigId = :wxhConfigId ");
		        }	
        Map parms = BeanUtils.describeForHQL(wxTextMaterial);
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
	public WxTextMaterial get(Dto dto) throws ServiceException {
		 WxTextMaterial wxTextMaterial = (WxTextMaterial)dto.get("model");
		 wxTextMaterial = (WxTextMaterial)dao.load(WxTextMaterial.class, wxTextMaterial.getId());
		return  wxTextMaterial;
	}
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		 WxTextMaterial wxTextMaterial = get(dto);
		dao.delete(  wxTextMaterial);
	}

	public void delList(Dto dto) throws ServiceException {

	}
	/**
	 * 保存
	 */
	public WxTextMaterial save(Dto dto) throws ServiceException {
		 WxTextMaterial wxTextMaterial = (WxTextMaterial)dto.get("model");
		dao.save( wxTextMaterial);
		return   wxTextMaterial;
	}
	/**
	 * 更新
	 */
	public WxTextMaterial update(Dto dto) throws ServiceException {
		 WxTextMaterial wxTextMaterial = (WxTextMaterial)dto.get("model");
		dao.update(wxTextMaterial);
		return  wxTextMaterial;
	}
	

}
