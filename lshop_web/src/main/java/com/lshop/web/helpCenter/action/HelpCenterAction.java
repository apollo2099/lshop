package com.lshop.web.helpCenter.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.exception.ActionException;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.action.BaseAction;
import com.lshop.common.pojo.logic.LvHelp;
import com.lshop.common.pojo.logic.LvHelpType;

/**
 * 帮助中心
 * @author zhengxue
 * @version 2.0 2012-07-09
 *
 */
@SuppressWarnings("serial")
@Controller("HelpCenterAction")
@Scope("prototype")
public class HelpCenterAction extends BaseAction {
	
	private Integer id;
	

	/**
	 * 显示所有的帮助名称
	 * 用于页面底部的帮助名称显示
	 * @return
	 * @throws ActionException
	 */
	@SuppressWarnings("unchecked")
	public String getHelps()throws ActionException{

		List<Object[]> objList=new ArrayList<Object[]>();; //包装所有的帮助类别和二级标题
		
		//获取所有的帮助类别
		dto.put("storeFlag", this.getFlag());
		List<LvHelpType> helpTypes = (List<LvHelpType>)this.doService("HelpCenterService", "getHelpTypes", dto);
		
		
		//获取每个帮助类别下面的帮助标题
		if(null!=helpTypes && helpTypes.size()>0){
			for(LvHelpType type : helpTypes){
				//dto里面放helpId
				dto.put("helpId", type.getId());
				List<LvHelp> helps = (List<LvHelp>)this.doService("HelpCenterService", "getHelpsByType", dto);
				if(null!=helps && helps.size()>0){
					Object[] obj = new Object[2];
					obj[0] = type;
					obj[1] = helps;
					objList.add(obj);
				}
			}
		}
		//移动端需要判断帮助中心首页
		this.getRequest().setAttribute("hid", id);
		//判断是不是直接点击的“帮助中心”
		if(ObjectUtils.isNullOrEmptyString(id)){
			id = helpTypes.get(0).getId();
		}
		
		//根据当前的帮助类型id查询帮助列表
    	dto.put("helpId", id);
    	List<LvHelp> helpList=(List<LvHelp>)doService("HelpCenterService", "getHelpsByType", dto);
    	this.getRequest().setAttribute("lvHelps",helpList );
    	
    	this.getRequest().setAttribute("objList", objList);
    	this.getRequest().setAttribute("id", id);
    	//得到分类标题
    	for (int i = helpTypes.size()-1; i >= 0; i--) {
			if (helpTypes.get(i).getId().equals(id)) {
				this.getRequest().setAttribute("htitle", helpTypes.get(i).getName());
				break;
			}
		}
		return "helpCenter";
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
