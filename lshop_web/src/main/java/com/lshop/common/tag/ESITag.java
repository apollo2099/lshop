package com.lshop.common.tag;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang3.StringUtils;

import com.gv.core.proxy.ServiceConstants;
import com.lshop.common.redis.RedisExpire;
import com.lshop.common.redis.RedisKeyConstant;
import com.lshop.common.redis.StringValueCacheDao;

/**
 * 页面片断缓存
 * @author caicl
 *
 */
public class ESITag extends BodyTagSupport {
	private static final long serialVersionUID = 5545757702079980199L;
	private String uri;//引入jsp页面路径
	private StringValueCacheDao stringValueCacheDao;
	private String content;
	
	public ESITag() {
		super();
		stringValueCacheDao = (StringValueCacheDao) ServiceConstants.beanFactory.getBean("StringValueCacheDao");
	}

	@Override
	public int doStartTag() throws JspException {
		content = stringValueCacheDao.get(RedisKeyConstant.FrameKey(uri));//从缓存获取页面片断
        if(content != null) {   //尝试从缓存中读取页面片段
            return SKIP_BODY;
        }
		return super.doStartTag();
	}


	@Override
	public int doEndTag() throws JspException {
		if(StringUtils.isBlank(content)){
			content = bodyContent.getString().trim();
			stringValueCacheDao.set(RedisKeyConstant.FrameKey(uri), content);
			stringValueCacheDao.expire(RedisKeyConstant.FrameKey(uri), RedisExpire.CacheSecond, TimeUnit.SECONDS);
        }
		JspWriter out = pageContext.getOut();
		try {
			out.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doEndTag();
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
}
