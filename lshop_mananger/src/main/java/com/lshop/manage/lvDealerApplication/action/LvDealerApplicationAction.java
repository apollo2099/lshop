package com.lshop.manage.lvDealerApplication.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvDealerApplication;
import com.lshop.common.pojo.logic.LvNetwork;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.manage.common.action.BaseManagerAction;

/**
 * 服务网点管理
 * @author zhengxue
 *
 */
@Controller("LvDealerApplicationAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvDealerApplicationAction  extends BaseManagerAction{
	
	private static final Log logger	= LogFactory.getLog(LvDealerApplicationAction.class);
	private LvDealerApplication lvDealerApplication;

	/**
	 * 列表
	 * @return
	 * @throws ActionException
	 */
	public String list()throws ActionException{
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvDealerApplicationAction.list() method begin*****");
		}
		
		dto.put("page", page);
		dto.setDefaultPo(lvDealerApplication);
		page = (Pagination)this.doService("LvDealerApplicationService", "findPage", dto);

    	if (logger.isInfoEnabled()){
			logger.info("***** LvDealerApplicationAction.list() method end*****");
		}
    	
		return LIST;
	}
	

	/**
	 * 详情
	 * @return
	 * @throws ActionException
	 */
	public String view()throws ActionException{
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvDealerApplicationAction.view() method begin*****");
		}
		
		dto.setDefaultPo(lvDealerApplication);
		lvDealerApplication = (LvDealerApplication)this.doService("LvDealerApplicationService", "get", dto);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvDealerApplicationAction.view() method end*****");
		}
		return "view";
	}
	
	public LvDealerApplication getLvDealerApplication() {
		return lvDealerApplication;
	}


	public void setLvDealerApplication(LvDealerApplication lvDealerApplication) {
		this.lvDealerApplication = lvDealerApplication;
	}

}
