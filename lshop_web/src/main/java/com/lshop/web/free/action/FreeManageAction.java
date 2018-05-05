package com.lshop.web.free.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lshop.common.action.BaseAction;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvEmailTpl;
import com.lshop.common.util.Constants;

/**
 * 免费试用模块
 * @author zhengxue
 * @version 2.0 2012-08-27
 *
 */
@SuppressWarnings("serial")
@Controller("FreeManageAction")
@Scope("prototype")
public class FreeManageAction extends BaseAction {

	private Integer mark; //1，验证码不正确   2，申请成功   3，申请失败
	
	/**
	 * 跳转至免费试用模块
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String toFree() throws Exception{
		
		//获取国家列表
		List<LvArea>contryList=(List<LvArea>)this.doService("ShopCartService", "getContryList", dto);
		this.getRequest().setAttribute("contryList", contryList);
		
		this.getRequest().setAttribute("cMark", mark);
		
		return "toFree";
	}
	
	/**
	 * 发送申请邮件
	 * @return
	 * @throws Exception
	 */
	public String toEmail() throws Exception{
		
		//获取申请人信息
		String name = this.getRequest().getParameter("name");
		String tel = this.getRequest().getParameter("tel");
		String email = this.getRequest().getParameter("email");
		String postCode = this.getRequest().getParameter("postCode");
		String adress = this.getRequest().getParameter("adress");
		String cityName = this.getRequest().getParameter("cityName");
		String provinceName = this.getRequest().getParameter("provinceName");
		String contryName = this.getRequest().getParameter("contryName");
		
		//检验验证码是否正确
		String code = this.getRequest().getParameter("code");
		if(!code.trim().equals(this.getSession().getAttribute("rcode"))){
			mark=1;
			return "toFreeAction";
		}
		
		this.getSession().removeAttribute("rcode");
		
		//将申请人信息发送邮件给管理员
		dto.put("tplKey", "USER_FREE");
		dto.put("storeId", this.getFlag());
		LvEmailTpl	lvEmailTpl = (LvEmailTpl) this.doService("lvEmailManageService","get", dto);
		if(null!=lvEmailTpl){
			dto.put("mallFlag", Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag())); //获取当前店铺所属系统（华扬orBanana）
			dto.put("title", lvEmailTpl.getEnTitle());
			dto.put("userEmail", this.getText("email.free")); //该申请人信息应该发送到的管理员邮箱地址market@hmall.hk
			String body=(lvEmailTpl.getEn()+lvEmailTpl.getZh()).replace("{name}", name).replace("{tel}", tel).replace("{email}", email).replace("{postCode}", postCode).replace("{adress}", adress).replace("{cityName}", cityName).replace("{provinceName}", provinceName).replace("{contryName}", contryName);
			dto.put("content", body);
		    doService("emailSendService", "sendEmailNotice", dto);
		   	mark=2;
		}
		
		mark=3;
		return "toFreeAction";
	}

	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

	
}
