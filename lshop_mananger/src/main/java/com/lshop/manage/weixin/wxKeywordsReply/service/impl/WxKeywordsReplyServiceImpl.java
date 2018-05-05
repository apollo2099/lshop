/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.weixin.wxKeywordsReply.service.impl;

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
import com.lshop.manage.weixin.pojo.WxKeywordsReply;
import com.lshop.manage.weixin.pojo.WxMenu;
import com.lshop.manage.weixin.wxKeywordsReply.service.WxKeywordsReplyService;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Service("WxKeywordsReplyService")
public class WxKeywordsReplyServiceImpl extends BaseServiceImpl implements WxKeywordsReplyService {
	/**
	 * 获得所有
	 */
	@Resource private HibernateBaseDAO dao;
	public List<WxKeywordsReply> findAll(Dto dto) throws ServiceException {
		StringBuilder sql = new StringBuilder("select t from WxKeywordsReply t where 1=1 ");
		WxKeywordsReply wxKeywordsReply = (WxKeywordsReply)dto.get("model");
		if(wxKeywordsReply!=null && ObjectUtils.isNotEmpty(wxKeywordsReply.getWxhConfigId())) {
	        	sql.append(" and t.wxhConfigId ='"+wxKeywordsReply.getWxhConfigId()+"'");
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
		 WxKeywordsReply wxKeywordsReply = (WxKeywordsReply)dto.get("model");
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from WxKeywordsReply t where 1=1 ");
		        if(ObjectUtils.isNotEmpty(wxKeywordsReply.getId())) {
		        	sql.append(" and  t.id = :id ");
		        }	
	        	if(ObjectUtils.isNotEmpty(wxKeywordsReply.getName())) {
		        	sql.append(" and  t.name like :name ");
		        }
	        	if(ObjectUtils.isNotEmpty(wxKeywordsReply.getKeywords())) {
		        	sql.append(" and  t.keywords like :keywords ");
		        }
		        if(ObjectUtils.isNotEmpty(wxKeywordsReply.getMaterialType())) {
		        	sql.append(" and  t.materialType = :materialType ");
		        }	
		        if(ObjectUtils.isNotEmpty(wxKeywordsReply.getMaterialId())) {
		        	sql.append(" and  t.materialId = :materialId ");
		        }	
		        if(ObjectUtils.isNotEmpty(wxKeywordsReply.getWxhConfigId())) {
		        	sql.append(" and  t.wxhConfigId = :wxhConfigId ");
		        }	
        Map parms = BeanUtils.describeForHQL(wxKeywordsReply);
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
	public WxKeywordsReply get(Dto dto) throws ServiceException {
		 WxKeywordsReply wxKeywordsReply = (WxKeywordsReply)dto.get("model");
		 wxKeywordsReply = (WxKeywordsReply)dao.load(WxKeywordsReply.class, wxKeywordsReply.getId());
		return  wxKeywordsReply;
	}
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		 WxKeywordsReply wxKeywordsReply = get(dto);
		dao.delete(  wxKeywordsReply);
	}

	public void delList(Dto dto) throws ServiceException {

	}
	/**
	 * 保存
	 */
	public WxKeywordsReply save(Dto dto) throws ServiceException {
		 WxKeywordsReply wxKeywordsReply = (WxKeywordsReply)dto.get("model");
		dao.save( wxKeywordsReply);
		return   wxKeywordsReply;
	}
	/**
	 * 更新
	 */
	public WxKeywordsReply update(Dto dto) throws ServiceException {
		 WxKeywordsReply wxKeywordsReply = (WxKeywordsReply)dto.get("model");
		dao.update(wxKeywordsReply);
		return  wxKeywordsReply;
	}

}
