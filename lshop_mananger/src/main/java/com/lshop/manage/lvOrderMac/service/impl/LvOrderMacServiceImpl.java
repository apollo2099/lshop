/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvOrderMac.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.lshop.manage.lvOrderMac.service.LvOrderMacService;
import com.lshop.common.pojo.logic.LvOrderMac;
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
 */@Service("LvOrderMacService")
public class LvOrderMacServiceImpl extends BaseServiceImpl implements LvOrderMacService {
	 private static final Log logger = LogFactory.getLog(LvOrderMacServiceImpl.class);
	/**
	 * 获得所有
	 */
	@Resource private HibernateBaseDAO dao;
	public List<LvOrderMac> findAll(Dto dto) throws ServiceException {
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
		 LvOrderMac lvOrderMac = (LvOrderMac)dto.get("lvOrderMac");
		 Map<String ,Object> params=new HashMap<String ,Object>();
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from LvOrderMac t where status <>-1 ");
        if(ObjectUtils.isNotEmpty(lvOrderMac)){
        	if(ObjectUtils.isNotEmpty(lvOrderMac.getOrderId())) {
	        	sql.append(" and  t.orderId like :orderId ");
	        	params.put("orderId", "%"+lvOrderMac.getOrderId().trim()+"%");
	        }
        	if(ObjectUtils.isNotEmpty(lvOrderMac.getMac())) {
	        	sql.append(" and  t.mac like :mac ");
	        	params.put("mac", "%"+lvOrderMac.getMac().trim()+"%");
	        }
        	if(ObjectUtils.isNotEmpty(lvOrderMac.getUserEmail())) {
	        	sql.append(" and  t.userEmail like :userEmail ");
	        	params.put("userEmail", "%"+lvOrderMac.getUserEmail().trim()+"%");
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
	public LvOrderMac get(Dto dto) throws ServiceException {
		 LvOrderMac lvOrderMac = (LvOrderMac)dto.get("lvOrderMac");
		 lvOrderMac = (LvOrderMac)dao.load(LvOrderMac.class, lvOrderMac.getId());
		 return  lvOrderMac;
	}
	
	/**
	 * 根据订单号获得单独的实体信息
	 */
	public LvOrderMac findByOrderId(String orderId) throws ServiceException {
		 LvOrderMac lvOrderMac=null;
		 if(ObjectUtils.isNotEmpty(orderId)){
			 String hql="FROM LvOrderMac t where t.status <>-1 and orderId=:orderId";
			 Map<String,Object> param=new HashMap<String, Object>();
			 param.put("orderId", orderId);
			 lvOrderMac=(LvOrderMac) dao.findUnique(hql, param);
		 }
		 return  lvOrderMac;
	}
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		 LvOrderMac lvOrderMac = (LvOrderMac)dto.get("lvOrderMac");
		 
		 String hql="update LvOrderMac set status=-1 where id=:id";
		 Map<String,Object> param=new HashMap<String, Object>();
		 param.put("id", lvOrderMac.getId());
		 dao.update(hql, param);;
		 dao.delete(  lvOrderMac);
	}

	@Override
	public List exportOrderMac(Dto dto)throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponMacServiceImpl.exportCouponMac() method begin*****");
		}
		// TODO Auto-generated method stub
		List list = new ArrayList();
		String hql="FROM LvOrderMac t where t.status <>-1  and id in (" + dto.getAsString("ids") + ")";

		List listPage = dao.find(hql, null);
		if (listPage != null) {
			String[] tempArray = null;
			// 增加表头
			String[] title = new String[26];
			title[0] = "订单号";
			title[1] = "mac";
			title[2] = "邮箱";
			title[3] = "电话";
			title[4] = "兑换来源";
			title[5] = "IP地址";
			title[6] = "创建时间";
			list.add(title);
			for (int i = 0; i < listPage.size(); i++) {
				tempArray = new String[26];
				LvOrderMac om =(LvOrderMac) listPage.get(i);
				tempArray[0] = om.getOrderId();
				tempArray[1] = om.getMac();
				tempArray[2] = om.getUserEmail();
				tempArray[3] = om.getTel();
				tempArray[4] = om.getSourceUrl();
				tempArray[5] = om.getIp();
				tempArray[6] = DateUtils.formatDate(om.getCreateTime(), "yyyy-MM-dd HH:mm:ss");				
				list.add(tempArray);
			}

		}
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponMacServiceImpl.exportCouponMac() method end*****");
		}
		return list;
	}
	


}
