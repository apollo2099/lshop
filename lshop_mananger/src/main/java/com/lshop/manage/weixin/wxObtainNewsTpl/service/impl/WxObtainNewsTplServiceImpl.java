/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.weixin.wxObtainNewsTpl.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import com.lshop.manage.weixin.pojo.WxObtainNewsTpl;
import com.lshop.manage.weixin.wxObtainNewsTpl.service.WxObtainNewsTplService;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Service("WxObtainNewsTplService")
public class WxObtainNewsTplServiceImpl extends BaseServiceImpl implements WxObtainNewsTplService {
	/**
	 * 获得所有
	 */
	@Resource private HibernateBaseDAO dao;
	public List<WxObtainNewsTpl> findAll(Dto dto) throws ServiceException {
		String hql = "select t from WxObtainNewsTpl t";
		if(dto != null && dto.get("model")!=null){
			WxObtainNewsTpl obj = (WxObtainNewsTpl)dto.get("model");
			if(obj!=null && ObjectUtils.isNotEmpty(obj.getWxhConfigId())){
				hql += " where t.wxhConfigId ='"+obj.getWxhConfigId()+"'";
			}
		}
		List<WxObtainNewsTpl> list = dao.find(hql, null);
		if (list == null) {
			list = new ArrayList<WxObtainNewsTpl>();
		}
		return list;
	}
	/**
	 * 分页查询
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination findPage(Dto dto) throws ServiceException {
		SimplePage page = (SimplePage)dto.get("page");
		 WxObtainNewsTpl wxObtainNewsTpl = (WxObtainNewsTpl)dto.get("model");
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from WxObtainNewsTpl t where 1=1 ");
		        if(ObjectUtils.isNotEmpty(wxObtainNewsTpl.getId())) {
		        	sql.append(" and  t.id = :id ");
		        }	
	        	if(ObjectUtils.isNotEmpty(wxObtainNewsTpl.getPushKeyword())) {
		        	sql.append(" and  t.pushKeyword like :pushKeyword ");
		        }
	        	if(ObjectUtils.isNotEmpty(wxObtainNewsTpl.getQueryKeyword())) {
		        	sql.append(" and  t.queryKeyword like :queryKeyword ");
		        }
		        if(ObjectUtils.isNotEmpty(wxObtainNewsTpl.getNewsId())) {
		        	sql.append(" and  t.newsId = :newsId ");
		        }	
		        if(ObjectUtils.isNotEmpty(wxObtainNewsTpl.getWxhConfigId())) {
		        	sql.append(" and  t.wxhConfigId = :wxhConfigId ");
		        }	
		        if(ObjectUtils.isNotEmpty(wxObtainNewsTpl.getModifyUserId())) {
		        	sql.append(" and  t.modifyUserId = :modifyUserId ");
		        }	
	        	if(ObjectUtils.isNotEmpty(wxObtainNewsTpl.getModifyUserName())) {
		        	sql.append(" and  t.modifyUserName like :modifyUserName ");
		        }
        Map parms = BeanUtils.describeForHQL(wxObtainNewsTpl);
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
	public WxObtainNewsTpl get(Dto dto) throws ServiceException {
		 WxObtainNewsTpl wxObtainNewsTpl = (WxObtainNewsTpl)dto.get("model");
		 wxObtainNewsTpl = (WxObtainNewsTpl)dao.load(WxObtainNewsTpl.class, wxObtainNewsTpl.getId());
		return  wxObtainNewsTpl;
	}
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		 WxObtainNewsTpl wxObtainNewsTpl = get(dto);
		dao.delete(  wxObtainNewsTpl);
	}

	public void delList(Dto dto) throws ServiceException {

	}
	/**
	 * 保存
	 */
	public WxObtainNewsTpl save(Dto dto) throws ServiceException {
		 WxObtainNewsTpl wxObtainNewsTpl = (WxObtainNewsTpl)dto.get("model");
		dao.save( wxObtainNewsTpl);
		return   wxObtainNewsTpl;
	}
	/**
	 * 更新
	 */
	public WxObtainNewsTpl update(Dto dto) throws ServiceException {
		 WxObtainNewsTpl wxObtainNewsTpl = (WxObtainNewsTpl)dto.get("model");
		dao.update(wxObtainNewsTpl);
		return  wxObtainNewsTpl;
	}

}
