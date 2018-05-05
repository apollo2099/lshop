/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvCouponType.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BasePo;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.activity.constant.ActivityConstant;
import com.lshop.common.coupon.constant.CouponConstant;
import com.lshop.common.pojo.logic.LvActivity;
import com.lshop.common.pojo.logic.LvCoupon;
import com.lshop.common.pojo.logic.LvCouponProduct;
import com.lshop.common.pojo.logic.LvCouponType;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.Constants;
import com.lshop.common.util.DateUtils;
import com.lshop.manage.lvActivity.service.LvActivityService;
import com.lshop.manage.lvCoupon.service.LvCouponService;
import com.lshop.manage.lvCouponType.service.LvCouponTypeService;
import com.lshop.ws.server.creatent.order.bean.Result;
import com.lshop.ws.server.popularize.order.bean.PCouponDetailDto;
import com.lshop.ws.server.popularize.order.bean.PCouponDtoPageResposne;
import com.lshop.ws.server.popularize.order.bean.PCouponDtoResposne;
import com.lshop.ws.server.popularize.order.bean.PCouponMainDto;
import com.lshop.ws.server.popularize.order.bean.PCouponMainPageDto;
import com.lshop.ws.server.popularize.order.bean.PCouponProductDto;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Service("LvCouponTypeService")
public class LvCouponTypeServiceImpl extends BaseServiceImpl implements LvCouponTypeService {
	 private static final Log logger	= LogFactory.getLog(LvCouponTypeServiceImpl.class);
	 @Resource 
	 private HibernateBaseDAO dao;
	 @Resource
	 private LvActivityService lvActivityService;
	 @Resource
	 private LvCouponService lvCouponService;
	 /**
	 * 获得所有
	 */
	public List<LvCouponType> findAll(Dto dto) throws ServiceException {
		StringBuilder hql = new StringBuilder("select t from LvCouponType t where 1=1 ");
		return dao.find(hql.toString(), null);
	}
	/**
	 * 分页查询
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination findPage(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeServiceImpl.findPage() method begin*****");
		}
		SimplePage page = (SimplePage)dto.get("page");
		LvCouponType lvCouponType = (LvCouponType)dto.get("lvCouponType");
		LvActivity lvActivity =(LvActivity) dto.get("lvActivity");
		String mallSysFlag =(String) dto.get("mallSysFlag");
		Map<String ,Object> params=new HashMap<String ,Object>();
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from LvCouponType t where 1=1 ");
        if(ObjectUtils.isNotEmpty(lvCouponType)){
        	if(ObjectUtils.isNotEmpty(lvCouponType.getName())) {
	        	sql.append(" and  t.name like :name ");
	        	params.put("name", "%"+lvCouponType.getName().trim()+"%");
	        }
	        if(ObjectUtils.isNotEmpty(lvCouponType.getStatus())) {
	        	sql.append(" and  t.status = :status ");
	        	params.put("status", lvCouponType.getStatus());
			}
			if (ObjectUtils.isNotEmpty(lvCouponType.getReuse())) {
				sql.append(" and  t.reuse = :reuse ");
				params.put("reuse", lvCouponType.getReuse());
			}
	        if(ObjectUtils.isNotEmpty(lvCouponType.getStartAmount())&&ObjectUtils.isNotEmpty(lvCouponType.getEndAmount())){
	        	sql.append(" and  t.amount >= :startAmount and t.amount<=:endAmount ");
	        	params.put("startAmount", lvCouponType.getStartAmount());
	        	params.put("endAmount", lvCouponType.getEndAmount());
	        }
	        if(ObjectUtils.isNotEmpty(lvCouponType.getOverdue())){
	        	if(lvCouponType.getOverdue()==1){//当前时间大于结算时间为逾期
	        		sql.append(" and  t.endTime <= :endTime ");
	        		params.put("endTime", new Date());
	        	}else{//当前时间在指定优惠券有效期间内
	        		Date temp=new Date();
	        		sql.append(" and  t.startTime<=:startTime and t.endTime >= :endTime ");
	        		params.put("startTime", temp);
	        		params.put("endTime", temp);
	        	}
	        }
        }
        if(ObjectUtils.isNotEmpty(lvActivity)){
        	if(ObjectUtils.isNotEmpty(lvActivity.getEndTime())){
        		sql.append(" and t.endTime>=:endTime");
        		params.put("endTime", lvActivity.getEndTime());
        	}
        }
        if(ObjectUtils.isNotEmpty(mallSysFlag)){
        	sql.append(" and EXISTS (select 1 from LvCouponProduct cp where t.code=cp.couponTypeCode and cp.mallFlag=:mallFlag)");
        	params.put("mallFlag", mallSysFlag);
        }
	        	
        sql.append(" order by t.createTime desc");
		Finder finder = Finder.create(sql.toString());
		finder.setParams(params);
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeServiceImpl.findPage() method end*****");
		}
		return pag;
	}
	/**
	 * 获得单独的实体信息
	 */
	public LvCouponType get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeServiceImpl.get() method begin*****");
		}
		LvCouponType lvCouponType = (LvCouponType)dto.get("lvCouponType");
		lvCouponType = (LvCouponType)dao.load(LvCouponType.class, lvCouponType.getId());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeServiceImpl.get() method end*****");
		}
		return  lvCouponType;
	}
	/**
	 * 
	 * @Method: findByCode 
	 * @Description:  [根据优惠券code查询优惠券信息(for web)]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-23 上午10:35:31]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-23 上午10:35:31]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return LvCouponType
	 */
	public LvCouponType findByCode(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeServiceImpl.get() method begin*****");
		}
		String couponTypeCode=(String) dto.get("couponTypeCode");
		LvCouponType lvCouponType=this.findByCode(couponTypeCode);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeServiceImpl.get() method end*****");
		}
		return  lvCouponType;
	}
	
	
	/**
	 * 
	 * @Method: findByCode 
	 * @Description:  [根据优惠券code查询优惠券信息(业务层)]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-23 上午10:35:31]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-23 上午10:35:31]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param couponTypeCode 优惠券code
	 * @throws ServiceException 
	 * @return LvCouponType
	 */
	public LvCouponType findByCode(String couponTypeCode) throws ServiceException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeServiceImpl.get() method begin*****");
		}
		LvCouponType lvCouponType=null;
		if(ObjectUtils.isNotEmpty(couponTypeCode)){
			lvCouponType=(LvCouponType) dao.findUniqueByProperty(LvCouponType.class, "code", couponTypeCode);
		}
		
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeServiceImpl.get() method end*****");
		}
		return  lvCouponType;
	}
	
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		 LvCouponType lvCouponType = get(dto);
		dao.delete(  lvCouponType);
	}

	public void delList(Dto dto) throws ServiceException {

	}
	/**
	 * 保存
	 */
	public LvCouponType save(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeServiceImpl.save() method begin*****");
		}
		LvCouponType lvCouponType = (LvCouponType)dto.get("lvCouponType");
		 
		 //保存优惠券商品或者商品分类关联信息
		 List<LvCouponProduct> list= lvCouponType.getCouponProductlist();
		 if(list!=null&&list.size()>0){
			 for (LvCouponProduct lvCouponProduct : list) {
				 if(ObjectUtils.isNotEmpty(lvCouponProduct)){
					 LvCouponProduct couponProduct=new LvCouponProduct();
					 couponProduct.setCouponTypeCode(lvCouponType.getCode());
					 couponProduct.setRelationCode(lvCouponProduct.getRelationCode());
					 couponProduct.setCode(CodeUtils.getCode());
					 couponProduct.setCreateTime(new Date());
					 couponProduct.setStoreId(lvCouponProduct.getStoreId());
					 couponProduct.setMallFlag(Constants.STORE_TO_MALL_SYSTEM.get(lvCouponProduct.getStoreId()));//根据店铺标示获取体系标示
					 dao.save(couponProduct);
				 }
			}
		 }
		 
		//保存优惠券信息
		dao.save( lvCouponType);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeServiceImpl.save() method end*****");
		}
		return   lvCouponType;
	}
	/**
	 * 更新
	 */
	public LvCouponType update(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeServiceImpl.update() method begin*****");
		}
		LvCouponType lvCouponType = (LvCouponType)dto.get("lvCouponType");
		String hql = "update LvCouponType set name=:name ," 
		           + " remark=:remark,"
		           + " reuseNum=:reuseNum,"
				   + " modifyUserId=:modifyUserId," 
				   + " modifyUserName=:modifyUserName,"
				   + " modifyTime=:modifyTime"
				   + " where id =:id ";
		Map param = new HashMap();
		param.put("name", lvCouponType.getName());
		param.put("remark", lvCouponType.getRemark());
		param.put("reuseNum", lvCouponType.getReuseNum());
		param.put("modifyUserId", lvCouponType.getModifyUserId());
		param.put("modifyUserName", lvCouponType.getModifyUserName());
		param.put("modifyTime", lvCouponType.getModifyTime());
		param.put("id", lvCouponType.getId());
		dao.update(hql, param);
		
		 //同步优惠券名称变更，到对应的活动赠品名字
		 if(ObjectUtils.isNotEmpty(lvCouponType.getName())&&ObjectUtils.isNotEmpty(lvCouponType.getOldName())){
			 if(!lvCouponType.getName().equals(lvCouponType.getOldName())){				 
				 lvActivityService.updateActvityGivenName("LvActivityRegister", ActivityConstant.GIVEN_TYPE_NUM_COUPON,lvCouponType.getCode(),lvCouponType.getName());
				 lvActivityService.updateActvityGivenName("LvActivityShare", ActivityConstant.GIVEN_TYPE_NUM_COUPON,lvCouponType.getCode(),lvCouponType.getName());
				 lvActivityService.updateActvityGivenName("LvActivityLimitOrder", ActivityConstant.GIVEN_TYPE_NUM_COUPON,lvCouponType.getCode(),lvCouponType.getName());
				 lvActivityService.updateActvityGivenName("LvActivityLink", ActivityConstant.GIVEN_TYPE_NUM_COUPON,lvCouponType.getCode(),lvCouponType.getName());
				 lvActivityService.updateActvityGivenName("LvActivityAppointProduct", ActivityConstant.GIVEN_TYPE_NUM_COUPON,lvCouponType.getCode(),lvCouponType.getName());
			 }
		 }
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeServiceImpl.update() method end*****");
		}
		return  lvCouponType;
	}

	/**
	 * 
	 * @Method: updateTotalNumber 
	 * @Description:  [变更优惠券总数量]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-19 下午5:15:14]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-19 下午5:15:14]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public LvCouponType updateTotalNumber(Dto dto)throws ServiceException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeServiceImpl.updateTotalNumber() method begin*****");
		}
		LvCouponType lvCouponType = (LvCouponType)dto.get("lvCouponType");
		String hql = "update LvCouponType set total=:total ," 
				   + " modifyUserId=:modifyUserId," 
				   + " modifyUserName=:modifyUserName,"
				   + " modifyTime=:modifyTime"
				   + " where id =:id ";
		Map param = new HashMap();
		param.put("total", lvCouponType.getTotal()+lvCouponType.getAddNumber());
		param.put("modifyUserId", lvCouponType.getModifyUserId());
		param.put("modifyUserName", lvCouponType.getModifyUserName());
		param.put("modifyTime", lvCouponType.getModifyTime());
		param.put("id", lvCouponType.getId());
		dao.update(hql, param);
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeServiceImpl.update() method end*****");
		}
		return  lvCouponType;
	}
	/**
	 * 
	 * @Method: updateExtendDate 
	 * @Description:  [延长优惠券有效日期]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-19 下午5:15:37]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-19 下午5:15:37]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public LvCouponType updateExtendDate(Dto dto)throws ServiceException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeServiceImpl.updateExtendDate() method begin*****");
		}
		LvCouponType lvCouponType = (LvCouponType)dto.get("lvCouponType");
		String hql = "update LvCouponType set endTime=:endTime ," 
				   + " modifyUserId=:modifyUserId," 
				   + " modifyUserName=:modifyUserName,"
				   + " modifyTime=:modifyTime"
				   + " where id =:id ";
		Map param = new HashMap();
		param.put("endTime", lvCouponType.getEndTime());
		param.put("modifyUserId", lvCouponType.getModifyUserId());
		param.put("modifyUserName", lvCouponType.getModifyUserName());
		param.put("modifyTime", lvCouponType.getModifyTime());
		param.put("id", lvCouponType.getId());
		dao.update(hql, param);
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeServiceImpl.updateExtendDate() method end*****");
		}
		return  lvCouponType;
	}
	/**
	 * 
	 * @Method: updateDisable 
	 * @Description:  [停用优惠券并且填写停用原因]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-19 下午5:16:00]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-19 下午5:16:00]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public LvCouponType updateDisable(Dto dto)throws ServiceException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeServiceImpl.updateDisable() method begin*****");
		}
		LvCouponType lvCouponType = (LvCouponType)dto.get("lvCouponType");
		String hql = "update LvCouponType set status=:status ," +
				     " disableReasons=:disableReasons,"+ 
				     " modifyUserId=:modifyUserId," +
				     " modifyUserName=:modifyUserName,"+
				     " modifyTime=:modifyTime"+
				     " where id =:id ";
		Map param = new HashMap();
		param.put("status", lvCouponType.getStatus());
		param.put("disableReasons", lvCouponType.getDisableReasons());
		param.put("modifyUserId", lvCouponType.getModifyUserId());
		param.put("modifyUserName", lvCouponType.getModifyUserName());
		param.put("modifyTime", lvCouponType.getModifyTime());
		param.put("id", lvCouponType.getId());
		dao.update(hql, param);
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeServiceImpl.updateDisable() method end*****");
		}
		return  lvCouponType;
	}
	/**
	 * 
	 * @Method: updateStatus 
	 * @Description:  [修改优惠券状态]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-19 下午5:16:36]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-19 下午5:16:36]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public LvCouponType updateStatus(Dto dto)throws ServiceException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeServiceImpl.updateStatus() method begin*****");
		}
		LvCouponType lvCouponType = (LvCouponType)dto.get("lvCouponType");
		String hql = "update LvCouponType set status=:status ," 
				   + " modifyUserId=:modifyUserId," 
				   + " modifyUserName=:modifyUserName,"
				   + " modifyTime=:modifyTime"
				   + " where id =:id ";
		Map param = new HashMap();
		param.put("status", lvCouponType.getStatus());
		param.put("modifyUserId", lvCouponType.getModifyUserId());
		param.put("modifyUserName", lvCouponType.getModifyUserName());
		param.put("modifyTime", lvCouponType.getModifyTime());
		param.put("id", lvCouponType.getId());
		dao.update(hql, param);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeServiceImpl.updateStatus() method end*****");
		}
		return  lvCouponType;
	}
	/**
	 * 
	 * @Method: updateDownload 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-20 下午4:32:05]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-20 下午4:32:05]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return LvCouponType
	 */
	public synchronized List<LvCoupon> updateDownload(Dto dto)throws ServiceException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeServiceImpl.updateStatus() method begin*****");
		}
		List<LvCoupon> list=new ArrayList<LvCoupon>();
		LvCouponType lvCouponType = (LvCouponType)dto.get("lvCouponType");
		Integer downloadNum=(Integer) dto.get("downloadNum");
		//生成对应数量优惠码信息
		for(int i=0;i<downloadNum;i++){
		    LvCoupon coupon=new LvCoupon();
			//调用生成优惠券服务，验证优惠券是否重复
			String couponCode = null;
			try {
				couponCode = lvCouponService.createCouponCode();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//组装优惠码信息
			coupon.setCouponCode(couponCode);
		    coupon.setCouponTypeCode(lvCouponType.getCode());
		    coupon.setCouponStatus(Short.parseShort("1"));
		    coupon.setGrantWay("管理员下载");
		    coupon.setGrantType(Short.parseShort("2"));
		    coupon.setOperator(lvCouponType.getModifyUserName());
		    coupon.setCreateTime(new Date());
		    coupon.setCode(CodeUtils.getCode());
		    dao.save(coupon);
		    list.add(coupon);
		}
		
		//修改优惠券类型信息总数量和已经发放数量
		String hql = "update LvCouponType set grantNumber=:grantNumber,"+ 
				     " modifyUserId=:modifyUserId,"+   
				     " modifyUserName=:modifyUserName,"+ 
				     " modifyTime=:modifyTime"+ 
				     " where id =:id ";
		Map param = new HashMap();
		if(ObjectUtils.isNotEmpty(lvCouponType.getGrantNumber())){
			param.put("grantNumber", lvCouponType.getGrantNumber()+downloadNum);
		}else{
			param.put("grantNumber", downloadNum);
		}
		param.put("modifyUserId", lvCouponType.getModifyUserId());
		param.put("modifyUserName", lvCouponType.getModifyUserName());
		param.put("modifyTime", lvCouponType.getModifyTime());
		param.put("id", lvCouponType.getId());
		dao.update(hql, param);
		
		//生成 Excel返回
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeServiceImpl.updateStatus() method end*****");
		}
		return  list;
	}
	
	public boolean updateNoGrantNumber(Integer noGentNumber,Integer createNum,Integer couponTypeId){
		// 更新优惠券信息
		String hql = " update LvCouponType set noGrantNumber=:noGrantNumber"
				   + " where id=:id ";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("noGrantNumber", noGentNumber+ createNum);
		paramMap.put("id", couponTypeId);
		dao.update(hql, paramMap);
		return true;
	}
	
	/**
	 * 
	 * @Method: exportCouponTypeList 
	 * @Description:  [导出优惠券列表信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-20 下午4:29:32]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-20 下午4:29:32]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public List exportCouponTypeList(Dto dto)throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponTypeServiceImpl.exportOrder() method begin*****");
		}
		List list = new ArrayList();
		String hql="select t from LvCouponType t where id in (" + dto.get("ids") + ")";
		List listPage = dao.find(hql, null);
		if (listPage != null) {
			String[] tempArray = null;
			LvCouponType couponType = null;
			// 增加表头
			String[] title = new String[7];
			title[0] = "优惠券名称";
			title[1] = "有效时间";
			title[2] = "优惠券金额";
			title[3] = "总数";
			title[4] = "未发放数目";
			title[5] = "已发放数目";
			title[6] = "已使用数目";
			list.add(title);
			for (int i = 0; i < listPage.size(); i++) {
				tempArray = new String[7];
				couponType = (LvCouponType) listPage.get(i);
				tempArray[0] = couponType.getName();
				tempArray[1] = DateUtils.formatDate(couponType.getStartTime(), "yyyy-MM-dd")+"至"+DateUtils.formatDate(couponType.getEndTime(), "yyyy-MM-dd");
				tempArray[2] = String.valueOf(couponType.getAmount());
				tempArray[3] = String.valueOf(couponType.getTotal());
				tempArray[4] = String.valueOf(couponType.getNoGrantNumber());
				tempArray[5] = String.valueOf(couponType.getGrantNumber());
				tempArray[6] = String.valueOf(couponType.getUsedNumber());
				list.add(tempArray);
			}

		}
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.exportOrder() method end*****");
		}
		return list;
	}

	@Override
	public PCouponDtoPageResposne findPageForTg(String couponName, String pageNum, String numPerPage) throws ServiceException {

		PCouponDtoPageResposne couponDtoPageResposne = null;

		if (StringUtils.isNotBlank(pageNum) && StringUtils.isNotBlank(numPerPage)) {

			Map<String, Object> params = new HashMap<String, Object>();
			// 组装SQL,时间类型的没组装，如果有需要自己添加SQL
			// 字符串类型为模糊查询，不区分大小写
			StringBuilder sql = new StringBuilder("select t from LvCouponType t where 1=1 ");
			if (StringUtils.isNotBlank(couponName)) {
				sql.append(" and  t.name like :name ");
				params.put("name", "%" + couponName.trim() + "%");
			}
			Date temp = new Date();
			sql.append(" and t.endTime >= :endTime ");
			params.put("endTime", temp);

			sql.append(" and t.status=:status");
			params.put("status", Integer.valueOf(CouponConstant.STATUS_USE).shortValue());

			sql.append(" order by t.createTime desc");

			Finder finder = Finder.create(sql.toString());
			finder.setParams(params);
			Pagination pag = dao.find(finder, Integer.parseInt(pageNum), Integer.parseInt(numPerPage));

			List<LvCouponType> couponTypeList = (List<LvCouponType>) pag.getList();

			couponDtoPageResposne = new PCouponDtoPageResposne();

			List<PCouponMainPageDto> couponMainPageList = new ArrayList<PCouponMainPageDto>();

			for (LvCouponType lvCouponType : couponTypeList) {
				/**
				 * 优惠券类型编码
				 */
				String code = lvCouponType.getCode();
				/**
				 * 优惠券名称
				 */
				String name = lvCouponType.getName();
				/**
				 * 优惠券金额
				 */
				double amount = lvCouponType.getAmount();
				/**
				 * 指定金额
				 */
				double limitAmount = lvCouponType.getLimitAmount();
				/**
				 * 有效起始时间
				 */
				String startTime = DateUtils.formatDate(lvCouponType.getStartTime(), "yyyy-MM-dd");
				/**
				 * 有效结束时间
				 */
				String endTime = DateUtils.formatDate(lvCouponType.getEndTime(), "yyyy-MM-dd");
				/**
				 * 描述信息
				 */
				String remark = lvCouponType.getRemark();
				/**
				 * 币种
				 */
				String currency = lvCouponType.getCurrency();
				/**
				 * 重复使用
				 */
				Integer reuse = lvCouponType.getReuse();

				List<PCouponProductDto> couponProducts = getPCouponProductDto(code, lvCouponType.getRelationType());

				PCouponMainPageDto couponMainPageDto = new PCouponMainPageDto(code, name, amount, limitAmount, startTime, endTime, remark, currency, reuse, couponProducts);

				couponMainPageList.add(couponMainPageDto);
			}

			int totalCount = pag.getTotalCount();

			couponDtoPageResposne.setCouponMainPageList(couponMainPageList);
			couponDtoPageResposne.setTotalCount(totalCount);

		}

		return couponDtoPageResposne;
	}

	@Override
	public PCouponDtoResposne getCoupon(String couponTypeCode, int couponQuantity) throws ServiceException {
		PCouponDtoResposne couponDtoResposne = new PCouponDtoResposne();
		com.lshop.ws.server.popularize.order.bean.Result result = couponDtoResposne.getResult();
		LvCouponType couponType = null;

		Map<String, Object> cparams = new HashMap<String, Object>();
		
		StringBuilder chql = new StringBuilder("select o from LvCouponType o where o.code=:code");
		cparams.put("code", couponTypeCode);
		
		chql.append(" and o.status=:status");
		cparams.put("status", Integer.valueOf(CouponConstant.STATUS_USE).shortValue());
		
		Date nowDate = new Date();
		chql.append(" and o.endTime >= :endTime ");
		cparams.put("endTime", nowDate);
		
		
		List<LvCouponType> couponTypes = (List<LvCouponType>) dao.find(chql.toString(), cparams);
		if (CollectionUtils.isNotEmpty(couponTypes) && couponTypes.size() == 1 && null != couponTypes.get(0)) {
			couponType = couponTypes.get(0);
		}

		if (null != couponType) {
			Integer surplusNum = couponType.getTotal() - (couponType.getGrantNumber() == null ? 0 : couponType.getGrantNumber())
					- (couponType.getNoGrantNumber() == null ? 0 : couponType.getNoGrantNumber()) - (couponType.getUsedNumber() == null ? 0 : couponType.getUsedNumber());// 剩余可分配数量
			if (couponQuantity <= surplusNum) {
				// 生成优惠券
				List<BasePo> list = new ArrayList<BasePo>();

				List<PCouponDetailDto> couponDetails = new ArrayList<PCouponDetailDto>();

				// 生成对应数量优惠码信息
				for (int i = 0; i < couponQuantity; i++) {
					LvCoupon coupon = new LvCoupon();

					String couponCode = lvCouponService.createCouponCode();
					// 组装优惠码信息
					coupon.setCouponCode(couponCode);
					coupon.setCouponTypeCode(couponTypeCode);
					coupon.setCouponStatus(Short.parseShort("1"));
					coupon.setGrantWay("推广获取优惠券");
					coupon.setGrantType(Short.parseShort("1"));
					coupon.setOperator("推广联盟");
					coupon.setCreateTime(new Date());
					coupon.setCode(CodeUtils.getCode());
					list.add(coupon);

					PCouponDetailDto couponDetailDto = new PCouponDetailDto();
					couponDetailDto.setCouponCode(couponCode);

					couponDetails.add(couponDetailDto);
				}

				dao.saveOrUpdate(list);

				// 修改优惠券类型信息总数量和已经发放数量
				String hql = "update LvCouponType set grantNumber=:grantNumber,modifyUserId=:modifyUserId,modifyUserName=:modifyUserName,modifyTime=:modifyTime"
						+ " where code =:code ";

				Map<String, Object> params = new HashMap<String, Object>();

				if (ObjectUtils.isNotEmpty(couponType.getGrantNumber())) {
					params.put("grantNumber", (couponType.getGrantNumber() == null ? 0 : couponType.getGrantNumber()) + couponQuantity);
				} else {
					params.put("grantNumber", couponQuantity);
				}
				params.put("modifyUserId", couponType.getModifyUserId());
				params.put("modifyUserName", couponType.getModifyUserName());
				params.put("modifyTime", couponType.getModifyTime());
				params.put("code", couponType.getCode());
				dao.update(hql, params);

				// 获取优惠券商品
				List<PCouponProductDto> couponProducts = getPCouponProductDto(couponTypeCode, couponType.getRelationType());

				/**
				 * 优惠券类型编码
				 */
				String code = couponType.getCode();
				/**
				 * 优惠券名称
				 */
				String name = couponType.getName();
				/**
				 * 优惠券金额
				 */
				double amount = couponType.getAmount();
				/**
				 * 指定金额
				 */
				double limitAmount = couponType.getLimitAmount();
				/**
				 * 有效起始时间
				 */
				String startTime = DateUtils.formatDate(couponType.getStartTime(), "yyyy-MM-dd");
				/**
				 * 有效结束时间
				 */
				String endTime = DateUtils.formatDate(couponType.getEndTime(), "yyyy-MM-dd");
				/**
				 * 描述信息
				 */
				String remark = couponType.getRemark();
				/**
				 * 币种
				 */
				String currency = couponType.getCurrency();
				/**
				 * 重复使用
				 */
				Integer reuse = couponType.getReuse();

				PCouponMainDto couponMainDto = new PCouponMainDto(code, name, amount, limitAmount, startTime, endTime, remark, currency, reuse, couponDetails, couponProducts);

				couponDtoResposne.setCouponMain(couponMainDto);
				result.setStatus(Result.STATUS_SUCCEED);
				result.setMessage("获取优惠成功");
			} else {
				result.setStatus(Result.STATUS_FAIL);
				result.setMessage("优惠券“" + couponType.getName() + "”,获取优惠券数量：" + couponQuantity + "，可分配数量：" + surplusNum);

				logger.error("优惠券：" + couponType.getName() + "获取优惠券数量：" + couponQuantity + " 可分配数量：" + surplusNum);
			}
		} else {
			result.setStatus(Result.STATUS_FAIL);
			result.setMessage("非有效优惠券");
		}
		couponDtoResposne.setResult(result);
		return couponDtoResposne;
	}

	public List<PCouponProductDto> getPCouponProductDto(String couponTypeCode, Short relationType) {
		List<PCouponProductDto> list = new ArrayList<PCouponProductDto>();
		if (StringUtils.isNotBlank(couponTypeCode)) {
			Map<String, Object> couponProductParam = new HashMap<String, Object>();

			String hql = "select o from LvCouponProduct o where o.couponTypeCode =:couponTypeCode";
			couponProductParam.put("couponTypeCode", couponTypeCode);

			List<LvCouponProduct> couponProductList = dao.find(hql, couponProductParam);

			if (relationType == CouponConstant.SCOPE_TYPE) {
				// 关联类型(商品类型)
				for (LvCouponProduct couponProduct : couponProductList) {
					String productTypeCode = couponProduct.getRelationCode();// 商品分类

					String productHql = "select o from LvProduct o where o.ptype =:ptype and o.status<>:status";

					Map<String, Object> productParam = new HashMap<String, Object>();
					productParam.put("ptype", productTypeCode);
					productParam.put("status", -1);

					List<LvProduct> productList = dao.find(productHql, productParam);
					Set<String> dupSet = new HashSet<String>();
					for (LvProduct lvProduct : productList) {
						String productCode = lvProduct.getPubProductCode();// 商品顶级编码
						if (!dupSet.contains(productCode)) {
							list.add(new PCouponProductDto(productCode));
						}
					}

				}
			} else if (relationType == CouponConstant.SCOPE_PRODUCT) {
				// 关联类型(商品)
				Set<String> dupSet = new HashSet<String>();
				for (LvCouponProduct couponProduct : couponProductList) {

					String secondCode = couponProduct.getRelationCode();// 商品二级编码

					String productHql = "select o from LvProduct o where o.code =:code and o.status<>:status";

					Map<String, Object> productParam = new HashMap<String, Object>();
					productParam.put("code", secondCode);
					productParam.put("status", -1);

					LvProduct product = (LvProduct) dao.findUnique(productHql, productParam);
					if(null!=product){
						String productCode = product.getPubProductCode();// 商品顶级编码

						if (!dupSet.contains(productCode)) {
							dupSet.add(productCode);

							list.add(new PCouponProductDto(productCode));
						}
					}else{
						continue;
					}


				}
			}
		}
		return list;
	}

	@Override
	public PCouponMainDto checkCouponCode(String couponCodes) throws ServiceException {
		PCouponMainDto couponMainDto = null;

		if (StringUtils.isNotBlank(couponCodes)) {

			String[] couponCodeArray = couponCodes.split(",");

			Set<String> couponTypeCodeSet = new HashSet<String>();// 验证是否同一个优惠券类型编码

			String couponTypeCode = null;// 优惠券类型编码

			boolean checkFlag = true;

			List<PCouponDetailDto> couponDetails = new ArrayList<PCouponDetailDto>();

			List<PCouponProductDto> couponProducts = new ArrayList<PCouponProductDto>();

			for (String couponCode : couponCodeArray) {

				Map<String, Object> couponParam = new HashMap<String, Object>();

				String hql = "select o from LvCoupon o,LvCouponType t where o.couponTypeCode=t.code and o.couponCode =:couponCode and t.status=:status and t.endTime >= :endTime and o.couponStatus=:couponStatus";
				couponParam.put("couponCode", couponCode);
				couponParam.put("status", Integer.valueOf(CouponConstant.STATUS_USE).shortValue());
				Date nowDate = new Date();
				couponParam.put("endTime", nowDate);
				couponParam.put("couponStatus", Integer.valueOf(CouponConstant.STATUS_GET_DISUSE).shortValue());

				LvCoupon coupon = (LvCoupon) dao.findUnique(hql, couponParam);

				if (null != coupon) {
					couponTypeCode = coupon.getCouponTypeCode();// 优惠券类型编码
					if (couponTypeCodeSet.isEmpty()) {
						couponTypeCodeSet.add(couponTypeCode);
					} else {
						if (!couponTypeCodeSet.contains(couponTypeCode)) {
							checkFlag = false;
							logger.error("checkCouponCode fail couponCode:" + couponCode);
							break;
						}
					}
				} else {
					checkFlag = false;
					logger.error("checkCouponCode fail couponCode:" + couponCode);
					break;
				}

				PCouponDetailDto couponDetailDto = new PCouponDetailDto();
				couponDetailDto.setCouponCode(couponCode);

				couponDetails.add(couponDetailDto);
			}
			if (checkFlag) {
				// 生成PCouponMainDto

				LvCouponType couponType = (LvCouponType) dao.findUniqueByProperty(LvCouponType.class, "code", couponTypeCode);

				/**
				 * 优惠券类型编码
				 */
				String code = couponTypeCode;
				/**
				 * 优惠券名称
				 */
				String name = couponType.getName();
				/**
				 * 优惠券金额
				 */
				double amount = couponType.getAmount();
				/**
				 * 指定金额
				 */
				double limitAmount = couponType.getLimitAmount();
				/**
				 * 有效起始时间
				 */
				String startTime = DateUtils.formatDate(couponType.getStartTime(), "yyyy-MM-dd");
				/**
				 * 有效结束时间
				 */
				String endTime = DateUtils.formatDate(couponType.getEndTime(), "yyyy-MM-dd");
				/**
				 * 描述信息
				 */
				String remark = couponType.getRemark();
				/**
				 * 币种
				 */
				String currency = couponType.getCurrency();
				/**
				 * 重复使用
				 */
				Integer reuse = couponType.getReuse();

				// 获取优惠券商品
				couponProducts = getPCouponProductDto(couponTypeCode, couponType.getRelationType());

				couponMainDto = new PCouponMainDto(code, name, amount, limitAmount, startTime, endTime, remark, currency, reuse, couponDetails, couponProducts);
			}

		}

		return couponMainDto;
	}
}
