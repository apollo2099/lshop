package com.lshop.web.tvpadTopic.action;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lshop.common.action.BaseAction;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvDealerApplication;
import com.lshop.common.pojo.logic.LvServiceNetwork;

/**
 * 专题模块
 * @author zhengxue
 * @version 2.0 2012-08-27
 *
 */
@SuppressWarnings("serial")
@Controller("TvpadTopicManageAction")
@Scope("prototype")
public class TvpadTopicManageAction extends BaseAction {
	
	private LvDealerApplication lvDealerApplication;

	public String toTvpadzt() throws Exception{
		return "toTvpadzt";
	}
	
	/**
	 * 前往服务网点
	 * @return
	 * @throws Exception
	 */
	public String toServiceNetwork() throws Exception{
		
		dto.put("storeFlag", this.getFlag());
		List<LvServiceNetwork> networkList = (List<LvServiceNetwork>)this.doService("TvpadTopicService", "getNetworkList", dto);
		
		//封装国旗
		if(null!=networkList && networkList.size()>0){
			for(LvServiceNetwork network : networkList){
				//查找该国家对应的国旗
				dto.put("areaId", network.getContryId());
				LvArea lvArea = (LvArea)this.doService("TvpadTopicService", "getAreaById", dto);
				network.setIcon(lvArea.getIcon());
			}
		}
		
		this.getRequest().setAttribute("networkList", networkList);
		return "serviceNetwork";
	}
	
	/**
	 * 前往经销商申请
	 * @return
	 * @throws Exception
	 */
	public String toApplication() throws Exception{
		return "app";
	}
	
	/**
	 * 保存经销商申请信息
	 * @return
	 * @throws Exception
	 */
	public String saveAgencyApplication() throws Exception{
		
		lvDealerApplication.setCreateTime(new Date());
		lvDealerApplication.setStoreId(this.getFlag());
		dto.setDefaultPo(lvDealerApplication);
		Boolean flag = (Boolean)this.doService("TvpadTopicService", "saveApplication", dto);
		
		this.getRequest().setAttribute("flag", flag);
		
		return "agencyApplication";
	}
	
	
	public LvDealerApplication getLvDealerApplication() {
		return lvDealerApplication;
	}

	public void setLvDealerApplication(LvDealerApplication lvDealerApplication) {
		this.lvDealerApplication = lvDealerApplication;
	}
}
