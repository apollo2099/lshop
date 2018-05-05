package com.lshop.web.mac.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.util.ObjectUtils;
import com.lshop.common.action.BaseAction;
import com.lshop.common.activity.vo.ActivityMac;
import com.lshop.common.pojo.logic.LvOrderMac;
import com.lshop.web.activity.service.ActivityMacService;
import com.lshop.web.order.service.OrderMacService;


@Controller("MacAction")
@Scope("prototype")
public class MacAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	
	@Resource
	private OrderMacService orderMacService;
	@Resource
	private ActivityMacService activityMacService;
	
	
	private String mac;
	private String productCode;
	
	/**
	 * 
	 * @Method: findMac 
	 * @Description:  [mac兑换规则限制
	 *                1.mac兑换总次数不能超过限制输入次数；
	 *                2.mac一天只能兑换一次
	 *                ]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015年7月16日 上午9:37:27]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015年7月16日 上午9:37:27]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 */
	public String findMac() {
		try {
			PrintWriter out = this.getResponse().getWriter();
			//根据产品查询mac兑换活动信息
			ActivityMac activityMac = activityMacService.findByProduct(productCode);
			if (ObjectUtils.isNotEmpty(activityMac)) {

				//根据mac查询兑换的总次数
				int macCount=orderMacService.countByMac(mac);
				if(macCount>=activityMac.getExchangeNum()){
					out.println(2);// mac兑换次数超过
				}else{
					//根据mac查询
					LvOrderMac orderMac = orderMacService.findByMac(mac);
					if (ObjectUtils.isNotEmpty(orderMac)) {
						out.println(1);// mac当天已兑换
					} else {
						out.println(0);// mac未使用
					}
				}
			} else {
				out.println(-1);// 活动结束
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	
}
