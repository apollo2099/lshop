/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.web.weixin.wxNewsMaterial.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lshop.web.weixin.WxConstant;
import com.lshop.web.weixin.message.BaseMessage;
import com.lshop.web.weixin.message.send.NewsArticlesItem;
import com.lshop.web.weixin.message.send.NewsMessage;
import com.lshop.web.weixin.message.send.TextMessage;
import com.lshop.web.weixin.util.JAXBUtil;
import com.lshop.web.weixin.wxNewsMaterial.service.WxNewsMaterialService;
import com.lshop.web.weixin.wxNewsMaterialItem.service.WxNewsMaterialItemService;
import com.lshop.web.weixin.common.pojo.WxNewsMaterial;
import com.lshop.web.weixin.common.pojo.WxNewsMaterialItem;
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
 */@Service("WxNewsMaterialService")
public class WxNewsMaterialServiceImpl extends BaseServiceImpl implements WxNewsMaterialService {
	/**
	 * 获得所有
	 */
	@Resource private HibernateBaseDAO lvlogicReadDao;
	@Resource
	private WxNewsMaterialItemService wxNewsMaterialItemService;
	public List<WxNewsMaterial> findAll(Dto dto) throws ServiceException {
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
		Pagination pag = lvlogicReadDao.find(finder, page.getPageNum(), page.getNumPerPage());
		return pag;
	}
	/**
	 * 获得单独的实体信息
	 */
	public WxNewsMaterial get(Dto dto) throws ServiceException {
		 WxNewsMaterial wxNewsMaterial = (WxNewsMaterial)dto.get("model");
		 wxNewsMaterial = (WxNewsMaterial)lvlogicReadDao.load(WxNewsMaterial.class, wxNewsMaterial.getId());
		return  wxNewsMaterial;
	}
	
	public WxNewsMaterial getById(int wxhConfigId, int id) throws ServiceException {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("wxhConfigId", wxhConfigId);
		WxNewsMaterial wxNewsMaterial = (WxNewsMaterial) lvlogicReadDao.findUnique("select t from WxNewsMaterial t where t.id = :id and t.wxhConfigId = :wxhConfigId", param);
		return  wxNewsMaterial;
	}
	
	/**
	 * 获取图文消息xml字符串
	 * @param wxhConfigId
	 * @param id
	 * @param textMessage
	 * @return
	 * @throws ServiceException
	 */	
	public String getNewsMessageXml(int wxhConfigId, int id, BaseMessage baseMessage) throws ServiceException {
		List<WxNewsMaterialItem> itemList = wxNewsMaterialItemService.getByNewsId(wxhConfigId, id);		
		NewsMessage newMessage = new NewsMessage();
		newMessage.setFromUserName(baseMessage.getToUserName());
		newMessage.setCreateTime(baseMessage.getCreateTime());
		newMessage.setMsgType(WxConstant.MsgType_news);
		newMessage.setToUserName(baseMessage.getFromUserName());
		newMessage.setArticleCount(itemList.size());
		//组合图文子项数据
		List<NewsArticlesItem> articlesItemList = new ArrayList<NewsArticlesItem>();
		for (WxNewsMaterialItem item: itemList) {
			NewsArticlesItem articlesItem = new NewsArticlesItem();
			articlesItem.setDescription(item.getDescription());
			articlesItem.setPicUrl(item.getPicurl());
			String url = item.getUrl();
			url = url.replaceAll("\\{openid\\}", baseMessage.getFromUserName());
			articlesItem.setUrl(url);
			articlesItem.setTitle(item.getTitle());
			articlesItemList.add(articlesItem);
		}
		newMessage.setItemList(articlesItemList);
		String xml = JAXBUtil.convertToXml(newMessage);
		return xml;
	}	
	
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		 WxNewsMaterial wxNewsMaterial = get(dto);
		lvlogicReadDao.delete(  wxNewsMaterial);
	}

	public void delList(Dto dto) throws ServiceException {

	}
	/**
	 * 保存
	 */
	public WxNewsMaterial save(Dto dto) throws ServiceException {
		 WxNewsMaterial wxNewsMaterial = (WxNewsMaterial)dto.get("model");
		lvlogicReadDao.save( wxNewsMaterial);
		return   wxNewsMaterial;
	}
	/**
	 * 更新
	 */
	public WxNewsMaterial update(Dto dto) throws ServiceException {
		 WxNewsMaterial wxNewsMaterial = (WxNewsMaterial)dto.get("model");
		lvlogicReadDao.update(wxNewsMaterial);
		return  wxNewsMaterial;
	}

}
