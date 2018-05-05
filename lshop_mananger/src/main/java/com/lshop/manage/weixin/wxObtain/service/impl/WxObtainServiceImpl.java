/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.weixin.wxObtain.service.impl;

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
import com.lshop.manage.weixin.pojo.WxObtain;
import com.lshop.manage.weixin.wxObtain.service.WxObtainService;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Service("WxObtainService")
public class WxObtainServiceImpl extends BaseServiceImpl implements WxObtainService {
	/**
	 * 获得所有
	 */
	@Resource private HibernateBaseDAO dao;
	public List<WxObtain> findAll(Dto dto) throws ServiceException {
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
		 WxObtain wxObtain = (WxObtain)dto.get("model");
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from WxObtain t where 1=1 ");
		        if(ObjectUtils.isNotEmpty(wxObtain.getId())) {
		        	sql.append(" and  t.id = :id ");
		        }	
	        	if(ObjectUtils.isNotEmpty(wxObtain.getOpenid())) {
		        	sql.append(" and  t.openid like :openid ");
		        }
	        	if(ObjectUtils.isNotEmpty(wxObtain.getNickname())) {
		        	sql.append(" and  t.nickname like :nickname ");
		        }
		        if(ObjectUtils.isNotEmpty(wxObtain.getObtainType())) {
		        	sql.append(" and  t.obtainType = :obtainType ");
		        }	
		        if(ObjectUtils.isNotEmpty(wxObtain.getWxhConfigId())) {
		        	sql.append(" and  t.wxhConfigId = :wxhConfigId ");
		        }	
	        	if(ObjectUtils.isNotEmpty(wxObtain.getIpAddress())) {
		        	sql.append(" and  t.ipAddress like :ipAddress ");
		        }
        Map parms = BeanUtils.describeForHQL(wxObtain);
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
	public WxObtain get(Dto dto) throws ServiceException {
		 WxObtain wxObtain = (WxObtain)dto.get("model");
		 wxObtain = (WxObtain)dao.load(WxObtain.class, wxObtain.getId());
		return  wxObtain;
	}
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		 WxObtain wxObtain = get(dto);
		dao.delete(  wxObtain);
	}

	public void delList(Dto dto) throws ServiceException {

	}
	/**
	 * 保存
	 */
	public WxObtain save(Dto dto) throws ServiceException {
		 WxObtain wxObtain = (WxObtain)dto.get("model");
		dao.save( wxObtain);
		return   wxObtain;
	}
	/**
	 * 更新
	 */
	public WxObtain update(Dto dto) throws ServiceException {
		 WxObtain wxObtain = (WxObtain)dto.get("model");
		dao.update(wxObtain);
		return  wxObtain;
	}

}
