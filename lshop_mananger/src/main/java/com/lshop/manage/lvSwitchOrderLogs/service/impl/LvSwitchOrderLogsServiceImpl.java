/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvSwitchOrderLogs.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.BeanUtils;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvOrderAddress;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.pojo.logic.LvSwitchOrderLogs;
import com.lshop.common.util.Constants;
import com.lshop.common.util.DateUtils;
import com.lshop.manage.lvOrder.service.impl.LvOrderServiceImpl;
import com.lshop.manage.lvSwitchOrderLogs.service.LvSwitchOrderLogsService;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Service("LvSwitchOrderLogsService")
public class LvSwitchOrderLogsServiceImpl extends BaseServiceImpl implements LvSwitchOrderLogsService {
	 private static final Log logger	= LogFactory.getLog(LvSwitchOrderLogsServiceImpl.class);
	 @Resource private HibernateBaseDAO dao;

	/**
	 * 分页查询
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination findPage(Dto dto) throws ServiceException {
		SimplePage page = (SimplePage)dto.get("page");
		 LvSwitchOrderLogs lvSwitchOrderLogs = (LvSwitchOrderLogs)dto.get("model");
		 String userEmail=(String) dto.get("userEmail");
		 String relName=(String) dto.get("relName");
		 String storeName=(String) dto.get("storeName");
		 Map<String ,Object> params=new HashMap<String ,Object>();
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
		 StringBuilder hql = new StringBuilder("select t.id as id ,t.orderId as orderId,t.oldStoreCode as oldStoreCode,t.targetStoreCode as targetStoreCode," +
		 		" t.createTime as createTime,t.createUserName as createUserName,o.totalPrice as totalPrice,la.relName as relName " +
		 		" from LvSwitchOrderLogs t,LvOrder o,LvOrderAddress la,LvStore ls" +
		 		" where o.oid=t.orderId " +
		 		" and o.oid=la.orderId " +
		 		" and ls.code=t.oldStoreCode ");
		 
		 if(lvSwitchOrderLogs!=null){
			 if(ObjectUtils.isNotEmpty(lvSwitchOrderLogs.getOrderId())){
				hql.append(" and t.orderId like :orderId");
				params.put("orderId", "%"+lvSwitchOrderLogs.getOrderId()+"%");
			 }
		 }
		 if(ObjectUtils.isNotEmpty(userEmail)){
			 hql.append(" and o.userEmail like :userEmail");
			 params.put("userEmail", "%"+userEmail+"%");
		 }
		 if(ObjectUtils.isNotEmpty(relName)){
			 hql.append(" and la.relName like :relName");
			 params.put("relName", "%"+relName+"%");
		 }
		 if (ObjectUtils.isNotEmpty(storeName)) {
			 hql.append(" and ls.name like :name");
			 params.put("name", "%"+storeName+"%");
		}
		 hql.append(" order by t.createTime desc"); 
		Pagination pag=dao.getMapListByHql(hql.toString(), page.getPageNum(), page.getNumPerPage(), params);
		return pag;
	}
	
	/**
	 * 
	 * @Method: exportLogs 
	 * @Description:  [导出转单日志]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-2-25 上午09:54:18]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-2-25 上午09:54:18]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public List exportLogs(Dto dto)throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvSwitchOrderLogsServiceImpl.exportLogs() method begin*****");
		}
		String ids=(String) dto.get("ids");
		List list = new ArrayList();
		 String hql="select t.id as id ,t.orderId as orderId,t.oldStoreCode as oldStoreCode,t.targetStoreCode as targetStoreCode," +
	 		" t.createTime as createTime,t.createUserName as createUserName,o.totalPrice as totalPrice,la.relName as relName " +
	 		" from LvSwitchOrderLogs t,LvOrder o,LvOrderAddress la" +
	 		" where o.oid=t.orderId " +
	 		" and o.oid=la.orderId" +
	 		" and t.id in("+ids+")";
		Pagination pageTmp=dao.getMapListByHql(hql,null);
		List listPage=pageTmp.getList();
		if (listPage != null) {
			String[] tempArray = null;
			LvOrder lvOrder = null;
			LvOrderAddress lvOrderAddress = null;
			// 增加表头
			String[] title = new String[22];
			title[0] = "原有店铺";
			title[1] = "订单编号";
			title[2] = "收件人姓名";
			title[3] = "订单总金额";
			title[4] = "转单目标店铺";
			title[5] = "操作时间";
			title[6] = "操作人";
			list.add(title);
			for (int i = 0; i < listPage.size(); i++) {
				tempArray = new String[22];
				Map map=  (Map) listPage.get(i);
				LvStore lvStore= (LvStore) dao.findUniqueByProperty(LvStore.class, "code", String.valueOf(map.get("oldStoreCode")));//源店铺信息
				LvStore targetStore= (LvStore) dao.findUniqueByProperty(LvStore.class, "code", String.valueOf(map.get("targetStoreCode")));//目标店铺信息
				
				tempArray[0] = lvStore.getName();
				tempArray[1] = String.valueOf(map.get("orderId"));
				tempArray[2] = String.valueOf(map.get("relName"));
				tempArray[3] = String.valueOf(map.get("totalPrice"));
				tempArray[4] = targetStore.getName();
				tempArray[5] = String.valueOf(map.get("createTime"));
				tempArray[6] = String.valueOf(map.get("createUserName"));
				list.add(tempArray);
			}

		}
		if (logger.isInfoEnabled()){
			logger.info("***** LvSwitchOrderLogsServiceImpl.exportLogs() method end*****");
		}
		return list;
	}
	
}
