/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.weixin.wxUser.service.impl;

import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
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
import com.lshop.common.util.DateUtils;
import com.lshop.manage.weixin.pojo.WxUser;
import com.lshop.manage.weixin.util.HttpClientUtil;
import com.lshop.manage.weixin.wxUser.service.WxUserService;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Service("WxUserService")
public class WxUserServiceImpl extends BaseServiceImpl implements WxUserService {
	 private String getWxUserInfo_url_pre="https://api.weixin.qq.com/cgi-bin/user/info?access_token=";
	/**
	 * 获得所有
	 */
	@Resource private  HibernateBaseDAO dao;
	public List<WxUser> findAll(Dto dto) throws ServiceException {
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
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		return pag;
	}
	/**
	 * 获得单独的实体信息
	 */
	public WxUser get(Dto dto) throws ServiceException {
		 WxUser wxUser = (WxUser)dto.get("model");
		 wxUser = (WxUser)dao.load(WxUser.class, wxUser.getId());
		return  wxUser;
	}
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		 WxUser wxUser = get(dto);
		dao.delete(  wxUser);
	}

	public void delList(Dto dto) throws ServiceException {

	}
	/**
	 * 保存
	 */
	public WxUser save(Dto dto) throws ServiceException {
		 WxUser wxUser = (WxUser)dto.get("model");
		dao.save( wxUser);
		return   wxUser;
	}
	/**
	 * 更新
	 */
	public WxUser update(Dto dto) throws ServiceException {
		WxUser wxUser = (WxUser)dto.get("model");
		wxUser.setModifyTime(new Date());
		wxUser.setModifyTimeString(DateUtils.formatDate(wxUser.getModifyTime(), wxUser.FORMAT_MODIFY_TIME));
		dao.update(wxUser);
		return  wxUser;
	}

	/**
	 * 这个方法主要是用来根据openid来判断到底要去更新用户信息还是增加一个用户信息
	 * @param openId
	 * @return
	 */
	public WxUser getUserIdByOpenId(Dto dto) throws ServiceException{
		String openId = dto.getAsString("openId");
		 String sql ="select t from WxUser t  where t.openid =:openid";
		 Map param = new HashMap<String, String>();
		 param.put("openid", openId);
		 List<WxUser> users = dao.find(sql, param);
		 if(users!= null && users.size() != 0){
			 return users.get(0);
		 }else{
			 return null;
		 }
		
	}

	
	
	
	public List<WxUser> constructWxUsersByOpenIds(Dto dto) throws ServiceException{
		List<WxUser> users = new ArrayList<WxUser>();
		String openIds = (String)dto.get("openIds");
		String token_access = (String)dto.get("token_access");
		int configId = (Integer) dto.get("configId");
		String[] openIdsArray = openIds.split(",");
		for(String id : openIdsArray){
			getWxUserInfo_url_pre +=token_access;
			getWxUserInfo_url_pre+="&openid="+id;
			getWxUserInfo_url_pre+="&lang=zh_CN";
			String userInfoJson ="";
			userInfoJson = HttpClientUtil.post(getWxUserInfo_url_pre);
			getWxUserInfo_url_pre ="https://api.weixin.qq.com/cgi-bin/user/info?access_token=";
			if(StringUtils.isEmpty(userInfoJson)){
				//请求失败
				return users;
			}else if(!StringUtils.isEmpty(userInfoJson) && JSONObject.fromObject(userInfoJson).get("errcode") != null ){
				//请求失败
				return users;
			}else {
				//请求成功
				String openId = JSONObject.fromObject(userInfoJson).getString("openid");
				String nickname = JSONObject.fromObject(userInfoJson).getString("nickname");
				Pattern emoji = Pattern . compile ("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]" ,Pattern . UNICODE_CASE | Pattern . CASE_INSENSITIVE ) ;
				String reg = emoji.pattern();
				nickname =nickname.replaceAll(reg, "");
				WxUser user = new WxUser();
				user.setNickname(nickname);
				user.setOpenid(openId);
				user.setObtainAmount(0);
				user.setWxhConfigId(configId);
				user.setCreateTime(new Date());
				user.setCreateTimeString(DateUtils.formatDate(user.getCreateTime(), "yyyy-MM-dd hh:mm:ss"));
				user.setModifyTime(new Date());
				user.setModifyTimeString(DateUtils.formatDate(user.getModifyTime(), "yyyy-MM-dd hh:mm:ss"));
				users.add(user);
			}
		}
		return users;
		
	}
	
}
