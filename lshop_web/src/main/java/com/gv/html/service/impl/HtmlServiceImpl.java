package com.gv.html.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.gv.html.core.DynamicHtmlPattern;
import com.gv.html.service.HtmlService;
import com.lshop.common.pojo.logic.LvLinkUrl;
@Service("HtmlService")
public class HtmlServiceImpl implements HtmlService{
	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	/**
	 * 店铺标识和动态模板地址查找表
	 */
	private Map<String, List<DynamicHtmlPattern>> parttenMap = new HashMap<String, List<DynamicHtmlPattern>>();
	@Override
	public List<LvLinkUrl> getAllLinkByShop(String shopFlag) {
		String hql = "FROM LvLinkUrl WHERE storeFlag=:storeFlag";
		Map map = new HashMap();
		map.put("storeFlag", shopFlag);
		List<LvLinkUrl> linkUrls = (List<LvLinkUrl>) lvlogicReadDao.find(hql, map);
		LvLinkUrl gvLinkUrl = null;
		List childObj = null;
		LvLinkUrl curl = null;
		List<LvLinkUrl> childUrls = new ArrayList<LvLinkUrl>();
		List<LvLinkUrl> tempUrls = new ArrayList<LvLinkUrl>();
		String staticUrl, dynamicUrl;
		//load child
		for(int i = linkUrls.size() - 1; i >= 0; i--) {
			gvLinkUrl = linkUrls.get(i);
			if (gvLinkUrl.getDynamicurl().indexOf("@") != -1 && !"".equals(gvLinkUrl.getHql())) {
				childObj = lvlogicReadDao.find(gvLinkUrl.getHql(), null);
				for(Object parm : childObj) {
					Object[] parms = null;
					if (parm instanceof Object[]) {
						parms = (Object[]) parm;
					} else {
						parms = new Object[1];
						parms[0] = parm;
					}
					staticUrl = gvLinkUrl.getStaticurl();
					dynamicUrl = gvLinkUrl.getDynamicurl();
					// set param
					for (int j = 0; j < parms.length; j++) {
						Object pm = parms[j];
						staticUrl = staticUrl.replaceFirst("@", pm == null ? ""
								: pm.toString());
						dynamicUrl = dynamicUrl.replaceFirst("@", pm == null ? ""
								: pm.toString());
					}
					curl = new LvLinkUrl();
					curl.setDynamicurl(dynamicUrl);
					curl.setStaticurl(staticUrl);
					childUrls.add(curl);
				}
				// add to array
				if (ObjectUtils.isNotEmpty(childUrls)) {
					tempUrls.addAll(childUrls);
				}
				linkUrls.remove(i);
			}
		}
		linkUrls.addAll(tempUrls);
		return linkUrls;
	}
	@Override
	public String getHtmlDynamicUri(String huri, String shopFlag) {
		//查找存在静态地址
		String hql = "FROM LvLinkUrl WHERE storeFlag=:storeFlag and staticurl=:staticurl";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("storeFlag", shopFlag);
		param.put("staticurl", huri);
		List<LvLinkUrl> linkUrls = lvlogicReadDao.find(hql, param);
		if (ObjectUtils.isNotEmpty(linkUrls)) {
			return linkUrls.get(0).getDynamicurl();
		}
		//正则匹配动态地址
		List<DynamicHtmlPattern> ptn = getHtmlPattern(shopFlag);
		if (ObjectUtils.isNotEmpty(ptn)) {
			for (Iterator<DynamicHtmlPattern> iterator = ptn.iterator(); iterator.hasNext();) {
				DynamicHtmlPattern dynamicHtmlPattern = iterator.next();
				if (Pattern.matches(dynamicHtmlPattern.getPartten(), huri)) {
					//根据静态地址模板,找到模板中@符号代表业务号
					String[] sf = StringUtils.split(dynamicHtmlPattern.getHuri(), "@");//@符号在静态uri的中间,且只有一个
					String bid = huri.substring(sf[0].length(), huri.lastIndexOf(sf[1]));
					return dynamicHtmlPattern.getDuri().replace("@", bid);
				}
			}
		}
		return null;
	}
	/**
	 * 查看店铺静态地址模板
	 * @param shopFlag
	 * @return
	 */
	private List<DynamicHtmlPattern> getHtmlPattern(String shopFlag) {
		List<DynamicHtmlPattern> ptn = parttenMap.get(shopFlag);
		if (ObjectUtils.isEmpty(ptn)) {
			//加载店铺动态地址模式
			String hql = "from LvLinkUrl where storeFlag=:storeFlag and staticurl like '%@%'";
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("storeFlag", shopFlag);
			List<LvLinkUrl> linkUrls = lvlogicReadDao.find(hql, param);
			if (ObjectUtils.isNotEmpty(linkUrls)) {
				ptn = new ArrayList<DynamicHtmlPattern>();
				DynamicHtmlPattern pattern;
				for (Iterator<LvLinkUrl> iterator = linkUrls.iterator(); iterator
						.hasNext();) {
					LvLinkUrl lvLinkUrl = iterator.next();
					String p = lvLinkUrl.getStaticurl().replace("@", "\\w*").replace(".", "\\.");
					pattern = new DynamicHtmlPattern(lvLinkUrl.getDynamicurl(), lvLinkUrl.getStaticurl(), p);
					ptn.add(pattern);
				}
				//缓存模板
				parttenMap.put(shopFlag, ptn);
			}
		}
		return ptn;
	}
}
