package com.lshop.common.tag;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang3.StringUtils;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.proxy.ServiceConstants;
import com.lshop.common.pojo.logic.LvAd;
import com.lshop.common.redis.RedisExpire;
import com.lshop.common.redis.RedisKeyConstant;
import com.lshop.common.redis.StringValueCacheDao;
import com.lshop.common.util.Constants;
import com.lshop.web.ad.AdService;

/**
 * 广告模块标签
 * @author zhengxue
 * @version 1.0 2012-07-21
 *
 */
@SuppressWarnings("serial")
public class AdTag extends BodyTagSupport {
	
	private String adkey;
	private StringValueCacheDao stringValueCacheDao;
	public Dto dto = new BaseDto();

	
	public AdTag() {
		super();
		stringValueCacheDao = (StringValueCacheDao) ServiceConstants.beanFactory.getBean("StringValueCacheDao");
	}

	public int doEndTag() throws JspException {
		
		JspWriter out = pageContext.getOut();
		
		String serverName = pageContext.getRequest().getServerName();
		String storeFlag = Constants.STORE_IDS.get(serverName.trim());//获取店铺标志
		//先从缓存读取
		String content = stringValueCacheDao.get(RedisKeyConstant.ADKey(storeFlag, adkey));
		if (null == content) {
			StringBuilder html=new StringBuilder();
			//得到帮助标签所涉及到的service
			AdService adService=(AdService)ServiceConstants.beanFactory.getBean("AdService");		
			
			//通过adKey获取LvAd广告信息
			dto.put("adKey", adkey);
			dto.put("storeFlag", storeFlag);
			List<LvAd> ads=(List<LvAd>)adService.getAdByKey(dto);
			
			if(ads!=null){
				for(LvAd ad:ads){
					html.append(ad.getAdContent());
				}
				content = html.toString();
				if (StringUtils.isNotBlank(content)) {
					//设置缓存
					stringValueCacheDao.set(RedisKeyConstant.ADKey(storeFlag, adkey), content);
				} else {
					stringValueCacheDao.set(RedisKeyConstant.ADKey(storeFlag, adkey), "");
				}
				stringValueCacheDao.expire(RedisKeyConstant.ADKey(storeFlag, adkey), RedisExpire.CacheSecond, TimeUnit.SECONDS);
			}
			
		}
		if (StringUtils.isNotBlank(content)) {
			try {
				out.write(content);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return super.doEndTag();

	}

	public String getAdkey() {
		return adkey;
	}

	public void setAdkey(String adkey) {
		this.adkey = adkey;
	}

	

}
