/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvPaymentStyle.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvPaymentStyle;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvProductType;
import com.lshop.common.pojo.logic.LvShopProductType;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.Constants;
import com.lshop.manage.common.action.BaseManagerAction;

/**
 * @author 好视网络-网站研发部：唐迪
 * @version 1.0
 * @since 1.0
 */@Controller("LvPaymentStyleMngAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvPaymentStyleMngAction extends BaseManagerAction{
	private static final Log logger	= LogFactory.getLog(LvPaymentStyleMngAction.class);
	private LvPaymentStyle lvPaymentStyle = new LvPaymentStyle();

	public LvPaymentStyle getLvPaymentStyle() {
		return lvPaymentStyle;
	}

	public void setLvPaymentStyle(LvPaymentStyle lvPaymentStyle) {
		this.lvPaymentStyle = lvPaymentStyle;
	}
	
	
	/**
	 * 列表
	 * @return
	 * @throws ActionException
	 */
	public String list()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvPaymentStyleMngAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("model", lvPaymentStyle);
		page = (Pagination)this.doService("LvPaymentStyleService", "findPage", dto);
		
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAll", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
    	if (logger.isInfoEnabled()){
			logger.info("***** LvPaymentStyleMngAction.list() method end*****");
		}
		return LIST;
	}
	
	/**
	 * 进入添加页面
	 * @return
	 * @throws ActionException
	 */
	public String befSave()throws ActionException{
		List payDataList=(List)this.doService("LvPaymentStyleService", "findPaymentDataAll", dto);
		request.setAttribute("payDataList", payDataList);
		
//		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
//		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAllNoShop", dto);
//		this.getRequest().setAttribute("storeList", storeList);
		
		return "befSave";
	}
	
	/**
	 * 添加
	 * @return
	 * @throws ActionException
	 */
	public String save()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvPaymentStyleMngAction.save() method begin*****");
		}
		dto.put("model", lvPaymentStyle);
		Integer isccuess =(Integer) this.doService("LvPaymentStyleService", "save", dto);
		if(isccuess==2){
		 json.setMessage("该支付方式值在列表中已存在");
		 json.setStatusCode(json.STATUS_CODE_ERROR);
		}else{
		json.setStatusCode(200);
		}
		if (logger.isInfoEnabled()){
			logger.info("***** LvPaymentStyleMngAction.save() method end*****");
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
			logger.info("***** LvPaymentStyleMngAction.view() method begin*****");
		}
		dto.put("model", lvPaymentStyle);
		lvPaymentStyle = (LvPaymentStyle)this.doService("LvPaymentStyleService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvPaymentStyleMngAction.view() method end*****");
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
			logger.info("***** LvPaymentStyleMngAction.befEdit() method begin*****");
		}
		dto.put("model", lvPaymentStyle);
		lvPaymentStyle = (LvPaymentStyle)this.doService("LvPaymentStyleService", "get", dto);
		String params=lvPaymentStyle.getParams();
		if(params!=null&&!"".equals(params)){
		  if(lvPaymentStyle.getPayValue()==Constants.PAY_METHOD_ALIPAY||lvPaymentStyle.getPayValue()==Constants.PAY_METHOD_PAYPALOUT_JCB||lvPaymentStyle.getPayValue()==Constants.PAY_METHOD_PAYPALOUT_MASTER||lvPaymentStyle.getPayValue()==Constants.PAY_METHOD_PAYPALOUT_VISA||lvPaymentStyle.getPayValue()==Constants.PAY_METHOD_OLD_PAYPALOUT_VISA||lvPaymentStyle.getPayValue()==Constants.PAY_METHOD_OLD_PAYPALOUT_MASTER||lvPaymentStyle.getPayValue()==Constants.PAY_METHOD_OLD_PAYPALOUT_JCB){
			     JSONObject object=JSONObject.fromObject(params);
			     request.setAttribute("partner", (String)object.get("partner"));
			     request.setAttribute("seller_email", (String)object.get("seller_email"));
			     request.setAttribute("key", (String)object.get("key"));
			}else if(lvPaymentStyle.getPayValue()==Constants.PAY_METHOD_95EPAY){//双乾
				JSONObject object=JSONObject.fromObject(params);
				request.setAttribute("merno", (String)object.get("merno"));
				request.setAttribute("key", (String)object.get("key"));
			}else if(lvPaymentStyle.getPayValue()==Constants.PAY_METHOD_PAYPAL){//paypal
				JSONObject object=JSONObject.fromObject(params);
			    request.setAttribute("business", (String)object.get("business"));
				request.setAttribute("key", (String)object.get("key"));
			}else if(lvPaymentStyle.getPayValue()==Constants.PAY_METHOD_KQ){//快钱支付
				JSONObject object=JSONObject.fromObject(params);
				request.setAttribute("business", (String)object.get("business"));
			    request.setAttribute("termId", (String)object.get("termId"));
				request.setAttribute("key", (String)object.get("key"));
			}else if(lvPaymentStyle.getPayValue()==Constants.PAY_METHOD_PAYDOLLAR){
				JSONObject object=JSONObject.fromObject(params);
				request.setAttribute("merchantId", (String)object.get("merchantId"));
				request.setAttribute("key", (String)object.get("key"));
				request.setAttribute("successUrl", (String)object.get("successUrl"));
				request.setAttribute("failUrl", (String)object.get("failUrl"));
				request.setAttribute("cancelUrl", (String)object.get("cancelUrl"));
			}else if(lvPaymentStyle.getPayValue()==Constants.PAY_METHOD_ALIPAY_INITIAL){
				JSONObject object=JSONObject.fromObject(params);
				request.setAttribute("userId", (String)object.get("userId"));
				request.setAttribute("password", (String)object.get("password"));
				request.setAttribute("entityId", (String)object.get("entityId"));
			}else if(lvPaymentStyle.getPayValue()==Constants.PAY_METHOD_QH){
				JSONObject object=JSONObject.fromObject(params);
				request.setAttribute("account", (String)object.get("account"));
				request.setAttribute("terminal", (String)object.get("terminal"));
				request.setAttribute("secureCode", (String)object.get("secureCode"));				
			}		 
		}
		String paySysParams=lvPaymentStyle.getPaymentSystemParams();
		if(ObjectUtils.isNotEmpty(paySysParams)){
			JSONObject object=JSONObject.fromObject(paySysParams);
			request.setAttribute("paySysMerno", (String)object.get("paySysMerno"));
			request.setAttribute("paySysKey", (String)object.get("paySysKey"));
			request.setAttribute("paySysUrl", (String)object.get("paySysUrl"));
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvPaymentStyleMngAction.befEdit() method end*****");
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
			logger.info("***** LvPaymentStyleMngAction.edit() method begin*****");
		}
	    if(lvPaymentStyle.getUrl()!=null){
		lvPaymentStyle.setUrl(lvPaymentStyle.getUrl().trim());
	    }
	   
	    if(lvPaymentStyle.getPayChannel()==0){
	    	Map<String,String>map=new HashMap(); 
			if(lvPaymentStyle.getPayValue()==Constants.PAY_METHOD_ALIPAY||lvPaymentStyle.getPayValue()==Constants.PAY_METHOD_PAYPALOUT_JCB||lvPaymentStyle.getPayValue()==Constants.PAY_METHOD_PAYPALOUT_MASTER||lvPaymentStyle.getPayValue()==Constants.PAY_METHOD_PAYPALOUT_VISA||lvPaymentStyle.getPayValue()==Constants.PAY_METHOD_OLD_PAYPALOUT_VISA||lvPaymentStyle.getPayValue()==Constants.PAY_METHOD_OLD_PAYPALOUT_MASTER||lvPaymentStyle.getPayValue()==Constants.PAY_METHOD_OLD_PAYPALOUT_JCB){
			    map.put("partner", request.getParameter("partner").trim());
			    map.put("seller_email", request.getParameter("seller_email").trim());
			    map.put("key", request.getParameter("key").trim());
			}else if(lvPaymentStyle.getPayValue()==Constants.PAY_METHOD_95EPAY){//双乾
				map.put("merno", request.getParameter("merno").trim());
				map.put("key", request.getParameter("key").trim());
			}else if(lvPaymentStyle.getPayValue()==Constants.PAY_METHOD_PAYPAL){//paypal
				map.put("business", request.getParameter("business").trim());
				map.put("key", request.getParameter("key").trim());
			}else if(lvPaymentStyle.getPayValue()==Constants.PAY_METHOD_KQ){//快钱支付
				map.put("business", request.getParameter("business").trim());
				map.put("termId", request.getParameter("termId").trim());
				map.put("key", request.getParameter("key").trim());
			}else if(lvPaymentStyle.getPayValue()==Constants.PAY_METHOD_PAYDOLLAR){//paydollar支付
				map.put("merchantId", request.getParameter("merchantId").trim());
				map.put("key", request.getParameter("key").trim());
				map.put("successUrl", request.getParameter("successUrl").trim());
				map.put("failUrl", request.getParameter("failUrl").trim());
				map.put("cancelUrl", request.getParameter("cancelUrl").trim());
			}else if(lvPaymentStyle.getPayValue()==Constants.PAY_METHOD_ALIPAY_INITIAL){//支付宝国际信用卡
				map.put("userId", request.getParameter("userId").trim());
				map.put("password", request.getParameter("password").trim());
				map.put("entityId", request.getParameter("entityId").trim());
			}else if(lvPaymentStyle.getPayValue()==Constants.PAY_METHOD_QH){//钱海支付
				map.put("account", request.getParameter("account").trim());
				map.put("terminal", request.getParameter("terminal").trim());
				map.put("secureCode", request.getParameter("secureCode").trim());
			}
			if(map.size()>0){
				JSONObject object=JSONObject.fromObject(map);
			    lvPaymentStyle.setParams(object.toString());//转换成json格式保存
			}
	    }else if(lvPaymentStyle.getPayChannel()==1){
	    	Map<String,String>map=new HashMap(); 
	    	map.put("paySysMerno", request.getParameter("paySysMerno").trim());
		    map.put("paySysKey", request.getParameter("paySysKey").trim());
		    map.put("paySysUrl", request.getParameter("paySysUrl").trim());
		    if(map.size()>0){
				JSONObject object=JSONObject.fromObject(map);
			    lvPaymentStyle.setPaymentSystemParams(object.toString());//转换成json格式保存
			}
	    }

		dto.put("model", lvPaymentStyle);
		this.doService("LvPaymentStyleService", "update", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvPaymentStyleMngAction.edit() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 删除
	 * @return
	 * @throws ActionException
	 */
	public String del()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvPaymentStyleMngAction.del() method begin*****");
		}
		dto.put("model", lvPaymentStyle);
		//删除model
		this.doService("LvPaymentStyleService", "del", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvPaymentStyleMngAction.del() method end*****");
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
			logger.info("***** LvPaymentStyleMngAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				lvPaymentStyle.setId(Integer.parseInt(temp_ids[i]));
				dto.put("model", lvPaymentStyle);
				//删除model
				this.doService("LvPaymentStyleService", "del", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvPaymentStyleMngAction.delList() method end*****");
		}
		return AJAX;
	}
	
	public String toFindStoreByPayType(){
		PrintWriter out = null;
		if(lvPaymentStyle!=null&&ObjectUtils.isNotEmpty(lvPaymentStyle.getPayType())){
			//查询套餐产品信息
			List<LvStore> storeList=null;
			if(lvPaymentStyle.getPayType()==0){
				storeList= (List<LvStore>) this.doService("LvStoreService","getAllNoShop", dto);
			}else if(lvPaymentStyle.getPayType()==1){
				storeList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
			}			
			
			List listTmp=new ArrayList();
			Map<String,Object>  mapTmp=new HashMap<String,Object>();
			if(storeList!=null&&storeList.size()>0){
				for (int i = 0; i < storeList.size(); i++) {
					LvStore store=storeList.get(i);
					Map<String,Object>  map=new HashMap<String,Object>();
					map.put("storeFlag", store.getStoreFlag());
					map.put("storeName", store.getName());
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
			}finally{
				out.close();
			}
		}
		
		return null;
	}

}
