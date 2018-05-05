package com.lshop.manage.common.action;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.exception.ActionException;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvStore;

public class BaseManagerAction extends com.gv.core.web.action.BaseAction {
	
	private String flag = "";
	public String getFlag() {

		return flag;
	}

	protected void setFlag() {
		BaseUsers user=	(BaseUsers) getRequest().getSession().getAttribute("USER");
		if(user!=null&&user.getStoreFlag()!=null){
			flag=user.getStoreFlag();
		}
	}

	@Override
	protected Object doService(String serviceId, String methodName, Dto dto)
			throws ActionException {
		
		setFlag();
		if(dto==null){
			dto= new BaseDto();
		}
		dto.put("flag", flag);
		
		// TODO Auto-generated method stub
		return super.doService(serviceId, methodName, dto);
	}

}
