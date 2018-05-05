/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.web.weixin.wxGzhConfig.service.impl;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.lshop.web.weixin.WxConstant;
import com.lshop.web.weixin.util.HttpClientUtil;
import com.lshop.web.weixin.wxGzhConfig.service.WxGzhConfigService;
import com.lshop.web.weixin.common.pojo.WxGzhConfig;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
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
 */@Service("WxGzhConfigService")
public class WxGzhConfigServiceImpl extends BaseServiceImpl implements WxGzhConfigService {
	 private static final Log logger = LogFactory.getLog(WxGzhConfigServiceImpl.class);
	/**
	 * 获得所有
	 */
	@Resource private HibernateBaseDAO lvlogicReadDao;
	@Resource private HibernateBaseDAO lvlogicWriteDao;
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
		Pagination pag = lvlogicReadDao.find(finder, page.getPageNum(), page.getNumPerPage());
		return pag;
	}
	/**
	 * 获得单独的实体信息
	 */
	public WxGzhConfig get(Dto dto) throws ServiceException {
		 WxGzhConfig wxGzhConfig = (WxGzhConfig)dto.get("model");
		 wxGzhConfig = (WxGzhConfig)lvlogicReadDao.load(WxGzhConfig.class, wxGzhConfig.getId());
		return  wxGzhConfig;
	}
	
	public WxGzhConfig getByCode(String code) throws ServiceException {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("code", code);
		 WxGzhConfig wxGzhConfig = (WxGzhConfig)lvlogicReadDao.findUnique("select t from WxGzhConfig t where t.code = :code", param);
		return  wxGzhConfig;
	}

	public WxGzhConfig getById(int id) throws ServiceException {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		 WxGzhConfig wxGzhConfig = (WxGzhConfig)lvlogicReadDao.findUnique("select t from WxGzhConfig t where t.id = :id", param);
		return  wxGzhConfig;
	}	
	
	public WxGzhConfig getByStoreId(String storeId) throws ServiceException {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("storeId", storeId);
		 WxGzhConfig wxGzhConfig = (WxGzhConfig)lvlogicReadDao.findUnique("select t from WxGzhConfig t where t.storeId = :storeId", param);
		return  wxGzhConfig;
	}	
	
	/**
	 * 获取最新的token
	 */
	public String getAccessToken(WxGzhConfig wxGzhConfig) throws ServiceException{
		String access_token ="";
		if(wxGzhConfig!=null){
			Date tokenTime = wxGzhConfig.getAccessTokenTime();
			Calendar cal = Calendar.getInstance();
			Long diff = cal.getTimeInMillis() - tokenTime.getTime();
			int diffSecond = (int)(diff / 1000);
			if(diffSecond+200>wxGzhConfig.getAccessTokenExpires()){
				//表示token过期 需要重新获取
				String url = MessageFormat.format(WxConstant.url_access_token, wxGzhConfig.getAppId(), wxGzhConfig.getAppSecret());
				logger.info("获取最新的accessToken：" + url);
				String configInfo = HttpClientUtil.httpsGet(url);
				logger.info("获取最新的accessToken返回结果：" + configInfo);
				String accessToken = JSONObject.fromObject(configInfo).getString("access_token");
				String expires_in = JSONObject.fromObject(configInfo).getString("expires_in");
				if(!StringUtils.isEmpty(accessToken) && !StringUtils.isEmpty(expires_in)){
					//表示已经获取到了accessToken
					wxGzhConfig.setAccessToken(accessToken);
					wxGzhConfig.setAccessTokenExpires(Integer.parseInt(expires_in));
					wxGzhConfig.setAccessTokenTime(new Date());
					wxGzhConfig.setModifyTime(new Date());
					wxGzhConfig.setModifyUserId(0);
					wxGzhConfig.setModifyUserName("web");
					BaseDto dto = new BaseDto();
					dto.put("model", wxGzhConfig);
					update(dto);
				}else{
					wxGzhConfig.setAccessToken("Error");
					wxGzhConfig.setAccessTokenExpires(0);
					BaseDto dto = new BaseDto();
					dto.put("model", wxGzhConfig);
					update(dto);
				}
			}
			access_token = wxGzhConfig.getAccessToken();
		}
		return access_token;
	}	
	
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		 WxGzhConfig wxGzhConfig = get(dto);
		lvlogicReadDao.delete(  wxGzhConfig);
	}

	public void delList(Dto dto) throws ServiceException {

	}
	/**
	 * 保存
	 */
	public WxGzhConfig save(Dto dto) throws ServiceException {
		 WxGzhConfig wxGzhConfig = (WxGzhConfig)dto.get("model");
		lvlogicReadDao.save( wxGzhConfig);
		return   wxGzhConfig;
	}
	/**
	 * 更新
	 */
	public WxGzhConfig update(Dto dto) throws ServiceException {
		 WxGzhConfig wxGzhConfig = (WxGzhConfig)dto.get("model");
		 lvlogicWriteDao.update(wxGzhConfig);
		return  wxGzhConfig;
	}

}
