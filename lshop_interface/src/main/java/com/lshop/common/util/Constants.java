
package com.lshop.common.util;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;

/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.common.util.Constants.java]  
 * @ClassName:    [Constants]   
 * @Description:  [常用常量字典定义]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-13 上午10:35:34]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-13 上午10:35:34]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@SuppressWarnings("unchecked")
public class Constants {
	
	public static String SORT_DESC_ = "desc";
	public static String SORT_ASC_ = "asc";
	public static BeanFactory beanFactory = null;
	public static final String CHARACTER_ENCODEING = "UTF-8";
	public static String USER_KEY_MANAGER="USER";//后台用户登录key

	//店铺信息(域名对应店铺标志)
	public static Map<String,String> STORE_IDS = new HashMap<String,String>();
	//店铺标志对于店铺id信息
	public static Map<String,Integer> STORE_FLAG_TO_IDS =new HashMap<String,Integer>();
	//商城对应下级店铺列表集合(只是包含启用店铺)
	public static Map<String,String> STORE_LIST = new HashMap<String,String>();
	
	//商城对应下级店铺列表集合
	public static Map<String,String> STORE_LIST_ALL = new HashMap<String,String>();
	//商城体系信息(店铺标志对应商城体系标志)
	public static Map<String,String> STORE_TO_MALL_SYSTEM = new HashMap<String,String>();
	//商城体系标示对应商城体系后缀域名信息
	public static Map<String,String> MALL_TO_DOMAIN_POSTFIX=new HashMap<String,String>();
	//商城体系标志对应商城店铺标志
	public static Map<String,List<String>> MALL_SYSTEM_TO_STORE=new HashMap<String,List<String>>();
	//数据迁移新旧产品code匹配对应关系
	public static Map<String,String> PRODUCT_CODES = new HashMap<String,String>();
	
	//语言版本定义	
	public static String LANG_EN="en";//语言版本 英文
	public static String LANG_CN="cn";//语言版本 中文
	public static String LANG_TW="tw";//语言版本 繁体
	public static String LANG_KN="kn";//语言版本 韩文
	public static String LANG_JA="ja";//语言版本 日文
	
    //操作标示定义
	public static Integer STATUS_CODE_SUCCESS=1;//操作成功
	public static Integer STATUS_CODE_FIAL=-1;  //操作失败
	//订单状态定义
	public static short ORDER_STATUS_0  = 0;  //待发货
	public static short ORDER_STATUS_1  = 1;  //已发货,未确认收货
	public static short ORDER_STATUS_2  = 2;  //已收货 ,待评价
	public static short ORDER_STATUS_3  = 3;  //已退货
	public static short ORDER_STATUS_4  = 4;  //已完成
	
	//支付方式定义
	public static short PAY_METHOD_PAYPAL = 1;		//paypal支付
	public static short PAY_METHOD_ICC    = 2;		//国际信用卡
	public static short PAY_METHOD_ALIPAY = 3;		//支付宝
	public static short PAY_METHOD_WESTERNUNION = 4;//西联汇款
	public static short PAY_METHOD_YOURSPAY     = 5;//优仕支付
	public static short PAY_METHOD_KQ = 6;		    //快钱支付
	public static short PAY_METHOD_INSTALL=7;       //上門安裝 體驗後付款
	public static short PAY_METHOD_95EPAY=8;//双乾支付
	public static short PAY_METHOD_GB    = 10;		//钱包支付
	public static short PAY_METHOD_PAYPALOUT_VISA=11;//支付宝外卡visa支付 moto 通道
	public static short PAY_METHOD_PAYPALOUT_MASTER=12;//支付宝外卡master支付 moto通道
	public static short PAY_METHOD_PAYPALOUT_JCB=13;//支付宝外卡jcb支付 moto通道
	public static short PAY_METHOD_PAYDOLLAR=14;//paydollar支付
	
	public static short PAY_METHOD_OLD_PAYPALOUT_VISA=15;//支付宝外卡visa支付
	public static short PAY_METHOD_OLD_PAYPALOUT_MASTER=16;//支付宝外卡master支付
	public static short PAY_METHOD_OLD_PAYPALOUT_JCB=17;//支付宝外卡jcb支付
	
	//支付状态
	public static short PAY_STATUS_NO    	 = 0;		//支付失败或尚未支付
	public static short PAY_STATUS_OK  		 = 1;		//支付成功 已确认
	public static short PAY_STATUS_OK_UNRECOGNIZED = 2;	//支付成功 未确认
	
	//订单审核状态(客服审核，财务审核)
	public static short ORDER_AUDIT_STATUS_NO=-1;//审核未通过
	public static short ORDER_AUDIT_STATUS_OK=1; //审核通过
	public static short ORDER_AUDIT_STATUS_UN=0; //未审核
	
	//软删除标志
	public static short DELETE_NO=0;  //未删除
	public static short DELETE_YES=-1;//已删除
	
	//待审核和未通过审核菜单配置标志(auditFlag)
	public static short AUDIT_WAIT=0; //待审核
	public static short AUDIT_NO=1;   //未通过审核
	
	//商家广告位置类型字典定义
	public static String AD_LOCATION_INDEX="AD_LOCATION_INDEX";         //首页顶部广告
	public static String AD_LOCATION_SUBJECT_WORD="AD_LOCATION_SUBJECT_WORD";//聚焦专题文字广告
	public static String AD_LOCATION_SUBJECT="AD_LOCATION_SUBJECT";     //聚集专题
	public static String AD_LOCATION_APP="AD_LOCATION_APP";             //应用广告
	public static String AD_LOCATION_LEFT="AD_LOCATION_LEFT";           //左侧广告
	public static String AD_LOCATION_TOP="AD_LOCATION_TOP";             //商家顶部广告
	//商城广告位置类型字典定义
	public static String AD_TVPAD_LEFT ="AD_TVPAD_LEFT";    //商城左侧广告
	public static String AD_TVPAD_INDEX ="AD_TVPAD_INDEX";  //商城首页顶部广告
	public static String AD_TVPAD_RIGHT1 ="AD_TVPAD_RIGHT1";//商城右侧第一个广告
	public static String AD_TVPAD_RIGHT2 ="AD_TVPAD_RIGHT2";//商城右侧第二个广告
	public static String AD_TVPAD_BOTTOM ="AD_TVPAD_BOTTOM";//商城下方广告
	public static String AD_TVPAD_ENTER ="AD_TVPAD_ENTER";  //商城华扬入口
	public static String AD_TVPAD_SPECIAL ="AD_TVPAD_SPECIAL";//商城专卖店广告
	public static String AD_TVPAD_INDEX_BOTTOM ="AD_TVPAD_INDEX_BOTTOM";//商城首页底部广告位
	
	
	//将支付方式放在Map集合，便于前台页面获取
	public static Map PAY_METHODS=new HashMap();
	static{
		PAY_METHODS.put(PAY_METHOD_PAYPAL, "paypal支付");
		PAY_METHODS.put(PAY_METHOD_ICC, "国际信用卡");
		PAY_METHODS.put(PAY_METHOD_ALIPAY, "支付宝");
		PAY_METHODS.put(PAY_METHOD_WESTERNUNION, "西联汇款");
		PAY_METHODS.put(PAY_METHOD_YOURSPAY, "优仕支付");
		PAY_METHODS.put(PAY_METHOD_KQ, "快钱支付");
		PAY_METHODS.put(PAY_METHOD_INSTALL, "上門安裝 體驗後付款");
		PAY_METHODS.put(PAY_METHOD_95EPAY, "双乾支付 "); //双乾支付 
		PAY_METHODS.put(PAY_METHOD_GB, "Globebill支付");//钱宝支付
		PAY_METHODS.put(PAY_METHOD_PAYPALOUT_VISA, "支付宝(外卡visa支付)");
		PAY_METHODS.put(PAY_METHOD_PAYPALOUT_MASTER, "支付宝(外卡master支付)");
		PAY_METHODS.put(PAY_METHOD_PAYPALOUT_JCB, "支付宝(外卡jcb支付");
		PAY_METHODS.put(PAY_METHOD_OLD_PAYPALOUT_VISA, "支付宝(外卡visa支付)");
		PAY_METHODS.put(PAY_METHOD_OLD_PAYPALOUT_MASTER, "支付宝(外卡master支付)");
		PAY_METHODS.put(PAY_METHOD_OLD_PAYPALOUT_JCB, "支付宝(外卡jcb支付");
		PAY_METHODS.put(PAY_METHOD_PAYDOLLAR, "paydollar支付");
	}
	
	//将广告位放置在Map集合中，便于前台页面获取
	public static Map<String ,String> AD_LOCATIONS=new HashMap<String ,String>();
	static{
		AD_LOCATIONS.put("AD_LOCATION_INDEX", "店铺首页顶部广告");
		AD_LOCATIONS.put("AD_LOCATION_SUBJECT_WORD", "店铺聚焦专题文字广告");
		AD_LOCATIONS.put("AD_LOCATION_SUBJECT", "店铺聚集专题");
		AD_LOCATIONS.put("AD_LOCATION_APP", "店铺应用广告");
		AD_LOCATIONS.put("AD_LOCATION_LEFT", "店铺左侧广告");
		AD_LOCATIONS.put("AD_LOCATION_TOP", "店铺顶部广告");
		AD_LOCATIONS.put("AD_BANANA_SERVICE", "底部服务指南广告");
		
		
	}
	//商城广告定义
	public static Map<String ,String> AD_TVPAD=new HashMap<String ,String>();
	static{
		AD_TVPAD.put("AD_TVPAD_LEFT", "商城左侧广告");
		AD_TVPAD.put("AD_TVPAD_INDEX", "商城首页顶部广告");
		AD_TVPAD.put("AD_TVPAD_RIGHT1", "商城右侧第一个广告");
		AD_TVPAD.put("AD_TVPAD_RIGHT2", "商城右侧第二个广告");
		AD_TVPAD.put("AD_TVPAD_BOTTOM", "商城下方广告");
		AD_TVPAD.put("AD_TVPAD_ENTER", "商城华扬入口");
		AD_TVPAD.put("AD_TVPAD_SPECIAL", "商城专卖店广告");
		AD_TVPAD.put("AD_TVPAD_INDEX_BOTTOM", "商城首页底部广告位");
		AD_TVPAD.put("AD_BANANA_ATTENTION", "商城关注我们广告位");
		AD_TVPAD.put("AD_BANANA_SERVICE", "商城底部服务指南广告位");
	}

	
	//Banner图位置字典定义
	public static String BANNER_LOCATION_TOP="BANNER_LOCATION_TOP";  //导航页面位置
	public static String BANNER_LOCATION_DOWN="BANNER_LOCATION_DOWN";//店铺显示位置
	
	public static float rateNum=6.3f;//美元与人民币的汇率
		
}
