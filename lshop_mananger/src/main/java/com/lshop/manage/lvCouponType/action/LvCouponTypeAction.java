/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvCouponType.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lshop.common.pojo.logic.LvActivity;
import com.lshop.common.pojo.logic.LvCoupon;
import com.lshop.common.pojo.logic.LvCouponProduct;
import com.lshop.common.pojo.logic.LvCouponType;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvProductType;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.progressBar.utils.ProgressBarConstants;
import com.lshop.common.progressBar.utils.ProgressBarUtils;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.Constants;
import com.lshop.common.util.DateUtils;
import com.lshop.common.util.FileUtil;
import com.lshop.common.util.POIExcelUtil;
import com.lshop.manage.common.action.BaseManagerAction;
import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.web.action.BaseAction;
import com.gv.core.util.ObjectUtils;
import com.gv.core.util.excel.ExcelWriter;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;

import freemarker.template.utility.DateUtil;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Controller("LvCouponTypeAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvCouponTypeAction extends BaseManagerAction{
	private static final Log logger	= LogFactory.getLog(LvCouponTypeAction.class);
	private LvCouponType lvCouponType = new LvCouponType();
	private LvProduct lvProduct;
	private LvProductType lvProductType;
	private LvActivity lvActivity;
	private String currency;
	private BufferedInputStream excelStream;
	private Integer couponDownloadNum;
	private String progressName;
	private String mallSysFlag;
	private String storeId;

	

	/**
	 * 列表
	 * @return
	 * @throws ActionException
	 */
	public String list()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeAction.list() method begin*****");
		}
		if(ObjectUtils.isNotEmpty(lvCouponType.getStartAmount())&&ObjectUtils.isNotEmpty(lvCouponType.getEndAmount())){
			if(lvCouponType.getStartAmount()>lvCouponType.getEndAmount()){
				json.setMessage("查询条件后面金额不能小于前面输入的金额！");
				json.setStatusCode(300);
				return AJAX;
			}
		}

		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("lvCouponType", lvCouponType);
		

		
		page = (Pagination)this.doService("LvCouponTypeService", "findPage", dto);

    	if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeAction.list() method end*****");
		}
		return LIST;
	}
	
	/**
	 * 
	 * @Method: selectProductType 
	 * @Description:  [根据币种查询商品类型信息，返回分页列表]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-18 下午4:18:57]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-18 下午4:18:57]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws ActionException 
	 * @return String
	 */
	public String selectMultipleProductType()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeAction.selectProductType() method begin*****");
		}
		if(ObjectUtils.isEmpty(currency)){
			json.setMessage("请先选择币种！");
			json.setStatusCode(300);
			return AJAX;
		}
		
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("lvProductType", lvProductType);
		dto.put("currency", currency);
		
		page = (Pagination)this.doService("LvProductTypeService", "findListByCurrency", dto);
		
		//查询对应币种对于的店铺信息
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService", "getAllNoShop", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeAction.selectProductType() method end*****");
		}
		return "selectMultipleProductType";
	}
	
	
	/**
	 * 
	 * @Method: selectProduct 
	 * @Description:  [根据币种查询商品信息，返回分页列表]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-18 下午4:17:18]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-18 下午4:17:18]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws ActionException 
	 * @return String
	 */
	public String selectMultipleProduct()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductAction.selectProductType() method begin*****");
		}
		if(ObjectUtils.isEmpty(currency)){
			json.setMessage("请先选择币种！");
			json.setStatusCode(300);
			return AJAX;
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("lvProduct", lvProduct);
		dto.put("currency", currency);
		
		page = (Pagination)this.doService("LvProductService", "findListByCurrency", dto);
		
		
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService", "getAllNoShop", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductAction.selectProductType() method end*****");
		}
		return "selectMultipleProduct";
	}
	
	/**
	 * 
	 * @Method: selectProduct 
	 * @Description:  [查找带回优惠券列表信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-18 下午4:17:18]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-18 下午4:17:18]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws ActionException 
	 * @return String
	 */
	public String selectSimpleCouponType()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeAction.selectSimpleCouponType() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		
		
		
		if(ObjectUtils.isNotEmpty(storeId)){
			mallSysFlag=Constants.STORE_TO_MALL_SYSTEM.get(storeId);
		}
		lvCouponType.setStatus(Short.parseShort("1"));
		lvCouponType.setOverdue(0);
		dto.put("lvCouponType", lvCouponType);
		dto.put("lvActivity", lvActivity);
		dto.put("mallSysFlag", mallSysFlag);
		page = (Pagination)this.doService("LvCouponTypeService", "findPage", dto);
				
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeAction.selectSimpleCouponType() method end*****");
		}
		return "selectSimpleCouponType";
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
			logger.info("***** LvCouponTypeAction.save() method begin*****");
		}
		/**********************************************************************
		 * 服务器端验证
		 **********************************************************************/
		//优惠券有效期结束时间需要大于开始时间
		if(ObjectUtils.isNotEmpty(lvCouponType.getStartTime())&&ObjectUtils.isNotEmpty(lvCouponType.getEndTime())){
			if (lvCouponType.getStartTime().getTime() > lvCouponType.getEndTime().getTime()) {
				this.json.setMessage("优惠券使用有效期开始时间不能大于结束时间！");
				this.json.setStatusCode(300);
				return AJAX;
			}
		}
		//验证
		List<LvCouponProduct> list= lvCouponType.getCouponProductlist();
		if(list==null){
			this.json.setMessage("请先给优惠券指定商品或者类别！");
			this.json.setStatusCode(300);
			return AJAX;
		}
		
		
		/**************************************************************************
		 * 业务逻辑实现
		 **************************************************************************/
		//设置优惠券时间从开始日期+00:00:00，结束日期+23:59:59设置
		String startTime= com.lshop.common.util.DateUtils.formatDate(lvCouponType.getStartTime(),"yyyy-MM-dd");
		String endTime= com.lshop.common.util.DateUtils.formatDate(lvCouponType.getEndTime(),"yyyy-MM-dd");
		lvCouponType.setStartTime(DateUtils.convertToDateTime(startTime+" 00:00:00"));
		lvCouponType.setEndTime(DateUtils.convertToDateTime(endTime+" 23:59:59"));
		lvCouponType.setCode(CodeUtils.getCode());//code设置
		lvCouponType.setCreateTime(new Date());   //创建时间
		dto.put("lvCouponType", lvCouponType);
		this.doService("LvCouponTypeService", "save", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeAction.save() method end*****");
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
			logger.info("***** LvCouponTypeAction.view() method begin*****");
		}
		dto.put("lvCouponType", lvCouponType);
		lvCouponType = (LvCouponType)this.doService("LvCouponTypeService", "get", dto);
		//根据优惠券类型查询关联数据
		if(ObjectUtils.isNotEmpty(lvCouponType.getRelationType())){
			dto.put("couponTypeCode", lvCouponType.getCode());
			if(lvCouponType.getRelationType()==1){
				page = (Pagination)this.doService("LvProductTypeService", "findListByCouponType", dto);
			}else if(lvCouponType.getRelationType()==2){
				page = (Pagination)this.doService("LvProductService", "findListByCouponType", dto);
			}
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeAction.view() method end*****");
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
			logger.info("***** LvCouponTypeAction.befEdit() method begin*****");
		}
		dto.put("lvCouponType", lvCouponType);
		lvCouponType = (LvCouponType)this.doService("LvCouponTypeService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeAction.befEdit() method end*****");
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
			logger.info("***** LvCouponTypeAction.edit() method begin*****");
		}		
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvCouponType.setModifyUserId(users.getId());
		lvCouponType.setModifyUserName(users.getUserName());
		lvCouponType.setModifyTime(new Date());
		
		if(ObjectUtils.isEmpty(lvCouponType.getReuseNum())){
			lvCouponType.setReuseNum(0);
		}
		
		dto.put("lvCouponType", lvCouponType);
		this.doService("LvCouponTypeService", "update", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeAction.edit() method end*****");
		}
		return AJAX;
	}
	
	

	/**
	 * 
	 * @Method: befAddNumber 
	 * @Description:  [跳转到添加优惠券数量页面]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-19 下午3:35:53]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-19 下午3:35:53]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 */
	public String befAddNumber()throws ActionException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeAction.befAddNumber() method begin*****");
		}
		dto.put("lvCouponType", lvCouponType);
		lvCouponType = (LvCouponType)this.doService("LvCouponTypeService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeAction.befAddNumber() method end*****");
		}
		return "befAddNumber";
	}

	/**
	 * 
	 * @Method: addNumber 
	 * @Description:  [添加优惠券总数量]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-19 下午3:36:27]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-19 下午3:36:27]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 */
	public String updateTotalNumber()throws ActionException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeAction.addNumber() method begin*****");
		}
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvCouponType.setModifyUserId(users.getId());
		lvCouponType.setModifyUserName(users.getUserName());
		lvCouponType.setModifyTime(new Date());
		dto.put("lvCouponType", lvCouponType);
		this.doService("LvCouponTypeService", "updateTotalNumber", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeAction.addNumber() method end*****");
		}
		return AJAX;
	}

	/**
	 * 
	 * @Method: befExtendDate 
	 * @Description:  [跳转到扩展有效期页面]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-19 下午3:38:30]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-19 下午3:38:30]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 */
	public String befExtendDate()throws ActionException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeAction.befExtendDate() method begin*****");
		}
		dto.put("lvCouponType", lvCouponType);
		lvCouponType = (LvCouponType)this.doService("LvCouponTypeService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeAction.befExtendDate() method end*****");
		}
		return "befExtendDate";
	}

	/**
	 * 
	 * @Method: updateExtendDate 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-19 下午3:39:08]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-19 下午3:39:08]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 */
	public String updateExtendDate()throws ActionException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeAction.updateExtendDate() method begin*****");
		}
		//验证时间
		if(ObjectUtils.isNotEmpty(lvCouponType.getOldEndTime())&&ObjectUtils.isNotEmpty(lvCouponType.getEndTime())){
			if(lvCouponType.getOldEndTime().getTime()>lvCouponType.getEndTime().getTime()){
				json.setMessage("延长有效期，不能小于原来的截止日期！");
				json.setStatusCode(300);
				return AJAX;
			}
		}
		
		String endTime= com.lshop.common.util.DateUtils.formatDate(lvCouponType.getEndTime(),"yyyy-MM-dd");
		lvCouponType.setEndTime(DateUtils.convertToDateTime(endTime+" 23:59:59"));
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvCouponType.setModifyUserId(users.getId());
		lvCouponType.setModifyUserName(users.getUserName());
		lvCouponType.setModifyTime(new Date());
		dto.put("lvCouponType", lvCouponType);
		this.doService("LvCouponTypeService", "updateExtendDate", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeAction.updateExtendDate() method end*****");
		}
		return AJAX;
	}

	/**
	 * 
	 * @Method: befDisable 
	 * @Description:  [跳转到停用页面]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-19 下午3:39:54]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-19 下午3:39:54]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 */
	public String befDisable()throws ActionException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeAction.befDisable() method begin*****");
		}
		dto.put("lvCouponType", lvCouponType);
		lvCouponType = (LvCouponType)this.doService("LvCouponTypeService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeAction.befDisable() method end*****");
		}
		return "befDisable";
	}

	/**
	 * 
	 * @Method: updateDisable 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-19 下午3:40:16]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-19 下午3:40:16]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 */
	public String updateDisable()throws ActionException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeAction.updateDisable() method begin*****");
		}
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvCouponType.setModifyUserId(users.getId());
		lvCouponType.setModifyUserName(users.getUserName());
		lvCouponType.setModifyTime(new Date());
		lvCouponType.setStatus(Short.valueOf("0"));
		dto.put("lvCouponType", lvCouponType);
		//验证是否存在启用的活动信息
		dto.put("givenProductCode", lvCouponType.getCode());
		Boolean flag= (Boolean) this.doService("LvActivityService", "isEffectActivityLimitOrder", dto);
		Boolean flaglink= (Boolean) this.doService("LvActivityService", "isEffectActivityLink", dto);
		if(flag||flaglink){
			json.setMessage("该优惠券存在启用的活动，请先停止活动信息！");
			json.setStatusCode(300);
			return AJAX;
		}
		
		
		//更新优惠券状态为停用
		this.doService("LvCouponTypeService", "updateDisable", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeAction.updateDisable() method end*****");
		}
		return AJAX;
	}

	/**
	 * 
	 * @Method: updateStatus 
	 * @Description:  [变更优惠券状态信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-19 下午3:41:50]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-19 下午3:41:50]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 */
	public String updateStatus()throws ActionException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeAction.updateStatus() method begin*****");
		}
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvCouponType.setModifyUserId(users.getId());
		lvCouponType.setModifyUserName(users.getUserName());
		lvCouponType.setModifyTime(new Date());
		lvCouponType.setStatus(Short.valueOf("1"));
		dto.put("lvCouponType", lvCouponType);
		this.doService("LvCouponTypeService", "updateStatus", dto);
        this.json.doNavTabTodo();
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeAction.updateStatus() method end*****");
		}
		return AJAX;
	}

	/**
	 * 
	 * @Method: befDownload 
	 * @Description:  [跳转到优惠码下载页面]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-20 下午4:24:06]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-20 下午4:24:06]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return String
	 */
	public String befDownload()throws ActionException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeAction.befDownload() method begin*****");
		}
		dto.put("lvCouponType", lvCouponType);
		lvCouponType = (LvCouponType)this.doService("LvCouponTypeService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeAction.befDownload() method end*****");
		}
		return "befDownload";
	}
	
	/**
	 * 
	 * @Method: couponDownload 
	 * @Description:  [后台运营人员下载优惠码信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-20 下午4:24:39]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-20 下午4:24:39]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return String
	 */
	public String couponDownload()throws ActionException {

		
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvCouponType.setModifyUserId(users.getId());
		lvCouponType.setModifyUserName(users.getUserName());
		lvCouponType.setModifyTime(new Date());
		lvCouponType.setStatus(Short.valueOf("1"));
		dto.put("lvCouponType", lvCouponType);
		
		//验证下载数目是否大于可剩余库存
        if(ObjectUtils.isNotEmpty(lvCouponType.getTotal())
        		&&ObjectUtils.isNotEmpty(lvCouponType.getGrantNumber())
        		&&ObjectUtils.isNotEmpty(lvCouponType.getNoGrantNumber())
        		&&ObjectUtils.isNotEmpty(lvCouponType.getUsedNumber())){
        	Integer surplusNum=lvCouponType.getTotal()-lvCouponType.getGrantNumber()-lvCouponType.getNoGrantNumber()-lvCouponType.getUsedNumber();
        	if(surplusNum<couponDownloadNum){
        		json.setMessage("下载数目不能大于剩余库存！");
    			json.setStatusCode(300);
    			//初始化进度条
    			ProgressBarUtils.iniProgressBar(ProgressBarConstants.PROGRESS_FLAT_RUN_ERROR, progressName, 0, this.getSession());
    			return AJAX;
        	}
        }
		
		
		
		int num=this.couponDownloadNum/1000;
		if(num>0){
			for(int i=0;i<num;i++){
				dto.put("downloadNum", 1000);
			}
		}else{
			dto.put("downloadNum", this.couponDownloadNum);
		}
		//生产优惠码
		List<LvCoupon> list=(List<LvCoupon>) this.doService("LvCouponTypeService", "updateDownload", dto);
		/*****************************************************************************
		 * 将生产好的优惠码信息输出到Excel表格中
		 *****************************************************************************/
		dto.put("lvCouponType", lvCouponType);
		lvCouponType = (LvCouponType)this.doService("LvCouponTypeService", "get", dto);
		List<String[]> reportList=new ArrayList();
		if(ObjectUtils.isEmpty(list)){
			ProgressBarUtils.iniProgressBar(ProgressBarConstants.PROGRESS_FLAT_RUN_ERROR, progressName, 0, this.getSession());
		}
		//初始化进度条
		ProgressBarUtils.iniProgressBar(ProgressBarConstants.PROGRESS_FLAT_RUN, progressName, list.size(), this.getSession());
		
		//填充优惠券表格信息
		reportList= initPrintCouponType(reportList,lvCouponType,list.size(),users.getUserName());
		//描绘进度条
		String[] reportTitle = new String[1];
		reportTitle[0]="优惠码";
		reportList.add(reportTitle);
		for (int i = 0; i < list.size(); i++) {
			LvCoupon coupon=list.get(i);
			String[] reportArr = new String[1];
			reportArr[0]=coupon.getCouponCode();
			reportList.add(reportArr);
			//描绘进度条
			ProgressBarUtils.drawProgressBar(0, progressName, this.getSession());
		}
		String reportListFile =lvCouponType.getName()+"_"+DateUtils.formatDate(new Date(),"yyyyMMdd")+".xls";
		//生成文件路径
		String servletName=request.getServerName();
		int servetPort=request.getServerPort();
		String path = request.getRealPath("/report") + "/" + reportListFile;
		String resPath="http://"+servletName+":"+servetPort+"/report/"+reportListFile;
		if(reportList!=null&&reportList.size()>0){
			POIExcelUtil.createExcelForXLS(path, 0, reportList);
		}
	    System.out.println(path);
	    
	    
		//终止进度条
	    ProgressBarUtils.stopProgressBar(progressName,resPath,this.getSession());
		
		
		
		
        //this.json.doNavTabTodo();
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeAction.updateStatus() method end*****");
		}
		
		return null;
	}
	
	

	
	/**
	 * 删除
	 * @return
	 * @throws ActionException
	 */
	public String del()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeAction.del() method begin*****");
		}
		dto.put("lvCouponType", lvCouponType);
		//删除lvCouponType
		this.doService("LvCouponTypeService", "del", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeAction.del() method end*****");
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
			logger.info("***** LvCouponTypeAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				lvCouponType.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("lvCouponType", lvCouponType);
				//删除lvCouponType
				this.doService("LvCouponTypeService", "del", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeAction.delList() method end*****");
		}
		return AJAX;
	}

	
	/**
	 * 
	 * @Method: exportCouponTypeList 
	 * @Description:  [导出优惠券类型信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-20 下午2:26:51]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-20 下午2:26:51]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws ActionException
	 * @throws FileNotFoundException 
	 * @return String
	 */
	public String exportCouponTypeList()throws ActionException, FileNotFoundException{
		if (logger.isInfoEnabled()) {
			logger.info("***** LvOrderManageAction.exportOrder() method begin*****");
		}
		//删除文件上传临时文件夹下面所有临时文件
		FileUtil.deleteTempFile(request);
		
		dto.put("ids",ids);
		List list = (List) this.doService("LvCouponTypeService", "exportCouponTypeList", dto);
		ExcelWriter writer = new ExcelWriter();
		String path = request.getRealPath("/temp") + "/" + System.currentTimeMillis() + ".tmp";
		writer.create(path, list);
		File file = new File(path);
		excelStream = new BufferedInputStream(new FileInputStream(file));
		file.delete();
		
		if (logger.isInfoEnabled()) {
			logger.info("***** LvOrderManageAction.exportOrder() method end*****");
		}
		return "excel";
	}
	
	
	private List initPrintCouponType(List<String[]> list,LvCouponType lvCouponType,Integer number,String userName) {
		String[] title = new String[2];
		title[0] = "优惠券名称";
		title[1] = lvCouponType.getName();
		list.add(title);
		
		//拼装优惠券使用规则
		StringBuffer applyString=new StringBuffer();
		applyString.append("限品类:");
		dto.put("couponTypeCode", lvCouponType.getCode());
		if(lvCouponType.getRelationType()==1){//当使用种类是商品类型
		   
		   page=(Pagination) this.doService("LvProductTypeService", "findListByCouponType", dto);
		   for (int i = 0; i < page.getList().size(); i++) {
			   Map map= (Map) page.getList().get(i);
			   applyString.append(map.get("typeName"));
			   applyString.append("(");
			   applyString.append(map.get("name"));
			   applyString.append(")\n");
		   }
	    	
		}else{//当使用种类是商品	
			page=(Pagination) this.doService("LvProductService", "findListByCouponType", dto);
			for (int i = 0; i < page.getList().size(); i++) {
			   Map map= (Map) page.getList().get(i);
			   applyString.append(map.get("productName"));
			   applyString.append("(");
			   applyString.append(map.get("name"));
			   applyString.append(")\n");
			}
		}

		applyString.append("商品总金额滿"+lvCouponType.getLimitAmount());
		
		String[] title2= new String[2];
		title2[0] = "使用条件";
		title2[1] = applyString.toString();
		list.add(title2);
		 
		
		String[] title3= new String[2];
		title3[0] = "优惠券金额";
		title3[1] = String.valueOf(lvCouponType.getAmount()) ;
		list.add(title3);
		
		String[] title4= new String[2];
		title4[0] = "有效时间";
		title4[1] = DateUtils.formatDate(lvCouponType.getStartTime(), "yyyy-MM-dd")+"至"+DateUtils.formatDate(lvCouponType.getEndTime(), "yyyy-MM-dd");
		list.add(title4);
		
		String[] title5= new String[2];
		title5[0] = "本次下载数量";
		title5[1] = String.valueOf(number);
		list.add(title5);
		
		String[] title6= new String[2];
		title6[0] = "本次下载时间";
		title6[1] = DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
		list.add(title6);
		
		String[] title7= new String[2];
		title7[0] = "本次下载人";
		title7[1] = userName;
		list.add(title7);
		return list;
	}
	
	public LvCouponType getLvCouponType() {
		return lvCouponType;
	}

	public void setLvCouponType(LvCouponType lvCouponType) {
		this.lvCouponType = lvCouponType;
	}

	public LvProduct getLvProduct() {
		return lvProduct;
	}

	public void setLvProduct(LvProduct lvProduct) {
		this.lvProduct = lvProduct;
	}

	public LvProductType getLvProductType() {
		return lvProductType;
	}

	public void setLvProductType(LvProductType lvProductType) {
		this.lvProductType = lvProductType;
	}

	public LvActivity getLvActivity() {
		return lvActivity;
	}

	public void setLvActivity(LvActivity lvActivity) {
		this.lvActivity = lvActivity;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BufferedInputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(BufferedInputStream excelStream) {
		this.excelStream = excelStream;
	}

	public Integer getCouponDownloadNum() {
		return couponDownloadNum;
	}

	public void setCouponDownloadNum(Integer couponDownloadNum) {
		this.couponDownloadNum = couponDownloadNum;
	}

	public String getProgressName() {
		return progressName;
	}

	public void setProgressName(String progressName) {
		this.progressName = progressName;
	}

	public String getMallSysFlag() {
		return mallSysFlag;
	}

	public void setMallSysFlag(String mallSysFlag) {
		this.mallSysFlag = mallSysFlag;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

}
