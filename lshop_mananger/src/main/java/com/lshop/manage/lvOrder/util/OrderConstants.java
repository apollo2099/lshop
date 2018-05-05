package com.lshop.manage.lvOrder.util;

import java.util.HashMap;
import java.util.Map;

public class OrderConstants {
	
	    public static String ORDER_IMPORT_ERROR_00000="00000";
	    public static String ORDER_IMPORT_ERROR_00001="00001";
	    public static String ORDER_IMPORT_ERROR_00002="00002";
	    public static String ORDER_IMPORT_ERROR_00003="00003";
	    public static String ORDER_IMPORT_ERROR_00004="00004";
	    public static String ORDER_IMPORT_ERROR_00005="00005";
	    public static String ORDER_IMPORT_ERROR_00006="00006";
	    public static String ORDER_IMPORT_ERROR_00007="00007";
	    public static String ORDER_IMPORT_ERROR_00008="00008";
	    public static String ORDER_IMPORT_ERROR_00009="00009";
	    public static String ORDER_IMPORT_ERROR_00010="00010";
	    public static String ORDER_IMPORT_ERROR_00011="00011";
	    public static String ORDER_IMPORT_ERROR_00012="00012";
	    public static String ORDER_IMPORT_ERROR_00013="00013";
	    public static String ORDER_IMPORT_ERROR_00014="00014";
	    public static String ORDER_IMPORT_ERROR_00015="00015";
	    public static String ORDER_IMPORT_ERROR_00016="00016";
	    public static String ORDER_IMPORT_ERROR_00017="00017";
	    public static String ORDER_IMPORT_ERROR_00018="00018";
	    public static String ORDER_IMPORT_ERROR_00019="00019";
	    public static String ORDER_IMPORT_ERROR_00020="00020";
	    public static String ORDER_IMPORT_ERROR_00021="00021";
	    public static String ORDER_IMPORT_ERROR_00022="00022";
	    public static String ORDER_IMPORT_ERROR_00023="00023";
	    public static String ORDER_IMPORT_ERROR_00024="00024";
	    public static String ORDER_IMPORT_ERROR_10000="10000";
	    public static String ORDER_IMPORT_ERROR_10001="10001";
	    public static String ORDER_IMPORT_ERROR_20000="20000";
	    public static String ORDER_IMPORT_ERROR_20001="20001";
	    public static String ORDER_IMPORT_ERROR_20002="20002";
	    public static String ORDER_IMPORT_ERROR_20003="20003";
	    public static String ORDER_IMPORT_ERROR_30000="30000";
	    public static String ORDER_IMPORT_ERROR_30001="30001";
	    public static String ORDER_IMPORT_ERROR_30002="30002";
	    public static String ORDER_IMPORT_ERROR_EXP="EXP";
	    
	
	    
	    //将支付方式放在Map集合，便于前台页面获取
		public static Map<String,Object> ORDER_IMPORT_ERROR=new HashMap<String,Object>();
		static{
			ORDER_IMPORT_ERROR.put(ORDER_IMPORT_ERROR_00000, "成功");
			ORDER_IMPORT_ERROR.put(ORDER_IMPORT_ERROR_00001, "第三方订单号为空");
			ORDER_IMPORT_ERROR.put(ORDER_IMPORT_ERROR_00002, "第三方订单下单时间为空");
			ORDER_IMPORT_ERROR.put(ORDER_IMPORT_ERROR_00003, "支付方式为空");
			ORDER_IMPORT_ERROR.put(ORDER_IMPORT_ERROR_00004, "支付方式填写错误");
			ORDER_IMPORT_ERROR.put(ORDER_IMPORT_ERROR_00005, "运费为空");
			ORDER_IMPORT_ERROR.put(ORDER_IMPORT_ERROR_00006, "订单总金额为空");
			ORDER_IMPORT_ERROR.put(ORDER_IMPORT_ERROR_00007, "用户邮箱为空");
			ORDER_IMPORT_ERROR.put(ORDER_IMPORT_ERROR_00008, "收货人为空");
			ORDER_IMPORT_ERROR.put(ORDER_IMPORT_ERROR_00009, "订单已经存在");
			ORDER_IMPORT_ERROR.put(ORDER_IMPORT_ERROR_00010, "收货地址街道为空");
			ORDER_IMPORT_ERROR.put(ORDER_IMPORT_ERROR_00011, "收货地址城市为空");
			ORDER_IMPORT_ERROR.put(ORDER_IMPORT_ERROR_00012, "收货地址省份为空");
			ORDER_IMPORT_ERROR.put(ORDER_IMPORT_ERROR_00013, "收货地址国家编码为空");
			ORDER_IMPORT_ERROR.put(ORDER_IMPORT_ERROR_00014, "sku为空");
			ORDER_IMPORT_ERROR.put(ORDER_IMPORT_ERROR_00015, "商品单价为空");
			ORDER_IMPORT_ERROR.put(ORDER_IMPORT_ERROR_00016, "商品数量为空");
			ORDER_IMPORT_ERROR.put(ORDER_IMPORT_ERROR_00017, "第三方支付单号为空");
			ORDER_IMPORT_ERROR.put(ORDER_IMPORT_ERROR_00018, "币种为空");
			ORDER_IMPORT_ERROR.put(ORDER_IMPORT_ERROR_00019, "订单来源为空");
			ORDER_IMPORT_ERROR.put(ORDER_IMPORT_ERROR_00020, "联系电话为空");
			ORDER_IMPORT_ERROR.put(ORDER_IMPORT_ERROR_00021, "运费格式错误");
			ORDER_IMPORT_ERROR.put(ORDER_IMPORT_ERROR_00022, "订单总金额格式错误");
			ORDER_IMPORT_ERROR.put(ORDER_IMPORT_ERROR_00023, "商品单价格式错误");
			ORDER_IMPORT_ERROR.put(ORDER_IMPORT_ERROR_00024, "商品总价格式错误");
			ORDER_IMPORT_ERROR.put(ORDER_IMPORT_ERROR_10000, "保存订单成功");
			ORDER_IMPORT_ERROR.put(ORDER_IMPORT_ERROR_10001, "订单已经存在");
			ORDER_IMPORT_ERROR.put(ORDER_IMPORT_ERROR_20000, "保存订单详情成功");
			ORDER_IMPORT_ERROR.put(ORDER_IMPORT_ERROR_20001, "SKU不存在");
			ORDER_IMPORT_ERROR.put(ORDER_IMPORT_ERROR_20002, "商品不存在");
			ORDER_IMPORT_ERROR.put(ORDER_IMPORT_ERROR_20003, "该订单商品详情已经存在");
			ORDER_IMPORT_ERROR.put(ORDER_IMPORT_ERROR_30000, "保存订单地址成功");
			ORDER_IMPORT_ERROR.put(ORDER_IMPORT_ERROR_30001, "订单地址信息已经存在");
			ORDER_IMPORT_ERROR.put(ORDER_IMPORT_ERROR_30002, "国家区域不存在");
			ORDER_IMPORT_ERROR.put(ORDER_IMPORT_ERROR_EXP, "保存订单异常");
		}
}
