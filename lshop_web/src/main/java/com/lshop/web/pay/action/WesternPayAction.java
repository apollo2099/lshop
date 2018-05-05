package com.lshop.web.pay.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.util.StringUtil;
import com.lshop.common.action.BaseAction;
import com.lshop.common.pojo.logic.LvWesternInfo;
import com.lshop.common.util.Constants;

/**
 * 西联汇款
 * @author xusl
 *
 */
@Controller("WesternPayAction")
@Scope("prototype")
public class WesternPayAction extends BaseAction {

	private LvWesternInfo vo;
	private String useremail;
	/**
	 * 支付
	 * @return
	 */
	public String pay(){
		if (vo==null||vo.getMtcn().length()!=10&&vo.getOid()==null||"".equals(vo.getOid().trim())) {
			return ERROR;
		}
		vo.setCreateTime(new Date());
		dto.setDefaultPo(vo);
		Integer status=(Integer) doService("WesternPayService", "saveWesternInfo", dto);
		 if (status==1) {
			
			 return SUCCESS;
		}
		 return ERROR;
		
	}
	public String updateWesternInfo(){
		if (vo==null||vo.getMtcn().length()!=10&&vo.getOid()==null||"".equals(vo.getOid().trim())) {
			return ERROR;
		}
		vo.setCreateTime(new Date());
		dto.setDefaultPo(vo);
		Integer status=(Integer) doService("WesternPayService", "saveWesternInfo", dto);
		try {
			String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); //获取当前店铺所属系统（华扬orBanana）
			addCookie("issuccess", status+"", false, -1,Constants.MALL_TO_DOMAIN_POSTFIX.get(mallSystem));
		} catch (Exception e) {
			
		}
		 if (status==1) {
			 return SUCCESS;
		}
		 return ERROR;
	}

	public String isExistsMTCN() {
		try {
			PrintWriter out = this.getResponse().getWriter();
			if (vo != null && !StringUtil.IsNullOrEmpty(vo.getMtcn())) {
				dto.put("mtch", vo.getMtcn());
				boolean flag = (Boolean) doService("WesternPayService", "isExistsMTCN", dto);
				out.print(flag);
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public LvWesternInfo getVo() {
		return vo;
	}
	public void setVo(LvWesternInfo vo) {
		this.vo = vo;
	}
	
}
