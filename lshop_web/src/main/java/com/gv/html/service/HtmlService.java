package com.gv.html.service;

import java.util.List;

import com.lshop.common.pojo.logic.LvLinkUrl;

/**
 * 提供静态动态路径数据查询服务
 * @author caicl
 *
 */
public interface HtmlService {
	/**
	 * 返回店铺标识下所有需要静态化的链接
	 * @param shopFlag
	 * @return
	 */
	List<LvLinkUrl> getAllLinkByShop(String shopFlag);
	/**
	 * 查找静态文件对应的动态uri
	 * @param huri
	 * @param shopFlag
	 * @return
	 */
	String getHtmlDynamicUri(String huri, String shopFlag);
}
