/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvActivity.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.lshop.manage.lvActivity.service.LvActivityGiftService;
import com.lshop.manage.lvActivity.service.LvActivityProductService;
import com.lshop.manage.lvActivity.service.LvActivityService;
import com.lshop.manage.lvCoupon.service.CouponTaskService;
import com.lshop.manage.lvCouponType.service.LvCouponTypeService;
import com.lshop.common.activity.constant.ActivityConstant;
import com.lshop.common.coupon.constant.CouponConstant;
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
import com.lshop.common.pojo.logic.LvProductGift;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.StoreHelp;
import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.ObjectUtils;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Service("LvActivityService")
public class LvActivityServiceImpl extends BaseServiceImpl implements LvActivityService {
	private static final Log logger = LogFactory.getLog(LvActivityServiceImpl.class);
	@Resource
	private HibernateBaseDAO dao;
	@Resource
	private LvCouponTypeService lvCouponTypeService;
	@Resource
	private CouponTaskService couponTaskService;
	@Resource
	private LvActivityGiftService lvActivityGiftService;
	@Resource
	private LvActivityProductService lvActivityProductService;
	
	/**
	 * 获得所有
	 */
	public List<LvActivity> findAllActivity(Dto dto)throws SecurityException{
		Integer typeKey=(Integer) dto.get("typeKey");
		StringBuffer hql=new StringBuffer("select t from LvActivity where status=1 and typeKey=:typeKey");
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("typeKey", typeKey);
		List<LvActivity> list =dao.find(hql.toString(), param);
		return list;
	}
	
	public List<LvActivity> findAllActivityLimitOrder(Dto dto) throws ServiceException {
		String givenProductCode=(String) dto.get("givenProductCode");
		StringBuilder hql = new StringBuilder("select t from LvActivity t,LvActivityLimitOrder o where t.code=o.activityCode" +
				" and o.givenTypeNum=1 and o.givenProductCode=:givenProductCode");
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("givenProductCode", givenProductCode);
		List<LvActivity> list =dao.find(hql.toString(), param);
		return list;
	}
	
	public List<LvActivity> findAllActivityLink(Dto dto) throws ServiceException {
		String givenProductCode=(String) dto.get("givenProductCode");
		StringBuilder hql = new StringBuilder("select t from LvActivity t,LvActivityLink o where t.code=o.activityCode" +
				" and o.givenTypeNum=1 and o.givenProductCode=:givenProductCode");
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("givenProductCode", givenProductCode);
		List<LvActivity> list =dao.find(hql.toString(), param);
		return list;
	}
	
	public List<LvActivity> findAllActivityRegister(Dto dto) throws ServiceException {
		String givenProductCode=(String) dto.get("givenProductCode");
		StringBuilder hql = new StringBuilder("select t from LvActivity t,LvActivityRegister o where t.code=o.activityCode" +
				" and o.givenTypeNum=1 and o.givenProductCode=:givenProductCode");
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("givenProductCode", givenProductCode);
		List<LvActivity> list =dao.find(hql.toString(), param);
		return list;
	}
	
	public List<LvActivity> findAllActivityShare(Dto dto) throws ServiceException {
		String givenProductCode=(String) dto.get("givenProductCode");
		StringBuilder hql = new StringBuilder("select t from LvActivity t,LvActivityShare o where t.code=o.activityCode" +
				" and o.givenTypeNum=1 and o.givenProductCode=:givenProductCode");
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("givenProductCode", givenProductCode);
		List<LvActivity> list =dao.find(hql.toString(), param);
		return list;
	}
	
	public List<LvActivity> findAllActivityLottery(Dto dto) throws ServiceException{
		String givenProductCode=(String) dto.get("givenProductCode");
		StringBuilder hql = new StringBuilder("select t from LvActivity t,LvLotteryPrize o where t.code=o.activityCode" +
				" and o.prizeType=1 and o.prizeCode=:givenProductCode");
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("givenProductCode", givenProductCode);
		List<LvActivity> list =dao.find(hql.toString(), param);
		return list;
	}
	
	public List<LvActivity> findAllActivityPointProduct(Dto dto) throws ServiceException{
		String givenProductCode=(String) dto.get("givenProductCode");
		StringBuilder hql = new StringBuilder("select t from LvActivity t,LvActivityAppointProduct o where t.code=o.activityCode" +
				" and o.givenTypeNum=1 and o.givenProductCode=:givenProductCode");
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("givenProductCode", givenProductCode);
		List<LvActivity> list =dao.find(hql.toString(), param);
		return list;
	}
	
	

	/**
	 * 分页查询
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination findPage(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvActivityServiceImpl.findPage() method begin*****");
		}
		SimplePage page = (SimplePage)dto.get("page");
		LvActivity lvActivity = (LvActivity)dto.get("lvActivity");
		Date effectDate=(Date)dto.get("effectDate");
		String mallSysFlag =(String) dto.get("mallSysFlag");
		Map<String ,Object> param=new HashMap<String ,Object>();
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from LvActivity t where 1=1 ");
        if(ObjectUtils.isNotEmpty(lvActivity)){
        	if(ObjectUtils.isNotEmpty(lvActivity.getActivityTitle())) {
	        	sql.append(" and  t.activityTitle like :activityTitle ");
	        	param.put("activityTitle", "%"+lvActivity.getActivityTitle().trim()+"%");
	        }
	        if(ObjectUtils.isNotEmpty(lvActivity.getStatus())) {
	        	sql.append(" and  t.status = :status ");
	        	param.put("status", lvActivity.getStatus());
	        }	
	        if(ObjectUtils.isNotEmpty(lvActivity.getTypeKey())) {
	        	sql.append(" and  t.typeKey = :typeKey ");
	        	param.put("typeKey", lvActivity.getTypeKey());
	        }	
	        if(ObjectUtils.isNotEmpty(effectDate)){
        		sql.append(" and  t.startTime<=:startTime and t.endTime >= :endTime ");
        		param.put("startTime", effectDate);
        		param.put("endTime", effectDate);
	        }
        }
        if(ObjectUtils.isNotEmpty(mallSysFlag)&&lvActivity.getTypeKey()==ActivityConstant.TYPE_LOTTERY){
        	sql.append(" and EXISTS (select 1 from LvActivityLottery acl where t.code=acl.activityCode and acl.storeId=:storeId)");
        	param.put("storeId", mallSysFlag);
        }
        sql.append(" order by t.createTime desc ");
		Finder finder = Finder.create(sql.toString());
		finder.setParams(param);
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvActivityServiceImpl.findPage() method end*****");
		}
		return pag;
	}
	/**
	 * 获得单独的实体信息
	 */
	public LvActivity get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvActivityServiceImpl.get() method begin*****");
		}
		LvActivity lvActivity = (LvActivity)dto.get("lvActivity");
		lvActivity = (LvActivity)dao.load(LvActivity.class, lvActivity.getId());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvActivityServiceImpl.get() method end*****");
		}
		return  lvActivity;
	}
	/**
	 * 根据活动code获得单独的实体信息
	 */
	public LvActivity findByCode(Dto dto)throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvActivityServiceImpl.get() method begin*****");
		}
		String code=(String) dto.get("code");
		LvActivity lvActivity = (LvActivity)dao.findUniqueByProperty(LvActivity.class, "code", code);
		if (logger.isInfoEnabled()){
			logger.info("***** LvActivityServiceImpl.get() method end*****");
		}
		return  lvActivity;
	}
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvActivityServiceImpl.del() method begin*****");
		}
		LvActivity lvActivity = get(dto);
		dao.delete(  lvActivity);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvActivityServiceImpl.del() method begin*****");
		}
	}


    /**
     * 
     * @Method: save 
     * @Description:  [活动信息保存入口程序]  
     ******************************************************************************
	 * 新增活动信息方法说明：
	 * 1.saveActivityLimitTime 保存限时活动
	 * 2.saveActivityLimted 保存限量活动
	 * 3.saveActivityLimitOrder 保存订单满就送活动
	 * 4.saveActivityLink 保存链接链接就送活动
	 * 5.saveActivityRegister 保存注册就活动
	 * 6.saveActivityShare 保存分享活动
	 * 7.saveActivityLottery 保存抽奖活动
	 * 8.saveActivityAppintProduct 保存购买指定商品就送活动
	 * 9.saveActivityMac 保存输入mac就送活动
	 ******************************************************************************
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2015-1-9 上午11:49:13]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2015-1-9 上午11:49:13]   
     * @UpdateRemark: [说明本次修改内容]  
     * @throws
     */
	public LvActivity save(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvActivityServiceImpl.save() method begin*****");
		}
		 BaseUsers user=(BaseUsers) dto.get("users");
		 String operator=user.getUserName();
		 LvActivity lvActivity = (LvActivity)dto.get("lvActivity");
		 LvActivityLimitTime lvActivityLimitTime = (LvActivityLimitTime)dto.get("lvActivityLimitTime");
		 LvActivityLimited lvActivityLimited = (LvActivityLimited)dto.get("lvActivityLimited");
		 LvActivityLimitOrder lvActivityLimitOrder = (LvActivityLimitOrder)dto.get("lvActivityLimitOrder");
		 LvActivityLink lvActivityLink = (LvActivityLink)dto.get("lvActivityLink");
		 LvActivityLottery lvActivityLottery=(LvActivityLottery) dto.get("lvActivityLottery");
		 LvActivityRegister lvActivityRegister=(LvActivityRegister) dto.get("lvActivityRegister");
		 LvActivityShare lvActivityShare=(LvActivityShare) dto.get("lvActivityShare");
		 LvActivityAppointProduct lvActivityAppointProduct=(LvActivityAppointProduct) dto.get("lvActivityAppointProduct");
		 LvActivityMac lvActivityMac=(LvActivityMac) dto.get("lvActivityMac");
		 
		 if(lvActivity.getTypeKey()==ActivityConstant.TYPE_LIMIT_TIME){//1=限时活动
			 //保存限时活动
			 saveActivityLimitTime(lvActivity.getCode(), lvActivityLimitTime);
		 }else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_LIMIT_QUANTITY){//限量活动
			 //保存限量活动
			 saveActivityLimted(lvActivity.getCode(), lvActivityLimited);
		 }else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_LIMIT_ORDER){//订单满就送活动
			 //保存订单满就送活动
			 saveActivityLimitOrder(lvActivity.getCode(),operator, lvActivityLimitOrder);
		 }else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_LINK){//点击连接就送活动
			 //保存链接链接就送活动
			 saveActivityLink(lvActivity.getCode(),operator,lvActivityLink);
		 }else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_REGISTER){//注册就送活动
			 //保存注册就活动
			 saveActivityRegister(lvActivity.getCode(),operator, lvActivityRegister);
		 }else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_SHARE){//分享就送活动
			 //保存分享活动
			 saveActivityShare(lvActivity.getCode(),operator,lvActivityShare);
		 }else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_LOTTERY){//抽奖活动
			 //保存抽奖活动
			 saveActivityLottery(lvActivity.getCode(), lvActivityLottery);
		 }else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_APPOINT_PRODUCT){//购买指定商品就送活动
			 //保存购买指定商品就送活动
			 saveActivityAppintProduct(lvActivity.getCode(),operator, lvActivityAppointProduct);
		 }else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_MAC){
			 saveActivityMac(lvActivity.getCode(), lvActivityAppointProduct,lvActivityMac);
		 }
		 //保存活动公共信息
		 dao.save( lvActivity);
		 
		 if(logger.isInfoEnabled()){
				logger.info("***** LvActivityServiceImpl.save() method end*****");
		 }
		 return   lvActivity;
	}

	/**
	 * 
	 * @Method: saveActivityAppintProduct 
	 * @Description:  [保存购买指定商品就送活动]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-9 下午1:45:42]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-9 下午1:45:42]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param activityCode 活动编码
	 * @param operator 操作人
	 * @param lvActivityAppointProduct 购买指定商品信息
	 * @return void
	 */
	private void saveActivityAppintProduct(String activityCode,String operator,LvActivityAppointProduct lvActivityAppointProduct) {
		if(lvActivityAppointProduct.getGivenTypeNum()==ActivityConstant.GIVEN_TYPE_NUM_LOTTORY){
			 lvActivityAppointProduct.setGivenProductCode(lvActivityAppointProduct.getAcLotteryCode());
			 lvActivityAppointProduct.setGivenTypeName(lvActivityAppointProduct.getAcLotteryName());
		 }
		 lvActivityAppointProduct.setActivityCode(activityCode);
		 lvActivityAppointProduct.setCode(CodeUtils.getCode());
		 lvActivityAppointProduct.setCreateTime(new Date());
		 dao.save(lvActivityAppointProduct);
		 
		 //保存商品和活动的关系
		 List<LvActivityProduct> productList= lvActivityAppointProduct.getAcProcuctList();
		 lvActivityProductService.saveBatch(productList, activityCode);
		 
		 
		 //保存商品和赠品的关系
		 List<LvActivityGift> giftList=lvActivityAppointProduct.getAcGiftList();
		 lvActivityGiftService.saveBatch(giftList, activityCode);
		 
		//保存商品和赠品的关系
		 saveProductGiftBatch(productList, giftList);
		 
		 //新增活动优惠券数目业务逻辑处理
		 createBatchCoupon(activityCode,
		                   lvActivityAppointProduct.getUncollectedTotal(),
				           lvActivityAppointProduct.getGivenTypeNum(), 
				           lvActivityAppointProduct.getGivenProductCode(), 
				           CouponConstant.TYPE_ONLINE,
				           operator,
				           "");
	}

	/**
	 * 
	 * @Method: saveProductGiftBatch 
	 * @Description:  [保存购买指定商品就送活动-商品赠品关系维护]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-9 下午2:28:03]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-9 下午2:28:03]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param productList 商品信息
	 * @param giftList  赠品信息
	 * @return void
	 */
	private void saveProductGiftBatch(List<LvActivityProduct> productList,List<LvActivityGift> giftList) {
		//保存商品和赠品的关系
		 if(ObjectUtils.isNotEmpty(productList)){
			 for (LvActivityProduct activityProduct : productList) {
				 if(ObjectUtils.isNotEmpty(giftList)){
					 for (LvActivityGift activityGift : giftList) {
					      LvProductGift pgift=new LvProductGift();
					      pgift.setProductCode(activityProduct.getProductCode());
					      pgift.setGiftCode(activityGift.getGiftCode());
					      pgift.setCode(CodeUtils.getCode());
					      pgift.setCreateTime(new Date());
						  dao.save(pgift);
					 }
				 }
			 }
		 }
	}

	/**
	 * 
	 * @Method: saveActivityLottery 
	 * @Description:  [保存抽奖活动详情信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-9 下午2:22:52]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-9 下午2:22:52]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param lvActivity
	 * @param lvActivityLottery 
	 * @return void
	 */
	private void saveActivityLottery(String activityCode,LvActivityLottery lvActivityLottery) {
		String demain=StoreHelp.getStoreDomainByFlag(lvActivityLottery.getStoreId());
		 lvActivityLottery.setActivityUrl("http://"+demain+"web/"+lvActivityLottery.getStoreId()+"/lottery/pan.jsp");
		 lvActivityLottery.setActivityCode(activityCode);
		 lvActivityLottery.setCode(CodeUtils.getCode());
		 lvActivityLottery.setCreateTime(new Date());
		 dao.save(lvActivityLottery);
	}

	/**
	 * 
	 * @Method: saveActivityShare 
	 * @Description:  [保存分享活动信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-9 下午2:29:27]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-9 下午2:29:27]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param activityCode 活动编码
	 * @param operator 操作人
	 * @param lvActivityShare  分享活动信息
	 * @return void
	 */
	private void saveActivityShare(String activityCode,String operator,LvActivityShare lvActivityShare) {
		if(lvActivityShare.getGivenTypeNum()==ActivityConstant.GIVEN_TYPE_NUM_LOTTORY){
			 lvActivityShare.setGivenProductCode(lvActivityShare.getAcLotteryCode());
			 lvActivityShare.setGivenTypeName(lvActivityShare.getAcLotteryName());
		 }
		 lvActivityShare.setActivityCode(activityCode);
		 lvActivityShare.setCode(CodeUtils.getCode());
		 lvActivityShare.setCreateTime(new Date());
		 dao.save(lvActivityShare);
		 
		 //新增活动优惠券数目业务逻辑处理
		 createBatchCoupon(activityCode,
		                  lvActivityShare.getUncollectedTotal(),
				          lvActivityShare.getGivenTypeNum(), 
				          lvActivityShare.getGivenProductCode(), 
				          CouponConstant.TYPE_ONLINE,
				          operator,
				          "");
	}

	/**
	 * 
	 * @Method: saveActivityLink 
	 * @Description:  [保存点击链接就送活动]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-9 下午2:32:01]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-9 下午2:32:01]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param activityCode 活动编码
	 * @param operator 操作人
	 * @param lvActivityLink 连接活动信息
	 * @return void
	 */
	private void saveActivityLink(String activityCode,String operator,LvActivityLink lvActivityLink) {
		 lvActivityLink.setActivityCode(activityCode);
		 lvActivityLink.setCode(CodeUtils.getCode());
		 lvActivityLink.setCreateTime(new Date());
		 lvActivityLink.setGrantTotal(0);
		 //查询优惠券商品信息，拼装活动链接信息
		 String hql="select st from LvStore st where code in(select s.parentCode from LvStore s where s.storeFlag in" +
		 		"(select storeId from LvCouponProduct p where p.couponTypeCode=:couponTypeCode))";
		 Map<String,Object> param=new HashMap<String, Object>();
		 param.put("couponTypeCode", lvActivityLink.getGivenProductCode());
		 List<LvStore> list= dao.find(hql, param);
		 /**
		  * 拼接活动连接示例：
		  * http://www.itvpad.com/web/activity!getCouponByActivityLink.action?activityCode=dffe92012ba44218bff594d996e49837
		  */
		 StringBuffer linkHtml=new StringBuffer();
		 for (LvStore lvStore : list) {
			 linkHtml.append("<div>\r\n活动商城名称："+lvStore.getName().trim()+"\r\n");
			 linkHtml.append("活动链接：http://"+lvStore.getDomainName().trim());
			 if(ObjectUtils.isNotEmpty(lvActivityLink.getStrategyType())&&lvActivityLink.getStrategyType()==2){
			   linkHtml.append("/web/activity!getCouponByActivityLink.action?activityCode="+activityCode);
			 }else{
			   linkHtml.append("/web/activity!getCouponByActivityLinkLogout.action?activityCode="+activityCode);
			 }
			 linkHtml.append("\r\n</div>\r\n");
		 }
		 lvActivityLink.setActivityHtml(linkHtml.toString());
		 dao.save(lvActivityLink);
		 		 
		 //新增活动优惠券数目业务逻辑处理	 
		 if(ObjectUtils.isNotEmpty(lvActivityLink.getStrategyType())&&lvActivityLink.getStrategyType()==2){//生成线上需登录的优惠码
		     createBatchCoupon(activityCode, 
		    		           lvActivityLink.getUncollectedTotal(),          
		    		           lvActivityLink.getGivenTypeNum(), 
				               lvActivityLink.getGivenProductCode(), 
				               CouponConstant.TYPE_ONLINE,
				               operator,
				               "");
		 }else{//生成免需登录的优惠码
			 createBatchCoupon(activityCode, 
					           lvActivityLink.getUncollectedTotal(),         
					           lvActivityLink.getGivenTypeNum(), 
			                   lvActivityLink.getGivenProductCode(), 
			                   CouponConstant.TYPE_BELOW_LINE,
			                   operator,
			                   "");
		 }
	}

	/**
	 * 
	 * @Method: saveActivityLimted 
	 * @Description:  [保存限量活动信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-9 下午2:33:12]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-9 下午2:33:12]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param activityCode 活动编码
	 * @param lvActivityLimited 限量活动信息
	 * @return void
	 */
	private void saveActivityLimted(String activityCode,LvActivityLimited lvActivityLimited) {
		 lvActivityLimited.setActivityCode(activityCode);
		 lvActivityLimited.setCode(CodeUtils.getCode());
		 lvActivityLimited.setCreateTime(new Date());
		 dao.save(lvActivityLimited);
	}

	/**
	 * 
	 * @Method: saveActivityLimitTime 
	 * @Description:  [保存限时活动信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-9 下午2:39:04]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-9 下午2:39:04]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param activityCode 活动编码
	 * @param lvActivityLimitTime 限时活动详情
	 * @return void
	 */
	private void saveActivityLimitTime(String activityCode,LvActivityLimitTime lvActivityLimitTime) {
		 lvActivityLimitTime.setActivityCode(activityCode);
		 lvActivityLimitTime.setCode(CodeUtils.getCode());
		 lvActivityLimitTime.setCreateTime(new Date());
		 dao.save(lvActivityLimitTime);
	}

    /**
     * 
     * @Method: saveActivityRegister 
     * @Description:  [保存注册就送活动信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2015-1-9 下午2:38:12]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2015-1-9 下午2:38:12]   
     * @UpdateRemark: [说明本次修改内容]  
     * @param activityCode 活动编码
	 * @param operator 操作人
     * @param lvActivityRegister 注册就送活动信息
     * @return void
     */
	private void saveActivityRegister(String activityCode,String operator,LvActivityRegister lvActivityRegister) {
		if(lvActivityRegister.getGivenTypeNum()==ActivityConstant.GIVEN_TYPE_NUM_LOTTORY){
			 lvActivityRegister.setGivenProductCode(lvActivityRegister.getAcLotteryCode());
			 lvActivityRegister.setGivenTypeName(lvActivityRegister.getAcLotteryName());
		 }
		 lvActivityRegister.setActivityCode(activityCode);
		 lvActivityRegister.setCode(CodeUtils.getCode());
		 lvActivityRegister.setCreateTime(new Date());
		 dao.save(lvActivityRegister);
		 

		//新增活动优惠券数目业务逻辑处理
		createBatchCoupon(activityCode, 
				         lvActivityRegister.getUncollectedTotal(),	      
				         lvActivityRegister.getGivenTypeNum(), 
					     lvActivityRegister.getGivenProductCode(), 
					     CouponConstant.TYPE_ONLINE,
					     operator,
					     "");
	}

	/**
	 * 
	 * @Method: saveActivityLimitOrder 
	 * @Description:  [保存订单满就送活动]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-9 下午2:36:38]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-9 下午2:36:38]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param activityCode 活动编码
	 * @param operator 操作人
	 * @param lvActivityLimitOrder 订单满就送活动信息
	 * @return void
	 */
	private void saveActivityLimitOrder(String activityCode,String operator,LvActivityLimitOrder lvActivityLimitOrder) {
		if(lvActivityLimitOrder.getGivenTypeNum()==2){
			 lvActivityLimitOrder.setGivenProductCode(lvActivityLimitOrder.getAcLotteryCode());
			 lvActivityLimitOrder.setGivenTypeName(lvActivityLimitOrder.getAcLotteryName());
		 }
		 lvActivityLimitOrder.setActivityCode(activityCode);
		 lvActivityLimitOrder.setCode(CodeUtils.getCode());
		 lvActivityLimitOrder.setCreateTime(new Date());
		 lvActivityLimitOrder.setGrantTotal(0);
		 lvActivityLimitOrder.setOccupiedTotal(0);
		 dao.save(lvActivityLimitOrder);
		 

		//新增活动优惠券数目业务逻辑处理
		createBatchCoupon(activityCode, 
				         lvActivityLimitOrder.getUncollectedTotal(),          
				         lvActivityLimitOrder.getGivenTypeNum(), 
						 lvActivityLimitOrder.getGivenProductCode(), 
						 CouponConstant.TYPE_ONLINE,
						 operator,
						 "");
	}
	
	
	
	/**
	 * 
	 * @Method: saveActivityMac 
	 * @Description:  [保存输入mac就送活动]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015年7月10日 下午5:36:54]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015年7月10日 下午5:36:54]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param activityCode 活动编码
	 * @param lvActivityAppointProduct 商品集合
	 * @param lvActivityMac 
	 * @return void
	 */
	private void saveActivityMac(String activityCode,LvActivityAppointProduct lvActivityAppointProduct,LvActivityMac lvActivityMac){
		lvActivityMac.setActivityCode(activityCode);
		lvActivityMac.setCode(CodeUtils.getCode());
		lvActivityMac.setCreateTime(new Date());
		dao.save(lvActivityMac);
		
		//保存商品和活动的关系
		List<LvActivityProduct> productList= lvActivityAppointProduct.getAcProcuctList();
		lvActivityProductService.saveBatch(productList, activityCode);
	}
	
	/******************************************************************************
	 * 更新各种类型活动信息说明：
	 * 1.updateActivityLimitOrder 更新订单满就送活动信息
	 * 2.updateActivityLink 更新点击连接就送活动信息
	 * 3.updateActivityRegister 更新注册就送活动信息
	 * 4.updateActivityShare 更新分享就送活动信息
	 * 5.updateActivityLottery 更新抽奖活动信息
	 * 6.updateActivityAppiontProduct 更新指定购买商品就送活动信息
	 * 7.updateActivityMac 更新输入mac就送活动信息
	 ******************************************************************************/

	/**
	 * 
	 * @Method: update 
	 * @Description:  [活动信息修改入口程序]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-9 下午2:17:13]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-9 下午2:17:13]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public LvActivity update(Dto dto) throws ServiceException {
		 if(logger.isInfoEnabled()){
				logger.info("***** LvActivityServiceImpl.update() method begin*****");
		 }
		 LvActivity lvActivity = (LvActivity)dto.get("lvActivity");
		 LvActivityLimitTime lvActivityLimitTime = (LvActivityLimitTime)dto.get("lvActivityLimitTime");
		 LvActivityLimited lvActivityLimited = (LvActivityLimited)dto.get("lvActivityLimited");
		 LvActivityLimitOrder lvActivityLimitOrder = (LvActivityLimitOrder)dto.get("lvActivityLimitOrder");
		 LvActivityLink lvActivityLink = (LvActivityLink)dto.get("lvActivityLink");
		 LvActivityLottery lvActivityLottery=(LvActivityLottery) dto.get("lvActivityLottery");
		 LvActivityRegister lvActivityRegister=(LvActivityRegister) dto.get("lvActivityRegister");
		 LvActivityShare lvActivityShare=(LvActivityShare) dto.get("lvActivityShare");
		 LvActivityAppointProduct lvActivityAppointProduct=(LvActivityAppointProduct) dto.get("lvActivityAppointProduct");
		 LvActivityMac lvActivityMac=(LvActivityMac) dto.get("lvActivityMac");
		 
		 if(lvActivity.getTypeKey()==ActivityConstant.TYPE_LIMIT_TIME){
			 //更新限时活动信息
			 dao.update(lvActivityLimitTime);
		 }else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_LIMIT_QUANTITY){
			 //更新限量活动信息
			 dao.update(lvActivityLimited);
		 }else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_LIMIT_ORDER){
			 //更新订单满就送活动信息
			 updateActivityLimitOrder(lvActivity.getCode(), lvActivityLimitOrder);
		 }else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_LINK){
			 //更新点击连接就送活动信息
			 updateActivityLink(lvActivity.getCode(), lvActivityLink);
		 }else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_REGISTER){
			 //更新注册就送活动信息
			 updateActivityRegister(lvActivity.getCode(), lvActivityRegister);			 
		 }else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_SHARE){
			 //更新分享就送活动信息
			 updateActivityShare(lvActivity.getCode(), lvActivityShare);	
		 }else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_LOTTERY){
			 //更新抽奖活动信息
			 updateActivityLottery(lvActivity, lvActivityLottery);
		 }else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_APPOINT_PRODUCT){
			 //更新指定购买商品就送活动信息
			 updateActivityAppiontProduct(lvActivity.getCode(),lvActivityAppointProduct);	
		 }else if(lvActivity.getTypeKey()==ActivityConstant.TYPE_MAC){
			 //更新指定购买商品就送活动信息
			 updateActivityMac(lvActivity.getCode(),lvActivityMac);	
		 }
		 
		 //更新活动公共信息
		 dao.update(lvActivity);
		 
		 if(logger.isInfoEnabled()){
				logger.info("***** LvActivityServiceImpl.update() method end*****");
		 }
		return  lvActivity;
	}

	/**
	 * 
	 * @Method: updateActivityLimitOrder 
	 * @Description:  [更新订单满额送活动信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-9 下午3:17:26]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-9 下午3:17:26]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param activityCode 活动编码
	 * @param lvActivityLimitOrder 订单满就送信息
	 * @return void
	 */
	private void updateActivityLimitOrder(String activityCode,LvActivityLimitOrder lvActivityLimitOrder) {
		 //更新订单满额送活动信息
		 dao.update(lvActivityLimitOrder);
			 
		 //追加活动优惠券数目业务逻辑处理
		 createBatchCoupon(activityCode, 
				           lvActivityLimitOrder.getAddTotal(),          
				           lvActivityLimitOrder.getGivenTypeNum(), 
				           lvActivityLimitOrder.getGivenProductCode(), 
				           CouponConstant.TYPE_ONLINE,
				           lvActivityLimitOrder.getModifyUserName(),
				           "");

	}

	/**
	 * 
	 * @Method: updateActivityAppiontProduct 
	 * @Description:  [保存购买指定商品就送活动详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-9 下午3:21:21]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-9 下午3:21:21]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param activityCode 活动编码
	 * @param lvActivityAppointProduct 购买指定商品活动信息
	 * @return void
	 */
	private void updateActivityAppiontProduct(String activityCode,LvActivityAppointProduct lvActivityAppointProduct) {
		 //保存购买指定商品就送活动详情
		 dao.update(lvActivityAppointProduct);
		 
		 //处理活动赠品数目业务逻辑
		 List<LvActivityGift> gitfList= lvActivityAppointProduct.getAcGiftList();
		 if(ObjectUtils.isNotEmpty(gitfList)){
			 for (LvActivityGift activityGift : gitfList) {
				 Integer giftAddNum= activityGift.getGiftAddNum();
				 Integer giftTotalNum=activityGift.getGiftTotalNum()+giftAddNum;
				 Integer giftSerplusNum=activityGift.getGiftSerplusNum()+giftAddNum;
				 
				 //更新活动赠品统计数目
				 lvActivityGiftService.updateGiftNum(activityGift.getGiftEveryNum(), giftTotalNum, giftSerplusNum, activityGift.getOrderValue(), activityGift.getId());
			}
		 }

		 //追加活动优惠券数目业务逻辑处理
		 createBatchCoupon(activityCode,
				           lvActivityAppointProduct.getAddTotal(),
				           lvActivityAppointProduct.getGivenTypeNum(), 
				           lvActivityAppointProduct.getGivenProductCode(), 
				           CouponConstant.TYPE_ONLINE,
				           lvActivityAppointProduct.getModifyUserName(),
				           "");
	}



	/**
	 * 
	 * @Method: updateActivityLottery 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-9 下午3:38:49]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-9 下午3:38:49]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param lvActivity
	 * @param lvActivityLottery 
	 * @return void
	 */
	private void updateActivityLottery(LvActivity lvActivity,LvActivityLottery lvActivityLottery) {
		//保存抽奖活动详情
		 dao.update(lvActivityLottery);
		 
		 //同步活动名称变更
		 if(ObjectUtils.isNotEmpty(lvActivity.getActivityTitle())&&ObjectUtils.isNotEmpty(lvActivity.getOldActivityTitle())){
			 if(!lvActivity.getActivityTitle().equals(lvActivity.getOldActivityTitle())){
				 this.updateActvityGivenName("LvActivityRegister", ActivityConstant.GIVEN_TYPE_NUM_LOTTORY,lvActivity.getCode(),lvActivity.getActivityTitle());
				 this.updateActvityGivenName("LvActivityShare", ActivityConstant.GIVEN_TYPE_NUM_LOTTORY,lvActivity.getCode(),lvActivity.getActivityTitle());
				 this.updateActvityGivenName("LvActivityLimitOrder", ActivityConstant.GIVEN_TYPE_NUM_LOTTORY,lvActivity.getCode(),lvActivity.getActivityTitle());
				 this.updateActvityGivenName("LvActivityAppointProduct", ActivityConstant.GIVEN_TYPE_NUM_LOTTORY,lvActivity.getCode(),lvActivity.getActivityTitle());
			 }
		 }
	}

	/**
	 * 
	 * @Method: updateActivityShare 
	 * @Description:  [更新分享赠送活动信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-9 下午3:46:39]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-9 下午3:46:39]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param activityCode 活动编码
	 * @param lvActivityShare 共享活动信息
	 * @return void
	 */
	private void updateActivityShare(String activityCode,LvActivityShare lvActivityShare) {
		//更新分享赠送活动信息
		dao.update(lvActivityShare);
		 
		//追加活动优惠券数目业务逻辑处理
		createBatchCoupon(activityCode, 
				          lvActivityShare.getAddTotal(),          
				          lvActivityShare.getGivenTypeNum(), 
				          lvActivityShare.getGivenProductCode(), 
				          CouponConstant.TYPE_ONLINE,
				          lvActivityShare.getModifyUserName(),
				          "");
	}

	/**
	 * 
	 * @Method: updateActivityRegister 
	 * @Description:  [更新注册就送活动]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-9 下午4:14:38]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-9 下午4:14:38]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param activityCode
	 * @param lvActivityRegister 
	 * @return void
	 */
	private void updateActivityRegister(String activityCode,LvActivityRegister lvActivityRegister) {
		dao.update(lvActivityRegister);
				 
		//追加活动优惠券数目业务逻辑处理
	    createBatchCoupon(activityCode, 
	    		          lvActivityRegister.getAddTotal(),	     
	    		          lvActivityRegister.getGivenTypeNum(), 
					      lvActivityRegister.getGivenProductCode(), 
					      CouponConstant.TYPE_ONLINE,
					      lvActivityRegister.getModifyUserName(),
					      "");
	}

	/**
	 * 
	 * @Method: updateActivityLink 
	 * @Description:  [更新点击连接就送活动]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-9 下午4:45:19]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-9 下午4:45:19]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param activityCode 活动编码
	 * @param lvActivityLink 连接活动信息
	 * @return void
	 */
	private void updateActivityLink(String activityCode,LvActivityLink lvActivityLink) {
		 //更新点击连接就送活动
		 dao.update(lvActivityLink);
		 
		 //追加活动优惠券数目业务逻辑处理	 
		 if(ObjectUtils.isNotEmpty(lvActivityLink.getStrategyType())&&lvActivityLink.getStrategyType()==2){//生成线上需登录的优惠码
			 createBatchCoupon(activityCode, 
					           lvActivityLink.getAddTotal(),	       
					           lvActivityLink.getGivenTypeNum(), 
			    		       lvActivityLink.getGivenProductCode(), 
							   CouponConstant.TYPE_ONLINE,
							   lvActivityLink.getModifyUserName(),
							   "");
		 }else if(ObjectUtils.isNotEmpty(lvActivityLink.getStrategyType())&&lvActivityLink.getStrategyType()==1){//生成免需登录的优惠码
			 createBatchCoupon(activityCode, 
					          lvActivityLink.getAddTotal(),          
					          lvActivityLink.getGivenTypeNum(), 
			                  lvActivityLink.getGivenProductCode(), 
			                  CouponConstant.TYPE_BELOW_LINE,
			                  lvActivityLink.getModifyUserName(),
			                  "");
		 }
	}
	
	
	/**
	 * 
	 * @Method: updateActivityMac 
	 * @Description:  [更新输入mac就送活动信息]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015年7月10日 下午5:33:41]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015年7月10日 下午5:33:41]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param activityCode
	 * @param lvActivityMac 
	 * @return void
	 */
	private void updateActivityMac(String activityCode,LvActivityMac lvActivityMac){
		dao.update(lvActivityMac);
	}
	
	
	
	/**
	 * 
	 * @Method: updateStatus 
	 * @Description:  [修改活动状态信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-24 上午11:22:30]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-24 上午11:22:30]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return LvActivity
	 */
	public LvActivity updateStatus(Dto dto)throws ServiceException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvActivityServiceImpl.updateStatus() method begin*****");
		}
		LvActivity lvActivity = (LvActivity)dto.get("lvActivity");
		String hql = "update LvActivity set status=:status ," 
				   + " modifyUserId=:modifyUserId," 
				   + " modifyUserName=:modifyUserName,"
				   + " modifyTime=:modifyTime"
				   + " where id =:id ";
		Map param = new HashMap();
		param.put("status", lvActivity.getStatus());
		param.put("modifyUserId", lvActivity.getModifyUserId());
		param.put("modifyUserName", lvActivity.getModifyUserName());
		param.put("modifyTime", lvActivity.getModifyTime());
		param.put("id", lvActivity.getId());
		dao.update(hql, param);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvActivityServiceImpl.updateStatus() method end*****");
		}
		return lvActivity;
	}
	
	/******************************************************************************
	 * 更新活动名称
	 ******************************************************************************/
	public void updateActvityGivenName(String tableName,Short givenTypeNum,String givenProductCode,String givenTypeName) throws ServiceException {
		  String hql="update "+tableName+" set givenTypeName=:givenTypeName" +
	      		    " where givenProductCode=:givenProductCode" +
	      		    " and givenTypeNum=:givenTypeNum";
	      Map<String,Object> param=new HashMap<String, Object>();
	      param.put("givenTypeNum", givenTypeNum);
	      param.put("givenProductCode", givenProductCode);
	      param.put("givenTypeName", givenTypeName);
	      dao.update(hql, param);
	}
	
	
	
	
	/**
	 * 
	 * @Method: createBatchCoupon 
	 * @Description:  [活动生产优惠券入口程序]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-9 下午4:29:36]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-9 下午4:29:36]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param activityCode 活动编码
	 * @param createNum 生产数量
	 * @param givenTypeNum 活动赠送类型（1=选择优惠券，2=赠送抽奖机会，3=赠送礼品）
	 * @param couponTypeCode 优惠券编码
	 * @param couponType 优惠券类型(1=线上，2=线下)
	 * @param operator 操作人
	 * @param prizeCode 奖项编码（非必填）
	 * @return void
	 */
	public void createBatchCoupon(String activityCode,Integer createNum,Short givenTypeNum,String couponTypeCode,Short couponType,String operator,String prizeCode) {
		if(givenTypeNum==ActivityConstant.GIVEN_TYPE_NUM_COUPON){
             //查询优惠券信息
			 LvCouponType lvCouponType=lvCouponTypeService.findByCode(couponTypeCode);
			 if(ObjectUtils.isNotEmpty(createNum)){
				 if(ObjectUtils.isEmpty(prizeCode)){
					//生成对应数量优惠码任务
					 couponTaskService.createBatchCoupon(createNum, activityCode, couponTypeCode,CouponConstant.STATUS_DISGET_DISUSE,couponType,operator);
				 }else{
					 couponTaskService.createBatchCoupon(createNum, activityCode, couponTypeCode,CouponConstant.STATUS_DISGET_DISUSE,couponType,operator,prizeCode);
				 }
				 //修改优惠券未发放统计数目
				 lvCouponTypeService.updateNoGrantNumber(lvCouponType.getNoGrantNumber(), createNum, lvCouponType.getId());
			 }
		 }
	}

	
	/**
	 * 
	 * @Method: findLimtTimeByCode 
	 * @Description:  [根据活动code查询限时活动详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-25 下午3:37:16]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-25 下午3:37:16]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public LvActivityLimitTime findLimtTimeByCode(Dto dto)throws ServiceException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvActivityServiceImpl.findLimtTimeByCode() method begin*****");
		}
		String activityCode=(String) dto.get("activityCode");
		return (LvActivityLimitTime) dao.findUniqueByProperty(LvActivityLimitTime.class, "activityCode", activityCode);
	}
	
	/**
	 * 
	 * @Method: findLimtNumByCode 
	 * @Description:  [根据活动code查询限量活动详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-25 下午3:37:40]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-25 下午3:37:40]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public LvActivityLimited findLimtNumByCode(Dto dto)throws ServiceException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvActivityServiceImpl.findLimtNumByCode() method begin*****");
		}
		String activityCode=(String) dto.get("activityCode");
		return (LvActivityLimited) dao.findUniqueByProperty(LvActivityLimited.class, "activityCode", activityCode);
	}
	
	/**
	 * 
	 * @Method: findLimtOrderByCode 
	 * @Description:  [根据活动code查询订单满就送活动详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-25 下午3:37:44]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-25 下午3:37:44]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public LvActivityLimitOrder findLimtOrderByCode(Dto dto)throws ServiceException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvActivityServiceImpl.findLimtOrderByCode() method begin*****");
		}
		String activityCode=(String) dto.get("activityCode");
		return (LvActivityLimitOrder) dao.findUniqueByProperty(LvActivityLimitOrder.class, "activityCode", activityCode);
	}
	
	/**
	 * 
	 * @Method: findLinkByCode 
	 * @Description:  [根据活动code查询点击连接就送活动详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-25 下午3:37:49]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-25 下午3:37:49]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public LvActivityLink findLinkByCode(Dto dto)throws ServiceException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvActivityServiceImpl.findLinkByCode() method begin*****");
		}
		String activityCode=(String) dto.get("activityCode");
		return (LvActivityLink) dao.findUniqueByProperty(LvActivityLink.class, "activityCode", activityCode);
	}
	
	/**
	 * 
	 * @Method: findACRegisterByCode 
	 * @Description:  [根据活动code查询注册赠送活动详情]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2014-8-13 上午11:54:43]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2014-8-13 上午11:54:43]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public LvActivityRegister findACRegisterByCode(Dto dto)throws ServiceException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvActivityServiceImpl.findACRegisterByCode() method begin*****");
		}
		String activityCode=(String) dto.get("activityCode");
		LvActivityRegister lvActivityRegister=(LvActivityRegister) dao.findUniqueByProperty(LvActivityRegister.class, "activityCode", activityCode);
		return lvActivityRegister;
	}
	
	/**
	 * 
	 * @Method: findACShareByCode 
	 * @Description:  [根据活动code查询分享赠送活动详情]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2014-8-13 上午11:55:13]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2014-8-13 上午11:55:13]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public LvActivityShare findACShareByCode(Dto dto)throws ServiceException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvActivityServiceImpl.findACRegisterByCode() method begin*****");
		}
		String activityCode=(String) dto.get("activityCode");
		return (LvActivityShare) dao.findUniqueByProperty(LvActivityShare.class, "activityCode", activityCode);
	}
	
	/**
	 * 
	 * @Method: findACLotteryByCode 
	 * @Description:  [根据活动code查询抽奖活动详情]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2014-8-13 上午11:55:36]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2014-8-13 上午11:55:36]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public LvActivityLottery findACLotteryByCode(Dto dto)throws ServiceException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvActivityServiceImpl.findACRegisterByCode() method begin*****");
		}
		String activityCode=(String) dto.get("activityCode");
		return (LvActivityLottery) dao.findUniqueByProperty(LvActivityLottery.class, "activityCode", activityCode);
	}
	
	
	/**
	 * 
	 * @Method: findACPointProductByCode 
	 * @Description:  [根据活动code查询购买指定商品就送活动详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-7 下午5:43:04]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-7 下午5:43:04]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return LvActivityAppointProduct
	 */
	public LvActivityAppointProduct findACPointProductByCode(Dto dto)throws ServiceException{
		String activityCode=(String) dto.get("activityCode");
		return (LvActivityAppointProduct) dao.findUniqueByProperty(LvActivityAppointProduct.class, "activityCode", activityCode);
	}
	
	/**
	 * 
	 * @Method: findACMacByCode 
	 * @Description:  [根据活动code查询输入mac就送活动详情]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015年7月10日 下午5:48:35]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015年7月10日 下午5:48:35]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return LvActivityMac
	 */
	public LvActivityMac findACMacByCode(Dto dto)throws ServiceException{
		String activityCode=(String) dto.get("activityCode");
		return (LvActivityMac) dao.findUniqueByProperty(LvActivityMac.class, "activityCode", activityCode);
	}	
	
	/**
	 * 
	 * @Method: isExistActivity 
	 * @Description:  [判断当前产品是否存在开启的活动]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-9-5 下午01:54:56]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-9-5 下午01:54:56]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public Boolean isExistActivityLimitTime(Dto dto)throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvActivityServiceImpl.isExistActivityLimitTime() method begin*****");
		}
		String productCode=(String) dto.get("productCode");
		if(ObjectUtils.isNotEmpty(productCode)){
			Map<String ,Object> params=new HashMap<String ,Object>();
			String hql="from LvActivityLimitTime where productCode=:productCode and activityCode in(select code from LvActivity where status=1)";
			params.put("productCode", productCode);
			Integer num= dao.countQueryResult(Finder.create(hql), params);
			if (num>0) {
				return true;
			}
		}
	    return false;	
	}
	
	/**
	 * 
	 * @Method: isExistActivityLimitNum 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-25 下午3:48:48]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-25 下午3:48:48]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return Boolean
	 */
	public Boolean isExistActivityLimitNum(Dto dto)throws ServiceException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvActivityServiceImpl.isExistActivityLimitNum() method begin*****");
		}
		String productCode=(String) dto.get("productCode");
		if(ObjectUtils.isNotEmpty(productCode)){
			Map<String ,Object> params=new HashMap<String ,Object>();
			String hql="from LvActivityLimited where productCode=:productCode and activityCode in(select code from LvActivity where status=1)";
			params.put("productCode", productCode);
			Integer num= dao.countQueryResult(Finder.create(hql), params);
			if (num>0) {
				return true;
			}
		}
	    return false;	
	}
	
	/**
	 * 
	 * @Method: isEffectActivityOrder 
	 * @Description:  [验证订单活动是否有效]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-7-3 下午4:19:18]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-7-3 下午4:19:18]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return Boolean
	 */
	public Boolean isEffectActivityLimitOrder(Dto dto)throws ServiceException{
		//验证活动是否启用
		String activityCode=(String) dto.get("activityCode");
		String givenProductCode=(String) dto.get("givenProductCode");
		Date nowDate=new Date();
		String hql="SELECT o.id FROM LvActivity o,LvActivityLimitOrder t " +
				   " WHERE o.code=t.activityCode " +
				   " AND o.typeKey=:typeKey " +
				   " AND o.status=:status " +
				   " AND o.startTime<=:startTime " +
				   " AND o.endTime>=:endTime " +
				   " AND t.uncollectedTotal>0";
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("typeKey", ActivityConstant.TYPE_LIMIT_ORDER);
		param.put("status",ActivityConstant.STATUS_USE);
		param.put("startTime",nowDate );
		param.put("endTime",nowDate );
		if(ObjectUtils.isNotEmpty(givenProductCode)){
			hql+=" and t.givenProductCode=:givenProductCode";
			param.put("givenProductCode",givenProductCode );
		}
		if(ObjectUtils.isNotEmpty(activityCode)){
			hql+= " AND o.code=:code " ;
			param.put("code", activityCode);
		}
		
		List list= dao.find(hql, param);
		if(list!=null&&list.size()>0){
			return true;
		}
		return false;
	}
	
	public Boolean isEffectLotteryForLimitOrder(Dto dto)throws ServiceException{
		//验证活动是否启用
		String givenProductCode=(String) dto.get("givenProductCode");
		Date nowDate=new Date();
		String hql="SELECT o.id FROM LvActivity o,LvActivityLimitOrder t " +
				   " WHERE o.code=t.activityCode " +
				   " AND o.typeKey=:typeKey " +
				   " AND o.status=:status " +
				   " AND o.startTime<=:startTime " +
				   " AND o.endTime>=:endTime " +
				   " AND t.givenTypeNum=:givenTypeNum";
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("typeKey", ActivityConstant.TYPE_LIMIT_ORDER);
		param.put("status",ActivityConstant.STATUS_USE);
		param.put("startTime",nowDate );
		param.put("endTime",nowDate );
		param.put("givenTypeNum", ActivityConstant.GIVEN_TYPE_NUM_LOTTORY);
		if(ObjectUtils.isNotEmpty(givenProductCode)){
			hql+=" and t.givenProductCode=:givenProductCode";
			param.put("givenProductCode",givenProductCode );
		}
		List list= dao.find(hql, param);
		if(list!=null&&list.size()>0){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @Method: isEffectActivityLink 
	 * @Description:  [验证连接活动是否有效]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-7-3 下午4:19:30]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-7-3 下午4:19:30]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return Boolean
	 */
	public Boolean isEffectActivityLink(Dto dto)throws ServiceException{
		String activityCode=(String) dto.get("activityCode");
		String givenProductCode=(String) dto.get("givenProductCode");
		Date nowDate=new Date();
		String hql="SELECT o.id FROM LvActivity o,LvActivityLink t " +
				   " WHERE o.code=t.activityCode " +
				   " AND o.typeKey=:typeKey " +
				   " AND o.status=:status " +
				   " AND o.startTime<=:startTime " +
				   " AND o.endTime>=:endTime " +
				   " AND t.uncollectedTotal>0";
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("typeKey", ActivityConstant.TYPE_LINK);
		param.put("status",ActivityConstant.STATUS_USE);
		param.put("startTime",nowDate );
		param.put("endTime",nowDate );
		if(ObjectUtils.isNotEmpty(givenProductCode)){
			hql+=" and t.givenProductCode=:givenProductCode";
			param.put("givenProductCode",givenProductCode );
		}
		if(ObjectUtils.isNotEmpty(activityCode)){
			hql+= " AND o.code=:code " ;
			param.put("code", activityCode);
		}
		
		List list= dao.find(hql, param);
		if(list!=null&&list.size()>0){
			return true;
		}
		return false;
	}
	
	public Boolean isEffectActivityRegister(Dto dto)throws ServiceException{
		//验证活动是否启用
		String givenProductCode = (String) dto.get("givenProductCode");
		Date nowDate = new Date();
		String hql = "SELECT o.id FROM LvActivity o,LvActivityRegister t "
				+ " WHERE o.code=t.activityCode " 
				+ " AND o.typeKey=:typeKey "
				+ " AND o.status=:status " 
				+ " AND o.startTime<=:startTime "
				+ " AND o.endTime>=:endTime " 
				+ " AND t.givenTypeNum=:givenTypeNum";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("typeKey", ActivityConstant.TYPE_REGISTER);
		param.put("status", ActivityConstant.STATUS_USE);
		param.put("startTime", nowDate);
		param.put("endTime", nowDate);
		param.put("givenTypeNum", ActivityConstant.GIVEN_TYPE_NUM_LOTTORY);
		if (ObjectUtils.isNotEmpty(givenProductCode)) {
			hql += " and t.givenProductCode=:givenProductCode";
			param.put("givenProductCode", givenProductCode);
		}

		List list = dao.find(hql, param);
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}
	
	public Boolean isEffectActivityShare(Dto dto)throws ServiceException{
		//验证活动是否启用
		String givenProductCode = (String) dto.get("givenProductCode");
		Date nowDate = new Date();
		String hql = "SELECT o.id FROM LvActivity o,LvActivityShare t "
				+ " WHERE o.code=t.activityCode " 
				+ " AND o.typeKey=:typeKey "
				+ " AND o.status=:status " 
				+ " AND o.startTime<=:startTime "
				+ " AND o.endTime>=:endTime " 
				+ " AND t.givenTypeNum=:givenTypeNum";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("typeKey", ActivityConstant.TYPE_SHARE);
		param.put("status", ActivityConstant.STATUS_USE);
		param.put("startTime", nowDate);
		param.put("endTime", nowDate);
		param.put("givenTypeNum", ActivityConstant.GIVEN_TYPE_NUM_LOTTORY);
		if (ObjectUtils.isNotEmpty(givenProductCode)) {
			hql += " and t.givenProductCode=:givenProductCode";
			param.put("givenProductCode", givenProductCode);
		}

		List list = dao.find(hql, param);
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @Method: isEffectActivityLimitTime 
	 * @Description:  [验证限时活动是否有效]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-7-3 下午4:20:19]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-7-3 下午4:20:19]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return Boolean
	 */
	public Boolean isEffectActivityLimitTime(Dto dto)throws ServiceException{
		return false;
	}
	
	/**
	 * 
	 * @Method: isEffectActivityLink 
	 * @Description:  [验证限量活动是否有效]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-7-3 下午4:20:19]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-7-3 下午4:20:19]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return Boolean
	 */
	public Boolean isEffectActivityLimited(Dto dto)throws ServiceException{
		return false;
	}
	
	/**
	 * 
	 * @Method: isEffectActivityAppointProduct 
	 * @Description:  [验证是否包含有效的抽取活动的购买指定商品就送信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-22 下午5:30:47]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-22 下午5:30:47]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return Boolean
	 */
	public Boolean isEffectActivityAppointProduct(Dto dto)throws ServiceException{
		//验证活动是否启用
		String givenProductCode = (String) dto.get("givenProductCode");
		Date nowDate = new Date();
		String hql = "SELECT o.id FROM LvActivity o,LvActivityAppointProduct t "
				   + " WHERE o.code=t.activityCode " 
				   + " AND o.typeKey=:typeKey "
				   + " AND o.status=:status " 
				   + " AND o.startTime<=:startTime "
				   + " AND o.endTime>=:endTime "
				   + " AND t.givenTypeNum=:givenTypeNum";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("typeKey", ActivityConstant.TYPE_APPOINT_PRODUCT);
		param.put("status", ActivityConstant.STATUS_USE);
		param.put("startTime", nowDate);
		param.put("endTime", nowDate);
		param.put("givenTypeNum", ActivityConstant.GIVEN_TYPE_NUM_LOTTORY);
		if (ObjectUtils.isNotEmpty(givenProductCode)) {
			hql += " and t.givenProductCode=:givenProductCode";
			param.put("givenProductCode", givenProductCode);
		}

		List list = dao.find(hql, param);
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}
		
}
