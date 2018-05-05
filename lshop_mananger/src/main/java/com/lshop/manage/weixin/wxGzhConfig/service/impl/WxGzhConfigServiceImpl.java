/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.weixin.wxGzhConfig.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
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
import com.lshop.manage.weixin.pojo.WxGzhConfig;
import com.lshop.manage.weixin.pojo.WxUser;
import com.lshop.manage.weixin.util.DateUtil;
import com.lshop.manage.weixin.util.HttpClientUtil;
import com.lshop.manage.weixin.wxGzhConfig.service.WxGzhConfigService;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Service("WxGzhConfigService")
public class WxGzhConfigServiceImpl extends BaseServiceImpl implements WxGzhConfigService {
	 private  String token_url_pre ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
	/**
	 * 获得所有
	 */
	@Resource private HibernateBaseDAO dao;
	public List<WxGzhConfig> findAll(Dto dto) throws ServiceException {
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
		 WxGzhConfig wxGzhConfig = (WxGzhConfig)dto.get("model");
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from WxGzhConfig t where 1=1 ");
		        if(ObjectUtils.isNotEmpty(wxGzhConfig.getId())) {
		        	sql.append(" and  t.id = :id ");
		        }	
	        	if(ObjectUtils.isNotEmpty(wxGzhConfig.getWxhName())) {
		        	sql.append(" and  t.wxhName like :wxhName ");
		        }
	        	if(ObjectUtils.isNotEmpty(wxGzhConfig.getPreUrl())) {
		        	sql.append(" and  t.preUrl like :preUrl ");
		        }
	        	if(ObjectUtils.isNotEmpty(wxGzhConfig.getToken())) {
		        	sql.append(" and  t.token like :token ");
		        }
	        	if(ObjectUtils.isNotEmpty(wxGzhConfig.getEncodingAesKey())) {
		        	sql.append(" and  t.encodingAesKey like :encodingAesKey ");
		        }
	        	if(ObjectUtils.isNotEmpty(wxGzhConfig.getAppId())) {
		        	sql.append(" and  t.appId like :appId ");
		        }
	        	if(ObjectUtils.isNotEmpty(wxGzhConfig.getAppSecret())) {
		        	sql.append(" and  t.appSecret like :appSecret ");
		        }
	        	if(ObjectUtils.isNotEmpty(wxGzhConfig.getAccessToken())) {
		        	sql.append(" and  t.accessToken like :accessToken ");
		        }
		        if(ObjectUtils.isNotEmpty(wxGzhConfig.getAccessTokenExpires())) {
		        	sql.append(" and  t.accessTokenExpires = :accessTokenExpires ");
		        }	
	        	if(ObjectUtils.isNotEmpty(wxGzhConfig.getCode())) {
		        	sql.append(" and  t.code like :code ");
		        }
		        if(ObjectUtils.isNotEmpty(wxGzhConfig.getModifyUserId())) {
		        	sql.append(" and  t.modifyUserId = :modifyUserId ");
		        }	
	        	if(ObjectUtils.isNotEmpty(wxGzhConfig.getModifyUserName())) {
		        	sql.append(" and  t.modifyUserName like :modifyUserName ");
		        }
        Map parms = BeanUtils.describeForHQL(wxGzhConfig);
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
	public WxGzhConfig get(Dto dto) throws ServiceException {
		 WxGzhConfig wxGzhConfig = (WxGzhConfig)dto.get("model");
		 wxGzhConfig = (WxGzhConfig)dao.load(WxGzhConfig.class, wxGzhConfig.getId());
		return  wxGzhConfig;
	}
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		 WxGzhConfig wxGzhConfig = get(dto);
		dao.delete(  wxGzhConfig);
	}

	public void delList(Dto dto) throws ServiceException {

	}
	/**
	 * 保存
	 */
	public WxGzhConfig save(Dto dto) throws ServiceException {
		 WxGzhConfig wxGzhConfig = (WxGzhConfig)dto.get("model");
		dao.save( wxGzhConfig);
		return   wxGzhConfig;
	}
	/**
	 * 更新
	 */
	public WxGzhConfig update(Dto dto) throws ServiceException {
		 WxGzhConfig wxGzhConfig = (WxGzhConfig)dto.get("model");
		dao.update(wxGzhConfig);
		return  wxGzhConfig;
	}
	/**
	 * 获取有效的token
	 */
	public WxGzhConfig getYxToken(Dto dto)throws ServiceException{
		 WxGzhConfig wxGzhConfig = (WxGzhConfig)dto.get("model");
		 Map param=new HashMap();
		 param.put("id", wxGzhConfig.getId());
		 param.put("date", wxGzhConfig.getAccessTokenTime());
		 List list=dao.find("select t from WxGzhConfig t where 1=1 and t.id=:id and t.accessTokenExpires < UNIX_TIMESTAMP(:date)-　UNIX_TIMESTAMP(t.accessTokenTime)+200  ", param);
		 if(list==null||list.size()==0)return null;
		 return (WxGzhConfig)list.get(0);
	}
	public String  getLatestToken(Dto dto) throws ServiceException{
		WxGzhConfig wxGzhConfig =  get(dto);
		BaseUsers users =(BaseUsers)dto.get("users");
		String access_token ="";
		if(wxGzhConfig!=null){
			int sec = DateUtil.calLastedTime(wxGzhConfig.getAccessTokenTime(), new Date());
			if(sec+200>wxGzhConfig.getAccessTokenExpires()){
				//表示token过期 需要重新获取
				token_url_pre+="&appid="+wxGzhConfig.getAppId();
				token_url_pre+="&secret="+wxGzhConfig.getAppSecret();
				String configInfo = HttpClientUtil.post(token_url_pre);
				String accessToken = JSONObject.fromObject(configInfo).getString("access_token");
				String expires_in = JSONObject.fromObject(configInfo).getString("expires_in");
				if(!StringUtils.isEmpty(accessToken) && !StringUtils.isEmpty(expires_in)){
					//表示已经获取到了accessToken
					wxGzhConfig.setAccessToken(accessToken);
					wxGzhConfig.setAccessTokenExpires(Integer.parseInt(expires_in));
					wxGzhConfig.setAccessTokenTime(new Date());
					wxGzhConfig.setAccessTokenTimeString(DateUtils.formatDate(wxGzhConfig.getAccessTokenTime(), "yyyy-MM-dd HH:mm:ss"));
					wxGzhConfig.setModifyTime(new Date());
					wxGzhConfig.setModifyTimeString(DateUtils.formatDate(wxGzhConfig.getModifyTime(), "yyyy-MM-dd HH:mm:ss"));
					wxGzhConfig.setModifyUserId(users.getId());
					wxGzhConfig.setModifyUserName(users.getUserName());
					dto.put("model", wxGzhConfig);
					update( dto);
				}else{
					wxGzhConfig.setAccessToken("Error");
					wxGzhConfig.setAccessTokenExpires(0);
					dto.put("model", wxGzhConfig);
					update( dto);
				}
				
			}
			access_token = wxGzhConfig.getAccessToken();
			
		}
		return access_token;
		
		
	}
	
	public List<WxGzhConfig> getAllWxgzh(Dto dto)throws ServiceException{
		List<WxGzhConfig> list = dao.find("select t from WxGzhConfig t where 1=1 order by id desc ", null);
		if(list!=null){
			return list;
		}else{
			list = new ArrayList<WxGzhConfig>();
			return list;
		}
		
	}
}
