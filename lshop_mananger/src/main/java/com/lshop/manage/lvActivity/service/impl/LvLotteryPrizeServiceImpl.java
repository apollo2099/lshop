/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvActivity.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.lshop.common.activity.constant.ActivityConstant;
import com.lshop.common.coupon.constant.CouponConstant;
import com.lshop.common.pojo.logic.LvCouponType;
import com.lshop.common.pojo.logic.LvLotteryPrize;
import com.lshop.manage.lvActivity.service.LvActivityService;
import com.lshop.manage.lvActivity.service.LvLotteryPrizeService;
import com.lshop.manage.lvCoupon.service.CouponTaskService;
import com.lshop.manage.lvCouponType.service.LvCouponTypeService;
import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.BeanUtils;
import com.gv.core.util.ObjectUtils;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Service("LvLotteryPrizeService")
public class LvLotteryPrizeServiceImpl extends BaseServiceImpl implements LvLotteryPrizeService {
	private static final Log logger	= LogFactory.getLog(LvLotteryPrizeServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	@Resource 
	private LvCouponTypeService lvCouponTypeService;
	@Resource
	private LvActivityService lvActivityService;
	@Resource
	private CouponTaskService couponTaskService;
	
	/**
	 * 获得所有
	 */
	public List<LvLotteryPrize> findAll(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvLotteryPrizeServiceImpl.get() method begin*****");
		}
		String activityCode=(String) dto.get("activityCode");
		Short prizeType=(Short) dto.get("prizeType");
		StringBuilder hql = new StringBuilder("select t from LvLotteryPrize t where activityCode=:activityCode and prizeType=:prizeType and isDraw=:isDraw");
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("activityCode", activityCode);
		param.put("prizeType", prizeType);
		param.put("isDraw", ActivityConstant.PRIZE_IS_DRAW_YES);
		List<LvLotteryPrize> list=dao.find(hql.toString(), param);
		
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvLotteryPrizeServiceImpl.get() method end*****");
		}
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
			logger.info("***** LvLotteryPrizeServiceImpl.findPage() method begin*****");
		}
		SimplePage page = (SimplePage)dto.get("page");
		 LvLotteryPrize lvLotteryPrize = (LvLotteryPrize)dto.get("lvLotteryPrize");
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from LvLotteryPrize t where 1=1 ");
		        if(ObjectUtils.isNotEmpty(lvLotteryPrize.getId())) {
		        	sql.append(" and  t.id = :id ");
		        }	
	        	if(ObjectUtils.isNotEmpty(lvLotteryPrize.getActivityCode())) {
		        	sql.append(" and  t.activityCode like :activityCode ");
		        }
	        	if(ObjectUtils.isNotEmpty(lvLotteryPrize.getPrizeName())) {
		        	sql.append(" and  t.prizeName like :prizeName ");
		        }
	        	if(ObjectUtils.isNotEmpty(lvLotteryPrize.getPrizeCode())) {
		        	sql.append(" and  t.prizeCode like :prizeCode ");
		        }
		        if(ObjectUtils.isNotEmpty(lvLotteryPrize.getPrizeType())) {
		        	sql.append(" and  t.prizeType = :prizeType ");
		        }	
	        	if(ObjectUtils.isNotEmpty(lvLotteryPrize.getPrizeImages())) {
		        	sql.append(" and  t.prizeImages like :prizeImages ");
		        }
		        if(ObjectUtils.isNotEmpty(lvLotteryPrize.getPrizeTotal())) {
		        	sql.append(" and  t.prizeTotal = :prizeTotal ");
		        }	
		        if(ObjectUtils.isNotEmpty(lvLotteryPrize.getGrantTotal())) {
		        	sql.append(" and  t.grantTotal = :grantTotal ");
		        }	
		        if(ObjectUtils.isNotEmpty(lvLotteryPrize.getIsDraw())) {
		        	sql.append(" and  t.isDraw = :isDraw ");
		        }	
	        	if(ObjectUtils.isNotEmpty(lvLotteryPrize.getCode())) {
		        	sql.append(" and  t.code like :code ");
		        }
		        if(ObjectUtils.isNotEmpty(lvLotteryPrize.getModifyUserId())) {
		        	sql.append(" and  t.modifyUserId = :modifyUserId ");
		        }	
	        	if(ObjectUtils.isNotEmpty(lvLotteryPrize.getModifyUserName())) {
		        	sql.append(" and  t.modifyUserName like :modifyUserName ");
		        }
        Map parms = BeanUtils.describeForHQL(lvLotteryPrize);
        sql.append(" order by t.sortId desc");
		Finder finder = Finder.create(sql.toString());
		finder.setParams(parms);
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvLotteryPrizeServiceImpl.findPage() method end*****");
		}
		return pag;
	}
	/**
	 * 获得单独的实体信息
	 */
	public LvLotteryPrize get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()) {
			logger.info("***** LvLotteryPrizeServiceImpl.get() method begin*****");
		}
		LvLotteryPrize lvLotteryPrize = (LvLotteryPrize) dto.get("lvLotteryPrize");
		lvLotteryPrize = (LvLotteryPrize) dao.load(LvLotteryPrize.class,lvLotteryPrize.getId());

		if (logger.isInfoEnabled()) {
			logger.info("***** LvLotteryPrizeServiceImpl.get() method end*****");
		}
		return  lvLotteryPrize;
	}
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()) {
			logger.info("***** LvLotteryPrizeServiceImpl.del() method begin*****");
		}
		LvLotteryPrize lvLotteryPrize = get(dto);
		dao.delete(  lvLotteryPrize);
		
		if (logger.isInfoEnabled()) {
			logger.info("***** LvLotteryPrizeServiceImpl.del() method end*****");
		}
	}

	public void delList(Dto dto) throws ServiceException {

	}
	/**
	 * 保存
	 */
	public LvLotteryPrize save(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()) {
			logger.info("***** LvLotteryPrizeServiceImpl.save() method begin*****");
		}
		BaseUsers users= (BaseUsers) dto.get("users");
		LvLotteryPrize lvLotteryPrize = (LvLotteryPrize) dto.get("lvLotteryPrize");
		dao.save(lvLotteryPrize);

		if (lvLotteryPrize.getPrizeType() == 1) {
			//调用优惠券生产入库程序
			lvActivityService.createBatchCoupon(lvLotteryPrize.getActivityCode(), 
					                            lvLotteryPrize.getPrizeTotal(), 
					                            ActivityConstant.GIVEN_TYPE_NUM_COUPON, 
					                            lvLotteryPrize.getPrizeCode(), 
					                            CouponConstant.TYPE_BELOW_LINE, 
					                            users.getUserName(),
					                            lvLotteryPrize.getCode());
			
		}
		if (logger.isInfoEnabled()) {
			logger.info("***** LvLotteryPrizeServiceImpl.save() method end*****");
		}
		return lvLotteryPrize;
	}
	/**
	 * 更新
	 */
	public LvLotteryPrize update(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()) {
			logger.info("***** LvLotteryPrizeServiceImpl.update() method begin*****");
		}
		LvLotteryPrize lvLotteryPrize = (LvLotteryPrize) dto.get("lvLotteryPrize");
		dao.update(lvLotteryPrize);

		if (lvLotteryPrize.getPrizeType() == 1&& ObjectUtils.isNotEmpty(lvLotteryPrize.getAddNumber())) {
			Integer createNum = lvLotteryPrize.getAddNumber();
			//调用优惠券生产入库程序
			lvActivityService.createBatchCoupon(lvLotteryPrize.getActivityCode(), 
					                            createNum, 
					                            ActivityConstant.GIVEN_TYPE_NUM_COUPON, 
					                            lvLotteryPrize.getPrizeCode(), 
					                            CouponConstant.TYPE_BELOW_LINE, 
					                            lvLotteryPrize.getModifyUserName(),
					                            lvLotteryPrize.getCode());
		}

		if (logger.isInfoEnabled()) {
			logger.info("***** LvLotteryPrizeServiceImpl.update() method end*****");
		}
		return  lvLotteryPrize;
	}

}
