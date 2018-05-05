/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.web.weixin.wxPassiveReply.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lshop.web.weixin.wxPassiveReply.service.WxPassiveReplyService;
import com.lshop.web.weixin.common.pojo.WxPassiveReply;
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
 */@Service("WxPassiveReplyService")
public class WxPassiveReplyServiceImpl extends BaseServiceImpl implements WxPassiveReplyService {
	/**
	 * 获得所有
	 */
	@Resource private HibernateBaseDAO lvlogicReadDao;
	@Resource private HibernateBaseDAO lvlogicWriteDao;
	public List<WxPassiveReply> findAll(Dto dto) throws ServiceException {
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
		 WxPassiveReply wxPassiveReply = (WxPassiveReply)dto.get("model");
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from WxPassiveReply t where 1=1 ");
		        if(ObjectUtils.isNotEmpty(wxPassiveReply.getId())) {
		        	sql.append(" and  t.id = :id ");
		        }	
	        	if(ObjectUtils.isNotEmpty(wxPassiveReply.getDescription())) {
		        	sql.append(" and  t.description like :description ");
		        }
		        if(ObjectUtils.isNotEmpty(wxPassiveReply.getMaterialType())) {
		        	sql.append(" and  t.materialType = :materialType ");
		        }	
		        if(ObjectUtils.isNotEmpty(wxPassiveReply.getMaterialId())) {
		        	sql.append(" and  t.materialId = :materialId ");
		        }	
		        if(ObjectUtils.isNotEmpty(wxPassiveReply.getWxhConfigId())) {
		        	sql.append(" and  t.wxhConfigId = :wxhConfigId ");
		        }	
        Map parms = BeanUtils.describeForHQL(wxPassiveReply);
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
	public WxPassiveReply get(Dto dto) throws ServiceException {
		 WxPassiveReply wxPassiveReply = (WxPassiveReply)dto.get("model");
		 wxPassiveReply = (WxPassiveReply)lvlogicReadDao.load(WxPassiveReply.class, wxPassiveReply.getId());
		return  wxPassiveReply;
	}
	
	public WxPassiveReply getByWxh(int wxhConfigId) throws ServiceException {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("wxhConfigId", wxhConfigId);
		WxPassiveReply reply = (WxPassiveReply) lvlogicReadDao.findUnique("select t from WxPassiveReply t where t.wxhConfigId = :wxhConfigId", param);
		return reply;
	}	
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		 WxPassiveReply wxPassiveReply = get(dto);
		lvlogicReadDao.delete(  wxPassiveReply);
	}

	public void delList(Dto dto) throws ServiceException {

	}
	/**
	 * 保存
	 */
	public WxPassiveReply save(Dto dto) throws ServiceException {
		 WxPassiveReply wxPassiveReply = (WxPassiveReply)dto.get("model");
		 lvlogicWriteDao.save( wxPassiveReply);
		return   wxPassiveReply;
	}
	/**
	 * 更新
	 */
	public WxPassiveReply update(Dto dto) throws ServiceException {
		 WxPassiveReply wxPassiveReply = (WxPassiveReply)dto.get("model");
		lvlogicReadDao.update(wxPassiveReply);
		return  wxPassiveReply;
	}

}
