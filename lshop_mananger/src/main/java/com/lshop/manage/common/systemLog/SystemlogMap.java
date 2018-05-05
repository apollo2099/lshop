package com.lshop.manage.common.systemLog;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.gv.base.model.spring.ApplicationContextHolder;

/**
 * AOP操作日志管理
 * @author zhengxue 2014-06-26
 *
 */
public class SystemlogMap {

	private static Map<String, BaseSystemLog> map = new HashMap<String, BaseSystemLog>();

	public static void initMap() {
		
		// 订单管理com.lshop.manage.lvOrder.action.LvOrderAction.updateStatus
		BaseSystemLog orderManageLog = getBean("OrderManageLog");
		map.put("com.lshop.manage.lvOrder.action.LvOrderAction.edit", orderManageLog); //修改
		map.put("com.lshop.manage.lvOrder.action.LvOrderAction.del", orderManageLog); //删除
		map.put("com.lshop.manage.lvOrder.action.LvOrderAction.audit", orderManageLog); //审核
		map.put("com.lshop.manage.lvOrder.action.LvOrderAction.editBreakRemark", orderManageLog); //退货
		map.put("com.lshop.manage.lvOrder.action.LvOrderAction.updateStatus", orderManageLog); //确认收货
		map.put("com.lshop.manage.lvOrder.action.LvOrderAction.updateOrderStatus", orderManageLog); //修改状态
		map.put("com.lshop.manage.lvOrder.action.LvOrderAction.updatePrice", orderManageLog); //修改金额
		map.put("com.lshop.manage.lvOrder.action.LvOrderAction.synSendOrder", orderManageLog); //同步物流
		map.put("com.lshop.manage.lvOrder.action.LvOrderAction.sendOrder", orderManageLog); //发货
		map.put("com.lshop.manage.lvOrder.action.LvOrderAction.changeOrder", orderManageLog); //转单
		map.put("com.lshop.manage.lvOrder.action.LvOrderAction.doShop", orderManageLog); //商家自主下单
		map.put("com.lshop.manage.lvProductComment.action.LvProductCommentAction.audit", orderManageLog); //审核产品评论
		map.put("com.lshop.manage.lvProductComment.action.LvProductCommentAction.add", orderManageLog); //添加系统评论
		
		//商品管理
		BaseSystemLog productManageLog = getBean("ProductManageLog");
		map.put("com.lshop.manage.lvProduct.action.LvProductAction.add", productManageLog); //发布商品
		map.put("com.lshop.manage.lvProduct.action.LvProductAction.edit", productManageLog); //修改商品
		map.put("com.lshop.manage.lvProduct.action.LvProductAction.updateSupport", productManageLog); //上下架
		map.put("com.lshop.manage.lvProduct.action.LvProductAction.commend", productManageLog); //推荐组合 
		map.put("com.lshop.manage.lvProductImage.action.LvProductImageAction.add", productManageLog); //效果图-添加
		map.put("com.lshop.manage.lvProductImage.action.LvProductImageAction.edit", productManageLog); //效果图-修改
		map.put("com.lshop.manage.lvProductImage.action.LvProductImageAction.del", productManageLog); //效果图-删除
		map.put("com.lshop.manage.lvProductProperty.action.LvProductPropertyAction.add", productManageLog); //扩展页-添加
		map.put("com.lshop.manage.lvProductProperty.action.LvProductPropertyAction.edit", productManageLog); //扩展页-修改
		map.put("com.lshop.manage.lvProductProperty.action.LvProductPropertyAction.del", productManageLog); //扩展页-删除
		
		//商品库管理
		BaseSystemLog productStorageLog = getBean("ProductStorageLog");
		map.put("com.lshop.manage.lvPubProduct.action.LvPubProductAction.save", productStorageLog); //新增商品库
		map.put("com.lshop.manage.lvPubProduct.action.LvPubProductAction.edit", productStorageLog); //修改商品库
		map.put("com.lshop.manage.lvPubProduct.action.LvPubProductAction.del", productStorageLog); //删除商品库
		map.put("com.lshop.manage.lvPubPackage.action.LvPubPackageAction.save", productStorageLog); //新增套餐
		map.put("com.lshop.manage.lvPubPackage.action.LvPubPackageAction.edit", productStorageLog); //修改套餐
		map.put("com.lshop.manage.lvPubPackage.action.LvPubPackageAction.del", productStorageLog); //删除套餐
		
		//优惠券管理
		BaseSystemLog couponManageLog = getBean("CouponManageLog");
		map.put("com.lshop.manage.lvCouponType.action.LvCouponTypeAction.save", couponManageLog); //添加优惠券
		map.put("com.lshop.manage.lvCouponType.action.LvCouponTypeAction.couponDownload", couponManageLog); //下载优惠码
		map.put("com.lshop.manage.lvCouponType.action.LvCouponTypeAction.updateTotalNumber", couponManageLog); //追加数量
		map.put("com.lshop.manage.lvCouponType.action.LvCouponTypeAction.updateExtendDate", couponManageLog); //延长有效期
		map.put("com.lshop.manage.lvCouponType.action.LvCouponTypeAction.updateDisable", couponManageLog); //停用
		map.put("com.lshop.manage.lvCouponType.action.LvCouponTypeAction.updateStatus", couponManageLog); //启用
		
		//活动管理
		BaseSystemLog activityManageLog = getBean("ActivityManageLog");
		map.put("com.lshop.manage.lvActivity.action.LvActivityAction.save", activityManageLog); //新增
		map.put("com.lshop.manage.lvActivity.action.LvActivityAction.edit", activityManageLog); //修改
		map.put("com.lshop.manage.lvActivity.action.LvActivityAction.updateStatus", activityManageLog); //启/停用
		
		//团购活动
		BaseSystemLog groupManageLog = getBean("GroupManageLog");
		map.put("com.lshop.manage.lvGroupBuy.action.LvGroupBuyAction.add", groupManageLog); //新增团购活动
		map.put("com.lshop.manage.lvGroupBuy.action.LvGroupBuyAction.edit", groupManageLog); //修改团购活动
		map.put("com.lshop.manage.lvGroupBuy.action.LvGroupBuyAction.del", groupManageLog); //删除团购活动
		map.put("com.lshop.manage.lvGroupProperty.action.LvGroupPropertyAction.add", groupManageLog); //新增团购扩展属性
		map.put("com.lshop.manage.lvGroupProperty.action.LvGroupPropertyAction.edit", groupManageLog); //修改团购扩展属性
		map.put("com.lshop.manage.lvGroupProperty.action.LvGroupPropertyAction.del", groupManageLog); //删除团购扩展属性
		
	}

	public static BaseSystemLog getName(String key) {
		BaseSystemLog baseSystemLog = null;
		if (MapUtils.isEmpty(map)) {
			initMap();
		}
		if (map.containsKey(key)) {
			baseSystemLog = map.get(key);
		}
		return baseSystemLog;
	}

	private static BaseSystemLog getBean(String beanName) {
		return (BaseSystemLog) ApplicationContextHolder.getBean(beanName);
	}
}
