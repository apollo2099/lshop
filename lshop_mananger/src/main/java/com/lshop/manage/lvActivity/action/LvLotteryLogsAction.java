/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvActivity.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.lshop.common.pojo.logic.LvAccountPhysicalTicket;
import com.lshop.common.pojo.logic.LvAccountPrize;
import com.lshop.common.pojo.logic.LvActivity;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvCouponType;
import com.lshop.common.pojo.logic.LvLotteryLogs;
import com.lshop.common.pojo.logic.LvLotteryPrize;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvProductType;
import com.lshop.common.pojo.logic.LvShopProductType;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.manage.common.action.BaseManagerAction;
import com.gv.core.web.action.BaseAction;
import com.gv.core.util.ObjectUtils;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Controller("LvLotteryLogsAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvLotteryLogsAction extends BaseManagerAction{
	private static final Log logger	= LogFactory.getLog(LvLotteryLogsAction.class);
	private LvLotteryLogs lvLotteryLogs = new LvLotteryLogs();
	private LvAccountPhysicalTicket lvAccountPhysicalTicket;
	private LvAccountPrize lvAccountPrize;
	private Short prizeType;
	private String activityCode;




	/**
	 * 列表
	 * @return
	 * @throws ActionException
	 */
	public String list()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvLotteryLogsAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("lvLotteryLogs", lvLotteryLogs);
		
		page = (Pagination)this.doService("LvLotteryLogsService", "findPage", dto);
		
		List<LvLotteryLogs> list=(List<LvLotteryLogs>) page.getList();
		List<LvLotteryLogs> listTmp=new ArrayList<LvLotteryLogs>();
		//根据日志查询用户奖品信息
		for (LvLotteryLogs lvLotteryLogs : list) {
			dto.put("accountPrizeCode", lvLotteryLogs.getAccountPrizeCode());
			LvAccountPrize lvAccountPrize=(com.lshop.common.pojo.logic.LvAccountPrize) this.doService("LvAccountPrizeService", "findByCode", dto);
			if(ObjectUtils.isNotEmpty(lvAccountPrize)&&ObjectUtils.isNotEmpty(lvAccountPrize.getStatus())){
				lvLotteryLogs.setStatus(lvAccountPrize.getStatus());
				lvLotteryLogs.setLvAccountPrize(lvAccountPrize);
			}
			listTmp.add(lvLotteryLogs);
		}
		page.setList(listTmp);

    	if (logger.isInfoEnabled()){
			logger.info("***** LvLotteryLogsAction.list() method end*****");
		}
		return LIST;
	}
	
	/**
	 * 进入添加页面
	 * @return
	 * @throws ActionException
	 */
	public String befSave()throws ActionException{
		return "befSave";
	}
	
	/**
	 * 添加
	 * @return
	 * @throws ActionException
	 */
	public String save()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvLotteryLogsAction.save() method begin*****");
		}

		//根据活动code查询抽奖活动信息
		dto.put("code", lvLotteryLogs.getActivityCode());
		LvActivity lvActivity= (LvActivity) this.doService("LvActivityService", "findByCode", dto);
		if(lvActivity!=null){
			lvLotteryLogs.setActivityName(lvActivity.getActivityTitle());
		}
		 
		lvLotteryLogs.setIsSystemFlag(Short.parseShort("1"));
		dto.put("lvLotteryLogs", lvLotteryLogs);
		this.doService("LvLotteryLogsService", "save", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvLotteryLogsAction.save() method end*****");
		}
		return AJAX;
	}


	/**
	 * 详情
	 * @return
	 * @throws ActionException
	 */
	public String view()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvLotteryLogsAction.view() method begin*****");
		}
		dto.put("lvLotteryLogs", lvLotteryLogs);
		lvLotteryLogs = (LvLotteryLogs)this.doService("LvLotteryLogsService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvLotteryLogsAction.view() method end*****");
		}
		return "view";
	}
	
	/**
	 * 跳入编辑页面
	 * @return
	 * @throws ActionException
	 */
	public String befEdit()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvLotteryLogsAction.befEdit() method begin*****");
		}
		dto.put("lvLotteryLogs", lvLotteryLogs);
		lvLotteryLogs = (LvLotteryLogs)this.doService("LvLotteryLogsService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvLotteryLogsAction.befEdit() method end*****");
		}
		return "edit";
	}
	/**
	 * 编辑
	 * @return
	 * @throws ActionException
	 */
	public String edit()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvLotteryLogsAction.edit() method begin*****");
		}
		dto.put("lvLotteryLogs", lvLotteryLogs);
		this.doService("LvLotteryLogsService", "update", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvLotteryLogsAction.edit() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 
	 * @Method: toShipment 
	 * @Description:  [跳转到实物商品发货页面]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2014-8-19 上午10:40:18]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2014-8-19 上午10:40:18]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return
	 * @throws ActionException 
	 * @return String
	 */
	public String toShipment()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvLotteryLogsAction.edit() method begin*****");
		}
		dto.put("accountPrizeCode", lvLotteryLogs.getAccountPrizeCode());
		LvAccountPrize lvAccountPrize=(com.lshop.common.pojo.logic.LvAccountPrize) this.doService("LvAccountPrizeService", "findByCode", dto);
		LvAccountPhysicalTicket apt= (LvAccountPhysicalTicket) this.doService("LvAccountPrizeService", "findByAcountPrizeCode", dto);
		this.getRequest().setAttribute("lvAccountPrize", lvAccountPrize);
		this.getRequest().setAttribute("lvAccountPhysicalTicket", apt);
		
		//查询所以国家区域信息
		List<LvArea> areaList=(List<LvArea>) this.doService("LvAreaService", "getAll", dto);
    	this.getRequest().setAttribute("areaList", areaList);
		return "toShipment";
	}
	
	/**
	 * 
	 * @Method: shipment 
	 * @Description:  [实物奖品线下发货处理]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2014-8-19 上午10:40:41]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2014-8-19 上午10:40:41]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws ActionException 
	 * @return String
	 */
	public String shipment()throws ActionException{
		dto.put("lvAccountPrize", lvAccountPrize);
		dto.put("lvAccountPhysicalTicket", lvAccountPhysicalTicket);
		this.doService("LvAccountPrizeService", "updateAccountPrizeStatus", dto);
		return AJAX;
	}
	/**
	 * 删除
	 * @return
	 * @throws ActionException
	 */
	public String del()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvLotteryLogsAction.del() method begin*****");
		}
		dto.put("lvLotteryLogs", lvLotteryLogs);
		//删除lvLotteryLogs
		this.doService("LvLotteryLogsService", "del", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvLotteryLogsAction.del() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 批量删除
	 * @return
	 * @throws ActionException
	 */
	public String delList()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvLotteryLogsAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				lvLotteryLogs.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("lvLotteryLogs", lvLotteryLogs);
				//删除lvLotteryLogs
				this.doService("LvLotteryLogsService", "del", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvLotteryLogsAction.delList() method end*****");
		}
		return AJAX;
	}
	
	
	
    /**
     * 
     * @Method: toPrizeByType 
     * @Description:  [根据类型查询优惠券信息或者奖项物品信息]  
     * @Author:       [liaoxj]     
     * @CreateDate:   [2014-8-14 上午10:59:35]   
     * @UpdateUser:   [liaoxj]     
     * @UpdateDate:   [2014-8-14 上午10:59:35]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return 
     * @return String
     */
	public String toPrizeByType(){
		PrintWriter out = null;
		if(ObjectUtils.isNotEmpty(prizeType)&&ObjectUtils.isNotEmpty(activityCode)){
			Map<String,Object>  mapTmp=new HashMap<String,Object>();
			//查询奖项物品信息
			dto.put("prizeType", prizeType);
			dto.put("activityCode", activityCode);
			List<LvLotteryPrize> list=(List<LvLotteryPrize>) this.doService("LvLotteryPrizeService", "findAll", dto);
			List listTmp=new ArrayList();
			if(list!=null&&list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					LvLotteryPrize prize=list.get(i);
					Map<String,Object>  map=new HashMap<String,Object>();
					map.put("prizeCode", prize.getCode());
					map.put("prizeName", prize.getPrizeName());
					listTmp.add(map);
				}
			}
			mapTmp.put("listTmp", listTmp);

			try {
				response.setContentType("text/html;charset=UTF-8");   
				response.setCharacterEncoding("UTF-8");
			    out=this.getResponse().getWriter();
			    String jsonTmp = JSONObject.fromObject(mapTmp).toString();
			    logger.info("json_message"+jsonTmp);
				out.print(jsonTmp );
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		return null;
	}
	
	public LvLotteryLogs getLvLotteryLogs() {
		return lvLotteryLogs;
	}

	public void setLvLotteryLogs(LvLotteryLogs lvLotteryLogs) {
		this.lvLotteryLogs = lvLotteryLogs;
	}
	
	public Short getPrizeType() {
		return prizeType;
	}

	public void setPrizeType(Short prizeType) {
		this.prizeType = prizeType;
	}

	public String getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}
	
	public LvAccountPrize getLvAccountPrize() {
		return lvAccountPrize;
	}

	public void setLvAccountPrize(LvAccountPrize lvAccountPrize) {
		this.lvAccountPrize = lvAccountPrize;
	}

	public LvAccountPhysicalTicket getLvAccountPhysicalTicket() {
		return lvAccountPhysicalTicket;
	}

	public void setLvAccountPhysicalTicket(
			LvAccountPhysicalTicket lvAccountPhysicalTicket) {
		this.lvAccountPhysicalTicket = lvAccountPhysicalTicket;
	}


}

 