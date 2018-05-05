package com.lshop.web.applist.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lshop.common.action.BaseAction;
import com.lshop.common.pojo.logic.LvApp;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvProductType;

/**
 * 运用模块
 * @author zhengxue
 * @since 2.0 2012-07-23
 *
 */
@SuppressWarnings("serial")
@Controller("ApplistManageAction")
@Scope("prototype")
public class ApplistManageAction extends BaseAction {
	
	/**
	 * 跳转至运用主页
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String toApplist(){
		
		//获取所有的产品类型
		dto.put("storeFlag", this.getFlag());
		List<LvProductType> typeList=(List<LvProductType>)this.doService("ProductService", "getTypes", dto);
		
		if(typeList!=null){
			List aList=new ArrayList(); //存放应用列表
			for(LvProductType pType:typeList){
				if(pType.getTypeFlag()==2){
					dto.put("ptype", pType.getCode());
					List<LvApp> appList=(List<LvApp>)this.doService("ApplistService", "getApps", dto);

					Map map=new HashMap();
					map.put("list", appList);
					map.put("title", pType.getTypeName());
					aList.add(map);
				}
			}
			this.getRequest().setAttribute("aList", aList);
		}
		this.getRequest().setAttribute("flag", "app");
		return "applist";
	}
	
	/**
	 * 跳转至某一个运用详情页面
	 * 需要传递参数code
	 * @return
	 */
	public String toApp(){
		dto.put("storeFlag", this.getFlag());
		String appCode=this.getRequest().getParameter("appCode");
		dto.put("appCode", appCode);
		LvApp app=(LvApp)this.doService("ApplistService", "getAppByCode", dto);
		this.getRequest().setAttribute("app", app);
		this.getRequest().setAttribute("flag", app.getCode());
		return "app";
	}

}
