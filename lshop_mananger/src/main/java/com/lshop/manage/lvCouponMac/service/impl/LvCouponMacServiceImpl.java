/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvCouponMac.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.lshop.manage.lvCouponMac.service.LvCouponMacService;
import com.lshop.common.pojo.logic.LvCouponMac;
import com.lshop.common.util.DateUtils;
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
 */
@Service("LvCouponMacService")
public class LvCouponMacServiceImpl extends BaseServiceImpl implements LvCouponMacService {
		private static final Log logger = LogFactory.getLog(LvCouponMacServiceImpl.class);
	/**
	 * 获得所有
	 */
	@Resource private HibernateBaseDAO dao;
	public List<LvCouponMac> findAll(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 分页查询
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination findPage(Dto dto) throws ServiceException {
		SimplePage page = (SimplePage)dto.get("page");
		LvCouponMac lvCouponMac = (LvCouponMac)dto.get("lvCouponMac");
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
		Map<String ,Object> params=new HashMap<String ,Object>();
        StringBuilder sql = new StringBuilder("select t from LvCouponMac t where status<>-1 ");
		if(ObjectUtils.isNotEmpty(lvCouponMac)){
			if(ObjectUtils.isNotEmpty(lvCouponMac.getUserEmail())) {
	        	sql.append(" and  t.userEmail like :userEmail ");
	        	params.put("userEmail", "%"+lvCouponMac.getUserEmail().trim()+"%");
	        }
        	if(ObjectUtils.isNotEmpty(lvCouponMac.getMac())) {
	        	sql.append(" and  t.mac like :mac ");
	        	params.put("mac", "%"+lvCouponMac.getMac().trim()+"%");
	        }
        	if(ObjectUtils.isNotEmpty(lvCouponMac.getCouponCode())) {
	        	sql.append(" and  t.couponCode like :couponCode ");
	        	params.put("couponCode", "%"+lvCouponMac.getCouponCode().trim()+"%");
	        }
        	if(ObjectUtils.isNotEmpty(lvCouponMac.getStatus())) {
	        	sql.append(" and  t.status=:status");
	        	params.put("status", lvCouponMac.getStatus());
	        }
            if(ObjectUtils.isNotEmpty(lvCouponMac.getStartTime())&&ObjectUtils.isNotEmpty(lvCouponMac.getEndTime())){//下单时间
            	sql.append(" and t.createTime>:startTime" +
            		       " and t.createTime<:endTime");
            	params.put("startTime", DateUtils.convertToDateTime(lvCouponMac.getStartTime()+" 00:00:00"));
            	params.put("endTime", DateUtils.convertToDateTime(lvCouponMac.getEndTime()+" 23:59:59"));
            }
		}        	
	        	

        if(ObjectUtils.isNotEmpty(dto.getAsString("orderField"))) {
            sql.append(" order by t." + dto.getAsString("orderField") + " " + dto.getAsString("orderDirection"));
        } 
		Finder finder = Finder.create(sql.toString());
		finder.setParams(params);
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		return pag;
	}
	/**
	 * 获得单独的实体信息
	 */
	public LvCouponMac get(Dto dto) throws ServiceException {
		 LvCouponMac lvCouponMac = (LvCouponMac)dto.get("lvCouponMac");
		 lvCouponMac = (LvCouponMac)dao.load(LvCouponMac.class, lvCouponMac.getId());
		return  lvCouponMac;
	}
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		 LvCouponMac lvCouponMac = (LvCouponMac)dto.get("lvCouponMac");
		 String hql="update LvCouponMac set status=-1 where id=:id";
		 Map<String,Object> param=new HashMap<String, Object>();
		 param.put("id", lvCouponMac.getId());
		 dao.update(hql, param);;

	}

	
	
	/**
	 * 
	 * @Method: exportCouponMac 
	 * @Description:  [导出MAC以旧换新详情信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-17 上午09:38:55]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-17 上午09:38:55]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public List exportCouponMac(Dto dto)throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponMacServiceImpl.exportCouponMac() method begin*****");
		}
		// TODO Auto-generated method stub
		List list = new ArrayList();
		String hql="FROM LvCouponMac where id in (" + dto.getAsString("ids") + ")";

		List listPage = dao.find(hql, null);
		if (listPage != null) {
			String[] tempArray = null;
			// 增加表头
			String[] title = new String[7];
			title[0] = "用户邮箱";
			title[1] = "MAC";
			title[2] = "优惠码";
			title[3] = "兑换来源";
			title[4] = "IP地址";
			title[5] = "创建时间";
			title[6] = "状态";
			list.add(title);
			for (int i = 0; i < listPage.size(); i++) {
				tempArray = new String[7];
				LvCouponMac cm =(LvCouponMac) listPage.get(i);
				tempArray[0] = cm.getUserEmail();
				tempArray[1] = cm.getMac();
				tempArray[2] = cm.getCouponCode();
				tempArray[3] = cm.getSourceUrl();
				tempArray[4] = cm.getIp();
				tempArray[5] = DateUtils.formatDate(cm.getCreateTime(), "yyyy-MM-dd HH:mm:ss");
				String statusStr="";
				if(ObjectUtils.isNotEmpty(cm.getStatus())){
					if(cm.getStatus()==0){
						statusStr="已领券";
					}else if(cm.getStatus()==1){
						statusStr="已下单";
					}
				}
				tempArray[6]=statusStr;
				list.add(tempArray);
			}

		}
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponMacServiceImpl.exportCouponMac() method end*****");
		}
		return list;
	}
	

}
