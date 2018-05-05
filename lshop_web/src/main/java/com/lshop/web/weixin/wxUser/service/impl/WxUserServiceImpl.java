/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.web.weixin.wxUser.service.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.lshop.web.weixin.WxConstant;
import com.lshop.web.weixin.message.BaseMessage;
import com.lshop.web.weixin.message.send.TextMessage;
import com.lshop.web.weixin.util.HttpClientUtil;
import com.lshop.web.weixin.util.JAXBUtil;
import com.lshop.web.weixin.wxGzhConfig.service.WxGzhConfigService;
import com.lshop.web.weixin.wxUser.service.WxUserService;
import com.lshop.web.weixin.common.pojo.WxGzhConfig;
import com.lshop.web.weixin.common.pojo.WxUser;
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
 */@Service("WxUserService")
public class WxUserServiceImpl extends BaseServiceImpl implements WxUserService {
	 private static final Log logger = LogFactory.getLog(WxUserServiceImpl.class);
	/**
	 * 获得所有
	 */
	@Resource private HibernateBaseDAO lvlogicReadDao;
	@Resource private HibernateBaseDAO lvlogicWriteDao;
	@Resource private WxGzhConfigService wxGzhConfigService;
	public List<WxUser> findAll(Dto dto) throws ServiceException {	
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
		 WxUser wxUser = (WxUser)dto.get("model");
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from WxUser t where 1=1 ");
		        if(ObjectUtils.isNotEmpty(wxUser.getId())) {
		        	sql.append(" and  t.id = :id ");
		        }	
	        	if(ObjectUtils.isNotEmpty(wxUser.getOpenid())) {
		        	sql.append(" and  t.openid like :openid ");
		        }
	        	if(ObjectUtils.isNotEmpty(wxUser.getNickname())) {
		        	sql.append(" and  t.nickname like :nickname ");
		        }
	        	if(ObjectUtils.isNotEmpty(wxUser.getEmail())) {
		        	sql.append(" and  t.email like :email ");
		        }
		        if(ObjectUtils.isNotEmpty(wxUser.getWxhConfigId())) {
		        	sql.append(" and  t.wxhConfigId = :wxhConfigId ");
		        }	
        Map parms = BeanUtils.describeForHQL(wxUser);
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
	public WxUser get(Dto dto) throws ServiceException {
		 WxUser wxUser = (WxUser)dto.get("model");
		 wxUser = (WxUser)lvlogicReadDao.load(WxUser.class, wxUser.getId());
		return  wxUser;
	}
	
	public WxUser getByOpenid(int wxhConfigId, String openid) throws ServiceException {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("wxhConfigId", wxhConfigId);
		param.put("openid", openid);
		WxUser wxUser = (WxUser) lvlogicReadDao.findUnique("select t from WxUser t where t.openid = :openid and t.wxhConfigId = :wxhConfigId", param);
		return wxUser;
	}
	
	/**
	 * 获取排行榜名次
	 * @param wxhConfigId
	 * @param openid
	 * @return
	 * @throws ServiceException
	 */	
	public int getRank(int wxhConfigId, String openid) throws ServiceException {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("wxhConfigId", wxhConfigId);
		String hql = "select t from WxUser t where t.wxhConfigId = :wxhConfigId order by t.obtainAmount desc,t.lastObtainTime desc";
		List<WxUser> userList = lvlogicReadDao.find(hql, param);
		int rank = 0;
		for (WxUser user: userList) {
			rank++;
			if (user.getOpenid().equals(openid)) {
				return rank;
			}
		}
		return 0;
	}	
	
	public String getNicknameFromWx(int wxhConfigId, String openid) throws ServiceException {
		try {
			WxGzhConfig wxGzhConfig = wxGzhConfigService.getById(wxhConfigId);
			String accessToken = wxGzhConfigService.getAccessToken(wxGzhConfig);
			String url = MessageFormat.format(WxConstant.url_user_info, accessToken, openid);
			logger.info("获取微信用户信息：" + url);
			String userInfo = HttpClientUtil.httpsGet(url);
			logger.info("获取微信用户信息返回结果：" + userInfo);
			JSONObject jo = JSONObject.fromObject(userInfo);
			String nickname = jo.getString("nickname");
			return nickname;
		} catch (Exception e) {
			logger.error(e);
		}
		return "unknown";
	}
	
	/**
	 * 获取排行榜
	 * @param wxhConfigId
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String, Object>> getRankList(int wxhConfigId, int lastNum) throws ServiceException {
		int maxTopNum = 10;
		if (lastNum <= maxTopNum) {
			lastNum = maxTopNum + 1;
		}
		SimplePage page = new SimplePage();
		page.setNumPerPage(lastNum);
		page.setPageNum(1);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("wxhConfigId", wxhConfigId);
		String hql = "select t from WxUser t where t.wxhConfigId = :wxhConfigId order by t.obtainAmount desc,t.lastObtainTime desc";
		Finder finder = Finder.create(hql);
		finder.setParams(param);
		Pagination pag = lvlogicReadDao.find(finder, page.getPageNum(), page.getNumPerPage());
		List<WxUser> userList = (List<WxUser>) pag.getList();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for (int i=1; i<= userList.size(); i++) {
			if (i<=maxTopNum) {
				WxUser user = userList.get(i-1);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("rankNum", i);
				map.put("nickname", user.getNickname());
				map.put("obtainAmount", user.getObtainAmount());
				mapList.add(map);				
			} else if (i>maxTopNum && i<lastNum) {
				continue;
			} else if (i == lastNum) {
				WxUser user = userList.get(i-1);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("rankNum", lastNum);
				map.put("nickname", user.getNickname());
				map.put("obtainAmount", user.getObtainAmount());
				mapList.add(map);				
			}
		}
		return mapList;
	}	
	
	/**
	 * 查询领取活动详情
	 * @param wxhConfigId
	 * @param openid
	 * @param textMessage
	 * @return
	 * @throws ServiceException
	 */	
	public String getTextMessageXml(int wxhConfigId, BaseMessage baseMessage) throws ServiceException {
		WxUser user = this.getByOpenid(wxhConfigId, baseMessage.getFromUserName());
		String s = "您已筹得{amount}元";
		Integer amount = 0;
		if (user != null) {
			amount = user.getObtainAmount();
		} else {
			logger.info("getTextMessageXml user is null,wxhConfigId:"+wxhConfigId+",baseMessage.getToUserName():"+baseMessage.getFromUserName());
		}
		s = s.replaceAll("\\{amount\\}", amount.toString());
		TextMessage tm = new TextMessage();
		tm.setContent(s);
		tm.setCreateTime(baseMessage.getCreateTime());
		tm.setFromUserName(baseMessage.getToUserName());
		tm.setToUserName(baseMessage.getFromUserName());
		tm.setMsgType(WxConstant.MsgType_text);
		return JAXBUtil.convertToXml(tm);
	}	
	
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		 WxUser wxUser = get(dto);
		lvlogicReadDao.delete(  wxUser);
	}

	public void delList(Dto dto) throws ServiceException {

	}
	/**
	 * 保存
	 */
	public WxUser save(Dto dto) throws ServiceException {
		 WxUser wxUser = (WxUser)dto.get("model");
		 lvlogicWriteDao.save( wxUser);
		return   wxUser;
	}
	/**
	 * 更新
	 */
	public WxUser update(Dto dto) throws ServiceException {
		 WxUser wxUser = (WxUser)dto.get("model");
		 lvlogicWriteDao.update(wxUser);
		return  wxUser;
	}

}
