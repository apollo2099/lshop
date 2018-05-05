/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvCoupon.service.impl;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.lshop.manage.lvCoupon.service.CouponRedisServer;
import com.lshop.manage.lvCoupon.service.LvCouponService;
import com.lshop.manage.lvCouponType.service.LvCouponTypeService;
import com.lshop.common.coupon.constant.CouponConstant;
import com.lshop.common.pojo.logic.LvCoupon;
import com.lshop.common.util.DateUtils;
import com.lshop.common.util.OrderHelp;
import com.lshop.common.util.RedisClient;
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
 */@Service("LvCouponService")
public class LvCouponServiceImpl extends BaseServiceImpl implements LvCouponService {
	 private static final Log logger	= LogFactory.getLog(LvCouponServiceImpl.class);
	 @Resource 
	 private HibernateBaseDAO dao;
	 @Resource
	 private LvCouponTypeService lvCouponTypeService;
	 @Resource
	 private CouponRedisServer couponRedisServer;
	 
	 /**
	 * 获得所有
	 */
	
	public List<LvCoupon> findAll(Dto dto) throws ServiceException {
		LvCoupon lvCoupon=(LvCoupon) dto.get("lvCoupon");
		//查询优惠码信息
		Map<String ,Object> param=new HashMap<String ,Object>();
		StringBuilder sql = new StringBuilder("select t from LvCoupon t where t.couponStatus in(1,2)");
	        if(ObjectUtils.isNotEmpty(lvCoupon)){
	        	if(ObjectUtils.isNotEmpty(lvCoupon.getCouponCode())) {
		        	sql.append(" and  t.couponCode =:couponCode ");
		        	param.put("couponCode", lvCoupon.getCouponCode().trim());
		        }
	          	if(ObjectUtils.isNotEmpty(lvCoupon.getOrderId())) {
		        	sql.append(" and  t.orderId =:orderId ");
		        	param.put("orderId", lvCoupon.getOrderId());
		        }
	        	if(ObjectUtils.isNotEmpty(lvCoupon.getCouponTypeCode())) {
		        	sql.append(" and  t.couponTypeCode =:couponTypeCode ");
		        	param.put("couponTypeCode", lvCoupon.getCouponTypeCode().trim());
		        }
		        if(ObjectUtils.isNotEmpty(lvCoupon.getCouponStatus())) {
		        	sql.append(" and  t.couponStatus = :couponStatus ");
		        	param.put("couponStatus", lvCoupon.getCouponStatus());
		        }	
	        	if(ObjectUtils.isNotEmpty(lvCoupon.getGrantWay())) {
	        		if(lvCoupon.getGrantWay().equals("0000")){
	        			sql.append(" and  t.grantType=2");
	        		}else{
	        			sql.append(" and  t.grantWay =:grantWay and t.grantType=1");
			        	param.put("grantWay", lvCoupon.getGrantWay());
	        		}
		        }	
	        	if(ObjectUtils.isNotEmpty(lvCoupon.getOperator())) {
		        	sql.append(" and  t.operator like :operator ");
		        	param.put("operator", "%"+lvCoupon.getOperator().trim()+"%");
		        }
	        	if(ObjectUtils.isNotEmpty(lvCoupon.getApplyName())) {
		        	sql.append(" and  t.applyName like :applyName ");
		        	param.put("applyName", "%"+lvCoupon.getApplyName().trim()+"%");
		        }
	        	if(ObjectUtils.isNotEmpty(lvCoupon.getObtainStartTime())&&ObjectUtils.isNotEmpty(lvCoupon.getObtainEndTime())){
	        		sql.append(" and  t.obtainTime >=:obtainStartTime ");
	        		sql.append(" and  t.obtainTime <=:obtainEndTime ");
	             	param.put("obtainStartTime", DateUtils.convertToDateTime(lvCoupon.getObtainStartTime()+" 00:00:00"));
	             	param.put("obtainEndTime", DateUtils.convertToDateTime(lvCoupon.getObtainEndTime()+" 23:59:59"));
	        	}
	        }
	    List<LvCoupon> listPage=dao.find(sql.toString(), param); 
	    return listPage;
	}
	/**
	 * 分页查询
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination findPage(Dto dto) throws ServiceException {
		SimplePage page = (SimplePage)dto.get("page");
		LvCoupon lvCoupon = (LvCoupon)dto.get("lvCoupon");
		Map<String ,Object> param=new HashMap<String ,Object>();
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("");
        
        if(StringUtils.isNotBlank(lvCoupon.getOrderId())||StringUtils.isNotBlank(lvCoupon.getApplyName())){
        	sql.append("select t from LvCoupon t,LvCouponApply ca where t.code=ca.couponCode and t.couponStatus in(1,2)");
        }else if(StringUtils.isNotBlank(lvCoupon.getObtainStartTime())||StringUtils.isNotBlank(lvCoupon.getObtainEndTime())){
        	sql.append("select t from LvCoupon t,LvCouponObtain co where t.code=co.couponCode and t.couponStatus in(1,2)");
        }else if((StringUtils.isNotBlank(lvCoupon.getOrderId())||StringUtils.isNotBlank(lvCoupon.getApplyName()))&&(StringUtils.isNotBlank(lvCoupon.getObtainStartTime())||StringUtils.isNotBlank(lvCoupon.getObtainEndTime()))){
        	sql.append("select t from LvCoupon t,LvCouponApply ca,LvCouponObtain co where t.code=ca.couponCode and t.code=co.couponCode and t.couponStatus in(1,2)");
        }else{
        	sql.append("select t from LvCoupon t where t.couponStatus in(1,2)");
        }
        
        if(ObjectUtils.isNotEmpty(lvCoupon)){
        	if(StringUtils.isNotBlank(lvCoupon.getCouponCode())) {
	        	sql.append(" and  t.couponCode =:couponCode ");
	        	param.put("couponCode", lvCoupon.getCouponCode().trim());
	        }
          	if(StringUtils.isNotBlank(lvCoupon.getOrderId())) {
	        	sql.append(" and  ca.orderId like:orderId ");
	        	param.put("orderId", "%"+lvCoupon.getOrderId()+"%");
	        }
        	if(StringUtils.isNotBlank(lvCoupon.getCouponTypeCode())) {
	        	sql.append(" and  t.couponTypeCode =:couponTypeCode ");
	        	param.put("couponTypeCode", lvCoupon.getCouponTypeCode().trim());
	        }
	        if(ObjectUtils.isNotEmpty(lvCoupon.getCouponStatus())) {
	        	sql.append(" and  t.couponStatus = :couponStatus ");
	        	param.put("couponStatus", lvCoupon.getCouponStatus());
	        }	
        	if(StringUtils.isNotBlank(lvCoupon.getOperator())) {
	        	sql.append(" and  t.operator like :operator ");
	        	param.put("operator", "%"+lvCoupon.getOperator().trim()+"%");
	        }
        	if(StringUtils.isNotBlank(lvCoupon.getApplyName())) {
	        	sql.append(" and  ca.applyName like :applyName ");
	        	param.put("applyName", "%"+lvCoupon.getApplyName().trim()+"%");
	        }
        	if(StringUtils.isNotBlank(lvCoupon.getObtainStartTime())&&StringUtils.isNotBlank(lvCoupon.getObtainEndTime())){
        		sql.append(" and  co.obtainTime >=:obtainStartTime ");
        		sql.append(" and  co.obtainTime <=:obtainEndTime ");
             	param.put("obtainStartTime", DateUtils.convertToDateTime(lvCoupon.getObtainStartTime()+" 00:00:00"));
             	param.put("obtainEndTime", DateUtils.convertToDateTime(lvCoupon.getObtainEndTime()+" 23:59:59"));
        	}
        	if(StringUtils.isNotBlank(lvCoupon.getObtainStartTime())&&StringUtils.isBlank(lvCoupon.getObtainEndTime())){
        		sql.append(" and  co.obtainTime >=:obtainStartTime ");
             	param.put("obtainStartTime", DateUtils.convertToDateTime(lvCoupon.getObtainStartTime()+" 00:00:00"));
        	}
        	if(StringUtils.isBlank(lvCoupon.getObtainStartTime())&&StringUtils.isNotBlank(lvCoupon.getObtainEndTime())){
        		sql.append(" and  co.obtainTime <=:obtainEndTime ");
             	param.put("obtainEndTime", DateUtils.convertToDateTime(lvCoupon.getObtainEndTime()+" 23:59:59"));
        	}
        	if(StringUtils.isNotBlank(lvCoupon.getGrantWay())) {
        		if(lvCoupon.getGrantWay().equals("0000")){
        			sql.append(" and  t.grantType=2");
        		}else if(lvCoupon.getGrantWay().equals("0001")){
        			sql.append(" and  t.grantWay='推广获取优惠券'");
        		}else{
        			sql.append(" and  t.grantWay like :grantWay and t.grantType=1");
		        	param.put("grantWay", lvCoupon.getGrantWay());
        		}
	        }	
        }
        if(ObjectUtils.isNotEmpty(dto.getAsString("orderField"))) {
            sql.append(" order by t." + dto.getAsString("orderField") + " " + dto.getAsString("orderDirection"));
        } 
		Finder finder = Finder.create(sql.toString());
		finder.setParams(param);
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		return pag;
	}
	/**
	 * 获得单独的实体信息
	 */
	public LvCoupon get(Dto dto) throws ServiceException {
		 LvCoupon lvCoupon = (LvCoupon)dto.get("lvCoupon");
		 lvCoupon = (LvCoupon)dao.load(LvCoupon.class, lvCoupon.getId());
		return  lvCoupon;
	}
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		 LvCoupon lvCoupon = get(dto);
		dao.delete(  lvCoupon);
	}

	public void delList(Dto dto) throws ServiceException {

	}
	/**
	 * 保存
	 */
	public LvCoupon save(Dto dto) throws ServiceException {
		 LvCoupon lvCoupon = (LvCoupon)dto.get("lvCoupon");
		dao.save( lvCoupon);
		return   lvCoupon;
	}
	/**
	 * 更新
	 */
	public LvCoupon update(Dto dto) throws ServiceException {
		 LvCoupon lvCoupon = (LvCoupon)dto.get("lvCoupon");
		dao.update(lvCoupon);
		return  lvCoupon;
	}
	
	
	public LvCoupon findByCouponCode(String couponCode)throws ServiceException {
		LvCoupon coupon=null;
		String hql="select t from LvCoupon t where t.couponCode=:couponCode";
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("couponCode", couponCode);
		List<LvCoupon> list= dao.find(hql, param);
		if(list!=null&&list.size()>0){
			coupon=list.get(0);
		}
		return coupon;
	}
	
	
	//设置redis缓存客户端调用对象
	private Jedis jedis = RedisClient.getRedis();
	public synchronized String createCouponCode(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvCouponServiceImpl.createCouponCode() method begin*****");
		}
		String coupon=null;
		//验证优惠码是否存在
		try {
			while (true) {
					//随机生成优惠码
					coupon=OrderHelp.createNumberCouponCode();
					//获取优惠码缓存
					String couponKey=CouponConstant.getCouponKey(coupon);
					Set set=jedis.smembers(couponKey);
					if(ObjectUtils.isNotEmpty(set)){
						Iterator i = set.iterator();// 先迭代出来
						while (i.hasNext()) {// 遍历
							String couponJedis=String.valueOf(i.next());
							//判断缓存是否存在优惠码数据
							if(ObjectUtils.isEmpty(couponJedis)){
								//设置优惠码缓存
								couponRedisServer.cacheCouponCode(couponKey, coupon);
								break;
							}
						}
					}else{
						break;
					}
			}
		} catch (Exception e) {
			logger.error("生成优惠码出错:"+coupon+",错误信息："+e.getMessage());
		}
		if(logger.isInfoEnabled()){
			  logger.info("***** 生成优惠码为："+coupon);
			  logger.info("***** LvCouponServiceImpl.createCouponCode() method end*****");
		}
		return coupon;
	}
	
	/**
	 * 
	 * @Method: findPageForObtain 
	 * @Description:  [根据优惠码查询获取人信息]  
	 * @Author:       [chenyj]     
	 * @CreateDate:   [2015-2-9 下午6:05:34]   
	 * @UpdateUser:   [chenyj]     
	 * @UpdateDate:   [2015-2-9 下午6:05:34]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Pagination findPageForObtain(Dto dto) throws ServiceException {
		SimplePage page = (SimplePage)dto.get("page");
		String couponCode = dto.getAsString("couponCode");
		Map<String ,Object> param=new HashMap<String ,Object>();
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select o from LvCouponObtain o where o.couponCode =:couponCode");
        param.put("couponCode", couponCode);
        
        sql.append(" order by o.obtainTime DESC");
        
		Finder finder = Finder.create(sql.toString());
		finder.setParams(param);
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		return pag;
	}
	
	
	/**
	 * 
	 * @Method: findPageForApply 
	 * @Description:  [根据优惠码查询领券人信息]  
	 * @Author:       [chenyj]     
	 * @CreateDate:   [2015-2-9 下午6:05:54]   
	 * @UpdateUser:   [chenyj]     
	 * @UpdateDate:   [2015-2-9 下午6:05:54]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Pagination findPageForApply(Dto dto) throws ServiceException {
		SimplePage page = (SimplePage)dto.get("page");
		String couponCode = dto.getAsString("couponCode");
		Map<String ,Object> param=new HashMap<String ,Object>();
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select o from LvCouponApply o where o.couponCode =:couponCode");
        param.put("couponCode", couponCode);
        
        sql.append(" order by o.applyTime DESC");
        
		Finder finder = Finder.create(sql.toString());
		finder.setParams(param);
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		return pag;
	}
}
