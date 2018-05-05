/**
 * 常量类
 */
package com.gv.base.util;

import java.util.Map;

import com.gv.base.dictionary.service.impl.InitDictionaryServiceImpl;

/**
 * @author panjin
 *
 */
public class Constants {
	
	// 数据字典 Map<父编码,<编码,字典名称>>
	public static final Map<String, Map<String, String>> DICTIONARY_MAP = InitDictionaryServiceImpl.getDictionary();
	
	// 数据字典根节点code
	public static final String ROOT_DICTIONARY_CODE = "000000";
	
	// 数据字典根节点parentCode
	public static final String ROOT_DICTIONARY_PARENT_CODE = "-1";
}