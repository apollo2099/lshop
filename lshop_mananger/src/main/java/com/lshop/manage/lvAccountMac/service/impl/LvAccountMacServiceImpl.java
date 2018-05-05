/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvAccountMac.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.lshop.manage.lvAccountMac.action.LvAccountMacAction;
import com.lshop.manage.lvAccountMac.service.LvAccountMacService;
import com.lshop.common.pojo.logic.LvAccountMac;
import com.lshop.common.pojo.logic.LvCouponMac;
import com.lshop.common.util.DateUtils;
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
 */@Service("LvAccountMacService")
public class LvAccountMacServiceImpl extends BaseServiceImpl implements LvAccountMacService {
	 private static final Log logger	= LogFactory.getLog(LvAccountMacServiceImpl.class);
	 
	 @Resource private HibernateBaseDAO dao;
	 /**
	 * 获得所有
	 */
	public List<LvAccountMac> findAll(Dto dto) throws ServiceException {
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
		 LvAccountMac lvAccountMac = (LvAccountMac)dto.get("lvAccountMac");
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
		Map<String ,Object> params=new HashMap<String ,Object>();
        StringBuilder sql = new StringBuilder("select t from LvAccountMac t where 1=1 ");
        if(ObjectUtils.isNotEmpty(lvAccountMac)){
        	if(ObjectUtils.isNotEmpty(lvAccountMac.getUserEmail())) {
	        	sql.append(" and  t.userEmail like :userEmail ");
	        	params.put("userEmail", "%"+lvAccountMac.getUserEmail().trim()+"%");
	        }
        	if(ObjectUtils.isNotEmpty(lvAccountMac.getMac())) {
	        	sql.append(" and  t.mac like :mac ");
	        	params.put("mac", "%"+lvAccountMac.getMac().trim()+"%");
	        }
        	if(ObjectUtils.isNotEmpty(lvAccountMac.getSourceUrl())) {
	        	sql.append(" and  t.sourceUrl like :sourceUrl ");
	        	params.put("sourceUrl", "%"+lvAccountMac.getSourceUrl().trim()+"%");
	        }
	        if(ObjectUtils.isNotEmpty(lvAccountMac.getStatus())) {
	        	sql.append(" and  t.status = :status ");
	        	params.put("status", lvAccountMac.getStatus());
	        }	
            if(ObjectUtils.isNotEmpty(lvAccountMac.getStartTime())&&ObjectUtils.isNotEmpty(lvAccountMac.getEndTime())){//下单时间
            	sql.append(" and t.createTime>:startTime" +
            		       " and t.createTime<:endTime");
            	params.put("startTime", DateUtils.convertToDateTime(lvAccountMac.getStartTime()+" 00:00:00"));
            	params.put("endTime", DateUtils.convertToDateTime(lvAccountMac.getEndTime()+" 23:59:59"));
            }
        }

        sql.append(" order by t.createTime desc");
		Finder finder = Finder.create(sql.toString());
		finder.setParams(params);
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		return pag;
	}
	/**
	 * 获得单独的实体信息
	 */
	public LvAccountMac get(Dto dto) throws ServiceException {
		 LvAccountMac lvAccountMac = (LvAccountMac)dto.get("lvAccountMac");
		 lvAccountMac = (LvAccountMac)dao.load(LvAccountMac.class, lvAccountMac.getId());
		return  lvAccountMac;
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
	public List exportAccountMac(Dto dto)throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountMacServiceImpl.exportAccountMac() method begin*****");
		}
		// TODO Auto-generated method stub
		List list = new ArrayList();
		String hql="FROM LvAccountMac where id in (" + dto.getAsString("ids") + ")";

		List listPage = dao.find(hql, null);
		if (listPage != null) {
			String[] tempArray = null;
			// 增加表头
			String[] title = new String[8];
			title[0] = "用户邮箱";
			title[1] = "MAC";
			title[2] = "联系手机";
			title[3] = "联系固话";
			title[4] = "兑换来源";
			title[5] = "IP地址";
			title[6] = "创建时间";
			title[7] = "状态";
			list.add(title);
			for (int i = 0; i < listPage.size(); i++) {
				tempArray = new String[8];
				LvAccountMac cm =(LvAccountMac) listPage.get(i);
				tempArray[0] = cm.getUserEmail();
				tempArray[1] = cm.getMac();
				tempArray[2] = cm.getContactPhone();
				tempArray[3] = cm.getContactTel();
				tempArray[4] = cm.getSourceUrl();
				tempArray[5] = cm.getIp();
				tempArray[6] = DateUtils.formatDate(cm.getCreateTime(), "yyyy-MM-dd HH:mm:ss");
				String statusStr="";
				if(ObjectUtils.isNotEmpty(cm.getStatus())){
					if(cm.getStatus()==1){
						statusStr="已兑换";
					}else if(cm.getStatus()==-1){
						statusStr="已下单";
					}
				}
				tempArray[7]=statusStr;
				list.add(tempArray);
			}

		}
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountMacServiceImpl.exportAccountMac() method end*****");
		}
		return list;
	}
	


}
