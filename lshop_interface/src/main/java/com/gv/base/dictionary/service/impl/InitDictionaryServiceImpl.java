/**
 * 
 */
package com.gv.base.dictionary.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.gv.base.dictionary.service.InitDictionaryService;
import com.gv.base.springSecurity.common.pojo.BaseDictionarys;
import com.gv.base.util.Constants;
import com.gv.core.hibernate3.HibernateBaseDAO;

/**
 * 数据字典初始化服务类
 * @author Administrator
 *
 */
@Service("initDictionaryService")
@Lazy(false)
public class InitDictionaryServiceImpl implements InitDictionaryService {

	private static final Log logger	= LogFactory.getLog(InitDictionaryServiceImpl.class);
	
	//Map<父编码,<编码,字典名称>>
	private static Map<String, Map<String, String>> dictionaryMap = new HashMap<String, Map<String, String>>();
	@Resource private HibernateBaseDAO dao;
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		if (logger.isInfoEnabled()){
			logger.info("***** InitDictionaryServiceImpl.init() method begin*****");
		}
		//先清除数据字典数据
		dictionaryMap.clear();
		
		String hql = "from BaseDictionarys t where t.parentCode != '" + Constants.ROOT_DICTIONARY_CODE + 
		             "' and t.parentCode != '" + Constants.ROOT_DICTIONARY_PARENT_CODE + "'";
		List<BaseDictionarys> dictionaryList = (List<BaseDictionarys>)dao.find(hql, null);
		
		//将字典数据存入MAP中
		for (BaseDictionarys dictionary : dictionaryList) {
			Map<String, String> tmpMap = null;
			if (dictionaryMap.get(dictionary.getParentCode()) == null) {
				tmpMap = new HashMap<String, String>();
			} else {
				tmpMap = dictionaryMap.get(dictionary.getParentCode());
			}
			tmpMap.put(dictionary.getDictionaryCode(), dictionary.getCodeName());
			dictionaryMap.put(dictionary.getParentCode(), tmpMap);
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** InitDictionaryServiceImpl.init() method end*****");
		}
	}
	
	/**
	 * 获取数据字典
	 * @return
	 */
	public static Map<String, Map<String, String>> getDictionary() {
		return dictionaryMap;
	}
}
