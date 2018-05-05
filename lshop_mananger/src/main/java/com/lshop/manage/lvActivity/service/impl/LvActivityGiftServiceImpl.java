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
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.lshop.common.activity.constant.ActivityConstant;
import com.lshop.common.pojo.logic.LvActivityGift;
import com.lshop.common.pojo.logic.LvActivityProduct;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvActivity.service.LvActivityGiftService;
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
 */@Service("LvActivityGiftService")
public class LvActivityGiftServiceImpl extends BaseServiceImpl implements LvActivityGiftService {
	@Resource private HibernateBaseDAO dao;
	
	
	/**
	 * 获得所有
	 */
	public List findAll(Dto dto) throws ServiceException {
		List list=null;
		String activityCode=(String) dto.get("activityCode"); 
	    if(ObjectUtils.isNotEmpty(activityCode)){
			String hql=" select t.id as id,t.giftCode as giftCode,t.giftEveryNum as giftEveryNum," +
					   " t.giftTotalNum as giftTotalNum,t.giftHaveNum as giftHaveNum,t.giftSerplusNum as giftSerplusNum," +
					   " t.orderValue as orderValue,pg.giftName as giftName " +
					   " from LvActivityGift t,LvPubGift pg " +
					   " where t.giftCode=pg.code " +
					   " and t.activityCode =:activityCode " +
					   " order by t.orderValue desc ";
			Map<String,Object> param=new HashMap<String, Object>();
			param.put("activityCode", activityCode);
			list=dao.getMapListByHql(hql, param).getList();
		}
		return list;
	}
	
    /**
     * 
     * @Method: saveBatch 
     * @Description:  [批量保存活动赠品信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2015-1-9 下午2:12:11]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2015-1-9 下午2:12:11]   
     * @UpdateRemark: [说明本次修改内容]  
     * @param giftList 赠品集合
     * @param acitivityCode 活动编码
     * @return Boolean
     */
	public Boolean saveBatch(List<LvActivityGift> giftList,String acitivityCode){
		 if(ObjectUtils.isNotEmpty(giftList)){
			 for (LvActivityGift activityGift : giftList) {
				 activityGift.setActivityCode(acitivityCode);
				 activityGift.setGiftHaveNum(0);
				 activityGift.setGiftSerplusNum(activityGift.getGiftTotalNum());
				 activityGift.setCode(CodeUtils.getCode());
				 activityGift.setCreateTime(new Date());
				 dao.save(activityGift);
			}
		 }
		return true;
	}	
	
	/**
	 * 
	 * @Method: updateGiftNum 
	 * @Description:  [更新活动赠品数目]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-8 上午11:12:29]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-8 上午11:12:29]   
	 * @UpdateRemark: [说明本次修改内容]   
	 * @param giftEveryNum 每次赠送数量
	 * @param giftTotalNum 赠品总数
	 * @param giftSerplusNum 剩余赠品数量
	 * @param orderValue 排序值
	 * @param id 活动赠品编号
	 * @throws ServiceException 
	 * @return boolean
	 */
	public boolean updateGiftNum(Integer giftEveryNum,Integer giftTotalNum,Integer giftSerplusNum,Integer orderValue,Integer id)throws ServiceException{
		 //更新活动赠品统计数目
		 String hql=" update LvActivityGift set giftEveryNum=:giftEveryNum," +
		 		    " giftTotalNum=:giftTotalNum," +
		 		    " giftSerplusNum=:giftSerplusNum," +
		 		    " orderValue=:orderValue" +
		 		    " where id=:id";
		 Map<String,Object> param=new HashMap<String, Object>();
		 param.put("giftEveryNum", giftEveryNum);
		 param.put("giftTotalNum", giftTotalNum);
		 param.put("giftSerplusNum", giftSerplusNum);
		 param.put("orderValue", orderValue);
		 param.put("id", id);
		 dao.update(hql, param);
		 
		 return true;
	}
	
	/**
	 * 
	 * @Method: isExsitsAcivityByGiftCode 
	 * @Description:  [根据赠品编号判断是否存在有效的活动信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-29 下午3:18:54]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-29 下午3:18:54]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param giftCode 赠品编号
	 * @return boolean
	 */
	public boolean isExsitsAcivityByGiftCode(String giftCode){
		String hql = " from LvActivityGift t where t.giftCode=:giftCode "
				   + " and EXISTS(select 1 from LvActivity ta where ta.code=t.activityCode and ta.status=:status and ta.startTime<=:startTime and ta.endTime>=:endTime)";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("status", ActivityConstant.STATUS_USE);
		param.put("startTime", new Date());
		param.put("endTime", new Date());
		param.put("giftCode", giftCode);
		List list = dao.find(hql, param);
		if (ObjectUtils.isNotEmpty(list)) {
			return true;
		}
		return false;
	}

}
