/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvActivity.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lshop.common.activity.constant.ActivityConstant;
import com.lshop.common.pojo.logic.LvActivity;
import com.lshop.common.pojo.logic.LvActivityAppointProduct;
import com.lshop.common.pojo.logic.LvActivityGift;
import com.lshop.common.pojo.logic.LvActivityLimitOrder;
import com.lshop.common.pojo.logic.LvActivityLimitTime;
import com.lshop.common.pojo.logic.LvActivityLimited;
import com.lshop.common.pojo.logic.LvActivityLink;
import com.lshop.common.pojo.logic.LvActivityLottery;
import com.lshop.common.pojo.logic.LvActivityMac;
import com.lshop.common.pojo.logic.LvActivityProduct;
import com.lshop.common.pojo.logic.LvActivityRegister;
import com.lshop.common.pojo.logic.LvActivityShare;
import com.lshop.common.pojo.logic.LvCouponType;
import com.lshop.common.pojo.logic.LvMallSystem;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.Constants;
import com.lshop.manage.common.action.BaseManagerAction;
import com.lshop.manage.lvActivity.service.LvActivityProductService;
import com.lshop.manage.message.service.ProductMsgService;
import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.util.ObjectUtils;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */
@Controller("LvActivityAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvActivityAction extends BaseManagerAction{
	private static final Log logger	= LogFactory.getLog(LvActivityAction.class);
	private LvActivity lvActivity;
	private LvActivityLimitTime lvActivityLimitTime;
	private LvActivityLimited lvActivityLimited;
	private LvActivityLimitOrder lvActivityLimitOrder;
	private LvActivityLink lvActivityLink;
	private LvActivityLottery  lvActivityLottery;
	private LvActivityRegister lvActivityRegister;
	private LvActivityShare lvActivityShare;
	private LvActivityAppointProduct lvActivityAppointProduct;
	private LvActivityMac lvActivityMac;
	private File img;//图片
	private String imgFileName;//图片名称
	private String mallSysFlag;//商城体系标示
	private String storeId;//店铺标志
	@Resource
	private LvActivityProductService lvActivityProductService;


	@Resource(name = "productMsgService")
	private ProductMsgService productMsgService;



	/**
	 * 列表
	 * @return
	 * @throws ActionException
	 */
	public String list()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvActivityAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("lvActivity", lvActivity);
		
		page = (Pagination)this.doService("LvActivityService", "findPage", dto);

    	if (logger.isInfoEnabled()){
			logger.info("***** LvActivityAction.list() method end*****");
		}
		return LIST;
	}
	
	/**
	 * 进入添加页面
	 * @return
	 * @throws ActionException
	 */
	public String befSave()throws ActionException{
		
		List<LvStore> storeList=(List<LvStore>) this.doService("LvStoreService", "getAllNoShop", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
		dto.put("lvActivity", lvActivity);
		if(lvActivity.getTypeKey()==ActivityConstant.TYPE_LIMIT_TIME){
			//查询所有店铺信息
			return "befLimitTimeAdd";
		}else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_LIMIT_QUANTITY){
			return "befLimitNumAdd";
		}else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_LIMIT_ORDER){
			return "befLimitOrderAdd";
		}else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_LINK){
			return "befLimitLinkAdd";	
		}else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_REGISTER){
			//查询商城体系信息
			List<LvMallSystem> mallList= (List<LvMallSystem>) this.doService("LvMallSystemService", "findAll", dto);
			this.getRequest().setAttribute("mallList", mallList);
			return "befRegisterAdd";
		}else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_SHARE){
			//查询商城体系信息
			List<LvMallSystem> mallList= (List<LvMallSystem>) this.doService("LvMallSystemService", "findAll", dto);
			this.getRequest().setAttribute("mallList", mallList);
			return "befShareAdd";
		}else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_LOTTERY){
			//查询商城体系信息
			List<LvMallSystem> mallList= (List<LvMallSystem>) this.doService("LvMallSystemService", "findAll", dto);
			this.getRequest().setAttribute("mallList", mallList);
			return "befLotteryAdd";
		}else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_APPOINT_PRODUCT){
			return "befPointAdd";
		}else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_MAC){
			return "befMacAdd";
		}
		return null;
	}
	
	/**
	 * 添加
	 * @return
	 * @throws ActionException
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public String save()throws ActionException, FileNotFoundException, IOException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvActivityAction.save() method begin*****");
		}
		/**************************************************************************
		 * 服务器端验证
		 **************************************************************************/
		if (lvActivity.getTypeKey() == ActivityConstant.TYPE_LIMIT_TIME || 
			lvActivity.getTypeKey() == ActivityConstant.TYPE_LIMIT_ORDER || 
			lvActivity.getTypeKey() == ActivityConstant.TYPE_LINK ||
			lvActivity.getTypeKey() == ActivityConstant.TYPE_REGISTER ||
			lvActivity.getTypeKey() == ActivityConstant.TYPE_SHARE ||
			lvActivity.getTypeKey() == ActivityConstant.TYPE_LOTTERY||
			lvActivity.getTypeKey() == ActivityConstant.TYPE_APPOINT_PRODUCT) {
			//活动开始时间不能大于系统当前时间
			Date nowDate=new Date();
			if(nowDate.getTime()>lvActivity.getStartTime().getTime()){
				this.json.setMessage("活动开始时间不能小于当前系统时间！");
				this.json.setStatusCode(300);
				return AJAX;
			}
			//活动结束时间需要大于活动开始时间
			if (lvActivity.getStartTime().getTime() > lvActivity.getEndTime().getTime()) {
				this.json.setMessage("活动开始时间不能大于活动结束时间！");
				this.json.setStatusCode(300);
				return AJAX;
			}
		}
		
		//判断同一商品是否存在开启的限时限量活动
		if(ObjectUtils.isNotEmpty(lvActivityLimitTime)&&ObjectUtils.isNotEmpty(lvActivityLimitTime.getProductCode())){
			dto.put("productCode", lvActivityLimitTime.getProductCode());
			Boolean timeFlag=(Boolean) this.doService("LvActivityService", "isExistActivityLimitTime", dto);
			Boolean numFlag=(Boolean) this.doService("LvActivityService", "isExistActivityLimitNum", dto);
			Boolean productFlag=lvActivityProductService.isEffectiveActivity(lvActivityLimitTime.getProductCode());
			if(timeFlag||numFlag||productFlag){
				this.json.setMessage("该产品存在已经启动的活动!请先关闭该产品的其他活动。");
				this.json.setStatusCode(300);
				return AJAX;
			}	
		}
		if(ObjectUtils.isNotEmpty(lvActivityLimited)&&ObjectUtils.isNotEmpty(lvActivityLimited.getProductCode())){
			dto.put("productCode", lvActivityLimited.getProductCode());
			Boolean timeFlag=(Boolean) this.doService("LvActivityService", "isExistActivityLimitTime", dto);
			Boolean numFlag=(Boolean) this.doService("LvActivityService", "isExistActivityLimitNum", dto);
			Boolean productFlag=lvActivityProductService.isEffectiveActivity(lvActivityLimited.getProductCode());
			if(timeFlag||numFlag||productFlag){
				this.json.setMessage("该产品存在已经启动的活动!请先关闭该产品的其他活动。");
				this.json.setStatusCode(300);
				return AJAX;
			}	
		}
		if(ObjectUtils.isNotEmpty(lvActivityAppointProduct)&&ObjectUtils.isNotEmpty(lvActivityAppointProduct.getAcProcuctList())){
			for (int i = 0; i < lvActivityAppointProduct.getAcProcuctList().size(); i++) {
				LvActivityProduct acp= lvActivityAppointProduct.getAcProcuctList().get(i);
				dto.put("productCode", acp.getProductCode());
				Boolean timeFlag=(Boolean) this.doService("LvActivityService", "isExistActivityLimitTime", dto);
				Boolean numFlag=(Boolean) this.doService("LvActivityService", "isExistActivityLimitNum", dto);
				Boolean productFlag=lvActivityProductService.isEffectiveActivity(acp.getProductCode());
				if(timeFlag||numFlag||productFlag){
					this.json.setMessage("该产品存在已经启动的活动!请先关闭该产品的其他活动。");
					this.json.setStatusCode(300);
					return AJAX;
				}
			}
		}
		
		//验证优惠券相关约束信息
		if (lvActivity.getTypeKey() == ActivityConstant.TYPE_LIMIT_ORDER ||
			lvActivity.getTypeKey() == ActivityConstant.TYPE_LINK ||
			lvActivity.getTypeKey() == ActivityConstant.TYPE_REGISTER ||
			lvActivity.getTypeKey() == ActivityConstant.TYPE_SHARE ||
			lvActivity.getTypeKey() == ActivityConstant.TYPE_APPOINT_PRODUCT) {
			 //查询优惠券信息
			 if(ObjectUtils.isNotEmpty(lvActivityLimitOrder)){
				 dto.put("couponTypeCode", lvActivityLimitOrder.getGivenProductCode());
			 }
			 if(ObjectUtils.isNotEmpty(lvActivityLink)){
				 dto.put("couponTypeCode", lvActivityLink.getGivenProductCode());
			 }
			 if(ObjectUtils.isNotEmpty(lvActivityRegister)){
				 dto.put("couponTypeCode", lvActivityRegister.getGivenProductCode());
			 }
			 if(ObjectUtils.isNotEmpty(lvActivityShare)){
				 dto.put("couponTypeCode", lvActivityShare.getGivenProductCode());
			 }
			 if(ObjectUtils.isNotEmpty(lvActivityAppointProduct)){
				 dto.put("couponTypeCode", lvActivityAppointProduct.getGivenProductCode());
			 }
			 LvCouponType lvCouponType=(LvCouponType) this.doService("LvCouponTypeService", "findByCode", dto);
			 if(ObjectUtils.isNotEmpty(lvCouponType)){
				 //活动有效时间判断
				 if(lvActivity.getEndTime().getTime()>lvCouponType.getEndTime().getTime()){
					 this.json.setMessage("活动结束日期不能大于优惠券的有效日期！");
				     this.json.setStatusCode(300);
					 return AJAX;
				 }
				 //剩余库存判断
				 Integer num=lvCouponType.getTotal()-(lvCouponType.getNoGrantNumber()+lvCouponType.getUsedNumber()+lvCouponType.getGrantNumber());
				 if(lvActivity.getTypeKey() == ActivityConstant.TYPE_LIMIT_ORDER&&lvActivityLimitOrder.getUncollectedTotal()>num){
					 this.json.setMessage("添加活动优惠券数量不能大于可分配库存！");
				     this.json.setStatusCode(300);
					 return AJAX;
				 }
				 if(lvActivity.getTypeKey() == ActivityConstant.TYPE_LINK&&lvActivityLink.getUncollectedTotal()>num){
					 this.json.setMessage("添加活动优惠券数量不能大于可分配库存！");
				     this.json.setStatusCode(300);
					 return AJAX;
				 }
				 if(lvActivity.getTypeKey() == ActivityConstant.TYPE_REGISTER&&lvActivityRegister.getUncollectedTotal()>num){
					 this.json.setMessage("添加活动优惠券数量不能大于可分配库存！");
				     this.json.setStatusCode(300);
					 return AJAX;
				 }
				 if(lvActivity.getTypeKey() ==ActivityConstant.TYPE_SHARE&&lvActivityShare.getUncollectedTotal()>num){
					 this.json.setMessage("添加活动优惠券数量不能大于可分配库存！");
				     this.json.setStatusCode(300);
					 return AJAX;
				 }
				 if(lvActivity.getTypeKey() ==ActivityConstant.TYPE_APPOINT_PRODUCT&&lvActivityAppointProduct.getUncollectedTotal()>num){
					 this.json.setMessage("添加活动优惠券数量不能大于可分配库存！");
				     this.json.setStatusCode(300);
					 return AJAX;
				 }
			 }
		}
		
		//验证活动选择抽奖机会
		if (lvActivity.getTypeKey() == ActivityConstant.TYPE_LIMIT_ORDER ||
			lvActivity.getTypeKey() == ActivityConstant.TYPE_REGISTER ||
			lvActivity.getTypeKey() == ActivityConstant.TYPE_SHARE ||
			lvActivity.getTypeKey() == ActivityConstant.TYPE_APPOINT_PRODUCT) {
			 if(ObjectUtils.isNotEmpty(lvActivityLimitOrder)&&lvActivityLimitOrder.getGivenTypeNum()==2){
				 dto.put("code", lvActivityLimitOrder.getAcLotteryCode());
				 LvActivity activity= (LvActivity) this.doService("LvActivityService", "findByCode", dto);
				 if(ObjectUtils.isNotEmpty(activity)){
					 if(lvActivity.getEndTime().getTime()>activity.getEndTime().getTime()){
						 this.json.setMessage("活动结束日期不能大于抽奖活动的结束日期！");
					     this.json.setStatusCode(300);
						 return AJAX;
					 }
				 }
			 }
			 if(ObjectUtils.isNotEmpty(lvActivityRegister)&&ObjectUtils.isNotEmpty(lvActivityRegister.getGivenTypeNum())&&lvActivityRegister.getGivenTypeNum()==2){
				 dto.put("code", lvActivityRegister.getAcLotteryCode());
				 LvActivity activity= (LvActivity) this.doService("LvActivityService", "findByCode", dto);
				 if(ObjectUtils.isNotEmpty(activity)){
					 if(lvActivity.getEndTime().getTime()>activity.getEndTime().getTime()){
						 this.json.setMessage("活动结束日期不能大于抽奖活动的结束日期！");
					     this.json.setStatusCode(300);
						 return AJAX;
					 }
				 }
			 }
			 if(ObjectUtils.isNotEmpty(lvActivityShare)&&ObjectUtils.isNotEmpty(lvActivityShare.getGivenTypeNum())&&lvActivityShare.getGivenTypeNum()==2){
				 dto.put("code", lvActivityShare.getAcLotteryCode());
				 LvActivity activity= (LvActivity) this.doService("LvActivityService", "findByCode", dto);
				 if(ObjectUtils.isNotEmpty(activity)){
					 if(lvActivity.getEndTime().getTime()>activity.getEndTime().getTime()){
						 this.json.setMessage("活动结束日期不能大于抽奖活动的结束日期！");
					     this.json.setStatusCode(300);
						 return AJAX;
					 }
				 }
			 }
			 
			 if(ObjectUtils.isNotEmpty(lvActivityAppointProduct)&&ObjectUtils.isNotEmpty(lvActivityAppointProduct.getGivenTypeNum())&&lvActivityAppointProduct.getGivenTypeNum()==2){
				 dto.put("code", lvActivityAppointProduct.getAcLotteryCode());
				 LvActivity activity= (LvActivity) this.doService("LvActivityService", "findByCode", dto);
				 if(ObjectUtils.isNotEmpty(activity)){
					 if(lvActivity.getEndTime().getTime()>activity.getEndTime().getTime()){
						 this.json.setMessage("活动结束日期不能大于抽奖活动的结束日期！");
					     this.json.setStatusCode(300);
						 return AJAX;
					 }
				 }
			 }
		}
		

		//图片文件上传
		if(lvActivity.getTypeKey()==ActivityConstant.TYPE_SHARE){
			LvProduct lvProduct=new LvProduct();
			lvProduct.setStoreId("tvpadcn");
			dto.put("lvProduct", lvProduct);
			//商城产品详情主图片上传
			if(img!=null){
				dto.put("img", img);
				dto.put("imgFileName", imgFileName);
				dto.put("request", this.getRequest());
				dto.put("resPath", this.getText("res.domain.path"));
				String type=imgFileName.substring(imgFileName.lastIndexOf("."), imgFileName.length());
				if (!(type.toLowerCase().equals(".jpg")
						|| type.toLowerCase().equals(".jpeg")
						|| type.toLowerCase().equals(".png")
						|| type.toLowerCase().equals(".gif"))) {
					json.setMessage("只能上传JPG、JPEG、PNG、GIF格式的图片文件，请重新选择！");
					json.setStatusCode(300);
					return AJAX;
				}
				if (new FileInputStream(img).available() > 1024 * 1024 * 2) {
					json.setMessage("文件大小过大，请压缩至2M以内！");
					json.setStatusCode(300);
					return AJAX;
				}
				String imgPath=(String) this.doService("LvProductService", "upload", dto);
				lvActivityShare.setShareImage(imgPath);
			}
		}
		
		/**************************************************************************
		 * 业务逻辑实现
		 **************************************************************************/
		lvActivity.setActivityTitle(com.lshop.common.util.StringUtil.replaceChars(lvActivity.getActivityTitle()));
		lvActivity.setCode(CodeUtils.getCode());//code设置
		lvActivity.setCreateTime(new Date());   //创建时间
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		dto.put("users", users);
		dto.put("lvActivityLimitTime", lvActivityLimitTime);
		dto.put("lvActivityLimited", lvActivityLimited);
		dto.put("lvActivityLimitOrder", lvActivityLimitOrder);
		dto.put("lvActivityLink", lvActivityLink);
		dto.put("lvActivityLottery", lvActivityLottery);
		dto.put("lvActivityRegister", lvActivityRegister);
		dto.put("lvActivityShare", lvActivityShare);
		dto.put("lvActivity", lvActivity);
		dto.put("lvActivityAppointProduct", lvActivityAppointProduct);
		dto.put("lvActivityMac", lvActivityMac);
		this.doService("LvActivityService", "save", dto);
		
		
		
		//发送消息到前台，通知前台更新缓存
		sendProductChangeToWeb();
		
		//发送改变的数据到前端系统中
		if (lvActivity.getTypeKey() == 4){
			dto.put("activityCode",lvActivity.getCode());
			this.doService("ActivityToWebService", "sendJSONToWeb", dto);
		}
		if (logger.isInfoEnabled()){
			logger.info("***** LvActivityAction.save() method end*****");
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
			logger.info("***** LvActivityAction.view() method begin*****");
		}
		dto.put("lvActivity", lvActivity);
		lvActivity = (LvActivity)this.doService("LvActivityService", "get", dto);
		dto.put("activityCode", lvActivity.getCode());
		if(lvActivity.getTypeKey()==ActivityConstant.TYPE_LIMIT_TIME){//限时活动
			//查询限时活动详情信息
			lvActivityLimitTime=(LvActivityLimitTime) this.doService("LvActivityService", "findLimtTimeByCode", dto);
			this.getRequest().setAttribute("lvActivityLimitTime", lvActivityLimitTime);
			//查询商品信息
			LvProduct product=new LvProduct();
			product.setCode(lvActivityLimitTime.getProductCode());
			dto.put("lvProduct", product);
			product=(LvProduct) this.doService("LvProductService", "getProduct", dto);
			this.getRequest().setAttribute("product", product);
			
			//查询店铺信息
			List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService", "getAllNoShop", dto);
			this.getRequest().setAttribute("storeList", storeList);
			
			return "befLimitTimeView";
		}else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_LIMIT_QUANTITY){//限量活动
			lvActivityLimited=(LvActivityLimited) this.doService("LvActivityService", "findLimtNumByCode", dto);
			this.getRequest().setAttribute("lvActivityLimited", lvActivityLimited);
			
			//查询商品信息
			LvProduct product=new LvProduct();
			product.setCode(lvActivityLimited.getProductCode());
			dto.put("lvProduct", product);
			product=(LvProduct) this.doService("LvProductService", "getProduct", dto);
			this.getRequest().setAttribute("product", product);
			
			//查询店铺信息
			List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService", "getAllNoShop", dto);
			this.getRequest().setAttribute("storeList", storeList);
			
			return "befLimitNumView";
		}else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_LIMIT_ORDER){//订单满就送活动
			lvActivityLimitOrder=(LvActivityLimitOrder) this.doService("LvActivityService", "findLimtOrderByCode", dto);
			this.getRequest().setAttribute("lvActivityLimitOrder", lvActivityLimitOrder);
			//查询优惠券信息
			dto.put("couponTypeCode", lvActivityLimitOrder.getGivenProductCode());
			LvCouponType lvCouponType=(LvCouponType) this.doService("LvCouponTypeService", "findByCode", dto);
			this.getRequest().setAttribute("lvCouponType", lvCouponType);			
			
			//查询店铺信息
			List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService", "getAllNoShop", dto);
			this.getRequest().setAttribute("storeList", storeList);
			
			return "befLimitOrderView";
		}else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_LINK){//点击连接就送活动
			lvActivityLink=(LvActivityLink) this.doService("LvActivityService", "findLinkByCode", dto);
			this.getRequest().setAttribute("lvActivityLink", lvActivityLink);
			
			//查询优惠券信息
			dto.put("couponTypeCode", lvActivityLink.getGivenProductCode());
			LvCouponType lvCouponType=(LvCouponType) this.doService("LvCouponTypeService", "findByCode", dto);
			this.getRequest().setAttribute("lvCouponType", lvCouponType);
			
			return "befLimitLinkView";	
		}else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_REGISTER){//注册就送活动
			lvActivityRegister=(LvActivityRegister) this.doService("LvActivityService", "findACRegisterByCode", dto);
			this.getRequest().setAttribute("lvActivityRegister", lvActivityRegister);
			
			if(lvActivityRegister.getGivenTypeNum()==1){
				//查询优惠券信息
				dto.put("couponTypeCode", lvActivityRegister.getGivenProductCode());
				LvCouponType lvCouponType=(LvCouponType) this.doService("LvCouponTypeService", "findByCode", dto);
				this.getRequest().setAttribute("lvCouponType", lvCouponType);	
			}
			
			//查询商城体系信息
			List<LvMallSystem> mallList= (List<LvMallSystem>) this.doService("LvMallSystemService", "findAll", dto);
			this.getRequest().setAttribute("mallList", mallList);
			return "befRegisterView";
		}else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_SHARE){//分享就送活动
			lvActivityShare=(LvActivityShare) this.doService("LvActivityService", "findACShareByCode", dto);
			this.getRequest().setAttribute("lvActivityShare", lvActivityShare);
			
			if(lvActivityShare.getGivenTypeNum()==1){
				//查询优惠券信息
				dto.put("couponTypeCode", lvActivityShare.getGivenProductCode());
				LvCouponType lvCouponType=(LvCouponType) this.doService("LvCouponTypeService", "findByCode", dto);
				this.getRequest().setAttribute("lvCouponType", lvCouponType);
			}
			
			//查询商城体系信息
			List<LvMallSystem> mallList= (List<LvMallSystem>) this.doService("LvMallSystemService", "findAll", dto);
			this.getRequest().setAttribute("mallList", mallList);
			return "befShareView";
		}else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_LOTTERY){//抽奖活动
			lvActivityLottery=(LvActivityLottery) this.doService("LvActivityService", "findACLotteryByCode", dto);
			this.getRequest().setAttribute("lvActivityLottery", lvActivityLottery);
			
			//查询商城体系信息
			List<LvMallSystem> mallList= (List<LvMallSystem>) this.doService("LvMallSystemService", "findAll", dto);
			this.getRequest().setAttribute("mallList", mallList);
			return "befLotteryView";
		}else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_APPOINT_PRODUCT){//购买指定商品就送活动
			
			//查询购买指定商品就送活动详情
			lvActivityAppointProduct=(LvActivityAppointProduct) this.doService("LvActivityService", "findACPointProductByCode", dto);
			this.getRequest().setAttribute("lvActivityAppointProduct", lvActivityAppointProduct);

			//查询活动商品信息
			List acProductList=(List) this.doService("LvActivityProductService", "findAll", dto);
			
			if(lvActivityAppointProduct.getGivenTypeNum()==1){
				//查询优惠券信息
				dto.put("couponTypeCode", lvActivityAppointProduct.getGivenProductCode());
				LvCouponType lvCouponType=(LvCouponType) this.doService("LvCouponTypeService", "findByCode", dto);
				this.getRequest().setAttribute("lvCouponType", lvCouponType);	
			}else if(lvActivityAppointProduct.getGivenTypeNum()==3){
				//查询活动赠品信息
				List<LvActivityGift> giftList= (List<LvActivityGift>) this.doService("LvActivityGiftService", "findAll", dto);
				this.getRequest().setAttribute("giftList", giftList);
			}
			this.getRequest().setAttribute("acProductList", acProductList);
			return "befPointView";
		}else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_MAC){//输入mac就送活动
			
			//查询购买指定商品就送活动详情
			lvActivityMac=(LvActivityMac) this.doService("LvActivityService", "findACMacByCode", dto);
			this.getRequest().setAttribute("lvActivityMac", lvActivityMac);

			//查询活动商品信息
			List acProductList=(List) this.doService("LvActivityProductService", "findAll", dto);
			this.getRequest().setAttribute("acProductList", acProductList);
			return "befMacView";
		}
		if (logger.isInfoEnabled()){
			logger.info("***** LvActivityAction.view() method end*****");
		}
		return null;
	}
	
	/**
	 * 根据活动类型跳入不同类型活动的编辑页面
	 * @return
	 * @throws ActionException
	 */
	public String befEdit()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvActivityAction.befEdit() method begin*****");
		}
		dto.put("lvActivity", lvActivity);
		lvActivity = (LvActivity)this.doService("LvActivityService", "get", dto);
		dto.put("activityCode", lvActivity.getCode());
		
		//查询店铺信息
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService", "getAllNoShop", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
		if(lvActivity.getTypeKey()==ActivityConstant.TYPE_LIMIT_TIME){
			//查询限时活动详情信息
			lvActivityLimitTime=(LvActivityLimitTime) this.doService("LvActivityService", "findLimtTimeByCode", dto);
			this.getRequest().setAttribute("lvActivityLimitTime", lvActivityLimitTime);
			//查询商品信息
			LvProduct product=new LvProduct();
			product.setCode(lvActivityLimitTime.getProductCode());
			dto.put("lvProduct", product);
			product=(LvProduct) this.doService("LvProductService", "getProduct", dto);
			this.getRequest().setAttribute("product", product);
			

			return "befLimitTimeEdit";
		}else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_LIMIT_QUANTITY){
			lvActivityLimited=(LvActivityLimited) this.doService("LvActivityService", "findLimtNumByCode", dto);
			this.getRequest().setAttribute("lvActivityLimited", lvActivityLimited);
			
			//查询商品信息
			LvProduct product=new LvProduct();
			product.setCode(lvActivityLimited.getProductCode());
			dto.put("lvProduct", product);
			product=(LvProduct) this.doService("LvProductService", "getProduct", dto);
			this.getRequest().setAttribute("product", product);			
			return "befLimitNumEdit";
		}else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_LIMIT_ORDER){
			lvActivityLimitOrder=(LvActivityLimitOrder) this.doService("LvActivityService", "findLimtOrderByCode", dto);
			this.getRequest().setAttribute("lvActivityLimitOrder", lvActivityLimitOrder);
			//查询优惠券信息
			dto.put("couponTypeCode", lvActivityLimitOrder.getGivenProductCode());
			LvCouponType lvCouponType=(LvCouponType) this.doService("LvCouponTypeService", "findByCode", dto);
			this.getRequest().setAttribute("lvCouponType", lvCouponType);			
			return "befLimitOrderEdit";
		}else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_LINK){
			lvActivityLink=(LvActivityLink) this.doService("LvActivityService", "findLinkByCode", dto);
			this.getRequest().setAttribute("lvActivityLink", lvActivityLink);
			
			//查询优惠券信息
			dto.put("couponTypeCode", lvActivityLink.getGivenProductCode());
			LvCouponType lvCouponType=(LvCouponType) this.doService("LvCouponTypeService", "findByCode", dto);
			this.getRequest().setAttribute("lvCouponType", lvCouponType);	
			
			return "befLimitLinkEdit";	
		}else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_REGISTER){
			lvActivityRegister=(LvActivityRegister) this.doService("LvActivityService", "findACRegisterByCode", dto);
			this.getRequest().setAttribute("lvActivityRegister", lvActivityRegister);
			
			if(lvActivityRegister.getGivenTypeNum()==1){
				//查询优惠券信息
				dto.put("couponTypeCode", lvActivityRegister.getGivenProductCode());
				LvCouponType lvCouponType=(LvCouponType) this.doService("LvCouponTypeService", "findByCode", dto);
				this.getRequest().setAttribute("lvCouponType", lvCouponType);	
			}
			
			//查询商城体系信息
			List<LvMallSystem> mallList= (List<LvMallSystem>) this.doService("LvMallSystemService", "findAll", dto);
			this.getRequest().setAttribute("mallList", mallList);
			
			return "befRegisterEdit";
		}else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_SHARE){//分享就送活动
			lvActivityShare=(LvActivityShare) this.doService("LvActivityService", "findACShareByCode", dto);
			this.getRequest().setAttribute("lvActivityShare", lvActivityShare);
			
			if(lvActivityShare.getGivenTypeNum()==1){
				//查询优惠券信息
				dto.put("couponTypeCode", lvActivityShare.getGivenProductCode());
				LvCouponType lvCouponType=(LvCouponType) this.doService("LvCouponTypeService", "findByCode", dto);
				this.getRequest().setAttribute("lvCouponType", lvCouponType);
			}
	
			//查询商城体系信息
			List<LvMallSystem> mallList= (List<LvMallSystem>) this.doService("LvMallSystemService", "findAll", dto);
			this.getRequest().setAttribute("mallList", mallList);
			
			return "befShareEdit";
		}else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_LOTTERY){//抽奖活动
			lvActivityLottery=(LvActivityLottery) this.doService("LvActivityService", "findACLotteryByCode", dto);
			this.getRequest().setAttribute("lvActivityLottery", lvActivityLottery);
			//查询商城体系信息
			List<LvMallSystem> mallList= (List<LvMallSystem>) this.doService("LvMallSystemService", "findAll", dto);
			this.getRequest().setAttribute("mallList", mallList);
			return "befLotteryEdit";
		}else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_APPOINT_PRODUCT){//购买指定商品就送活动
			//查询购买指定商品就送活动详情
			lvActivityAppointProduct=(LvActivityAppointProduct) this.doService("LvActivityService", "findACPointProductByCode", dto);
			this.getRequest().setAttribute("lvActivityAppointProduct", lvActivityAppointProduct);
			

			//查询活动商品信息
			List<LvActivityProduct> acProductList=(List<LvActivityProduct>) this.doService("LvActivityProductService", "findAll", dto);
			if(lvActivityAppointProduct.getGivenTypeNum()==1){
				//查询优惠券信息
				dto.put("couponTypeCode", lvActivityAppointProduct.getGivenProductCode());
				LvCouponType lvCouponType=(LvCouponType) this.doService("LvCouponTypeService", "findByCode", dto);
				this.getRequest().setAttribute("lvCouponType", lvCouponType);	
			}else if(lvActivityAppointProduct.getGivenTypeNum()==3){
				//查询活动赠品信息
				List<LvActivityGift> giftList= (List<LvActivityGift>) this.doService("LvActivityGiftService", "findAll", dto);
				this.getRequest().setAttribute("giftList", giftList);
			}
			
			this.getRequest().setAttribute("acProductList", acProductList);

			return "befPointEdit";
		}else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_MAC){//输入mac就送活动
			
			//查询购买指定商品就送活动详情
			lvActivityMac=(LvActivityMac) this.doService("LvActivityService", "findACMacByCode", dto);
			this.getRequest().setAttribute("lvActivityMac", lvActivityMac);

			//查询活动商品信息
			List acProductList=(List) this.doService("LvActivityProductService", "findAll", dto);
			this.getRequest().setAttribute("acProductList", acProductList);
			
			return "befMacEdit";
		}
		if (logger.isInfoEnabled()){
			logger.info("***** LvActivityAction.befEdit() method end*****");
		}
		return null;
	}
	/**
	 * 编辑
	 * @return
	 * @throws ActionException
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public String edit()throws ActionException, FileNotFoundException, IOException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvActivityAction.edit() method begin*****");
		}
		/**************************************************************************
		 * 服务器端验证
		 **************************************************************************/
		if (lvActivity.getTypeKey() == ActivityConstant.TYPE_LIMIT_TIME || 
			lvActivity.getTypeKey() == ActivityConstant.TYPE_LIMIT_ORDER || 
			lvActivity.getTypeKey() == ActivityConstant.TYPE_LINK ||
			lvActivity.getTypeKey() == ActivityConstant.TYPE_REGISTER ||
			lvActivity.getTypeKey() == ActivityConstant.TYPE_SHARE ||
			lvActivity.getTypeKey() == ActivityConstant.TYPE_LOTTERY ||
			lvActivity.getTypeKey() == ActivityConstant.TYPE_APPOINT_PRODUCT) {
			// 活动结束时间需要大于活动开始时间
			if (lvActivity.getStartTime().getTime() > lvActivity.getEndTime().getTime()) {
				this.json.setMessage("活动开始时间不能大于活动结束时间！");
				this.json.setStatusCode(300);
				return AJAX;
			}
		}
		
		//判断同一商品是否存在开启的限时限量活动(活动状态变更时)
		if(!lvActivity.getStatus().equals(lvActivity.getOldStatus())&&lvActivity.getStatus().equals(1)){//
			if(ObjectUtils.isNotEmpty(lvActivityLimitTime)&&ObjectUtils.isNotEmpty(lvActivityLimitTime.getProductCode())){
				Boolean isFlag=isMutexActivity(lvActivityLimitTime.getProductCode());
				if(isFlag){
					this.json.setMessage("该产品存在已经启动的活动!请先关闭该产品的其他活动。");
					this.json.setStatusCode(300);
					return AJAX;
				}
			}
			if(ObjectUtils.isNotEmpty(lvActivityLimited)&&ObjectUtils.isNotEmpty(lvActivityLimited.getProductCode())){
				Boolean isFlag=isMutexActivity(lvActivityLimited.getProductCode());
				if(isFlag){
					this.json.setMessage("该产品存在已经启动的活动!请先关闭该产品的其他活动。");
					this.json.setStatusCode(300);
					return AJAX;
				}
			}
		}
		//判断同一商品是否存在开启的限时限量活动(商品关系更时)
		if(lvActivity.getStatus().equals(1)){
			if(ObjectUtils.isNotEmpty(lvActivityLimitTime)&&!lvActivityLimitTime.getProductCode().equals(lvActivityLimitTime.getOldProductCode())){
				Boolean isFlag=isMutexActivity(lvActivityLimitTime.getProductCode());
				if(isFlag){
					this.json.setMessage("该产品存在已经启动的活动!请先关闭该产品的其他活动。");
					this.json.setStatusCode(300);
					return AJAX;
				}
			}
			if(ObjectUtils.isNotEmpty(lvActivityLimited)&&!lvActivityLimited.getProductCode().equals(lvActivityLimited.getOldProductCode())){
				Boolean isFlag=isMutexActivity(lvActivityLimited.getProductCode());
				if(isFlag){
					this.json.setMessage("该产品存在已经启动的活动!请先关闭该产品的其他活动。");
					this.json.setStatusCode(300);
					return AJAX;
				}
			}
		}
		
		
		//验证优惠券相关约束信息
		if (lvActivity.getTypeKey() == ActivityConstant.TYPE_LIMIT_ORDER ||
			lvActivity.getTypeKey() == ActivityConstant.TYPE_LINK ||
			lvActivity.getTypeKey() == ActivityConstant.TYPE_REGISTER ||
			lvActivity.getTypeKey() == ActivityConstant.TYPE_SHARE ||
			lvActivity.getTypeKey() == ActivityConstant.TYPE_APPOINT_PRODUCT) {
			
			
			
			 //查询优惠券信息
			 if(ObjectUtils.isNotEmpty(lvActivityLimitOrder)&&lvActivityLimitOrder.getGivenTypeNum()==1){
				 dto.put("couponTypeCode", lvActivityLimitOrder.getGivenProductCode());
			 }
			 if(ObjectUtils.isNotEmpty(lvActivityLink)&&lvActivityLink.getGivenTypeNum()==1){
				 dto.put("couponTypeCode", lvActivityLink.getGivenProductCode());
			 }
			 if(ObjectUtils.isNotEmpty(lvActivityRegister)&&lvActivityRegister.getGivenTypeNum()==1){
				 dto.put("couponTypeCode", lvActivityRegister.getGivenProductCode());
			 }
			 if(ObjectUtils.isNotEmpty(lvActivityShare)&&lvActivityShare.getGivenTypeNum()==1){
				 dto.put("couponTypeCode", lvActivityShare.getGivenProductCode());
			 }
			 if(ObjectUtils.isNotEmpty(lvActivityAppointProduct)&&lvActivityAppointProduct.getGivenTypeNum()==1){
				 dto.put("couponTypeCode", lvActivityAppointProduct.getGivenProductCode());
			 }
			 
			 if(ObjectUtils.isNotEmpty(dto.get("couponTypeCode"))){
				 LvCouponType lvCouponType=(LvCouponType) this.doService("LvCouponTypeService", "findByCode", dto);
				 if(ObjectUtils.isNotEmpty(lvCouponType)){
					 //活动有效时间判断
					 if(lvActivity.getEndTime().getTime()>lvCouponType.getEndTime().getTime()){
						 this.json.setMessage("活动结束日期不能大于优惠券的有效日期！");
					     this.json.setStatusCode(300);
						 return AJAX;
					 }
					 //剩余库存判断
					 Integer num=lvCouponType.getTotal()-(lvCouponType.getNoGrantNumber()+lvCouponType.getUsedNumber()+lvCouponType.getGrantNumber());
					 if(lvActivity.getTypeKey() == ActivityConstant.TYPE_LIMIT_ORDER&&ObjectUtils.isNotEmpty(lvActivityLimitOrder.getAddTotal())&&lvActivityLimitOrder.getAddTotal()>num){
						 this.json.setMessage("添加活动优惠券数量不能大于可分配库存！");
					     this.json.setStatusCode(300);
						 return AJAX;
					 }
					 if(lvActivity.getTypeKey() == ActivityConstant.TYPE_LINK&&ObjectUtils.isNotEmpty(lvActivityLink.getAddTotal())&&lvActivityLink.getAddTotal()>num){
						 this.json.setMessage("添加活动优惠券数量不能大于可分配库存！");
					     this.json.setStatusCode(300);
						 return AJAX;
					 }
					 if(lvActivity.getTypeKey() == ActivityConstant.TYPE_REGISTER&&ObjectUtils.isNotEmpty(lvActivityRegister.getAddTotal())&&lvActivityRegister.getAddTotal()>num){
						 this.json.setMessage("添加活动优惠券数量不能大于可分配库存！");
					     this.json.setStatusCode(300);
						 return AJAX;
					 }
					 if(lvActivity.getTypeKey() == ActivityConstant.TYPE_SHARE&&ObjectUtils.isNotEmpty(lvActivityShare.getAddTotal())&&lvActivityShare.getAddTotal()>num){
						 this.json.setMessage("添加活动优惠券数量不能大于可分配库存！");
					     this.json.setStatusCode(300);
						 return AJAX;
					 }
					 if(lvActivity.getTypeKey() == ActivityConstant.TYPE_APPOINT_PRODUCT&&ObjectUtils.isNotEmpty(lvActivityAppointProduct.getAddTotal())&&lvActivityAppointProduct.getAddTotal()>num){
						 this.json.setMessage("添加活动优惠券数量不能大于可分配库存！");
					     this.json.setStatusCode(300);
						 return AJAX;
					 }
				 }
			 }
		}
		
		//验证活动选择抽奖机会
		if (lvActivity.getTypeKey() == ActivityConstant.TYPE_LIMIT_ORDER ||
			lvActivity.getTypeKey() == ActivityConstant.TYPE_REGISTER ||
			lvActivity.getTypeKey() == ActivityConstant.TYPE_SHARE ||
			lvActivity.getTypeKey() == ActivityConstant.TYPE_APPOINT_PRODUCT) {
			 if(ObjectUtils.isNotEmpty(lvActivityLimitOrder)&&lvActivityLimitOrder.getGivenTypeNum()==2){
				 dto.put("code", lvActivityLimitOrder.getGivenProductCode());
				 LvActivity activity= (LvActivity) this.doService("LvActivityService", "findByCode", dto);
				 if(ObjectUtils.isNotEmpty(activity)){
					 if(lvActivity.getEndTime().getTime()>activity.getEndTime().getTime()){
						 this.json.setMessage("活动结束日期不能大于抽奖活动的结束日期！");
					     this.json.setStatusCode(300);
						 return AJAX;
					 }
				 }
			 }
			 if(ObjectUtils.isNotEmpty(lvActivityRegister)&&lvActivityRegister.getGivenTypeNum()==2){
				 dto.put("code", lvActivityRegister.getGivenProductCode());
				 LvActivity activity= (LvActivity) this.doService("LvActivityService", "findByCode", dto);
				 if(ObjectUtils.isNotEmpty(activity)){
					 if(lvActivity.getEndTime().getTime()>activity.getEndTime().getTime()){
						 this.json.setMessage("活动结束日期不能大于抽奖活动的结束日期！");
					     this.json.setStatusCode(300);
						 return AJAX;
					 }
				 }
			 }
			 if(ObjectUtils.isNotEmpty(lvActivityShare)&&lvActivityShare.getGivenTypeNum()==2){
				 dto.put("code", lvActivityShare.getGivenProductCode());
				 LvActivity activity= (LvActivity) this.doService("LvActivityService", "findByCode", dto);
				 if(ObjectUtils.isNotEmpty(activity)){
					 if(lvActivity.getEndTime().getTime()>activity.getEndTime().getTime()){
						 this.json.setMessage("活动结束日期不能大于抽奖活动的结束日期！");
					     this.json.setStatusCode(300);
						 return AJAX;
					 }
				 }
			 }
			 if(ObjectUtils.isNotEmpty(lvActivityAppointProduct)&&lvActivityAppointProduct.getGivenTypeNum()==2){
				 dto.put("code", lvActivityAppointProduct.getGivenProductCode());
				 LvActivity activity= (LvActivity) this.doService("LvActivityService", "findByCode", dto);
				 if(ObjectUtils.isNotEmpty(activity)){
					 if(lvActivity.getEndTime().getTime()>activity.getEndTime().getTime()){
						 this.json.setMessage("活动结束日期不能大于抽奖活动的结束日期！");
					     this.json.setStatusCode(300);
						 return AJAX;
					 }
				 }
			 }
		}
		
		//图片文件上传
		if(lvActivity.getTypeKey()==ActivityConstant.TYPE_SHARE){
			LvProduct lvProduct=new LvProduct();
			lvProduct.setStoreId("tvpadcn");
			dto.put("lvProduct", lvProduct);
			//商城产品详情主图片上传
			if(img!=null){
				dto.put("img", img);
				dto.put("imgFileName", imgFileName);
				dto.put("request", this.getRequest());
				dto.put("resPath", this.getText("res.domain.path"));
				String type=imgFileName.substring(imgFileName.lastIndexOf("."), imgFileName.length());
				if (!(type.toLowerCase().equals(".jpg")
						|| type.toLowerCase().equals(".jpeg")
						|| type.toLowerCase().equals(".png")
						|| type.toLowerCase().equals(".gif"))) {
					json.setMessage("只能上传JPG、JPEG、PNG、GIF格式的图片文件，请重新选择！");
					json.setStatusCode(300);
					return AJAX;
				}
				if (new FileInputStream(img).available() > 1024 * 1024 * 2) {
					json.setMessage("文件大小过大，请压缩至2M以内！");
					json.setStatusCode(300);
					return AJAX;
				}
				String imgPath=(String) this.doService("LvProductService", "upload", dto);
				lvActivityShare.setShareImage(imgPath);
			}
		}
		
		/**************************************************************************
		 * 业务逻辑实现
		 **************************************************************************/
		
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		if(ObjectUtils.isNotEmpty(lvActivity)){
			lvActivity.setModifyUserId(users.getId());
			lvActivity.setModifyUserName(users.getUserName());
			lvActivity.setModifyTime(new Date());
		}
		
		if(ObjectUtils.isNotEmpty(lvActivityLimitTime)){
			lvActivityLimitTime.setModifyUserId(users.getId());
			lvActivityLimitTime.setModifyUserName(users.getUserName());
			lvActivityLimitTime.setModifyTime(new Date());
		}
		if(ObjectUtils.isNotEmpty(lvActivityLimited)){
			lvActivityLimited.setModifyUserId(users.getId());
			lvActivityLimited.setModifyUserName(users.getUserName());
			lvActivityLimited.setModifyTime(new Date());
		}
		if(ObjectUtils.isNotEmpty(lvActivityLimitOrder)){
			if(ObjectUtils.isNotEmpty(lvActivityLimitOrder.getAddTotal())){
				lvActivityLimitOrder.setUncollectedTotal(lvActivityLimitOrder.getUncollectedTotal()+lvActivityLimitOrder.getAddTotal());//赠送优惠券未领取数量累计
			}
			lvActivityLimitOrder.setModifyUserId(users.getId());
			lvActivityLimitOrder.setModifyUserName(users.getUserName());
			lvActivityLimitOrder.setModifyTime(new Date());
		}
		if(ObjectUtils.isNotEmpty(lvActivityLink)){
			if(ObjectUtils.isNotEmpty(lvActivityLink.getAddTotal())){
				lvActivityLink.setUncollectedTotal(lvActivityLink.getUncollectedTotal()+lvActivityLink.getAddTotal());//赠送优惠券未领取数量累计
			}
			lvActivityLink.setModifyUserId(users.getId());
			lvActivityLink.setModifyUserName(users.getUserName());
			lvActivityLink.setModifyTime(new Date());
		}
		if(ObjectUtils.isNotEmpty(lvActivityLottery)){
			lvActivityLottery.setModifyUserId(users.getId());
			lvActivityLottery.setModifyUserName(users.getUserName());
			lvActivityLottery.setModifyTime(new Date());
		}
		if(ObjectUtils.isNotEmpty(lvActivityRegister)){
			if(ObjectUtils.isNotEmpty(lvActivityRegister.getAddTotal())){
				lvActivityRegister.setUncollectedTotal(lvActivityRegister.getUncollectedTotal()+lvActivityRegister.getAddTotal());//赠送优惠券未领取数量累计
			}
			lvActivityRegister.setModifyUserId(users.getId());
			lvActivityRegister.setModifyUserName(users.getUserName());
			lvActivityRegister.setModifyTime(new Date());
		}
		if(ObjectUtils.isNotEmpty(lvActivityShare)){
			if(ObjectUtils.isNotEmpty(lvActivityShare.getAddTotal())){
				lvActivityShare.setUncollectedTotal(lvActivityShare.getUncollectedTotal()+lvActivityShare.getAddTotal());//赠送优惠券未领取数量累计
			}
			lvActivityShare.setModifyUserId(users.getId());
			lvActivityShare.setModifyUserName(users.getUserName());
			lvActivityShare.setModifyTime(new Date());
		}
		if(ObjectUtils.isNotEmpty(lvActivityAppointProduct)){
			if(ObjectUtils.isNotEmpty(lvActivityAppointProduct.getAddTotal())){
				lvActivityAppointProduct.setUncollectedTotal(lvActivityAppointProduct.getUncollectedTotal()+lvActivityAppointProduct.getAddTotal());//赠送优惠券未领取数量累计
			}
			lvActivityAppointProduct.setModifyUserId(users.getId());
			lvActivityAppointProduct.setModifyUserName(users.getUserName());
			lvActivityAppointProduct.setModifyTime(new Date());
		}
		lvActivity.setActivityTitle(com.lshop.common.util.StringUtil.replaceChars(lvActivity.getActivityTitle()));
		dto.put("lvActivityLimitTime", lvActivityLimitTime);
		dto.put("lvActivityLimited", lvActivityLimited);
		dto.put("lvActivityLimitOrder", lvActivityLimitOrder);
		dto.put("lvActivityLink", lvActivityLink);
		dto.put("lvActivityLottery", lvActivityLottery);
		dto.put("lvActivityRegister", lvActivityRegister);
		dto.put("lvActivityShare", lvActivityShare);
		dto.put("lvActivity", lvActivity);
		dto.put("lvActivityAppointProduct", lvActivityAppointProduct);
		dto.put("lvActivityMac", lvActivityMac);
		this.doService("LvActivityService", "update", dto);
		
		
		//发送消息到前台，通知前台更新缓存
		sendProductChangeToWeb();
		
		
		//发送改变的数据到前端系统中
		if (lvActivity.getTypeKey() == 4){
			dto.put("activityCode",lvActivity.getCode());
			this.doService("ActivityToWebService", "sendJSONToWeb", dto);
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvActivityAction.edit() method end*****");
		}
		return AJAX;
	}
	
	

	/**
	 * 
	 * @Method: updateStatus 
	 * @Description:  [修改活动状态信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-24 上午11:25:20]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-24 上午11:25:20]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws ActionException 
	 * @return String
	 */
	public String updateStatus()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvActivityAction.edit() method begin*****");
		}
		
		/**************************************************************************
		 * 服务器端验证
		 **************************************************************************/
		dto.put("activityCode", lvActivity.getCode());
		lvActivityLimitTime=(LvActivityLimitTime) this.doService("LvActivityService", "findLimtTimeByCode", dto);
		lvActivityLimited=(LvActivityLimited) this.doService("LvActivityService", "findLimtNumByCode", dto);
		if(lvActivity.getStatus()==ActivityConstant.STATUS_USE){
			if(lvActivity.getTypeKey()==ActivityConstant.TYPE_LIMIT_TIME){
//				lvActivityLimitTime=(LvActivityLimitTime) this.doService("LvActivityService", "findLimtTimeByCode", dto);
				Boolean isFlag=this.isMutexActivity(lvActivityLimitTime.getProductCode());
				if(isFlag){
					this.json.setMessage("该产品存在已经启动的活动!请先关闭该产品的其他活动。");
					this.json.setStatusCode(300);
					return AJAX;
				}
			}if(lvActivity.getTypeKey()==ActivityConstant.TYPE_LIMIT_QUANTITY){
//				lvActivityLimited=(LvActivityLimited) this.doService("LvActivityService", "findLimtNumByCode", dto);
				Boolean isFlag=this.isMutexActivity(lvActivityLimited.getProductCode());
				if(isFlag){
					this.json.setMessage("该产品存在已经启动的活动!请先关闭该产品的其他活动。");
					this.json.setStatusCode(300);
					return AJAX;
				}
			}if(lvActivity.getTypeKey()==ActivityConstant.TYPE_APPOINT_PRODUCT){
				//根据活动查询活动商品信息
				List<LvActivityProduct> productList= lvActivityProductService.findByActivityCode(lvActivity.getCode());
				for (int i = 0; i < productList.size(); i++) {
					LvActivityProduct acp= productList.get(i);
					Boolean isFlag=this.isMutexActivity(acp.getProductCode());
					if(isFlag){
						this.json.setMessage("该产品存在已经启动的活动!请先关闭该产品的其他活动。");
						this.json.setStatusCode(300);
						return AJAX;
					}
				}
			}
		}else if(lvActivity.getStatus()==ActivityConstant.STATUS_DIS_USE){
			if(lvActivity.getTypeKey()==ActivityConstant.TYPE_LOTTERY){
			  //验证是否有选择抽奖活动的启用活动
			  dto.remove("activityCode");
			  dto.put("givenProductCode", lvActivity.getCode());
			  Boolean flag= (Boolean) this.doService("LvActivityService", "isEffectLotteryForLimitOrder", dto);
			  Boolean flagReg= (Boolean) this.doService("LvActivityService", "isEffectActivityRegister", dto);
			  Boolean flagShare= (Boolean) this.doService("LvActivityService", "isEffectActivityShare", dto);
			  Boolean flagAppoint= (Boolean) this.doService("LvActivityService", "isEffectActivityAppointProduct", dto);
			  if(flag||flagReg||flagShare||flagAppoint){
					json.setMessage("其他启用的活动包含了该抽奖活动，不能停用！");
					json.setStatusCode(300);
					return AJAX;
			  }
			}
		}
		//
		
		
		/**************************************************************************
		 * 业务逻辑实现
		 **************************************************************************/
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvActivity.setModifyUserId(users.getId());
		lvActivity.setModifyUserName(users.getUserName());
		lvActivity.setModifyTime(new Date());
		dto.put("lvActivity", lvActivity);
		this.doService("LvActivityService", "updateStatus", dto);
		
		//发送改变的数据到前端系统中
		dto.put("code", lvActivity.getCode());
		lvActivity=(LvActivity) this.doService("LvActivityService", "findByCode", dto);
		if (lvActivity.getTypeKey() == 4){
			dto.put("activityCode",lvActivity.getCode());
			this.doService("ActivityToWebService", "sendJSONToWeb", dto);
		}
		
		//发送消息到前台，通知前台更新缓存
		sendProductChangeToWeb();
		
		this.json.doNavTabTodo();
		if (logger.isInfoEnabled()){
			logger.info("***** LvActivityAction.edit() method end*****");
		}
		return AJAX;
	}
	
	
	
	/**
	 * 
	 * @Method: isMutexActivity 
	 * @Description:  [验证商品显示限量是否同时存在多个活动]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-7-14 下午3:46:54]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-7-14 下午3:46:54]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param productCode
	 * @return String
	 */
	public Boolean isMutexActivity(String productCode){
		dto.put("productCode", productCode);
		Boolean timeFlag=(Boolean) this.doService("LvActivityService", "isExistActivityLimitTime", dto);
		Boolean numFlag=(Boolean) this.doService("LvActivityService", "isExistActivityLimitNum", dto);
		Boolean productFlag=lvActivityProductService.isEffectiveActivity(productCode);
		if(timeFlag||numFlag||productFlag){
		   return true;
		}
		return false;	
	}
	
	/**
	 * 
	 * @Method: selectSimpleCouponType 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2014-8-15 上午11:19:01]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2014-8-15 上午11:19:01]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 */
	public String selectSimpleACLottery(){
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
	
		if(ObjectUtils.isNotEmpty(storeId)){
			mallSysFlag=Constants.STORE_TO_MALL_SYSTEM.get(storeId);
		}
		
		lvActivity.setStatus(ActivityConstant.STATUS_USE);
		dto.put("effectDate", new Date());
		dto.put("lvActivity", lvActivity);
		dto.put("mallSysFlag", mallSysFlag);
		page = (Pagination)this.doService("LvActivityService", "findPage", dto);
		
		return "selectACLottery";
	}
	
	//当产品的限时或限量活动改变（添加）的时候发送消息给前台，通知前台更新缓存
	private void sendProductChangeToWeb(){
		String productCode = null;
		//限时活动
		if(lvActivity.getTypeKey() == ActivityConstant.TYPE_LIMIT_TIME){
			productCode = lvActivityLimitTime.getProductCode();
		}
		//限量活动
		if(lvActivity.getTypeKey() == ActivityConstant.TYPE_LIMIT_QUANTITY){
			productCode = lvActivityLimited.getProductCode();
		}
		if(productCode != null && !"".equals(productCode)){
			productMsgService.sendProductMsg(productCode);
		}
	}

	public LvActivity getLvActivity() {
		return lvActivity;
	}

	public void setLvActivity(LvActivity lvActivity) {
		this.lvActivity = lvActivity;
	}

	public LvActivityLimitTime getLvActivityLimitTime() {
		return lvActivityLimitTime;
	}

	public void setLvActivityLimitTime(LvActivityLimitTime lvActivityLimitTime) {
		this.lvActivityLimitTime = lvActivityLimitTime;
	}

	public LvActivityLimited getLvActivityLimited() {
		return lvActivityLimited;
	}

	public void setLvActivityLimited(LvActivityLimited lvActivityLimited) {
		this.lvActivityLimited = lvActivityLimited;
	}

	public LvActivityLimitOrder getLvActivityLimitOrder() {
		return lvActivityLimitOrder;
	}

	public void setLvActivityLimitOrder(
			LvActivityLimitOrder lvActivityLimitOrder) {
		this.lvActivityLimitOrder = lvActivityLimitOrder;
	}

	public LvActivityLink getLvActivityLink() {
		return lvActivityLink;
	}

	public void setLvActivityLink(LvActivityLink lvActivityLink) {
		this.lvActivityLink = lvActivityLink;
	}

	public LvActivityLottery getLvActivityLottery() {
		return lvActivityLottery;
	}

	public void setLvActivityLottery(LvActivityLottery lvActivityLottery) {
		this.lvActivityLottery = lvActivityLottery;
	}

	public LvActivityRegister getLvActivityRegister() {
		return lvActivityRegister;
	}

	public void setLvActivityRegister(LvActivityRegister lvActivityRegister) {
		this.lvActivityRegister = lvActivityRegister;
	}

	public LvActivityShare getLvActivityShare() {
		return lvActivityShare;
	}

	public void setLvActivityShare(LvActivityShare lvActivityShare) {
		this.lvActivityShare = lvActivityShare;
	}

	
	public LvActivityAppointProduct getLvActivityAppointProduct() {
		return lvActivityAppointProduct;
	}

	public void setLvActivityAppointProduct(
			LvActivityAppointProduct lvActivityAppointProduct) {
		this.lvActivityAppointProduct = lvActivityAppointProduct;
	}

	public LvActivityMac getLvActivityMac() {
		return lvActivityMac;
	}

	public void setLvActivityMac(LvActivityMac lvActivityMac) {
		this.lvActivityMac = lvActivityMac;
	}

	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
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
